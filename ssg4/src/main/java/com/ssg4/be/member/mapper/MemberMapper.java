package com.ssg4.be.member.mapper;

import com.ssg4.be.member.model.LoginDto;
import com.ssg4.be.member.model.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
	List<MemberVo> findAllMember();

	MemberVo findMemberByIdAndPw(LoginDto param);
}
