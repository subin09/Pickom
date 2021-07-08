package edu.kh.semi.movie.model.vo;

public class movieSort {
	
	private int movieNo;
	private int movieGenreCode;
	
	public movieSort() {
		// TODO Auto-generated constructor stub
	}

	public movieSort(int movieNo, int movieGenreCode) {
		super();
		this.movieNo = movieNo;
		this.movieGenreCode = movieGenreCode;
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

	@Override
	public String toString() {
		return "movieSort [movieNo=" + movieNo + ", movieGenreCode=" + movieGenreCode + "]";
	}
	
	
	

}
