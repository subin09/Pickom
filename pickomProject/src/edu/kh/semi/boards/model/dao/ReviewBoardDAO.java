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
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.boards.model.vo.ReviewCategory;
import edu.kh.semi.boards.model.vo.ReviewComment;
import edu.kh.semi.movie.model.vo.Movie;
import edu.kh.semi.movie.model.vo.MovieGenre;

public class ReviewBoardDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public ReviewBoardDAO() {
		// member-query.xml 파일의 경로 얻어오기
		String filePath 
			= ReviewBoardDAO.class.getResource("/edu/kh/semi/sql/board/reviewBoard-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 리뷰 게시판 페이징 처리
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

	/** 리뷰 게시판 페이징 처리 - 신고글
	 * @param conn
	 * @param cp
	 * @return 
	 * @return listCount
	 * @throws Exception
	 */
	public int getReportCount(Connection conn, int cp, int boardType) throws Exception{
		int listCount = 0;	
		String sql = prop.getProperty("getReportCount");
		
		
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
	
	/** 리뷰 게시판 페이징 처리 - 신고댓글
	 * @param conn
	 * @param cp
	 * @param boardType
	 * @return
	 * @throws Exception
	 */
	public int getReportCommentCount(Connection conn, int cp, int boardType) throws Exception {
		int listCount = 0;	
		String sql = prop.getProperty("getReportCommentCount");
		
		
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
	
	/** 고객센터 검색 페이징 처리 - 내용
	 * @param conn
	 * @param cp
	 * @return listCount
	 * @throws Exception
	 */
	public int getContentSearchCount(Connection conn, int cp, int searchType, String searchValue) throws Exception {
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
	
	/** 고객센터 검색 페이징 처리 - 영화
	 * @param conn
	 * @param cp
	 * @param searchType
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public int getMovieSearchCount(Connection conn, int cp, int searchType, String searchValue) throws Exception{
		int listCount = 0;	
		String sql = prop.getProperty("getMovieListCount");
		
		try {
			 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			pstmt.setString(2, searchValue);

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
	
	/** 리뷰게시판 카테고리별 페이징 처리
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
	public List<ReviewBoard> selectBoardList(Connection conn, Pagination pagination) throws Exception {
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();
		
		String sql = prop.getProperty("selectReviewBoardList");


		
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
				ReviewBoard board = new ReviewBoard();
				
				
				board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				board.setCategoryName(rs.getString("REVIEW_CATEGORY_CD"));
				board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
				board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
				board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
				board.setMovieTitleKo(rs.getString("MOVIE_TITLE_EN"));
				

				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
			
			
			
		}finally {
			close(rs);
			close(pstmt);
		}

		return boardList;
	}
	
	/** 리뷰 게시글 목록 조회 - 신고
	 * @param conn
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<ReviewBoard> getReportBoardList(Connection conn, Pagination pagination, int boardType) throws SQLException {
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();
		
		String sql = prop.getProperty("selectReportBoardList");
		
		
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
				ReviewBoard board = new ReviewBoard();
				
				
				board.setReportCd(rs.getInt("CS_CATEGORY_CD"));
				board.setReportTitle(rs.getString("REPORT_TITLE"));
				board.setReportContent(rs.getString("REPORT_CONTENT"));
				board.setReportDate(rs.getTimestamp("REPORT_DATE"));
				board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setReportCdNm(rs.getString("CS_CATEGORY_NM"));
				board.setReportNo(rs.getInt("REPORT_NO"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
			
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}
	
	
	/** 리뷰 댓글 목록 조회 - 신고
	 * @param conn
	 * @param pagination
	 * @param boardType
	 * @return
	 * @throws Exception
	 */
	public List<Report> getReportCommentBoardList(Connection conn, Pagination pagination, int boardType) throws Exception{
		List<Report> commentList = new ArrayList<Report>();
		String sql = prop.getProperty("selectReportCommentList");
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
				Report comment = new Report();
				comment.setBoardNo(rs.getInt("REVIEW_BOARD_NO"));
				comment.setReportNo(rs.getInt("REPORT_NO"));
				comment.setReportTitle(rs.getString("REPORT_TITLE"));
				comment.setReportContent(rs.getString("REPORT_CONTENT"));
				comment.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				comment.setReportDate(rs.getTimestamp("REPORT_DATE"));
				comment.setReportCdNm(rs.getString("CS_CATEGORY_NM"));
				
				
				
				// set 완료된 board를 boardList에 추가
				commentList.add(comment);
			}
		} finally {
			
		}
		return commentList;
	}
	
	
	/** 고객센터 카테고리별 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<ReviewBoard> getCategoryBoardList(Connection conn, Pagination pagination, int boardType) throws Exception {
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();
		
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
				ReviewBoard board = new ReviewBoard();
			
				
				board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				board.setCategoryName(rs.getString("REVIEW_CATEGORY_CD"));
				board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
				board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
				board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
				board.setMovieTitleKo(rs.getString("MOVIE_TITLE_EN"));
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	
	/** 리뷰게시판 검색 게시글 목록 조회(제목)
	 * @param conn
	 * @param pagination
	 * @param searchType
	 * @return
	 * @throws Exception
	 */
	public List<ReviewBoard> getSearchTitleList(Connection conn, Pagination pagination, int searchType, String searchValue) throws Exception{
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();

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
				ReviewBoard board = new ReviewBoard();
			
				
				board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				board.setCategoryName(rs.getString("REVIEW_CATEGORY_CD"));
				board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
				board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
				board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
				board.setMovieTitleKo(rs.getString("MOVIE_TITLE_EN"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;

	}
	
	/** 리뷰게시판 검색 게시글 목록 조회(내용)
	 * @param conn
	 * @param pagination
	 * @param searchType
	 * @return
	 * @throws Exception
	 */
	public List<ReviewBoard> getSearchContentList(Connection conn, Pagination pagination, int searchType, String searchValue) throws Exception{
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();
		
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
				ReviewBoard board = new ReviewBoard();
				
				
				board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				board.setCategoryName(rs.getString("REVIEW_CATEGORY_CD"));
				board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
				board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
				board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
				board.setMovieTitleKo(rs.getString("MOVIE_TITLE_EN"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
		
	}
	
	
	public List<ReviewBoard> getSearchMovieBoardList(Connection conn, Pagination pagination, int searchType,
			String searchValue) throws Exception {
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();

		String sql = prop.getProperty("selectSearchMovieList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
									// (1-1)*10+1  /  (5-1)*10+1
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt.setString(1, searchValue);
			pstmt.setString(2, searchValue);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewBoard board = new ReviewBoard();
			
				
				board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				board.setCategoryName(rs.getString("REVIEW_CATEGORY_CD"));
				board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
				board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
				board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
				board.setMovieTitleKo(rs.getString("MOVIE_TITLE_EN"));
				
				
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
	public List<ReviewBoard> selectNoticeList(Connection conn) throws Exception{
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();
		
		String sql = prop.getProperty("selectReviewNoticeList");
		
		
		
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ReviewBoard board = new ReviewBoard();
				
				board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
				board.setCategoryName(rs.getString("REVIEW_CATEGORY_CD"));
				board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
				board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
				board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
				
				
				
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
	public ReviewBoard selectBoard(Connection conn, int boardNo) throws Exception{
		ReviewBoard board = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			board = new ReviewBoard();
			
			// board에 필드 중 atList에 값을 저장할 수 있도록 List 객체 하나를 생성하여 세팅 
			board.setAtList(new ArrayList<ComplainAttachment>());
			board.setGnList(new ArrayList<MovieGenre>());
			
			
			boolean flag = true; // 아래 반복문 첫 반복을 하고 있s을 때 true, 아닐 때 false를 나타내는 신호
			while(rs.next()) {
				
				if(flag) {
					// REVIEW_BOARD_ENTIRE_LIST
					board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
					board.setCategoryName(rs.getString("REVIEW_CATEGORY_NM"));
					board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
					board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
					board.setMemberGrade(rs.getString("MEMBER_GRADE"));
					board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
					board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
					board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
					board.setMovieTitleEn(rs.getString("MOVIE_TITLE_EN"));
					board.setReviewContent(rs.getString("REVIEW_BOARD_CONTENT"));
					board.setMemberNo(rs.getInt("MEMBER_NO"));
					board.setModifyDt(rs.getTimestamp("REVIEW_BOARD_MODIFY_DT"));
					flag = false;
				}
				
				MovieGenre gn = new MovieGenre();
				gn.setMovieGenreCode(rs.getInt("MOVIE_GENRE_CD"));
				gn.setMovieGenreNM(rs.getString("MOVIE_GENRE_NM"));
				
				// 조회된 파일 관련 정보를 저장할 객체 선언(경로, 이름, 레벨)
				ComplainAttachment at = new ComplainAttachment();
				at.setFilePath(rs.getString("REVIEW_FILE_PATH"));
				at.setFileName(rs.getString("REVIEW_FILE_NM"));
				at.setFileLevel(rs.getInt("REVIEW_FILE_LV"));
				
		
				// 값 세팅이 완료된 Attachment 객체를 
				// board의 atList에 추가 
				board.getAtList().add(at);
				board.getGnList().add(gn);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}
	
	
	/** 게시글 상세조회 DAO - 신고
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public ReviewBoard selectReportBoard(Connection conn, int boardNo) throws Exception{
		ReviewBoard board = null;
		
		String sql = prop.getProperty("selectReportBoard");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			board = new ReviewBoard();
			
			// board에 필드 중 atList에 값을 저장할 수 있도록 List 객체 하나를 생성하여 세팅 
			board.setAtList(new ArrayList<ComplainAttachment>());
			board.setGnList(new ArrayList<MovieGenre>());
			
			
			boolean flag = true; // 아래 반복문 첫 반복을 하고 있s을 때 true, 아닐 때 false를 나타내는 신호
			while(rs.next()) {
				
				if(flag) {
					// REVIEW_BOARD_ENTIRE_LIST
					board.setReviewNo(rs.getInt("REVIEW_BOARD_NO"));
					board.setReportNo(rs.getInt("REPORT_NO"));
					board.setCategoryName(rs.getString("REVIEW_CATEGORY_NM"));
					board.setReviewTitle(rs.getString("REVIEW_BOARD_TITLE"));
					board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
					board.setMemberGrade(rs.getString("MEMBER_GRADE"));
					board.setReadCount(rs.getInt("REVIEW_BOARD_READ_COUNT"));
					board.setCreateDt(rs.getTimestamp("REVIEW_BOARD_CREATE_DT"));
					board.setReviewStatus(rs.getString("REVIEW_BOARD_CONDITION"));
					board.setMovieTitleEn(rs.getString("MOVIE_TITLE_EN"));
					board.setReviewContent(rs.getString("REVIEW_BOARD_CONTENT"));
					board.setMemberNo(rs.getInt("MEMBER_NO"));
					board.setModifyDt(rs.getTimestamp("REVIEW_BOARD_MODIFY_DT"));
					flag = false;
				}
				
				MovieGenre gn = new MovieGenre();
				gn.setMovieGenreCode(rs.getInt("MOVIE_GENRE_CD"));
				gn.setMovieGenreNM(rs.getString("MOVIE_GENRE_NM"));
				
				// 조회된 파일 관련 정보를 저장할 객체 선언(경로, 이름, 레벨)
				ComplainAttachment at = new ComplainAttachment();
				at.setFilePath(rs.getString("REVIEW_FILE_PATH"));
				at.setFileName(rs.getString("REVIEW_FILE_NM"));
				at.setFileLevel(rs.getInt("REVIEW_FILE_LV"));
				
		
				// 값 세팅이 완료된 Attachment 객체를 
				// board의 atList에 추가 
				board.getAtList().add(at);
				board.getGnList().add(gn);
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
	public int increaseReviewCount(Connection conn, int boardNo) throws Exception {
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


	/** 리뷰게시판 영화장르 드롭박스 카테고리 리스트 조회 DAO
	 * @return
	 * @throws Exception
	 */
	public List<MovieGenre> getCategoryList(Connection conn) throws Exception {
		List<MovieGenre> categoryList = new ArrayList<MovieGenre>();
		
		String sql = prop.getProperty("getCategoryList");
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {

				
				MovieGenre gn = new MovieGenre();
				gn.setMovieGenreCode(rs.getInt("MOVIE_GENRE_CD"));
				gn.setMovieGenreNM(rs.getString("MOVIE_GENRE_NM"));
				
				categoryList.add(gn);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return categoryList;
	}


	/** 게시글 작성 ajax 영화 응답 DAO
	 * @param conn
	 * @param keyword
	 * @return movieList
	 * @throws Exception
	 */
	public List<Movie> getMovieList(Connection conn, String keyword) throws Exception {
		List<Movie> movieList = new ArrayList<Movie>();
		
		String sql = prop.getProperty("getMovieList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieTitleKo(rs.getString("MOVIE_TITLE_EN"));
				movie.setMovieNo(rs.getInt("MOVIE_NO"));

				movieList.add(movie);

			}

		} finally {
			close(rs);
			close(pstmt);
		}
		return movieList;

	}


	/** 게시글 작성 ajax 카테고리 응답 DAO
	 * @param conn
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	public List<MovieGenre> getGnList(Connection conn, int movieNo) throws Exception {
		List<MovieGenre> genreList = new ArrayList<MovieGenre>();
		
		String sql = prop.getProperty("getCategory");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MovieGenre genre = new MovieGenre();
				genre.setMovieGenreCode(rs.getInt("MOVIE_GENRE_CD"));
				genre.setMovieGenreNM(rs.getString("MOVIE_GENRE_NM"));
				genreList.add(genre);

			}

		} finally {
			close(rs);
			close(pstmt);
		}
		return genreList;

	}


	


	


	


	


	






	


	


	


	

	

}
