package edu.kh.semi.movie.model.vo;

public class MovieGenre {
	
	private int movieGenreCode;
	private String movieGenreNM;
	private String movieTitleEn;
	private String movieTitleKo;
	
	
	public MovieGenre() {
		// TODO Auto-generated constructor stub
	}
	
	public MovieGenre(int movieGenreCode, String movieGenreNM) {
		super();
		this.movieGenreCode = movieGenreCode;
		this.movieGenreNM = movieGenreNM;
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
		return "MovieGenre [movieGenreCode=" + movieGenreCode + ", movieGenreNM=" + movieGenreNM + ", movieTitleEn="
				+ movieTitleEn + ", movieTitleKo=" + movieTitleKo + "]";
	}

	
	
	

	
}
