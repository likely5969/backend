package com.backend.com.utils;


import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.backend.com.entity.Member;
import com.backend.com.enums.EnumStorage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
@Slf4j 
@Component
public class JwtUtil {
	private final Key key;
	private final long accessTokenExpTime;
	  
	public JwtUtil(
			@Value("${jwt.secret}") String secretKey,
			@Value("${jwt.expiration_time}") long accessTokenExpTime
			)
	 {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
	 }
	
	  /**
     * Access Token 생성
     * @param member
     * @return Access Token String
     */
    public String createAccessToken(Member member) {
        return createToken(member, accessTokenExpTime);
    }

    private String createToken(Member member, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("memberId", member.getMemberId());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    public String createRefreshToken(long expireTime) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    
    

    /**
     * JWT Claims 추출
     * @param accessToken
     * @return JWT Claims
     */
    public Claims parseClaims(String accessToken) {
        try {
            Claims claim =  Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
            return claim;
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    
    public String getUserId(String token) {
        return parseClaims(token).get("memberId", String.class);
    }

    
    

    /**
     * JWT 검증
     * @param token
     * @return IsValidate
     */
    public HashMap<String,Object> validateToken(String token) {
    	HashMap<String,Object> retCheck = new HashMap<String,Object>();
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            retCheck.put("retSign", "Y");
            retCheck.put("retMsg", "정상");
             
            		
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            retCheck.put("retSign", "N");
            retCheck.put("retMsg", "Invalid JWT Token");
            
        
        } catch (ExpiredJwtException e) {
            retCheck.put("retSign", "N");
            retCheck.put("retMsg", "Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            retCheck.put("retSign", "N");
            retCheck.put("retMsg", "Unsupported JWT Token");
            
        } catch (IllegalArgumentException e) {
            retCheck.put("retSign", "N");
            retCheck.put("retMsg", "JWT claims string is empty.");
        }
        return retCheck;
    }
    
    
    public int  getRemainingExpiration(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                                .setSigningKey(key)
                                .build()
                                .parseClaimsJws(token)
                                .getBody();

            Date expiration = claims.getExpiration();
            long now = System.currentTimeMillis();
            long remainingTime = expiration.getTime() - now;
            if (remainingTime <= 0) {
                return 0; // 만료됨
            }
            return (int) (remainingTime / 1000); // 남은 시간(초 단위)
        } catch (ExpiredJwtException e) {
            return 0; // 유효하지 않은 토큰
        } catch (Exception e) {
            log.error("토큰 검증 중 오류 발생", e);
            return -1; // 유효하지 않은 토큰
        }
    }
    
    
    
}
