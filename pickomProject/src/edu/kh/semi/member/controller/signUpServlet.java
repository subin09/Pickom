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

// 회원가입 요청 주소 : /semi/member/signUp
@WebServlet("/member/signUp")
public class signUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// get 방식 요청을 받아서 처리하는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 화면을 응답해주는 메소드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/signUp.jsp"); 
		view.forward(request, response);
	}
	
	// post 방식 요청을 처리하는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입페이지에서 가입하기 버튼을 눌렀을 때 요청을 받고 응답하는 메소드
		
		// 1. POST 방식으로 전달된 요청의 문자 인코딩 변경 
		request.setCharacterEncoding("UTF-8");
		
		// 2. 전달 받은 파라미터를 모두 변수에 저장
		String memberId = request.getParameter("id");
		String memberPw = request.getParameter("pw1");
		String memberNm = request.getParameter("name");
		String memberEmail = request.getParameter("email");
		String memberNickNm = request.getParameter("nickname");
		
		// * 같은 name 속성으로 전달된 파라미터를 얻어오는 방법
		// String[] 배열명 = request.getParameterValues("name속성값");
		
		String[] phone = request.getParameterValues("phone");
		
		// DB 저장을 위해 구분자 '-'를 이용하여 하나의 문자열로 합침
		String memberPhone = String.join("-", phone);
		
		String[] address = request.getParameterValues("address");
		// 주소는 필수 입력 사항이 아니기 때문에 파라미터가 없을 수 있다 == null
		// -> String.join() 수행 시 NullPointerException가 발생할 수 있다. 
		
		String memberAddress = null;
		if(address != null) {
			 memberAddress = String.join(",", address);			
		}
		
		Member member = new Member(memberId, memberPw, memberNm, memberPhone, memberEmail, memberAddress, memberNickNm);
		
		// 입력받은 회원 정보를 DB의 MEMBER 테이블에 INSERT하고 결과를 Servlet까지 반환하기
		
		// 로그인 요청을 처리할 수 있는 서비스 메소드를 호출하고 로그인 결과를 반환 받음.

		try {
			int result = new MemberService().signUp(member);
			if(result > 0) {
				System.out.println("회원가입 성공!");

			} else {
				System.out.println("회원가입 실패");
			}
			
			// session에 값 세팅 


			
			// 메인페이지로 돌아간다 -> redirect 
			// 메인페이지를 응답하는 요청 주소로 재요청하기
			// 기존에 있던 request가 없어지기 때문에 session에 새로 담아서 보낸당
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// 자바 또는 DB에서 오류 발생 시 500에러가 발생함
			// -> 응답화면에 어떤 서비스 이용 과정에 오류 발생했다는 메세지 출력
			
			request.setAttribute("errorMsg", "회원 가입 과정에서 문제가 발생");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			
			view.forward(request, response);
		}
		
		
		
	}

}