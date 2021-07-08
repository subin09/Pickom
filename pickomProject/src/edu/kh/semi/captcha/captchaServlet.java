package edu.kh.semi.captcha;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;

@WebServlet("/member/mypage/captchar")
public class captchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Captcha captcha = new Captcha.Builder(250, 50)
					.addText().addNoise().addBackground(new GradiatedBackgroundProducer())
					.addBorder().build();

			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");

			CaptchaServletUtil.writeImage(response, captcha.getImage());
			String captcha_str = captcha.getAnswer();
			
			System.out.println(captcha_str);
			
			request.getSession().setAttribute("captcha", captcha_str);
			String path = "/WEB-INF/views/member/mypage/changePw.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
