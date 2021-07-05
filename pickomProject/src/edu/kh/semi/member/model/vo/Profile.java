package edu.kh.semi.member.model.vo;

public class Profile {
	private int memberNo;
	private String filePath;
	private String fileName;
	
	public Profile() {}
	
	
	
	public Profile(int memberNo, String filePath, String fileName) {
		this.memberNo = memberNo;
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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

	@Override
	public String toString() {
		return "Profile [memberNo=" + memberNo + ", filePath=" + filePath + ", fileName=" + fileName + "]";
	}
	
	
}
