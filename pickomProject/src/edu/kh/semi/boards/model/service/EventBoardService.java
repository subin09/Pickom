package edu.kh.semi.boards.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.semi.boards.model.dao.EventBoardDAO;
import edu.kh.semi.boards.model.vo.EventBoard;
import edu.kh.semi.boards.model.vo.Pagination;

public class EventBoardService {

	private EventBoardDAO dao = new EventBoardDAO();

	/**
	 * 페이징 처리 객체 생성용 Service
	 * 
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public Pagination getPagination(int cp) throws Exception {

		Connection conn = getConnection();

		// DB에서 전체 게시글 수 + 게시판 이름 얻어오기
		int listCount = dao.getListCount(conn, cp);

		close(conn);

		return new Pagination(cp, listCount);
	}

	/**
	 * 행사게시글 목록 조회 Service
	 * 
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<EventBoard> selectEventBoardList(Pagination pagination) throws Exception {
		Connection conn = getConnection();

		List<EventBoard> boardList = dao.selectEventBoardList(conn, pagination);

		close(conn);

		return boardList;
	}

	/**
	 * 게시글 상세 조회
	 * 
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public EventBoard selectEventBoard(int boardNo) throws Exception {

		Connection conn = getConnection();
		
		EventBoard board = dao.selectEventBoard(conn, boardNo);

		// 게시글이 정상 조회된 경우

		if (board.getEventBodTitle() != null) {

			int result = dao.increaseEventBoardReadCount(conn, boardNo);

			if(result>0) {
				commit(conn);
				
				// DB에서 조회수를 증가하기 전에 얻어왔던 값을 1 증가 시키기
				board.setEventBodReadCount(board.getEventBodReadCount()+1);
			}else {
				rollback(conn);
			}
			
		}

		close(conn);
		return board;
	}
	
	//---------------------------검색---------------------------
	
	private String createCondition(String searchKey, String searchValue) {
		String condition = null;
		
		switch(searchKey) {
		case "title" : condition = "AND EVENT_BOD_TITLE LIKE'%"+searchValue+"%' ";
		break;
		case "content" : condition = "AND EVENT_BOD_CONTENT LIKE'%"+searchValue+"%' ";
		break;
		case "titcont" : condition = "AND (EVENT_BOD_TITLE LIKE'%"+searchValue+"%' " +
									"OR EVENT_BOD_CONTENT LIKE'%"+searchValue+"%' " ;
		break;
		}
		
		
		return condition;
	}
	
	
	/** 검색
	 * 페이징 처리 객체 생성용 Service
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public Pagination getPagination(int cp, String searchKey, String searchValue) throws Exception {

		Connection conn = getConnection();
		
		String condition = createCondition(searchKey, searchValue);

		// DB에서 전체 게시글 수 + 게시판 이름 얻어오기
		int listCount = dao.getListCount(conn, cp, condition);

		close(conn);

		return new Pagination(cp, listCount);
	}
	
	
	
	/**
	 * 행사게시글 목록 조회 Service
	 * 
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<EventBoard> selectEventBoardList(Pagination pagination, String searchKey, String searchValue) throws Exception {
		Connection conn = getConnection();
		
		String condition = createCondition(searchKey, searchValue);

		List<EventBoard> boardList = dao.selectEventBoardList(conn, pagination, condition);

		close(conn);

		return boardList;
	}
	
	
	

}
