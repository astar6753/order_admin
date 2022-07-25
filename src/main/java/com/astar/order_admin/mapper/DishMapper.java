package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.RestaurantViewVO;
import com.astar.order_admin.data.request.BlockRequestVO;
import com.astar.order_admin.data.request.DescRequestVO;
import com.astar.order_admin.data.request.DishRequestVO;
import com.astar.order_admin.data.response.BlockVO;
import com.astar.order_admin.data.response.DescVO;
import com.astar.order_admin.data.response.DishVO;

@Mapper
public interface DishMapper {
    public RestaurantViewVO selectRestaurantView(Integer ri_seq);
    public List<DishVO> selectDishInfoByRestaurant(Integer ri_seq);
    public List<BlockVO> selectOptionBlockInfoByDish(Integer di_seq);
    public List<DescVO> selectOptionDescInfoByBlock(Integer opt_seq);

    public void insertDishInfoByRestaurant(DishRequestVO data);
    public void insertOptionBlockInfoByDish(BlockRequestVO data);
    public void insertOptionDescByBlock(DescRequestVO data);

}
