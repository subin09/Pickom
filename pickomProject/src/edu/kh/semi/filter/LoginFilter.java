package edu.kh.semi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 회원 전용 서비스에 비로그인 상태로 요청이 올 경우 메인페이지로 돌려보내는 필터
@WebFilter(filterName = "loginFilter", urlPatterns = {"/member/mypage", "/member/mypage/*", "/complainBoardDML/*", "/FAQBoardDML/*","/reviewBoardDML/*","/reviewComment/*","/report/*","/admin/adminMain","/admin/adminMember/*","/admin/report/*"} )
public class LoginFilter implements Filter {

	
	
	public void destroy() {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		if( session.getAttribute("loginMember") == null  ) {
			
			session.setAttribute("icon", "warning");
			session.setAttribute("title", "로그인 후 이용해주세요.");
			
			( (HttpServletResponse)response ).sendRedirect(req.getContextPath());
			
		}else {
			
			chain.doFilter(request, response);
		}
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {

	}

}