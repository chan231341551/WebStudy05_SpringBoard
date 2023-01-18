package kr.or.ddit.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.security.AuthMember;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MypageController {
	
	@RequestMapping("/mypage")
	public String myPage(
		Authentication authentication
		,@AuthenticationPrincipal MemberVOWrapper user
		,@AuthenticationPrincipal(expression="realMember") MemberVO member
		,@AuthMember MemberVO authMember
		,Model model
	) {
//		MemberVOWrapper user = (MemberVOWrapper) authentication.getPrincipal();
//		String memId = user.getUsername();
//		MemberVO member = user.getRealMember();
		log.info("authentication : {}",authentication);
		log.info("authentication's principal : {}",user);
		log.info("principal;s adaptee : {}",member);
		log.info("auth Member : {}",authMember);
		model.addAttribute("member",member);
		
		return "member/mypage";
	}
}
