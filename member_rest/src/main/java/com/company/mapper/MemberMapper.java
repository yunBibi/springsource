package com.company.mapper;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;

public interface MemberMapper {
	// insert는 0/1을 리턴하기 때문에 int
	public int insert(MemberDTO memberDTO);
	public MemberDTO selectById(String userid);
	public LoginDTO login(LoginDTO loginDto);
	public int update(ChangeDTO chaDto);
	public int delete(LoginDTO leaveDto);   // 괄호 안에 무엇을 넘길 지는 작성한 sql문을 바탕으로 생각
	// public ind delete(@Param("userid") String userid, @Param("password")  String password);  개별로 넘기는 것도 가능
}
