package com.jslhrd.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.service.Action;

public class UserLoginAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_login.jsp");
		dispatcher.forward(request, response);

	}
}
