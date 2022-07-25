package com.astar.order_admin.data.request;

import lombok.Data;

@Data
public class DescRequestVO {
    private Integer desc_seq;
    private String desc_name;
    private Integer desc_price;
    private Integer desc_opt_seq;    
}
