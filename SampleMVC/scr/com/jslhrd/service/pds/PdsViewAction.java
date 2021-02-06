package com.jslhrd.service.pds;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.pds.pdsDAO;
import com.jslhrd.domain.pds.pdsVO;
import com.jslhrd.service.Action;


public class PdsViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idx = Integer.parseInt(request.getParameter("idx"));
		pdsDAO dao = pdsDAO.getInstance();
		pdsVO vo = dao.pdsSelect(idx);
		
		request.setAttribute("vo", vo);
		RequestDispatcher dis = request.getRequestDispatcher("Pds/pds_view.jsp");
		dis.forward(request, response);
	}

}
