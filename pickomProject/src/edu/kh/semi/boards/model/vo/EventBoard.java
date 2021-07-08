package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class EventBoard {
	
	private int eventBodNo;
	private String eventBodTitle;
	private String eventBodContent;
	private int eventBodReadCount;
	private Timestamp eventBodCreateDt;
	private Timestamp eventBodModifyDt;
	private String startDate;
	private String finalDate;
	private String eventBodStatus;
	
	private int memberNo;
	private String memberNm;
	
	private int eventCategoryCd;
	private String eventCategoryNm;

	private int eventCommentNo;
	
	
	private List<String> filePath;
	private List<String> fileName;
	
	private List<EventAttachment> atList;
	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public EventBoard() {}

	public int getEventBodNo() {
		return eventBodNo;
	}

	public void setEventBodNo(int eventBodNo) {
		this.eventBodNo = eventBodNo;
	}

	public String getEventBodTitle() {
		return eventBodTitle;
	}

	public void setEventBodTitle(String eventBodTitle) {
		this.eventBodTitle = eventBodTitle;
	}

	public String getEventBodContent() {
		return eventBodContent;
	}

	public void setEventBodContent(String eventBodContent) {
		this.eventBodContent = eventBodContent;
	}

	public int getEventBodReadCount() {
		return eventBodReadCount;
	}

	public void setEventBodReadCount(int eventBodReadCount) {
		this.eventBodReadCount = eventBodReadCount;
	}

	public Timestamp getEventBodCreateDt() {
		return eventBodCreateDt;
	}

	public void setEventBodCreateDt(Timestamp eventBodCreateDt) {
		this.eventBodCreateDt = eventBodCreateDt;
	}

	public Timestamp getEventBodModifyDt() {
		return eventBodModifyDt;
	}

	public void setEventBodModifyDt(Timestamp eventBodModifyDt) {
		this.eventBodModifyDt = eventBodModifyDt;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public String getEventBodStatus() {
		return eventBodStatus;
	}

	public void setEventBodStatus(String eventBodStatus) {
		this.eventBodStatus = eventBodStatus;
	}

	public String getMemberNm() {
		return memberNm;
	}

	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}

	public int getEventCategoryCd() {
		return eventCategoryCd;
	}

	public void setEventCategoryCd(int eventCategoryCd) {
		this.eventCategoryCd = eventCategoryCd;
	}

	public String getEventCategoryNm() {
		return eventCategoryNm;
	}

	public void setEventCategoryNm(String eventCategoryNm) {
		this.eventCategoryNm = eventCategoryNm;
	}

	public int getEventCommentNo() {
		return eventCommentNo;
	}

	public void setEventCommentNo(int eventCommentNo) {
		this.eventCommentNo = eventCommentNo;
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

	public List<EventAttachment> getAtList() {
		return atList;
	}

	public void setAtList(List<EventAttachment> atList) {
		this.atList = atList;
	}

	@Override
	public String toString() {
		return "EventBoard [eventBodNo=" + eventBodNo + ", eventBodTitle=" + eventBodTitle + ", eventBodContent="
				+ eventBodContent + ", eventBodReadCount=" + eventBodReadCount + ", eventBodCreateDt="
				+ eventBodCreateDt + ", eventBodModifyDt=" + eventBodModifyDt + ", startDate=" + startDate
				+ ", finalDate=" + finalDate + ", eventBodStatus=" + eventBodStatus + ", memberNo=" + memberNo
				+ ", memberNm=" + memberNm + ", eventCategoryCd=" + eventCategoryCd + ", eventCategoryNm="
				+ eventCategoryNm + ", eventCommentNo=" + eventCommentNo + ", filePath=" + filePath + ", fileName="
				+ fileName + ", atList=" + atList + "]";
	}


	

}
