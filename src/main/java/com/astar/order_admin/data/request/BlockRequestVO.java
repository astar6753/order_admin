package com.astar.order_admin.data.request;

import java.util.List;

import lombok.Data;

@Data
public class BlockRequestVO {
    private Integer opt_seq;
    private String opt_title;
    private Integer opt_di_seq;
    private Integer opt_allowed_no;
    private Integer opt_requierd;
    private List<DescRequestVO> descList;
    
}
