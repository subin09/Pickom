package edu.kh.semi.boards.model.vo;

public class EventAttachment {

	private int eventFileNo;
	private String eventFilePath;
	private String eventFileNm;
	private int eventFileLv;
	private int eventBodNo;
	
	public EventAttachment(){}

	
	
	public EventAttachment(int eventFileNo, String eventFilePath, String eventFileNm, int eventFileLv, int eventBodNo) {
		super();
		this.eventFileNo = eventFileNo;
		this.eventFilePath = eventFilePath;
		this.eventFileNm = eventFileNm;
		this.eventFileLv = eventFileLv;
		this.eventBodNo = eventBodNo;
	}



	public int getEventFileNo() {
		return eventFileNo;
	}

	public void setEventFileNo(int eventFileNo) {
		this.eventFileNo = eventFileNo;
	}

	public String getEventFilePath() {
		return eventFilePath;
	}

	public void setEventFilePath(String eventFilePath) {
		this.eventFilePath = eventFilePath;
	}

	public String getEventFileNm() {
		return eventFileNm;
	}

	public void setEventFileNm(String eventFileNm) {
		this.eventFileNm = eventFileNm;
	}

	public int getEventFileLv() {
		return eventFileLv;
	}

	public void setEventFileLv(int eventFileLv) {
		this.eventFileLv = eventFileLv;
	}

	public int getEventBodNo() {
		return eventBodNo;
	}

	public void setEventBodNo(int eventBodNo) {
		this.eventBodNo = eventBodNo;
	}

	@Override
	public String toString() {
		return "EventAttachment [eventFileNo=" + eventFileNo + ", eventFilePath=" + eventFilePath + ", eventFileNm="
				+ eventFileNm + ", eventFileLv=" + eventFileLv + ", eventBodNo=" + eventBodNo + "]";
	}
	
	
}
