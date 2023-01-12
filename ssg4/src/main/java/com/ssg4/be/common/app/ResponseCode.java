package com.ssg4.be.common.app;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

	OK("성공"),
	INTERNAL_SERVER_ERROR("서버 에러"),
	;

	private final String message;
}
