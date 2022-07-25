package com.astar.order_admin.data.response.order;

import java.util.Date;

import lombok.Data;

@Data
public class OrderResponseVO {
    private Integer oi_seq;
    private Integer oi_status;
    private String oi_address;
    private Date oi_reg_dt;
    private String mi_id;
    private String mi_name;
    private String mi_phone;
    private String ri_name;
    private String ri_address;
}
