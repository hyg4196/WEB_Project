package exSample.servlet.guest;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.*;
import exSample.util.PageIndex;

/**
 * Servlet implementation class GuestListServlet
 */
@WebServlet("/guest_list.do")
public class GuestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 + 페이지 처리 추가
		
		GuestDAO dao = GuestDAO.getInstance();
		String s_query="", search ="", key="";
		int totcount = 0; // 게시글 총수 계산용
		
		// 검색 판단
		if(request.getParameter("key") != null) {
			key = request.getParameter("key");
			search = request.getParameter("search");
			s_query = search + " like '%"+key+"%'";
			totcount = dao.guestCount(s_query);

		}else {
			totcount = dao.guestCount();
		}
		
		int nowpage = 1;	// 현재 페이지 초기화
		int maxlist = 10;	// 페이지당 글수 초기화
		int totpage = 1;	// 총 페이지 초기화
		
		// 총 페이지 수 계산
		
		if(totcount % maxlist == 0) {
			totpage = totcount / maxlist;
		}else {
			totpage = totcount/maxlist + 1;
		}

		// 페이지 번호가 입력될 경우
		if(request.getParameter("page") != null) {
			nowpage = Integer.parseInt(request.getParameter("page"));
		}
		
		// 페이지 별 출력 될 시작, 끝번호 찾기
		int startpage = (nowpage - 1)*maxlist +1;//게시글 시작 번호
		int endpage = nowpage * maxlist;		 //게시글 끝 번호
		int listcount = totcount - (nowpage-1)*maxlist;
		
		// !!!!!!!! 요기까지 분석 완료 !!!!!!!!!!!!
		
		List<GuestVO> list = null;
		if(key.equals("")) {
			list = dao.guestList(startpage, endpage);
		}else {
			list = dao.guestList(startpage, endpage,s_query);
		}
		
		String pageSkip="";
		if(key.equals("")){
			pageSkip = PageIndex.pageList(nowpage, totpage, "/guest_list.do", "");
		}else {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, "/guest_list.do",search, key);
		}
		
		request.setAttribute("page", nowpage);
		request.setAttribute("totpage", totpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Guest/guest_list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
