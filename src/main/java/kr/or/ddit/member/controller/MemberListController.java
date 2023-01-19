package kr.or.ddit.member.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberListController{
	
	
	private final MemberService service;
	
	@RequestMapping("/member/memberList.do")
	public ModelAndView memberList(
		@RequestParam(value="page",required=false, defaultValue="1") int currentPage
		,@ModelAttribute SearchVO simpleCondition // SearchVO -> searchVO 바뀌고 들어감
	){
				
		PagingVO<MemberVO> pagingVO = new PagingVO<>(4,2);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleCondition(simpleCondition);
		
		ModelAndView mav = new ModelAndView();
				
//		2.
		service.retrieveMemberList(pagingVO);
		
//		3.
		mav.addObject("pagingVO", pagingVO);
//		model.addAttribute("pagingVO",pagingVO);
		
		log.info("paging data : {}",pagingVO);
//		4.
		String viewName = "member/memberList";
		
//		5.
		mav.setViewName(viewName);
		return mav;
//		return viewName;
	}
}
