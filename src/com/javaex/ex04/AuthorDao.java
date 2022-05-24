package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**********************************************
 * DAO(Data Access Object)
 * Database(오라클) 관련된 일을 하는 클래스 *
 ***********************************************/


public class AuthorDao {
	//필드
	private String id = "webdb";
	private String pw = "webdb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//생성자
	
	
	//메소드 gs
	
	
	//메소드-일반
	//DB 연결 메소드
	private void getConnection() {
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName(driver);
		
		// 2. Connection 얻어오기
		conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	
	
	//작가 등록 메소드
	public int authorInsert(AuthorVo authorVo) {
		int count = -1;
		try {
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비

			String query = "";
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 등록 되었습니다.");
			
			
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//자원 정리
			close();
		}
		
		
		// 4.결과처리
		return count;
	}

	//작가 삭제 메소드
	public int authorDelete(int authorId) {
		int count = -1;

		try {
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
			

			//실행
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제 되었습니다.");
			
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//자원 정리
			close();
		}
		// 4.결과처리
		return count;
	}

	//작가 수정 메소드
	public int authorUpdate(AuthorVo authorVo) {
		int count = -1;
		
		try {
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " update author ";
			query += " set author_name = ? ";
			query += "     ,author_desc = ? ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorID());
			
			count = pstmt.executeUpdate();
			
			
			
			// 4.결과처리
			System.out.println(count+"건이 수정 되었습니다.");
			
		}catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//자원 정리
			close();
		}
		return count;
	}

	//작가 전체 출력 메소드
	public List<AuthorVo> authorSelect() {

		//리스트 준비
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		try {
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " select * ";
			query += " from author ";
			
			System.out.println(query);
			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 쿼리로 만들기		
			
			
			//실행
			rs = pstmt.executeQuery();
			
			
			// 4.결과처리
			while(rs.next()) {
				/*int author_id = rs.getInt("author_id");
				String author_name = rs.getString("author_name");
				String author_desc = rs.getString("author_desc");*/
				
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);				
				
				authorList.add(new AuthorVo(authorId, authorName, authorDesc));
			} //알아서 끝까지 가면 while문 끝냄
			
			/*
			 * //authorList 출력 System.out.println(authorList.toString());
			 */
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//자원 정리
			close();
		}
		
		return authorList;
	}
	
	
	
	//자원정리 메소드
	private void close() {
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
