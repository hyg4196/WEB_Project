package com.jslhrd.service.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.notice.NoticeDAO;
import com.jslhrd.domain.notice.NoticeVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.PageIndex;

public class NoticeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NoticeDAO dao = NoticeDAO.getInstance();
		int totcount = dao.noticeCount();
		
		String s_query="", search ="", key="";

		// 검색 판단
		if(request.getParameter("key") != null) {
			key = request.getParameter("key");
			search = request.getParameter("search");
			s_query = search + " like '%"+key+"%'";
			totcount = dao.noticeCount(s_query);

		}else {
			totcount = dao.noticeCount();
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
		
		List<NoticeVO> list = null;
		if(key.equals("")) {
			list = dao.noticeList(startpage, endpage);
		}else {
			list = dao.noticeList(startpage, endpage,s_query);
		}
			
		String pageSkip="";
		if(key.equals("")){
			pageSkip = PageIndex.pageList(nowpage, totpage, "/Notice?cmd=notice_list", "");
		}else {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, "/Notice?cmd=notice_list",search, key);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("Notice/notice_list.jsp");
		dispatcher.forward(request, response);
	}

}
