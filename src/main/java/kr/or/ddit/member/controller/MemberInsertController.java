package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * Backend controller(command hannler) --> Plain Old Java Object
 */
@Slf4j
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertController {
	
	@Inject
	private MemberService service;
	
	@ModelAttribute("member")
	public MemberVO member() {
		log.info("@modelAttribute 메소드 실행 -> member 속성 생성");
		return new MemberVO();
	}
	
	@GetMapping
	public String memberFrom()  {
		
		return "member/memberForm";
	
	}
	
	@PostMapping
	public String insert(
		HttpServletRequest req
		, @Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
		, Errors errors
	) throws ServletException, IOException {
		
		boolean valid = !errors.hasErrors();
		
		String viewName = "";
		if(valid) {
			ServiceResult result = service.createMember(member);
			
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case FAIL:
				req.setAttribute("message", "서버에 문제 있음. 다시 가입하셈");
				viewName = "member/memberForm";
				break;
			default:
				viewName = "redirect:/";
				break;
			}
		}
		else {
			viewName = "member/memberForm";
		}
		return viewName;
	}
}
