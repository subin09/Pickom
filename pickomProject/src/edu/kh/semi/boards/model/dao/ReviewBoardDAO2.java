package edu.kh.semi.boards.model.dao;

import static edu.kh.semi.common.JDBCTemplate.close;

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
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.boards.model.vo.ReviewCategory;
import edu.kh.semi.member.model.dao.MemberDAO;

public class ReviewBoardDAO2 {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public ReviewBoardDAO2() {
		// selectBoard-query.xml 파일의 경로 얻어오기
		String filePath 
		= ReviewBoardDAO2.class.getResource("/edu/kh/semi/sql/board/reviewBoard2-query.xml").getPath();                                 
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 카테고리 목록 조회 DAO
	 * @param conn
	 * @return category
	 * @throws Exception
	 */
	public List<ReviewCategory> selectCategoryList(Connection conn) throws Exception{
		
		List<ReviewCategory> categoryList = new ArrayList<ReviewCategory>();

		String sql = prop.getProperty("selectCategoryList");
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ReviewCategory category = new ReviewCategory();
				
				category.setCategoryCode(rs.getInt("REVIEW_CATEGORY_CD"));
				category.setCategoryName(rs.getString("REVIEW_CATEGORY_NM"));

				categoryList.add(category);
			}
		
		} finally {
			close(rs);
			close(stmt);
		}
		
		return categoryList;
	}


	/** 다음 게시글 번호 조회 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{
		
		int boardNo = 0;
		
		String sql = prop.getProperty("nextBoardNo");
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return boardNo;
	}


	/** 게시글 삽입 DAO
	 * @param conn 
	 * @param board
	 * @param boardType
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, ReviewBoard board) throws Exception {
		int result = 0; 
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getReviewNo());
			pstmt.setString(2, board.getReviewTitle());
			pstmt.setString(3, board.getReviewContent());
			pstmt.setInt(4, board.getMovieNo());
			pstmt.setInt(5, board.getMemberNo());
			pstmt.setInt(6, board.getCategoryCd());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		return result;
	}


	/** 첨부 파일 삽입 DAO
	 * @param conn
	 * @param at
	 * @param boardNo
	 * @return result 
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, ComplainAttachment at) throws Exception {
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFilePath());
			pstmt.setString(2, at.getFileName());
			pstmt.setInt(3, at.getFileLevel());
			pstmt.setInt(4, at.getBoardNo());

			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 게시글 수정 DAO
	 * @param conn
	 * @param board
	 * @return result 
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, ReviewBoard board) throws Exception{
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setString(1, board.getReviewTitle());
			pstmt.setString(2, board.getReviewContent());
			pstmt.setInt(3, board.getCategoryCd());
			pstmt.setInt(4, board.getReviewNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
			
		}
				
		return result;
	}

	/** 첨부파일 수정 DAO
	 * @param conn
	 * @param at
	 * @return result 
	 * @throws Exception
	 */
	public int updateAttachment(Connection conn, ComplainAttachment at) throws Exception {
		int result = 0;
		String sql = prop.getProperty("updateAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFileName());
			pstmt.setInt(2, at.getBoardNo());
			pstmt.setInt(3, at.getFileLevel());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}


	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		String sql = prop.getProperty("deleteBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();
					
		}finally {
			close(pstmt);
		}
		return result;
	}
	



	/** 신고글 신고여부/삭제여부 확인
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int reportPostCheck(Connection conn, Report report) throws Exception{
		int result = 0;
		String sql = prop.getProperty("reportPostCheck");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, report.getBoardNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	/** 신고댓글 신고여부/삭제여부 확인
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int reportCmCheck(Connection conn, Report report) throws Exception{
		int result = 0;
		String sql = prop.getProperty("reportCmCheck");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, report.getCommentNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}


	/** 신고 다음 번호 가져오기 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int nextReportNo(Connection conn) throws Exception{
		int reportNo = 0;
		
		String sql = prop.getProperty("nextReportNo");
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				reportNo = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return reportNo;
	}


	/** 신고글 삽입
	 * @param report
	 * @return
	 * @throws Exception
	 */
	public int insertReport(Connection conn, Report report) throws Exception{
		int result = 0; 
		String sql = prop.getProperty("insertReport");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, report.getReportNo());
			pstmt.setString(2, report.getReportContent());
			pstmt.setString(3, report.getReportTitle());
			pstmt.setInt(4, report.getMemberNo());
			pstmt.setInt(5, report.getCategoryCd());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		return result;
	}
	


	/** 리뷰게시판 신고번호 삽입
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int updateBoardReport(Connection conn, int reportNo, int boardNo) throws Exception{
		int result = 0;
		String sql = prop.getProperty("updateBoardReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 리뷰댓글 신고번호 삽입
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int updateCmtReport(Connection conn, int reportNo, int commentNo) throws Exception{
		int result = 0;
		String sql = prop.getProperty("updateCmtReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			pstmt.setInt(2, commentNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 신고테이블 신고행 삭제
	 * @param conn
	 * @param reportNo
	 * @return
	 * @throws Exception
	 */
	public int deleteReport(Connection conn, int reportNo) throws Exception{
		int result = 0;
		String sql  = prop.getProperty("deleteReport");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 리뷰테이블 신고번호 삭제
	 * @param conn
	 * @param reportNo
	 * @return
	 * @throws Exception
	 */
	public int recoverBoard(Connection conn, int reportNo) throws Exception{
		int result = 0;
		String sql  = prop.getProperty("recoverBoard");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 댓글테이블 신고번호 삭제
	 * @param conn
	 * @param commentNo
	 * @return
	 * @throws Exception
	 */
	public int recoverComment(Connection conn, int reportNo ) throws Exception {
		int result = 0;
		String sql  = prop.getProperty("recoverComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}




	

}
