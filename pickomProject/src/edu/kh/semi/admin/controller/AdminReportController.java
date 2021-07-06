package edu.kh.semi.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.admin.controller.model.service.AdminBoardService;


@WebServlet("/admin/report/*")
public class AdminReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/admin/report/").length());
		
		String path = null; // 응답 화면 주소 또는 경로 
		RequestDispatcher view = null; // 요청 위임 객체 저장 참조 변수 
		
		// sweetalert 용 변수 
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			

			if(command.equals("postList")) {
			view = request.getRequestDispatcher("/WEB-INF/views/admin/adminReportPost.jsp"); 
			view.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
