package edu.kh.semi.boards.model.service;

import static edu.kh.semi.common.JDBCTemplate.close;
import static edu.kh.semi.common.JDBCTemplate.commit;
import static edu.kh.semi.common.JDBCTemplate.getConnection;
import static edu.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.kh.semi.boards.model.dao.ComplainBoardDAO;
import edu.kh.semi.boards.model.dao.ReviewBoardDAO;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.boards.model.vo.Report;
import edu.kh.semi.boards.model.vo.ReviewBoard;
import edu.kh.semi.boards.model.vo.ReviewCategory;
import edu.kh.semi.boards.model.vo.ReviewComment;
import edu.kh.semi.movie.model.vo.Movie;
import edu.kh.semi.movie.model.vo.MovieGenre;

public class ReviewBoardService {
	
	private ReviewBoardDAO dao = new ReviewBoardDAO();


	/** 페이징 처리 객체 생성용 Service
	 * @param cp
	 * @param boardType
	 * @return
	 * @throws Exception
	 */
	public Pagination getPagination(int cp, int boardType) throws Exception {
		Connection conn = getConnection();
		int listCount = 0;
		// DB에서 전체 게시글 수 + 게시판 이름을 얻어옴
		
		switch(boardType) {
		case 1 :
		case 2 : 
		case 3 : 
		case 4 : 
		case 5 : 
		case 6 : 
		case 7 : 
		case 8 : 
		case 9 : 
		case 10 : 
		case 11 : 
		case 12 : 
		case 13 : 
		case 14 : 
		case 15 : 
		case 16 : 
		case 17 : 
		case 18 : 
		case 19 : 
		case 20 : 
		case 21 : 
		case 22 : 
		case 23 : 
		case 24 : 
		case 25 : 
		case 29 : 
			listCount = dao.getCategoryCount(conn, cp, boardType); break;
		case 200 : listCount = dao.getReportCount(conn, cp, boardType); break;
		case 300 : listCount = dao.getReportCommentCount(conn, cp, boardType); break;
		default : listCount = dao.getListCount(conn, cp, boardType); break;
		}
		close(conn);
		
		return new Pagination(cp, listCount, boardType);
	}
	
	 /** 검색 페이징 처리 객체 생성용 Service
	 * @param cp
	 * @param boardType
	 * @param searchType
	 * @param searchValue
	 * @return
	 * @throws Exception
	 */
	public Pagination getSearchPagination(int cp, int boardType, int searchType, String searchValue) throws Exception {
		Connection conn = getConnection();
		int listCount = 0;
		// DB에서 전체 게시글 수 + 게시판 이름을 얻어옴

	
		switch(searchType) {
		case 1 : listCount = dao.getMovieSearchCount(conn, cp, searchType, searchValue); break;
		case 2 : listCount = dao.getTitleSearchCount(conn, cp, searchType, searchValue); break;
		case 3 : listCount = dao.getContentSearchCount(conn, cp, searchType, searchValue); break;
		}

		
		close(conn);
		
		return new Pagination(cp, listCount, boardType);
	}


	/** 게시글 목록 조회
	 * @param pagination
	 * @return boardList
	 * @throws Exception
	 */
	public List<ReviewBoard> selectBoardList(Pagination pagination, int boardType) throws Exception{
		Connection conn = getConnection();
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();

		switch(boardType) {
		case 1 :
		case 2 : 
		case 3 : 
		case 4 : 
		case 5 : 
		case 6 : 
		case 7 : 
		case 8 : 
		case 9 : 
		case 10 : 
		case 11 : 
		case 12 : 
		case 13 : 
		case 14 : 
		case 15 : 
		case 16 : 
		case 17 : 
		case 18 : 
		case 19 : 
		case 20 : 
		case 21 : 
		case 22 : 
		case 23 : 
		case 24 : 
		case 25 : 
		case 29 :  boardList= dao.getCategoryBoardList(conn, pagination, boardType); break;
		case 200 : boardList = dao.getReportBoardList(conn,  pagination, boardType); break;
			default : boardList = dao.selectBoardList(conn, pagination); break;
		}
		close(conn);
		return boardList;
	}
	
	public List<Report> selectReportCommentList(Pagination pagination, int boardType) throws Exception{
		
		Connection conn = getConnection();
		List<Report> commentList = new ArrayList<Report>();
		commentList = dao.getReportCommentBoardList(conn,  pagination, boardType); 
		close(conn);
		return commentList;
	}

	
	
	/** 검색 게시글 목록 조회
	 * @param pagination
	 * @return boardList
	 * @throws Exception
	 */
	public List<ReviewBoard> searchBoardList(Pagination pagination, int searchType, String searchValue) throws Exception{
		Connection conn = getConnection();
		List<ReviewBoard> boardList = new ArrayList<ReviewBoard>();

		switch(searchType) {
		case 1: boardList= dao.getSearchMovieBoardList(conn, pagination, searchType, searchValue); break;
		case 2 : boardList= dao.getSearchTitleList(conn, pagination, searchType, searchValue); break;
		case 3 : boardList= dao.getSearchContentList(conn, pagination, searchType, searchValue); break;
		}
		
		
		
		close(conn);
		return boardList;
	}

		
	/** 공지 목록 조회
	 * @return boardList
	 * @throws Exception 
	 */
	public List<ReviewBoard> selectNoticeList() throws Exception {
		Connection conn = getConnection();
		
		List<ReviewBoard> boardList = dao.selectNoticeList(conn);
		
		close(conn);
		return boardList;
	}
	
	
	/** 게시글 상세 조회
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public ReviewBoard selectBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		ReviewBoard board = dao.selectBoard(conn, boardNo);
		List<MovieGenre> gnList = new ArrayList<MovieGenre>();

		// 게시글이 정상 조회된 경우 
		if(board.getReviewTitle() != null) {
			gnList = dao.getGnList(conn, board.getMovieNo());
			board.setGnList(gnList);

			int result = dao.increaseReviewCount(conn, boardNo);
		
			if(result>0) {
				board.setReadCount(board.getReadCount()+1);
				board.setReviewNo(boardNo);
				commit(conn);
			} else {
				rollback(conn);
			}
			
		}
		
		close(conn);
		return board;
	}
	
	/** 게시글 상세 조회-신고
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public ReviewBoard selectReportBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		ReviewBoard board = dao.selectReportBoard(conn, boardNo);
		List<MovieGenre> gnList = new ArrayList<MovieGenre>();
		// 게시글이 정상 조회된 경우 
			if(board.getReviewTitle() != null) {
				gnList = dao.getGnList(conn, board.getMovieNo());
				board.setGnList(gnList);
				int result = dao.increaseReviewCount(conn, boardNo);
				
				if(result>0) {
					board.setReadCount(board.getReadCount()+1);
					board.setReviewNo(boardNo);
				}
				
			}
	
		close(conn);
		return board;
	}

	/** 리뷰게시판 영화 장르 드롭박스 카테고리 리스트 조회 Service
	 * @return
	 * @throws Exception
	 */
	public List<MovieGenre> getCategoryList() throws Exception{
		
		List<MovieGenre> categoryList = new ArrayList<MovieGenre>();
		Connection conn = getConnection();
		
		categoryList = dao.getCategoryList(conn);
		
		close(conn);
		
		return categoryList;
	}

	/** 게시글 작성 ajax 영화 응답 Service
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<Movie> getMovieList(String keyword) throws Exception{
		List<Movie> movieList = new ArrayList<Movie>();
		Connection conn = getConnection();
		movieList = dao.getMovieList(conn, keyword);

		close(conn);
		return movieList;
	}

	/** 게시글 작성 ajax 카테고리 응답 Service
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	public List<MovieGenre> getGnList(int movieNo) throws Exception{
		List<MovieGenre> gnList = new ArrayList<MovieGenre>();
		Connection conn = getConnection();
		gnList = dao.getGnList(conn, movieNo);
		close(conn);
		return gnList;
	}

	
	
	

	

	

	


}
