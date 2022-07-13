<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/member/login.js"></script>
</head>
<body>
    <div class="login_box">
        <h1>사업자 회원 로그인</h1>
        <p class="label">아이디</p>
        <input type="text" id="user_id">
        <p class="error">아이디를 올바르게 입력하세요.</p>
        <p class="label">비밀번호</p>
        <input type="password" id="user_pwd">
        <p class="error">비밀번호를 올바르게 입력하세요</p>
    </div>
    <div class="login_btns">
        <button id="member_join">회원가입</button>
        <button id="member_login">로그인</button>
    </div>
</body>
</html>