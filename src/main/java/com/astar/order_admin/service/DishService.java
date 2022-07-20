package com.astar.order_admin.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astar.order_admin.data.DishInfoALLVO;
import com.astar.order_admin.data.DishInfoVO;
import com.astar.order_admin.data.OptionBlockInfoVO;
import com.astar.order_admin.data.OptionDesctiptionInfoVO;
import com.astar.order_admin.data.Response.DishViewResponse;
import com.astar.order_admin.mapper.DishMapper;

@Service
public class DishService {
    @Autowired DishMapper dish_mapper;
    
    public Map<String,Object> viewManageDish(Integer ri_seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        
        DishViewResponse response = new DishViewResponse();
        

        List<DishInfoVO> dishList = new ArrayList<DishInfoVO>();
        List<OptionBlockInfoVO> blockList = new ArrayList<OptionBlockInfoVO>();
        List<OptionDesctiptionInfoVO> descList = new ArrayList<OptionDesctiptionInfoVO>();

        //input//ri_seq 리퀘스트파람
        //output//dishcount 메뉴개수
        //output//dishinfo 메뉴 개별 정보 di_seq
        dishList = dish_mapper.selectDishInfoByRestaurant(ri_seq);

        //input// 메뉴정보에서 for문으로 di_seq 뽑아서 넣고
        //output// di_seq에 해당하는 옵션박스 정보 가져오기
        for(int i = 0; i<dishList.size(); i++){
            Integer di_seq = dishList.get(i).getDi_seq(); //메뉴리스트에서 메뉴번호 뽑고
            blockList=(dish_mapper.selectOptionBlockInfoByDish(di_seq)); //메뉴번호로 옵션박스정보조회해서 리스트에 추가
        }

        //input//opt_seq 옵션블록번호
        //output//옵션박스 정보
        data = dish_mapper.countOptionDescInfoByBlock();
        data = dish_mapper.selectOptionDescInfoByBlock();


        resultMap.put("status",true);
        resultMap.put("dishList",dishList);
        resultMap.put("blockList",blockList);
        resultMap.put("descList",descList);
        return resultMap;
    }
    public List<DishInfoVO> selectDishInfo(Integer ri_seq){
        
        List<DishInfoVO> dishList = new ArrayList<DishInfoVO>();

    }



    
}

/*메뉴조회
response{
    dish[1]{이름 가격 etc 
            opt[1]{제목 최대선택수 필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격}
                    desc[3]{옵션이름 가격}
            }
            opt[2]{제목 최대선택수 필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격}
                    desc[3]{옵션이름 가격}
            }
    }
    dish[2]{이름 가격 etc 
            opt[1]{제목 최대선택수 필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격} 
                    desc[3]{옵션이름 가격}
            }
    }
}
*/