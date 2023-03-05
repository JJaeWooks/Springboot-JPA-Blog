package com.cosblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cosblog.service.BoardService;

@Controller

public class BoardController {


	@Autowired
	private BoardService boardService;
	//@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size = 3,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {// 컨트롤로에서 세션을 어떻게 찾는지?
		//WEB-INF/views/index.jsp
		model.addAttribute("boards",boardService.글목록(pageable));
		return "index";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail";
		
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {// 컨트롤로에서 세션을 어떻게 찾는지?
		//WEB-INF/views/index.jsp
		return "board/saveForm";
	}
}
