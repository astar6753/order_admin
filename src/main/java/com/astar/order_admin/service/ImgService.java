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
    public Integer imgTypeToNumber(String type){
        Integer type_no = 0;
        if(type.equals("restaurant")) type_no = 1;
        else if(type.equals("dish")) type_no = 2;
        else if(type.equals("member")) type_no = 3;
        return type_no;
    }

    public Map<String,Object> ImgList(String type, Integer offset, Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(offset<=0) offset =0;
        
        List<ImgInfoVO> img_list = img_mapper.selectImgInfoBySeq(imgTypeToNumber(type),offset,seq);
        resultMap.put("status", true);
        resultMap.put("img_list", img_list);
        return resultMap;
    }
    
    public Map<String,Object> ImgInfo(ImgInfoVO data, String type){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        data.setImg_type(imgTypeToNumber(type));
        img_mapper.insertImgInfo(data);
        resultMap.put("status", true);
        resultMap.put("message", "이미지가 추가되었습니다.");
        return resultMap;
    }
    

    public Map<String,Object> deleteImgInfoByImgSeq(Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        img_mapper.deleteImgInfoByImgSeq(seq);
        resultMap.put("status", true);
        resultMap.put("message", "이미지가 삭제되었습니다.");
        return resultMap;
    }

    public Map<String,Object> updateImgToInfoByType(Integer img_seq, Integer seq, String type){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        Integer type_no = imgTypeToNumber(type);
        if(type_no==0) {
            resultMap.put("status", false);
            resultMap.put("message", "맞지 않는 타입입니다.");
        }
        else if(type_no==1) img_mapper.updateImgToRestaurantInfo(img_seq,seq);
        else if(type_no==2) img_mapper.updateImgToDishInfo(img_seq,seq);
        else if(type_no==3) img_mapper.updateImgToMemberInfo(img_seq,seq);
        resultMap.put("status", true);
        resultMap.put("message", "이미지가 등록되었습니다.");
        return resultMap;
    }

}
