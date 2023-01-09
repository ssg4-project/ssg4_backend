package com.ssg4.be.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssg4.be.member.model.LoginDto;
import com.ssg4.be.member.service.MemberService;

import io.swagger.annotations.ApiParam;


@RestController
public class MemberRestController {

	@Autowired
	MemberService memberService;
	
	@GetMapping("/test")
	public List<Map<String, String>> test() {
		List<Map<String, String>> list = memberService.selectMember();
		return list;
	}
	
	//로그인
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody LoginDto param) {
		return memberService.login(param);
	}
	
}
