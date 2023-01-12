package com.ssg4.be.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssg4.be.common.model.Response;
import com.ssg4.be.common.utils.JsonUtil;
import com.ssg4.be.config.jwt.JwtProvider;
import com.ssg4.be.member.model.LoginDto;
import com.ssg4.be.member.model.MemberVo;
import com.ssg4.be.member.model.token.TokenDataResponse;
import com.ssg4.be.member.service.AuthService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "인증 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {

    private final JwtProvider jwtProvider;
    private final AuthService authService;

    /**
     * 로그인
     */
    @ApiOperation("로그인")
    @PostMapping("/login")
    public Response<TokenDataResponse> login(HttpServletRequest req,
        @RequestBody LoginDto param) {
        MemberVo member;
        try {
            member = authService.login(req, param);
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e.toString());
            return null;
        }

        String json = JsonUtil.getJsonFromPojo(member);
        String token = jwtProvider.createToken(json); // 토큰 생성
        Claims claims = jwtProvider.parseJwtToken("Bearer " + token); // 토큰 검증

        TokenDataResponse tokenDataResponse = new TokenDataResponse(token, claims.getSubject(),
            claims.getIssuedAt().toString(), claims.getExpiration().toString());
        return Response.ok(tokenDataResponse);
    }
}
