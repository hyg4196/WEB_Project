package com.jslhrd.domain.comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jslhrd.util.DBManager;

public class CommentsDAO {
	private CommentsDAO() {}
	private static CommentsDAO instance = new CommentsDAO();
    
	public static CommentsDAO getInstance() {
		return instance;
	}
	
	//최근 등록된 글 3개 추출
	
	public List<CommentsVO> boardListTop(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		String query="select * from tbl_board where rownum <= 3 order by idx desc";
				
		List<CommentsVO> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()){
				CommentsVO vo = new CommentsVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setUserid(rs.getString("usreid"));
				vo.setContents(rs.getString("Contents"));
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
	
	public int commentsCount(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select count(*) from tbl_comments where idx=?";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
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
	
	public int commentsWrite(CommentsVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String query = "insert into tbl_comments(idx, userid, contents, regdate,num)"
				+ " values (?,?,?,sysdate,TBL_COMMENTS_NUM.nextval)";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getContents());
			row = pstmt.executeUpdate();

		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	
	public List<CommentsVO> CommentsList(int idx, int startpage, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommentsVO> list = new ArrayList<CommentsVO>();

		String query = "select X.* from (select rownum as rnum, A.* from  \r\n" + 
				"				(select * from tbl_comments where idx = "+idx+" order by num desc) A \r\n" + 
				"				where rownum <= "+endpage+") X where X.rnum >= "+startpage;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentsVO dto = new CommentsVO();
				dto.setIdx(rs.getInt("idx"));
				dto.setContents(rs.getString("contents"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setUserid(rs.getString("userid"));
				list.add(dto);
				System.out.println("확인");
			}

		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}

}
