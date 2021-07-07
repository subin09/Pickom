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

		if(result>0) commit(conn);
		else rollback(conn);

		close(conn);

		return result;
	}


	/**파일(예고편, 스틸컷, 포스터)Link Service
	 * @param movieNo
	 * @param arrPoster
	 * @param arrStillCut
	 * @param arrMedia
	 * @return
	 * @throws Exception
	 */
	public int insertFile(int movieNo, ArrayList<String> arrPoster, ArrayList<String> arrStillCut,
			ArrayList<String> arrMedia) throws Exception {

		int result = 0;
		Connection conn = getConnection();
		
		boolean flag = true;
		String poster = arrPoster.get(0);
		String fileType = "P";
		
		while(flag) {
			// 포스터
			if(fileType=="P") {
				int fileLv = 0;
				result = dao.insertPoster(conn, poster, fileType, fileLv, movieNo);
				if(result>0) {
					fileType = "S";	
				}
			}else if(fileType=="S") {
				// 스틸컷
				for(int i=0; i<arrStillCut.size(); i++) {
					String stillCut = arrStillCut.get(i);
					int fileLv = i;
					if(arrStillCut.get(i) != null) {
						result = dao.insertStillCut(conn, stillCut, fileType, fileLv, movieNo);
					}
				}
				fileType = "M";
			}else if(fileType=="M") {
				// 동영상
				for(int i=0; i<arrMedia.size(); i++) {
					String media = arrMedia.get(i);
					int fileLv = i;
					if(arrMedia.get(i) != null) {
						result = dao.insertMedia(conn, media, fileType, fileLv, movieNo);
					}
				}
				flag = false;
			}
		}

		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}

	/**영화배우 삽입 Service
	 * @param movieNo
	 * @param arrActorNmKo
	 * @param arrActorNmEn
	 * @param arrActorCd
	 * @return
	 * @throws Exception
	 */
	public int insertActor(int movieNo, ArrayList<String> arrActorNmKo, ArrayList<String> arrActorNmEn,
			ArrayList<String> arrActorCd) throws Exception {

		int result = 0;
		Connection conn = getConnection();

		for(int i=0; i<arrActorNmKo.size(); i++) {

			String actorNmKo = arrActorNmKo.get(i);
			String actorNmEn = arrActorNmEn.get(i);
			String actorCd = arrActorCd.get(i);

			if(arrActorNmKo.get(i) != null) {
				result = dao.insertActor(conn, actorCd, movieNo, actorNmKo, actorNmEn);
			}else break;
		}
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}



	/**영화별 장르 삽입 Service
	 * @param movieNo
	 * @param arrGenreCd1
	 * @throws Exception 
	 */
	public void insertActor(int movieNo, ArrayList<String> arrGenreCd1) throws Exception {
		int result = 0;

		Connection conn = getConnection();

		for(int i=0; i<arrGenreCd1.size(); i++) {

			String genreCd1 = arrGenreCd1.get(i);

			if(genreCd1!= null) {
				result = dao.insertGenre(conn, movieNo, genreCd1);
			}else break;
		}

		if(result>0) commit(conn);
		else rollback(conn);

		close(conn);

	}

	
	/**장르 테이블 삽입 Service
	 * @param arrGenreNm
	 * @param arrGenreCd2
	 * @return result
	 */
	public int insertGenre(ArrayList<String> arrGenreNm, ArrayList<String> arrGenreCd2) throws Exception {
		
		int result = 0;
		
		Connection conn = getConnection();

		for(int i=0; i<arrGenreNm.size(); i++) {

			String GenreNm = arrGenreNm.get(i);
			String GenreCd2 = arrGenreCd2.get(i);

			if(arrGenreNm.get(i) != null) {
				result = dao.insertGenre(conn, GenreCd2, GenreNm);
			}else if(result == 0) {
				rollback(conn);
				break;
			}
		}
		
		commit(conn);
		close(conn);
		
		return result;
	}


	/** 영화 등록여부 확인 service
	 * @param movieNo
	 * @return
	 */
	public int searchMovie(int movieNo) throws Exception{
		int search = 0;
		
		Connection conn = getConnection();		
		search = dao.searchMovie(conn, movieNo);
		
		close(conn);
		
		return search;
	}


	/** 장르 등록여부 확인 service
	 * @param checkGenreCd2
	 * @return
	 */
	public int seachGenre(String checkGenreCd2) throws Exception {
		int search = 0;
		
		Connection conn = getConnection();		
		search = dao.seachGenre(conn, checkGenreCd2);
		
		close(conn);
		
		return search;
	}



}
