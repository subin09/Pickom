package edu.kh.semi.boards.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.boards.model.vo.ReviewCategory;
import edu.kh.semi.boards.model.vo.ReviewComment;
import edu.kh.semi.movie.model.vo.Movie;
import edu.kh.semi.movie.model.vo.MovieGenre;

import edu.kh.semi.boards.model.service.ComplainBoardService;
import edu.kh.semi.boards.model.service.ReviewBoardService;


/** 리뷰 게시판 Servlet
 * Servlet implementation class ComplainBoardController
 */
@WebServlet("/reviewBoard/*")
public class ReviewBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/reviewBoard/").length());
	
		
		String path = null; 
		RequestDispatcher view = null; 
		
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			ReviewBoardService service = new ReviewBoardService();

			int cp = request.getParameter("cp") == null ? 1 :
					Integer.parseInt(request.getParameter("cp")); 
			
			// 리뷰 게시판 목록조회
			if(command.equals("list")) {
				
				// 공지,일반게시글 분류를 위한 type 가져오기
				int boardType = Integer.parseInt(request.getParameter("type"));
				
				
				// 검색했을 때 
				if(boardType == 100) {
					// 제목, 내용 중 선택한 값 가져오기 
					int searchType = Integer.parseInt(request.getParameter("searchType"));
					// 검색값 가져오기
					String searchValue = request.getParameter("searchValue");
					
					// 검색조건에 맞는 검색목록 페이징 처리
					Pagination pagination = service.getSearchPagination(cp, boardType, searchType, searchValue);
					request.setAttribute("pagination", pagination);
					System.out.println("영화제목으로 검색 pagination" + pagination);
					// 검색조건에 맞는 게시글 리스트 가져오기
					List<ReviewBoard> boardList = service.searchBoardList(pagination, searchType, searchValue);
					System.out.println("영화제목으로 검색 boardList" + boardList);
					request.setAttribute("boardList", boardList);
					request.setAttribute("searchValue", searchValue);
					request.setAttribute("searchType", searchType);
					
					path="/WEB-INF/views/boards/reviewBoardList.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				}  else if(boardType == 200) {  // 관리자 페이지 신고글 모음 
					
					Pagination pagination = service.getPagination(cp, boardType);
					request.setAttribute("pagination", pagination);
					
					List<ReviewBoard> boardList = service.selectBoardList(pagination, boardType);
					request.setAttribute("boardList", boardList);
					path="/WEB-INF/views/admin/adminReportPost.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} else if(boardType == 300){ // 관리자 페이지 신고댓글 모음
					Pagination pagination = service.getPagination(cp, boardType);
					request.setAttribute("pagination", pagination);
					
					List<Report> boardList = service.selectReportCommentList(pagination, boardType);
	
					request.setAttribute("boardList", boardList);
					path="/WEB-INF/views/admin/adminReportComment.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} else { // 리뷰게시판 첫번째 페이지
					
					// 페이징 처리는 일반게시글만 
					Pagination pagination = service.getPagination(cp, boardType);
					request.setAttribute("pagination", pagination);
					
					List<ReviewBoard> boardList = service.selectBoardList(pagination, boardType);
					request.setAttribute("boardList", boardList);
				
				List<MovieGenre> categoryList = service.getCategoryList();
				
				// 공지리스트도 가져오기
				List<ReviewBoard> noticeList = service.selectNoticeList();
				
				// 페이징처리를 위한 pagination 객체, 공지,일반게시글 목록  set
				request.setAttribute("noticeList", noticeList);
				System.out.println("noticeList" + noticeList);
				request.setAttribute("categoryList", categoryList);


				path="/WEB-INF/views/boards/reviewBoardList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				}
				
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
				
				ReviewBoard board = service.selectBoard(boardNo);
				request.setAttribute("board", board);
				request.setAttribute("boardType", boardType);
				request.setAttribute("cp", cp);
				
				path ="/WEB-INF/views/boards/reviewBoardDetail.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} else if(boardType == 201) { //신고폼 보여주기
				ReviewBoard board = service.selectReportBoard(boardNo);

				// 현재 클릭한 게시글의 상세정보와 type, 전역변수 cp를 set해서 Detail.jsp를 보여주는 응답보내기
				request.setAttribute("board", board);
				request.setAttribute("boardType", boardType);
				request.setAttribute("cp", cp);
				
				path ="/WEB-INF/views/admin/reportHandle.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} else {
				ReviewBoard board = service.selectBoard(boardNo);
				
				// 현재 클릭한 게시글의 상세정보와 type, 전역변수 cp를 set해서 Detail.jsp를 보여주는 응답보내기
				request.setAttribute("board", board);
				request.setAttribute("boardType", boardType);
				request.setAttribute("cp", cp);
				
				path ="/WEB-INF/views/boards/reviewBoardDetail.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
					
		} else if(command.equals("showMovies")) {
			
			String keyword = request.getParameter("keyword");
			List<Movie> movieList = service.getMovieList(keyword);
			System.out.println(movieList);
			Gson gson = new Gson();
			gson.toJson(movieList, response.getWriter());
		
		
		} else if(command.equals("showGenre")) {
			int movieNo = Integer.parseInt(request.getParameter("movieNo"));
			List<MovieGenre> gnList = service.getGnList(movieNo);
			Gson gson = new Gson();
			gson.toJson(gnList, response.getWriter());
			
		
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
