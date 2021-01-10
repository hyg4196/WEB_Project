package exSample.util;

import java.sql.*;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		String myDriver = "oracle.jdbc.driver.OracleDriver";
		String myUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String myId = "jslhrd46";
		String myPass = "1234";
		
		try {
			Class.forName(myDriver);
			conn = DriverManager.getConnection(myUrl, myId, myPass);
		}catch(Exception e){
			e.printStackTrace();
		}	
		return conn;
	}
	
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
