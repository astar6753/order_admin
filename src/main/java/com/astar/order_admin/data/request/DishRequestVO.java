package com.astar.order_admin.data.request;

import java.util.List;

import lombok.Data;

@Data
public class DishRequestVO {
        private Integer di_seq;
        private Integer di_ri_seq;
        private String di_name;
        private Integer di_price;
        private Integer di_img_seq;
        private String dish_img_file;
        private String di_description;
        private List<BlockRequestVO> blockList;
}

/*메뉴조회
request{
    dish[1]{
            이름 
            가격 
            etc 
            opt[1]{
                    제목 
                    최대선택수
                    필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격}
                    desc[3]{옵션이름 가격}
            }
            opt[2]{제목 최대선택수 필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격}
                    desc[3]{옵션이름 가격}
            }
    }
}
*/