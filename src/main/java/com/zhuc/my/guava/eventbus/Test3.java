package com.zhuc.my.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventBus eventBus = new EventBus("test");
		DeadEventListener deadEventListener = new DeadEventListener();
		eventBus.register(deadEventListener);

		eventBus.post(new TestEvent(200));
		eventBus.post(new TestEvent(300));

		// 如果没有消息订阅者监听消息， EventBus将发送DeadEvent消息，这时我们可以通过log的方式来记录这种状态。
		System.out.println("deadEvent:" + deadEventListener.isNotDelivered());

	}

}

class DeadEventListener {
	boolean notDelivered = false;

	@Subscribe
	public void listen(DeadEvent event) {
		// 如果EventBus发送的消息都不是订阅者关心的称之为Dead Event。
		notDelivered = true;
	}

	public boolean isNotDelivered() {
		return notDelivered;
	}
}
