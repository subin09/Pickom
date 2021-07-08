package edu.kh.semi.boards.model.service;

import static edu.kh.semi.common.JDBCTemplate.close;
import static edu.kh.semi.common.JDBCTemplate.commit;
import static edu.kh.semi.common.JDBCTemplate.getConnection;
import static edu.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;


import edu.kh.semi.boards.model.dao.ComplainBoardDAO;
import edu.kh.semi.boards.model.dao.ComplainBoardDAO2;
import edu.kh.semi.boards.model.dao.ReviewBoardDAO2;
import edu.kh.semi.boards.model.vo.ComplainAttachment;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.ComplainCategory;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.boards.model.vo.ReviewCategory;

public class ReviewBoardService2 {
	
	private ReviewBoardDAO2 dao = new ReviewBoardDAO2();

	/** 카테고리 목록 조회 Service
	 * @return category
	 * @throws Exception
	 */
	public List<ReviewCategory> selectCategoryList() throws Exception{
		
		Connection conn = getConnection();
		
		List<ReviewCategory> categoryList = dao.selectCategoryList(conn);
		close(conn);
		
		return categoryList;
	}
	
	/** 게시글 삽입 Service
	 * @param board
	 * @param atList
	 * @param boardType
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(ReviewBoard board, List<ComplainAttachment> atList) throws Exception{
		Connection conn = getConnection();
		
		// 1. 다음 게시글 번호 얻어오기 
		// 왜? 동시 다발적인 INSERT 발생 시 시퀀스.NEXTVAL가 연속으로 이루어져 
		// 이후 시퀀스.CURRVAL가 호출될 때 원하는 값을 못얻어오는 경우가 생기기 때문에...
		int boardNo = dao.nextBoardNo(conn);
		int result = 0;
		// 2. 얻어온 boardNo가 존재할 경우 board객체 추가 후 board를 insert
		if(boardNo > 0) {
			board.setReviewNo(boardNo);
			
			// 2.5  게시글 내용의 줄바꿈을 <br> 태그로 변환하는 작업 필요
			//		+ 크로스 사이트 스크립팅 방지 처리
			// textarea의 개행문자 : \r\n
			// div의 개행문자 : <br>
			String boardContent = board.getReviewContent();
			boardContent = replaceParameter(boardContent);
			boardContent = boardContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
			board.setReviewContent(boardContent);
//					board.getBoardContent().replaceAll("\r\n", "<br>"); -> 더 짧은 코드!
			
			// 게시글 부분만 먼저 insert / Attachment는 나중에
			result = dao.insertBoard(conn, board);
			
			// 3. 게시글 부분 삽입 성공 시 파일 정보 삽입
			if(result > 0) {
				
				// 1. atList를 통째로 전달해서 insert 반복 수행 
				// 2. atList를 통째로 전달해서 insertAll 수행
				// 3. atList에서 하나씩 꺼내서 한 행씩 insert --> 우리는 3번 방법!
				for(ComplainAttachment at : atList) {
					at.setBoardNo(boardNo); // 게시글 번호 세팅
					
					
					result = dao.insertAttachment(conn, at);
					
					if(result == 0) { // insert 실패 시 바로 rollback후 남은 구문은 수행하지 않음.
						rollback(conn);
						break;
					}
				}
				
				if(result > 0) {
					commit(conn);
					// 게시글 삽입 성공하면 작성한 글로 가야되니까 게시글 번호가 필요함
					
					result = boardNo;
					// result에 boardNo를 담아 상세조회로 이동할 때 사용
				} else {
					rollback(conn); // 커밋 된 부분이 없이 close를 해버리면 자동으로 커밋을 해버리기 때문에 rollback 처리를 반드시 ㄱㄱ 
				}
				
				
			} else {
				rollback(conn);
			}
		}
		close(conn);
		return result;
		
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
		 * @param boardType
		 * @return result
		 * @throws Exception
		 */

		public int updateBoard(ReviewBoard board, List<ComplainAttachment> atList) throws Exception {
			Connection conn = getConnection();
			
					
			// 게시글, 첨부파일 DAO를 분리해서 호출
			
			// 크로스 사이트 스크립팅 방지 + 개행문자처리 
			board.setReviewContent(replaceParameter(board.getReviewContent()));
			board.setReviewTitle(replaceParameter(board.getReviewTitle())); // input type="text"는 개행문자가 못 들어옴
			
			board.setReviewContent(board.getReviewContent().replaceAll("\r\n", "<br>"));
			
			// 게시글 수정
			int result = dao.updateBoard(conn, board);
			
			if(result > 0) { //게시글 수정 성공 시 
				
				// 파일 정보를 atList에서 하나씩 꺼내서 DAO를 호출
				for(ComplainAttachment at : atList) {
					result = dao.updateAttachment(conn, at);
					
					// udpateAttachment() 수행 결과가 0인 경우 
					// ==  기존에 해당 레벨에 첨부 파일이 없었음
					// --> 없으면 insert해주자 
					
					if(result == 0) {
						result = dao.insertAttachment(conn, at);
						
						if(result == 0) { // insert가 안된 경우 

							break;
						}
					}
					
					
				}
				
				// 반복문 종료 후 result가 0보다 큰 경우 
				// == 첨부 파일의 수정, 삭제가 모두 성공한 경우 
				if(result>0) {
					commit(conn);;
				}else {
					rollback(conn);
				}
				
			} else {
				rollback(conn);
			}
			close(conn);
			return result;
		}

		/** 게시글 삭제 service
		 * @param boardNo
		 * @return
		 * @throws Exception
		 */
		public int deleteBoard(int boardNo) throws Exception{
			Connection conn = getConnection();
			
			int result = dao.deleteBoard(conn, boardNo);
			if(result>0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			return result;
		}
		


		/** 신고글 완료 Service
		 * @param report
		 * @return
		 * @throws Exception
		 */
		public int insertPostReport(Report report) throws Exception{
			Connection conn = getConnection();

			int result = 0;
			result = dao.reportPostCheck(conn, report);
			if(result > 0 ) { // 신고되어있지 않은 경우
				
				// 1. 신고 다음 번호를 구해온다. 
				int reportNo = dao.nextReportNo(conn);
				if(result>0) {
					
					String boardContent = report.getReportContent();
					boardContent = replaceParameter(boardContent);
					boardContent = boardContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
					report.setReportContent(boardContent);
					report.setReportNo(reportNo);
					
					
					// 신고 테이블 삽입
					result =  dao.insertReport(conn, report);
					if(result>0) { // 신고테이블에 삽입 성공하면 
						// 리뷰 테이블에서 해당 신고글 신고번호 삽입
						result = dao.updateBoardReport(conn, reportNo, report.getBoardNo());
						if(result >0 ) { // 까지 성공하면 커밋
							commit(conn);
						}else {
							rollback(conn);
						}
						
					} else {
						rollback(conn);
					}
				} else {
					rollback(conn);
				}
			
			} else { // 이미 신고된 경우
				rollback(conn);
			}
			close(conn);
			
			return result;
		}

		/** 신고댓글 완료 Service
		 * @param report
		 * @return
		 * @throws Exception
		 */
		public int insertCmReport(Report report) throws Exception{
			Connection conn = getConnection();

			int result = 0;
			result = dao.reportCmCheck(conn, report);
			if(result > 0 ) { // 신고되어있지 않은 경우
				
				// 1. 신고 다음 번호를 구해온다. 
				int reportNo = dao.nextReportNo(conn);
				if(result>0) {
					
					String boardContent = report.getReportContent();
					boardContent = replaceParameter(boardContent);
					boardContent = boardContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
					report.setReportContent(boardContent);
					report.setReportNo(reportNo);
					
					
					// 신고 테이블 삽입
					result =  dao.insertReport(conn, report);
					if(result>0) { // 신고테이블에 삽입 성공하면 
						// 리뷰 테이블에서 해당 신고글 신고번호 삽입
						result = dao.updateCmtReport(conn, reportNo, report.getCommentNo());
						if(result >0 ) { // 까지 성공하면 커밋
							commit(conn);
						}else {
							rollback(conn);
						}
						
					} else {
						rollback(conn);
					}
				} else {
					rollback(conn);
				}
			
			} else { // 이미 신고된 경우
				rollback(conn);
			}
			close(conn);
			
			return result;
		}

		/** 신고 게시글 복구 Service
		 * @param boardNo
		 * @return
		 * @throws Exception
		 */
		public int recoverBoard(int reportNo) throws Exception{
			Connection conn = getConnection();

			int result = 0;
			
			// 해당 게시글 신고번호 없애주기
			result = dao.recoverBoard(conn, reportNo);
			if(result > 0 ) { // 신고테이블 삭제 성공한 경우 
				
				// 신고테이블 신고행 삭제 
				result = dao.deleteReport(conn, reportNo);

				if(result>0) {
					 commit(conn);
				} else {
					rollback(conn);
				}
			
			} else { // 이미 신고된 경우
				rollback(conn);
			}
			close(conn);
			
			return result;
		}

		/** 신고 댓글 복구 Service
		 * @param commentNo
		 * @return
		 * @throws Exception
		 */
		public int recoverComment(int reportNo ) throws Exception{
			Connection conn = getConnection();

			int result = 0;
			
			// 해당 게시글 신고번호 없애주기
			result = dao.recoverComment(conn, reportNo );
			if(result > 0 ) { // 신고테이블 삭제 성공한 경우 
				
				// 신고테이블 신고행 삭제 
				result = dao.deleteReport(conn, reportNo);
				if(result>0) {
					 commit(conn);
				} else {
					rollback(conn);
				}
			
			} else { // 이미 신고된 경우
				rollback(conn);
			}
			close(conn);
			
			return result;
		}

		
		

		



}
