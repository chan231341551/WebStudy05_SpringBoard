package kr.or.ddit.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

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
}
