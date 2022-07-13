package com.astar.order_admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.astar.order_admin.data.MemberInfoVO;
import com.astar.order_admin.mapper.MemberMapper;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired MemberMapper member_mapper;
    @GetMapping("/login")    //로그인페이지
    public String getMemberLogin(){
        return "/member/login";
    }
    @GetMapping("/join")    //회원가입페이지
    public String getMemberJoin(){
        return "/member/join";
    }
    @GetMapping("/info")    //회원정보 조회(로그인 후 남아있는 세션으로 부터 정보 조회)
    public String getMemberInfo(HttpSession session, Model model){
        MemberInfoVO user = (MemberInfoVO)session.getAttribute("user");
        if(user==null) return "redirect:/"; //세션에 회원정보가 없으면 로그인페이지로 리턴
        model.addAttribute("user",user);
        return "/member/info";
    }
    @GetMapping("/logout")  //로그아웃 세션정보 초기화
    public String getMemberLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
