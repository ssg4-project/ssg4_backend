package com.ssg4.be.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	List<Map<String, String>> selectMember();
}
