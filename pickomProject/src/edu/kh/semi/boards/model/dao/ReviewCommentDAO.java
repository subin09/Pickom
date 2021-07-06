package edu.kh.semi.boards.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.semi.boards.model.vo.ComplainAttachment;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.ComplainCategory;
import edu.kh.semi.boards.model.vo.ComplainComment;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.ReviewComment;
import edu.kh.semi.member.model.dao.MemberDAO;

public class ReviewCommentDAO {

	// 자주 사용하는 JDBC 객체 참조 변수 선언
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public ReviewCommentDAO() {
		// selectBoard-query.xml 파일의 경로 얻어오기
		String filePath 
			= ReviewCommentDAO.class.getResource("/edu/kh/semi/sql/board/reviewComment-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 댓글 목록 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return list
	 * @throws Exception
	 */
	public List<ReviewComment> selectList(Connection conn, int boardNo) throws Exception{
		
		List<ReviewComment> list = new ArrayList<ReviewComment>();
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewComment comment = new ReviewComment();
				
				comment.setCommentNo(rs.getInt("REVIEW_COMMENT_NO"));
				comment.setCommentContent(rs.getString("REVIEW_COMMENT_CONTENT"));
				comment.setCommentDt(rs.getTimestamp("REVIEW_COMMENT_DT"));
				comment.setCommentModifyDt(rs.getTimestamp("REVIEW_COMMENT_MODIF"));
				comment.setCommentStatus(rs.getString("REVIEW_COMMENT_STATUS"));
				comment.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				comment.setMemberNo(rs.getInt("MEMBER_NO"));
				comment.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				comment.setMemberGrade(rs.getString("MEMBER_GRADE"));
				comment.setReportNo(rs.getInt("REPORT_NO"));
				System.out.println(comment.getMemberGrade());
				list.add(comment);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return list;
	}


	/** 댓글 삽입 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Connection conn, ReviewComment comment) throws Exception{
		
		int result = 0;

		String sql = prop.getProperty("insertComment");
		
		try {

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getMemberNo());
			pstmt.setInt(3, comment.getReviewNo());
			
			result = pstmt.executeUpdate();
					
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 댓글 수정 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Connection conn, ReviewComment comment) throws Exception {
		int result = 0;

		String sql = prop.getProperty("updateComment");
		
		try {

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 댓글 삭제
	 * @param conn
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteComment(Connection conn, int commentNo) throws Exception{
		int result = 0;

		String sql = prop.getProperty("deleteComment");
		
		try {

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}