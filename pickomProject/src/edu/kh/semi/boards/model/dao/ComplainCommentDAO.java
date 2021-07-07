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
import edu.kh.semi.member.model.dao.MemberDAO;

public class ComplainCommentDAO {

	// 자주 사용하는 JDBC 객체 참조 변수 선언
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public ComplainCommentDAO() {
		// selectBoard-query.xml 파일의 경로 얻어오기
		String filePath 
			= ComplainCommentDAO.class.getResource("/edu/kh/semi/sql/board/complainComment-query.xml").getPath();                    
		
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
	public List<ComplainComment> selectList(Connection conn, int boardNo) throws Exception{
		
		List<ComplainComment> list = new ArrayList<ComplainComment>();
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ComplainComment comment = new ComplainComment();
				
				comment.setCommentNo(rs.getInt("COMPLAIN_COMMENT_NO"));
				comment.setCommentContent(rs.getString("COMPLAIN_COMMENT_CONTENT"));
				comment.setCommentDt(rs.getTimestamp("COMPLAIN_COMMENT_DT"));
				comment.setCommentModify(rs.getTimestamp("COMPLAIN_COMMENT_MODIFY"));
				comment.setCommentStatus(rs.getString("COMPLAIN_COMMENT_STATUS"));
				comment.setComplainNo(rs.getInt("COMPLAIN_NO"));
				comment.setMemberNo(rs.getInt("MEMBER_NO"));
				comment.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				comment.setMemberGrade(rs.getString("MEMBER_GRADE"));
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
	public int insertComment(Connection conn, ComplainComment comment) throws Exception{
		
		int result = 0;

		String sql = prop.getProperty("insertComment");
		
		try {

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getMemberNo());
			pstmt.setInt(3, comment.getComplainNo());
			
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
	public int updateComment(Connection conn, ComplainComment comment) throws Exception {
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