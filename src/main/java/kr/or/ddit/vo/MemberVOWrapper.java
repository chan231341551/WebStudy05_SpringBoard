package kr.or.ddit.vo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberVOWrapper extends User {
	private MemberVO realMember;

	public MemberVOWrapper(MemberVO realMember) {
		super(realMember.getMemId(),realMember.getMemPass(),AuthorityUtils.createAuthorityList(realMember.getMemRole()));
		this.realMember = realMember;
	}
	
	public MemberVO getRealMember() {
		return realMember;
	}
}
