package edu.kh.semi.member.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.member.model.dao.MemberDAO;
import edu.kh.semi.member.model.vo.Member;
import edu.kh.semi.member.model.vo.Profile;


// Service : 비즈니스 로직 처리(데이터 가공, 트랜잭션 처리)
public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	
	/** 로그인 Service
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn, memberId, memberPw);
		
		close(conn);
		
		return loginMember;
	}
	
	
	/** 회원가입 Service
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, member);
		
		if(result > 0) {
			String memberId = member.getMemberId();
			result = dao.insertProfile(conn, memberId);
			
			if(result > 0) commit(conn);
			else rollback(conn);
		} else rollback(conn);		
		
		close(conn);
		
		return result;
	}


	/** 아이디 중복확인 Service
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String id) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.idDubCheck(conn, id);
		
		close(conn);
		
		return result;
	}


	/** 비밀번호찾기 
	 * @param memberId
	 * @param memberEmail 
	 * @return m
	 * @throws Exception
	 */
	public Member searchPw(String memberId, String memberEmail) throws Exception{
		
		Connection conn = getConnection();
		
		// 얻어온 Connection과 매개변수를 DAO의 알맞은 메소드로 전달하여 결과를 반환 받음.
		Member m = dao.searchPw(conn, memberId,memberEmail);
		
		// 커넥션 반환
		close(conn);
		
		return m;
	}


	/** 회원 정보 수정 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int memberUpdate(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.memberUpdate(conn, member);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/**프로필 수정 Service
	 * @param memberNo
	 * @param atList
	 * @param memberNickNm
	 * @return result
	 * @throws Exception
	 */
	public int updateProfile(int memberNo, List<Profile> atList, String memberNickNm) throws Exception  {
		
		Connection conn = getConnection();
		
		int result = dao.updateNickNm(conn, memberNo, memberNickNm);
		
		if(result >0) {
			
			for(Profile at : atList) {
				at.setMemberNo(memberNo);				
					result = dao.updateProfile(conn, at);
				if(result==0) {
					rollback(conn);
					break;
				}
			}
			if(result>0) commit(conn);
			else rollback(conn);
		
		} else rollback(conn);
		
		close(conn);
		return result;
	}


	/**기존 프로필 check Service
	 * @param memberNo
	 * @return
	 */
	public Profile memberPreProfile(int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		Profile memberPreProfile = dao.memberPreProfile(conn, memberNo);
		
		close(conn);
		
		return memberPreProfile;
	}


	/**비밀 번호 변경 service
	 * @param currentPw
	 * @param newPw1
	 * @param memberNo
	 * @return
	 */
	public int changePw(String currentPw, String newPw1, int memberNo) throws Exception {
		
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.chagePw(conn, currentPw, newPw1, memberNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/**비밀번호 확인 service
	 * @param secessionPw
	 * @return
	 */
	public int checkPw(String secessionPw, int memberNo)  throws Exception {
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.chagePw(conn, secessionPw, memberNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/**회원 탈퇴 Service
	 * @param memberNo
	 * @param secessionAgree
	 * @param secessionReason
	 * @return
	 */
	public int secession(int memberNo, String secessionAgree, String secessionReason) throws Exception{
		
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.secession(conn, memberNo);
		
		if(result>0) {
			result = dao.insertDropTable(conn, memberNo, secessionAgree, secessionReason);
			
			if(result >0) commit(conn);
			else rollback(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
	/** 비밀번호찾기 
	 * @param memberId
	 * @param memberEmail 
	 * @param memberId 
	 * @return m
	 * @throws Exception
	 */
	public Member searchPw(String memberEmail, String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		// 얻어온 Connection과 매개변수를 DAO의 알맞은 메소드로 전달하여 결과를 반환 받음.
		Member m = dao.searchPw(conn,memberEmail,memberId);
		
		// 커넥션 반환
		close(conn);
		
		return m;
	}


	/** 아이디찾기 
	 * @param memberNm
	 * @param memberEmail
	 * @return loginId
	 * @throws Exception
	 */
	public Member searchId(String memberNm, String memberEmail) throws Exception{
		
		Connection conn = getConnection();
		
		Member loginId = dao.searchId(conn, memberNm, memberEmail);
		
		close(conn);
		
		return loginId;
	}


	/** 비밀번호찾기 비밀번호변경
	 * @param memberPw
	 * @param memberPw2
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int newPw(String memberPw, String memberId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.newPw(conn, memberPw,memberId);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		close(conn);
		
		return result;
	}
}