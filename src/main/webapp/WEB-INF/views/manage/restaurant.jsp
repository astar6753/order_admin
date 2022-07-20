<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/imgbox.jsp"%>
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
                        <tr>
                            <td>번호</td>
                            <td>분류</td>
                            <td>상호명</td>
                            <td>최소주문가격</td>
                            <td>배달료</td>
                            <td>위치</td>
                            <td>로고</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody id="restaurant_list">
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
            </div>
        </section>

        <section class="popup_area">
            <div class="restaurant_add">
                <h1 class="popup_title"></h1>
                <div class="content">
                    <p>분류</p>
                        <input type="text" id="cate_name" placeholder="카테고리 검색">
                        <div class="search_area">
                            <select id="cate_list" onchange="changeSelect()">
                                <option value='' selected disabled>카테고리 선택</option>
                            </select>                            
                        </div>
                    <p>상호명</p><input type="text" id="ri_name">
                    <p>최소주문가격</p><input type="text" id="ri_min_price">
                    <p>배달료</p><input type="text" id="ri_delivery_fee">
                    <p>위치</p><input type="text" id="ri_address">
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
