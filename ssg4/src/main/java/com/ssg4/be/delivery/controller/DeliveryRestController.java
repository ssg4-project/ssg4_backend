package com.ssg4.be.delivery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssg4.be.delivery.model.DeliveryDto;
import com.ssg4.be.delivery.service.DeliveryService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "배송 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/delivery")
@CrossOrigin("*")
public class DeliveryRestController {

    private final DeliveryService deliveryService;

    @GetMapping("/list")
    public List<DeliveryDto> findAllDelivery(@RequestParam String type, @RequestParam String no, @RequestParam(required = false) String schTime) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("type", type);
        param.put("no", no);
        if(schTime != null) {
        	param.put("schTime", schTime);
        }
    	
    	List<DeliveryDto> list = deliveryService.findAllDelivery(param);
        return list;
    }

    @GetMapping("/info")
    public DeliveryDto findDeliveryByDno(@RequestParam int dno) {
        DeliveryDto info = deliveryService.findDeliveryByDno(dno);
        return info;
    }

    @PostMapping(value = "/excel/read")
    public void readExcel(@RequestPart("file") MultipartFile file){
        try {
            deliveryService.insertExcelInfo(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/updateReceivedYn")
    public void updateReceivedYn(@RequestBody int dno){
        deliveryService.updateReceivedYn(dno);
    }
    @PostMapping("/updateReturnYn")
    public void updateReturnYn(@RequestBody int dno){
        deliveryService.updateReturnYn(dno);
    }

    @PostMapping("/updateDeliveryYn")
    public void updateDeliveryYn(@RequestBody int dno){
        deliveryService.updateDeliveryYn(dno);
    }

    @PostMapping("/updateSendingYn")
    public void updateSendingYn(@RequestBody int dno){
        deliveryService.updateSendingYn(dno);
    }

}
