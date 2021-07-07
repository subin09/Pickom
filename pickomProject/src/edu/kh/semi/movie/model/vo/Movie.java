package edu.kh.semi.movie.model.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Movie {
	
	private int movieNo;
	private String movieTitleEn;
	private String movieTitleKo;
	private String movieSummary;
	private int movieCount;
	private Timestamp createDt;
	private Timestamp modifyDt;
	private String movieStatus;
	private String movieCountry;
	private int movieRuntime;
	private String movieDirector;
	private Date movieOpenDt;
	
	private List<MovieLink> moList;
	private int actorCd;
	private String actorNmKo;
	private int actorNo;
	private int movieGenreCode;
	private String movieGenreNM;
	
	

	public Movie() {
		// TODO Auto-generated constructor stub
	}


	
	public Movie(int movieNo, String movieTitleEn, String movieTitleKo, String movieSummary, int movieCount,
			Timestamp createDt, Timestamp modifyDt, String movieStatus, String movieCountry, int movieRuntime,
			String movieDirector, Date movieOpenDt, List<MovieLink> moList, int actorCd, String actorNmKo, int actorNo,
			int movieGenreCode, String movieGenreNM) {
		super();
		this.movieNo = movieNo;
		this.movieTitleEn = movieTitleEn;
		this.movieTitleKo = movieTitleKo;
		this.movieSummary = movieSummary;
		this.movieCount = movieCount;
		this.createDt = createDt;
		this.modifyDt = modifyDt;
		this.movieStatus = movieStatus;
		this.movieCountry = movieCountry;
		this.movieRuntime = movieRuntime;
		this.movieDirector = movieDirector;
		this.movieOpenDt = movieOpenDt;
		this.moList = moList;
		this.actorCd = actorCd;
		this.actorNmKo = actorNmKo;
		this.actorNo = actorNo;
		this.movieGenreCode = movieGenreCode;
		this.movieGenreNM = movieGenreNM;
	}













	public int getMovieNo() {
		return movieNo;
	}


	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}


	public String getMovieTitleEn() {
		return movieTitleEn;
	}


	public void setMovieTitleEn(String movieTitleEn) {
		this.movieTitleEn = movieTitleEn;
	}


	public String getMovieTitleKo() {
		return movieTitleKo;
	}


	public void setMovieTitleKo(String movieTitleKo) {
		this.movieTitleKo = movieTitleKo;
	}


	public String getMovieSummary() {
		return movieSummary;
	}


	public void setMovieSummary(String movieSummary) {
		this.movieSummary = movieSummary;
	}


	public int getMovieCount() {
		return movieCount;
	}


	public void setMovieCount(int movieCount) {
		this.movieCount = movieCount;
	}


	public Timestamp getCreateDt() {
		return createDt;
	}


	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}


	public Timestamp getModifyDt() {
		return modifyDt;
	}


	public void setModifyDt(Timestamp modifyDt) {
		this.modifyDt = modifyDt;
	}


	public String getMovieStatus() {
		return movieStatus;
	}


	public void setMovieStatus(String movieStatus) {
		this.movieStatus = movieStatus;
	}


	public String getMovieCountry() {
		return movieCountry;
	}


	public void setMovieCountry(String movieCountry) {
		this.movieCountry = movieCountry;
	}


	public int getMovieRuntime() {
		return movieRuntime;
	}


	public void setMovieRuntime(int movieRuntime) {
		this.movieRuntime = movieRuntime;
	}


	public String getMovieDirector() {
		return movieDirector;
	}


	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}


	public Date getMovieOpenDt() {
		return movieOpenDt;
	}


	public void setMovieOpenDt(Date movieOpenDt) {
		this.movieOpenDt = movieOpenDt;
	}







	public List<MovieLink> getMoList() {
		return moList;
	}



	public void setMoList(List<MovieLink> moList) {
		this.moList = moList;
	}



	public int getActorCd() {
		return actorCd;
	}



	public void setActorCd(int actorCd) {
		this.actorCd = actorCd;
	}



	public String getActorNmKo() {
		return actorNmKo;
	}



	public void setActorNmKo(String actorNmKo) {
		this.actorNmKo = actorNmKo;
	}



	public int getActorNo() {
		return actorNo;
	}



	public void setActorNo(int actorNo) {
		this.actorNo = actorNo;
	}



	public int getMovieGenreCode() {
		return movieGenreCode;
	}



	public void setMovieGenreCode(int movieGenreCode) {
		this.movieGenreCode = movieGenreCode;
	}



	public String getMovieGenreNM() {
		return movieGenreNM;
	}



	public void setMovieGenreNM(String movieGenreNM) {
		this.movieGenreNM = movieGenreNM;
	}



	@Override
	public String toString() {
		return "Movie [movieNo=" + movieNo + ", movieTitleEn=" + movieTitleEn + ", movieTitleKo=" + movieTitleKo
				+ ", movieSummary=" + movieSummary + ", movieCount=" + movieCount + ", createDt=" + createDt
				+ ", modifyDt=" + modifyDt + ", movieStatus=" + movieStatus + ", movieCountry=" + movieCountry
				+ ", movieRuntime=" + movieRuntime + ", movieDirector=" + movieDirector + ", movieOpenDt=" + movieOpenDt
				+ ", moList=" + moList + ", actorCd=" + actorCd + ", actorNmKo=" + actorNmKo + ", actorNo=" + actorNo
				+ ", movieGenreCode=" + movieGenreCode + ", movieGenreNM=" + movieGenreNM + "]";
	}








	
	















	






	
	
}
