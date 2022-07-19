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
        <h1 class="img_popup_title">영업점 이미지 등록</h1>
        <form id="img_form">
            <input type="file" id="img_file" name="file" hidden accept="image/gif, image/jpeg, image/png">
        </form>
        <div class="img_box">
            <div class="img_list">
            </div>
            <button id="browse" onclick="document.getElementById('img_file').click()">이미지 추가</button>
        </div>
    </div>
    

    이미지 편집 버튼>>팝업 오픈
    본인이 업로드한 사진 전체 조회>>
    추가>>파일 업로드 및 db추가
    선택>>db등록
    삭제>>db삭제 및 파일 삭제


</section>
</main>
</body>
</html>
