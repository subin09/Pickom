package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;

public class ComplainComment {
	private int commentNo;
	private String commentContent;
	private Timestamp commentDt;
	private Timestamp commentModify;
	private String commentStatus;
	private int memberNo;
	private String memberNm;
	private String memberGrade;
	private String memberNickNm;
	
	
	
	public String getMemberNickNm() {
		return memberNickNm;
	}
	public void setMemberNickNm(String memberNickNm) {
		this.memberNickNm = memberNickNm;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	private int complainNo;
	
	
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
	public Timestamp getCommentModify() {
		return commentModify;
	}
	public void setCommentModify(Timestamp commentModify) {
		this.commentModify = commentModify;
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
	public int getComplainNo() {
		return complainNo;
	}
	public void setComplainNo(int complainNo) {
		this.complainNo = complainNo;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	@Override
	public String toString() {
		return "ComplainComment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentDt="
				+ commentDt + ", commentModify=" + commentModify + ", commentStatus=" + commentStatus + ", memberNo="
				+ memberNo + ", memberNm=" + memberNm + ", memberGrade=" + memberGrade + ", memberNickNm="
				+ memberNickNm + ", complainNo=" + complainNo + "]";
	}

	
	
	
	
	
	
}
