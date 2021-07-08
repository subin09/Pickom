package edu.kh.semi.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.semi.common.MyFileRenamePolicy;
import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;
import edu.kh.semi.member.model.vo.Profile;

@WebServlet("/member/mypage/profile")
public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberService service = new MemberService();
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		try {
			int memberNo = loginMember.getMemberNo();
			Profile memberPreProfile = service.memberPreProfile(memberNo);
			
			String checkfilePath = memberPreProfile.getFilePath();
			String checkfileName = memberPreProfile.getFileName();
			String checkMemberNickNm = memberPreProfile.getMemberNickNm();
					
			System.out.println(checkfilePath);
			System.out.println(checkfileName);
			
			session.setAttribute("filePath", checkfilePath);
			session.setAttribute("fileName", checkfileName);
			session.setAttribute("memberNickNm", checkMemberNickNm);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String path = "/WEB-INF/views/member/mypage/profile.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberService service = new MemberService();
		Profile at = new Profile();	
		
		String icon = null;
		String title = null;
		String path = null;
		String memberNickNm = null;
		String memberNickNm2 = null;
				
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();
		
		String root = session.getServletContext().getRealPath("/");
		String filePath = "resources/img/profileImg/";
		
		
		int result = 0;
		int maxSize = 1024 * 1024 * 20;
		int sel = 1;
		try {
			Profile memberPreProfile = service.memberPreProfile(memberNo);
			String checkfilePath = memberPreProfile.getFilePath();
			String checkfileName = memberPreProfile.getFileName();
						
			MultipartRequest mpRequest 
			= new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			List<Profile> atList = new ArrayList<Profile>();
			Enumeration<String> images = mpRequest.getFileNames();

			memberNickNm = mpRequest.getParameter("memberNickNm");
			memberNickNm2 = mpRequest.getParameter("memberNickNm2");

			// System.out.println(memberNickNm);
			// System.out.println("1 :" + memberNickNm2);
			System.out.println(images);
			if(!memberNickNm.isEmpty()) {
				if(images.hasMoreElements()) {

					String name = images.nextElement();
					if(mpRequest.getFilesystemName(name) != null) {
						System.out.println("1??");
						at.setFilePath(filePath);
						at.setFileName(mpRequest.getFilesystemName(name));
						atList.add(at);
					}else {
						System.out.println("2??");
						at.setFilePath(checkfilePath);
						at.setFileName(checkfileName);
						atList.add(at);
					}
				}
				result = service.updateProfile(memberNo, atList, memberNickNm);	
			}else {
				if(images.hasMoreElements()) {
					System.out.println("3??");
					String name = images.nextElement();
					if(mpRequest.getFilesystemName(name) != null) {
						at.setFilePath(filePath);
						at.setFileName(mpRequest.getFilesystemName(name));
						atList.add(at);
					}
				}
				result = service.updateProfile(memberNo, atList, memberNickNm2);	
			}

			if(result > 0){
				icon = "success";
				title = "프로필 수정 성공";
			}else{
				icon = "error";
				title = "프로필 수정 실패";
			}
			
			session.setAttribute("icon", icon);
			session.setAttribute("title", title);
			response.sendRedirect("profile");	
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "프로필 수정 과정에서 오류 발생");
			RequestDispatcher view 
				= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			view.forward(request, response);
		}
	}
}
