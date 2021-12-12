package com.company.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@MapperScan("com.company.mapper")
@ComponentScan("com.company.service")
@Configuration
public class TransactionConfig {

	@Bean
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
	
	@Bean
	public DataSourceTransactionManager txtManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}










