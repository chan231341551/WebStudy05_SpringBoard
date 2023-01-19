package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequestMapping("/member/memberUpdate.do")
@RequiredArgsConstructor
@Controller
public class MemberUpdateController {
	
	private final MemberService service;
	
	@GetMapping
	public String UpdateForm(
		@SessionAttribute("authMember") MemberVO authMember
		,Model model
		) throws ServletException {
		
		MemberVO member = service.retrieveMember(authMember.getMemId());
		
		model.addAttribute("member", member); 
		
		String viewName = "member/memberForm";
		return viewName;
	
	}
	
	@PostMapping
	public String UpdateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member // vo를 첨부터 만들고 member라는 이름으로 데이터를 넣어줌
//		,@RequestPart(value="memImage",required=false) MultipartFile memImage
		,BindingResult errors
		,Model model
		,HttpSession session
		) throws ServletException, IOException {
			
		boolean valid = !errors.hasErrors();
		
		String viewName = "";
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			
			switch (result) {
			case INVALIDPASSWORD:
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
				
			case FAIL:
				model.addAttribute("message", "서버에 문제 있음. 다시 ");
				viewName = "member/memberForm";
				break;
				
			default:
				MemberVO authMember = service.retrieveMember(member.getMemId());
				session.setAttribute("authMember", authMember); 
				
				viewName = "redirect:/mypage.do";
				break;
			}
		}
		else {
			viewName = "member/memberForm";
			
		}
		return viewName;
	
	}
	
	
}
