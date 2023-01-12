package com.ssg4.be.member.model.token;

import com.ssg4.be.member.model.MemberVo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDataResponse {
	private String token;
	private MemberVo member;
	private String issued_time;
	private String expired_time;
}