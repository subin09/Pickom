package edu.kh.semi.boards.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.boards.model.service.ComplainBoardService;
import edu.kh.semi.boards.model.service.ReportService;
import edu.kh.semi.boards.model.service.ReviewBoardService2;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewComment;
import edu.kh.semi.member.model.vo.Member;

/**
 * Servlet implementation class ReportController
 */
@WebServlet("/report/*")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/report/").length());
	
		
		String path = null; 
		RequestDispatcher view = null; 
		
		String icon = null;
		String title = null;
		String text = null;
		
		try {
			
			ReviewBoardService2 service = new ReviewBoardService2();
			
			int cp = request.getParameter("cp") == null ? 1 :
				Integer.parseInt(request.getParameter("cp"));  
			
			if(command.equals("reportPostForm")) {

				
				path = "/WEB-INF/views/boards/reportPostForm.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			} else if(command.equals("reportPostInsert")) {
				
				HttpSession session = request.getSession();
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				int categoryCd = Integer.parseInt(request.getParameter("reportType"));
				String reportTitle = request.getParameter("reportTitle");
				String reportContent = request.getParameter("reportContent");
				int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
				
				Report report = new Report();
				report.setReportTitle(reportTitle);
				report.setReportContent(reportContent);
				report.setMemberNo(memberNo);
				report.setCategoryCd(categoryCd);
				report.setBoardNo(boardNo);
				//System.out.println(report + "report 잘 넘어옴");
				
				int result = service.insertPostReport(report);
				
				
				if(result > 0) {

					
					icon = "success";
					title = "신고가 완료되었습니다";
					path = "../reviewBoard/list?type=0&cp=1";
					
					
				} else {
					icon = "error";
					title = "신고를 실패하였습니다";
					
					// 현재 주소를 요청하기 이전 주소 = insertForm
					path = request.getHeader("referer");
					
				}
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
				
			}  else if(command.equals("reportCmForm")) {
				
				path = "/WEB-INF/views/boards/reportCmForm.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			} else if(command.equals("reportCmInsert")) {
				HttpSession session = request.getSession();
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				int commentNo = Integer.parseInt(request.getParameter("cno"));
				int categoryCd = Integer.parseInt(request.getParameter("reportType"));
				String reportTitle = request.getParameter("reportTitle");
				String reportContent = request.getParameter("reportContent");
				int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
				
				Report report = new Report();
				report.setReportTitle(reportTitle);
				report.setReportContent(reportContent);
				report.setMemberNo(memberNo);
				report.setCategoryCd(categoryCd);
				report.setCommentNo(commentNo);

				
				int result = service.insertCmReport(report);
				
				
				if(result > 0) {

					
					icon = "success";
					title = "신고가 완료되었습니다";
					path = "../reviewBoard/list?type=0&cp=1&no="+boardNo;
					
					
				} else {
					icon = "error";
					title = "신고를 실패하였습니다";
					
					// 현재 주소를 요청하기 이전 주소 = insertForm
					path = request.getHeader("referer");
					
				}
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
			
			} else if(command.equals("recoveryPost")) {
				int reportNo = Integer.parseInt(request.getParameter("no"));
				int result = service.recoverBoard(reportNo);
				
				if(result > 0) {
					icon ="success";
					title ="게시글 복구 성공";
					path = "../reviewBoard/list?type=200";							

				} else {
					icon = "error";
					title ="게시글 복구 실패";
					path = request.getHeader("referer");
					
				}
				HttpSession session = request.getSession();
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
				
				
			} else if(command.equals("recoveryComment")) {
				int reportNo  = Integer.parseInt(request.getParameter("no"));
				
				int result = service.recoverComment(reportNo);
				
				response.getWriter().print(result);
			}
			
		}catch(Exception e ) {
				e.printStackTrace();
		}
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
