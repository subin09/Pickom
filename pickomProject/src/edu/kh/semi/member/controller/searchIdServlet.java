package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/searchId")
public class searchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		RequestDispatcher view 
		= request.getRequestDispatcher("/WEB-INF/views/member/searchId.jsp");	
			
		view.forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
        String memberNm = request.getParameter("memberNm");
        String memberEmail = request.getParameter("memberEmail");
        
        try {
       	 MemberService service = new MemberService();
       	 Member loginId = service.searchId(memberNm, memberEmail);
       	 
       	 HttpSession session = request.getSession();

       	 
       	 System.out.println(loginId);
       	 if(loginId != null) {
       		 session.setAttribute("loginId", loginId);
       		 RequestDispatcher view 
       		 = request.getRequestDispatcher("/WEB-INF/views/member/searchId2.jsp");
       		 
       		 
       		 view.forward(request, response);
       		 
       		 
       	 }else {
       		 session.setAttribute("icon", "error"); 
				session.setAttribute("title", "로그인 실패");
				session.setAttribute("text", "아이디 또는 비밀번호가 일치하지 않습니다.");			
       		 
       		 
       	 }
       	 
       	
       	 
       	 
        }catch(Exception e) {
       	 e.printStackTrace();
       	 
       	 request.setAttribute("errorMsg", "아이디찾기 과정에서 오류 발생");
      
       	 
        }
        
}
	
		
	}
	



