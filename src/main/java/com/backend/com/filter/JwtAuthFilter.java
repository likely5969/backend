package com.backend.com.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.com.codes.HttpCodes;
import com.backend.com.enums.EnumStorage;
import com.backend.com.response.common.ErrorResponseDto;
import com.backend.com.security.CustomUserDetails;
import com.backend.com.service.CustomUserDetailsService;
import com.backend.com.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

	private final CustomUserDetailsService customUserDetailsService;
	private final JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.substring(7);
			if (jwtUtil.validateToken(token).get("retSign").toString().equals(EnumStorage.Y.toString())) {
				int remainSecond = jwtUtil.getRemainingExpiration(token);
				if (remainSecond < 0) {
				  sendErrorResponse(response, HttpCodes.TOKEN_EXPIRED);
                  return;
				}
				String userId = jwtUtil.getUserId(token);
				CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userId);
				if (customUserDetails != null) {
					UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							customUserDetails, null, customUserDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
					// 인증 정보 확인
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					if (authentication != null) {
						// 인증 정보가 제대로 설정되었는지 확인
						log.info("인증된 사용자: " + authentication.getName());
						log.info("권한: " + authentication.getAuthorities());
					} else {
						// JWT 토큰이 유효하지 않거나 만료된 경우
						sendErrorResponse(response, HttpCodes.INVALID_TOKEN);
                        return;
					}
				} else {
					 sendErrorResponse(response, HttpCodes.INVALID_TOKEN);
                     return;
				}

			} else {
				sendErrorResponse(response, HttpCodes.INVALID_TOKEN);
				return;

			}
		} else {
			// JWT 토큰이 유효하지 않거나 만료된 경우
			sendErrorResponse(response, HttpCodes.MISSING_AUTH_HEADER);
			return;

		}

		filterChain.doFilter(request, response);
	}

	private void sendErrorResponse(HttpServletResponse response, HttpCodes httpCode) throws IOException {
		response.setStatus(httpCode.getHttpStatus().value());
		response.setContentType("application/json");

		ErrorResponseDto errorResponse = ErrorResponseDto.of(httpCode);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonResponse = objectMapper.writeValueAsString(errorResponse);

		response.getWriter().write(jsonResponse);
	}

}
