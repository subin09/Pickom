package edu.kh.semi.admin.controller.model.dao;

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

import edu.kh.semi.admin.controller.model.vo.Pagination;
import edu.kh.semi.member.model.vo.Member;




public class AdminBoardDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public AdminBoardDAO() {
		// member-query.xml 파일의 경로 얻어오기
		String filePath 
			= AdminBoardDAO.class.getResource("/edu/kh/semi/sql/board/selectBoard-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 관리자 메인 게시판 DAO
	 * @return 
	 * @throws Exception
	 */
	public List<String> getBoardsContent(Connection conn, String board, String boardContents) throws Exception{

		List<String> boardLists = new ArrayList<String>();
		
		// prop에서 알맞은 SQL 구문 꺼내오기
		String sql = prop.getProperty("getBoardContent");
		System.out.println(sql);
		System.out.println(board);

		try {
			
			// DB로 SQL을 전달하고 수행 후 결과를 반환 받을 객체를 Connection에 세팅

			stmt = conn.createStatement();
			
			
			// 위치홀더에 알맞은 값 채우기
//			pstmt.setString(1, boardContents);

			// SQL 구문 수행 후 조회 결과인 ResultSet을 rs 변수에 저장
			rs = stmt.executeQuery(sql);
			// 조회 결과가 있는지 확인 후 있으면 Member 객체를 생성하여 조회된 값을 저장
			// -> 로그인 결과는 없거나 1행만 있음 -> if문으로 검사

			while(rs.next()) {
				// rs.next() : 다음 행에 조회 결과가 있을 경우 다음 행으로 이동
				String content = rs.getString("EVENT_BOD_CONTENT");

				boardLists.add(content);

			}
			System.out.println(boardLists);
		}finally {
			
			// 사용한 JDBC 객체 생성 역순으로 반환
			close(rs);
			close(stmt);
		}
		
		// 조회 결과 반환 (조회 성공 시 Member, 실패 시 null 이 반환됨)
		return boardLists;
	}

	
	public int getListCount(Connection conn, int cp) throws SQLException {
		int listCount = 0;
		String sql = prop.getProperty("getMemberListCount");
		try {
			 
			stmt = conn.prepareStatement(sql);

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
	
	/** 검색 페이징 처리 - 아이디일 때 
	 * @param conn
	 * @param cp
	 * @param searchInput
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getIdListCount(Connection conn, int cp, String searchInput, String condition) throws Exception{
		int listCount = 0;
		String sql = prop.getProperty("getIdListCount");
		try {
			 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchInput);
			pstmt.setString(2, condition);
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
	
	/** 검색 페이징 처리 - 닉네임일 때 
	 * @param conn
	 * @param cp
	 * @param searchInput
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getNicknameListCount(Connection conn, int cp, String searchInput, String condition) throws Exception{
		int listCount = 0;
		String sql = prop.getProperty("getNicknameListCount");
		try {
			 System.out.println(condition);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchInput);
			pstmt.setString(2, condition);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);   
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(listCount);
		return listCount;
	}

	
	/** 회원 리스트 조회
	 * @param conn
	 * @param pagination
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Connection conn, Pagination pagination) throws Exception{
		List<Member> memberList = new ArrayList<Member>();
		
		String sql = prop.getProperty("selectMemberList");
		
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
				Member member = new Member();
				
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberNm(rs.getString("MEMBER_NM"));
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				member.setSignUpDt(rs.getDate("SIGNUP_DT"));
				member.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				member.setMemberGrade(rs.getNString("MEMBER_GRADE"));
				member.setMemberStatus(rs.getString("MEMBER_STATUS"));

				memberList.add(member);
			}
		}finally {
			close(rs);
			close(pstmt);
		}

		return memberList;
	}

	/** 검색 회원 리스트 조회 - 아이디 
	 * @param conn
	 * @param pagination
	 * @param searchType
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectIdMemberList(Connection conn, Pagination pagination, String searchInput, String condition) throws Exception{
		List<Member> memberList = new ArrayList<Member>();
		
		String sql = prop.getProperty("selectIdMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
									// (1-1)*10+1  /  (5-1)*10+1
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt.setString(1, searchInput);
			pstmt.setString(2, condition);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberNm(rs.getString("MEMBER_NM"));
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				member.setSignUpDt(rs.getDate("SIGNUP_DT"));
				member.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				member.setMemberGrade(rs.getNString("MEMBER_GRADE"));
				member.setMemberStatus(rs.getString("MEMBER_STATUS"));

				memberList.add(member);
			}
		}finally {
			close(rs);
			close(pstmt);
		}

		return memberList;
	}

	/** 검색 회원 리스트 조회 - 닉네임
	 * @param conn
	 * @param pagination
	 * @param searchInput2
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Member> getNicknameMemberList(Connection conn, Pagination pagination, String searchInput,
			String condition) throws Exception{
		List<Member> memberList = new ArrayList<Member>();
		
		String sql = prop.getProperty("selectNicknameMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			// 조회할 범위를 지정할 변수 선언
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() +1;
									// (1-1)*10+1  /  (5-1)*10+1
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt.setString(1, searchInput);
			pstmt.setString(2, condition);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberNm(rs.getString("MEMBER_NM"));
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				member.setSignUpDt(rs.getDate("SIGNUP_DT"));
				member.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				member.setMemberGrade(rs.getNString("MEMBER_GRADE"));
				member.setMemberStatus(rs.getString("MEMBER_STATUS"));

				memberList.add(member);
			}
		}finally {
			close(rs);
			close(pstmt);
		}

		return memberList;
	}

	

	
	

}
