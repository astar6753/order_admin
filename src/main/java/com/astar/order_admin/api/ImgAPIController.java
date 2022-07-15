package com.astar.order_admin.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.MemberInfoVO;
import com.astar.order_admin.service.ImgService;

@RestController
@RequestMapping("/api/img")
public class ImgAPIController {
    @Autowired ImgService img_service;

    //본인이 등록한 이미지 조회 (이미지편집박스에서 화면출력용 1영업점 2음식 3프로필)
    // http://localhost:8888/api/img/1?seq=1
    @GetMapping("/{type}/{offset}")
    public ResponseEntity<Map<String,Object>> getImgList(
        @PathVariable Integer type, @PathVariable Integer offset,
        @RequestParam Integer seq,
        HttpSession session
        ){
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        return new ResponseEntity<Map<String,Object>>(img_service.ImgList(type,offset,1),HttpStatus.OK);
    }
    
    //음식점 이미지 편집



    
}
