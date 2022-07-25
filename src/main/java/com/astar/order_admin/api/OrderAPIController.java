package com.astar.order_admin.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.response.order_detail.EachOrderVO;
import com.astar.order_admin.data.response.order_detail.OptionDetailVO;
import com.astar.order_admin.data.response.order_detail.OrderDetailResponseVO;
import com.astar.order_admin.mapper.OrderMapper;

@RestController
@RequestMapping("/api")
public class OrderAPIController {
    @Autowired OrderMapper order_mapper;

    @GetMapping("/order/list/{user_seq}")
    public Map<String,Object> getManageOrderView(
        @PathVariable Integer user_seq, 
        @RequestParam @Nullable String keyword, 
        @RequestParam @Nullable String start_dt,@RequestParam @Nullable String end_dt, 
        @RequestParam @Nullable Integer page
        ){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(page==null) page=1;
        Integer offset = (page-1)*20;
        System.out.println(user_seq);
        System.out.println(keyword);
        resultMap.put("OrderList", order_mapper.selectOrderRespoenseListByUserSeq(user_seq, keyword, start_dt, end_dt, offset));
        resultMap.put("TotalCnt", order_mapper.selectOrderRespoenseTotalCntByUserSeq(user_seq, keyword, start_dt, end_dt));
        resultMap.put("TotalPage", order_mapper.selectOrderRespoenseTotalPageByUserSeq(user_seq, keyword, start_dt, end_dt));
        return resultMap;
    }

    @GetMapping("/order/detail/{order_seq}")
    public Map<String,Object> getManageOrderView(
        @PathVariable Integer order_seq
        ){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        //1티어 데이터 조회 총주문(주문번호,주문상태,배달주소,주문일시,고객아이디,고객이름,고객전화번호,업장이름,업장주소)
        OrderDetailResponseVO resultData = order_mapper.selectOrderDetailResponseByOrderSeq(order_seq);
        //2티어 데이터 조회 개별주문(메뉴이름 메뉴가격 주문개수)
        List<EachOrderVO> eachOrderList = order_mapper.selectEachOrderByOrderSeq(order_seq);
        
        Integer total_price = 0;
        
        //2티어 메뉴번호로부터
        //3티어 선택옵션정보 조회(옵션이름 옵션가격)
        for(int i=0; i<eachOrderList.size(); i++){
            List<OptionDetailVO> optionList = order_mapper.selectOptionDetailByEachSeq(eachOrderList.get(i).getEach_seq());
            eachOrderList.get(i).setOptionList(optionList);
            
            Integer option_price = 0;
            //옵션가격정보 다 더해서 옵션가격option_price 계산하고
            for(OptionDetailVO temp : optionList){
                option_price += temp.getDesc_price();
            }
            //단품가격=(메뉴가격(+옵션가격) * 주문개수)
            Integer each_price = (eachOrderList.get(i).getDi_price()+option_price)*(eachOrderList.get(i).getEach_quantity());
            eachOrderList.get(i).setEach_price(each_price);

            total_price += eachOrderList.get(i).getEach_price();
        }
        //총가격 = 각 메뉴 가격총합

        resultData.setTotal_price(total_price);
        resultData.setEachOrderList(eachOrderList);
        
        resultMap.put("orderDetail", resultData);
        return resultMap;
    }



}
