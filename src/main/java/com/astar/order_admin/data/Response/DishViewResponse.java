package com.astar.order_admin.data.Response;

import java.util.List;

import lombok.Data;

@Data
public class DishViewResponse {

        private List<DishVO> dishList;

}

/*메뉴조회
response{
    dish[1]{이름 가격 etc 
            opt[1]{제목 최대선택수 필수선택여부 
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
    dish[2]{이름 가격 etc 
            opt[1]{제목 최대선택수 필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격} 
                    desc[3]{옵션이름 가격}
            }
    }
}
*/