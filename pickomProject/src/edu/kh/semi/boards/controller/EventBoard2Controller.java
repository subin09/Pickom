package edu.kh.semi.boards.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import edu.kh.semi.boards.model.service.EventBoard2Service;
import edu.kh.semi.boards.model.service.EventBoardService;
import edu.kh.semi.boards.model.vo.EventAttachment;
import edu.kh.semi.boards.model.vo.EventBoard;
import edu.kh.semi.boards.model.vo.EventCategory;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.common.EventFileRenamePolicy;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/eventBoard2/*")
public class EventBoard2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/eventBoard2/").length());

		String path = null; // 응답 화면 주소 또는 경로
		RequestDispatcher view = null; // 요청 위임 객체 저장 참조 변수

		// sweetalert
		String icon = null;
		String title = null;
		String text = null;

		try {

			EventBoard2Service service = new EventBoard2Service();

			int cp = request.getParameter("cp") == null ? 1 : Integer.parseInt(request.getParameter("cp"));

			// 게시글 등록 화면 전환 Controller
			if (command.equals("insertForm")) {

				// 카테고리 목록 조회
				List<EventCategory> category = service.selectEventCategoryList();

				request.setAttribute("category", category);

				path = "/WEB-INF/views/boards/eventBoardInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			} else if (command.equals("insert")) {

				// jsp 안에 넘어와야 하는 파라미터들 name속성 값들 가져오기
				// categoryCode ,boardTitle, memberNo(Session)
				// 이미지 : img0~3
				// 내용 : boardContent

				// int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				//String boardTitle = request.getParameter("boardTitle");
				//String boardContent = request.getParameter("boardContent");

				HttpSession session = request.getSession();
				int memberNo = ((Member) session.getAttribute("loginMember")).getMemberNo();

				// System.out.println(categoryCode);
				//System.out.println(boardTitle);
				//System.out.println(boardContent);
				//System.out.println(memberNo);

				int maxSize = 1024 * 1024 * 20;

				String root = session.getServletContext().getRealPath("/");
				System.out.println("root : " + root);

				String filePath = "resources/img/";
				filePath += "eventboard/";

				// 실제 저장 경로 System.out.println("실제 저장 경로 : " + root + filePath);

				// 저장되는 파일명 변환 작업 // 중복되는 파일명 문제 ==> 변환 작업 // -> EventFileRenamePolicy

				MultipartRequest mpRequest = new MultipartRequest(request, root + filePath, maxSize, "UTF-8",
						new EventFileRenamePolicy());

				List<EventAttachment> atList = new ArrayList<EventAttachment>();
				
				//System.out.println(atList);
				
				
				Enumeration<String> images = mpRequest.getFileNames();

				while (images.hasMoreElements()) {

					String name = images.nextElement();
					//System.out.println("name : " + name);
					//System.out.println(mpRequest.getFilesystemName(name));
					//System.out.println(mpRequest.getOriginalFileName(name));

					// file명이 없으면 담을 필요가 없는 조건문을 생성
					// 전달된 파일의 변경된 이름이 있을 경우
					if (mpRequest.getFilesystemName(name) != null) {
						

						EventAttachment at = new EventAttachment();
						
						at.setEventFilePath(filePath);
						at.setEventFileNm(mpRequest.getFilesystemName(name));
						at.setEventFileLv(Integer.parseInt( name.substring("img".length())  ));
						
						atList.add(at);
					}

				}
				
				
				for(EventAttachment a : atList) {
					//System.out.println(a);
				}
				
				String boardTitle = mpRequest.getParameter("boardTitle");
				String boardContent = mpRequest.getParameter("boardContent");
				int categoryCode = Integer.parseInt(mpRequest.getParameter("categoryCode"));
				
				
				String startDate = mpRequest.getParameter("startDate");

				String finalDate = mpRequest.getParameter("finalDate");
				
				
				//System.out.println(startDate);
				//System.out.println(finalDate);
				
				
				
				//System.out.println(categoryCode);
				
				
				EventBoard board = new EventBoard();
				board.setEventBodTitle(boardTitle);
				board.setEventBodContent(boardContent);
				board.setEventCategoryCd(categoryCode);
				board.setMemberNo(memberNo);
				board.setStartDate(startDate);
				board.setFinalDate(finalDate);
				
				
				
				int result = service.insertEventBoard(board,atList);
				
				// 삽입 결과에 따른 결과 화면 제어
				if(result>0) {
					icon = "success";
					title = "게시글 등록 성공!!";
					
					path = "../eventBoard/view?no=" + result + "&cp=1";
			
				}else{
					icon = "error";
					title= "게시글 등록 실패";
					
					path = request.getHeader("referer");
				}
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
			}
			
			
			// 게시글 수정 화면 전환 Controller
			else if(command.equals("updateForm")) {
				
				// 수정하려고 하면 수정하려는 게시글의 내용이 미리 작성 되어 있어야 함
				// 필요한 것들 == 게시글 상세조회 + 카테고리 목록 조회
				
				
				// 만들어 둔 카테고리 목록 조회 가져오기
				List<EventCategory> category = service.selectEventCategoryList();
				
				// 게시글 상세조회 (게시글 번호 필요)
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				
				// 상세 게시글 조회는 EventBoard2Service가 아닌 EventBoard2service에 있긱 때문에 새로운 객체 생성
				EventBoard board = new EventBoardService().selectEventBoard(boardNo);
			
				// 게시글에 있는 개행문자 <br> --> \r\n으로 변경
				board.setEventBodContent(board.getEventBodContent().replaceAll("<br", "\r\n"));
				
				request.setAttribute("category", category);
				request.setAttribute("board", board);
			
				path="/WEB-INF/views/boards/eventBoardUpdate.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// 게시글 수정 Controller
			else if(command.equals("update")){

				
				// MultipartRequest 객체를 만들기 위한 값을 준비
				int maxSize = 1024*1024*20; // 20MB 용량 제한
				
				// 실제 서버 저장 경로 + 웹상 저장 경로
				HttpSession session = request.getSession();
				
				// --> WeBContent까지의 실제 경로
				String root = session.getServletContext().getRealPath("/");
				String filePath = "resources/img/";
				
				filePath += "eventboard/" ;
				
				MultipartRequest mpRequest = new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new EventFileRenamePolicy());
				
				// MultipartRequest 객체 생성 시 지정된 경로에 파일이 업로드 된다!
				
				// 게시글 수정에 사용되는 것에 필요한 파라미터 들 
				// 게시글 번호, 제목, 내용, 카테고리, 이미지 시작, 끝 날짜...
				int boardNo = Integer.parseInt(mpRequest.getParameter("boardNo"));
				String boardTitle = mpRequest.getParameter("boardTitle");
				String boardContent = mpRequest.getParameter("boardContent");
				int categoryCode = Integer.parseInt(mpRequest.getParameter("categoryCode"));
				String startDate = mpRequest.getParameter("startDate");
				String finalDate = mpRequest.getParameter("finalDate");
				
				EventBoard board = new EventBoard();
				board.setEventBodNo(boardNo);
				board.setEventBodTitle(boardTitle);
				board.setEventBodContent(boardContent);
				board.setEventCategoryCd(categoryCode);
				board.setStartDate(startDate);
				board.setFinalDate(finalDate);
				
				// 첨부파일(이미지) 정보를 List에 담기
				List<EventAttachment> atList = new ArrayList<EventAttachment>();
				
				// ****전달 받은 파라미터 중 type이 file 요소의 name 속성 값을 모두 반환****
				Enumeration<String> images = mpRequest.getFileNames();
				
				while(images.hasMoreElements()) { // 다음 name 속성 값이 있으면
					
					String name = images.nextElement(); // name 하나를 얻어온다
					System.out.println("name : " + name);
					System.out.println("변경 전 : " + mpRequest.getOriginalFileName(name));
					System.out.println("변경 후 : " + mpRequest.getFilesystemName(name));
				
					// 업로드 된 파일이 있을 때
					if(mpRequest.getFilesystemName(name) != null) {
						EventAttachment at = new EventAttachment();
						
						at.setEventFilePath(filePath);
						at.setEventFileNm(mpRequest.getFilesystemName(name));
						
						// 파일 레벨 at에 세팅하기
						at.setEventFileLv(Integer.parseInt( name.substring("img".length())  ));
						at.setEventBodNo(boardNo);
						
						atList.add(at);
					}
				}
				
				//System.out.println(board);
				for(EventAttachment at : atList) {
					//System.out.println(at);
				}
				
				
				// 게시글 수정 Service 호출
				int result = service.updateEventBoard(board, atList);
				
				// 수정 성공 -> 수정된 게시글 상세 조회 화면 == eventView
					// boardNo, cp
				
				// 수정 실패 -> 수정하던 페이지로 이동 eventUpdate로 
				
				cp = Integer.parseInt(mpRequest.getParameter("cp"));
				
				if(result > 0) {
					icon ="success";
					title = "게시글 수정 성공!!!";
					path = "../eventBoard/view?no="+boardNo+"&cp=1" ;
				}else {
					icon = "error";
					title = "게시글 수정 실패";
					path = request.getHeader("referer");
				}
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
				
				
			}
			
			
			else if(command.equals("delete")) {
				
				// 실제 서버 저장 경로 + 웹상 저장 경로
				HttpSession session = request.getSession();
				
				System.out.println(request.getParameter("boardNo"));
				
				int boardNo =Integer.parseInt(request.getParameter("boardNo"));
				
				EventBoard board = new EventBoard();
				board.setEventBodNo(boardNo);
				
				int result = service.deleteEventBoard(board);
				
				
				
				
				if(result>0) {
					icon="success";
					title="게시글 삭제 성공!";
					path = "/WEB-INF/views/boards/eventBoardList.jsp";
				}else {
					icon="error";
					title="게시글 삭제 실패";
					path = request.getHeader("referer");
				}
				
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
