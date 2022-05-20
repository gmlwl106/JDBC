package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BookSelect {

	public static void main(String[] args) {


		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //결과를 받아옴
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " select * ";
			query += " from book ";
			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 쿼리로 만들기		
			
			
			//실행
			rs = pstmt.executeQuery();
			
			System.out.println("BookID -- Title -- Pubs -- PubDate -- AuthorID");
			System.out.println("=========================================================================");
			// 4.결과처리
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			while(rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);	
				String pubDate = sdf.format(rs.getDate(4));
				int authorId = rs.getInt(5);
				
				
				
				//System.out.println(author_id + " " + author_name + " " + author_desc);
				System.out.println(bookId+" "+title+" "+pubs+" "+pubDate+" "+authorId);
				System.out.println("-------------------------------------------------------------------------");
				
			} //알아서 끝까지 가면 while문 끝냄
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
