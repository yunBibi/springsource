package com.company.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.company.service"})
@MapperScan("com.company.mapper")
public class BoardConfig {
	
	@Bean // 인스턴스를 생성하고 스프링 컨테이너가 관리
	public DataSource dataSource() {
		HikariConfig hikafiConfig = new HikariConfig();
		hikafiConfig.setDriverClassName("");
		hikafiConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikafiConfig.setUsername("c##java");
		hikafiConfig.setPassword("12345");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikafiConfig);
		return hikariDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		sqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();
	}
}
