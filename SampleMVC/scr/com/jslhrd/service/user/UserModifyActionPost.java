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

public class UserModifyActionPost implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();	// 세션을 이용해 접속된 아이디 비번 가져옴
		UserVO vo = (UserVO) session.getAttribute("user"); // vo 안에 현재 접속된 아이디 비번 정보 넣음
		String newpasswd = SHA256Util.getEncSHA256(request.getParameter("newpasswd"));
		String passwd = SHA256Util.getEncSHA256(request.getParameter("passwd"));
		String tel = request.getParameter("tel");
		String userid = request.getParameter("userid");
		
		
		int row =0;
		if(vo.getPasswd().equals(passwd)) {	// vo 비번과 입력된 비번이 일치하는지 확인
			UserDAO dao = UserDAO.getInstance();
			row = dao.userModify(newpasswd, tel,userid);
			session.invalidate();
		}
		
		request.setAttribute("row", row);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_modify_pro.jsp");
		dispatcher.forward(request, response);
		
	}
}
