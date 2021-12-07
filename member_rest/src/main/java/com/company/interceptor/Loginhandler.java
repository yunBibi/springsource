package com.company.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.company.domain.LoginDTO;

public class Loginhandler implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// session 정보를 확인해서 로그인 정보가 있다면 원하는 컨트롤러로 진입
		HttpSession session = request.getSession();
		LoginDTO loginDto = (LoginDTO) session.getAttribute("loginDto");
		
		if(loginDto!=null) {
			return true;    // /member/changePwd 컨트롤러 진입
		}
		
		// session이 없다면 로그인 페이지로 이동
		response.sendRedirect("/member/signin");
		return false;
	}
}
