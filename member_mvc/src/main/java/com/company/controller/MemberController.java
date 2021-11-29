package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 로그인
	@GetMapping("/signin")
	public void signin() {
		log.info("로그인 페이지 요청");
		// /member/sigin
	}
	
	// loginPost() 사용자 입력 아이디, 비밀번호 받아오기
	@PostMapping("/signin")
	public String loginPost(LoginDTO loginDto, HttpSession session) {
		log.info("login 요청 " + loginDto);
		
		loginDto = service.login(loginDto);
		session.setAttribute("loginDto", loginDto);
		
		return "redirect:/";	
	
	} 
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃 요청");
		session.invalidate();    // session.removeAttribute("loginDto");
		return "redirect:/";
	}
	
	// /changePwd : changePwd.jsp 보여주는 컨트롤러
//	@GetMapping("/changePwd")
//	public String changPwd() {
//		log.info("비밀번호 변경 요청");
		// return "/changePwd";   -->  [/WEB-INF/views/changePwd.jsp]
		// return "/member/changePwd";   vs  return "/changePwd";
//	}
	
	@GetMapping("/changePwd")          // void는 그대로 간다. ---> /member/changePwd.jsp 
	public void changePwd() {
		log.info("changePwd.jsp 요청");
	}
	
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDto, HttpSession session) {
		log.info("비밀번호 변경 요청"+changeDto);
		
		// 비밀번호 변경 요청
		// 1. userid 가져오기
		LoginDTO loginDto = (LoginDTO) session.getAttribute("loginDto");
		changeDto.setUserid(loginDto.getUserid());
		
		if(service.changePwd(changeDto)) {
			// 비밀번호 변경이 되면 기존의 세션 해제
			session.invalidate();
			// 로그인(sign in) 페이지 보여주기
			return "redirect:/member/signin";			
		}  // 변경 실패 시 다시 changePwd 보여주기
		return "redirect:/member/changePwd";
	}
	
	// 회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("leave.jsp 요청");
	}
	
	@PostMapping("/leave")
	public String leavePost(LoginDTO leaveDto, HttpSession session) {
		log.info("탈퇴 요청" + leaveDto);
		
		if(service.leave(leaveDto)) {          // 탈퇴 성공 시
			session.invalidate();
			return "redirect:/";
		}
		return "redirect:/member/leave";       // 탈퇴 실패 시 
	}
}




















