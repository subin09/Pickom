package edu.kh.semi.movie.model.vo;

public class MovieGenre {
	
	private int movieGenreCode;
	private String movieGenreNM;
	
	
	public MovieGenre() {
		// TODO Auto-generated constructor stub
	}
	
	public MovieGenre(int movieGenreCode, String movieGenreNM) {
		super();
		this.movieGenreCode = movieGenreCode;
		this.movieGenreNM = movieGenreNM;
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
		return "MovieGenre [movieGenreCode=" + movieGenreCode + ", movieGenreNM=" + movieGenreNM + "]";
	}
	
	
	

	
}
