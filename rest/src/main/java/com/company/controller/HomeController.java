package com.company.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.domain.SampleDTO;

import lombok.extern.log4j.Log4j2;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j2
public class HomeController {	

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		log.info("index 요청");
		return "index";
	}
	
	@ResponseBody
	@GetMapping(value="/sample2", produces= {MediaType.APPLICATION_JSON_VALUE})
	public SampleDTO sampleJson() {
		log.info("Sample2");
		
		SampleDTO sample = new SampleDTO();
		sample.setFirstName("hong");
		sample.setLastName("dong");
		
		return sample;
	}
	
	@ResponseBody     // 야, 이거 데이터야~ jsp 아니야
	@GetMapping(value="/list2", produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<SampleDTO> sampleJsonList() {
		log.info("Sample");
		
		List<SampleDTO> list = new ArrayList<SampleDTO>();
		
		for(int i=0;i<10;i++) {

		SampleDTO sample = new SampleDTO();
		sample.setMno(i+"");
		sample.setFirstName("hong");
		sample.setLastName("dong");
		list.add(sample);
		}
		return list;
	}
	
}