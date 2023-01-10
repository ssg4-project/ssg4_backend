package com.ssg4.be.update.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssg4.be.update.mapper.UpdateMapper;

@Service
public class UpdateService {
	@Autowired
	UpdateMapper mapper;
	
	public void finish_deli(String time, int post) {
		mapper.finish_deli(time, post);
		return;
	}
	public void get_deli(String time, int post) {
		mapper.get_deli(time, post);
		return;
	}
}
