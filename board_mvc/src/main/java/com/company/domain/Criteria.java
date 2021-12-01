package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;   // 페이지 번호
	private int amount;    // 한 페이지 당 보여줄 게시물 수 
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1, 10);
		
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		// 제목 => T => {'T'}
		// 제목 or 내용 or 작성자 =>  => {'T', 'C', 'W'}
		
		return type == null? new String[] {}:type.split("");   
		// 조건에 맞지 않으면 문자 하나씩 잘라서 리턴하게 하는
	}
}
