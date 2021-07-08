package edu.kh.semi.admin.controller.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.kh.semi.admin.controller.model.dao.AdminBoardDAO;
import edu.kh.semi.admin.controller.model.vo.Pagination;
import edu.kh.semi.member.model.dao.MemberDAO;
import edu.kh.semi.member.model.vo.Member;




// Service : 비즈니스 로직 처리(데이터 가공, 트랜잭션 처리)
public class AdminBoardService {
	
	private AdminBoardDAO dao = new AdminBoardDAO();

	
	/** 관리자 메인 게시판 Service
	 * @return
	 * @throws Exception
	 */
	public List<String> getBoardsContent(String board, String boardContents) throws Exception{
		Connection conn = getConnection();
		List<String>  boardLists = dao.getBoardsContent(conn, board, boardContents);
		close(conn);
		return boardLists;
	}

	/** 페이징 처리 객체 생성용 Service
	 * @param cp
	 * @param boardType
	 * @return map
	 * @throws Exception 
	 * @throws Exception
	 */

	public Pagination getPagination(int cp, int boardType) throws Exception {
		Connection conn = getConnection();
		
		// DB에서 전체 게시글 수 + 게시판 이름을 얻어옴
		int listCount = dao.getListCount(conn, cp);
		
		close(conn);
		
		return new Pagination(cp, listCount, boardType);
	}

	/** 페이징 처리 검색
	 * @param cp
	 * @param boardType
	 * @param searchType
	 * @param searchInput
	 * @return
	 * @throws Exception
	 */
	public Pagination getSearchPagination(int cp, int boardType, int searchType, String searchInput, String condition) throws Exception{
		Connection conn = getConnection();
		int listCount = 0;
		switch(searchType) {
		case 1 : listCount = dao.getIdListCount(conn, cp ,searchInput, condition); break;
		case 2 : listCount = dao.getNicknameListCount(conn, cp, searchInput, condition); break;
		}
	
		
		close(conn);
		
		return new Pagination(cp, listCount, boardType);
	}
	
	/** 회원 리스트
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Pagination pagination) throws Exception{
		Connection conn = getConnection();
		
		List<Member> boardList = dao.selectMemberList(conn, pagination);
		close(conn);
		return boardList;
	}

	/** 회원 검색 리스트 
	 * @param pagination
	 * @param searchType
	 * @param searchInput
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Pagination pagination, int searchType, String searchInput, String condition) throws Exception {
		Connection conn = getConnection();
		
		List<Member> boardList = new ArrayList<Member>();
		
		switch(searchType) {
		case 1 : boardList = dao.selectIdMemberList(conn, pagination, searchInput, condition); break;
		case 2 : boardList = dao.getNicknameMemberList(conn, pagination, searchInput, condition); break;
		}
		close(conn);
		return boardList;
	}



	}