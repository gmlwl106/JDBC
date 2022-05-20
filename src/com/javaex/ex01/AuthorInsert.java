package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorInsert {
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
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?, ?) ";
			
			System.out.println(query);
			
			List<Author> authors = new ArrayList<Author>();
			authors.add(new Author("김문열", "경북 영양"));
			authors.add(new Author("박경리", "경상남도 통영"));
			authors.add(new Author("유시민", "17대 국회의원"));
			authors.add(new Author("기안84", "기안동에 산 84년생"));
			authors.add(new Author("강풀", "온라인 만화가 1세대"));
			authors.add(new Author("김영하", "알뜰신잡"));
			
			int count = 0;
			
			for(Author a : authors) {
				
				//바인딩
				pstmt = conn.prepareStatement(query); //문자열 쿼리로 만들기
				pstmt.setString(1, a.getName()); //query의 ? 안에 들어가는 문자열. *숫자는 위치*
				pstmt.setString(2, a.getDesc());
				
				//실행
				pstmt.executeUpdate(); //쿼리문 실행
				count++;
			
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
