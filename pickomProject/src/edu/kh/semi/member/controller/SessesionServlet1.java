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

@WebServlet("/member/mypage/secession")
public class SessesionServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/mypage/secession1.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String secessionPw = (String)request.getParameter("secessionPw");
		int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
		
		
		MemberService service = new MemberService();
		String icon = null;
		String title = null;
		String path = null;
		
		int result = 0;
		try {
			
			result = service.checkPw(secessionPw, memberNo);
		
			if(result > 0) {
				path = "/WEB-INF/views/member/mypage/secession2.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} else {
				icon = "warning";
				title = "올바른 비밀번호를 입력해주세요";
				path = "/WEB-INF/views/member/mypage/secession1.jsp";
				RequestDispatcher view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
