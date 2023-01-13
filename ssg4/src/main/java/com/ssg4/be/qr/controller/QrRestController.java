package com.ssg4.be.qr.controller;

import com.ssg4.be.qr.service.QrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api(tags = "Qrcode API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/qr")
@CrossOrigin("*")
public class QrRestController {

    private final QrService qrService;
    
    /**
     * Qrcode 조회
     */
    @ApiOperation(
            value = "Qrcode 이미지 조회"
            , notes = "배송번호를 통해 저장된 Qrcode를 조회한다.")
    @ApiImplicitParam(
            name = "dno"
            , value = "배송번호"
            , required = true
            , dataType = "string")
    @GetMapping("/display")
    public String display(@RequestParam("dno") int dno) {
    	String path = qrService.findFilePathByDno(dno);
    	
    	return path;
    }

}
