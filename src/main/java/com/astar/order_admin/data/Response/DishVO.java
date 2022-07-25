package com.astar.order_admin.data.response;

import java.util.List;

import lombok.Data;

@Data
public class DishVO {
    private Integer di_seq;
    private Integer di_ri_seq;
    private String di_name;
    private Integer di_price;
    private Integer di_img_seq;
    private String dish_img_file;
    private String di_description;
    private List<BlockVO> blockList;
}