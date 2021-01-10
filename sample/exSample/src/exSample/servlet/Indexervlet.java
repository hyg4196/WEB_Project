package exSample.servlet;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.BoardDAO;
import exSample.model.BoardVO;
import exSample.model.GuestDAO;
import exSample.model.GuestVO;
import exSample.servlet.guest.GuestViewServlet;

/**
 * Servlet implementation class IndexLoginServlet
 */
@WebServlet("/index.do")
public class Indexervlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Indexervlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		BoardDAO bdao = BoardDAO.getInstance();
		 
		GuestDAO gdao = GuestDAO.getInstance();
		
		List<BoardVO> blist = bdao.boardListTop();
		List<GuestVO> glist = gdao.guestListTop();
		
		request.setAttribute("blist", blist);
		request.setAttribute("glist", glist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String ser = request.getParameter("ser");
		String key = request.getParameter("key");
		String search = request.getParameter("search");
		int row = 0;
		if(ser.equals("gong")) {
			request.setAttribute("row", row);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index_pro.jsp");
			dispatcher.forward(request, response);
		}else if(ser.equals("ja")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index_pro.jsp");
			dispatcher.forward(request, response);
		}else if(ser.equals("board")) {
			row = 1;
			request.setAttribute("key", key);
			request.setAttribute("search", search);
			request.setAttribute("row", row);

			RequestDispatcher dispatcher = request.getRequestDispatcher("index_pro.jsp");
			dispatcher.forward(request, response);
		}else if(ser.equals("guest")) {
			row = 2;
			request.setAttribute("key", key);
			request.setAttribute("search", search);
			request.setAttribute("row", row);

			RequestDispatcher dispatcher = request.getRequestDispatcher("index_pro.jsp");
			dispatcher.forward(request, response);
		}
	}

}
