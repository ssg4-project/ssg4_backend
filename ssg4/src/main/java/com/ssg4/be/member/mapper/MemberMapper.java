package com.ssg4.be.member.mapper;

import com.ssg4.be.member.model.LoginDto;
import com.ssg4.be.member.model.MemberVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	MemberVo findMemberByIdAndPw(LoginDto param);
}
