package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.response.order.OrderResponseVO;
import com.astar.order_admin.data.response.order_detail.EachOrderVO;
import com.astar.order_admin.data.response.order_detail.OptionDetailVO;
import com.astar.order_admin.data.response.order_detail.OrderDetailResponseVO;

@Mapper
public interface OrderMapper {
    public List<OrderResponseVO> selectOrderRespoenseListByUserSeq(Integer user_seq, String keyword, String start_dt, String end_dt, Integer offset);
    public Integer selectOrderRespoenseTotalCntByUserSeq(Integer user_seq, String keyword, String start_dt, String end_dt);
    public Integer selectOrderRespoenseTotalPageByUserSeq(Integer user_seq, String keyword, String start_dt, String end_dt);

    public OrderDetailResponseVO selectOrderDetailResponseByOrderSeq(Integer order_seq);
    public List<EachOrderVO> selectEachOrderByOrderSeq(Integer order_seq);
    public List<OptionDetailVO> selectOptionDetailByEachSeq(Integer each_seq);
}
