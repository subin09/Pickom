package edu.kh.semi.boards.model.vo;

public class ReviewCategory {
	private int categoryCode;
	private String categoryName;
	private int boardNo;
	
	
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "ReviewCategory [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", boardNo="
				+ boardNo + "]";
	}
	
	
	
	
}
