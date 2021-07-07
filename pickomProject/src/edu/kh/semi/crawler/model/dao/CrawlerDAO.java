package edu.kh.semi.crawler.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			//e.printStackTrace();
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

	/**포스터 삽입 DAO
	 * @param conn
	 * @param poster
	 * @param fileType
	 * @param fileLv
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	public int insertPoster(Connection conn, String poster, String fileType, int fileLv, int movieNo) throws Exception {
		int result = 0;

		String sql = prop.getProperty("insertFileLink");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, poster);
			pstmt.setString(2, fileType);
			pstmt.setInt(3, fileLv);
			pstmt.setInt(4, movieNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}


	/**스틸컷 삽입 DAO
	 * @param conn
	 * @param stillCut
	 * @param fileType
	 * @param fileLv
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	public int insertStillCut(Connection conn, String stillCut, String fileType, int fileLv, int movieNo) throws Exception{
		int result = 0;

		String sql = prop.getProperty("insertFileLink");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, stillCut);
			pstmt.setString(2, fileType);
			pstmt.setInt(3, fileLv);
			pstmt.setInt(4, movieNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}


	/**동영상 삽입 DAO
	 * @param conn
	 * @param media
	 * @param fileType
	 * @param fileLv
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	public int insertMedia(Connection conn, String media, String fileType, int fileLv, int movieNo) throws Exception{
		int result = 0;

		String sql = prop.getProperty("insertFileLink");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, media);
			pstmt.setString(2, fileType);
			pstmt.setInt(3, fileLv);
			pstmt.setInt(4, movieNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 영화배우 테이블 삽입 DAO
	 * @param conn
	 * @param actorCd
	 * @param movieNo
	 * @param actorNmKo
	 * @param actorNmEn
	 * @return
	 * @throws Exception
	 */
	public int insertActor(Connection conn, String actorCd, int movieNo, String actorNmKo, String actorNmEn) throws Exception {
		int result = 0;

		String sql = prop.getProperty("insertActor");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(actorCd));
			pstmt.setInt(2, movieNo);
			pstmt.setString(3, actorNmKo);
			pstmt.setString(4, actorNmEn);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**영화별 장르 삽입 DAO
	 * @param conn
	 * @param movieNo
	 * @param genreCd1
	 * @return
	 * @throws Exception
	 */
	public int insertGenre(Connection conn, int movieNo, String genreCd1) throws Exception {
		int result = 0;

		String sql = prop.getProperty("insertMovieSort");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, Integer.parseInt(genreCd1));

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	/**장르 테이블 삽입 DAO
	 * @param conn
	 * @param GenreCd2
	 * @param GenreNm
	 * @return result
	 * @throws Exception
	 */
	public int insertGenre(Connection conn, String GenreCd2, String GenreNm) throws Exception {
		int result = 0;

		String sql = prop.getProperty("insertGenre");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(GenreCd2));
			pstmt.setString(2, GenreNm);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 영화 테이블 등록 여부 DAO
	 * @param conn
	 * @param movieNo
	 * @return
	 * @throws Exception
	 */
	public int searchMovie(Connection conn, int movieNo) throws Exception{
		
		int search = 0;
		
		String sql = prop.getProperty("searchMovie");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, movieNo);

			search = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return search;
	}


	/** 장르 테이블 등록 여부 DAO
	 * @param conn
	 * @param checkGenreCd2
	 * @return
	 * @throws Exception
	 */
	public int seachGenre(Connection conn, String checkGenreCd2) throws Exception {
		int search = 0;
		
		String sql = prop.getProperty("seachGenre");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(checkGenreCd2));

			search = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		return search;
	}




	
}
