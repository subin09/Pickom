package edu.kh.semi.mail.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/searchPw")
public class searchPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/member/searchPw.jsp");
	
		view.forward(request, response);
		
		
	}		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memberEmail = request.getParameter("memberEmail");
		String memberId = request.getParameter("memberId");
		
		
		
		try {
			
			Member m = new MemberService().searchPw(memberEmail,memberId);
			
			System.out.println(m);
			
			
			if(m==null|| !m.getMemberEmail().equals(memberEmail)) {
				
				request.setAttribute("errorMsg", "회원 정보 수정 과정에서 오류 발생");
				
				RequestDispatcher view 
					= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
				
				view.forward(request, response);
				return;
				
				}else {
					
					
					String host = "smtp.naver.com";
					String user = "happyb1234@naver.com";
					String password = "happyb12!";
					
					String to_email = m.getMemberEmail();
					
					Properties props = new Properties();
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", 465);
					props.put("mail.smtp.ssl.enable", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.debug", "true");
					props.put("mail.smtp.auth", "true");
					
					StringBuffer temp = new StringBuffer();
					Random rnd = new Random();
					for (int i = 0; i < 10; i++) {
						int rIndex = rnd.nextInt(3);
						switch (rIndex) {
						case 0:
							// a-z
							temp.append((char) ((int) (rnd.nextInt(26)) + 97));
							break;
						case 1:
							// A-Z
							temp.append((char) ((int) (rnd.nextInt(26)) + 65));
							break;
						case 2:
							// 0-9
							temp.append((rnd.nextInt(10)));
							break;
						}
					}
					String AuthenticationKey = temp.toString();
					System.out.println(AuthenticationKey);
					
					
					Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user,password);
						}
					});
					
					try {
						MimeMessage msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(user, "Pickom"));
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
						
						//메일 제목
						msg.setSubject("안녕하세요 Pickom 인증 메일입니다.");
						//메일 내용
						msg.setText("회원님의 인증번호는 :"+temp);
						
						Transport.send(msg);
						System.out.println("이메일 전송");
						
					}catch (Exception e) {
						e.printStackTrace();
					}
					HttpSession saveKey = request.getSession();
	                saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
					saveKey.setAttribute("memberId", memberId);
					
					
					RequestDispatcher view =request.getRequestDispatcher("/WEB-INF/views/member/newPw.jsp");
					view.forward(request, response);
					
				}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
				
				
			
		
	}

	
}
