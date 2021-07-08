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


import edu.kh.semi.boards.model.vo.FAQAttachment;
import edu.kh.semi.boards.model.vo.FAQBoard;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.member.model.dao.MemberDAO;

public class FAQBoardDAO2 {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public FAQBoardDAO2() {
		// selectBoard-query.xml 파일의 경로 얻어오기
		String filePath 
		= FAQBoardDAO2.class.getResource("/edu/kh/semi/sql/board/FAQBoard2-query.xml").getPath();                                 
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
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
	public int insertBoard(Connection conn, FAQBoard board) throws Exception {
		int result = 0; 
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getFaqNo());
			pstmt.setString(2, board.getFaqContent());
			pstmt.setString(3, board.getFaqTitle());
			pstmt.setInt(4, board.getMemberNo());

			
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
	public int insertAttachment(Connection conn, FAQAttachment at) throws Exception {
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
	public int updateBoard(Connection conn, FAQBoard board) throws Exception{
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setString(1, board.getFaqTitle());
			pstmt.setString(2, board.getFaqContent());
			pstmt.setInt(3, board.getFaqNo());
			
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
	public int updateAttachment(Connection conn, FAQAttachment at) throws Exception {
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
	

}
