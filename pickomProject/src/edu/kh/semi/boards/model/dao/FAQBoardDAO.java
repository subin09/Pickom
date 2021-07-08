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
import edu.kh.semi.boards.model.vo.FAQAttachment;
import edu.kh.semi.boards.model.vo.FAQBoard;
import edu.kh.semi.boards.model.vo.Pagination;

public class FAQBoardDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public FAQBoardDAO() {
		// member-query.xml 파일의 경로 얻어오기
		String filePath 
			= FAQBoardDAO.class.getResource("/edu/kh/semi/sql/board/FAQBoard-query.xml").getPath();                    
		
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
	public int getListCount(Connection conn, int cp) throws Exception{
		int listCount = 0;
		String sql = prop.getProperty("getMemberListCount");
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


	/** 고객센터 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @return 
	 * @throws Exception
	 */
	public List<FAQBoard> selectBoardList(Connection conn, Pagination pagination) throws Exception {
		List<FAQBoard> boardList = new ArrayList<FAQBoard>();
		
		String sql = prop.getProperty("selectFAQBoardList");
		
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
				FAQBoard board = new FAQBoard();
				
				board.setFaqNo(rs.getInt("FAQ_NO"));
				board.setFaqTitle(rs.getString("FAQ_TITLE"));
				board.setFaqContent(rs.getString("FAQ_CONTENT"));
				board.setFaqDate(rs.getTimestamp("FAQ_DT"));
				board.setFaqModifyDt(rs.getTimestamp("FAQ_MODIFY_DT"));
				board.setFaqStatus(rs.getString("FAQ_STATUS"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
				board.setMemberGrade(rs.getString("MEMBER_GRADE"));
				
				
				// set 완료된 board를 boardList에 추가
				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	/** 게시글 상세조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public FAQBoard selectBoard(Connection conn, int boardNo) throws Exception{
		FAQBoard board = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			board = new FAQBoard();
			
			// board에 필드 중 atList에 값을 저장할 수 있도록 List 객체 하나를 생성하여 세팅 
			board.setAtList(new ArrayList<FAQAttachment>());
		
			boolean flag = true; // 아래 반복문 첫 반복을 하고 있s을 때 true, 아닐 때 false를 나타내는 신호
			while(rs.next()) {
				
				if(flag) {
					board.setFaqNo(rs.getInt("FAQ_NO"));
					board.setFaqTitle(rs.getString("FAQ_TITLE"));
					board.setFaqContent(rs.getString("FAQ_CONTENT"));
					board.setFaqDate(rs.getTimestamp("FAQ_DT"));
					board.setFaqModifyDt(rs.getTimestamp("FAQ_MODIFY_DT"));
					board.setFaqStatus(rs.getString("FAQ_STATUS"));
					board.setMemberNo(rs.getInt("MEMBER_NO"));
					board.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
					
					flag = false;
				}
				
				// 조회된 파일 관련 정보를 저장할 객체 선언(경로, 이름, 레벨)
				FAQAttachment at = new FAQAttachment();
				at.setFilePath(rs.getString("FAQ_FILE_PATH"));
				at.setFileName(rs.getString("FAQ_FILE_NM"));
				at.setFileLevel(rs.getInt("FAQ_FILE_LV"));
				
		
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


	
}
