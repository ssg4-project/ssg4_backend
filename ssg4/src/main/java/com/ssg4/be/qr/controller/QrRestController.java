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
    @GetMapping("/display")
    public ResponseEntity<Resource> display(@RequestParam("dno") int dno) {
    	String path = qrService.findFilePathByDno(dno);
    	
    	if(path == null) {
    		// TODO. 나중에 해야할 부분
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
