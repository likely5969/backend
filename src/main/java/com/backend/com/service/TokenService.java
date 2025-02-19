package com.backend.com.service;

import java.time.Duration;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.backend.com.dto.request.LoginDto;
import com.backend.com.exception.GeneralException;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class TokenService {
    private final RedisTemplate<String, String> redisTemplate;

    public TokenService(@Qualifier("redisTemplate") RedisTemplate<String, String> redisTemplate) {
       this.redisTemplate = redisTemplate;
    }
	
	public void setttingTokens(HttpServletResponse response,LoginDto login, HashMap<String, Object> tokenMap) {
		String accessToken = tokenMap.get("accessToken") != null ? String.valueOf(tokenMap.get("accessToken")) : "";
		String refreshToken = tokenMap.get("refreshToken") != null ? String.valueOf(tokenMap.get("refreshToken")) : "";
		Duration refreshTime = Duration.ofDays(7);
		if(accessToken.length() >1  && refreshToken.length() > 1) {

			String key = "refreshToken:"+login.getUserId();
			redisTemplate.opsForValue().set(key, refreshToken,refreshTime);
			response.setHeader("Authorization", "Bearer "+accessToken);
	        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken)
	                .httpOnly(true)
	                .secure(true)
	                .path("/")
	                .maxAge(refreshTime) // Refresh Token 7일 유지
	                .build();
	        response.addHeader("Set-Cookie", refreshTokenCookie.toString());
		}
		else {
			throw new GeneralException("토큰 만들다가 에러");
			
		}
 	}
	//리프레쉬 토큰 
	public boolean validateRefreshTokenRedis(String userId, String refreshToken) {
		String key = "refreshToken:"+userId;
		String redisRefreshToken  = redisTemplate.opsForValue().get(key);
		return refreshToken.equals(redisRefreshToken);
	}

	
	// Refresh Token 삭제 (로그아웃 시)
    public void deleteRefreshToken(String userId) {
        String key = "refreshToken:" + userId;
        redisTemplate.delete(key);
    }


}
