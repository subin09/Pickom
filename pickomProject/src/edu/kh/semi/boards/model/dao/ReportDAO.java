package edu.kh.semi.boards.model.dao;

import java.sql.Connection;

import edu.kh.semi.boards.model.vo.Report;
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

public class ReportDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop = null;
	
	
	// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
	public ReportDAO() {
		// member-query.xml 파일의 경로 얻어오기
		String filePath 
			= ComplainBoardDAO.class.getResource("/edu/kh/semi/sql/board/report-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			
			// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 신고 접수 DAO
	 * @param conn
	 * @param report
	 * @return
	 * @throws Exception
	 */
	public int insertReport(Connection conn, Report report) throws Exception{
		int result = 0;
		
		String sql = prop.getProperty("report");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, report.getReportContent());
			pstmt.setString(2, report.getReportTitle());
			pstmt.setInt(3, report.getMemberNo());
			pstmt.setInt(4, report.getCategoryCd());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
			
		}
		return result;
	}
	
	
	
}
