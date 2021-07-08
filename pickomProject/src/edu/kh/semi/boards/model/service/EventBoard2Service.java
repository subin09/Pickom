package edu.kh.semi.boards.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.semi.boards.model.dao.EventBoard2DAO;
import edu.kh.semi.boards.model.dao.EventBoardDAO;
import edu.kh.semi.boards.model.vo.EventAttachment;
import edu.kh.semi.boards.model.vo.EventBoard;
import edu.kh.semi.boards.model.vo.EventCategory;
import edu.kh.semi.boards.model.vo.Pagination;

public class EventBoard2Service {

	private EventBoard2DAO dao = new EventBoard2DAO();

	/** 행사게시판 카테고리 조회 Service
	 * @return category
	 * @throws Exception
	 */
	public List<EventCategory> selectEventCategoryList() throws Exception{
		Connection conn = getConnection();
		
		List<EventCategory> category = dao.selectEventCategoryList(conn);
		
		close(conn);
		
		return category;
	}

	/** 게시글 삽입 Service
	 * @param board
	 * @param atList
	 * @return result
	 * @throws Exception
	 */
	public int insertEventBoard(EventBoard board, List<EventAttachment> atList) throws Exception {
		Connection conn =getConnection();
		
		int boardNo = dao.nextBoardNo(conn);
		
		int result =0;
		
		if(boardNo>0) {
			board.setEventBodNo(boardNo);
			
			
			// 게시글 내용의 줄바꿈을 <br>하는 처리
			String boardTitle = board.getEventBodTitle();
			String boardContent = board.getEventBodContent();
			
			boardContent = replaceParameter(boardContent);
			boardTitle = replaceParameter(boardTitle);
			
			boardContent = boardContent.replaceAll("\r\n","<br>");
			board.setEventBodContent(boardContent);
			board.setEventBodTitle(boardTitle);
			
			
			result = dao.insertBoard(conn,board);
			// System.out.println("board" + board);
			// System.out.println("result"+result);
			
			// 게시글 부분 삽입 성공 시 파일 정보 삽입
			if(result>0) {
				
				for(EventAttachment at : atList) {
					at.setEventBodNo(boardNo); // 게시글 번호 세팅
					
					result = dao.insertEventAttachment(conn,at);
					
					
					// 삽입 실패 시
					if(result == 0) {
						// 삽입 실패 시 바로 rollback으로 남은 구문 수행하지 않음.
						rollback(conn);
						break;
					}
				}
				
				if(result>0) {
					commit(conn);
					result = boardNo;
					// result에 boardNo를 담아 상세조회로 이동할 때 사용하게 함.
				}else {
					rollback(conn);
				}
				
				
			}else {
				rollback(conn);
			}
		}
		close(conn);
		
		return result;
		
		// 게시글 삽입 성공 시 ----> 작성한 글로 이동
	}

	
	
	
	   // 크로스 사이트 스크립트 방지 메소드
	   private String replaceParameter(String param) {
	      String result = param;
	      if(param != null) {
	         result = result.replaceAll("&", "&amp;");
	         result = result.replaceAll("<", "&lt;");
	         result = result.replaceAll(">", "&gt;");
	         result = result.replaceAll("\"", "&quot;");
	      }
	      
	      return result;
	   }

	   
	   
	   
	   
	/** 게시글 수정 Service
	 * @param board
	 * @param atList
	 * @return result
	 * @throws Exception
	 */
	public int updateEventBoard(EventBoard board, List<EventAttachment> atList) throws Exception{
		Connection conn =getConnection();
		
		// 크로스 사이트 스크립팅 방지 + 개행문자 처리
		board.setEventBodContent(replaceParameter(board.getEventBodContent()) );
		board.setEventBodTitle(replaceParameter(board.getEventBodTitle()));
		board.setEventBodContent(board.getEventBodContent().replaceAll("\r\n","<br>"));
		
		// 게시글 수정
		int result = dao.updateEventBoard(conn,board);
		
		// 게시글 수정 성공 시
		if(result >0) {
			
			// atList에서 하나씩 꺼내서 DAO 호출
			for(EventAttachment at : atList) {
				
				result = dao.updateEventAttachment(conn, at);
				
				// updateEventAttachment() 수행 결과가 0인경우 == 기존에 해당 레벨에 첨부파일이 없었다
				// 없으면 insert
				
				if(result==0) {
					result=dao.insertEventAttachment(conn, at);
					
					// 파일이 삽입이 되지 않았을 때
					if(result ==0) {
						break;
					}
				}
			}
			
			// 반복문 종료 후 result가 0보다 큰 경우 == 수정, 삭제가 성공한 경우
			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	/** 행사 게시글 삭제 Service
	 * @param board
	 * @return
	 * @throws Exception
	 */
	public int deleteEventBoard(EventBoard board) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteEventBoard(conn,board);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		System.out.println(result);
		return result;
		
	}
	
	
	
	
}
