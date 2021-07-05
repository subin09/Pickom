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

@WebServlet("/member/mypage/profilefn")
public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberService service = new MemberService();

		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");

		try {
			int maxSize = 1024 * 1024 * 20;
			String root = session.getServletContext().getRealPath("/");
			String filePath = "resources/img/profileImg";		
			MultipartRequest mpRequest 
			= new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			List<Profile> atList = new ArrayList<Profile>();
			Enumeration<String> images = mpRequest.getFileNames();

			while(images.hasMoreElements()) {

				String name = images.nextElement();

				if(mpRequest.getFilesystemName(name) != null) {
					Profile at = new Profile();

					at.setFilePath(filePath);
					at.setFileName(mpRequest.getFilesystemName(name));
					atList.add(at);
				}
			}

			int memberNo = loginMember.getMemberNo();
			String memberNickNm = mpRequest.getParameter("memberNickNm");

			System.out.println(memberNo);
			System.out.println(atList);
			System.out.println(memberNickNm);


			int result = service.insertProfile(memberNo, atList, memberNickNm);
		} catch (Exception e) {
			e.printStackTrace();
		}


		String path = "/WEB-INF/views/member/mypage/mypageMain.jsp";
		RequestDispatcher view = request.getRequestDispatcher(path);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
