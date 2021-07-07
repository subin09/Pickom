package edu.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;

@WebServlet("/member/idDupCheck")
public class IdDupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	// 아이디 중복 검사 팝업창으로 요청 위임
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view 
			= request.getRequestDispatcher("/WEB-INF/views/member/idDupCheck.jsp");	
				
		view.forward(request, response);
		
	}
	
	// DB에 같은 아이디가 있는지 중복 검사 후 결과 반환
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String id = request.getParameter("id");
		
	
		try {
			
			int result = new MemberService().idDupCheck(id);
			
			PrintWriter out = response.getWriter();
			out.print(result);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		
		
		
		
	}

	
	
	
	
}