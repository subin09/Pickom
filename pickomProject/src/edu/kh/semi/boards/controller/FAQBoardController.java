package edu.kh.semi.boards.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.FAQBoard;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.service.ComplainBoardService;
import edu.kh.semi.boards.model.service.FAQBoardService;

/**
 * Servlet implementation class ComplainBoardController
 */
@WebServlet("/FAQBoard/*")
public class FAQBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Front Controller 패턴(디자인 패턴)
	// - 클라이언트의 요청을 한 곳으로 집중시켜 
	// 	  개발 및 유지보수의 효율성을 증가시킨 패턴
	
	//   요청에 따른 Servlet을 각각 생성하는 것이 아닌, 하나의 Servlet으로 여러 요청을 받음. 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/FAQBoard/").length());
	
		
		String path = null; // 응답 화면 주소 또는 경로 
		RequestDispatcher view = null; // 요청 위임 객체 저장 참조 변수 
		
		// sweetalert 용 변수 
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			
			FAQBoardService service = new FAQBoardService();
			// 현재 페이지(우리가 항상 지속적으로 가지고 있는 값)
			// 삼항 연산자를 이용해서 cp가 없으면 1, 있으면 int형태로 파싱한 cp값을 저장
			int cp = request.getParameter("cp") == null ? 1 : // current page에 아무것도 안 써져 있으면 무조건 첫 페이지 
					Integer.parseInt(request.getParameter("cp")); // current page에 뭐가 있다! 라고 하면 cp에 현재 cp 값을 지정 
			
			// 게시글 목록 조회 Controller
			if(command.equals("list")) {
				
				// 페이징 처리를 위한 여러 정보를 담고 있는 객체 Pagination 생성
				Pagination pagination = service.getPagination(cp);
				
				// pagination을 이용하여 게시글 목록에 보여져야할 내용을 DB에서 조회
				List<FAQBoard> boardList = service.selectBoardList(pagination);
				
				// pagination, boardList를 request에 속성으로 추가한 후 boardList.jsp로 forward
				request.setAttribute("pagination", pagination);
				request.setAttribute("boardList", boardList);
				System.out.println(boardList);
				path="/WEB-INF/views/boards/FAQBoardList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
		} else if(command.equals("view")) {
			int boardNo = Integer.parseInt(request.getParameter("no"));
			FAQBoard board = service.selectBoard(boardNo);
			
//			System.out.println(board);
			request.setAttribute("board", board);
			
			path ="/WEB-INF/views/boards/FAQBoardDetail.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
					
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
