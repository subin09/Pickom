package edu.kh.semi.crawler.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import edu.kh.semi.crawler.model.dao.CrawlerDAO;

public class CrawlerService {

	private CrawlerDAO dao = new CrawlerDAO(); 

	
	
	/** 영화 테이블 삽입 Service
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
	public int insertMovie(int movieNo, ArrayList<String> arrMovieTitleKo, ArrayList<String> arrMovieTitleEn,
			ArrayList<String> arrMovieDirector, ArrayList<String> arrMovieSummary,
			ArrayList<String> arrMovieCountry, ArrayList<Date> arrMovieOpenDt, ArrayList<String> arrRuntime) throws Exception {

		int result = 0;

		Connection conn = getConnection();

		result = dao.insertMovie(conn, movieNo, arrMovieTitleKo, arrMovieTitleEn, arrMovieDirector, arrMovieSummary,
				arrMovieCountry, arrMovieOpenDt, arrRuntime );

		if(result>1) commit(conn);
		else rollback(conn);

		close(conn);

		return result;
	}

}
