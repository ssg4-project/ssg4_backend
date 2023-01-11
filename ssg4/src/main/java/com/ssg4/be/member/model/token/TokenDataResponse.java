package com.ssg4.be.member.model.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDataResponse {
	private String token;
	private String subject;
	private String issued_time;
	private String expired_time;
}