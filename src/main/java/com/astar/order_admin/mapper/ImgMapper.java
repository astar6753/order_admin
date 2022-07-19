package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.ImgInfoVO;

@Mapper
public interface ImgMapper {
    public List<ImgInfoVO> selectImgInfoBySeq(Integer type, Integer offset, Integer seq);
    public void insertImgInfo(ImgInfoVO data);
    public void deleteImgInfoByImgSeq(Integer seq);
    public void updateImgToRestaurantInfo(Integer img_seq, Integer seq);
    public void updateImgToDishInfo(Integer img_seq, Integer seq);
    public void updateImgToMemberInfo(Integer img_seq, Integer seq);
}
