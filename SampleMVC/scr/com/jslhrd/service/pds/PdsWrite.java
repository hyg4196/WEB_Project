package com.jslhrd.service.pds;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jslhrd.service.Action;


public class PdsWrite implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int page = Integer.parseInt(request.getParameter("page"));
			request.setAttribute("page", page);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Pds/pds_write.jsp");
			dispatcher.forward(request, response);

	}

}
