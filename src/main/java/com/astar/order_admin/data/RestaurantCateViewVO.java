package com.astar.order_admin.data;

import lombok.Data;

@Data
public class RestaurantCateViewVO {
    private Integer ri_seq;
    private Integer ri_mi_seq;
    private String ri_name;
    private Integer ri_min_price;
    private Integer ri_delivery_fee;
    private Integer ri_img_seq;
    private String ri_address;
    private Integer ri_cate_seq;
    private String cate_name;    
}