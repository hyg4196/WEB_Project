package com.jslhrd.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.user.UserDAO;
import com.jslhrd.domain.user.UserVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.SHA256Util;

public class UserInsertAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		if (request.getParameter("name") != null) {
			UserDAO dao = UserDAO.getInstance();
			UserVO vo = new UserVO();
			vo.setName(request.getParameter("name"));
			vo.setUserid(request.getParameter("userid"));
			vo.setPasswd(SHA256Util.getEncSHA256(request.getParameter("passwd")));
			String email = request.getParameter("email1") + "@" + request.getParameter("email2");

			vo.setEmail(email);
			vo.setTel(request.getParameter("tel"));
			int row = dao.userInsert(vo);
			request.setAttribute("row", row);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_insert_pro.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_insert.jsp");
			dispatcher.forward(request, response);

		}

	}
}
