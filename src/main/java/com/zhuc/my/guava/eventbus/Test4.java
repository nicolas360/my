package com.zhuc.my.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class Test4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventBus eventBus = new EventBus("test");
		IntegerListener integerListener = new IntegerListener();
		NumberListener numberListener = new NumberListener();
		eventBus.register(integerListener);
		eventBus.register(numberListener);

		eventBus.post(new Integer(100));

		System.out.println("integerListener message:" + integerListener.getLastMessage());
		System.out.println("numberListener message:" + numberListener.getLastMessage());

		eventBus.post(new Long(200L));

		System.out.println("integerListener message:" + integerListener.getLastMessage());
		System.out.println("numberListener message:" + numberListener.getLastMessage());

	}

}

class NumberListener {

	private Number lastMessage;

	@Subscribe
	public void listen(Number integer) {
		lastMessage = integer;
		System.out.println("Message:" + lastMessage);
	}

	public Number getLastMessage() {
		return lastMessage;
	}
}

class IntegerListener {

	private Integer lastMessage;

	@Subscribe
	public void listen(Integer integer) {
		lastMessage = integer;
		System.out.println("Message:" + lastMessage);
	}

	public Integer getLastMessage() {
		return lastMessage;
	}
}
