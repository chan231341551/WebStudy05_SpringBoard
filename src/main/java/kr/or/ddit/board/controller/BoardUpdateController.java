package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.UpdateGroup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board/boardUpdate.do")
public class BoardUpdateController {
	
	private final BoardService service;
	
	@GetMapping
	public String updateForm(
		@RequestParam("what") int boNo
		,Model model
		) {
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board",board);
		
		return "board/boardForm";
	}
	
	@PostMapping
	public String boardUpdate(
		@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board
		,Model model
		,BindingResult errors
		) {
		
		String viewName = null;
		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			ServiceResult result = service.modifyBoard(board);
			if(result.equals(ServiceResult.OK)) {
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
			}
			else {
				model.addAttribute("message","서버 오류");
				viewName = "board/boardForm";
			}
		}
		else {
			model.addAttribute("message","수정 오류");
			viewName = "board/boardForm";
		}
		return viewName;
	}

}
