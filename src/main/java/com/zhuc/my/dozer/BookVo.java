package com.zhuc.my.dozer;

/**
 * @author zhuc
 * @create 2013-3-28 下午1:58:34
 */
public class BookVo {

	private Integer id;

	private String nameVo;

	private String authorVo;

	private Integer capacity;

	private String day;

	private BookType bookType;

	private String source;

	/**
	 * @return the nameVo
	 */
	public String getNameVo() {
		return nameVo;
	}

	/**
	 * @return the authorVo
	 */
	public String getAuthorVo() {
		return authorVo;
	}

	/**
	 * @param nameVo the nameVo to set
	 */
	public void setNameVo(String nameVo) {
		this.nameVo = nameVo;
	}

	/**
	 * @param authorVo the authorVo to set
	 */
	public void setAuthorVo(String authorVo) {
		this.authorVo = authorVo;
	}

	/**
	 * @return the capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the bookType
	 */
	public BookType getBookType() {
		return bookType;
	}

	/**
	 * @param bookType the bookType to set
	 */
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookVo [id=" + id + ", nameVo=" + nameVo + ", authorVo=" + authorVo + ", capacity=" + capacity
				+ ", day=" + day + ", bookType=" + bookType + ", source=" + source + "]";
	}

}
