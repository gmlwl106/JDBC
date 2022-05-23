package com.javaex.ex02;

public class BookVo {

	//필드
	private int bookId;
	private String title;
	private String pubs;
	private String pub_date;
	private String authorName;
	
	
	//생성자
	public BookVo() {
	}

	public BookVo(int bookId, String title, String pubs, String pub_date, String authorName) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorName = authorName;
	}
	
	//메소드 gs
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
	
	//메소드 일반
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date
				+ ", authorName=" + authorName + "]";
	}
	
	
}
