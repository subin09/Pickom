package edu.kh.semi.movie.model.service;

import static edu.kh.semi.common.JDBCTemplate.close;
import static edu.kh.semi.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import edu.kh.semi.movie.model.dao.MovieDAO;
import edu.kh.semi.movie.model.vo.Actor;
import edu.kh.semi.movie.model.vo.Movie;
import edu.kh.semi.movie.model.vo.MovieLink;

public class MovieService {
	
	private MovieDAO dao = new MovieDAO();

	


	public List<MovieLink> selectMain() throws Exception{
		
		Connection conn = getConnection();
		
		List<MovieLink> mainList = new ArrayList<MovieLink>();
		
		mainList = dao.selectMain(conn);
		
		close(conn);
		return mainList;
	}




	public Movie selectDetail(int movieNo) throws Exception{
		
		Connection conn = getConnection();
		
		Movie mo =	dao.selectDetail(conn, movieNo);
		
		close(conn);
		
		return mo;
	}




	public List<Actor> selectActor(int movieNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Actor> ac = new ArrayList<Actor>();
		
		ac = dao.selectActor(conn, movieNo);
		
		close(conn);
		
		return ac;
	}





	


	



	

}
