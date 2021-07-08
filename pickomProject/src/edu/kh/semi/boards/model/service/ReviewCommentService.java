package edu.kh.semi.boards.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.boards.model.dao.ComplainCommentDAO;
import edu.kh.semi.boards.model.dao.ReviewCommentDAO;
import edu.kh.semi.boards.model.vo.ComplainComment;
import edu.kh.semi.boards.model.vo.ReviewComment;


public class ReviewCommentService {
	
	private ReviewCommentDAO dao = new ReviewCommentDAO();

	/** 댓글 목록 조회 Service
	 * @param boardNo 
	 * @return list
	 * @throws Exception
	 */
	public List<ReviewComment> selectList(int boardNo) throws Exception{
		Connection conn = getConnection();
		
		List<ReviewComment> list = dao.selectList(conn, boardNo);
		
		close(conn);
		
		return list;
	}

	/** 댓글 삽입 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(ReviewComment comment) throws Exception{
		Connection conn = getConnection();
		
		// 크로스 사이트 스크립팅 방지 처리
		comment.setCommentContent( replaceParameter(comment.getCommentContent()) );
		// 개행 문자 변경
		comment.setCommentContent( comment.getCommentContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>")  );
		
		int result = dao.insertComment(conn, comment);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 수정 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(ReviewComment comment) throws Exception{
		
		Connection conn = getConnection();
		
		// 크로스 사이트 스크립팅 방지 처리
		comment.setCommentContent( replaceParameter(comment.getCommentContent()) );
		// 개행 문자 변경
		comment.setCommentContent( comment.getCommentContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>")  );
		
		
		int result = dao.updateComment(conn, comment);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	/** 댓글 삭제 Service
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteComment(int commentNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteComment(conn, commentNo);
		
		if(result > 0) 	commit(conn);
		else			rollback(conn);
		
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


}








