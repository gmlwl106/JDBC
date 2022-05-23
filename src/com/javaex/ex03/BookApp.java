package com.javaex.ex03;

import java.util.List;
import java.util.Scanner;


public class BookApp {

	public static void main(String[] args) {
		AuthorDao authorDao = new AuthorDao();
		BookDao bookDao = new BookDao();
		
		authorDao.authorInsert("김문열","경북 영양");
		authorDao.authorInsert("박경리","경상남도 통영");
		authorDao.authorInsert("유시민","17대 국회의원");
		authorDao.authorInsert("기안84","기안동에서 산 84년생");
		authorDao.authorInsert("강풀","온라인 만화가 1세대");
		authorDao.authorInsert("김영하","알뜰신잡");
		
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22",1);
		bookDao.bookInsert("삼국지", "민음사", "2002-03-01",1);
		bookDao.bookInsert("토지", "마로니에북스", "2012-08-15",2);
		bookDao.bookInsert("유시민의 글쓰기 특강", "생각의길", "2015-04-01",3);
		bookDao.bookInsert("패션왕", "중앙북스(books)", "2012-02-22",4);
		bookDao.bookInsert("순정만화", "재미주의", "2011-08-03",5);
		bookDao.bookInsert("오직두사람", "문학동네", "2017-05-04",6);
		bookDao.bookInsert("26년", "재미주의", "2012-02-04",5);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어를 입력하세요 > ");
		String keyword = sc.nextLine();
		List<BookVo> bookList = bookDao.bookSelect(keyword);
		for(BookVo b : bookList) {
			System.out.println(b.getBookId() + " "
							+ b.getTitle() + " "
							+ b.getPubs() + " "
							+ b.getPub_date() + " "
							+ b.getAuthorName());
		}
		
		sc.close();
	}
}
