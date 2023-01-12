package com.ssg4.be.member.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssg4.be.config.jwt.JwtProvider;
import com.ssg4.be.member.model.token.TokenDataResponse;
import com.ssg4.be.member.model.token.TokenResponse;
import com.ssg4.be.member.model.token.TokenResponseNoData;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "토큰 테스트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/test/token")
@CrossOrigin("*")
public class TokenRestControllerTest {

	private final JwtProvider jwtProvider;

	/**
	 * 토큰 생성 컨트롤러
	 * @param subject 유저 ID
	 */
	@ApiOperation("임시 토큰 생성")
	@GetMapping(value = "/create/{subject}")
	public TokenResponse createToken(@PathVariable("subject") String subject) throws Exception {
		String token = jwtProvider.createToken(subject); // 토큰 생성
		Claims claims = jwtProvider.parseJwtToken("Bearer " + token); // 토큰 검증

		TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(),
			claims.getIssuedAt().toString(), claims.getExpiration().toString());
		TokenResponse tokenResponse = new TokenResponse("200", "OK", tokenDataResponse);

		return tokenResponse;
	}

	/**
	 * 토큰 인증 컨트롤러
	 * @param token 토큰 Key
	 */
	@ApiOperation("입력된 토큰 유효성 검사")
	@GetMapping(value = "/check")
	public TokenResponseNoData checkToken(@RequestHeader(value = "Authorization") String token) throws Exception {
		Claims claims = jwtProvider.parseJwtToken(token);

		TokenResponseNoData tokenResponseNoData = new TokenResponseNoData("200", "success");
		return tokenResponseNoData;
	}
}
