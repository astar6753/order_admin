package com.astar.order_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.astar.order_admin.mapper.DishMapper;


@Controller
@RequestMapping("/manage")
public class ManageController {
    @Autowired DishMapper dish_mapper;
    
    @GetMapping("/customer")  //고객 주문 이력 조회
    public String getManageCustomer() {
        return "/manage/customer";
    }
    @GetMapping("/restaurant")  //영업장 관리
    public String getManageRestaurant() {
        return "/manage/restaurant";
    }
    @GetMapping("/order")  //주문 관리
    public String getManageOrder() {
        return "/manage/order";
    }    
    @GetMapping("/dish")  //주문 관리
    public String getManageDish(@RequestParam @Nullable Integer seq, Model model) {
        model.addAttribute("ri_seq", seq);
        


        return "/manage/dish";
    }
    
}
