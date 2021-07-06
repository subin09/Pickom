package edu.kh.semi.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.admin.controller.model.vo.Pagination;
import edu.kh.semi.admin.controller.model.service.AdminBoardService;
import edu.kh.semi.member.model.vo.Member;


@WebServlet("/admin/adminMember/*")
public class AdminMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/admin/adminMember/").length());
		
		String path = null; // 응답 화면 주소 또는 경로 
		RequestDispatcher view = null; // 요청 위임 객체 저장 참조 변수 
		
		// sweetalert 용 변수 
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			
			AdminBoardService service = new AdminBoardService();
			if(command.equals("list")) {
				// 현재 페이지(우리가 항상 지속적으로 가지고 있는 값)
				// 삼항 연산자를 이용해서 cp가 없으면 1, 있으면 int형태로 파싱한 cp값을 저장
				int cp = request.getParameter("cp") == null ? 1 : // current page에 아무것도 안 써져 있으면 무조건 첫 페이지 
						Integer.parseInt(request.getParameter("cp")); // current page에 뭐가 있다! 라고 하면 cp에 현재 cp 값을 지정 
			
				int boardType = Integer.parseInt(request.getParameter("type"));
				
				if(boardType == 1) {
					// 아이디 = 1 / 닉네임 = 2

					int searchType = Integer.parseInt(request.getParameter("search"));
					// 활동중 = Y / 탈퇴 = N
					String condition = request.getParameter("condition");
					String searchInput = request.getParameter("searchInput");
		
					Pagination pagination = service.getSearchPagination(cp, boardType, searchType, searchInput, condition);
					request.setAttribute("pagination", pagination);

					List<Member> memberList = service.selectMemberList(pagination, searchType, searchInput, condition);
					System.out.println(memberList);
					request.setAttribute("memberList", memberList);
					request.setAttribute("searchType", searchType);
					request.setAttribute("condition", condition);
				} else {

					// 페이징 처리를 위한 여러 정보를 담고 있는 객체 Pagination 생성
					Pagination pagination = service.getPagination(cp, boardType);
					
					// pagination을 이용하여 게시글 목록에 보여져야할 내용을 DB에서 조회
					List<Member> memberList = service.selectMemberList(pagination);
					
					// pagination, boardList를 request에 속성으로 추가한 후 boardList.jsp로 forward
					request.setAttribute("pagination", pagination);
					request.setAttribute("memberList", memberList);
					
				}
				
					
				path="/WEB-INF/views/admin/adminMember.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
		
			} else if(command.equals("search")) {
				
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
