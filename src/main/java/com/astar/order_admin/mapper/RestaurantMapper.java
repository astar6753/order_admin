package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.RestaurantCateImgViewVO;
import com.astar.order_admin.data.RestaurantInfoVO;

@Mapper
public interface RestaurantMapper {

    public void insertRestaurantInfo(RestaurantInfoVO data);

    public List<RestaurantCateImgViewVO> selectRestaurantCateImgViewBySeq(Integer seq, Integer offset);
    public void deleteRestaurantInfoBySeq(Integer ri_seq, Integer ri_mi_seq);
    public void updateRestaurantInfo(RestaurantInfoVO data);










    public List<RestaurantInfoVO> selectRestaurantInfo(String keyword, Integer offset);
    public Integer selectRestaurantInfoPageCnt(String keyword);
    public Integer selectRestaurantInfoTotalCnt(String keyword);


}
