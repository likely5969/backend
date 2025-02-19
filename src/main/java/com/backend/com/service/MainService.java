package com.backend.com.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.com.dto.RoleDto;
import com.backend.com.entity.Member;
import com.backend.com.entity.Role;
import com.backend.com.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {
	
	private final MemberRepository memberRepository;
	

}
