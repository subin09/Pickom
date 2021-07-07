package edu.kh.semi.movie.model.dao;

import static edu.kh.semi.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import edu.kh.semi.member.model.dao.MemberDAO;
import edu.kh.semi.member.model.vo.Member;
import edu.kh.semi.movie.model.vo.Actor;
import edu.kh.semi.movie.model.vo.Movie;
import edu.kh.semi.movie.model.vo.MovieLink;

public class MovieDAO {

	
	// 자주 사용하는 JDBC 객체 참조 변수 선언
		private Statement stmt = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		// 외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
		private Properties prop = null;
		
		
		// 기본 생성자를 이용한 DAO 객체 생성 시 외부 XML파일을 읽어와 prop에 저장
		public MovieDAO() {
			// member-query.xml 파일의 경로 얻어오기
			String filePath 
				= MemberDAO.class.getResource("/edu/kh/semi/sql/movie/movie-query.xml").getPath();                    
			
			try {
				prop = new Properties();
				
				// filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
				prop.loadFromXML(new FileInputStream(filePath));
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}


		



		public List<MovieLink> selectMain(Connection conn) throws Exception{
			
			List<MovieLink> mainList = new ArrayList<MovieLink>();
			
			String sql = prop.getProperty("selectMain");
			
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					MovieLink mlink = new MovieLink();
					String movieFileLink = rs.getString("MOVIE_FILE_LINK");
					String movieTitleEn = rs.getString("MOVIE_TITLE_EN");
					int movieGenreCode = rs.getInt("MOVIE_GENRE_CD");
					int movieNo = rs.getInt("MOVIE_NO");
							
					mlink.setMovieFileLink(movieFileLink);
					mlink.setMovieTitleEn(movieTitleEn);
					mlink.setMovieGenreCode(movieGenreCode);
					mlink.setMovieNo(movieNo);
					
					mainList.add(mlink);
				}
				
			}finally {
				close(rs);
				close(stmt);
			}
			return mainList;
		}






		public Movie selectDetail(Connection conn, int movieNo) throws Exception{
			
			Movie mo = null;
			
			String sql = prop.getProperty("selectDetail");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, movieNo);
				
				rs= pstmt.executeQuery();
						
				mo = new Movie();
				
				mo.setMoList(new ArrayList<MovieLink>());
				
				boolean flag = true;
				
				while(rs.next()) {
					if(flag) {
					mo.setMovieTitleEn(rs.getString("MOVIE_TITLE_EN"));
					mo.setMovieTitleKo(rs.getString("MOVIE_TITLE_KO"));
					mo.setMovieSummary(rs.getString("MOVIE_SUMMARY"));
					mo.setMovieCountry(rs.getString("MOVIE_COUNTRY"));
					mo.setMovieRuntime(rs.getInt("MOVIE_RUNTIME"));
					mo.setMovieDirector(rs.getString("MOVIE_DIRECTOR"));
					mo.setMovieOpenDt(rs.getDate("MOVIE_OPEN_DT"));
					mo.setMovieGenreCode(rs.getInt("MOVIE_GENRE_CD"));
					mo.setMovieGenreNM(rs.getString("MOVIE_GENRE_NM"));
					mo.setActorCd(rs.getInt("ACTOR_CD"));
					mo.setActorNmKo(rs.getString("ACTOR_NM_KO"));
					mo.setActorNo(rs.getInt("ACTOR_NO"));
					
					flag = false;
				}
					MovieLink mli = new MovieLink();
					mli.setMovieLinkNo(rs.getInt("MOVIE_LINK_NO"));
					mli.setMovieFileLink(rs.getString("MOVIE_FILE_LINK"));
					mli.setMovieLinkType(rs.getString("MOVIE_LINK_TYPE"));
					mli.setMovieLinkLV(rs.getInt("MOVIE_LINK_LV"));
					
					mo.getMoList().add(mli);
					
					
				}
				
				
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return mo;
		}






		public List<Actor> selectActor(Connection conn, int movieNo) throws Exception{
			List<Actor> ac = new ArrayList<Actor>();
			
			String sql = prop.getProperty("selectActor");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, movieNo);
				
				rs= pstmt.executeQuery();
				while(rs.next()) {
					Actor a = new Actor();
					int actorCd = rs.getInt("ACTOR_CD");
					String actorNmKo = rs.getString("ACTOR_NM_KO");
					String actorNmEn = rs.getString("ACTOR_NM_EN");
					int actorNo = rs.getInt("ACTOR_NO");
					
					a.setActorCd(actorCd);
					a.setActorNmKo(actorNmKo);
					a.setActorNmEn(actorNmEn);
					a.setActorNo(actorNo);
					
					ac.add(a);
				}
				
			}finally {
				close(rs);
				close(pstmt);
			}
			return ac;
		}



		
}
