package kr.or.ddit.member.controller;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;


@Controller
public class MemberDeleteController {
	
	@Inject
	private MemberService service;
	
	@PostMapping("/member/memberDelete.do")
	public String memberDelete(
		@RequestParam(value="memPass", required=true)String memPass // 이미 어댑터가 검증하고나서 줌
		,@SessionAttribute(value="authMember",required=true) MemberVO authMember
		,HttpSession session
		,RedirectAttributes redirectAttributes
		,HttpServletRequest req) throws ServletException {
//		1.
		String memId = authMember.getMemId();
		
		
		MemberVO inputDate = new MemberVO();
		inputDate.setMemId(memId);
		inputDate.setMemPass(memPass);
		
		
		String viewName = null;
			ServiceResult result = service.removeMember(inputDate);
			
		switch (result) {
		case INVALIDPASSWORD:
			redirectAttributes.addFlashAttribute("message", "비번 오류");
			viewName = "redirect:/mypage.do";
			break;
		case FAIL:
			redirectAttributes.addFlashAttribute("message", "서버 오류");
			viewName = "redirect:/mypage.do";
			break;
		default:
			session.invalidate();
			viewName = "redirect:/";
			break;
		}
		return viewName;
	}
}

