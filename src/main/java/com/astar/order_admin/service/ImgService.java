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
    public Integer imgTypeNumber(String type){
        Integer type_no = 0;
        if(type.equals("restaurant")) type_no = 1;
        else if(type.equals("dish")) type_no = 2;
        else if(type.equals("member")) type_no = 3;
        return type_no;
    }

    public Map<String,Object> ImgList(String type, Integer offset, Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(offset<=0) offset =0;
        
        List<ImgInfoVO> img_list = img_mapper.selectImgInfoBySeq(imgTypeNumber(type),offset,seq);
        resultMap.put("status", true);
        resultMap.put("img_list", img_list);
        return resultMap;
    }
    
    public Map<String,Object> ImgInfo(ImgInfoVO data, String type){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        data.setImg_type(imgTypeNumber(type));
        img_mapper.insertImgInfo(data);
        resultMap.put("status", true);
        resultMap.put("message", "이미지가 등록되었습니다.");
        return resultMap;
    }
    

    public Map<String,Object> delImgInfo(Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        img_mapper.deleteImgInfo(seq);
        resultMap.put("status", true);
        resultMap.put("message", "이미지가 삭제되었습니다.");
        return resultMap;
    }
    


    public Map<String,Object> ImgToRestaurantInfo(Integer img_seq, Integer ri_seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();


        resultMap.put("status", true);
        return resultMap;
    }

}
