package com.ssg4.be.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssg4.be.member.mapper.MemberMapper;
import com.ssg4.be.member.model.LoginDto;

@Service
public class MemberService {

	@Autowired
	MemberMapper mapper;
	
	public List<Map<String, String>> selectMember() {
		return mapper.selectMemberList();
	}
	
	public Map<String, String> login(LoginDto param){
		return mapper.selectMember(param);
	}
	
}
