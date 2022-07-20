package com.astar.order_admin.data.Response;

import java.util.List;

import lombok.Data;

@Data
public class BlockVO {
    private Integer opt_seq;
    private Integer opt_title;
    private Integer opt_di_seq;
    private Integer opt_allowed_no;
    private Integer opt_requierd;
    private List<DescVO> descList;
    
}
