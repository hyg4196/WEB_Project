package com.jslhrd.service.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.service.Action;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_delete.jsp");
		dispatcher.forward(request, response);
	}

}
