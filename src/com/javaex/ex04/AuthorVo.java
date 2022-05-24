package com.javaex.ex04;

public class AuthorVo {

	//필드
	private int authorID;
	private String authorName;
	private String authorDesc;
	
	
	//생성자
	public AuthorVo() {
	}
	public AuthorVo(String authorName, String authorDesc) {
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	public AuthorVo(int authorID, String authorName, String authorDesc) {
		this.authorID = authorID;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	
	
	//메소드 gs
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	

	
	//메소드 일반
	@Override
	public String toString() {
		return "AuthorVo [authorID=" + authorID + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}
	
	
}
