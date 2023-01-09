package com.ssg4.be.member.mapper;

import com.ssg4.be.member.model.LoginDto;
import com.ssg4.be.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

	int countMember();

	List<Member> findAllMember();

	Member findMemberByIdAndPw(LoginDto param);
}
