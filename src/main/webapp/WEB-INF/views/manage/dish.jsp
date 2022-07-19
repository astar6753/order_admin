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
<body>
    dish_info메뉴 추가 어느영업장/메뉴이름/기본가격/이미지/세부설명
    


    <main>
        <section class="manage_dish">
            <h1>메뉴 관리</h1>
            <button id="open_popup">
                <i class="fas fa-plus-square"></i><span>메뉴 추가</span>
            </button>
            <div class="dish_table">
                <table>
                    <thead>
                        <tr>
                            <td>번호</td>
                            <td>상호명</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody id="dish_list">
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
            </div>
        </section>


    </main>
</body>
</html>
