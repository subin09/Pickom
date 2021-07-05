package edu.kh.semi.member.model.vo;

public class Profile {
	private int MemberNo;
	private String filePath;
	private String fileName;
	
	public Profile() {}

	public int getMemberNo() {
		return MemberNo;
	}

	public void setMemberNo(int memberNo) {
		MemberNo = memberNo;
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
		return "Profile [MemberNo=" + MemberNo + ", filePath=" + filePath + ", fileName=" + fileName + "]";
	}
	
	
}
