package com.astar.order_admin.data;

import lombok.Data;

@Data
public class RestaurantInfoVO {
    private Integer ri_seq;
    private Integer ri_mi_seq;
    private String ri_name;
    private Integer ri_min_price;
    private Integer ri_delivery_fee;
    private Integer ri_img_seq;
    private String ri_address;
    private Integer ri_cate_seq;
    private String ri_open_time;
    private String ri_end_time;
    private String ri_description;
}