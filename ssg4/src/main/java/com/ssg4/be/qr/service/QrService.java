package com.ssg4.be.qr.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ssg4.be.qr.mapper.QrMapper;
import com.ssg4.be.qr.model.QrFileVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class QrService {
    private final QrMapper qrMapper;

    private static final String QR_CODE_IMAGE_PATH = "D:\\ssg4\\qr\\";

    public Map<String, String> createQrcode(int dno) throws WriterException, IOException {
        Map<String, String> resultMap = new HashMap<>();

        //1. 현재 dno로 생성된 qrcode가 있는지 조회
        int qrCnt = qrMapper.findQrCntByDno(dno);
        if(qrCnt > 0){ //현재 생성된 qrcode 이미지 파일이 있다면 리턴
            resultMap.put("msg","이미 생성된 QR코드가 있습니다.");
            return resultMap;
        }

        //2. qrcode 생성 및 이미지 저장
        int width = 20;
        int height = 20;
        BitMatrix matrix = new MultiFormatWriter().encode("url", BarcodeFormat.QR_CODE, width, height);

        String filePath = QR_CODE_IMAGE_PATH + dno + ".png";
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);

        resultMap.put("msg","QR코드가 생성 되었습니다.");
        
        //3. qr 이미지 파일 db 저장
        QrFileVO qr = new QrFileVO();
        qr.setFilePath(filePath);
        qr.setDno(dno);
        qrMapper.insertQrFile(qr);

        return resultMap;
    }
    
    public String findFilePathByFno(int fno) {
		return qrMapper.findFilePathByFno(fno);
    }

}
