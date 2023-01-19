package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import kr.or.ddit.vo.PagingVO;

@Mapper
public interface MemberDAO extends UserDetailsService {
	public MemberVO selectMember(String memId);
	
	@Override
	default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = selectMember(username);
		if(member == null) {
			throw new UsernameNotFoundException("해당 사용자 없음.");
		}
		UserDetails user = new MemberVOWrapper(member);
		return user;
	}
	
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);
	
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO);
	
	public int insertMember(MemberVO member);
	
	public int updateMember(MemberVO member);
	
	public int deleteMember(String memId);
}
