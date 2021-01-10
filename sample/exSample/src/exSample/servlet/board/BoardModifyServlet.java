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
 * Servlet implementation class BoardModifyServlet
 */
@WebServlet("/board_modify.do")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getInstance();
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		BoardVO vo = dao.boardSelect(idx);
		
		request.setAttribute("page", page);
		request.setAttribute("idx", idx);
		request.setAttribute("vo", vo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_modify.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardVO vo = new BoardVO();
		vo.setIdx(Integer.parseInt(request.getParameter("idx")));
		vo.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
		vo.setSubject(request.getParameter("subject"));
		vo.setContents(request.getParameter("contents"));
		vo.setEmail(request.getParameter("email"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int row = dao.boardModify(vo);
		
//		if(row == 1) {
//			response.sendRedirect("guest_list.do?page="+page);
//		}else {
//			request.setAttribute("idx", request.getParameter("idx"));
//			request.setAttribute("page", page);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/Guest/guest_modify_error.jsp");
//			dispatcher.forward(request, response);
//		}
		
		request.setAttribute("row", row);
		request.setAttribute("page", page);
		request.setAttribute("vo", vo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Board/board_modify_pro.jsp");
		dispatcher.forward(request, response);
	}

}
