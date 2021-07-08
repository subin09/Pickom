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


@WebServlet("/member/mypage/changePw")
public class ChangePwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/mypage/changePw.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String currentPw = request.getParameter("currentPw");
		String newPw1 = request.getParameter("newPw1");
		
		// session에서 회원 번호 얻어오기
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
		
		System.out.println(newPw1);
		System.out.println(currentPw);
		
		try {
			int result = new MemberService().changePw(currentPw, newPw1, memberNo);
			
			String icon = null;
			String title = null;
			String text = null;
			
			
			if(result > 0) {  
				// icon = "success";
				// title = "비밀번호 변경 성공";
				// text = "비밀번호가 변경 되었습니다.";
				
				// session.setAttribute("title", title);
				// session.setAttribute("text", text);
				
				request.getSession().invalidate(); 
			}else { 
				icon = "error";
				title = "비밀번호 변경 실패 실패";
				text = "비밀번호가 변경 중 문제가 발생했습니다.";
				
				session.setAttribute("title", title);
				session.setAttribute("text", text);
			}
		
			response.sendRedirect("changePw");
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "비밀 번호 변경 과정에서 오류 발생");
			
			RequestDispatcher view 
				= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			
			view.forward(request, response);
		}
	}

}
