package com.zhuc.my.disruptor.t1;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory<LongEvent> {

	public LongEvent newInstance() {
		return new LongEvent();
	}
}