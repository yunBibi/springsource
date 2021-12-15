package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.PageDTO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;	
	
	//    /board/register.jsp
	//  isAuthenticated() : 인증된 사용자의 경우 true
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void registerGet() {
		log.info("register 폼 요청");
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto,RedirectAttributes rttr) {
		log.info("register 가져오기 "+insertDto);
		
		// 첨부파일 확인하기
//		if(insertDto.getAttachList()!=null) {
//			insertDto.getAttachList().forEach(attach -> log.info(attach+""));
//		}
		service.register(insertDto);	
		
		// log.info("bno "+insertDto.getBno());
		
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(Model model,Criteria cri) {
		log.info("전체 리스트 요청"+cri);
		
		List<BoardDTO> list = service.getList(cri);
		
		// 페이지 나누기를 위한 정보 얻기   - 전체 게시물 수가 중요
		int totalCnt = service.getTotalCount(cri);
		
		model.addAttribute("pageDto", new PageDTO(cri, totalCnt));
		model.addAttribute("list", list);
	}
	
	@GetMapping({"/read","/modify"})
	public void readGet(int bno,@ModelAttribute("cri") Criteria cri,Model model) {
		log.info("read or modify 요청"+bno);
		
		BoardDTO dto = service.getRow(bno);
		
		model.addAttribute("dto",dto);
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO modifyDto,Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 수정"+modifyDto+"   "+cri);
		
		// 수정 완료 후 리스트로 이동
		// service.update(modifyDto);
		
		// 페이지 나누기 값
		rttr.addAttribute("pageNum", cri.getPageNum());   // Parameter 형태로 주소줄에 따라감
		rttr.addAttribute("amount", cri.getAmount());
		// 보던 데로 돌아가게 해주는 코드
		
		// 검새 값
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result","success");
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String removePost(int bno,Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 삭제" + bno);
		
		// 첨부파일 목록 얻어오기
		List<AttachFileDTO> attachList =service.findByBno(bno);
		deleteFiles(attachList);
		
		// 수정 삭제 후 리스트로 이동
		if(service.remove(bno)) {
			// 첨부 파일 삭제
			deleteFiles(attachList);
			
			// 페이지 나누기 값
			rttr.addAttribute("pageNum", cri.getPageNum());   // Parameter 형태로 주소줄에 따라감
			rttr.addAttribute("amount", cri.getAmount());
			// 검새 값
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			
			rttr.addFlashAttribute("result","success");    // 주소줄에 따라가지 않음	
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno){
		log.info("파일 첨부 가져오기 " + bno);
		return new ResponseEntity<List<AttachFileDTO>>(service.findByBno(bno),HttpStatus.OK);
	}
	
	private void deleteFiles(List<AttachFileDTO> attachList) {
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		log.info("파일 삭제 중...");
		attachList.forEach(attach -> {
			Path file = Paths.get("c:\\upload\\"+attach.getUploadPath()
											+"\\"+attach.getUuid()+"_"+attach.getFileName());
			
			try {
				// 일반파일, 이미지 원본파일만 삭제
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					Path thumbNail = Paths.get("c:\\upload\\"+attach.getUploadPath()
														+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					// 이미지 썸네일 삭제
					Files.delete(thumbNail);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}












