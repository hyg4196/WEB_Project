package exSample.servlet.guest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exSample.model.*;
import exSample.util.SHA256Util;

/**
 * Servlet implementation class GuestWriteServlet
 */
@WebServlet("/guest_write.do")
public class GuestWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		
		
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		GuestDAO dao = GuestDAO.getInstance();
		GuestVO dto = new GuestVO();
		dto.setName(request.getParameter("name"));
		
		if(cnt == 0) {
			dto.setPass(SHA256Util.getEncSHA256(request.getParameter("pass")));
		}else {
			dto.setPass(request.getParameter("pass"));
		}
		
		
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		
		int page = Integer.parseInt(request.getParameter("page"));
		int row = dao.guestWrite(dto);
		System.out.println(row);
		request.setAttribute("row", row);
		request.setAttribute("page", page);
		RequestDispatcher rd = request.getRequestDispatcher("Guest/guest_write_pro.jsp");
		rd.forward(request, response);
	}

}

