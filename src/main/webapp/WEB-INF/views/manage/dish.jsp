<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/imgbox.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/manage/dish.js"></script>
</head>
<body>
    dish_info메뉴 추가 어느영업장/메뉴이름/기본가격/이미지/세부설명
    메뉴를 어느 영업장에 추가할 것인가
    메뉴에 기본가격설정
    옵션추가 블록타이틀 블록내부에서 옵션 몇개추가가능한지> 옵션로우추가 옵션이름/가격
    


    <main>
        <section class="manage_dish">
            <h1>메뉴 관리</h1>
            <button id="open_popup">
                <i class="fas fa-plus-square"></i><span>메뉴 추가</span>
            </button>
            <div id="dish_list">
                <div class="dish_area">
                    <h1 class="dish_name">메뉴이름</h1>
                    <div class="img_area"><img src="asdwqdwq"></div>
                    <div class="text_area">
                        <p>기본가격</p>
                        <p>메뉴설명</p>
                    </div>
                    <div class="option_area">
                        <p>기본옵션(필수)</p>
                        <div class="select_area">
                            <p><label><input type="checkbox" name="option_block_seq" value="01"> 01</label></p>
                            <p><label><input type="checkbox" name="option_block_seq" value="02"> 02</label></p>
                            <p><label><input type="checkbox" name="option_block_seq" value="03"> 03</label></p>
                            <p><label><input type="checkbox" name="option_block_seq" value="04"> 04</label></p>
                            <p><label><input type="checkbox" name="option_block_seq" value="05"> 05</label></p>
                        </div>                        
                    </div>
                </div>
            </div>
            <div class="pager_area">
            </div>
        </section>


    </main>
</body>
</html>

