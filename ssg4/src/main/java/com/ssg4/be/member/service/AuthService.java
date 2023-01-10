package com.ssg4.be.member.service;

import com.ssg4.be.member.mapper.MemberMapper;
import com.ssg4.be.member.model.LoginDto;
import com.ssg4.be.member.model.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberMapper mapper;

    /**
     * 로그인
     */
    public MemberVo login(HttpServletRequest req, LoginDto param) throws Exception {
        MemberVo member = mapper.findMemberByIdAndPw(param);
        if (member == null)
            throw new RuntimeException("회원 정보가 존재하지 않습니다.");
        HttpSession session = req.getSession();
        session.setAttribute("member", member);
        return member;
    }
}
