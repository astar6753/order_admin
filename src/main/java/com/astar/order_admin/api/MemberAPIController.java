package com.astar.order_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.MemberInfoVO;
import com.astar.order_admin.mapper.MemberMapper;
import com.astar.order_admin.utils.AESAlgorithm;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @Autowired MemberMapper member_mapper;

    //회원가입
    @PutMapping("/join")
    public ResponseEntity<Map<String,Object>> putMemberInfo(@RequestBody MemberInfoVO data, HttpSession session) throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        data.setMi_pwd(AESAlgorithm.Encrypt(data.getMi_pwd()));
        try {
            member_mapper.insertMemberInfo(data);  //회원정보추가하고 반환된 유저정보 값을 저장
        } catch (DuplicateKeyException e) {         //아이디PK키 중복시 catch
            resultMap.put("status",false);
            resultMap.put("message",data.getMi_pwd()+"은(는) 이미 등록된 아이디입니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        
        resultMap.put("status",true);
        resultMap.put("message","회원 정보를 추가했습니다.");
        MemberInfoVO user = member_mapper.selectMemberInfoBySeq(data.getMi_seq());    //반환된 mi_seq값으로 방금 회원가입한 유저정보를 찾아서
        session.setAttribute("user",user);                                      //세션에 등록
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> postAdminLogin(@RequestBody MemberInfoVO data, HttpSession session) throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        data.setMi_pwd(AESAlgorithm.Encrypt(data.getMi_pwd())); //사용자로부터 입력받은 비밀번호 정보를 암호화
        MemberInfoVO user = member_mapper.selectMemberLogin(data);
        //사용자로부터 입력받은 id pwd로부터 회원정보가 null일 경우 status false리턴
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        else if(user.getMi_grade()==1){
            resultMap.put("status", false);
            resultMap.put("message", "사업자 회원만 이용가능한 서비스입니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        resultMap.put("status", true);
        resultMap.put("message", user.getMi_name()+"님 방문을 환영합니다.");
        session.setAttribute("user",user);  //로그인성공시 세션에 user정보 세팅
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }

    //아이디 중복검사
    @GetMapping("/chk")
    public Boolean getMemberJoinIdChk(@RequestParam String id) {
        return member_mapper.isDuplicatedId(id);
    }

    //회원정보
    @GetMapping("/info")
    public ResponseEntity<Map<String,Object>> getMemberInfo(HttpSession session) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        
        //세션의 유저정보가 null일 경우 status false리턴
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "정상적인 접근 경로가 아닙니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        
        resultMap.put("status",true);
        resultMap.put("message", "현재 로그인한 회원의 정보입니다.");
        resultMap.put("member_info",member_mapper.selectMemberInfoBySeq(user.getMi_seq()));
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }

    //회원정보 수정
    @PatchMapping("/update")
    public ResponseEntity<Map<String,Object>> postMethodName(@RequestBody MemberInfoVO data, HttpSession session) throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");

        //세션의 유저정보가 null일 경우 status false리턴
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "정상적인 접근 경로가 아닙니다.");
            return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.BAD_REQUEST);
        }
        data.setMi_seq(user.getMi_seq());                       //현재 세션의 유저정보를 데이터에 삽입
        data.setOrigin_pwd(AESAlgorithm.Encrypt(data.getOrigin_pwd())); //기존비밀번호 암호화해서 DB확인
        data.setMi_pwd(AESAlgorithm.Encrypt(data.getMi_pwd())); //사용자로부터 입력받은 비밀번호 정보를 암호화
        System.out.println(data);
        member_mapper.updateMemberInfo(data);   //update실행
        session.setAttribute("user",user);      //세션의 유저정보도 동시에 업데이트
        resultMap.put("status",true);
        resultMap.put("message","회원 정보를 수정했습니다.");
        return new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
    }
    


}
