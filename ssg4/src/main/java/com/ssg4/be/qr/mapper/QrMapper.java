package com.ssg4.be.qr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssg4.be.qr.model.QrFileVO;

@Mapper
public interface QrMapper {

	int findQrCntByDno(int dno);
	
	int insertQrFile(QrFileVO qr);
	
	String findFilePathByDno(int dno);
}
