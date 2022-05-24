package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private String id = "webdb";
	private String pw = "webdb";
	private ResultSet rs = null;
	
	
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
	}
	
	//책 추가
	public int bookInsert(BookVo bookVo) {
		int count = -1;
		
		try {
			
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values (seq_book_id.nextval, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPub_date());
			pstmt.setInt(4, bookVo.getAuthorId());
			
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+"건이 등록되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
		return count;
	}

	//책 수정
	public int bookUpdate(BookVo bookVo) {
		int count = -1;
		
		try {
			
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " update book ";
			query += " set title = ? ";
			query += "     ,pubs = ? ";
			query += "     ,pub_date = ? ";
			query += "     ,author_id = ? ";
			query += " where book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPub_date());
			pstmt.setInt(4, bookVo.getAuthorId());
			pstmt.setInt(5, bookVo.getBookId());
			
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+"건이 수정 되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
		
		return count;
	}

	//책 삭제
	public int bookDelete(int bookId) {
		int count = -1;
		
		try {
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " delete book ";
			query += " where book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+"건이 삭제 되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
		
		return count;
	}

	//책 전체 출력
	public List<BookVo> bookSelect() {
		//리스트 준비
		List<BookVo> bookList = new ArrayList<BookVo>();

		try {
			//DB연결
			getConnection();
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " select  b.book_id ";
			query += " ,b.title ";
			query += " ,b.pubs ";
			query += " ,b.pub_date ";
			query += " ,a.author_name ";
			query += " from book b, author a ";
			query += " where b.author_id = a.author_id ";
			
			System.out.println(query);
			
			//바인딩
			pstmt = conn.prepareStatement(query); //문자열 쿼리로 만들기		
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			while(rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);	
				String pubDate = sdf.format(rs.getDate(4));
				String authorName = rs.getString(5);
				
				bookList.add(new BookVo(bookId, title, pubs, pubDate, authorName));
				
			} //알아서 끝까지 가면 while문 끝냄
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			close();
		}
		
		return bookList;
	}
	
	private void close() {
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
