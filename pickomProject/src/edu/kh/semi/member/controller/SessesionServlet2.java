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

@WebServlet("/member/mypage/secession2")
public class SessesionServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		String icon = null;
		String title = null;
		String text = null;

		System.out.println("요청 왔니??");
		
		HttpSession session = request.getSession();
		MemberService service = new MemberService();
		int result = 0;
		
		int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
		String secessionAgree = request.getParameter("secessionAgree");
		String secessionReason = request.getParameter("secessionreason");
		String checkAgree = "\"회원 탈퇴 신청하겠습니다.\"";

		System.out.println(secessionAgree);
		
		

			try {
				if(checkAgree.equals(secessionAgree)) {
				result = service.secession(memberNo, secessionAgree, secessionReason);


				if (result > 0) { // 성공
					icon = "success";
					title = "회원 탈퇴 성공";
					text = "이용해 주셔서 감사합니다.";

					path = request.getContextPath(); // 메인 페이지 요청 주소
					session.invalidate(); // 세션 무효화 (로그아웃 시키기)

				} else { // 실패
					icon = "error";
					title = "회원 탈퇴 실패";
					text = "비밀번호가 일치하지 않습니다.";

					path = "secession"; // 회원 탈퇴 페이지 요청 주소
				}
			}else { // 실패
				icon = "error";
				title = "회원 탈퇴 실패";
				text = "비밀번호가 일치하지 않습니다.";

				path = "secession"; // 회원 탈퇴 페이지 요청 주소
			}
				// - 탈퇴 성공 시 이전에 얻어온 세션이 무효화 되기 때문에
				// 		새롭게 세션을 얻어옴
				// - 탈퇴 실패 시 이전 세션을 그대로 얻어옴
				session = request.getSession();

				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				session.setAttribute("text", text);
				
				System.out.println("처리 완료");
				response.sendRedirect(path);
				
			} catch (Exception e) {
				e.printStackTrace();

				request.setAttribute("errorMsg", "탈퇴 과정에서 오류 발생");

				RequestDispatcher view 
				= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
				view.forward(request, response);
			}
		
	}

}
