package com.ssg4.be.update.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssg4.be.update.service.UpdateService;

@RestController
public class UpdateRestController {

	@Autowired
	UpdateService service;
	/*
	 * 배송완료시간 변경, 널일 때만 가능, 운송장 번호로 업데이트
	 * */
	@PutMapping("/putfinaldeli")
	public void finish_deli(@RequestParam String time, @RequestParam int post) {
		service.finish_deli(time, post);
	}
	
	/*
	 * 수령시간 변경, 널일 때만 가능, 운송장 번호로 업데이트
	 * */
	@PutMapping("/putgetdeli")
	public void get_deli(@RequestParam String time, @RequestParam int post) {
		service.get_deli(time, post);
	}
	
	/*
	 * 반송여부 변경
	 * */
	@PutMapping("/putReturn")
	public void returnProduct(int post) {
		service.returnProduct(post);
	}
	
	/*
	 * 메세지 전송여부 변경
	 * */
	@PutMapping("/putMsg")
	public void msgProduct(int post) {
		service.msgProduct(post);
	}
	
}
