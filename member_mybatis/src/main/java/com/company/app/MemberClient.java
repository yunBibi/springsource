package com.company.app;

import java.util.List;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MemberClient {

	public static void main(String[] args) {
		
		log.info("main 시작");
		// 스프링 컨테이너 로드
		ApplicationContext ctx
		= new ClassPathXmlApplicationContext("config.xml");
		
		// 서비스 메소드 호출
		MemberService service = (MemberService) ctx.getBean("service");
		
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		
		while(flag) {
			System.out.println("===============================");
			System.out.println("1. 전체 멤버 조회");
			System.out.println("2. 특정 멤버 조회");
			System.out.println("3. 특정 멤버 수정");
			System.out.println("4. 특정 멤버 삭제");
			System.out.println("5. 특정 멤버 추가");
			System.out.println("6. 프로그램 종료");
			System.out.println("===============================");
			
			System.out.print("메뉴 >> ");
			int no = Integer.parseInt(sc.nextLine());
			
			switch (no) {
				case 1:
					System.out.println();
					
					List<MemberDTO> list = service.getList();
					
					System.out.println("아이디\t 성명\t 성별\t 이메일\t");
					System.out.println("-----------------------------");
					
					for(MemberDTO dto:list) {
						System.out.print(dto.getUserid()+"\t");
						System.out.print(dto.getName()+"\t");
						System.out.print(dto.getGender()+"\t");
						System.out.print(dto.getEmail()+"\t");
					}
					System.out.println();
					
					break;
				case 2:
					System.out.println("조회할 사용자 정보 입력");
					System.out.println("-------------------");
					System.out.println("userid : ");
					String userid = sc.nextLine();
					System.out.println("password : ");
					String password = sc.nextLine();
					
					MemberDTO memberDto = service.getRow(userid, password);
					System.out.println();
					System.out.println("조회한 사용자 정보는 다음과 같습니다.");
					System.out.println("-------------------");
					System.out.print("userid : "+memberDto.getUserid()+"\t");
					System.out.print("name : "+memberDto.getName()+"\t");
					System.out.print("gender : "+memberDto.getGender()+"\t");
					System.out.print("email : "+memberDto.getEmail()+"\t");
					break;
				case 3:
					System.out.println();
					System.out.println("수정할 사용자 정보 입력");
					System.out.println("-------------------");
					System.out.println("userid : ");
					userid = sc.nextLine();
					System.out.println("password : ");
					password = sc.nextLine();
					System.out.println("confirm_password : ");
					String confirm_password = sc.nextLine();
					
					ChangeDTO changeDTO = new ChangeDTO();
					changeDTO.setUserid(userid);
					changeDTO.setPassword(password);
					changeDTO.setConfirm_password(confirm_password);
					
					System.out.println("수정 결과는 다음과 같습니다.");
					System.out.println(service.updateMember(changeDTO)?"수정성공":"수정실패");
					
					
					break;
				case 4:
					System.out.println("탈퇴할 사용자 정보 입력");
					System.out.println("-------------------");
					System.out.println("userid : ");
					userid = sc.nextLine();
					System.out.println("password : ");
					password = sc.nextLine();
				
					System.out.println("탈퇴 결과는 다음과 같습니다.");
					System.out.println(service.deleteMember(userid, password)?"탈퇴 성공":"탈퇴 실패");
					
					
					break;
				case 5:
					System.out.println("회원가입 정보 입력");
					System.out.println("-------------------");
					
					MemberDTO insertDto = new MemberDTO();
					
					System.out.print("userid : ");
					insertDto.setUserid(sc.nextLine());
					
					System.out.print("password : ");
					insertDto.setPassword(sc.nextLine());
					
					System.out.print("name : ");
					insertDto.setName(sc.nextLine());
					
					System.out.print("gender : ");
					insertDto.setGender(sc.nextLine());
					
					System.out.print("email : ");
					insertDto.setEmail(sc.nextLine());
					
					System.out.println("회원가입 결과는 다름과 같습니다.");
					System.out.println(service.insertMember(insertDto)?"추가 성공":"추가 실패");
				
					break;
				case 6:
					flag = false;
					System.out.println("프로그램을 종료합니다.");
					break;
		
			}
			
		}
	}

}
