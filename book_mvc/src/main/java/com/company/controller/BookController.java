package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	// insert.jsp 보여주는 컨트롤러 생성
	@GetMapping("/insert")
	public void insertGet() {
		log.info("insert 폼 요청");
	}
	
	// 새 도서 입력
	// 입력 성공 시 전체 목록 보기로 이동
	@PostMapping("/insert")
	public String insertPost(BookDTO insertDto) {
		log.info("새 도서 입력"+insertDto);
		
		try {
			if(service.insert(insertDto)) {
				return "redirect:list";
			}			
		} catch (Exception e) {
			return "/book/insert";
		}
		return "/book/insert";
	}
	
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("전체 리스트 요청");
		List<BookDTO> list = service.getList();
		
		log.info(""+list);	
		model.addAttribute("list",list);     // ==request.setAttribute()
		
		//  /book/list  => jsp 찾는데 사용됨
	}
	
	@GetMapping("/read")
	public void readGet(String code, Model model) {
		log.info("read 요청 "+code);
		BookDTO dto = service.getRow(code);
		
		model.addAttribute("dto", dto);
	}
	
}
