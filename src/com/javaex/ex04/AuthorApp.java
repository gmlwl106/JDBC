package com.javaex.ex04;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		AuthorDao authorDao = new AuthorDao();

		AuthorVo vo1 = new AuthorVo("김문열", "경북 영양");
		AuthorVo vo2 = new AuthorVo("박경리","경상남도 통영");
		AuthorVo vo3 = new AuthorVo("유시민","17대 국회의원");
		AuthorVo vo4 = new AuthorVo("기안84","기안동에서 산 84년생");
		AuthorVo vo5 = new AuthorVo("강풀","온라인 만화가 1세대");
		AuthorVo vo6 = new AuthorVo("김영하","알뜰신잡");
		
		authorDao.authorInsert(vo1);
		authorDao.authorInsert(vo2);
		authorDao.authorInsert(vo3);
		authorDao.authorInsert(vo4);
		authorDao.authorInsert(vo5);
		authorDao.authorInsert(vo6);
		
		AuthorVo uVo = new AuthorVo(7, "유재석", "개그맨");
		authorDao.authorUpdate(uVo);
		
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
