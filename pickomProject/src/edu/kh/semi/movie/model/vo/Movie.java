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
	
	
	private List<String> movieLinkNo;
	private List<String> movieFileLink;
	
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}


	public Movie(int movieNo, String movieTitleEn, String movieTitleKo, String movieSummary, int movieCount,
			Timestamp createDt, Timestamp modifyDt, String movieStatus, String movieCountry, int movieRuntime,
			String movieDirector, Date movieOpenDt, List<String> movieLinkNo, List<String> movieFileLink) {
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
		this.movieLinkNo = movieLinkNo;
		this.movieFileLink = movieFileLink;
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


	@Override
	public String toString() {
		return "Movie [movieNo=" + movieNo + ", movieTitleEn=" + movieTitleEn + ", movieTitleKo=" + movieTitleKo
				+ ", movieSummary=" + movieSummary + ", movieCount=" + movieCount + ", createDt=" + createDt
				+ ", modifyDt=" + modifyDt + ", movieStatus=" + movieStatus + ", movieCountry=" + movieCountry
				+ ", movieRuntime=" + movieRuntime + ", movieDirector=" + movieDirector + ", movieOpenDt=" + movieOpenDt
				+ ", movieLinkNo=" + movieLinkNo + ", movieFileLink=" + movieFileLink + "]";
	}
	
	
}