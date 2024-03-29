package com.astar.order_admin.data;

import lombok.Data;

@Data
public class DishInfoVO {
    private Integer di_seq;
    private Integer di_ri_seq;
    private String di_name;
    private Integer di_price;
    private Integer di_img_seq;
    private String di_description;
}
