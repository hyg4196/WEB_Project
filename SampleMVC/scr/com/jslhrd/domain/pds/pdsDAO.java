package com.jslhrd.domain.pds;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.jslhrd.util.DBManager;



public class pdsDAO {
	private pdsDAO(){
		
	}
	private static pdsDAO instance = new pdsDAO();
	public static pdsDAO getInstance() {
		return instance;
	}
	
	public int pdsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select count(*) from tbl_pds";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return row;
	}
	
	public int pdsCount(String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		String sql = "select count(*) from tbl_pds where "+s_query;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return row;
	}
	
	public List<pdsVO> pdsList(int startpage, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<pdsVO> list = new ArrayList<pdsVO>();
		
		String sql = "select X.* from (select rownum as rnum, A.* from "
				+ "(select * from tbl_pds order by idx desc) A where rownum <= ?)"
				+ " X where X.rnum >= ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pdsVO vo = new pdsVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setPass(rs.getString("pass"));
				vo.setSubject(rs.getString("subject"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setFilename(rs.getString("filename"));

				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
	public List<pdsVO> pdsList(int startpage, int endpage, String s_query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<pdsVO> list = new ArrayList<pdsVO>();
		String sql = "select X.* from (select rownum as rnum,A.* from "
				+ "(select * from tbl_pds where "+s_query +" and rownum <=? order by idx desc)A) X\r\n" + 
				"where "+s_query+" and X.rnum >=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pdsVO vo = new pdsVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setPass(rs.getString("pass"));
				vo.setSubject(rs.getString("subject"));
				vo.setEmail(rs.getString("email"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setFilename(rs.getString("filename"));
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
	
	public int pdsWrite(pdsVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		String sql = "insert into tbl_pds(idx,name,email,subject, contents,pass, filename) "
				+ "values(tbl_pds_seq_idx.nextval,?,?,?,?,?,?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContents());
			pstmt.setString(5, vo.getPass());
			pstmt.setString(6, vo.getFilename());
			row = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return row;
	}
	
	
	public pdsVO pdsSelect(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pdsVO vo = new pdsVO();
		
		String sql = "select * from tbl_pds where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setPass(rs.getString("pass"));
				vo.setSubject(rs.getString("subject"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setFilename(rs.getString("filename"));
				vo.setContents(rs.getString("contents"));
				vo.setEmail(rs.getString("email"));

			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return vo;
	}
	
	public int pdsDelete(int idx, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		String sql = "delete from tbl_pds where idx = ? and pass = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return row;
	}
	
	
	public int pdsModify(pdsVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		String sql = "update tbl_pds set email = ?, subject = ?, contents = ?, filename = ? where idx = ? and pass = ? ";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getFilename());
			pstmt.setInt(5, vo.getIdx());
			pstmt.setString(6, vo.getPass());
			row = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return row;
	}


	public String getFile(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String pdsFilename ="";
		
		String sql = "select Filename from tbl_pds where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pdsFilename = rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBManager.close(conn, pstmt, rs);				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return pdsFilename;
	}
	
}
