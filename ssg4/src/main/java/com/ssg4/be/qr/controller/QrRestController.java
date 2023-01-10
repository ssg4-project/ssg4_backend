package com.ssg4.be.qr.controller;

import com.ssg4.be.qr.service.QrService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "Qrcode API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/qr")
public class QrRestController {

    private final QrService qrService;

    /**
     * 로그인
     */
    @PostMapping("/create")
    public Map<String, String> createQrcode(int dno) {

        try {
            return qrService.createQrcode(dno);
        } catch (Exception e) {
            log.error("QrRestController >> createQrcode >> {}",e.toString());
            throw new RuntimeException(e);
        }
    }

}
