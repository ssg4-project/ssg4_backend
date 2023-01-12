package com.ssg4.be.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ssg4.be.config.jwt.JwtProvider;
import com.ssg4.be.member.model.MemberVo;
import com.ssg4.be.member.service.MemberService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        // 요청 로깅 처리
        log.info("[REQUEST] >> [{}] {}", request.getMethod(), request.getRequestURI());

        // 토큰의 회원 정보 조회
        MemberVo member = memberService.getMemberFromRequest(request);

        // 회원 ID 가 존재하지 않는 경우
        if (member.getId() == null)
            return false;
        // 회원 타입이 없는 경우
        if (member.getMemberType() == null)
            return false;
        // 최종 성공
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
