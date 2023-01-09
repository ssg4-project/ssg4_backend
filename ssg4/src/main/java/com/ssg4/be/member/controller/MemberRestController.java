package com.ssg4.be.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssg4.be.member.mapper.MemberMapper;


@RestController
public class MemberRestController {

	@Autowired
	MemberMapper mapper;
	
	@GetMapping("/test")
	public List<Map<String, String>> test() {
		List<Map<String, String>> list = mapper.selectMember();
		return list;
	}
}
