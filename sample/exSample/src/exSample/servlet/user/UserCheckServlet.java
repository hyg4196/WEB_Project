package exSample.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.UserDAO;



/**
 * Servlet implementation class UserCheckServlet
 */
@WebServlet("/user_check")
public class UserCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		
		String userid = request.getParameter("userid");
		UserDAO dao = UserDAO.getInstance();
		int row = dao.userCheck(userid);
		
		request.setAttribute("row", row);
		request.setAttribute("userid", userid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_idcheck.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("user_id");
		UserDAO dao = UserDAO.getInstance();
		int row = dao.userCheck(userid);
		request.setAttribute("row", row);
		request.setAttribute("userid", userid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Users/user_idcheck.jsp");
		dispatcher.forward(request, response);
	}

}
