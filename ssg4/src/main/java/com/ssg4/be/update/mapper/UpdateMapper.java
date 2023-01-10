package com.ssg4.be.update.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateMapper {
	void finish_deli(String time, int post); 
	void get_deli(String time, int post); 
}
