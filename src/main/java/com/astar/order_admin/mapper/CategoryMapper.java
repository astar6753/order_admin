package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.CategoryInfoVO;

@Mapper
public interface CategoryMapper {
    public List<CategoryInfoVO> getCategoryInfoByName(String keyword);
    public List<CategoryInfoVO> getCategoryInfoList(Integer Offset);
    public Integer getCategoryInfoTotalCnt();
    public Integer getCategoryInfoPageCnt();
}
