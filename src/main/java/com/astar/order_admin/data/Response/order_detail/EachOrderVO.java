package com.astar.order_admin.data.response.order_detail;

import java.util.List;

import lombok.Data;

@Data
public class EachOrderVO {
    private Integer each_seq;
    private String di_name;
    private Integer di_price;
    private Integer each_quantity;
    private Integer each_price;
    private List<OptionDetailVO> optionList;
}
