package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.ImgInfoVO;

@Mapper
public interface ImgMapper {
    public List<ImgInfoVO> selectImgInfoBySeq(Integer type, Integer offset, Integer seq);
    public void insertImgInfo(ImgInfoVO data);
    public void deleteImgInfo(Integer seq);

    public void insertImgToRestaurantInfo(Integer ri_seq, Integer img_seq);
}
