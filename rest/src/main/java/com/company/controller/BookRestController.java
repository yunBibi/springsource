package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/book/*")
@Log4j2
public class BookRestController {
	
	@Autowired
	private BookService service;
	
	@PostMapping("/insert-rest")
	// @ResponseBody
	public String insertPost(@RequestBody BookDTO insertDto) {
		log.info("새 도서 입력"+insertDto);
		
		try {
			if(service.insert(insertDto)) {
				return "success";
			}			
		} catch (Exception e) {
			return "fail";
		}
		return "fail";
	}
	
	@GetMapping(value="/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> row(@PathVariable("code") String code){
		log.info("책 하나 가져오기 "+code);
		
		BookDTO dto = service.getRow(code);
		
		return new ResponseEntity<BookDTO>(dto,HttpStatus.OK);
	}
	
}
