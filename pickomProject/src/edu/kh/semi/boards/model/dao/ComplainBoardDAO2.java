package edu.kh.semi.boards.model.dao;

import static edu.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import edu.kh.semi.boards.model.vo.ComplainAttachment;
import edu.kh.semi.boards.model.vo.ComplainBoard;
import edu.kh.semi.boards.model.vo.ComplainCategory;
import edu.kh.semi.boards.model.vo.Pagination;
import edu.kh.semi.member.model.dao.MemberDAO;

public class ComplainBoardDAO2 {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public ComplainBoardDAO2() {
		// selectBoard-query.xml 파일의 경로 얻어오기
		String filePath 
		= ComplainBoardDAO2.class.getResource("/edu/kh/semi/sql/board/complainBoard2-query.xml").getPath();                                 
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 카테고리 목록 조회 DAO
	 * @param conn
	 * @return category
	 * @throws Exception
	 */
	public List<ComplainCategory> selectCategoryList(Connection conn) throws Exception{
		
		List<ComplainCategory> categoryList = new ArrayList<ComplainCategory>();

		String sql = prop.getProperty("selectCategoryList");
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				ComplainCategory category = new ComplainCategory();
				
				category.setCategoryCode(rs.getInt("CS_CATEGORY_CD"));
				category.setCategoryName(rs.getString("CS_CATEGORY_NM"));

				categoryList.add(category);
			}
		
		} finally {
			close(rs);
			close(stmt);
		}
		
		return categoryList;
	}


	/** 다음 게시글 번호 조회 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{
		
		int boardNo = 0;
		
		String sql = prop.getProperty("nextBoardNo");
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(stmt);
		}
		
		return boardNo;
	}


	/** 게시글 삽입 DAO
	 * @param conn 
	 * @param board
	 * @param boardType
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, ComplainBoard board) throws Exception {
		int result = 0; 
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getComplainNo());
			pstmt.setString(2, board.getComplainTitle());
			pstmt.setString(3, board.getComplainContent());
			pstmt.setInt(4, board.getMemberNo());
			pstmt.setInt(5, board.getCategoryCd());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		return result;
	}


	/** 첨부 파일 삽입 DAO
	 * @param conn
	 * @param at
	 * @param boardNo
	 * @return result 
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, ComplainAttachment at) throws Exception {
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFilePath());
			pstmt.setString(2, at.getFileName());
			pstmt.setInt(3, at.getFileLevel());
			pstmt.setInt(4, at.getBoardNo());

			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 게시글 수정 DAO
	 * @param conn
	 * @param board
	 * @return result 
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, ComplainBoard board) throws Exception{
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setString(1, board.getComplainTitle());
			pstmt.setString(2, board.getComplainContent());
			pstmt.setInt(3, board.getCategoryCd());
			pstmt.setInt(4, board.getComplainNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
			
		}
				
		return result;
	}

	/** 첨부파일 수정 DAO
	 * @param conn
	 * @param at
	 * @return result 
	 * @throws Exception
	 */
	public int updateAttachment(Connection conn, ComplainAttachment at) throws Exception {
		int result = 0;
		String sql = prop.getProperty("updateAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFileName());
			pstmt.setInt(2, at.getBoardNo());
			pstmt.setInt(3, at.getFileLevel());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}


	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		String sql = prop.getProperty("deleteBoard");
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
