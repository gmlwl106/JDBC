package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInsert {

	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values (seq_book_id.nextval, ?, ?, ?, ?) ";
			
			System.out.println(query);
			
			List<Book> books = new ArrayList<Book>();
			books.add(new Book("우리들의 일그러진 영웅", "다림", "1998-02-22",1));
			books.add(new Book("삼국지", "민음사", "2002-03-01",1));
			books.add(new Book("토지", "마로니에북스", "2012-08-15",2));
			books.add(new Book("유시민의 글쓰기 특강", "생각의길", "2015-04-01",3));
			books.add(new Book("패션왕", "중앙북스(books)", "2012-02-22",4));
			books.add(new Book("순정만화", "재미주의", "2011-08-03",5));
			books.add(new Book("오직두사람", "문학동네", "2017-05-04",6));
			books.add(new Book("26년", "재미주의", "2012-02-04",5));
			
			int count = 0;
			for(Book b : books) {
				//바인딩
				pstmt = conn.prepareStatement(query); //문자열 쿼리로 만들기
				pstmt.setString(1, b.getTitle());
				pstmt.setString(2, b.getPubs());
				pstmt.setString(3, b.getPubDate());
				pstmt.setInt(4, b.getAuthorId());
				
				
				//실행
				count += pstmt.executeUpdate(); //쿼리문 실행
			}
			
			// 4.결과처리
			System.out.println(count+"건이 등록되었습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				/*if (rs != null) {
					rs.close();
				}*/
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
}
