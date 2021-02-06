package com.jslhrd.service.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.notice.NoticeDAO;
import com.jslhrd.domain.notice.NoticeVO;
import com.jslhrd.service.Action;

public class NoticeWriteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("subject") != null) {
			request.setCharacterEncoding("utf-8");

			NoticeDAO dao = NoticeDAO.getInstance();
			NoticeVO vo = new NoticeVO();
			
			vo.setSubject(request.getParameter("subject"));
			vo.setContents(request.getParameter("contents"));
			
			int page = Integer.parseInt(request.getParameter("page"));
			int row = dao.noticeWrite(vo);
			request.setAttribute("row", row);
			request.setAttribute("page", page);
			RequestDispatcher rd = request.getRequestDispatcher("Admin/notice_write_pro.jsp");
			rd.forward(request, response);
		}else {
			String check = request.getParameter("check");
			int page = Integer.parseInt(request.getParameter("page"));

			request.setAttribute("check", check);
			request.setAttribute("page", page);

			RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/notice_write.jsp");
			dispatcher.forward(request, response);
		}
	}
}
