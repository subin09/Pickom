package crawler.model.vo;

import java.util.Date;

public class CreawlerVo {
	
	// 영화
	private int movieNo;
	private String movieTitleKo;
	private String movieTitleEn;
	private String movieCountry;
	private int runtime;
	private String movieDirector;
	private Date movieOpenDt; 
	private String movieSummary;
	
	// 장르코드
	private String genreNm;
	private int genreCd;
	
	// 배우
	private String actorNmKo;
	private String actorNmEn;
	private int actorCd;
	
	// 파일링크
	private int movieLinkNo;
	private String movieFileLink;
	private String movieLinkType;
	private int movieLinkLv;
	
	// 매개변수
	public CreawlerVo() {
		super();
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovieTitleKo() {
		return movieTitleKo;
	}

	public void setMovieTitleKo(String movieTitleKo) {
		this.movieTitleKo = movieTitleKo;
	}

	public String getMovieTitleEn() {
		return movieTitleEn;
	}

	public void setMovieTitleEn(String movieTitleEn) {
		this.movieTitleEn = movieTitleEn;
	}

	public String getMovieCountry() {
		return movieCountry;
	}

	public void setMovieCountry(String movieCountry) {
		this.movieCountry = movieCountry;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getGenreNm() {
		return genreNm;
	}

	public void setGenreNm(String genreNm) {
		this.genreNm = genreNm;
	}

	public int getGenreCd() {
		return genreCd;
	}

	public void setGenreCd(int genreCd) {
		this.genreCd = genreCd;
	}

	public String getActorNmKo() {
		return actorNmKo;
	}

	public void setActorNmKo(String actorNmKo) {
		this.actorNmKo = actorNmKo;
	}

	public String getActorNmEn() {
		return actorNmEn;
	}

	public void setActorNmEn(String actorNmEn) {
		this.actorNmEn = actorNmEn;
	}

	public int getActorCd() {
		return actorCd;
	}

	public void setActorCd(int actorCd) {
		this.actorCd = actorCd;
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

	public int getMovieLinkLv() {
		return movieLinkLv;
	}

	public void setMovieLinkLv(int movieLinkLv) {
		this.movieLinkLv = movieLinkLv;
	}

	public Date getMovieOpenDt() {
		return movieOpenDt;
	}

	public void setMovieOpenDt(Date movieOpenDt) {
		this.movieOpenDt = movieOpenDt;
	}

	public String getMovieSummary() {
		return movieSummary;
	}

	public void setMovieSummary(String movieSummary) {
		this.movieSummary = movieSummary;
	}

	@Override
	public String toString() {
		return "CreawlerVo [movieNo=" + movieNo + ", movieTitleKo=" + movieTitleKo + ", movieTitleEn=" + movieTitleEn
				+ ", movieCountry=" + movieCountry + ", runtime=" + runtime + ", movieDirector=" + movieDirector
				+ ", movieOpenDt=" + movieOpenDt + ", movieSummary=" + movieSummary + ", genreNm=" + genreNm
				+ ", genreCd=" + genreCd + ", actorNmKo=" + actorNmKo + ", actorNmEn=" + actorNmEn + ", actorCd="
				+ actorCd + ", movieLinkNo=" + movieLinkNo + ", movieFileLink=" + movieFileLink + ", movieLinkType="
				+ movieLinkType + ", movieLinkLv=" + movieLinkLv + "]";
	}	
}
