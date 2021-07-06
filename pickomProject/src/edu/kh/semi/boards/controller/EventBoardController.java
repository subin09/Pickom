package edu.kh.semi.boards.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.boards.model.service.EventBoardService;
import edu.kh.semi.boards.model.service.EventReplyService;
import edu.kh.semi.boards.model.vo.EventBoard;
import edu.kh.semi.boards.model.vo.EventReply;
import edu.kh.semi.boards.model.vo.Pagination;

@WebServlet("/eventBoard/*")
public class EventBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/eventBoard/").length());
		
		String path =null; // 응답 화면 주소 또는 경로
		RequestDispatcher view = null; // 요청 위임 객체 저장 참조 변수
		
		// sweetalert
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			
			EventBoardService service = new EventBoardService();
			
			int cp = request.getParameter("cp") == null ? 1 : Integer.parseInt(request.getParameter("cp"));
			
			if(command.equals("list")) {
				
				
				
				// -------------검색 추가 시작-----------------
				Pagination pagination = null;
				List<EventBoard> boardList = null;
				
				if(request.getParameter("sv") == null) {
					
					pagination = service.getPagination(cp);
					
					
					//System.out.println(pagination);
					
					// pagination을 이용해 게시글 목록에 보여져야 할 내용을 DB에서 조회
					boardList = service.selectEventBoardList(pagination);
					
				}else {
					String searchKey = request.getParameter("sk");
					String searchValue = request.getParameter("sv");
					
					pagination = service.getPagination(cp, searchKey, searchValue);
					boardList = service.selectEventBoardList(pagination, searchKey, searchValue);
				}
				
				
				
				
				
				// -------------검색 추가 끝-------------------
				
				
				
				
				// 페이징 처리를 위한 여러 정보를 담고 있는 객체 Pagination 생성
				
				request.setAttribute("pagination", pagination);
				request.setAttribute("boardList", boardList);
				
				//System.out.println(boardList);
				
				path="/WEB-INF/views/boards/eventBoardList.jsp";
				
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
				// pagination, boardList를 request에 속성으로 추가한 후 boardList.jsp로 forward
				
			}else if(command.equals("view")) {
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				EventBoard board = service.selectEventBoard(boardNo);
				
				List<EventReply> rList = new EventReplyService().selectList(boardNo);
				
				 System.out.println(board);
				
				 request.setAttribute("board", board);
				 request.setAttribute("rList", rList);
				 
				path="/WEB-INF/views/boards/eventBoardView.jsp";
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
