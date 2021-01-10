package exSample.servlet.guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.*;
import exSample.util.SHA256Util;

/**
 * Servlet implementation class GuestModifyServlet
 */
@WebServlet("/guest_modify.do")
public class GuestModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestDAO dao = GuestDAO.getInstance();
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		GuestVO guest = dao.guestSelect(idx);
		
		request.setAttribute("page", page);
		request.setAttribute("guest", guest);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_modify.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		GuestVO guest = new GuestVO();
		guest.setIdx(Integer.parseInt(request.getParameter("idx")));
		guest.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
		guest.setSubject(request.getParameter("subject"));
		guest.setContents(request.getParameter("contents"));
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		GuestDAO dao = GuestDAO.getInstance();
		
		int row = dao.guestModify(guest);
		
//		if(row == 1) {
//			response.sendRedirect("guest_list.do?page="+page);
//		}else {
//			request.setAttribute("idx", request.getParameter("idx"));
//			request.setAttribute("page", page);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_modify_error.jsp");
//			dispatcher.forward(request, response);
//		}
		
		request.setAttribute("page", page);
		request.setAttribute("row", row);
		request.setAttribute("guest", guest);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_modify_pro.jsp");
		dispatcher.forward(request, response);
	}

}

