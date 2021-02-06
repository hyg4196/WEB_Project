package com.jslhrd.service.comments;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jslhrd.domain.comments.CommentsDAO;
import com.jslhrd.domain.comments.CommentsVO;
import com.jslhrd.service.Action;

public class CommentsWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int com_page = Integer.parseInt(request.getParameter("com_page"));
		CommentsDAO dao = CommentsDAO.getInstance();
		CommentsVO vo = new CommentsVO();
		
		vo.setUserid(request.getParameter("userid"));
		vo.setContents(request.getParameter("contents"));
		vo.setIdx(Integer.parseInt(request.getParameter("idx")));
		int page = Integer.parseInt(request.getParameter("page"));
		int row = dao.commentsWrite(vo);
		request.setAttribute("row", row);
		request.setAttribute("page", page);
		request.setAttribute("com_page", com_page);
		request.setAttribute("idx", vo.getIdx());
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_view_pro.jsp");
		rd.forward(request, response);
	}
}
