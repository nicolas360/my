package com.zhuc.my.xstream.t1;

public class Person {
	private String firstname;
	private String lastname;
	private PhoneNumber phone;
	private PhoneNumber fax;

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the phone
	 */
	public PhoneNumber getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	/**
	 * @return the fax
	 */
	public PhoneNumber getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(PhoneNumber fax) {
		this.fax = fax;
	}

	public Person(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone + ", fax=" + fax + "]";
	}

}
