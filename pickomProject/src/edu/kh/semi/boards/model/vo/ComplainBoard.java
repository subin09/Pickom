package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;
import java.util.List;

import edu.kh.semi.boards.model.vo.ComplainAttachment;

public class ComplainBoard {
	private int complainNo;
	private String complainTitle;
	private String complainContent;
	private int complainCount;
	private Timestamp complainDate;
	private Timestamp complainModifyDt;
	private String complainStatus;
	private int memberNo;
	private int categoryCd;
	private int complainCommentNo;
	private String categoryName;
	private String memberNm;
	private String memberGrade;
	private String memberNickNm;
	
	private List<String> filePath;
	private List<String> fileName;
	
	private List<ComplainAttachment> atList;

	public int getComplainNo() {
		return complainNo;
	}

	public void setComplainNo(int complainNo) {
		this.complainNo = complainNo;
	}

	public String getComplainTitle() {
		return complainTitle;
	}

	public void setComplainTitle(String complainTitle) {
		this.complainTitle = complainTitle;
	}

	public String getComplainContent() {
		return complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}

	public int getComplainCount() {
		return complainCount;
	}

	public void setComplainCount(int complainCount) {
		this.complainCount = complainCount;
	}

	public Timestamp getComplainDate() {
		return complainDate;
	}

	public void setComplainDate(Timestamp complainDate) {
		this.complainDate = complainDate;
	}

	public Timestamp getComplainModifyDt() {
		return complainModifyDt;
	}

	public void setComplainModifyDt(Timestamp complainModifyDt) {
		this.complainModifyDt = complainModifyDt;
	}

	public String getComplainStatus() {
		return complainStatus;
	}

	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
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

	public int getComplainCommentNo() {
		return complainCommentNo;
	}

	public void setComplainCommentNo(int complainCommentNo) {
		this.complainCommentNo = complainCommentNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getMemberNickNm() {
		return memberNickNm;
	}

	public void setMemberNickNm(String memberNickNm) {
		this.memberNickNm = memberNickNm;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<ComplainAttachment> getAtList() {
		return atList;
	}

	public void setAtList(List<ComplainAttachment> atList) {
		this.atList = atList;
	}

	@Override
	public String toString() {
		return "ComplainBoard [complainNo=" + complainNo + ", complainTitle=" + complainTitle + ", complainContent="
				+ complainContent + ", complainCount=" + complainCount + ", complainDate=" + complainDate
				+ ", complainModifyDt=" + complainModifyDt + ", complainStatus=" + complainStatus + ", memberNo="
				+ memberNo + ", categoryCd=" + categoryCd + ", complainCommentNo=" + complainCommentNo
				+ ", categoryName=" + categoryName + ", memberNm=" + memberNm + ", memberGrade=" + memberGrade
				+ ", memberNickNm=" + memberNickNm + ", filePath=" + filePath + ", fileName=" + fileName + ", atList="
				+ atList + "]";
	}
	

	
	
	
	
}
