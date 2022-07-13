<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/manage/restaurant.js"></script>
    <link rel="stylesheet" href="/assets/css/restaurant.css">
</head>
<body>
    <main>
        <section class="manage_restaurant">
            <h1>영업장 관리</h1>
            <button id="open_popup">
                <i class="fas fa-plus-square"></i><span>영업장 추가</span>
            </button>
            <div class="restaurant_table">
                <table>
                    <thead>
                        <div class="img_area"></div>
                        <tr>
                            <td>번호</td>
                            <td>상호명</td>
                            <td>최소주문가격</td>
                            <td>배달료</td>
                            <td>위치</td>
                            <td>영업장 이미지</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody id="restaurant_list">
                    </tbody>
                </table>
            </div>
        </section>

        <section class="popup_area">
            <div class="restaurant_add">
                <h1 class="popup_title"></h1>
                <div class="content">
                    <p><input type="text" id="ri_name">상호명</p>
                    <p><input type="text" id="ri_min_price">최소주문가격</p>
                    <p><input type="text" id="ri_delivery_fee">배달료</p>
                    <p><input type="text" id="ri_address">위치</p>
                </div>
                <div class="btn">
                    <button id="add_btn" data-seq="${user.mi_seq}">등록</button>
                    <button id="mod_btn" data-seq="${user.mi_seq}">수정</button>
                    <button id="cancel">취소</button>
                </div>
            </div>
        </section>

    </main>
</body>
</html>
