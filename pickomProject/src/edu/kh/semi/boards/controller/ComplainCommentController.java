package edu.kh.semi.boards.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.kh.semi.boards.model.service.ComplainCommentService;
import edu.kh.semi.boards.model.service.ComplainCommentService;
import edu.kh.semi.boards.model.vo.ComplainComment;

@WebServlet("/complainComment/*")
public class ComplainCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/complainComment/").length());
		
		try {
			
			ComplainCommentService service = new ComplainCommentService();
			
			// 댓글 목록 조회 (ajax)
			if(command.equals("list")) {
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				
				List<ComplainComment> list = service.selectList(boardNo);
				// response.getWriter().print(list); 
				// 밑에 isnertcomment에서처럼 이렇게 보내면 배열이 아니라 객체의 toString 문자열로 보내져버림
				// 왜? 요청/응답 시 데이터는 기본적으로 문자열의 형태로 전달이 된다

				// 해결 방법이 필요 -> List가 List로 인식될 수 있게 처리작업 필요
				// List == Object
				// Ajax == Javascript
				// 자바스크립트에서 객체를 작성하는 방법 == {K:V, K:V}
				
				// 자바스크립트에서 객체형태라고 인식하는 문자열 형태로 바꿔준다. 
				// 자바스크립트 객체의 모양을 하고 있는 문자열 
				// 대부분의 프로그래밍 언어에서 JSON포맷 데이터를 핸들링 할 수 있는 
				// 라이브러리를 제공하기 때문에 
				// C에서 JAVA, 파이썬 간에 JSON 형태로 돌려가며 데이터 공유 가능 
				// 경량 데이터임 그래서 REST API에서 포함
				// Javascript Object Notaion
				
				// JSON 데이터 반환하기(GSON 사용)
				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				gson.toJson(list, response.getWriter());
				// -> list를 JSON 형태로 변환하여 
				//    연결된 응답용 스트림을 이용해 출력			
			
			}
			
			// 댓글 삽입 Controller
			else if(command.equals("insertComment")) {
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				String commentContent = request.getParameter("commentContent");
				
				ComplainComment comment = new ComplainComment();

				comment.setMemberNo(memberNo);
				comment.setComplainNo(boardNo);
				comment.setCommentContent(commentContent);
				
				
				int result = service.insertComment(comment);
				
				//response.getWriter() : 클라이언트에게 응답할 수 있는 스트림을 얻어옴
				// 깜빡거림없이 그 부분만 서버가 연결되어있어야 하는데 그 기능을 얘가 한다.
				response.getWriter().print(result);
			}
			
			
			// 댓글 수정 Controller
			else if(command.equals("updateComment")) {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				String commentContent = request.getParameter("commentContent");
				
				ComplainComment comment = new ComplainComment();

				comment.setCommentNo(commentNo);
				comment.setCommentContent(commentContent);
				

				int result = service.updateComment(comment);
				
				response.getWriter().print(result);
			}
			
			
			// 댓글 삭제 Controller
			else if(command.equals("deleteComment")) {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				
				int result = service.deleteComment(commentNo);
				
				response.getWriter().print(result);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}

	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	
	
	
	
	
	
	

}
