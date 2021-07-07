package edu.kh.semi.mail.controller;

import java.io.IOException;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/newPw")
public class newPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		 	String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");
	        String AuthUser = request.getParameter("AuthUser");
	        if(!AuthenticationKey.equals(AuthUser)){
	            System.out.println("인증번호 일치하지 않음");
	            request.setAttribute("msg", "인증번호가 일치하지 않습니다");
	            request.setAttribute("loc", "/member/searchPw");
	            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	            return;
	        }else {
	        	String memberPw = request.getParameter("memberPw");
	        	String memberId = (String)request.getSession().getAttribute("memberId");
	        	
	        	HttpSession session = request.getSession();
	        	
	        	try {
	        		int result = new MemberService().newPw(memberPw, memberId);
	        		
	        		String icon = null;
	    			String title = null;
	    			String text = null;
	    			
	    			
		    			if(result > 0) {  
		    				icon = "success";
		    				title = "비밀번호 변경 성공";
		    				text = "비밀번호가 변경 되었습니다.";
		    				
		    			}else { 
		    				icon = "error";
		    				title = "비밀번호 변경 실패 실패";
		    				text = "비밀번호가 변경 중 문제가 발생했습니다.";
		    			}
		    			
	    			
	    			session.setAttribute("icon", icon);
	    			session.setAttribute("title", title);
	    			session.setAttribute("text", text);
	    			
	    			response.sendRedirect(request.getContextPath());
	    			
	        	}catch(Exception e) {
	        		e.printStackTrace();
	        	}
	        	
	        }
	        	
	        }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					doGet(request, response);
			
		        }


	
	
	}
	

