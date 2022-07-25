<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/manage/dish.js"></script>
    <link rel="stylesheet" href="/assets/css/dish.css">
</head>
<!--        // r.key.restaurantInfo 음식점정보 ri_name 음식점이름 / ri_min_price 최소주문가격 / ri_delivery_fee 배달료 / ri_img_seq이미지 / ri_address주소
            // r.key.dishList[0] 음식점의 메뉴 정보 di_name 메뉴이름/ di_price 기본가격 / di_description 설명 / di_img_seq 이미지
            // r.key.dishList[0].blockList 메뉴의 옵션블록 정보 opt_title 블록제목 / opt_allowed_no 선택허용개수 / opt_requierd 필수여부
            // r.key.dishList[0].blockList[0].descList[0] 옵션블록의 옵션정보 desc_name 선택지설명 / desc_price 옵션가격 / desc_opt_seq 옵션 -->
<body>
    <aside>
        <div class="aside_img">
            <div class="aside_restaurant_img">
                <img class="restaurant_img" src="" alt="">
            </div>
        </div>
        <div class="aside_restaurant_area">
            <div class="restaurant_title_area">
                <h2 class="restaurant_title"></h2>
            </div>
            
            <div class="restaurant_info">
                <h2 class="category"></h2>
                <div>
                    <p>최소주문가격
                        <span class="sep">|</span>
                        <span class="min_price"></span>
                    </p>
                </div>
                <div>
                    <p>배달료
                        <span class="sep">|</span>
                        <span class="delivery_fee"></span>
                    </span>
                </div>
                <div>
                    <p>영업시간
                        <span class="sep">|</span>
                        <span class="open_time"></span>
                        <span class="sep">~</span>
                        <span class="end_time"></span>
                    </p>
                </div>
            </div>

            <div class="dish_list_area">
                <h1>메뉴</h1>
                <ul class="dish_list">

                </ul>
            </div>
        </div>
    </aside>

    <main>
        <div class="dish_area">
        </div>
    </main>
</body>
</html>

