package com.zhuc.my.mina.t4;

public class SmsObject {

	private String sender;// 短信发送者
	private String receiver;// 短信接受者
	private String message;// 短信内容

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SmsObject [sender=" + sender + ", receiver=" + receiver + ", message=" + message + "]";
	}

}