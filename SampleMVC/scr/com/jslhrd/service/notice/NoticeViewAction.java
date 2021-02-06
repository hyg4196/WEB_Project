package com.jslhrd.service.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.notice.NoticeDAO;
import com.jslhrd.domain.notice.NoticeVO;
import com.jslhrd.service.Action;

public class NoticeViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		NoticeDAO dao = NoticeDAO.getInstance();
		
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
			dao.noticeHits(idx); // 조회수 증가
			info = new Cookie("Board"+idx, newValue);	// 쿠키 생성
			info.setMaxAge(60*60); // 1시간
			response.addCookie(info); // 쿠키 전송
		}
		
		
		NoticeVO vo = dao.noticeSelect(idx);
		vo.setContents(vo.getContents().replace("\n", "<br>"));
		
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
	
		RequestDispatcher rd = request.getRequestDispatcher("Notice/notice_view.jsp");
		rd.forward(request, response);
	
	}

}
