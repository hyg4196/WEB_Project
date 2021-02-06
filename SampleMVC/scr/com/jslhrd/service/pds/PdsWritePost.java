package com.jslhrd.service.pds;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.pds.pdsDAO;
import com.jslhrd.domain.pds.pdsVO;
import com.jslhrd.service.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PdsWritePost implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(request.getParameter("page"));

		ServletContext context = request.getSession().getServletContext(); // 모델 2 방식은 이렇게 사용

		String path = context.getRealPath("Pds/upload");
		String encType = "UTF-8";
		int sizeLimit = 2 * 1024 * 1024; // 파일 최대 용량 설정 (지금은 최대 2M로 설정함)

		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType,
				new DefaultFileRenamePolicy());
		// new DefalutFileRenamePolicy() : 이 기능을 주면 파일 중복시 자동이름 변경 a.gif -> a1.gif

		pdsVO vo = new pdsVO();
		vo.setName(multi.getParameter("name"));
		vo.setEmail(multi.getParameter("email"));
		vo.setSubject(multi.getParameter("subject"));
		vo.setContents(multi.getParameter("contents"));
		vo.setPass(multi.getParameter("pass"));
		vo.setFilename(multi.getFilesystemName("filename"));

		System.out.println("파일" + vo.getFilename());
		pdsDAO dao = pdsDAO.getInstance();
		int row = dao.pdsWrite(vo);
		request.setAttribute("row", row);
		request.setAttribute("page", page);
		response.sendRedirect("Pds/pds_write_pro.jsp");
		
	}
}
