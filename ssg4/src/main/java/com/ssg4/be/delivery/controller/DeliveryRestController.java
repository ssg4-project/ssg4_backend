package com.ssg4.be.delivery.controller;

import com.ssg4.be.delivery.model.DeliveryDto;
import com.ssg4.be.delivery.service.DeliveryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "배송 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/delivery")
public class DeliveryRestController {

    private final DeliveryService deliveryService;

    @GetMapping("/list")
    public List<DeliveryDto> findAllDelivery(@RequestParam String type, @RequestParam String no) {
        List<DeliveryDto> list = deliveryService.findAllDelivery(type, no);
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
