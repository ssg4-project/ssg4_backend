package com.ssg4.be.qr.controller;

import com.ssg4.be.qr.service.QrService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Api(tags = "Qrcode API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/qr")
public class QrRestController {

    private final QrService qrService;

    /**
     * qrcode 생성
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
    
    /**
     * Qrcode 조회
     */
    @GetMapping("/display")
    public ResponseEntity<Resource> display(@RequestParam("fno") int fno) {
    	String path = qrService.findFilePathByFno(fno);
    	
    	if(path == null) {
    		//TODO.
    	}
    	Resource resource = new FileSystemResource(path);
    	if(!resource.exists()) 
    		return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
    	HttpHeaders header = new HttpHeaders();
    	Path filePath = null;
    	try{
    		filePath = Paths.get(path);
    		header.add("Content-type", Files.probeContentType(filePath));
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

}
