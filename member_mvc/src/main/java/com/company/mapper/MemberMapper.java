package com.company.mapper;

import com.company.domain.MemberDTO;

public interface MemberMapper {
	// insert는 0/1을 리턴하기 때문에 int
	public int insert(MemberDTO memberDTO);
	public MemberDTO selectById(String userid);
}
