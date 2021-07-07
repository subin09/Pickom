package edu.kh.semi.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.admin.controller.model.service.AdminBoardService;
import edu.kh.semi.boards.model.service.ComplainBoardService;
import edu.kh.semi.boards.model.service.EventBoardService;
import edu.kh.semi.boards.model.service.ReviewBoardService;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.EventBoard;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/admin/adminMain")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		String path = null; 
		
		
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			int cp = request.getParameter("cp") == null ? 1 :
				Integer.parseInt(request.getParameter("cp")); 
			
			
			
			// 행사게시판 리스트 가져오기
			EventBoardService eventService = new EventBoardService();
			Pagination eventPagination = eventService.getPagination(cp);
			request.setAttribute("eventPagination", eventPagination);
			List<EventBoard> eventBoardList = eventService.selectEventBoardList(eventPagination);
			request.setAttribute("eventBoardList", eventBoardList);
			
			// 리뷰게시판 리스트 가져오기
			ReviewBoardService reviewService = new ReviewBoardService();
			Pagination reviewPagination = reviewService.getPagination(cp, 0);
			request.setAttribute("reviewPagination", reviewPagination);
			List<ReviewBoard> reviewBoardList = reviewService.selectBoardList(reviewPagination, 0);
			request.setAttribute("reviewBoardList", reviewBoardList);

			// 불편사항 게시판 리스트 가져오기
			ComplainBoardService complainService = new ComplainBoardService();
			Pagination complainPagination = complainService.getPagination(cp, 0);
			request.setAttribute("complainPagination", complainPagination);
			List<ComplainBoard> complainBoardList = complainService.selectBoardList(complainPagination, 0);
			request.setAttribute("complainBoardList", complainBoardList);
			
			
			// 신고게시글 리스트 가져오기
			Pagination reportPostPagination = reviewService.getPagination(cp, 200);
			request.setAttribute("reportPostPagination", reportPostPagination);
			List<ReviewBoard> reportPostList = reviewService.selectBoardList(reportPostPagination, 200);
			request.setAttribute("reportPostList", reportPostList);
			
			// 신고댓글 리스트 가져오기 
			Pagination reportCmtPagination = reviewService.getPagination(cp, 300);
			request.setAttribute("reportCmtPagination", reportCmtPagination);
			List<Report> reportCmtList = reviewService.selectReportCommentList(reportCmtPagination, 300);
			request.setAttribute("reportCmtList", reportCmtList);
			
			
			// 필요한거 : 행사게시판 게시글, 리뷰게시판 게시글, 신고게시글, 불편사항 게시글
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/adminMain.jsp"); 
			view.forward(request, response);
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
