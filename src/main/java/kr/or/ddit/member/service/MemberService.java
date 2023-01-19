package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Business Logic Layer
 * @author PC-02
 *
 */
public interface MemberService {
	/**
	 * 회원 가입
	 * @param member
	 * @return 아이디 중복으로 인한 실패 (PKDUPLICATED), 가입 성공 (OK), 가입 실패 (FAIL)
	 */
	public ServiceResult createMember(MemberVO member);
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO);
	/**
	 * 회원 정보 상세 조회
	 * @param memId
	 * @return 존재하지 않는 경우, {@link UserNotFoundException} 발생.
	 */
	public MemberVO retrieveMember(String memId);
	/**
	 * 회원 수정
	 * @param member
	 * @return 존재 여부(NOTEXIST), 비번 인증(INVALIDPASSWORD), 성공 (OK), 실패(FAIL)
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 회원 탈퇴
	 * @param memId
	 * @return 존재 여부(NOTEXIST), 비번 인증(INVALIDPASSWORD), 성공 (OK), 실패(FAIL)
	 */
	public ServiceResult removeMember(MemberVO member);
	
}
