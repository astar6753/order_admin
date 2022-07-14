package com.astar.order_admin.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.astar.order_admin.data.CategoryInfoVO;
import com.astar.order_admin.mapper.CategoryMapper;

@Service
public class CategoryService {
    @Autowired CategoryMapper cate_mapper;

    //카테고리 검색해서 근사치 3개만 가져옴
    public Map<String,Object> searchCategoryByName(String keyword){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        // 검색어 없을시 표시안함
        if(keyword==null || keyword.trim().equals("")) {
            resultMap.put("status", false);
            return resultMap;
        }
        
        //근사치 검색을 위해 앞뒤%%로 키워드 변형하고 검색해서 결과 list
        keyword = "%"+keyword+"%";
        List<CategoryInfoVO> cate_search = cate_mapper.getCategoryInfoByName(keyword);
        
        //결과값 없을시 표시안함
        if(cate_search==null){
            resultMap.put("status", false);
            return resultMap;
        }
        resultMap.put("status", true);
        resultMap.put("cate_search", cate_search);
        return resultMap;
    }

    //카테고리 전체 목록
    public Map<String,Object> selectCategoryList(@Nullable Integer page){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        //페이지값 없을시 1페이지 고정 10개씩 보여주기
        if(page==null) page=1;
        List<CategoryInfoVO> cate_list = cate_mapper.getCategoryInfoList((page-1)*10);
        resultMap.put("status", true);
        resultMap.put("current_page", page);    //현재 페이지
        resultMap.put("cate_list", cate_list);  //전체 카테고리 리스트
        resultMap.put("cate_total_cnt", cate_mapper.getCategoryInfoTotalCnt()); //총 cnt수
        resultMap.put("cate_page_cnt", cate_mapper.getCategoryInfoPageCnt());   //페이지 올림(total/10)
        return resultMap;
    }


}
