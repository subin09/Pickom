package edu.kh.semi.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;			// 회원 번호
	private String memberId;		// 회원 아이디
	private String memberPw;		// 회원 비밀번호
	private String memberNm;		// 회원 이름
	private String memberPhone;		// 전화번포('-' 포함)
	private String memberEmail;		// 이메일
	private String memberAddress;	// 주소
	private Date signUpDt;	// 가입일
	private String memberStatus;	// 회원 상태(Y:정상, N:탈퇴)
	private String memberGrade;		// 회원 등급(A:관리자, G:일반)
	private String memberNickNm;		// 회원 닉네임

	public Member() {}
	

	


	public Member(int memberNo, String memberId, String memberPw, String memberNm, String memberPhone,
			String memberEmail, String memberAddress, Date signUpDt, String memberStatus, String memberGrade,
			String memberNickNm) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberNm = memberNm;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.signUpDt = signUpDt;
		this.memberStatus = memberStatus;
		this.memberGrade = memberGrade;
		this.memberNickNm = memberNickNm;
	}

	

	public Member(int memberNo, String memberId, String memberNm, String memberPhone, String memberEmail,
			String memberAddress, Date signUpDt, String memberGrade, String memberNickNm) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNm = memberNm;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.signUpDt = signUpDt;
		this.memberGrade = memberGrade;
		this.memberNickNm = memberNickNm;
	}

	

	public Member(String memberId, String memberPw, String memberNm, String memberPhone, String memberEmail,
				String memberAddress, String memberNickNm) {
			super();
			this.memberId = memberId;
			this.memberPw = memberPw;
			this.memberNm = memberNm;
			this.memberPhone = memberPhone;
			this.memberEmail = memberEmail;
			this.memberAddress = memberAddress;
			this.memberNickNm = memberNickNm;
		}


	

	public Member(String memberId, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		
	}


	public Member(String memberId) {
		super();
		this.memberId = memberId;
	}


	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberNm() {
		return memberNm;
	}

	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public Date getSignUpDt() {
		return signUpDt;
	}

	public void setSignUpDt(Date signUpDt) {
		this.signUpDt = signUpDt;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
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

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberNm="
				+ memberNm + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberAddress="
				+ memberAddress + ", signUpDt=" + signUpDt + ", memberStatus=" + memberStatus + ", memberGrade="
				+ memberGrade + ", memberNickNm=" + memberNickNm + "]";
	}


}