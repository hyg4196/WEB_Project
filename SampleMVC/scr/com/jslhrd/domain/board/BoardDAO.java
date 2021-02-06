package com.jslhrd.domain.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jslhrd.domain.comments.CommentsDAO;
import com.jslhrd.util.DBManager;


public class BoardDAO {
	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
    
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//최근 등록된 글 3개 추출
	
	public List<BoardVO> boardListTop(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		CommentsDAO cd = CommentsDAO.getInstance();

		String query="select * from tbl_board where rownum <= 3 order by idx desc";
				
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				BoardVO vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setSubject(rs.getString("subject"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				int c = cd.commentsCount(rs.getInt("idx"));
				vo.setComments(c);
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		
		return list;
		
	}
	
	public int boardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select count(*) from tbl_board";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				row = rs.getInt(1);
			}

		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	
	// 검색 조건을 통한 게시글 총수를 구하는 메소드
	public int boardCount(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select count(*) from tbl_board where "+s_query;
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				row = rs.getInt(1);
			}

		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
//	
//	
	// 전체 게시글 목록 구하는 메소드 (페이지 처리 없음, 검색 가능 없음 )
		public List<BoardVO> boardList() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CommentsDAO cd = CommentsDAO.getInstance();
			List<BoardVO> list = new ArrayList<BoardVO>();

			String query = "select * from tbl_board order by idx desc";
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardVO dto = new BoardVO();
					dto.setIdx(rs.getInt("idx"));
					dto.setName(rs.getString("name"));
					dto.setSubject(rs.getString("subject"));
					dto.setRegdate(rs.getString("Regdate"));
					dto.setReadcnt(rs.getInt("readcnt"));
					int c = cd.commentsCount(rs.getInt("idx"));
					dto.setComments(c);
					list.add(dto);
				}

			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return list;
		}
		
		

		// 전체 게시글 목록 구하는 메소드 (페이지 처리, 검색기능은 없음. )
			public List<BoardVO> boardList(int startpage, int endpage) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<BoardVO> list = new ArrayList<BoardVO>();
				CommentsDAO cd = CommentsDAO.getInstance();

				String query = "select X.* from (select rownum as rnum, A.* from "
						+ "(select * from tbl_board order by idx desc) A "
						+ "where rownum <= ?) X where X.rnum >= ?";
				
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, endpage);
					pstmt.setInt(2, startpage);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						BoardVO dto = new BoardVO();
						dto.setIdx(rs.getInt("idx"));
						dto.setName(rs.getString("name"));
						dto.setSubject(rs.getString("subject"));
						dto.setRegdate(rs.getString("Regdate"));
						dto.setReadcnt(rs.getInt("readcnt"));
						int c = cd.commentsCount(rs.getInt("idx"));
						dto.setComments(c);
						list.add(dto);
					}

				}catch (Exception e) {
					// TODO: handle exception
				}finally {
					DBManager.close(conn, pstmt, rs);
				}
				
				return list;
			}
//			
			// 전체 게시글 목록 구하는 메소드 (페이지 처리, 검색기능 )
			public List<BoardVO> boardList(int startpage, int endpage, String s_query) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<BoardVO> list = new ArrayList<BoardVO>();
				CommentsDAO cd = CommentsDAO.getInstance();

				String query = "select X.* from (select rownum as rnum, A.* from \r\n" + 
						"(select * from tbl_board order by idx desc) A where "+s_query+" and rownum <= ?) X\r\n" + 
						"where "+s_query+" and X.rnum >= ?";
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, endpage);
					pstmt.setInt(2, startpage);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						BoardVO dto = new BoardVO();
						dto.setIdx(rs.getInt("idx"));
						dto.setName(rs.getString("name"));
						dto.setSubject(rs.getString("subject"));
						dto.setRegdate(rs.getString("Regdate"));
						dto.setReadcnt(rs.getInt("readcnt"));
						int c = cd.commentsCount(rs.getInt("idx"));
						dto.setComments(c);
						list.add(dto);
					}

				}catch (Exception e) {
					// TODO: handle exception
				}finally {
					DBManager.close(conn, pstmt, rs);
				}
				
				return list;
			}
//			
//	// 특정글 조회수 증가
	public void boardHits(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "update tbl_board set readcnt = readcnt+1 where idx = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	public BoardVO boardSelect(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO dto = new BoardVO();
		
		String query = "select * from tbl_board where idx = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContents(rs.getString("contents"));
				dto.setRegdate(rs.getString("Regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setEmail(rs.getString("email"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return dto;
	}
//	
	// 등록 메소드
	public int boardWrite(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "insert into tbl_board(idx, name, pass, subject, contents, email)"
				+ " values (board_seq_idx.nextval, ?,?,?,?,?)";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContents());
			pstmt.setString(5, vo.getEmail());
			row = pstmt.executeUpdate();
			

		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
//	
//	
	public int boardModify(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "update tbl_board set email =?, subject=?, contents=? where idx=? and pass =?";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			pstmt.setInt(4, vo.getIdx());
			pstmt.setString(5, vo.getPass());
			row = pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	
	public int boardDelete(int idx, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "delete from tbl_board where idx = ? and pass = ?";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			row = pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
		
}
