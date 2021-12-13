package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.AddDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/calc/*")
public class AddController {
	
	// get방식 => add.jsp를 보여주도록
	@GetMapping("/add")
	public void addGet() {
		log.info("/calc/add 요청 ... ");	
	}
//	public void addPost() {
//		log.info("/calc/add post 요청");
//		
//	}
	
	@PostMapping("/add")
	public void addPost(AddDTO addDTO, Model model) {
		log.info("/calc/add post 요청 " + addDTO.getNum1()+", "+addDTO.getNum2());
		
		
		// 덧셈한 후 결과를 add.jsp에 보여주기
		int result = addDTO.getNum1() + addDTO.getNum2();
		model.addAttribute("result", result);
		
		log.info("/calc/add post 결과 " + result);
		
	}
	
}
