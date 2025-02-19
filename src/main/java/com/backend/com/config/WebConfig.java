package com.backend.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.backend.com.interceptor.ProjectInterceptor;
import com.backend.com.service.CommonService;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{
	 private final ProjectInterceptor projectInterceptor;
 	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        // 특정 URL 패턴에 대해 인터셉터를 등록
	        registry.addInterceptor(projectInterceptor)
	                .addPathPatterns("/api/v2/**/**") // URL 패턴 지정
	        		.excludePathPatterns("/api/v2/login/loginAction");
	    }  
}
