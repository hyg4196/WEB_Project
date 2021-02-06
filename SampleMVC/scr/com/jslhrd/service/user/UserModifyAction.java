package com.jslhrd.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jslhrd.domain.user.UserVO;
import com.jslhrd.service.Action;


public class UserModifyAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_modify.jsp");
		dispatcher.forward(request, response);
		
	}
}
