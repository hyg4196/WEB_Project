package com.jslhrd.service.guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.guest.GuestDAO;
import com.jslhrd.domain.guest.GuestVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.SHA256Util;



public class GuestWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("name") != null) {
			request.setCharacterEncoding("utf-8");
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			GuestDAO dao = GuestDAO.getInstance();
			GuestVO dto = new GuestVO();
			dto.setName(request.getParameter("name"));
			
			if(cnt == 0) {
				dto.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
			}else {
				dto.setPass(request.getParameter("pass"));
			}
			
			
			dto.setSubject(request.getParameter("subject"));
			dto.setContents(request.getParameter("contents"));
			
			int page = Integer.parseInt(request.getParameter("page"));
			int row = dao.guestWrite(dto);
			System.out.println(row);
			request.setAttribute("row", row);
			request.setAttribute("page", page);
			RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_write_pro.jsp");
			rd.forward(request, response);
		}else {
			int page = Integer.parseInt(request.getParameter("page"));
			
			request.setAttribute("page", page);
			RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_write.jsp");
			rd.forward(request, response);
		}

	}

}
