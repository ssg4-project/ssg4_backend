package com.ssg4.be.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ssg4.be.common.utils.JsonUtil;
import com.ssg4.be.config.jwt.JwtProvider;
import com.ssg4.be.member.model.MemberVo;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final JwtProvider jwtProvider;

	/**
	 * Token 으로 회원 정보 조회
	 */
	public MemberVo getMemberFromRequest(HttpServletRequest request) throws Exception {
		Claims claims = jwtProvider.getClaimsFromRequest(request);
		String subject = claims.getSubject();
		return JsonUtil.getPojoFromJson(subject, MemberVo.class);
	}
}
