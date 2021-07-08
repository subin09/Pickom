package edu.kh.semi.member.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.semi.member.model.vo.Member;
import edu.kh.semi.member.model.vo.Profile;

public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	
	
	public MemberDAO() {
		String filePath 
			= MemberDAO.class.getResource("/edu/kh/semi/sql/member/member-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** 로그인 DAO
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception {

		Member loginMember = null;
		
		String sql = prop.getProperty("login");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			System.out.println(memberId);
			System.out.println(memberPw);
			
			if(rs.next()) {
				loginMember 
				= new Member(rs.getInt("MEMBER_NO"), 
							rs.getString("MEMBER_ID"), 
							rs.getString("MEMBER_NM"), 
							rs.getString("MEMBER_PHONE"), 
							rs.getString("MEMBER_EMAIL"), 
							rs.getString("MEMBER_ADDR"), 
							rs.getDate("SIGNUP_DT"), 
							rs.getString("MEMBER_GRADE"),
							rs.getString("MEMBER_NICKNAME")
							);
			}
			
			
		}finally {
			
			// 사용한 JDBC 객체 생성 역순으로 반환
			close(rs);
			close(pstmt);
		}
		
		// 조회 결과 반환 (조회 성공 시 Member, 실패 시 null 이 반환됨)
		return loginMember;
	}
	
	
	
	
	/** 회원가입 DAO
	 * @param conn
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception{
		int result = 0;
		String sql = prop.getProperty("signUp");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberNm());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setString(7, member.getMemberNickNm());
			
			result = pstmt.executeUpdate();
		
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 아이디 중복확인 DAO
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDubCheck(Connection conn, String id) throws Exception{
		int result = 0;
		String sql = prop.getProperty("idDubCheck");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);

			// result = pstmt.executeUpdate();
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result= rs.getInt(1);
			}
			System.out.println(result);
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	/**비밀번호
	 * @param conn
	 * @param memberId
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	public Member searchPw(Connection conn, String memberId, String memberEmail) throws Exception{
		
		Member m = null;
		
		String sql = prop.getProperty("searchPw");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberEmail);
			
			// SQL 구문 수행 후 조회 결과인 ResultSet을 rs 변수에 저장
			rs = pstmt.executeQuery();
			
			// 조회 결과가 있는지 확인 후 있으면 Member 객체를 생성하여 조회된 값을 저장
			// -> 로그인 결과는 없거나 1행만 있음 -> if문으로 검사
			if(rs.next()) {
				m= new Member(rs.getString("MEMBER_ID"), 
							rs.getString("MEMBER_EMAIL"));
			}
			
			
		}finally {
			
			close(rs);
			close(pstmt);
		}
		
		return m;
	}
	
	
	/** 회원 정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int memberUpdate(Connection conn, Member member) throws Exception{
		int result = 0;

		String sql = prop.getProperty("memberUpdate");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPhone());
			pstmt.setString(3, member.getMemberAddress());
			pstmt.setInt(4, member.getMemberNo());

			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}


	/**프로필 닉네임 수정 DAO
	 * @param conn
	 * @param memberNo
	 * @param memberNickNm
	 * @return
	 * @throws Exception
	 */
	public int updateNickNm(Connection conn, int memberNo, String memberNickNm) throws Exception {
		int result = 0;

		String sql = prop.getProperty("updateNickNm");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNickNm);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}

		return result;
	}


	/**프로필 사진 수정 DAO
	 * @param conn
	 * @param at
	 * @return
	 * @throws Exception
	 */
	public int updateProfile(Connection conn, Profile at) throws Exception {
		int result = 0;

		String sql = prop.getProperty("updateProfile");

		try {
			pstmt = conn.prepareStatement(sql);
				
				System.out.println(at.getFileName());
				System.out.println(at.getFilePath());
				
				pstmt.setString(1, at.getFilePath());
				pstmt.setString(2, at.getFileName());
				pstmt.setInt(3, at.getMemberNo());
			
				result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}

		return result;
	}


	/** 프로필 테이블 삽입 DAO
	 * @param conn
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public int insertProfile(Connection conn, String memberId) throws Exception{
		int result = 0;

		String sql = prop.getProperty("insertProfile");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}

		return result;
	}


	/**기존 프로필 check DAO
	 * @param conn
	 * @param memberNo
	 * @return
	 */
	public Profile memberPreProfile(Connection conn, int memberNo) throws Exception{
		
		Profile memberPreProfile = null;
		
		String sql = prop.getProperty("memberPreProfile");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberPreProfile
				= new Profile(
							rs.getInt("MEMBER_NO"), 
							rs.getString("PROFILE_PICTURE"), 
							rs.getString("PROFILE_PICTURE_NAME"),
							rs.getString("MEMBER_NICKNAME")
							);
			}			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return memberPreProfile;
	}


	/**비밀번호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int chagePw(Connection conn, String currentPw, String newPw1, int memberNo) throws Exception{
		
		int result = 0;
		
		String sql = prop.getProperty("changePw");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw1);
			pstmt.setString(2, currentPw);
			pstmt.setInt(3, memberNo);

			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int chagePw(Connection conn, String secessionPw, int memberNo) throws Exception{
		int result = 0;
		
		String sql = prop.getProperty("checkPw");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, secessionPw);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	/**탈퇴 DAO
	 * @param conn
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo) throws Exception {
		int result = 0;
		
		String sql = prop.getProperty("secession");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	/**Drop 테이블 삽입 DAO
	 * @param conn
	 * @param memberNo
	 * @param secessionAgree
	 * @param secessionReason
	 * @return
	 * @throws Exception
	 */
	public int insertDropTable(Connection conn, int memberNo, String secessionAgree, String secessionReason) throws Exception{
		int result = 0;
		
		String sql = prop.getProperty("insertDropTable");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, secessionReason);
			pstmt.setString(3, secessionAgree);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 비밀번호 찾기 DAO
	 * @param conn
	 * @param memberEmail
	 * @param memberId 
	 * @return
	 * @throws Exception
	 */
	public Member searchPw(Connection conn, String memberEmail, String memberId) throws Exception{
		
		Member m = null;
		
		String sql = prop.getProperty("searchPw");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberId);
			
			// SQL 구문 수행 후 조회 결과인 ResultSet을 rs 변수에 저장
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				m = new Member();
				m.setMemberNo(rs.getInt("MEMBER_NO"));
				m.setMemberId(rs.getString("MEMBER_ID"));
				m.setMemberPw(rs.getString("MEMBER_PW"));
				m.setMemberNm(rs.getString("MEMBER_NM"));
				m.setMemberPhone(rs.getString("MEMBER_PHONE"));
				m.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				m.setMemberAddress(rs.getString("MEMBER_ADDR"));
				m.setSignUpDt(rs.getDate("SIGNUP_DT"));
				m.setMemberStatus(rs.getString("MEMBER_STATUS"));
				m.setMemberGrade(rs.getString("MEMBER_GRADE"));
				m.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
			}
			
		}finally {
			
			close(rs);
			close(pstmt);
		}
		
		return m;
	}


	/** 아이디찾기 DAO
	 * @param conn
	 * @param memberNm
	 * @param memberEmail
	 * @return loginId
	 * @throws Exception
	 */
	public Member searchId(Connection conn, String memberNm, String memberEmail) throws Exception{
		
		Member loginId = null;
		
		String sql = prop.getProperty("searchId");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberNm);
			pstmt.setString(2, memberEmail);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				loginId = new Member();
				loginId.setMemberNo(rs.getInt("MEMBER_NO"));
				loginId.setMemberId(rs.getString("MEMBER_ID"));
				loginId.setMemberPw(rs.getString("MEMBER_PW"));
				loginId.setMemberNm(rs.getString("MEMBER_NM"));
				loginId.setMemberPhone(rs.getString("MEMBER_PHONE"));
				loginId.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginId.setMemberAddress(rs.getString("MEMBER_ADDR"));
				loginId.setSignUpDt(rs.getDate("SIGNUP_DT"));
				loginId.setMemberStatus(rs.getString("MEMBER_STATUS"));
				loginId.setMemberGrade(rs.getString("MEMBER_GRADE"));
				loginId.setMemberNickNm(rs.getString("MEMBER_NICKNAME"));
						
					
			}
			
		
			
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return loginId;
	}


		/**새로운 비밀번호 DAO
		 * @param conn
		 * @param memberPw
		 * @param memberId
		 * @return
		 * @throws Exception
		 */
		public int newPw(Connection conn, String memberPw,String memberId) throws Exception{
			int result = 0;
			
			String sql = prop.getProperty("newPw");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, memberPw);
				pstmt.setString(2, memberId);
				
				result = pstmt.executeUpdate();
				
				
			}finally {
				close(pstmt);
			}
			
			return result;
		}

}