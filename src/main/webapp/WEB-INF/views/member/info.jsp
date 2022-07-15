<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/member/info.js"></script>
</head>
<body>
    <main>
        <h1>회원정보 수정</h1>
        <div class="member_info_box">
            <div class="input_area">
                <table>
                    <tbody>
                        <tr>
                            <td>기존 비밀번호</td>
                            <td>
                                <input type="password" id="origin_pwd" placeholder="기존 비밀번호 (16자 이내)">
                            </td>
                            <td class="msg_box">
                                <p id="pwd_not_valid"></p>
                            </td>
                        </tr>
                        <tr>
                            <td>변경할 비밀번호</td>
                            <td>
                                <input type="password" id="mi_pwd" placeholder="변경할 비밀번호 (16자 이내)">
                            </td>
                            <td class="msg_box">
                                <p id="pwd_not_confirm"></p>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호 확인</td>
                            <td>
                                <input type="password" id="mi_pwd_confirm" placeholder="비밀번호 확인">
                            </td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" id="mi_name" placeholder="이름 (50자 이내)" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>전화번호</td>
                            <td>
                                <input type="text" id="mi_phone" placeholder="- 을 빼고 입력하세요." autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <textarea name="" id="mi_address" cols="21" rows="3"></textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="btn">
                <button id="modify">회원정보 수정</button>
                <button id="cancel">돌아가기</button>
            </div>
        </div>
    </main>
</body>
</html>