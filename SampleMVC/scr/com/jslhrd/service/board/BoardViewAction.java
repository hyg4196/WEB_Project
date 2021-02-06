package com.jslhrd.service.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.board.BoardDAO;
import com.jslhrd.domain.board.BoardVO;
import com.jslhrd.domain.comments.CommentsDAO;
import com.jslhrd.domain.comments.CommentsVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.PageIndex;
import com.jslhrd.util.PageIndex2;


public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		int com_page = Integer.parseInt(request.getParameter("com_page"));
		BoardDAO dao = BoardDAO.getInstance();
		CommentsDAO cd = CommentsDAO.getInstance();
		
		int totcount = cd.commentsCount(idx);
	
		
		int nowpage = 1;	// 현재 페이지 초기화
		int maxlist = 5;	// 페이지당 글수 초기화
		int totpage = 1;	// 총 페이지 초기화
		
		if(request.getParameter("com_page") != null) nowpage = Integer.parseInt(request.getParameter("com_page"));
		
		if(totcount%maxlist == 0) totpage = totcount/maxlist;
		else totpage = (totcount/maxlist)+1; 	
		
		int startpage = (nowpage-1)*maxlist+1;//게시글 시작 번호
		int endpage = nowpage*maxlist;//게시글 끝 번호
		int listcount = totcount - (nowpage - 1)*maxlist;
		
		List<CommentsVO> list = cd.CommentsList(idx,startpage, endpage);	
		String pageSkip = PageIndex2.pageList(nowpage, totpage, "/Board?cmd=board_view&idx="+idx+"&page="+page, "");
		
		
		
		// 쿠키 검사 및 생성
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int x=0; x<cookies.length; x++) {
			info = cookies[x];
			if(info.getName().equals("Board"+idx)) {
				bool = true;
				break;
			}
		}
		
		String newValue=""+System.currentTimeMillis();
		if(!bool) { // 쿠키가 존재 하지 않으면 
			dao.boardHits(idx); // 조회수 증가
			info = new Cookie("Board"+idx, newValue);	// 쿠키 생성
			info.setMaxAge(60*60); // 1시간
			response.addCookie(info); // 쿠키 전송
		}
		
		BoardVO vo = dao.boardSelect(idx);
		vo.setContents(vo.getContents().replace("\n", "<br>"));
		request.setAttribute("totcount", totcount);
		request.setAttribute("vo", vo);
		request.setAttribute("com_page", com_page);
		request.setAttribute("list", list);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("page", page);

		RequestDispatcher rd = request.getRequestDispatcher("Board/board_view.jsp");
		rd.forward(request, response);
	}

}
