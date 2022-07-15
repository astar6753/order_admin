<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/imgbox.js"></script>
    <link rel="stylesheet" href="/assets/css/imgbox.css">
</head>
<section class="img_popup_area">
    <div class="img_edit">
        <h1 class="popup_title">이미지</h1>
        <form id="img_form">
            <input type="file" id="img_file" name="file" hidden accept="image/gif, image/jpeg, image/png">
        </form>
        <div class="img_box">
            <div class="img_list">
                <button id="del_img" data-seq="${user.mi_seq}">수정</button>
                <button id="add_img" data-seq="${user.mi_seq}">추가</button>
            </div>
            <button id="browse" onclick="document.getElementById('actor_img_file').click()">이미지 추가</button>
        </div>
    </div>
</section>

</main>
</body>
</html>
