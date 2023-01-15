package com.ssg4.be.delivery.controller;

import com.ssg4.be.delivery.service.DeliveryService;
import com.ssg4.be.member.model.MemberVo;
import com.ssg4.be.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "배송 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/delivery")
@CrossOrigin("*")
public class DeliveryRestController {

    private final DeliveryService deliveryService;
	private final MemberService memberService;

    @ApiOperation(
            value = "배송리스트 조회"
            , notes = "사용자, 택배기사, 판매자 별 배송리스트를 조회한다.")
    @GetMapping("/list")
    public Map<String, Object> findAllDelivery(@RequestParam String type, @RequestParam String no, @RequestParam(required = false) String schTime) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("type", type);
        param.put("no", no);
        if(schTime != null && !schTime.equals("")) {
        	param.put("schTime", schTime);
        }
    	
    	Map<String, Object> list = deliveryService.findAllDelivery(param);
        return list;
    }

    @ApiOperation(
            value = "배송정보 조회"
            , notes = "배송번호를 통해 배송정보를 조회한다.")
    @GetMapping("/info")
    public Map<String,Object> findDeliveryByDno(HttpServletRequest req, @RequestParam int dno) {
    	int mno = 0;
    	try {
    		MemberVo member = memberService.getMemberFromRequest(req);
    		mno = member.getMno();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	Map<String,Object> info = deliveryService.findDeliveryByDno(dno, mno);
    	
        return info;
    }

    @ApiOperation(
            value = "엑셀 정보 등록"
            , notes = "엑셀파일에 있는 운송장번호를 DB에 저장한다.")
    @PostMapping(value = "/excel/read")
    public void readExcel(@RequestPart("file") MultipartFile file){
        try {
            deliveryService.insertExcelInfo(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @ApiOperation(
            value = "수령 여부 수정"
            , notes = "배송수령여부를 수정한다.")
    @PostMapping("/updateReceivedYn")
    public void updateReceivedYn(@RequestBody int dno){
        deliveryService.updateReceivedYn(dno);
    }
    
    @ApiOperation(
            value = "반품 여부 수정"
            , notes = "반품여부를 수정한다.")
    @PostMapping("/updateReturnYn")
    public void updateReturnYn(@RequestBody int dno){
        deliveryService.updateReturnYn(dno);
    }

    @ApiOperation(
            value = "배송완료 여부 수정"
            , notes = "배송완료여부를 수정한다.")
    @PostMapping("/updateDeliveryYn")
    public void updateDeliveryYn(@RequestBody int dno){
        deliveryService.updateDeliveryYn(dno);
    }

    @ApiOperation(
            value = "문자발송 여부 수정"
            , notes = "문자발송 여부를 수정한다.")
    @PostMapping("/updateSendingYn")
    public void updateSendingYn(@RequestBody int dno){
        deliveryService.updateSendingYn(dno);
    }

}
