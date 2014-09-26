package com.zhuc.my.disruptor.t1;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
		System.out.println(sequence + ":" + endOfBatch);
		System.out.println("Event: " + JSON.toJSONString(event));
	}
}