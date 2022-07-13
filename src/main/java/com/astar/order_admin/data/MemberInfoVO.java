package com.astar.order_admin.data;

import lombok.Data;

@Data
public class MemberInfoVO {
    private Integer mi_seq;
    private String mi_id;
    private String mi_pwd;
    private String mi_name;
    private String mi_phone;
    private Integer mi_img_seq;
    private Integer mi_status;
    private Integer mi_grade;
    private String mi_address;
}