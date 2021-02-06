package com.jslhrd.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.user.UserDAO;
import com.jslhrd.service.Action;

public class UserCheckAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		UserDAO dao = UserDAO.getInstance();
		int row = dao.userCheck(userid);
		request.setAttribute("row", row);
		request.setAttribute("userid", userid);
		System.out.println(userid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_idcheck.jsp");

		dispatcher.forward(request, response);
	}
}
