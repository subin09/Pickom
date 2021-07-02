package edu.kh.semi.crawler.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

public class CrawlerDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;
	
	public CrawlerDAO() {
		String filePath 
			= CrawlerDAO.class.getResource("/edu/kh/semi/sql/cralwer/crawlerMovie-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**영화 테이블 삽입 DAO
	 * @param conn
	 * @param movieNo
	 * @param arrMovieTitleKo
	 * @param arrMovieTitleEn
	 * @param arrMovieDirector
	 * @param arrMovieSummary
	 * @param arrMovieCountry
	 * @param arrMovieOpenDt
	 * @param arrRuntime
	 * @return result
	 * @throws Exception
	 */
	public int insertMovie(Connection conn, int movieNo, ArrayList<String> arrMovieTitleKo, ArrayList<String> arrMovieTitleEn,
			ArrayList<String> arrMovieDirector, ArrayList<String> arrMovieSummary, ArrayList<String> arrMovieCountry,
			ArrayList<Date> arrMovieOpenDt, ArrayList<String> arrRuntime) throws Exception {
		
		int result = 0;
		
		String sql = prop.getProperty("insertMovie");
		System.out.println(arrRuntime);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, movieNo);
			pstmt.setString(2, arrMovieTitleKo.get(0));
			pstmt.setString(3, arrMovieTitleEn.get(0));
			pstmt.setString(4, arrMovieSummary.get(0));
			pstmt.setString(5, arrMovieCountry.get(0));
			pstmt.setInt(6, Integer.parseInt(arrRuntime.get(0)));
			pstmt.setString(7, arrMovieDirector.get(0));
			pstmt.setDate(8, arrMovieOpenDt.get(0));
			
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
}
