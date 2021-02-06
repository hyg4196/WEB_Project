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



public class GuestModifyAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("subject")!=null) {
			request.setCharacterEncoding("utf-8");
			GuestVO guest = new GuestVO();
			guest.setIdx(Integer.parseInt(request.getParameter("idx")));
			guest.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
			guest.setSubject(request.getParameter("subject"));
			guest.setContents(request.getParameter("contents"));
			
			int page = Integer.parseInt(request.getParameter("page"));
			
			GuestDAO dao = GuestDAO.getInstance();
			
			int row = dao.guestModify(guest);
			
			request.setAttribute("page", page);
			request.setAttribute("row", row);
			request.setAttribute("guest", guest);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_modify_pro.jsp");
			dispatcher.forward(request, response);
		}else {
			GuestDAO dao = GuestDAO.getInstance();
			int idx = Integer.parseInt(request.getParameter("idx"));
			int page = Integer.parseInt(request.getParameter("page"));
			
			GuestVO guest = dao.guestSelect(idx);
			
			request.setAttribute("page", page);
			request.setAttribute("guest", guest);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_modify.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
