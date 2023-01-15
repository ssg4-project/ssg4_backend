package com.ssg4.be.delivery.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDto {
	
	private int dno; 
	private int customerNo; 
	private String id;
	private String name;
	private String nickname;
	private String tel;
	private String address;
	private String postNo;
	
	private int riderId;
	private String riderName;
	
	private int sellerId;
	private String sellerName;
	private String sellerTel;
	private String sellerPostNo;
	private String sellerAddress;
	
	private String courier;
	private String productName;
	private int trackingNo;
	private String deliveryState;
	private String deliverySchTime;
	private String deliveryTime;
	private String receivedTime;
	private String receivedYn;
	private String returnYn;
	private String sendingYn;
	private String regDate;
	private String delYn;
}
