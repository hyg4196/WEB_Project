package com.jslhrd.service.pds;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.pds.pdsDAO;
import com.jslhrd.domain.pds.pdsVO;
import com.jslhrd.service.Action;
import com.jslhrd.util.PageIndex;


public class PdsListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		pdsDAO dao = pdsDAO.getInstance();
		int totcount = 0;
		String s_query="", search ="", key="";
		if(request.getParameter("key") != null) {
			key = request.getParameter("key");
			search = request.getParameter("search");
			s_query =search +" like '%"+key+"%'";
			totcount = dao.pdsCount(s_query);
		}else {
			totcount = dao.pdsCount();
		}
		
		int nowpage = 1;
		int maxpage = 10;
		int totpage = 1;
		
		if(request.getParameter("page") != null) nowpage = Integer.parseInt(request.getParameter("page"));
		
		if(totcount%maxpage == 0) totpage = totcount/maxpage;
		else totpage = (totcount/maxpage)+1;
		
		int startpage = (nowpage-1)*maxpage+1;
		int endpage = nowpage*maxpage;
		int listcount = totcount - (nowpage-1)*maxpage;
		
		List<pdsVO> list = null;
		if(key.equals("")) list = dao.pdsList(startpage, endpage);
		else  list = dao.pdsList(startpage, endpage, s_query);
		
		
		String pageTag = "";
		if(key.equals("")) pageTag= PageIndex.pageList(nowpage, totpage, "Pds?cmd=pds_list","");
		else pageTag = PageIndex.pageListHan(nowpage, totpage, "Pds?cmd=pds_list", search, key);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("totpage", totpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("pageTag", pageTag);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		
		request.setAttribute("totcount", totcount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Pds/pds_list.jsp");
		dispatcher.forward(request, response);
	}
}
