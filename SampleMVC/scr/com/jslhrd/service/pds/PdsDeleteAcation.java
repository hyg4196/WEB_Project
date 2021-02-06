package com.jslhrd.service.pds;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.pds.pdsDAO;
import com.jslhrd.service.Action;


public class PdsDeleteAcation implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("pass") != null) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String pass = request.getParameter("pass");
			System.out.println("pass = "+pass +" row = "+idx);

			pdsDAO dao = pdsDAO.getInstance();
			String filename = dao.getFile(idx);
			int row = dao.pdsDelete(idx, pass);
			if(row == 1) {
				ServletContext context = request.getSession().getServletContext(); // 모델 2 방식은 이렇게 사용
				String path = context.getRealPath("Pds/upload/");
				File file = new File(path+filename);
				if(file.exists()) {
					file.delete();
				}
			}
			request.setAttribute("row", row);
			
			RequestDispatcher disp = request.getRequestDispatcher("Pds/pds_delete_pro.jsp");
			disp.forward(request, response);
		}
		else {
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			request.setAttribute("idx", idx);
			RequestDispatcher disp = request.getRequestDispatcher("Pds/pds_delete.jsp");
			disp.forward(request, response);
		}
	}
}
