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

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("name") != null) {
			request.setCharacterEncoding("utf-8");
			int cnt = Integer.parseInt(request.getParameter("cnt"));

			BoardDAO dao = BoardDAO.getInstance();
			BoardVO vo = new BoardVO();
			vo.setName(request.getParameter("name"));

			if (cnt == 0) {
				vo.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
			} else {
				vo.setPass(request.getParameter("pass"));
			}
			vo.setSubject(request.getParameter("subject"));
			vo.setContents(request.getParameter("contents"));
			vo.setEmail(request.getParameter("email"));
			int page = Integer.parseInt(request.getParameter("page"));
			int row = dao.boardWrite(vo);
			request.setAttribute("row", row);
			request.setAttribute("page", page);
			RequestDispatcher rd = request.getRequestDispatcher("Board/board_write_pro.jsp");
			rd.forward(request, response);
		} else {
			int page = Integer.parseInt(request.getParameter("page"));

			request.setAttribute("page", page);
			RequestDispatcher rd = request.getRequestDispatcher("Board/board_write.jsp");
			rd.forward(request, response);
		}

	}

}
