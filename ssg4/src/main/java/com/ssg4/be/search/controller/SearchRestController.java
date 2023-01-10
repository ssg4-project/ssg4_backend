package com.ssg4.be.search.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssg4.be.search.service.ProductListService;

import io.swagger.annotations.Api;

@Api(tags = "getProductList: 판매자 물품 리스트, getProductConsumer : 고객 물품 리스트, getProductRider : 택배 기사 물품조회")
@RestController
public class SearchRestController {

	@Autowired
	ProductListService productListservice;
	
	/*
	 * 판매자 물품 리스트 조회
	 */
	@GetMapping("/getProductList")
	public List<Map<String, Object>> getProductList(@RequestParam String param) {
		List<Map<String, Object>> list = productListservice.selectAllProduct(param);
		return list;
	}
	
	/*
	 * 고객 물품 리스트 조회
	 */
	@GetMapping("/getProductConsumer")
	public List<Map<String, Object>> getProductConsumer(@RequestParam String param){
		List<Map<String, Object>> list = productListservice.selectAllConsumer(param);
		return list;
	}
	
	/*
	 * 택배 기사 물품 리스트 조회
	 * */
	@GetMapping("/getProductRider")
	public List<Map<String, Object>> getProductRider(@RequestParam String param){
		List<Map<String, Object>> list = productListservice.selectAllRider(param);
		return list;
	}
	
	/*
	 * 택배 기사 물품 디테일
	 * */
	@GetMapping("/riderDetail")
	public Map<String, Object> getRiderDetail(@RequestParam int param){
		Map<String, Object> map = productListservice.riderDetail(param);
		return map;
	}

}
