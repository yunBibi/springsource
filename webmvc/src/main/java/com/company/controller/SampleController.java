package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.UserDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller     // 객체 생성
@RequestMapping("/sample/*")
public class SampleController {
	
	// ModelAndView <- 옛날 리턴 타입 중 하나
	
	@RequestMapping("/basic")
	public void basic() {
		log.info("/basic 요청..........");
		// view 리졸버  /sample/basic 그대로 전달(void일 경우)     
		// void 타입일 경우 /WEB-INF/views/sample/basic.jsp
	}
	
//		// 기본 GET / POST 둘 다 응답
//	@RequestMapping("/login")
//	public String login() {
//		log.info("/basic 요청.........");
//		return "login";    
//  //String 타입일 경우 login만을 가지고 servlet view resolver로 간다. -> 앞뒤로 /WEB-INF/views 와 .jsp를 붙인다.
//	}
	
//	// GET 방식 응답
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		log.info("/basic 요청.........");
		return "login";    
	}
		
	// POST 방식 응답
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String loginPost() {
//		log.info("/login Post 요청");
//		return "/sample/basic";
//	}
//	
	
	
	// POST 방식 응답 + 사용자의 입력값 가져오기
	// 1. HttpServletRequest 객체 사용
	// 2. 바인딩 변수 사용
	// 3. 바인딩 객체 사용
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String loginPost(HttpServletRequest req) {
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+req.getParameter("userid"));
//		log.info("password : "+req.getParameter("password"));
//		
//		return "/sample/basic";
//	}
	
	
//	// 바인딩 변수
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String loginPost(String userid,@RequestParam("pwd") String password, Model model) {
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+userid);
//		log.info("password : "+password);
//		
//		model.addAttribute("userid", userid);
//		model.addAttribute("password", password);   // request.setAttribute
//		
//		return "/sample/basic";
//	}
//	
	
//	// 바인딩 객체
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(@ModelAttribute("login") UserDTO userDto) {
		log.info("/login Post 요청");
		
		log.info("userid : "+userDto.getUserid());
    	log.info("password : "+userDto.getPassword());
    	log.info("name : " + userDto.getName());
		
		return "/sample/basic";
	}

	@RequestMapping(value="/info", method=RequestMethod.GET)
	public void method1() {
		log.info("/sample/info 요청...");
		// 뷰 리졸버 : void인 경우 현재의 주소 /sample/info 그대로가 넘어간다
	} 
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String method2() {
		log.info("/sample/admin 요청....");
		// return "/sample/admin";
		// 뷰리졸버 : /sample/admin
		return "admin";  // => admin.jsp를 만들어야 하는 경로가 달라진다. 
	}
}









