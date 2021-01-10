package exSample.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import exSample.util.*;

public class UserDAO {
	private UserDAO() {}
	private static UserDAO instance = new UserDAO();
    
	public static UserDAO getInstance() {
		return instance;
	}

	public int userInsert(UserVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "insert into tbl_users_sec(name, userid, passwd, tel, email, first_time) "
				+ "values(?, ?,?,?,?,SYSDATE)";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			row = pstmt.executeUpdate();
			

		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	
	public int userCheck(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "select userid from tbl_users_sec where userid=?";
			
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				row = 1;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	
	
	public int userLogin(String userid, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		
//		String query = "select count(*) from tbl_users where userid=? and passwd=?";
		String query ="select passwd from tbl_users_sec where userid=?";
			
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbpass = rs.getString("passwd");
				System.out.println(dbpass);
				if(dbpass.equals(passwd)) {
					query = "update tbl_users_sec set last_time=sysdate where userid=?";
//					pstmt.getConnection().prepareStatement(query);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, userid);
					pstmt.executeUpdate();
					row = 1;
				}else {
					row = 0;
				}
			}else {
				row=-1; // id 존재 x
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	
	public UserVO userSelect(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserVO vo = new UserVO();
		String query = "select * from tbl_users_sec where userid=?";
			
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setUserid(rs.getString("userid"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setEmail(rs.getString("Email"));
				vo.setTel(rs.getString("tel"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	public int userModify(String passwd, String tel, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int row = 0;
		String query = "update tbl_users_sec set passwd=?,tel=? where userid=?";
		try {
			// conn = getConnection();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, passwd);
			pstmt.setString(2, tel);
			pstmt.setString(3, userid);
			row = pstmt.executeUpdate();
			

		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return row;
	}
	

	
}
