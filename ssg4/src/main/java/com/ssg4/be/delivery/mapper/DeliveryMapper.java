package com.ssg4.be.delivery.mapper;

import com.ssg4.be.delivery.model.DeliveryDto;
import com.ssg4.be.delivery.model.ExcelData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeliveryMapper {
	
	List<DeliveryDto> findAllDelivery(Map<String, String> param);
	DeliveryDto findDeliveryByDno(int dno);
	int findDeliveryCntByDno(int dno);
	int updateTrackingNo(ExcelData excelData);
	int updateReceivedYn(int dno);
	int updateReturnYn(int dno);
	int updateDeliveryYn(int dno);
	int updateSendingYn(int dno);
}
