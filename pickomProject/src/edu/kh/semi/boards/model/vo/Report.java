package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;

public class Report {
	private int reportNo;
	private String reportContent;
	private String reportTitle;
	private Timestamp reportDate;
	private int memberNo;
	private int categoryCd;
	private int boardNo;
	private int commentNo;
	private String memberNickNm;
	private String reportCdNm;

	
	
	public String getReportCdNm() {
		return reportCdNm;
	}
	public void setReportCdNm(String reportCdNm) {
		this.reportCdNm = reportCdNm;
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
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public Timestamp getReportDate() {
		return reportDate;
	}
	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getCategoryCd() {
		return categoryCd;
	}
	public void setCategoryCd(int categoryCd) {
		this.categoryCd = categoryCd;
	}
	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportContent=" + reportContent + ", reportTitle=" + reportTitle
				+ ", reportDate=" + reportDate + ", memberNo=" + memberNo + ", categoryCd=" + categoryCd + ", boardNo="
				+ boardNo + ", commentNo=" + commentNo + ", memberNickNm=" + memberNickNm + ", reportCdNm=" + reportCdNm
				+ "]";
	}
	
	
}
