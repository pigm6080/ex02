package com.study.erum.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.erum.dto.BoardDTO;
import com.study.erum.dto.PageDTO;
import com.study.erum.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller //컨트롤러임을 나타냄.
@RequiredArgsConstructor //필드기반 생성자 생성. @Autowired 상위버전
@RequestMapping("/board") // 핸들러 메서
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/save") // Get
	public String saveForm() {
		
		return "save";
	}
	
	@PostMapping("/save") // Post
	public String save(@ModelAttribute BoardDTO boardDTO) {
		
		// ModelAttribute == jsp단에서 name과 일치하는필드명에 값을 넣어주는 어노텐션
		int saveResult = boardService.save(boardDTO);
		
		if(saveResult > 0) {
			return "redirect:/board/";
		}else {
			return "save";
		}
	}
	
	@GetMapping("/")
	public String findAll(Model model) {
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList",boardDTOList);
		return "list";
	}
	
	@GetMapping
	public String findById(@RequestParam("id") Long id,	@RequestParam(value = "page", required = false, defaultValue = "1") int page,Model model) {
		
		boardService.updateHits(id);
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board",boardDTO);
		model.addAttribute("page",boardDTO);
		return "detail";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		boardService.delete(id);
		return "redirect:/board/";
	}
	
	@GetMapping("/update")
	public String updateForm(@RequestParam("id") Long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board",boardDTO);
		return "update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
//        return "redirect:/board?id="+boardDTO.getId(); // 조회수 증가
	}
	 @GetMapping("/paging")
	    public String paging(Model model,
	                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
	        System.out.println("page = " + page);
	        List<BoardDTO> pagingList = boardService.pageList(page);
	        System.out.println("pageList = " + pagingList); 
	        PageDTO pageDTO = boardService.pagingParam(page);
	        model.addAttribute("boardList",pagingList);
	        model.addAttribute("paging",pageDTO);
	        return "paging";
	    }
	
}
