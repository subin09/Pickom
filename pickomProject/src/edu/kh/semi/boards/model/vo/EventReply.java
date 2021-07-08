package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;

public class EventReply {
	private int eventReplyNo; 		 		// 댓글 번호
	private String eventReplyContent; 		// 댓글 내용
	private Timestamp eventReplyCreateDt;		// 댓글 작성일
	private Timestamp eventReplyModifyDt;
	private int eventBodNo;			// 댓글이 작성된 게시글 번호
	private int memberNo;				// 댓글 작성 회원 번호
	private String memberNm;			// 댓글 작성 회원 이름
	
	
	public EventReply() {
		// TODO Auto-generated constructor stub
	}

	public Timestamp getEventReplyModifyDt() {
		return eventReplyModifyDt;
	}

	public void setEventReplyModifyDt(Timestamp eventReplyModifyDt) {
		this.eventReplyModifyDt = eventReplyModifyDt;
	}

	public int getEventReplyNo() {
		return eventReplyNo;
	}

	public void setEventReplyNo(int eventReplyNo) {
		this.eventReplyNo = eventReplyNo;
	}

	public String getEventReplyContent() {
		return eventReplyContent;
	}

	public void setEventReplyContent(String eventReplyContent) {
		this.eventReplyContent = eventReplyContent;
	}

	public Timestamp getEventReplyCreateDt() {
		return eventReplyCreateDt;
	}

	public void setEventReplyCreateDt(Timestamp eventReplyCreateDt) {
		this.eventReplyCreateDt = eventReplyCreateDt;
	}

	public int getEventBodNo() {
		return eventBodNo;
	}

	public void setEventBodNo(int eventBodNo) {
		this.eventBodNo = eventBodNo;
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

	@Override
	public String toString() {
		return "EventReply [eventReplyNo=" + eventReplyNo + ", eventReplyContent=" + eventReplyContent
				+ ", eventReplyCreateDt=" + eventReplyCreateDt + ", eventReplyModifyDt=" + eventReplyModifyDt
				+ ", eventBodNo=" + eventBodNo + ", memberNo=" + memberNo + ", memberNm=" + memberNm + "]";
	}


	
	
	
	
	
	
}
