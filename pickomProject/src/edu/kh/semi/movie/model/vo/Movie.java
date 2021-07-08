package edu.kh.semi.movie.model.vo;

import java.sql.Date;
import java.sql.Timestamp;
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
	
	// MOVIE_GENRE
	private List<Integer> movieGenreCode;
	private List<String> movieGenreNm;
	private List<MovieGenre> gnList;
	
	
	private List<String> movieLinkNo;
	private List<String> movieFileLink;
	
	// detail 
	private List<MovieLink> moList;
	private int actorCd;
	private String actorNmKo;
	private int actorNo;
	private String movieGenreNM;
	private int movieGenreCD;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}


	public Movie(int movieNo, String movieTitleEn, String movieTitleKo, String movieSummary, int movieCount,
			Timestamp createDt, Timestamp modifyDt, String movieStatus, String movieCountry, int movieRuntime,
			String movieDirector, Date movieOpenDt, List<Integer> movieGenreCode, List<String> movieGenreNm,
			List<MovieGenre> gnList, List<String> movieLinkNo, List<String> movieFileLink, List<MovieLink> moList,
			int actorCd, String actorNmKo, int actorNo, String movieGenreNM2, int movieGenreCD) {
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
		this.movieGenreCode = movieGenreCode;
		this.movieGenreNm = movieGenreNm;
		this.gnList = gnList;
		this.movieLinkNo = movieLinkNo;
		this.movieFileLink = movieFileLink;
		this.moList = moList;
		this.actorCd = actorCd;
		this.actorNmKo = actorNmKo;
		this.actorNo = actorNo;
		movieGenreNM = movieGenreNM2;
		this.movieGenreCD = movieGenreCD;
	}



	public List<Integer> getMovieGenreCode() {
		return movieGenreCode;
	}


	public void setMovieGenreCode(List<Integer> movieGenreCode) {
		this.movieGenreCode = movieGenreCode;
	}


	public List<String> getMovieGenreNm() {
		return movieGenreNm;
	}


	public void setMovieGenreNm(List<String> movieGenreNm) {
		this.movieGenreNm = movieGenreNm;
	}


	public List<MovieGenre> getGnList() {
		return gnList;
	}


	public void setGnList(List<MovieGenre> gnList) {
		this.gnList = gnList;
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


	public List<String> getMovieLinkNo() {
		return movieLinkNo;
	}


	public void setMovieLinkNo(List<String> movieLinkNo) {
		this.movieLinkNo = movieLinkNo;
	}


	public List<String> getMovieFileLink() {
		return movieFileLink;
	}


	public void setMovieFileLink(List<String> movieFileLink) {
		this.movieFileLink = movieFileLink;
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




	public String getMovieGenreNM() {
		return movieGenreNM;
	}




	public void setMovieGenreNM(String movieGenreNM) {
		this.movieGenreNM = movieGenreNM;
	}




	public int getMovieGenreCD() {
		return movieGenreCD;
	}


	public void setMovieGenreCD(int movieGenreCD) {
		this.movieGenreCD = movieGenreCD;
	}


	@Override
	public String toString() {
		return "Movie [movieNo=" + movieNo + ", movieTitleEn=" + movieTitleEn + ", movieTitleKo=" + movieTitleKo
				+ ", movieSummary=" + movieSummary + ", movieCount=" + movieCount + ", createDt=" + createDt
				+ ", modifyDt=" + modifyDt + ", movieStatus=" + movieStatus + ", movieCountry=" + movieCountry
				+ ", movieRuntime=" + movieRuntime + ", movieDirector=" + movieDirector + ", movieOpenDt=" + movieOpenDt
				+ ", movieGenreCode=" + movieGenreCode + ", movieGenreNm=" + movieGenreNm + ", gnList=" + gnList
				+ ", movieLinkNo=" + movieLinkNo + ", movieFileLink=" + movieFileLink + ", moList=" + moList
				+ ", actorCd=" + actorCd + ", actorNmKo=" + actorNmKo + ", actorNo=" + actorNo + ", movieGenreNM="
				+ movieGenreNM + ", movieGenreCD=" + movieGenreCD + "]";
	}


	



	
	
	
	
}
