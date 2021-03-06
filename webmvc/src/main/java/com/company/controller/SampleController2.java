package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller     // 객체 생성
public class SampleController2 {
	
	// ModelAndView <- 옛날 리턴 타입 중 하나
	
	// @RequestMapping("/member/basic")
	@GetMapping("/member/basic")
	public void basic() {
		log.info("/member/basic 요청..........");
		// view 리졸버  /sample/basic 그대로 전달(void일 경우)     
		// void 타입일 경우 /WEB-INF/views/sample/basic.jsp
	}
	
	// GET 방식 응답
	// @RequestMapping(value="/member/login", method=RequestMethod.GET)
	@GetMapping("/member/login")
	public String login() {
		log.info("/member/basic 요청.........");
		return "login";    
	}
		
	// POST 방식 응답
	// @RequestMapping(value="/member/login", method=RequestMethod.POST)
	@PostMapping("/member/login")
	public String loginPost() {
		log.info("/member/login Post 요청");
		return "/sample/basic";
	}
	
	// @RequestMapping(value="/admin/info", method=RequestMethod.GET)
	@GetMapping("/admin/info")
	public void method1() {
		log.info("/admin/info 요청...");
		// 뷰 리졸버 : void인 경우 현재의 주소 /sample/info 그대로가 넘어간다
	} 
	
	// @RequestMapping(value="/admin", method=RequestMethod.GET)
	@GetMapping("/admin")
	public String method2() {
		log.info("/admin 요청....");
		// return "/sample/admin";
		// 뷰리졸버 : /sample/admin
		return "admin";  // => admin.jsp를 만들어야 하는 경로가 달라진다. 
	}
}









