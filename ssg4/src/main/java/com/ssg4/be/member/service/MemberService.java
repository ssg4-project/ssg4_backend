package com.ssg4.be.member.service;

import com.ssg4.be.member.mapper.MemberMapper;
import com.ssg4.be.member.model.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberMapper mapper;

	/**
	 * 회원 목록 조회
	 */
	public List<MemberVo> findAllMember() {
		return mapper.findAllMember();
	}
}
