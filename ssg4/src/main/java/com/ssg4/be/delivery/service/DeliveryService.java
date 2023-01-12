package com.ssg4.be.delivery.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.WriterException;
import com.ssg4.be.delivery.mapper.DeliveryMapper;
import com.ssg4.be.delivery.model.DeliveryDto;
import com.ssg4.be.delivery.model.ExcelData;
import com.ssg4.be.qr.service.QrService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService {
	private final DeliveryMapper mapper;
	private final QrService qrService;

	/**
	 * 회원 목록 조회
	 */
	public List<DeliveryDto> findAllDelivery(Map<String, String> param) {
		
		String type = param.get("type");
		switch (type) {
		case "customer": //사용자
			param.put("type", "D.MNO");
			break;
		case "rider": //배송기사
			param.put("type", "D.RIDER_ID");
			break;
		case "seller": //판매자
			param.put("type", "D.REG_ID");
			break;

		default:
			break;
		}
		
		return mapper.findAllDelivery(param);
	}
	
	public DeliveryDto findDeliveryByDno(int dno) {
		return mapper.findDeliveryByDno(dno);
	}

	/**
	 * 엑셀 데이터로 운송장정보 저장 및 qr생성
	 */
	public void insertExcelInfo(MultipartFile file) throws IOException, WriterException {
		List<ExcelData> dataList = new ArrayList<>();
		//파일 확장자 추출
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (!extension.equals("xlsx") && !extension.equals("xls")) {
			throw new IOException("엑셀파일만 업로드 해주세요.");
		}

		Workbook workbook = null;

		if (extension.equals("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
		} else if (extension.equals("xls")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		}

		Sheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

			Row row = worksheet.getRow(i);

			ExcelData data = new ExcelData();

			data.setDno((int) row.getCell(0).getNumericCellValue());
			data.setTrackingNo((int) row.getCell(1).getNumericCellValue());

			dataList.add(data);
		}

		// 엑셀에 저장된 운송장번호 저장 및 qr생성
		for(ExcelData excel : dataList){
			log.debug("excel>>>{},{}",excel.getDno(),excel.getTrackingNo());
			int dno = excel.getDno();
			// 배송번호가 있고, 운송장번호 저장되지 않은 배송정보 조회
			int cnt = mapper.findDeliveryCntByDno(dno);
			if(cnt == 1){
				// 운송장번호 저장
				mapper.updateTrackingNo(excel);
				// qrcode 생성
				qrService.createQrcode(dno);
			}
		}
	}

	/**
	 * 수령 완료
	 */
	public void updateReceivedYn(int dno) {
		mapper.updateReceivedYn(dno);
	}
	/**
	 * 반품 신청
	 */
	public void updateReturnYn(int dno) {
		mapper.updateReturnYn(dno);
	}

	/**
	 * 배송 완료
	 */
	public void updateDeliveryYn(int dno) {
		mapper.updateDeliveryYn(dno);
	}

	/**
	 * 문자 발송
	 */
	public void updateSendingYn(int dno) {
		mapper.updateSendingYn(dno);
	}
}
