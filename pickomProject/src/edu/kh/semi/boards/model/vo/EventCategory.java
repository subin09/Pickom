package edu.kh.semi.boards.model.vo;

public class EventCategory {

	private int eventCategoryCd;
	private String eventCategoryNm;


	public EventCategory() {}


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


	@Override
	public String toString() {
		return "EventCategory [eventCategoryCd=" + eventCategoryCd + ", eventCategoryNm=" + eventCategoryNm + "]";
	}
	
	
}
