package com.backend.com.service;

import java.util.HashMap;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;  // 반드시 필요
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.com.dto.request.LoginDto;
import com.backend.com.entity.Member;
import com.backend.com.entity.QMember;
import com.backend.com.repository.MemberRepository;
import com.backend.com.utils.JwtUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LoginService {
	private final MemberRepository memberRepository;
	private final JwtUtil jwtUtils;
 	private final PasswordEncoder encoder;  // 이미 생성자 주입으로 가져옴
    private StringRedisTemplate redisTemplate;
    private final JPAQueryFactory jpaQuertyFactory;
 	
 	
 	public HashMap<String,Object> loginAction(LoginDto login) {
		long refreshTokenTime = 604800;
		String encodePasswd =encoder.encode("123123123");
		log.info("encodePasswd::::::::::::::::::::::::::::::"+encodePasswd);

		Member member = memberRepository.findByMemberId(login.getUserId());
		if (member == null) {
			throw new UsernameNotFoundException("존재하지 않는 멤버입니다.(" + login.getUserId() + ")");
		}
		
		if (!encoder.matches(login.getPassword(), member.getPassword())) {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}

		QMember qmember = QMember.member;
		Member result = jpaQuertyFactory.selectFrom(qmember)
						.where(login.getUserId() !=null ? qmember.memberId.eq(login.getUserId()) : null).fetchOne();
		// 암호화된 password를 디코딩한 값과 입력한 패스워드 값이 다르면 null 반환
		HashMap<String, Object> retCheck = new HashMap<>();
		if(!result.getMemberId().isBlank()) {
			String accessToken = jwtUtils.createAccessToken(member);
			String refreshToken = jwtUtils.createRefreshToken(refreshTokenTime);
	        retCheck.put("accessToken", accessToken);
	        retCheck.put("refreshToken", refreshToken);
	        retCheck.put("member", member);
		}
		return retCheck;
	}
}
