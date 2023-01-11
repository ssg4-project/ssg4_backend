package com.ssg4.be.delivery.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssg4.be.delivery.model.DeliveryDto;

@Mapper
public interface DeliveryMapper {
	
	List<DeliveryDto> findAllDelivery(Map<String, String> param);
	
	DeliveryDto findDeliveryByDno(int dno);
}
