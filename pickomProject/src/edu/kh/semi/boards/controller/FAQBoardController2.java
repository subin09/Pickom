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


import edu.kh.semi.boards.model.vo.FAQAttachment;
import edu.kh.semi.boards.model.vo.FAQBoard;
import edu.kh.semi.common.MyFileRenamePolicy;
import edu.kh.semi.member.model.vo.Member;

import edu.kh.semi.boards.model.service.FAQBoardService;
import edu.kh.semi.boards.model.service.FAQBoardService2;

/**
 * Servlet implementation class ComplainBoardController
 */
@WebServlet("/FAQBoardDML/*")
public class FAQBoardController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Front Controller 패턴(디자인 패턴)
		// - 클라이언트의 요청을 한 곳으로 집중시켜 
		// 	  개발 및 유지보수의 효율성을 증가시킨 패턴
		
		//   요청에 따른 Servlet을 각각 생성하는 것이 아닌, 하나의 Servlet으로 여러 요청을 받음. 
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String command = uri.substring((contextPath + "/FAQBoardDML/").length());
			
			String path = null; // 응답 화면 주소 또는 경로 
			RequestDispatcher view = null; // 요청 위임 객체 저장 참조 변수 
			
			// sweetalert 용 변수 
			String icon = null;
			String title = null;
			String text = null;
			
			try {
				
				FAQBoardService2 service = new FAQBoardService2();
				
				// 현재 페이지(우리가 항상 지속적으로 가지고 있는 값)
				// 삼항 연산자를 이용해서 cp가 없으면 1, 있으면 int형태로 파싱한 cp값을 저장
				int cp = request.getParameter("cp") == null ? 1 : // current page에 아무것도 안 써져 있으면 무조건 첫 페이지 
						Integer.parseInt(request.getParameter("cp")); // current page에 뭐가 있다! 라고 하면 cp에 현재 cp 값을 지정 
				if(command.equals("insertForm")) {
					
					
					path = "/WEB-INF/views/boards/FAQBoardInsert.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
			
				
				// 게시글 삽입 Controller
				else if(command.equals("insert")) {
					
					// 게시글 쓸 때 필요한 정보 친구들 
					// categoryCode  /  boardTitle  /  memberNo(Session)
					// 이미지 : img0부터 img3까지, boardContent, type(boardType)
					
					//int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
					//String boardTitle = request.getParameter("boardTitle");
					//String boardContent = request.getParameter("boardContent");
					
					HttpSession session = request.getSession();
					int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
					
					//System.out.println(categoryCode);
					//System.out.println(boardTitle);
					//System.out.println(boardContent);

					
					// * 문제점 : POST 방식으로 전달된 데이터가 NULL로 표기됨
					//			  그런데 GET방식 전달 데이터는 잘 넘어옴
					// * 원인 : form 태그에 encType="multipart/form-data" 때문(이미지때문에 이걸 쓰는 것임)
					// * 해결 방법 : MultipartRequest를 이용하여 파라미터를 얻어오면 해결됨
					
					// ** MultipartRequest 생성 준비
					// MultipartRequest란?
					// - cos.jar 라이브러리에서 제공하는 multipart/form-data 형식의 요청을 
					//   받아 쉽게 처리할 수 있는 객체 
					// - 업로드 된 파일을 다룰 수 있음
					
					// 1. 전송되는 파일의 크기 제한 수치를 지정
					// 1KB == 1024byte 
					// 1MB == 1024KB
					
					// 100Mbps == 13.~~~ MB
					int maxSize = 1024 * 1024 * 20; // 20MB -> byte 
					
					// 2. 업로드 되는 파일이 실제로 저장될 서버 경로
					String root = session.getServletContext().getRealPath("/");
					
					
					String filePath = "resources/img/FAQBoard/";

					
					// 실제 저장 경로
					//System.out.println("실제 저장 경로 : "+ root+filePath);
					
					
					// 3. 저장되는 파일명 변환 작업
					// 파일명 중복으로 인한 문제를 해결하기 위해서 변호나 작업이 필요하다. 
					// KakaoTalk 파일명 참고
					// -> MyRenamePolicy 클래스 파일 생성
					
					// 4. MultipartRequest 객체 생성**************
					// ** MultipartRequest 객체가 생성되는 순간 
					//    전달된 파라미터 중 파일 관련 데이터는 지정된 경로에 파일로 바로 저장된다!!!!!!!!!!!!!
					MultipartRequest mpRequest 
						= new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy()); 
								   //기존 요청관련 객체 , 파일실제저장경로, 용량제한, 요청 중 파일이 아닌 파라미터의 문자인코딩, 파일명 변경 객체
					
					
					// 5. 전달 받은 파라미터 중 첨부파일(이미지)를 다루는 방법 
					
					// 5-1. DB에 이미지 정보를 모아서 전달할 List 생성
					List<FAQAttachment> atList = new ArrayList<FAQAttachment>();
					
					// 5-2. MultipartRequest에서 이미지 정보를 모두 얻어옴
					Enumeration<String> images = mpRequest.getFileNames();
					// Enumeration : Iterator의 과거 버전
					// Iterator : 컬렉션에 저장된 요소를 순차접근하는 반복접근자.
					
					// 5-3  얻어온 파일 정보를 반복 접근하여 atList에 순서대로 담기
					while(images.hasMoreElements()) {
						
						// Enumeration.hasMoreElements() : 다음 접근할 요소(값)이 있으면 true 
						String name = images.nextElement(); // 다음 요소(값) 얻어오기
						//System.out.println("input type=file의 name 속성값 : " + name);
						//System.out.println("변경된 파일명" + mpRequest.getFilesystemName(name));
						//System.out.println("변경전 파일명" + mpRequest.getOriginalFileName(name));
						// input type=file의 name 속성값 : img1
						// 변경된 파일명 : 20210630111716_10625.jpg
						// 변경전 파일명 : 2018 冬期日本語・日本文化研修_180206_0072.jpg
						
						// 근데 파일이 없어도 input 태그에서는 그냥 무조건 name값이 넘어와버림
						// 그니까 null 값인 애들은 DB에 저장 안되게 후처리를 해줘야 함
						// input type=file의 name 속성값 : img2
						// 변경된 파일명 : null
						// 변경전 파일명 : null
						
						// 여기서 알 수 있었던 것!!!***********
						// 실제 파일 업로드가 되지 않아도 
						// 비어있는 input type=file 태그가 넘어온다
						// 대신 변경 전/후 파일명은 null이다.
						
						// 전달된 파일의 변경된 이름이 있을 경우
						// == 업로드된 파일이 있다면
						if(mpRequest.getFilesystemName(name) != null) {
							
							// 파일 정보 저장용 객체 생성	
							FAQAttachment at = new FAQAttachment();
							
							// 파일경로, 변경된 파일명, 파일 레벨
						    at.setFilePath(filePath); // 웹상 접근 경로만 저장
						    at.setFileName(mpRequest.getFilesystemName(name));
						    at.setFileLevel(Integer.parseInt(name.substring("img".length() ) ) );
						    
						    // 저장 완료된 Attachment 객체를 atList에 추가
						    atList.add(at); 
						    
						} // end if
					} // end while
					
					
					
					// 6. 파일 외에 게시글 관련 정보를 MultipartRequest에서 얻어오기
					String boardTitle = mpRequest.getParameter("boardTitle");
					String boardContent = mpRequest.getParameter("boardContent");
					
					// 7. 게시글 관련 정보를 Board 객체에 저장하기
					// + 회원 번호(누가 썼는가?)
					FAQBoard board = new FAQBoard();
					board.setFaqTitle(boardTitle);
					board.setFaqContent(boardContent);
					board.setMemberNo(memberNo);
					
					// 8. 게시글 정보와 이미지를 삽입하는 Service를 호출
					int result = service.insertBoard(board, atList);
					// result 는 insert를 삽입 성공한 boardNo나 0이 담겨있음
					
					// 9. 삽입 결과에 따른 결과 화면 제어
					if(result > 0) {
						icon = "success";
						title = "게시글 등록 성공!";
						
						// 절대경로
						//path = request.getContextPath() + "/board/view?no=" + result + "&cp=1&type=" + boardType;
						
						// 상대경로
						path = "../FAQBoard/view?no=" + result + "&cp=1";
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
				
				else if(command.equals("updateForm")) {
					// 게시글 수정화면에 수정하려는 게시글의 내용이 미리 작성되어 있어야 함 
					// --> 게시글 상세 조회 + 카테고리 목록 조회
					
					int boardNo = Integer.parseInt(request.getParameter("boardNo"));
					
					FAQBoard board = new FAQBoardService().selectBoard(boardNo);
//					--> 틀린 이유 : boardContent에 줄바꿈 태그가 그대로 보여지게 됨 -> 바꿔서 가져오는 변경작업 필요
					
					// 게시글 내용에 있는 개행문자 <br> --> \r\n으로 변경(원래는 Service에 작성하는 게 좋음)
					board.setFaqContent(board.getFaqContent().replaceAll("<br>", "\r\n"));
					
					request.setAttribute("board", board);
					path = "/WEB-INF/views/boards/FAQBoardUpdate.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}
				
				// 게시글 수정 Controller 
				else if(command.equals("update")) {
					
					// MultipartRequest 객체를 만들기 위한 값 준비
					int maxSize = 1024 * 1024 * 20; // 20MB 용량 제한 
					
					// 실제 서버 저장 경로 + 웹상 접근 경로 
					HttpSession session = request.getSession();
					
					String root = session.getServletContext().getRealPath("/"); // WebContent 의 실제 경로
					String filePath = "resources/img/FAQBoard"; // 웹상 접근 경로(1/2)
					
					// multipart/form-data 형식으로 전달된 파라미터 중 
					// POST는 request.getParameter() 사용시 null을 반환하지만 
					// GET방식 데이터는 request.getParameter() 사용 시 정상적으로 값을 반환한다.

					
					MultipartRequest mpRequest = new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
					
					// MultipartRequest 객체 생성 시 지정된 경로에 파일이 업로드 된다.
					
					// 게시글 수정에 사용되는 파라미터(번호, 제목, 내용, 카테고리, 이미지..)
					int boardNo = Integer.parseInt(mpRequest.getParameter("boardNo")); // boardUpdate.jsp 에서 hidden form 으로 넘어옴 
					String boardTitle = mpRequest.getParameter("boardTitle");
					String boardContent = mpRequest.getParameter("boardContent");
					
					FAQBoard board = new FAQBoard();
					board.setFaqNo(boardNo);
					board.setFaqTitle(boardTitle);
					board.setFaqContent(boardContent);
					
					// 첨부파일(이미지) 정보를 List에 담기 
					List<FAQAttachment> atList = new ArrayList<FAQAttachment>();
					
					// 전달받은 파라미터 중 type이 file인 요소의 name 속성값을 모두 반환
					Enumeration<String> images = mpRequest.getFileNames();
					
					while(images.hasMoreElements()) { // 다음 name 속성 값이 있으면
						String name = images.nextElement(); // name 하나 얻어오기 --> originalFilename fileSystemName => 값이 안 넘어오면 빈 문자열
						
				
						// 업로드된 파일이 있을 때 
						if(mpRequest.getFilesystemName(name) != null) {
							FAQAttachment at = new FAQAttachment();
							
							at.setFilePath(filePath);
							at.setFileName(mpRequest.getFilesystemName(name));
							
							// 파일 레벨 at에 세팅하기
							at.setFileLevel(Integer.parseInt(name.substring("img".length() ) ) );
							
							at.setBoardNo(boardNo);
							
							atList.add(at);
						}
						
					}
					
//					System.out.println(board);
					for(FAQAttachment at : atList) { 
						// 새로 추가/수정된 부분만 넘어온다. 
						// => 바꾼 사진 정보 Attachment [fileNo=0, filePath=resources/images/freeboard/, fileNm=20210701121658_88641.jpg, fileLevel=0, boardNo=511]
						System.out.println(at);
					}
					
				
					
					// 게시글 수정 Service 호출
					int result = service.updateBoard(board, atList);
					
					// 수정 성공 --> 수정된 게시글 상세 조회 화면 
								// -> boardNo, type, cp 
					// 수정 실패 --> 수정하던 페이지로 이동
					
					cp = Integer.parseInt(mpRequest.getParameter("cp"));
					if(result > 0) {
						icon ="success";
						title ="게시글 수정 성공";
						path = "../FAQBoard/view?no="+boardNo+"&cp="+cp;
					} else {
						icon = "error";
						title ="게시글 수정 실패";
						path = request.getHeader("referer");
							
					}
					
					session.setAttribute("icon", icon);
					session.setAttribute("title", title);
					response.sendRedirect(path);
					
					
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
