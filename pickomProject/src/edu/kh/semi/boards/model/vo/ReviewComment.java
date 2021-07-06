package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;

public class ReviewComment {
	private int commentNo;
	private String commentContent;
	private Timestamp commentDt;
	private Timestamp commentModifyDt;
	private String commentStatus;
	private int memberNo;
	private String memberNm;
	private String memberGrade;
	private int reviewNo;
	private int reportNo;
	private String memberNickNm;
	private int boardNo;
	
	
	
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberNickNm() {
		return memberNickNm;
	}
	public void setMemberNickNm(String memberNickNm) {
		this.memberNickNm = memberNickNm;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Timestamp getCommentDt() {
		return commentDt;
	}
	public void setCommentDt(Timestamp commentDt) {
		this.commentDt = commentDt;
	}
	public Timestamp getCommentModifyDt() {
		return commentModifyDt;
	}
	public void setCommentModifyDt(Timestamp commentModifyDt) {
		this.commentModifyDt = commentModifyDt;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	@Override
	public String toString() {
		return "ReviewComment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentDt="
				+ commentDt + ", commentModifyDt=" + commentModifyDt + ", commentStatus=" + commentStatus
				+ ", memberNo=" + memberNo + ", memberNm=" + memberNm + ", memberGrade=" + memberGrade + ", reviewNo="
				+ reviewNo + ", reportNo=" + reportNo + ", memberNickNm=" + memberNickNm + ", boardNo=" + boardNo + "]";
	}
	
	
	
	
	
	
	
	
}
