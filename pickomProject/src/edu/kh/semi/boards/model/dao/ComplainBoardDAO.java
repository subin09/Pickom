package edu.kh.semi.boards.model.dao;

import static edu.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



import edu.kh.semi.boards.model.vo.ComplainAttachment;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.Pagination;

public class ComplainBoardDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public ComplainBoardDAO() {
		// member-query.xml 파일의 경로 얻어오기
		String filePath 
			= ComplainBoardDAO.class.getResource("/edu/kh/semi/sql/board/complainBoard-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 고객센터 게시판 페이징 처리
	 * @param conn
	 * @param cp
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int cp, int boardType) throws Exception{
		int listCount = 0;	
		String sql = prop.getProperty("getBoardListCount");
		
		
		try {
			 
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				listCount = rs.getInt(1);   
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return listCount;
	}
	 
	/** 고객센터 검색 페이징 처리 - 제목
	 * @param conn
	 * @param cp
	 * @return listCount
	 * @throws Exception
	 */
	public int getTitleSearchCount(Connection conn, int cp, int searchType, String searchValue) throws Exception{
		int listCount = 0;	
		String sql = prop.getProperty("getTitleListCount");


		
		try {
			 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);   
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	/** 고객센터 검색 페이징 처리 - 게시글
	 * @param conn
	 * @param cp
	 * @return listCount
	 * @throws Exception
	 */
	public int getContentSearchCount(Connection conn, int cp, int searchType, String searchValue) throws Exception{
		int listCount = 0;	
		String sql = prop.getProperty("getContentListCount");

		
		try {
			 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);   
			}
		} finally {
			close(rs);
			close(pstmt);
		}
	
		return listCount;
	}
	
	
	/** 고객센터 카테고리별 페이징 처리
	 * @param conn
	 * @param cp
	 * @return listCount
	 * @throws Exception 
	 * @throws Exception
	 */
	public int getCategoryCount(Connection conn, int cp, int boardType) throws Exception {
		int listCount = 0;	
		String sql = prop.getProperty("getCategoryListCount");

		try {
			 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardType);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);   
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}



	/** 고객센터 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<ComplainBoard> selectBoardList(Connection conn, Pagination pagination) throws Exception {
		List<ComplainBoard> boardList = new ArrayList<ComplainBoard>();
		
		String sql = prop.getProperty("selectComplainBoardList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);

			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
									// (1-1)*10+1  /  (5-1)*10+1
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ComplainBoard board = new ComplainBoard();
				
				board.setComplainNo(rs.getInt("COMPLAIN_NO"));
				System.out.println(rs.getInt("COMPLAIN_NO"));
				board.setCategoryName(rs.getString("CS_CATEGORY_NM"));
				board.setComplainTitle(rs.getString("COMPLAIN_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setComplainCount(rs.getInt("COMPLAIN_COUNT"));
				board.setComplainDate(rs.getTimestamp("COMPLAIN_DATE"));
				board.setComplainStatus(rs.getString("COMPLAIN_STATUS"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}
	
	
	/** 고객센터 카테고리별 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<ComplainBoard> getCategoryBoardList(Connection conn, Pagination pagination, int boardType) throws Exception {
		List<ComplainBoard> boardList = new ArrayList<ComplainBoard>();
		
		String sql = prop.getProperty("selectCategoryBoardList");

		try {
			pstmt = conn.prepareStatement(sql);

			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
									// (1-1)*10+1  /  (5-1)*10+1
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ComplainBoard board = new ComplainBoard();
				
				board.setComplainNo(rs.getInt("COMPLAIN_NO"));
				System.out.println(rs.getInt("COMPLAIN_NO"));
				board.setCategoryName(rs.getString("CS_CATEGORY_NM"));
				board.setComplainTitle(rs.getString("COMPLAIN_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setComplainCount(rs.getInt("COMPLAIN_COUNT"));
				board.setComplainDate(rs.getTimestamp("COMPLAIN_DATE"));
				board.setComplainStatus(rs.getString("COMPLAIN_STATUS"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	
	/** 고객센터 검색 게시글 목록 조회 - 제목
	 * @param conn
	 * @param pagination
	 * @param searchType
	 * @return
	 * @throws Exception
	 */
	public List<ComplainBoard> getSearchTitleList(Connection conn, Pagination pagination, int searchType, String searchValue) throws Exception{
		List<ComplainBoard> boardList = new ArrayList<ComplainBoard>();

		String sql = prop.getProperty("selectSearchTitleList");
	
		
		try {
			pstmt = conn.prepareStatement(sql);

			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
									// (1-1)*10+1  /  (5-1)*10+1
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ComplainBoard board = new ComplainBoard();
				
				board.setComplainNo(rs.getInt("COMPLAIN_NO"));
				System.out.println(rs.getInt("COMPLAIN_NO"));
				board.setCategoryName(rs.getString("CS_CATEGORY_NM"));
				board.setComplainTitle(rs.getString("COMPLAIN_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setComplainCount(rs.getInt("COMPLAIN_COUNT"));
				board.setComplainDate(rs.getTimestamp("COMPLAIN_DATE"));
				board.setComplainStatus(rs.getString("COMPLAIN_STATUS"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("pickom이 왜 2개나 넘어오지?" + boardList);
		return boardList;

	}
	
	/** 고객센터 검색 게시글 목록 조회 - 내용
	 * @param conn
	 * @param pagination
	 * @param searchType
	 * @return
	 * @throws Exception
	 */
	public List<ComplainBoard> getSearchContentList(Connection conn, Pagination pagination, int searchType, String searchValue) throws Exception{
		List<ComplainBoard> boardList = new ArrayList<ComplainBoard>();

		String sql = prop.getProperty("selectSearchContentList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
									// (1-1)*10+1  /  (5-1)*10+1
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ComplainBoard board = new ComplainBoard();
				
				board.setComplainNo(rs.getInt("COMPLAIN_NO"));
				System.out.println(rs.getInt("COMPLAIN_NO"));
				board.setCategoryName(rs.getString("CS_CATEGORY_NM"));
				board.setComplainTitle(rs.getString("COMPLAIN_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setComplainCount(rs.getInt("COMPLAIN_COUNT"));
				board.setComplainDate(rs.getTimestamp("COMPLAIN_DATE"));
				board.setComplainStatus(rs.getString("COMPLAIN_STATUS"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
				
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;

	}


	/** 고객센터 공지사항 조회 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<ComplainBoard> selectNoticeList(Connection conn) throws Exception{
		List<ComplainBoard> boardList = new ArrayList<ComplainBoard>();
		
		String sql = prop.getProperty("selectComplainNoticeList");
		
		
		
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ComplainBoard board = new ComplainBoard();
				
				board.setComplainNo(rs.getInt("COMPLAIN_NO"));
				System.out.println(rs.getInt("COMPLAIN_NO"));
				board.setCategoryName(rs.getString("CS_CATEGORY_NM"));
				board.setComplainTitle(rs.getString("COMPLAIN_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setComplainCount(rs.getInt("COMPLAIN_COUNT"));
				board.setComplainDate(rs.getTimestamp("COMPLAIN_DATE"));
				board.setComplainStatus(rs.getString("COMPLAIN_STATUS"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(stmt);
		}
		
		return boardList;
	}
	
	/** 게시글 상세조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public ComplainBoard selectBoard(Connection conn, int boardNo) throws Exception{
		ComplainBoard board = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			board = new ComplainBoard();
			
			// board에 필드 중 atList에 값을 저장할 수 있도록 List 객체 하나를 생성하여 세팅 
			board.setAtList(new ArrayList<ComplainAttachment>());
			
			
			
			boolean flag = true; // 아래 반복문 첫 반복을 하고 있s을 때 true, 아닐 때 false를 나타내는 신호
			while(rs.next()) {
				
				if(flag) {
					board.setComplainNo(rs.getInt("COMPLAIN_NO"));
					board.setCategoryName(rs.getString("CS_CATEGORY_NM"));
					board.setComplainTitle(rs.getString("COMPLAIN_TITLE"));
					board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
					board.setMemberGrade(rs.getString("MEMBER_GRADE"));
					board.setComplainCount(rs.getInt("COMPLAIN_COUNT"));
					board.setComplainDate(rs.getTimestamp("COMPLAIN_DATE"));
					board.setComplainStatus(rs.getString("COMPLAIN_STATUS"));
					
					board.setComplainContent(rs.getString("COMPLAIN_CONTENT"));
					board.setMemberNo(rs.getInt("MEMBER_NO"));
					board.setComplainModifyDt(rs.getTimestamp("COMPLAIN_MODIFY_DT"));
					flag = false;
				}
				
				// 조회된 파일 관련 정보를 저장할 객체 선언(경로, 이름, 레벨)
				ComplainAttachment at = new ComplainAttachment();
				at.setFilePath(rs.getString("COMPLAIN_FILE_PATH"));
				at.setFileName(rs.getString("COMPLAIN_FILE_NM"));
				at.setFileLevel(rs.getInt("COMPLAIN_FILE_LV"));
				
		
				// 값 세팅이 완료된 Attachment 객체를 
				// board의 atList에 추가 
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
	 * @return
	 * @throws Exception
	 */
	public int increaseComplainCount(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		String sql = prop.getProperty("increaseReadCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();

			
		}finally {
			close(pstmt);
		}
		return result;
	}


	

}
