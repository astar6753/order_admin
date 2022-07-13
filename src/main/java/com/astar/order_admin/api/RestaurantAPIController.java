package com.astar.order_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.MemberInfoVO;
import com.astar.order_admin.data.RestaurantInfoVO;
import com.astar.order_admin.mapper.RestaurantMapper;
import com.astar.order_admin.service.MemberService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantAPIController {
    @Autowired RestaurantMapper rest_mapper;
    @Autowired MemberService member_service;
    //사업자회원의 영업장 추가
    //chk//유저로그인/사업자회원/세션유저eq=영업장주인seq/상호명중복검사
    @PutMapping("/insert")
    public ResponseEntity<Map<String,Object>> putRestaurantInfo(
        @RequestBody RestaurantInfoVO data, HttpSession session
        ){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>(); 
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");

        if(!((Boolean)member_service.isUserValidUser(user).get("status"))){
            return new ResponseEntity<Map<String,Object>>(
            member_service.isUserValidUser(user),HttpStatus.BAD_REQUEST
            );
        }
        if(!((Boolean)member_service.isValidOwner(user,data).get("status"))){
            return new ResponseEntity<Map<String,Object>>(
                member_service.isValidOwner(user,data),HttpStatus.BAD_REQUEST
            );
        }        
        try {
            rest_mapper.insertRestaurantInfo(data);
        } catch (DuplicateKeyException e) {   
            resultMap.put("status",false);
            resultMap.put("message",data.getRi_name()+"은(는) 이미 등록된 상호 명입니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        resultMap.put("message", "영업장이 정상적으로 등록되었습니다.");
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }

    //회원 자신의 영업장 조회  10개씩 1페이지 조회 
    //chk//유저로그인/사업자회원
    @GetMapping("/user/list") 
    public ResponseEntity<Map<String,Object>> getUserRestaurant(
        @RequestParam @Nullable Integer page, HttpSession session
    ) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        
        if(!((Boolean)member_service.isUserValidUser(user).get("status"))){
            return new ResponseEntity<Map<String,Object>>(
            member_service.isUserValidUser(user),HttpStatus.BAD_REQUEST
            );
        }

        if(page==null)page=1;
        resultMap.put("list",rest_mapper.selectRestaurantInfoBySeq(user.getMi_seq(), (page-1)/10)); //현재 로그인한 회원의 번호로 영업장 조회
        resultMap.put("message", "현재 로그인한 사업자 회원의 식당정보입니다.");
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }

    //회원 자신의 영업장 삭제
    //chk//유저로그인/사업자회원
    @DeleteMapping("/delete") 
    public ResponseEntity<Map<String,Object>> deleteUserRestaurant(
        @RequestParam Integer seq, HttpSession session
    ) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");

        if(!((Boolean)member_service.isUserValidUser(user).get("status"))){
            return new ResponseEntity<Map<String,Object>>(
            member_service.isUserValidUser(user),HttpStatus.BAD_REQUEST
            );
        }

        rest_mapper.deleteRestaurantInfoBySeq(seq,user.getMi_seq());
        resultMap.put("message", "선택한 영업장 정보가 삭제되었습니다.");
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }

    //회원 자신의 영업장 정보 수정
    //chk//유저로그인/사업자회원
    @PatchMapping("/update") 
    public ResponseEntity<Map<String,Object>> updateUserRestaurant(
        @RequestBody RestaurantInfoVO data, HttpSession session
    ) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        data.setRi_mi_seq(user.getMi_seq());    //현재 세션의 유저seq를 세팅

        if(!((Boolean)member_service.isUserValidUser(user).get("status"))){
            return new ResponseEntity<Map<String,Object>>(
            member_service.isUserValidUser(user),HttpStatus.BAD_REQUEST
            );
        }
        try {
            rest_mapper.updateRestaurantInfo(data);
        } catch (Exception e) { //세션 유저정보의 영업장이 아님
            resultMap.put("status", false);
            resultMap.put("message", "정상적인 접근경로가 아닙니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        resultMap.put("status", true);
        resultMap.put("message", "선택한 영업장 정보가 수정되었습니다.");
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }




    
    @GetMapping("/list")        //식당 {검색어} 전체조회 10개씩 1페이지로
    public ResponseEntity<Map<String,Object>> postRestautantInfo(
        @RequestBody MemberInfoVO data,
        @RequestParam @Nullable String keyword,
        @RequestParam Integer page,
        HttpSession session
    ) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        //세션체크 서비스단으로
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "음식점을 추가하려면 로그인이 필요합니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        else if(user.getMi_grade()==1){
            resultMap.put("status", false);
            resultMap.put("message", "사업자 회원만 이용가능한 서비스입니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }

        if(page==null) page=1;

        resultMap.put("message", keyword+"로 검색한 식당 정보입니다.");
        resultMap.put("keyword", keyword);  //검색어 유지를 위해 키워드 반환
        resultMap.put("currentPage", page);   //현재 페이지수 반환
        resultMap.put("totalPage", rest_mapper.selectRestaurantInfoPageCnt(keyword)); //총 페이지수
        resultMap.put("totalCnt", rest_mapper.selectRestaurantInfoTotalCnt(keyword)); //식당이름검색 총 숫자
        resultMap.put("asd", rest_mapper.selectRestaurantInfo(keyword,(page-1)*10)); //식당이름으로 검색한 식당정보 검색어 없을시 전체정보 조회

        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }
    
}
