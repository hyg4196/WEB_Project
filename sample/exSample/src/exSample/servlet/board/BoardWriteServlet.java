package exSample.servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.*;
import exSample.util.SHA256Util;;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/board_write.do")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = new BoardVO();
		vo.setName(request.getParameter("name"));
		
		if(cnt == 0) {
			vo.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
		}else {
			vo.setPass(request.getParameter("pass"));
		}		
		vo.setSubject(request.getParameter("subject"));
		vo.setContents(request.getParameter("contents"));
		vo.setEmail(request.getParameter("email"));
		int page = Integer.parseInt(request.getParameter("page"));
		int row = dao.boardWrite(vo);
		request.setAttribute("row", row);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_write_pro.jsp");
		rd.forward(request, response);
	}

}