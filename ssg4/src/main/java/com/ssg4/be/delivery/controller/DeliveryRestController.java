package com.ssg4.be.delivery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
//    @PostMapping("/updateReturnYn")
//    public void updateReturnYn()

}
