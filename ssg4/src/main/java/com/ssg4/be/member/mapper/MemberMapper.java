package com.ssg4.be.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssg4.be.member.model.LoginDto;

@Mapper
public interface MemberMapper {

	List<Map<String, String>> selectMemberList();
	
	Map<String, String> selectMember(LoginDto param);
}
