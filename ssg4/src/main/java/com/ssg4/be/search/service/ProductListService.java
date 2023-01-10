package com.ssg4.be.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssg4.be.search.mapper.ProductListMapper;

@Service
public class ProductListService {

	@Autowired
	ProductListMapper mapper;
	
	public List<Map<String, Object>> selectAllProduct(String param) {
		return mapper.selectAllProduct(param);
	}
	
	public List<Map<String, Object>> selectAllConsumer(String param) {
		return mapper.selectAllConsumer(param);
	}
	
	public List<Map<String, Object>> selectAllRider(String param) {
		return mapper.selectAllRider(param);
	}
	
	public Map<String, Object> riderDetail(int param){
		return mapper.riderDetail(param);
	}
}
