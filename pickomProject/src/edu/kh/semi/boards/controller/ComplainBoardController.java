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
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.boards.model.service.ComplainBoardService;

/** 불편사항 게시판 Servlet
 * Servlet implementation class ComplainBoardController
 */
@WebServlet("/complainBoard/*")
public class ComplainBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/complainBoard/").length());
	
		
		String path = null; 
		RequestDispatcher view = null; 
		
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			ComplainBoardService service = new ComplainBoardService();

			int cp = request.getParameter("cp") == null ? 1 :
					Integer.parseInt(request.getParameter("cp")); 
			
			// 불편사항 게시판 목록조회
			if(command.equals("list")) {
				
				// 공지,일반게시글 분류를 위한 type 가져오기
				int boardType = Integer.parseInt(request.getParameter("type"));
				
				// 검색했을 때 
				if(boardType == 100) {
					int searchType = Integer.parseInt(request.getParameter("searchType"));
					// 검색값 가져오기
					String searchValue = request.getParameter("searchValue");
					
					Pagination pagination = service.getSearchPagination(cp, boardType, searchType, searchValue);
					request.setAttribute("pagination", pagination);
					
					// 검색조건에 맞는 게시글 리스트 가져오기
					List<ComplainBoard> boardList = service.searchBoardList(pagination, searchType, searchValue);

					request.setAttribute("boardList", boardList);
					request.setAttribute("searchValue", searchValue);
					request.setAttribute("searchType", searchType);
				} else {
					
					// 페이징 처리는 일반게시글만 
					Pagination pagination = service.getPagination(cp, boardType);
					request.setAttribute("pagination", pagination);
					System.out.println("complainList pagination : "+ pagination) ;
					List<ComplainBoard> boardList = service.selectBoardList(pagination, boardType);
					request.setAttribute("boardList", boardList);
				}
				
				
				// 공지리스트도 가져오기
				List<ComplainBoard> noticeList = service.selectNoticeList();
				
				// 페이징처리를 위한 pagination 객체, 공지,일반게시글 목록  set
				request.setAttribute("noticeList", noticeList);

				path="/WEB-INF/views/boards/complainBoardList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
		} else if(command.equals("view")) {
			
			
			// 현재 클릭한 게시글 번호 가져오기
			int boardNo = Integer.parseInt(request.getParameter("no"));
			// '목록으로' 기능을 위해 이전에 클릭한 category type 가져오기
			int boardType =  Integer.parseInt(request.getParameter("type"));
			if(boardType == 100) {
				int searchType = Integer.parseInt(request.getParameter("searchType"));
				String searchValue = request.getParameter("searchValue");
				request.setAttribute("searchValue", searchValue);
				request.setAttribute("searchType", searchType);
				
				ComplainBoard board = service.selectBoard(boardNo);
				request.setAttribute("board", board);
				request.setAttribute("boardType", boardType);
				request.setAttribute("cp", cp);
			}
			ComplainBoard board = service.selectBoard(boardNo);
			
			// 현재 클릭한 게시글의 상세정보와 type, 전역변수 cp를 set해서 Detail.jsp를 보여주는 응답보내기
			request.setAttribute("board", board);
			request.setAttribute("boardType", boardType);
			request.setAttribute("cp", cp);
			
			path ="/WEB-INF/views/boards/complainBoardDetail.jsp";
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
