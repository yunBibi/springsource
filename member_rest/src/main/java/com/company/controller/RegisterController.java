package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;

@Log4j2
@Controller
@RequestMapping("/register/*")
public class RegisterController {
	
	@Autowired
	private MemberService service;
	
	// registGet() 
	@GetMapping("/step1")
	public void registGet() {
		log.info("/step1 요청");
		// /register/step1
	}
	
	// step2
	@PostMapping("/step2")
	public String step2(boolean agree, RedirectAttributes rttr) {
		log.info("/step2 요청"+agree);
	
		if(!agree) {
			// step1 보여주기
			// return "step1";  //  WEB-INF/views/step1.jsp   (jsp페이지 보여주기
							 	                           // -> 주소는 /register/step2에 머무른다.)
			rttr.addFlashAttribute("check","false");
			return "redirect:/register/step1";    // http://localhost:8080/register/step1
			
		}
		return "/register/step2";
	}
	
	// 중복 아이디 검사
	@ResponseBody // 리턴하는 값이 데이터임!(jsp페이지 찾지 마시오)
	@PostMapping("/checkId")
	public String IdCheck(String userid) {
		log.info("중복 아이디 검사"+userid);
		
		if(service.dupId(userid)!=null) {
			return "false";
		}
		return "true";
	}
	
	
	 // step3
	@PostMapping("/step3")
	public String step2Post(MemberDTO memberDTO) {
		log.info("회원가입 요청 "+memberDTO);
		
		try {
			if(!service.register(memberDTO)) {
				// 회원가입 페이지로 이동
				return "/register/step2";
			}
			
		} catch (Exception e) {
			// 회원가입 페이지로 이동
			return "/register/step2";
		}
		return "redirect:/member/signin";		
	}
	
	
	// 들어오면 안 되는 경로를 요청할 때 처리법
	// 405 - 허용되지 않는 메소드 이러한 에러 페이지를 보여주지 않는 방법
	// http://localhost:8080/register/step3 + GET
	// http://localhost:8080/register/step2 + GET
	@GetMapping(value= {"/step2","/step3"})
	public String handleGet() {
		log.info("/step2,/step3 직접 요청");
		// return "redirect:/register/step1";
		return "redirect:/";   // 인덱스로 빼기
	}
	
}













