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


@WebServlet("/member/mypage/infoUpdate")
public class InfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/mypage/memberUpdate.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();

		String memberEmail = request.getParameter("email");
		String[] phone = request.getParameterValues("phone");

		String memberPhone = String.join("-", phone);

		String[] address = request.getParameterValues("address");
		String memberAddress = null;
		if(address != null) {
			memberAddress = String.join(",", address);
		}


		Member member = new Member();
		member.setMemberNo(memberNo);
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAddress(memberAddress);


		try {

			int result = new MemberService().memberUpdate(member);

			String icon = null;
			String title = null;
			String text = null;

			if(result > 0) {
				icon = "success";
				title = "회원 정보 수정 성공";
				text = "회원 정보가 수정되었습니다.";


				loginMember.setMemberEmail(memberEmail);
				loginMember.setMemberPhone(memberPhone);
				loginMember.setMemberAddress(memberAddress);

			}else { // 실패
				icon = "error";
				title = "회원 정보 수정 실패";
				text = "회원 정보 수정 중 문제가 발생했습니다. \n문제가 지속될 경우 고객센터 문의 바랍니다.";
			}

			session.setAttribute("icon", icon);
			session.setAttribute("title", title);
			session.setAttribute("text", text);


			response.sendRedirect("myPage");

		}catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMsg", "회원 정보 수정 과정에서 오류 발생");

			RequestDispatcher errorview 
			= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");

			errorview.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
