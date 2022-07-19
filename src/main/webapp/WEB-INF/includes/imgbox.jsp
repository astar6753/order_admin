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
        <h1 class="name"></h1>
        <h1 class="img_popup_title">이미지 등록</h1>
        <form id="img_form">
            <input type="file" id="img_file" name="file" hidden accept="image/gif, image/jpeg, image/png">
        </form>
        <div class="imgs">
            <div class="img_list">
            </div>            
            <button id="prev">prev</button>
            <button id="next">next</button>
            <button id="browse" onclick="document.getElementById('img_file').click()">이미지 추가</button>
        </div>
    </div>
</section>
</main>
</body>
</html>
