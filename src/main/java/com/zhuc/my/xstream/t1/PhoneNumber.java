package com.zhuc.my.xstream.t1;

public class PhoneNumber {
	private int code;
	private String number;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneNumber(int code, String number) {
		super();
		this.code = code;
		this.number = number;
	}

	public PhoneNumber() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PhoneNumber [code=" + code + ", number=" + number + "]";
	}

}