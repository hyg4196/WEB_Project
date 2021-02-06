package com.jslhrd.service.pds;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.domain.pds.pdsDAO;
import com.jslhrd.domain.pds.pdsVO;
import com.jslhrd.service.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



public class PdsModifyAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("subject") != null) {
			request.setCharacterEncoding("utf-8");
			ServletContext context = request.getSession().getServletContext(); // 모델 2 방식은 이렇게 사용
			String path = context.getRealPath("Pds/upload/");
			String encType = "UTF-8";
			int sizeLimit = 2*1024*1024; // 파일 최대 용량 설정 (지금은 최대 2M로 설정함)
			
			MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
			// new DefalutFileRenamePolicy()  :  이 기능을 주면 파일 중복시 자동이름 변경  a.gif -> a1.gif
			
			pdsVO vo = new pdsVO();
			vo.setIdx(Integer.parseInt(multi.getParameter("idx")));
			vo.setName(multi.getParameter("name"));
			vo.setEmail(multi.getParameter("email"));
			vo.setSubject(multi.getParameter("subject"));
			vo.setContents(multi.getParameter("contents"));
			vo.setPass(multi.getParameter("pass"));
			vo.setFilename(multi.getFilesystemName("filename"));
			
			String oldfilename = multi.getParameter("oldfilename");
			int oldCheck = 0;
			System.out.println(oldfilename);
			// 첨부 파일 여부 확인
			if(vo.getFilename() == null) {
				vo.setFilename(oldfilename);
			}else {
				// 변경 파일 삭제
				File f = new File(path+oldfilename);
				if(f.exists())
					f.delete();
			}
			pdsDAO dao = pdsDAO.getInstance();
			int row = dao.pdsModify(vo);
			request.setAttribute("row", row);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Pds/pds_modify_pro.jsp");
			dispatcher.forward(request, response);
		}else {
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			pdsDAO dao = pdsDAO.getInstance();
			pdsVO vo  = dao.pdsSelect(idx);
			
			request.setAttribute("pds", vo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Pds/pds_modify.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
