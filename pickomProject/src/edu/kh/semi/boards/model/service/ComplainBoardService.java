package edu.kh.semi.boards.model.service;

import static edu.kh.semi.common.JDBCTemplate.close;
import static edu.kh.semi.common.JDBCTemplate.commit;
import static edu.kh.semi.common.JDBCTemplate.getConnection;
import static edu.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.kh.semi.boards.model.dao.ComplainBoardDAO;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.Pagination;

public class ComplainBoardService {
	
	private ComplainBoardDAO dao = new ComplainBoardDAO();

	/** 페이징 처리 객체 생성용 Service
	 * @param cp
	 * @param boardType
	 * @return map
	 * @throws Exception 
	 * @throws Exception
	 */

	public Pagination getPagination(int cp, int boardType) throws Exception {
		Connection conn = getConnection();
		int listCount = 0;
		// DB에서 전체 게시글 수 + 게시판 이름을 얻어옴
		
		switch(boardType) {
		case 1 :
		case 2 : listCount = dao.getCategoryCount(conn, cp, boardType); break;
		default : listCount = dao.getListCount(conn, cp, boardType); break;
		}
		close(conn);
		
		return new Pagination(cp, listCount, boardType);
	}
	
	/** 검색 페이징 처리 객체 생성용 Service
	 * @param cp
	 * @param boardType
	 * @return map
	 * @throws Exception 
	 * @throws Exception
	 */
	public Pagination getSearchPagination(int cp, int boardType, int searchType, String searchValue) throws Exception {
		Connection conn = getConnection();
		int listCount = 0;
		// DB에서 전체 게시글 수 + 게시판 이름을 얻어옴
		
		
		switch(searchType) {
		case 1 : listCount = dao.getTitleSearchCount(conn, cp, searchType, searchValue); break;
		case 2 : listCount = dao.getContentSearchCount(conn, cp, searchType, searchValue); break;
		}
		

		
		close(conn);
		
		return new Pagination(cp, listCount, boardType);
	}


	/** 게시글 목록 조회
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<ComplainBoard> selectBoardList(Pagination pagination, int boardType) throws Exception{
		Connection conn = getConnection();
		List<ComplainBoard> boardList = new ArrayList<ComplainBoard>();

		switch(boardType) {
		case 1 :
		case 2 : boardList= dao.getCategoryBoardList(conn, pagination, boardType); break;
			default : boardList = dao.selectBoardList(conn, pagination); break;
		}
		close(conn);
		return boardList;
	}
	
	
	/** 검색 게시글 목록 조회
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<ComplainBoard> searchBoardList(Pagination pagination, int searchType, String searchValue) throws Exception{
		Connection conn = getConnection();
		List<ComplainBoard> boardList = new ArrayList<ComplainBoard>();

		
		switch(searchType) {
		case 1 : boardList= dao.getSearchTitleList(conn, pagination, searchType, searchValue);  break;
		case 2 : boardList= dao.getSearchContentList(conn, pagination, searchType, searchValue); break;
		}
		
		close(conn);
		return boardList;
	}

		
	/** 공지 목록 조회
	 * @return
	 * @throws Exception 
	 */
	public List<ComplainBoard> selectNoticeList() throws Exception {
		Connection conn = getConnection();
		
		List<ComplainBoard> boardList = dao.selectNoticeList(conn);
		close(conn);
		return boardList;
	}
	
	
	/** 게시글 상세 조회
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public ComplainBoard selectBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		ComplainBoard board = dao.selectBoard(conn, boardNo);
		
		// 게시글이 정상 조회된 경우 
		if(board.getComplainTitle() != null) {
			int result = dao.increaseComplainCount(conn, boardNo);
			
			if(result>0) {
				commit(conn);
				board.setComplainCount(board.getComplainCount()+1);
				board.setComplainNo(boardNo);
			}
			
			else rollback(conn);
		}
		
		close(conn);
		return board;
	}

	

	


}
