package com.ssg4.be.member.controller;

import com.ssg4.be.member.model.MemberVo;
import com.ssg4.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/member")
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/test")
    public List<MemberVo> test() {
        List<MemberVo> list = memberService.findAllMember();
        return list;
    }



}
