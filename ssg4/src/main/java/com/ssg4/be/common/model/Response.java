package com.ssg4.be.common.model;

import com.ssg4.be.common.app.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Response<T> {
	private ResponseCode code;
	private String msg;
	private T data;

	public static <T> Response<T> ok() {
		return Response.<T>builder().code(ResponseCode.OK).build();
	}

	public static <T> Response<T> ok(T data) {
		return Response.<T>builder().code(ResponseCode.OK).data(data).build();
	}

	public static <T> Response<T> ok(T data, String msg) {
		return Response.<T>builder().code(ResponseCode.OK).data(data).msg(msg).build();
	}

	public static <T> Response<T> okWithMsg(String msg) {
		return Response.<T>builder().code(ResponseCode.OK).msg(msg).build();
	}

	public static <T> Response<T> error(ResponseCode code, T data, String msg) {
		return Response.<T>builder().code(code).data(data).msg(msg).build();
	}

	public static <T> Response<T> error(ResponseCode code, String msg) {
		return Response.<T>builder().code(code).msg(msg).build();
	}

	public static <T> Response<T> error(T data, String msg) {
		return Response.<T>builder().code(ResponseCode.INTERNAL_SERVER_ERROR).msg(msg).build();
	}
}