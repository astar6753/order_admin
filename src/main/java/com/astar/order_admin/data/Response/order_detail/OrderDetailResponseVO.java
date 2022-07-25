package com.astar.order_admin.data.response.order_detail;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderDetailResponseVO {
    private Integer oi_seq;
    private Integer oi_status;
    private String oi_address;
    private Date oi_reg_dt;
    private String mi_id;
    private String mi_name;
    private String mi_phone;
    private String ri_name;
    private String ri_address;
    private Integer total_price;
    private List<EachOrderVO> eachOrderList;
}
