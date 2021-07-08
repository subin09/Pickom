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
import edu.kh.semi.boards.model.vo.Pagination;

public class EventBoardDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties 객체 참조 변수 선언
	private Properties prop = null;

	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML 파일을 읽어와 prop에 저장
	public EventBoardDAO() {
		prop = new Properties();

		String filePath = EventBoardDAO.class.getResource("/edu/kh/semi/sql/board/eventBoard-query.xml").getPath();

		try {

			prop = new Properties();
			// filePath 변수에 저장된 경로로부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 행사 게시판 페이징 처리
	 * 
	 * @param conn
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int cp) throws Exception {

		int listCount = 0;

		String sql = prop.getProperty("getEventBoardListCount");

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(stmt);
		}

		return listCount;
	}

	/**
	 * 행사 게시글 목록 조회
	 * 
	 * @param conn
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<EventBoard> selectEventBoardList(Connection conn, Pagination pagination) throws Exception {

		List<EventBoard> boardList = new ArrayList<EventBoard>();

		String sql = prop.getProperty("selectEventBoardList");

		try {
			pstmt = conn.prepareStatement(sql);

			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int endRow = startRow + pagination.getLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EventBoard board = new EventBoard();

				board.setEventBodNo(rs.getInt("EVENT_BOD_NO"));
				board.setEventBodTitle(rs.getString("EVENT_BOD_TITLE"));
				board.setMemberNm(rs.getString("MEMBER_NM"));
				board.setEventCategoryNm(rs.getString("EVENT_CATEGORY_NM"));
				board.setEventBodCreateDt(rs.getTimestamp("EVENT_BOD_CREATE_DT"));
				board.setEventBodReadCount(rs.getInt("EVENT_BOD_READ_COUNT"));

				boardList.add(board);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}

	/**
	 * 행사게시글 상세조회 DAO
	 * 
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public EventBoard selectEventBoard(Connection conn, int boardNo) throws Exception {

		EventBoard board = null;

		String sql = prop.getProperty("selectEventBoard");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();

			board = new EventBoard();

			// board에 필드 중 atList에 값을 저장할 수 있도록 List 객체 하나를 생성하여 세팅
			board.setAtList(new ArrayList<EventAttachment>());

			boolean flag = true;

			while (rs.next()) {

				if (flag) {
					board.setEventBodNo(rs.getInt("EVENT_BOD_NO"));
					board.setEventCategoryNm(rs.getString("EVENT_CATEGORY_NM"));
					board.setEventBodTitle(rs.getString("EVENT_BOD_TITLE"));
					board.setMemberNm(rs.getString("MEMBER_NM"));
					board.setEventBodReadCount(rs.getInt("EVENT_BOD_READ_COUNT"));
					board.setEventBodCreateDt(rs.getTimestamp("EVENT_BOD_CREATE_DT"));
					board.setStartDate(rs.getString("START_DATE"));
					board.setFinalDate(rs.getString("FINAL_DATE"));
					board.setEventBodContent(rs.getString("EVENT_BOD_CONTENT"));
					board.setMemberNo(rs.getInt("MEMBER_NO"));
					board.setEventBodModifyDt(rs.getTimestamp("EVENT_BOD_MODIFY_DT"));
				}

				// 조회된 파일 관련 정보를 저장할 객체 선언 (경로, 이름, 레벨)
				EventAttachment at = new EventAttachment();
				at.setEventFilePath(rs.getString("EVENT_FILE_PATH"));
				at.setEventFileNm(rs.getString("EVENT_FILE_NM"));
				at.setEventFileLv(rs.getInt("EVENT_FILE_LV"));

				// 값이 세팅이 완료된 Attachment 객체를 board의 atList에 추가
				board.getAtList().add(at);

			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return board;
	}

	/** 조회수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseEventBoardReadCount(Connection conn, int boardNo) throws Exception{
		 
		  int result =0;
	  
	  String sql = prop.getProperty("increaseEventBoardReadCount");
	  
	  try { 
	
		  pstmt=conn.prepareStatement(sql);
		  pstmt.setInt(1, boardNo);
		  
		  result = pstmt.executeUpdate();
	  
	  }finally {
	  
		  close(pstmt);
	  }
	  
	  return result;
	  
	}

	
	/**(검색)
	 * 행사 게시판 페이징 처리
	 * 
	 * @param conn
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int cp, String condition) throws Exception {

		int listCount = 0;

		String sql = prop.getProperty("getSearchCount") + condition;

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}
	
	/**
	 * 행사 게시글 목록 조회 (검색)
	 * 
	 * @param conn
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<EventBoard> selectEventBoardList(Connection conn, Pagination pagination, String condition) throws Exception {

		List<EventBoard> boardList = new ArrayList<EventBoard>();

		String sql = prop.getProperty("searchBoardList1") + condition + prop.getProperty("searchBoardList2");

		try {
			pstmt = conn.prepareStatement(sql);

			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int endRow = startRow + pagination.getLimit() - 1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				EventBoard board = new EventBoard();

				board.setEventBodNo(rs.getInt("EVENT_BOD_NO"));
				board.setEventBodTitle(rs.getString("EVENT_BOD_TITLE"));
				board.setMemberNm(rs.getString("MEMBER_NM"));
				board.setEventCategoryNm(rs.getString("EVENT_CATEGORY_NM"));
				board.setEventBodCreateDt(rs.getTimestamp("EVENT_BOD_CREATE_DT"));
				board.setEventBodReadCount(rs.getInt("EVENT_BOD_READ_COUNT"));

				boardList.add(board);
			}
		} finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}
	
	
	
	
	
	
}
