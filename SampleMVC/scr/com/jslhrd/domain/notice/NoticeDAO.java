package com.jslhrd.domain.notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jslhrd.util.DBManager;


public class NoticeDAO {
	private NoticeDAO() {}
	private static NoticeDAO instance = new NoticeDAO();
    
	public static NoticeDAO getInstance() {
		return instance;
	}
	
	//최근 등록된 글 3개 추출
	
	public List<NoticeVO> noticeListTop(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		String query="select * from notice where rownum <= 3 order by idx desc";
				
		List<NoticeVO> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				NoticeVO vo = new NoticeVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setSubject(rs.getString("subject"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		
		return list;
		
	}
	
	public int noticeCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select count(*) from notice";
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
	public int noticeCount(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select count(*) from notice where "+s_query;
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
		public List<NoticeVO> noticeList() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<NoticeVO> list = new ArrayList<NoticeVO>();

			String query = "select * from notice order by idx desc";
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					NoticeVO dto = new NoticeVO();
					dto.setIdx(rs.getInt("idx"));
					dto.setSubject(rs.getString("subject"));
					dto.setRegdate(rs.getString("Regdate"));
					dto.setReadcnt(rs.getInt("readcnt"));
					System.out.println(dto.getIdx());
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
			public List<NoticeVO> noticeList(int startpage, int endpage) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<NoticeVO> list = new ArrayList<NoticeVO>();

				String query = "select X.* from (select rownum as rnum, A.* from "
						+ "(select * from notice order by idx desc) A "
						+ "where rownum <= ?) X where X.rnum >= ?";
				
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, endpage);
					pstmt.setInt(2, startpage);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						NoticeVO dto = new NoticeVO();
						dto.setIdx(rs.getInt("idx"));
						dto.setSubject(rs.getString("subject"));
						dto.setRegdate(rs.getString("Regdate"));
						dto.setReadcnt(rs.getInt("readcnt"));
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
			public List<NoticeVO> noticeList(int startpage, int endpage, String s_query) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List<NoticeVO> list = new ArrayList<NoticeVO>();

				String query = "select X.* from (select rownum as rnum, A.* from \r\n" + 
						"(select * from notice order by idx desc) A where "+s_query+" and rownum <= ?) X\r\n" + 
						"where "+s_query+" and X.rnum >= ?";
				try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, endpage);
					pstmt.setInt(2, startpage);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						NoticeVO dto = new NoticeVO();
						dto.setIdx(rs.getInt("idx"));
						dto.setSubject(rs.getString("subject"));
						dto.setRegdate(rs.getString("Regdate"));
						dto.setReadcnt(rs.getInt("readcnt"));
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
	public void noticeHits(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "update notice set readcnt = readcnt+1 where idx = ?";
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
	
	
	public NoticeVO noticeSelect(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVO dto = new NoticeVO();
		
		String query = "select * from notice where idx = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setSubject(rs.getString("subject"));
				dto.setContents(rs.getString("contents"));
				dto.setRegdate(rs.getString("Regdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
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
	public int noticeWrite(NoticeVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "insert into notice(idx, subject, contents)"
				+ " values (tbl_notice_seq_idx.nextval, ?,?)";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContents());
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
	public int noticeModify(NoticeVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "update notice set subject=?, contents=? where idx=?";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getIdx());
			row = pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	
	public int noticeDelete(int idx, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "delete from notice where idx = ?";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			row = pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
		
}
