package com.zhuc.my.disruptor.t1;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class LongEventMain {
	public static void main(String[] args) throws Exception {
		// Executor that will be used to construct new threads for consumers
		Executor executor = Executors.newCachedThreadPool();

		// The factory for the event
		LongEventFactory factory = new LongEventFactory();

		// Specify the size of the ring buffer, must be power of 2.
		int bufferSize = 1024;

		// Construct the Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, executor);

		// Connect the handler
		disruptor.handleEventsWith(new LongEventHandler());

		// Start the Disruptor, starts all threads running
		disruptor.start();

		// Get the ring buffer from the Disruptor to be used for publishing.
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

		LongEventProducer producer = new LongEventProducer(ringBuffer);
		//		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);

		ByteBuffer bb = ByteBuffer.allocate(8);
		for (long l = 0;; l++) {
			bb.putLong(0, l);
			producer.onData(bb);
			//			Thread.sleep(1000l);
			TimeUnit.MILLISECONDS.sleep(1l);
		}

	}
}