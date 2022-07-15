package com.astar.order_admin.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astar.order_admin.data.ImgInfoVO;
import com.astar.order_admin.mapper.ImgMapper;

@Service
public class ImgService {
    @Autowired ImgMapper img_mapper;
    public Map<String,Object> ImgList(Integer type, Integer offset, Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(offset<=0) offset =0;
        List<ImgInfoVO> img_list = img_mapper.selectImgInfoBySeq(type,offset,seq);
        resultMap.put("status", true);
        resultMap.put("img_list", img_list);
        return resultMap;
    }
    
    public Map<String,Object> ImgInfo(ImgInfoVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();


        resultMap.put("status", true);
        return resultMap;
    }
    
    public Map<String,Object> ImgToRestaurantInfo(Integer img_seq, Integer ri_seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();


        resultMap.put("status", true);
        return resultMap;
    }

}
