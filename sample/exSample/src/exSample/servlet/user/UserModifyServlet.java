package exSample.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exSample.model.*;
import exSample.util.SHA256Util;

/**
 * Servlet implementation class UserModifyServlet
 */
@WebServlet("/user_modify")
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_modify.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		String newpasswd = SHA256Util.getEncSHA256(request.getParameter("newpasswd"));
		String passwd = SHA256Util.getEncSHA256(request.getParameter("passwd"));
		String tel = request.getParameter("tel");
		String userid = request.getParameter("userid");
		System.out.println("값: "+vo.getPasswd());
		System.out.println(passwd);
		int row =0;
		if(vo.getPasswd().equals(passwd)) {
			UserDAO dao = UserDAO.getInstance();
			row = dao.userModify(newpasswd, tel,userid);
			session.invalidate();
		}
		
		request.setAttribute("row", row);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_modify_pro.jsp");
		dispatcher.forward(request, response);
		
	}

}
