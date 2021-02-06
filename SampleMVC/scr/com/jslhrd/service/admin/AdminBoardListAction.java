package com.jslhrd.service.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.board.BoardDAO;
import com.jslhrd.domain.board.BoardVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.PageIndex;



public class AdminBoardListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = BoardDAO.getInstance();
		int totcount = dao.boardCount();
		
		String s_query="", search ="", key="";

		// 검색 판단
		if(request.getParameter("key") != null) {
			key = request.getParameter("key");
			search = request.getParameter("search");
			s_query = search + " like '%"+key+"%'";
			totcount = dao.boardCount(s_query);

		}else {
			totcount = dao.boardCount();
		}

		
		int nowpage = 1;	// 현재 페이지 초기화
		int maxlist = 10;	// 페이지당 글수 초기화
		int totpage = 1;	// 총 페이지 초기화
		
		if(request.getParameter("page") != null) nowpage = Integer.parseInt(request.getParameter("page"));
		
		if(totcount%maxlist == 0) totpage = totcount/maxlist;
		else totpage = (totcount/maxlist)+1; 	
		
		int startpage = (nowpage-1)*maxlist+1;//게시글 시작 번호
		int endpage = nowpage*maxlist;//게시글 끝 번호
		int listcount = totcount - (nowpage - 1)*maxlist;
		
		List<BoardVO> list = null;
		if(key.equals("")) {
			list = dao.boardList(startpage, endpage);
		}else {
			list = dao.boardList(startpage, endpage,s_query);
		}
			
		String pageSkip="";
		if(key.equals("")){
			pageSkip = PageIndex.pageList(nowpage, totpage, "/Admin?cmd=admin_boardList", "");
		}else {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, "/Admin?cmd=admin_boardList",search, key);
		}
		
		request.setAttribute("page", nowpage);
		request.setAttribute("totpage", totpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		
		request.setAttribute("totcount", totcount);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Admin/board_list.jsp");
		dispatcher.forward(request, response);
		
	}
}
