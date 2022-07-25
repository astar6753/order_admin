<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/header.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
        integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous"/>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.structure.min.css" integrity="sha512-oM24YOsgj1yCDHwW895ZtK7zoDQgscnwkCLXcPUNsTRwoW1T1nDIuwkZq/O6oLYjpuz4DfEDr02Pguu68r4/3w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css" integrity="sha512-9h7XRlUeUwcHUf9bNiWSTO9ovOWFELxTlViP801e5BbwNJ5ir9ua6L20tEroWZdm+HFBAWBLx2qH4l4QHHlRyg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="/assets/js/common/util.js"></script>
</head>
<body>
    <header class="nav">
        <div class="logo">
            <i class="fab fa-accusoft"></i>
            <a href="/">OrderAdmin</a>
        </div>

        <ul class="menu">
            <li class="hover"><a class="customer_list" href="/manage/customer">고객 이력 조회</a></li>
            <li><p class="sep">|</p></li>
            <li class="hover"><a class="restaurant_list" href="/manage/restaurant">영업장 관리</a></li>
            <li><p class="sep">|</p></li>
            <li class="hover"><a class="order_list" href="/manage/order">주문 관리</a></li>
        </ul>

        <ul class="user_info">
            <c:if test="${user!=null}">
                <li class="hover"><a class="user" href="/member/info">${user.mi_name}(${user.mi_id})</a></li>
                <li><p class="sep">|</p></li>
                <li class="hover"><a href="/member/logout">로그아웃</a></li>
            </c:if>
            <c:if test="${user==null}">
                <li class="hover"><a href="/member/join">회원가입</a></li>
                <li><p class="sep">|</p></li>
                <li class="hover"><a href="/member/login">로그인</a></li>
            </c:if>
        </ul>
        
        <a href="#" class="nav_toggle_btn">
            <i class="fas fa-bars"></i>
        </a>
    </header> 
</body>
</html>