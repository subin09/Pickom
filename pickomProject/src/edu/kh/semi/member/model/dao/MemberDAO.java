package edu.kh.semi.member.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.semi.member.model.vo.Member;

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
	
	


}