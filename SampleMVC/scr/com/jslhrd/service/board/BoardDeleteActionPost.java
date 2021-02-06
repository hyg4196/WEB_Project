package com.jslhrd.service.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.board.BoardDAO;
import com.jslhrd.service.Action;
import com.jslhrd.util.SHA256Util;


public class BoardDeleteActionPost implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pass = SHA256Util.getEncSHA256(request.getParameter("pass"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int row = dao.boardDelete(idx, pass);
		
		request.setAttribute("page", page);
		request.setAttribute("row", row);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_delete_pro.jsp");
		dispatcher.forward(request, response);
	}

}
