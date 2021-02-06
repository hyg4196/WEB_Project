package com.jslhrd.service.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.board.BoardDAO;
import com.jslhrd.domain.board.BoardVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.SHA256Util;


public class BoardModifyAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("email") != null) {
			request.setCharacterEncoding("utf-8");
			BoardVO vo = new BoardVO();
			vo.setIdx(Integer.parseInt(request.getParameter("idx")));
			vo.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
			vo.setSubject(request.getParameter("subject"));
			vo.setContents(request.getParameter("contents"));
			vo.setEmail(request.getParameter("email"));
			int page = Integer.parseInt(request.getParameter("page"));
			
			BoardDAO dao = BoardDAO.getInstance();
			
			int row = dao.boardModify(vo);
			
			request.setAttribute("row", row);
			request.setAttribute("page", page);
			request.setAttribute("vo", vo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_modify_pro.jsp");
			dispatcher.forward(request, response);
		}else {
			BoardDAO dao = BoardDAO.getInstance();
			int idx = Integer.parseInt(request.getParameter("idx"));
			int page = Integer.parseInt(request.getParameter("page"));
			
			BoardVO vo = dao.boardSelect(idx);
			
			request.setAttribute("page", page);
			request.setAttribute("idx", idx);
			request.setAttribute("vo", vo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_modify.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}
}
