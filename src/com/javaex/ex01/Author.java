package com.javaex.ex01;

public class Author {

	//필드
	private String name;
	private String desc;
	
	
	
	//생성자
	public Author() {
	}
	public Author(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	//메소드 gs
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	//메소드 일반
	
	
	
}
