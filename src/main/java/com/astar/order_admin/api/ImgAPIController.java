package com.astar.order_admin.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.ImgInfoVO;
import com.astar.order_admin.data.MemberInfoVO;
import com.astar.order_admin.service.ImgService;

@RestController
@RequestMapping("/api/user/img")
public class ImgAPIController {
    @Autowired ImgService img_service;

    //세션의 로그인한 유저가 본인이 등록한 이미지 조회 (이미지편집박스에서 화면출력용 1영업점 2음식 3프로필)
    // http://localhost:8888/api/img/1/0?seq=1
    @GetMapping("/{type}/{offset}")
    public ResponseEntity<Map<String,Object>> getImgList(
        HttpSession session,
        @PathVariable String type, @PathVariable Integer offset
        ){
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        return new ResponseEntity<Map<String,Object>>(img_service.ImgList(type,offset,user.getMi_seq()),HttpStatus.OK);
    }

    //이미치 추가시 DB img_info테이블에 등록
    @PutMapping("/{type}")
    public ResponseEntity<Map<String,Object>> putImgInfo(
        HttpSession session, @PathVariable String type, @RequestBody ImgInfoVO data
        ){
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        data.setImg_mi_seq(user.getMi_seq());
        return new ResponseEntity<Map<String,Object>>(img_service.ImgInfo(data,type),HttpStatus.OK);
    }
    
    @DeleteMapping
    public ResponseEntity<Map<String,Object>> deleteImgInfo(
        @RequestParam Integer seq
        ){
        return new ResponseEntity<Map<String,Object>>(img_service.delImgInfo(seq),HttpStatus.OK);
    }
    



    
}
