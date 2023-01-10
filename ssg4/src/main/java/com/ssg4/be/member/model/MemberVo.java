package com.ssg4.be.member.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVo {
    private int mno;
    private String id;
    private String pw;
    private String name;
    private String memberType;
    private String nickname;
    private String tel;
}
