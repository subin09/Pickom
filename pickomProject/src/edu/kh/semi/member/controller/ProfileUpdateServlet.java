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
			
			System.out.println(checkfilePath);
			System.out.println(checkfileName);
			session.setAttribute("filePath", checkfilePath);
			session.setAttribute("fileName", checkfileName);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String path = "/WEB-INF/views/member/mypage/profile.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberService service = new MemberService();
		String icon = null;
		String title = null;
		String path = null;
		String memberNickNm = null;
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		Profile at = new Profile();
		
		
		int result = 0;
		try {
			int memberNo = loginMember.getMemberNo();
			int maxSize = 1024 * 1024 * 20;
			String root = session.getServletContext().getRealPath("/");
			String filePath = "resources/img/profileImg/";
			Profile memberPreProfile = service.memberPreProfile(memberNo);
			
			MultipartRequest mpRequest 
			= new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			List<Profile> atList = new ArrayList<Profile>();
			Enumeration<String> images = mpRequest.getFileNames();
			
			if(images.hasMoreElements()) {
	
					String name = images.nextElement();
					
					
					if(mpRequest.getFilesystemName(name) != null) {
						at.setFilePath(filePath);
						at.setFileName(mpRequest.getFilesystemName(name));
						atList.add(at);
					}
				}
				
					memberNickNm = mpRequest.getParameter("memberNickNm");
					result = service.updateProfile(memberNo, atList, memberNickNm);
				
				
					// System.out.println("1 :" + memberNo);
					// System.out.println("1 :" + atList);
					// System.out.println("1 :" + memberNickNm);

				
					
			if(result > 0) {
				icon = "success";
				title = "프로필 수정 성공";
				path = "/WEB-INF/views/member/mypage/mypageMain.jsp";
				
			}else {
				icon = "error";
				title = "프로필 수정 실패";
				
				
				path = request.getHeader("referer");
			}
			
			session.setAttribute("icon", icon);
			session.setAttribute("title", title);
			
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("errorMsg", "프로필 수정 과정에서 오류 발생");
			
			RequestDispatcher view 
				= request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			
			view.forward(request, response);
		}
	}
}
