package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;
import java.util.List;

import edu.kh.semi.boards.model.vo.ComplainAttachment;

public class FAQBoard {
	private int faqNo;
	private String faqTitle;
	private String faqContent;
	private Timestamp faqDate;
	private Timestamp faqModifyDt;
	private String faqStatus;
	private int memberNo;
	private String memberNm;
	private String memberGrade;
	private String memberNickNm;
	
	private List<String> filePath;
	private List<String> fileName;
	
	private List<FAQAttachment> atList;

	
	
	public String getMemberNickNm() {
		return memberNickNm;
	}

	public void setMemberNickNm(String memberNickNm) {
		this.memberNickNm = memberNickNm;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public Timestamp getFaqDate() {
		return faqDate;
	}

	public void setFaqDate(Timestamp faqDate) {
		this.faqDate = faqDate;
	}

	public Timestamp getFaqModifyDt() {
		return faqModifyDt;
	}

	public void setFaqModifyDt(Timestamp faqModifyDt) {
		this.faqModifyDt = faqModifyDt;
	}

	public String getFaqStatus() {
		return faqStatus;
	}

	public void setFaqStatus(String faqStatus) {
		this.faqStatus = faqStatus;
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

	public List<FAQAttachment> getAtList() {
		return atList;
	}

	public void setAtList(List<FAQAttachment> atList) {
		this.atList = atList;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	@Override
	public String toString() {
		return "FAQBoard [faqNo=" + faqNo + ", faqTitle=" + faqTitle + ", faqContent=" + faqContent + ", faqDate="
				+ faqDate + ", faqModifyDt=" + faqModifyDt + ", faqStatus=" + faqStatus + ", memberNo=" + memberNo
				+ ", memberNm=" + memberNm + ", memberGrade=" + memberGrade + ", memberNickNm=" + memberNickNm
				+ ", filePath=" + filePath + ", fileName=" + fileName + ", atList=" + atList + "]";
	}

	

	

	
}