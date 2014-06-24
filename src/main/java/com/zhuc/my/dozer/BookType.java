package com.zhuc.my.dozer;

/**
 * @author zhuc
 * @create 2013-3-28 下午2:43:16
 */
public class BookType {

	private String name;

	private String desc;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookType [name=" + name + ", desc=" + desc + "]";
	}

}
