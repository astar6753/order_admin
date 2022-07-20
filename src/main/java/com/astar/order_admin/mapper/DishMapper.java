package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.DishInfoVO;
import com.astar.order_admin.data.OptionBlockInfoVO;
import com.astar.order_admin.data.OptionDesctiptionInfoVO;

@Mapper
public interface DishMapper {
    public Integer countDishInfoByRestaurant(Integer ri_seq);
    public List<DishInfoVO> selectDishInfoByRestaurant(Integer ri_seq);
    public Integer countOptionBlockInfoByDish(Integer di_seq);
    public List<OptionBlockInfoVO> selectOptionBlockInfoByDish(Integer di_seq);
    public Integer countOptionDescInfoByBlock(Integer opt_seq);
    public List<OptionDesctiptionInfoVO> selectOptionDescInfoByBlock(Integer opt_seq);
}
