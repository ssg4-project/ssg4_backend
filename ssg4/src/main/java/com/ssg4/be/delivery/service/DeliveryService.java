package com.ssg4.be.delivery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssg4.be.delivery.mapper.DeliveryMapper;
import com.ssg4.be.delivery.model.DeliveryDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService {

	private final DeliveryMapper mapper;

	/**
	 * 회원 목록 조회
	 */
	public List<DeliveryDto> findAllDelivery(String type, String no) {
		
		Map<String, String> param = new HashMap<>();
		
		switch (type) {
		case "customer": //사용자
			param.put("type", "d.mno");
			break;
		case "rider": //배송기사
			param.put("type","d.rider_id");
			break;
		case "seller": //판매자
			param.put("type", "d.reg_id");
			break;

		default:
			break;
		}
		param.put("no", no);
		
		return mapper.findAllDelivery(param);
	}
	
	public DeliveryDto findDeliveryByDno(int dno) {
		return mapper.findDeliveryByDno(dno);
	}
}
