package com.backend.com.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.backend.com.entrypoint.CustomAuthenticationEntryPoint;
import com.backend.com.filter.JwtAuthFilter;
import com.backend.com.handler.CustomAccessDeniedHandler;
import com.backend.com.service.CustomUserDetailsService;
import com.backend.com.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMethodSecurity
@Slf4j
@RequiredArgsConstructor 
public class SecurityConfig {
	private final CustomUserDetailsService customerUserDetailsService;
	private final JwtUtil jwtUtil;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private static final String [] AUTH_WHITELIST= {
    	"/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html","/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html"
    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf(csrf-> csrf.disable());
    	http.cors(cors-> cors.configurationSource(
    		request -> {
    			CorsConfiguration corsConfig = new org.springframework.web.cors.CorsConfiguration();
    		     corsConfig.setAllowedOrigins(List.of("http://localhost:5173")); // 클라이언트 URL
                 corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // 허용할 HTTP 메소드
                 corsConfig.setAllowedHeaders(List.of("*")); // 허용할 헤더
                 corsConfig.setAllowCredentials(true); // 인증이 필요한 경우
                 return corsConfig;
    		}));
    	//세션관리 상태없음으로 구성 
    	http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	http.formLogin(form -> form.disable());
    	http.httpBasic(AbstractHttpConfigurer::disable);
     	http.addFilterBefore(new JwtAuthFilter(customerUserDetailsService,jwtUtil), UsernamePasswordAuthenticationFilter.class);
 
    	
    	http.authorizeHttpRequests(
    			authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll()
    			.requestMatchers("/api/v2/login/loginAction").anonymous()
    			.requestMatchers("/api/v2/auth/menuAuth").hasAnyRole("ADMIN", "USER")
     	        .anyRequest().authenticated()
    	);
       	
    	//예외처리
    	http.exceptionHandling(
    			exceptionHandling -> exceptionHandling
    				.authenticationEntryPoint(authenticationEntryPoint)
    				.accessDeniedHandler(accessDeniedHandler)
    	);
    	return http.build();
    	
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   
}
