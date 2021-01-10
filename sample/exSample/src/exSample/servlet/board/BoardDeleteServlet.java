package exSample.servlet.board;


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
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board_delete.do")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_delete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pass = SHA256Util.getEncSHA256(request.getParameter("pass"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int row = dao.boardDelete(idx, pass);
		
		request.setAttribute("page", page);
		request.setAttribute("row", row);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_delete_pro.jsp");
		dispatcher.forward(request, response);
	}

}
