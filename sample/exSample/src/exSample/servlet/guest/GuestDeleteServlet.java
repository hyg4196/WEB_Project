package exSample.servlet.guest;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.GuestDAO;
import exSample.util.SHA256Util;

/**
 * Servlet implementation class GuestDeleteServlet
 */
@WebServlet("/guest_delete.do")
public class GuestDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		request.setAttribute("idx", idx);
		request.setAttribute("page", page);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_delete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		String pass = SHA256Util.getEncSHA256(request.getParameter("pass"));
		
		GuestDAO dao = GuestDAO.getInstance();
		int row = dao.guestDelete(idx, pass);
		
		request.setAttribute("row", row);
		request.setAttribute("page", page);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_delete_pro.jsp");
		dispatcher.forward(request, response);
		
	}

}
