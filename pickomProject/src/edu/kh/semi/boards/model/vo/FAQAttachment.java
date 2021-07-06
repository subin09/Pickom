package edu.kh.semi.boards.model.vo;

public class FAQAttachment {
	private int fileNo;
	private String filePath;
	private String fileName;
	private int fileLevel;
	private int boardNo;
	
	public FAQAttachment() {
		// TODO Auto-generated constructor stub
	}


	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", filePath=" + filePath + ", fileNm=" + fileName + ", fileLevel="
				+ fileLevel + ", boardNo=" + boardNo + "]";
	}
	
	
	
}
