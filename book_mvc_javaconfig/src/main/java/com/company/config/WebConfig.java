package com.company.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// 한글처리
	@Override
	protected Filter[] getServletFilters() {
		// boolean forceEncoding : true(기존 인코딩 설정 값이 있어도 현재 설정값으로 사용)
		CharacterEncodingFilter encodingFilter = 
				new CharacterEncodingFilter("utf-8", true);
		
		return new Filter[] {encodingFilter};
	}
}
