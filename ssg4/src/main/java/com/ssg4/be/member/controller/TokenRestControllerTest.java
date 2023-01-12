package com.ssg4.be.member.controller;

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
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/token")
public class TokenRestControllerTest {

	private final JwtProvider jwtProvider;

	@GetMapping(value = "/test")
	public String createToken() throws Exception {
		return "테스트 성공!";
	}

	/**
	 * 토큰 생성 컨트롤러
	 * @param userId 유저 ID
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/create/{userId}")
	public TokenResponse createToken(@PathVariable("userId") String userId) throws Exception {
		String token = jwtProvider.createToken(userId); // 토큰 생성
		Claims claims = jwtProvider.parseJwtToken("Bearer " + token); // 토큰 검증

		TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(),
			claims.getIssuedAt().toString(), claims.getExpiration().toString());
		TokenResponse tokenResponse = new TokenResponse("200", "OK", tokenDataResponse);

		return tokenResponse;
	}

	/**
	 * 토큰 인증 컨트롤러
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/check")
	public TokenResponseNoData checkToken(@RequestHeader(value = "Authorization") String token) throws Exception {
		Claims claims = jwtProvider.parseJwtToken(token);

		TokenResponseNoData tokenResponseNoData = new TokenResponseNoData("200", "success");
		return tokenResponseNoData;
	}
}
