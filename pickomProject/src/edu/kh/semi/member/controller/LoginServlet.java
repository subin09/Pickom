package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

// 로그인 요청 주소 : /semi/member/login
@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		String save = request.getParameter("save");
		
	try {
			
			MemberService service = new MemberService();
			
			Member loginMember = service.login(memberId, memberPw);
			
			HttpSession session = request.getSession();
			
			System.out.println(memberId);
			System.out.println(memberPw);
	
			 if(loginMember != null) { 
							
				session.setAttribute("loginMember", loginMember);
				
				
				// 일정 시간 후 세션 만료
				session.setMaxInactiveInterval(1800);
				
				
				// 아이디체크 저장(쿠키) 기능
				Cookie cookie = new Cookie("saveId", memberId);
				
				if(save != null) {
					cookie.setMaxAge(60 * 60 * 24 * 7); // 초 단위
				} else {
					cookie.setMaxAge(0); 
				}
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
								 
			 }else { 
				session.setAttribute("icon", "error"); 
				session.setAttribute("title", "로그인 실패");
				session.setAttribute("text", "아이디 또는 비밀번호가 일치하지 않습니다.");					
			 }

			 response.sendRedirect(request.getContextPath());
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}