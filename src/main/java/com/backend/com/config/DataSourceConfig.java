package com.backend.com.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	@Bean
	@Primary
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/admin?serverTimezone=Asia/Seoul&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
	 @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
	        return builder
	            .dataSource(dataSource())
	            .packages("com.backend.com.entity") // 엔티티 패키지 경로
	            .persistenceUnit("default")
	            .properties(getVendorProperties())
	            .build();
	    }	

	    private Map<String, Object> getVendorProperties() {
	        Map<String, Object> properties = new HashMap<>();
	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); // Dialect 설정
	        return properties;
	    }
	 
}
