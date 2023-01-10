package com.ssg4.be.member.controller;

import com.ssg4.be.member.model.LoginDto;
import com.ssg4.be.member.model.MemberVo;
import com.ssg4.be.member.service.AuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "인증 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {
    private final AuthService authService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<MemberVo> login(HttpServletRequest req,
                                          @RequestBody LoginDto param) {
        try {
            MemberVo member = authService.login(req, param);
            log.info("로그인 성공! {}", member.getMno());
            return ResponseEntity.status(200)
                    .body(member);
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e.toString());
            return ResponseEntity.status(400).body(new MemberVo());
        }
    }
}
