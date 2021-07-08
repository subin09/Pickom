package edu.kh.semi.boards.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.kh.semi.boards.model.vo.EventAttachment;
import edu.kh.semi.boards.model.vo.EventBoard;
import edu.kh.semi.boards.model.vo.EventCategory;
import edu.kh.semi.boards.model.vo.Pagination;

public class EventBoard2DAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties 객체 참조 변수 선언
	private Properties prop = null;

	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML 파일을 읽어와 prop에 저장
	public EventBoard2DAO() {
		prop = new Properties();

		String filePath = EventBoard2DAO.class.getResource("/edu/kh/semi/sql/board/eventBoard2-query.xml").getPath();

		try {

			prop = new Properties();
			// filePath 변수에 저장된 경로로부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 행사게시판 카테고리 DAO
	 * @param conn
	 * @return category
	 * @throws Exception
	 */
	public List<EventCategory> selectEventCategoryList(Connection conn) throws Exception {
		List<EventCategory> category = new ArrayList<EventCategory>();
		
		String sql=prop.getProperty("selectEventCategoryList");
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				EventCategory eca = new EventCategory();
				eca.setEventCategoryCd(rs.getInt("EVENT_CATEGORY_CD"));
				eca.setEventCategoryNm(rs.getString("EVENT_CATEGORY_NM"));
				
				category.add(eca);
			}
			
			
		}finally {
			close(rs);
		}
		
		return category;
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
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		
		return boardNo;
	}

	/** 게시글 작성 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, EventBoard board) throws Exception{
		
		int result = 0;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, board.getEventBodNo());
			pstmt.setString(2, board.getEventBodTitle());
			pstmt.setString(3, board.getEventBodContent());
			
			pstmt.setString(4, board.getStartDate());
			pstmt.setString(5, board.getFinalDate());
			
			pstmt.setInt(6,board.getEventCategoryCd());
			pstmt.setInt(7, board.getMemberNo());
			
			result = pstmt.executeUpdate();
					
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 첨부파일 삽입 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertEventAttachment(Connection conn, EventAttachment at) throws Exception{

		int result = 0;
		
		String sql = prop.getProperty("insertEventAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getEventFilePath());
			pstmt.setString(2, at.getEventFileNm());
			pstmt.setInt(3, at.getEventFileLv());
			pstmt.setInt(4, at.getEventBodNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 행사 게시글 수정 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateEventBoard(Connection conn, EventBoard board) throws Exception{
		int result = 0;
		
		String sql = prop.getProperty("updateEventBoard");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, board.getEventBodTitle());
			pstmt.setString(2, board.getEventBodContent());
			pstmt.setInt(3, board.getEventCategoryCd());
			pstmt.setString(4, board.getStartDate());
			pstmt.setString(5, board.getFinalDate());
			pstmt.setInt(6, board.getEventBodNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	/** 행사 게시글 첨부파일 수정 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int updateEventAttachment(Connection conn, EventAttachment at) throws Exception {
		
		int result = 0;
		
		String sql = prop.getProperty("updateEventAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getEventFileNm());
			pstmt.setInt(2, at.getEventBodNo());
			pstmt.setInt(3,at.getEventFileLv());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 행사게시글 삭제 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int deleteEventBoard(Connection conn, EventBoard board) throws Exception {
		int result = 0;
		String sql = prop.getProperty("deleteEventBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getEventBodNo());
			
			result = pstmt.executeUpdate();
					
		}finally {
			close(pstmt);
		}
			
		return result;
	}

	
	
	
	
	
	
}
