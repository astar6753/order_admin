package com.astar.order_admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.MemberInfoVO;

@Mapper
public interface MemberMapper {
    
    public void insertMemberInfo(MemberInfoVO data);
    public MemberInfoVO selectMemberLogin(MemberInfoVO data);
    public boolean isDuplicatedId(String id);
    public MemberInfoVO selectMemberInfoBySeq(Integer seq);
    public void updateMemberInfo(MemberInfoVO data);
}
