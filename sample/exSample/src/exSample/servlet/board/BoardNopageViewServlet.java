package exSample.servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.BoardDAO;
import exSample.model.BoardVO;

/**
 * Servlet implementation class BoardNopageVuewServlet
 */
@WebServlet("/board_nopage.do")
public class BoardNopageViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardNopageViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		BoardDAO dao = BoardDAO.getInstance();
		
		// 쿠키 검사 및 생성
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int x=0; x<cookies.length; x++) {
			info = cookies[x];
			if(info.getName().equals("Guest"+idx)) {
				bool = true;
				break;
			}
		}
		
		String newValue=""+System.currentTimeMillis();
		if(!bool) { // 쿠키가 존재 하지 않으면 
			dao.boardHits(idx); // 조회수 증가
			info = new Cookie("Guest"+idx, newValue);	// 쿠키 생성
			info.setMaxAge(60*60); // 1시간
			response.addCookie(info); // 쿠키 전송
		}
		
		
		BoardVO vo = dao.boardSelect(idx);
		vo.setContents(vo.getContents().replace("\n", "<br>"));
		
		request.setAttribute("vo", vo);
	
		RequestDispatcher rd = request.getRequestDispatcher("Board/board_view.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}