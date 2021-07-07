package edu.kh.semi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import edu.kh.semi.wrapper.EncryptWrapper;

@WebFilter(filterName = "encryptFilter", urlPatterns = {"/member/login", "/member/signUp", "/member/mypage/changePw",
														"/member/mypage/secession"})
public class EncryptFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest)request;

		if( req.getMethod().equals("POST")  ) {
			EncryptWrapper encWrapper = new EncryptWrapper(req);
			chain.doFilter(encWrapper, response);
			
		}else {
			chain.doFilter(request, response);
			
		}	
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}