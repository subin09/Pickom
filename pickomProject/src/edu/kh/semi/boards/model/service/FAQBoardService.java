package edu.kh.semi.boards.model.service;

import static edu.kh.semi.common.JDBCTemplate.close;
import static edu.kh.semi.common.JDBCTemplate.commit;
import static edu.kh.semi.common.JDBCTemplate.getConnection;
import static edu.kh.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.boards.model.dao.ComplainBoardDAO;
import edu.kh.semi.boards.model.dao.FAQBoardDAO;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.FAQBoard;
import edu.kh.semi.boards.model.vo.Pagination;

public class FAQBoardService {
	
	private FAQBoardDAO dao = new FAQBoardDAO();

	/** 페이징 처리 객체 생성용 Service
	 * @param cp
	 * @param boardType
	 * @return map
	 * @throws Exception 
	 * @throws Exception
	 */

	public Pagination getPagination(int cp) throws Exception {
		Connection conn = getConnection();
		
		// DB에서 전체 게시글 수 + 게시판 이름을 얻어옴
		int listCount = dao.getListCount(conn, cp);
		
		close(conn);
		
		return new Pagination(cp, listCount);
	}

	/** 게시글 목록 조회
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<FAQBoard> selectBoardList(Pagination pagination) throws Exception{
		Connection conn = getConnection();
		
		List<FAQBoard> boardList = dao.selectBoardList(conn, pagination);
		close(conn);
		return boardList;
	}

	/** 게시글 상세 조회
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public FAQBoard selectBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		FAQBoard board = dao.selectBoard(conn, boardNo);

		close(conn);
		return board;
	}


}
