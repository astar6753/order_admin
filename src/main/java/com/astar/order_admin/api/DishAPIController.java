package com.astar.order_admin.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.RestaurantViewVO;
import com.astar.order_admin.data.response.BlockVO;
import com.astar.order_admin.data.response.DescVO;
import com.astar.order_admin.data.response.DishVO;
import com.astar.order_admin.data.response.DishViewVO;
import com.astar.order_admin.data.request.BlockRequestVO;
import com.astar.order_admin.data.request.DescRequestVO;
import com.astar.order_admin.data.request.DishRequestVO;
import com.astar.order_admin.mapper.DishMapper;


@RestController
@RequestMapping("/api")
public class DishAPIController {
    @Autowired DishMapper dish_mapper;

    @GetMapping("/dish/list/all") 
    public Map<String,Object> getManageDishView(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        //responseVO생성
        DishViewVO resultData = new DishViewVO();
        
        //1티어 레스토랑정보 파라미터ri_seq로 조회
        RestaurantViewVO restaurantView = dish_mapper.selectRestaurantView(seq);
        //resultData에 대분류 레스토랑정보 삽입
        resultData.setRestaurantView(restaurantView);
        
        
        List<DishVO> resultDishList = new ArrayList<DishVO>();

        //set한값 확인용출력: 레스토랑정보만 담겨있는상태
        // System.out.println("1|"+resultData);

        //ri_seq로 2티어 음식정보list 조회 dishList를 세팅
        List<DishVO> dishList = dish_mapper.selectDishInfoByRestaurant(seq);
        //dishList를 1개씩 조회하면서
        //di_seq번호로 3티어blockList정보 꺼내옴
        for(DishVO temp2 : dishList){
            List<BlockVO> blockList = dish_mapper.selectOptionBlockInfoByDish(temp2.getDi_seq());
            
            List<BlockVO> resultBlockList = new ArrayList<BlockVO>();
            //blocklist를 1개씩 조회하면서
            //opt_seq번호로 4티어descList정보 꺼내옴
            for(BlockVO temp3 : blockList){
                
                //4티어정보 리스트 생성 및 옮겨담기
                List<DescVO> descList = dish_mapper.selectOptionDescInfoByBlock(temp3.getOpt_seq());
                temp3.setDescList(descList);

                //3티어 정보 가져와서 result리스트에 추가                
                resultBlockList.add(temp3);
                // System.out.println("3|"+temp3);
            }

            //2티어정보 가져와서 result리스트에 추가
            resultDishList.add(temp2);              //나머지 자투리 정보
            temp2.setBlockList(resultBlockList);    //위쪽for문에서 만들어서 담아둔 3티어list
            // System.out.println("2|"+temp2);

            resultData.setDishList(resultDishList);
        }

        // System.out.println(resultData);
        resultMap.put("key", resultData);
        return resultMap;
    }

    
    @PostMapping("/dish/list/all") 
    public Map<String,Object> postManageDishView(@RequestBody DishRequestVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
            
        dish_mapper.insertDishInfoByRestaurant(data);
        
        for(BlockRequestVO temp : data.getBlockList()){
            temp.setOpt_di_seq(data.getDi_seq());
            dish_mapper.insertOptionBlockInfoByDish(temp);
            
            for(DescRequestVO temp2:temp.getDescList()){
                temp2.setDesc_opt_seq(temp.getOpt_seq());
                dish_mapper.insertOptionDescByBlock(temp2);
            }
        }

        resultMap.put("status", true);
        resultMap.put("message","음식정보 및 옵션정보가 추가되었습니다.");
        return resultMap;
    }
}

// 메뉴정보
// 메뉴이름/가격/설명/이미지
// >블록정보List
//  블록이름/개수/필수여부
//   >옵션List
//     옵션이름/가격
// >블록정보List
//  블록이름/개수/필수여부
//   >옵션List
//     옵션이름/가격

// insert시
// 메뉴정보insert
// 메뉴번호 propertykey받아와서

// for블록리스트 조회하면서
// 메뉴번호 세팅하고
// 하나씩insert

// 블록번호 propertykey받아와서

// for옵션리스트 조회하면서
// 블론번호세팅하고 insert
