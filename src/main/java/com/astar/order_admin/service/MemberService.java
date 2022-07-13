package com.astar.order_admin.service;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astar.order_admin.data.MemberInfoVO;
import com.astar.order_admin.data.RestaurantInfoVO;
import com.astar.order_admin.mapper.MemberMapper;

@Service
public class MemberService {
    @Autowired MemberMapper member_mapper;
    //세션에 유저가 로그인 했는지 확인
    public Boolean isUserInSession(MemberInfoVO user) {
        if(user==null) return false;
        return true;
    }
    
    //사업자 회원인지 확인
    public Boolean isValidGrade(MemberInfoVO user) {
        if(user.getMi_grade()==1) return false;
        return true;
    }
    
    //로그인여부 사업자회원여부 검사
    public Map<String,Object> isUserValidUser(MemberInfoVO user) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(!isUserInSession(user)){
            resultMap.put("status", false);
            resultMap.put("messege", "로그인이 필요한 서비스입니다.");
            return resultMap;
        }
        else if(!isValidGrade(user)){
            resultMap.put("status", false);
            resultMap.put("messege", "사업자 회원만 이용가능한 서비스입니다.");
            return resultMap;
        }
        resultMap.put("status", true);
        return resultMap;
    }
    
    //세션유저seq와 영업장의유저seq가 일치하는지 검사
    public Map<String,Object> isValidOwner(MemberInfoVO user, RestaurantInfoVO data) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(user.getMi_seq()!=data.getRi_mi_seq()) {            
            resultMap.put("status", false);
            resultMap.put("messege", "비정상적인 접근입니다.");
            return resultMap;
        }
        resultMap.put("status", true);
        return resultMap;
    }
}