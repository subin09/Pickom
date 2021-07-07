package edu.kh.semi.movie.model.vo;

public class Actor {
	
	private int actorCd;
	private int movieNo;
	private String actorNmKo;
	private String actorNmEn;
	private int actorNo;
	
	
	public Actor() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Actor(int actorCd, int movieNo, String actorNmKo, String actorNmEn, int actorNo) {
		super();
		this.actorCd = actorCd;
		this.movieNo = movieNo;
		this.actorNmKo = actorNmKo;
		this.actorNmEn = actorNmEn;
		this.actorNo = actorNo;
	}
	public int getActorCd() {
		return actorCd;
	}
	public void setActorCd(int actorCd) {
		this.actorCd = actorCd;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
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
	public int getActorNo() {
		return actorNo;
	}
	public void setActorNo(int actorNo) {
		this.actorNo = actorNo;
	}
	
	
	@Override
	public String toString() {
		return "Actor [actorCd=" + actorCd + ", movieNo=" + movieNo + ", actorNmKo=" + actorNmKo + ", actorNmEn="
				+ actorNmEn + ", actorNo=" + actorNo + "]";
	}
	
	
	

}
