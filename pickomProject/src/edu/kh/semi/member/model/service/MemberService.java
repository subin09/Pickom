package edu.kh.semi.member.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.semi.member.model.dao.MemberDAO;
import edu.kh.semi.member.model.vo.Member;


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
		
		// DB 연결 정보를 담고있는 Connection을 얻어옴.
		Connection conn = getConnection();
		
		// 얻어온 Connection과 매개변수를 DAO의 알맞은 메소드로 전달하여 결과를 반환 받음.
		Member loginMember = dao.login(conn, memberId, memberPw);
		
		// 커넥션 반환
		close(conn);
		
		// 서비스 수행 결과 반환
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
			commit(conn);
		}else {
			rollback(conn);
		}
		
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