package com.ssg4.be.search.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductListMapper {
	List<Map<String, Object>> selectAllProduct(String param);
	List<Map<String, Object>> selectAllConsumer(String param);
	List<Map<String, Object>> selectAllRider(String param);
	Map<String, Object> riderDetail(int param);
}
