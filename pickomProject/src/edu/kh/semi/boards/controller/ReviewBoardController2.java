package edu.kh.semi.boards.controller;

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

import edu.kh.semi.boards.model.vo.ComplainAttachment;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.ComplainCategory;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.boards.model.vo.ReviewCategory;
import edu.kh.semi.common.MyFileRenamePolicy;
import edu.kh.semi.member.model.vo.Member;

import edu.kh.semi.boards.model.service.ComplainBoardService;
import edu.kh.semi.boards.model.service.ComplainBoardService2;
import edu.kh.semi.boards.model.service.ReviewBoardService;
import edu.kh.semi.boards.model.service.ReviewBoardService2;

/**
 * Servlet implementation class reviewBoardDMLController
 */
@WebServlet("/reviewBoardDML/*")
public class ReviewBoardController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String command = uri.substring((contextPath + "/reviewBoardDML/").length());
			
			String path = null; 
			RequestDispatcher view = null; 
			
			String icon = null;
			String title = null;
			String text = null;
			
			try {
				
				ReviewBoardService2 service = new ReviewBoardService2();

				int cp = request.getParameter("cp") == null ? 1 :
						Integer.parseInt(request.getParameter("cp"));  
				
				// 리뷰 게시글 작성폼 보여주기
				if(command.equals("insertForm")) {
					
					// 게시글 작성할 때 보여줄카테고리 목록 조회
					List<ReviewCategory> categoryList = service.selectCategoryList();
					
					// 응답 객체에 카테고리 목록 set해서 Insert.jsp로 응답 보내기
					request.setAttribute("categoryList", categoryList);		
					path = "/WEB-INF/views/boards/reviewBoardInsert.jsp";
					
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
				
				
				// 리뷰 게시글 작성 제출하기
				else if(command.equals("insert")) {
					
					HttpSession session = request.getSession();

					int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
					int maxSize = 1024 * 1024 * 20; 
					
					String root = session.getServletContext().getRealPath("/");
					String filePath = "resources/img/reviewBoard/";
					
					// POST로 넘어온 게시글에 포함되는 이미지와 자료들 MultipartRequest 후처리 작업
					MultipartRequest mpRequest 
						= new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy()); 

					List<ComplainAttachment> atList = new ArrayList<ComplainAttachment>();
					Enumeration<String> images = mpRequest.getFileNames();

					while(images.hasMoreElements()) {

						String name = images.nextElement();
						
						if(mpRequest.getFilesystemName(name) != null) { // input type 태그는 값이 없어도 빈 문자열로 넘어오기 때문에 
							
							ComplainAttachment at = new ComplainAttachment();
							
							// 파일경로, 변경된 파일명, 파일 레벨
						    at.setFilePath(filePath); // 웹상 접근 경로만 저장
						    at.setFileName(mpRequest.getFilesystemName(name));
						    at.setFileLevel(Integer.parseInt(name.substring("img".length() ) ) );
						    
						    // 저장 완료된 Attachment 객체를 atList에 추가
						    atList.add(at); 
						    
						} 
					} // atList set작업 끝
					
					// POST로 넘어온 다른 파라미터값 board객체에 싣기 위해(쓴 게시글 Detail.jsp에서 보여줘야 하니까) 다시 변수 저장해주기
					String boardTitle = mpRequest.getParameter("boardTitle");
					String boardContent = mpRequest.getParameter("boardContent");
					
					// 게시글 작성할 때는 카테고리 코드 안 넘어옴
					int categoryCode = Integer.parseInt(mpRequest.getParameter("categoryCode"));
					int movieNo = Integer.parseInt(mpRequest.getParameter("movieNo"));
					// 게시글 관련 정보를 Board 객체에 저장하기
					// + 회원 번호(누가 썼는가?)
					ReviewBoard board = new ReviewBoard();
					board.setReviewTitle(boardTitle);
					board.setReviewContent(boardContent);
					board.setCategoryCd(categoryCode);
					board.setMemberNo(memberNo);
					board.setMovieNo(movieNo);

					// DB에 게시글 정보와 이미지를 삽입하는 Service를 호출
					int result = service.insertBoard(board, atList);
					// result 는 insert를 삽입 성공한 boardNo나 0이 담겨있음
					
					if(result > 0) {
						icon = "success";
						title = "게시글 등록 성공!";
						path = "../reviewBoard/view?type=0&no=" + result + "&cp=1";
						
					} else {
						icon = "error";
						title = "게시글 등록 실패";
						
						// 현재 주소를 요청하기 이전 주소 = insertForm
						path = request.getHeader("referer");
						
					}
					
					session.setAttribute("icon", icon);
					session.setAttribute("title", title);
					response.sendRedirect(path);
				}
				
			
				// 리뷰 게시글 수정폼 전환 컨트롤러
				else if(command.equals("updateForm")) {
					// 게시글 수정화면에 수정하려는 게시글의 내용이 미리 작성되어 있어야 함 
					// --> 게시글 상세 조회 + 카테고리 목록 조회
					List<ReviewCategory> categoryList = service.selectCategoryList();
					int boardNo = Integer.parseInt(request.getParameter("boardNo"));
					int boardType = Integer.parseInt(request.getParameter("type"));

					ReviewBoard board = new ReviewBoardService().selectBoard(boardNo);
					
					// 게시글 내용에 있는 개행문자 <br> --> \r\n으로 변경
					board.setReviewContent(board.getReviewContent().replaceAll("<br>", "\r\n"));
					
					request.setAttribute("categoryList", categoryList);
					request.setAttribute("board", board);
					request.setAttribute("type", boardType);
					
					path = "/WEB-INF/views/boards/reviewBoardUpdate.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
				
				// 리뷰 게시글 수정 제출 
				else if(command.equals("update")) {

					int maxSize = 1024 * 1024 * 20; 

					HttpSession session = request.getSession();
					
					String root = session.getServletContext().getRealPath("/"); 
					String filePath = "resources/img/reviewBoard/"; 
					

					MultipartRequest mpRequest = new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

					int boardNo = Integer.parseInt(mpRequest.getParameter("boardNo")); // boardUpdate.jsp 에서 hidden form 으로 넘어옴 
					String boardTitle = mpRequest.getParameter("boardTitle");
					String boardContent = mpRequest.getParameter("boardContent");
					int categoryCode = Integer.parseInt(mpRequest.getParameter("categoryCode"));

					
					ReviewBoard board = new ReviewBoard();
					board.setReviewNo(boardNo);
					board.setReviewTitle(boardTitle);
					board.setReviewContent(boardContent);
					board.setCategoryCd(categoryCode);

					List<ComplainAttachment> atList = new ArrayList<ComplainAttachment>();
	
					Enumeration<String> images = mpRequest.getFileNames();
					
					while(images.hasMoreElements()) { 
						String name = images.nextElement(); 
						
						if(mpRequest.getFilesystemName(name) != null) { // 값이 안 넘어오면 빈 문자열 : 값이 없다.
							ComplainAttachment at = new ComplainAttachment();
							
							at.setFilePath(filePath);
							at.setFileName(mpRequest.getFilesystemName(name));

							at.setFileLevel(Integer.parseInt(name.substring("img".length() ) ) );
							
							at.setBoardNo(boardNo);
							
							atList.add(at);
						}
						
					}
					
					int result = service.updateBoard(board, atList);
					cp = Integer.parseInt(mpRequest.getParameter("cp"));
					
					if(result > 0) {
						icon ="success";
						title ="게시글 수정 성공";
						path = "../reviewBoard/view?type=0&no="+boardNo+"&cp="+cp;
					} else {
						icon = "error";
						title ="게시글 수정 실패";
						path = request.getHeader("referer");
							
					}
					
					session.setAttribute("icon", icon);
					session.setAttribute("title", title);
					response.sendRedirect(path);
					
					
				}
				
				else if(command.equals("delete")) {
				
					int boardNo = Integer.parseInt(request.getParameter("no"));
					int boardType = Integer.parseInt(request.getParameter("type"));
					System.out.println("boardNoo은? : "+ boardNo);
					
					int result = service.deleteBoard(boardNo);
					System.out.println("result는? : " +result);
					
						
					
					if(result > 0) {
						icon ="success";
						title ="게시글 삭제 성공";
						if(boardType == 201) {
							path = "../reviewBoard/list?type=200";							
						} else {
							path = "../reviewBoard/list?type=0";
						}
					} else {
						icon = "error";
						title ="게시글 삭제 실패";
						path = request.getHeader("referer");
						
					}
					HttpSession session = request.getSession();
					session.setAttribute("icon", icon);
					session.setAttribute("title", title);
					response.sendRedirect(path);
					
				}
				
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

	}
