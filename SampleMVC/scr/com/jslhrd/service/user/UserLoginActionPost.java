package com.jslhrd.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jslhrd.domain.user.UserDAO;
import com.jslhrd.domain.user.UserVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.SHA256Util;



public class UserLoginActionPost implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		String passwd = SHA256Util.getEncSHA256(request.getParameter("passwd"));
		UserDAO dao = UserDAO.getInstance();
		int row = dao.userLogin(userid, passwd); // 1 = 성공 , 0 - 비번 오류 , -1 = id 오류
		request.setAttribute("row", row);
		if(row == 1) {
			UserVO vo = dao.userSelect(userid);
			HttpSession session = request.getSession(); // 세션 객체 생성
			session.setAttribute("user", vo); // 세션 정보 담기
			session.setMaxInactiveInterval(1800); // 30분간 유지
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_login_pro.jsp");
		dispatcher.forward(request, response);	
	}
}
