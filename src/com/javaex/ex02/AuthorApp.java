package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		AuthorDao authorDao = new AuthorDao();
		/*
		authorDao.authorInsert("김문열","경북 영양");
		authorDao.authorInsert("박경리","경상남도 통영");
		authorDao.authorInsert("유시민","17대 국회의원");
		authorDao.authorInsert("기안84","기안동에서 산 84년생");
		authorDao.authorInsert("강풀","온라인 만화가 1세대");
		authorDao.authorInsert("김영하","알뜰신잡");
		*/
		//authorDao.authorDelete(5);
		
		//authorDao.authorUpdate("이문열", "삼국지 작가", 4);
		
		List<AuthorVo> authorList = authorDao.authorSelect();
		System.out.println("author_id author_name author_desc");
		for(AuthorVo author : authorList) {
			System.out.println(author.getAuthorID() + " " 
							+ author.getAuthorName() + " " 
							+ author.getAuthorDesc());
		}
		
	}
}
