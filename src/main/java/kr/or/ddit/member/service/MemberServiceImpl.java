package kr.or.ddit.member.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	
	// 결합력 최상... (안좋은경우)
	// 결합력 낮아짐 
	@Inject
	private MemberDAO memberDAO;
//	@Inject
//	private AuthenticateService authService;
	@Resource(name="authenticationManager")
	private AuthenticationManager authenticationManager;
	@Inject
	PasswordEncoder encoder;
	
	// Inject 후 실행 메소드
	@PostConstruct
	public void init() {
		log.info("주입된 객체 : {},{},{}",memberDAO,authenticationManager,encoder);
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		
		ServiceResult result = null;
		try {
			retrieveMember(member.getMemId());
			result = ServiceResult.PKDUPLICATED;
			
		}catch (UserNotFoundException e) {
			String encoded = encoder.encode(member.getMemPass());
			member.setMemPass(encoded);
			int rowcnt = memberDAO.insertMember(member);
			
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;

		}
		return result;
		
	}
		

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(memberDAO.selectTotalRecord(pagingVO));
		
		List<MemberVO> memberList = memberDAO.selectMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		memberList.stream()
		.forEach(System.out::println); // 메소드 레퍼런스 구조
	
		return memberList;
		
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO selectMember = memberDAO.selectMember(memId);
		if(selectMember == null) {
			throw new UserNotFoundException(String.format(memId+"에 해당하는 사용자 없음."));
		}
	
		return selectMember;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		
//		MemberVO inputDate = new MemberVO();
//		inputDate.setMemId(member.getMemId());
//		inputDate.setMemPass(member.getMemPass());
//		
//		ServiceResult result = authService.authenticate(inputDate);
//		
//		if(ServiceResult.OK.equals(result)) {
//			int rowcnt = memberDAO.updateMember(member);
//			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
//
//		}
//		return result;
		ServiceResult result = null;
		Authentication inputData = new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass());
		try {
			authenticationManager.authenticate(inputData);
			int rowcnt = memberDAO.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (UsernameNotFoundException e) {
			result = ServiceResult.NOTEXIST;
		}catch (AuthenticationException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
		
		
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		
//		ServiceResult result = authService.authenticate(member);
//		
//		if(ServiceResult.OK.equals(result)) {
//			int rowcnt = memberDAO.deleteMember(member.getMemId());
//			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
//			
//		}
//		return result;
		

		ServiceResult result = null;
		Authentication inputData = 
				new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass());
		
		try {
			authenticationManager.authenticate(inputData);
			int rowcnt = memberDAO.deleteMember(member.getMemId());
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}catch (UsernameNotFoundException e) {
			result = ServiceResult.NOTEXIST;
		}catch (AuthenticationException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
