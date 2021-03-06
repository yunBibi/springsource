package com.company.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;

/**
 * Handles requests for the application home page.
 */

@Log4j2
@Controller
public class HomeController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/doA")    //  http://localhost:8080/doA  => HomeController doA()
	public String doA(RedirectAttributes rttr) {
		
		log.info("/doA 요청");
		
		// RedirectAttributes : sendRedirect로 움직일 때 값을 전달하는 객체
		// addAttribute() : 주소줄에 파라메터로 보내짐
		// addFlashAttribute() : session객체를 이용하나, 잠시만 사용
		
		rttr.addAttribute("age", 20);
		rttr.addFlashAttribute("name", "홍길동");
		
		return "redirect:/doB";
	}
	
	@GetMapping("/doB")
	public void doB() {
		log.info("/doB 요청");
	}
	
	@GetMapping("/doC")
	public void doC() {
		log.info("/doC 요청");
	}
}
