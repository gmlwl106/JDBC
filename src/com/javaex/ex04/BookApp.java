package com.javaex.ex04;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		BookDao bookDao = new BookDao();
		
		bookDao.bookInsert(new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22",1));
		bookDao.bookInsert(new BookVo("삼국지", "민음사", "2002-03-01",1));
		bookDao.bookInsert(new BookVo("토지", "마로니에북스", "2012-08-15",2));
		bookDao.bookInsert(new BookVo("유시민의 글쓰기 특강", "생각의길", "2015-04-01",3));
		bookDao.bookInsert(new BookVo("패션왕", "중앙북스(books)", "2012-02-22",4));
		bookDao.bookInsert(new BookVo("순정만화", "재미주의", "2011-08-03",5));
		bookDao.bookInsert(new BookVo("오직두사람", "문학동네", "2017-05-04",6));
		bookDao.bookInsert(new BookVo("26년", "재미주의", "2012-02-04",5));
		
		bookDao.bookUpdate(new BookVo(2, "사국지", "당김사", "2002-03-01", 1));
		
		//bookDao.bookDelete(2);
		
		List<BookVo> bookList = bookDao.bookSelect();
		for(BookVo b : bookList) {
			System.out.println(b.getBookId() + " "
							+ b.getTitle() + " "
							+ b.getPubs() + " "
							+ b.getPub_date() + " "
							+ b.getAuthorName());
		}
	}

}
