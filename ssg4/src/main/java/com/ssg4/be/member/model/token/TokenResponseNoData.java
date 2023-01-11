package com.ssg4.be.member.model.token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponseNoData<T> {
	private String code;
	private String msg;
}