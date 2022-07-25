let query = window.location.search;
let param = new URLSearchParams(query);
let seq = param.get('seq');



$(function(){
    $.ajax({
        url:"/api/dish/list/all?seq="+seq,
        type:"get",
        success:function(r) {
            // console.log(r.key.restaurantView); 음식점정보 ri_name 음식점이름 / ri_min_price 최소주문가격 / ri_delivery_fee 배달료 / ri_img_seq이미지 / ri_address주소
            // console.log(r.key.dishList[0]); 음식점의 메뉴 정보 di_name 메뉴이름/ di_price 기본가격 / di_description 설명 / di_img_seq 이미지
            // console.log(r.key.dishList[0].blockList); 메뉴의 옵션블록 정보 opt_title 블록제목 / opt_allowed_no 선택허용개수 / opt_requierd 필수여부
            // console.log(r.key.dishList[0].blockList[0].descList[0]); 옵션블록의 옵션정보 desc_name 선택지설명 / desc_price 옵션가격 / desc_opt_seq 옵션
            
            //aside정보 
            $(".restaurant_img").attr("src","/api/img/restaurant/"+r.key.restaurantView.img_file)
            $(".restaurant_title").html(r.key.restaurantView.ri_name);
            $(".category").html(r.key.restaurantView.cate_name);
            $(".min_price").html(r.key.restaurantView.ri_min_price+"원");
            $(".delivery_fee").html(r.key.restaurantView.ri_delivery_fee+"원");
            $(".open_time").html(r.key.restaurantView.ri_open_time);
            $(".end_time").html(r.key.restaurantView.ri_end_time);
            
            //dish_list_area안쪽 dish_list태그
            let full_tag = "";
            
            if(r.key.dishList.length!=null){
                for(let i = 0; i < r.key.dishList.length; i++) {
                    //aside메뉴 태그
                    let tag='<li>'+r.key.dishList[i].di_name+" "+r.key.dishList[i].di_price+'</li>';
                    $(".dish_list").append(tag);

                    let dish_list_tag=
                        '<div class="dish_list_wrap">'+
                            '<div class="content_left_box">'+
                                '<div class="dish_img_area">'+
                                    '<a class="img_box_link" href="#">'+
                                        '<img class="img_btn" onclick="'+open_img_box+'" src="/api/img/dish/'+r.key.dishList[i].dish_img_file+'" alt="">'+
                                        '<img class="dish_img" src="/api/img/dish/'+r.key.dishList[i].dish_img_file+'" alt="">'+
                                    '</a>'+
                                '</div>'+
                                '<div class="dish_title_area">'+
                                    '<h1>'+r.key.dishList[i].di_name+'</h1>'+
                                '</div>'+
                            '</div>'+
                            '<div class="content_right_box">'+
                                '<div class="dish_block">'+
                                    '<p>가격<span class="sep"> | </span>'+r.key.dishList[i].di_price+'원</p>'+
                                    '<p class="descfffff">'+r.key.dishList[i].di_description+'</p>'+
                                '</div>'+
                                '<div class="option_area">';
                                full_tag += dish_list_tag;
                                    if(r.key.dishList[i].blockList.length!=null){
                                        for(let j = 0; j < r.key.dishList[i].blockList.length; j++) {
                                            let option_box_tag1 =
                                            '<div class="option_block">'+
                                                '<h3 class="option_title">'+r.key.dishList[i].blockList[j].opt_title+'</h3>';
                                                full_tag += option_box_tag1;
                                                if(r.key.dishList[i].blockList[j].descList.length!=null){
                                                    for(let k = 0; k< r.key.dishList[i].blockList[j].descList.length; k++) {
                                                        let option_desc_tag = '<p class="option_desc">'+r.key.dishList[i].blockList[j].descList[k].desc_name+'|'+r.key.dishList[i].blockList[j].descList[k].desc_price+'</p>'
                                                        full_tag += option_desc_tag;                            
                                                    }
                                                }
                                            let option_box_tag2 = '</div>';
                                            full_tag += option_box_tag2;
                                        }
                                    }
                            let close_tag = '</div>'+
                            '</div>'+
                        '</div>';
                        full_tag += close_tag;
                }
            }
            $(".dish_area").append(full_tag);
        }


    })
})


function open_img_box(mode){
        $(".img_popup_area").show();
        seq = $(this).attr("data-seq");
        $(".name").html($(this).attr("name"));
}