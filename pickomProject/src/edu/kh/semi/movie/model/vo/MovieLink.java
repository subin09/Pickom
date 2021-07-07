package edu.kh.semi.movie.model.vo;

import java.sql.Date;

public class MovieLink {
	
	private int movieLinkNo;
	private String movieFileLink;
	private String movieLinkType;
	private int movieLinkLV;
	private int movieNo;
	
	
	
	private int movieGenreCode;
	private String movieGenreNM;
	
	private String movieTitleEn;
	private String movieTitleKo;
	private String movieSummary;
	private String movieCountry;
	private int movieRuntime;
	private String movieDirector;
	private Date movieOpenDt;
	
	private int actorCd;
	private String actorNmKo;
	private int actorNo;
	
	
	
	
	public MovieLink() {
		// TODO Auto-generated constructor stub
	}

	



	



	public MovieLink(int movieLinkNo, String movieFileLink, String movieLinkType, int movieLinkLV, int movieNo,
			int movieGenreCode, String movieGenreNM, String movieTitleEn, String movieTitleKo, String movieSummary,
			String movieCountry, int movieRuntime, String movieDirector, Date movieOpenDt, int actorCd,
			String actorNmKo, String actorNmEn, int actorNo) {
		super();
		this.movieLinkNo = movieLinkNo;
		this.movieFileLink = movieFileLink;
		this.movieLinkType = movieLinkType;
		this.movieLinkLV = movieLinkLV;
		this.movieNo = movieNo;
		this.movieGenreCode = movieGenreCode;
		this.movieGenreNM = movieGenreNM;
		this.movieTitleEn = movieTitleEn;
		this.movieTitleKo = movieTitleKo;
		this.movieSummary = movieSummary;
		this.movieCountry = movieCountry;
		this.movieRuntime = movieRuntime;
		this.movieDirector = movieDirector;
		this.movieOpenDt = movieOpenDt;
		this.actorCd = actorCd;
		this.actorNmKo = actorNmKo;
		this.actorNo = actorNo;
	}









	public int getMovieLinkNo() {
		return movieLinkNo;
	}

	public void setMovieLinkNo(int movieLinkNo) {
		this.movieLinkNo = movieLinkNo;
	}

	public String getMovieFileLink() {
		return movieFileLink;
	}

	public void setMovieFileLink(String movieFileLink) {
		this.movieFileLink = movieFileLink;
	}

	public String getMovieLinkType() {
		return movieLinkType;
	}

	public void setMovieLinkType(String movieLinkType) {
		this.movieLinkType = movieLinkType;
	}

	public int getMovieLinkLV() {
		return movieLinkLV;
	}

	public void setMovieLinkLV(int movieLinkLV) {
		this.movieLinkLV = movieLinkLV;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	
	

	public int getMovieGenreCode() {
		return movieGenreCode;
	}



	public void setMovieGenreCode(int movieGenreCode) {
		this.movieGenreCode = movieGenreCode;
	}

	


	public String getMovieTitleEn() {
		return movieTitleEn;
	}




	public void setMovieTitleEn(String movieTitleEn) {
		this.movieTitleEn = movieTitleEn;
	}


	


	public String getMovieGenreNM() {
		return movieGenreNM;
	}





	public void setMovieGenreNM(String movieGenreNM) {
		this.movieGenreNM = movieGenreNM;
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









	@Override
	public String toString() {
		return "MovieLink [movieLinkNo=" + movieLinkNo + ", movieFileLink=" + movieFileLink + ", movieLinkType="
				+ movieLinkType + ", movieLinkLV=" + movieLinkLV + ", movieNo=" + movieNo + ", movieGenreCode="
				+ movieGenreCode + ", movieGenreNM=" + movieGenreNM + ", movieTitleEn=" + movieTitleEn
				+ ", movieTitleKo=" + movieTitleKo + ", movieSummary=" + movieSummary + ", movieCountry=" + movieCountry
				+ ", movieRuntime=" + movieRuntime + ", movieDirector=" + movieDirector + ", movieOpenDt=" + movieOpenDt
				+ ", actorCd=" + actorCd + ", actorNmKo=" + actorNmKo + ", actorNo=" + actorNo + "]";
	}




	
	












	
	
	

}
