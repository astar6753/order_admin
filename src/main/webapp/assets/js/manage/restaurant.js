let seq;
let type = "restaurant";

$(function(){
    //영업장 추가 버튼
    $("#open_popup").click(function(){
        set_popup("add");
    })
    $("#cancel").click(function(){  
        set_popup();
    })
    //카테고리 검색
    $("#cate_name").change(function(){
        $("#cate_list").html("");
        $("#cate_name").attr("cate-seq","")
        let keyword = $("#cate_name").val();
        $.ajax({
            url:"/api/restaurant/category?keyword="+keyword,
            type:"get",
            success:function(r) {
                if(r.cate_search.length==0)return;
                for (let i = 0; i < r.cate_search.length; i++) {
                    let tag = "<option id='select_cate"+i+"' value="+r.cate_search[i].cate_seq+">"+r.cate_search[i].cate_name+"</option>";
                    $("#cate_list").append(tag);
                }
                changeSelect();
            }
        })
    })

    //창열자마자 테이블 조회
    $.ajax({
        url:"/api/restaurant/user/list",
        type:"get",
        success:function(r) {
            console.log(r.message);

            for (let i = 0; i < r.list.length; i++) {
                if(r.list[i].img_file==null)r.list[i].img_file="default.jpg";
                let tag =   "<tr>"+
                                "<td id='order"+r.list[i].ri_seq+"'>"+(i+1)+"</td>"+
                                "<td id='cate"+r.list[i].ri_seq+"'>"+r.list[i].cate_name+"</td>"+
                                "<td id='name"+r.list[i].ri_seq+"'>"+r.list[i].ri_name+"</td>"+
                                "<td id='price"+r.list[i].ri_seq+"'>"+r.list[i].ri_min_price+"</td>"+
                                "<td id='fee"+r.list[i].ri_seq+"'>"+r.list[i].ri_delivery_fee+"</td>"+
                                "<td id='addr"+r.list[i].ri_seq+"'>"+r.list[i].ri_address+"</td>"+
                                "<td><img class='restaurant_small_img' src='/api/img/"+type+"/"+r.list[i].img_file+"'></td>"+
                                "<td><button class='img_btn' data-seq="+r.list[i].ri_seq+" name="+r.list[i].ri_name+"><i class='fas fa-edit'></i><span>로고 이미지</span></button></td>"+
                                "<td><button class='dish_btn'><i class='fas fa-edit'></i><a href='/manage/dish?seq="+r.list[i].ri_seq+"'>메뉴관리</a></button></td>"+
                                "<td><button class='edit_btn' data-seq="+r.list[i].ri_seq+" cate-seq="+r.list[i].ri_cate_seq+"><i class='fas fa-edit'></i><span>수정</span></button></td>"+
                                "<td><button class='del_btn' data-seq="+r.list[i].ri_seq+"><i class='fas fa-trash-alt'></i><span>삭제</span></button></td>"+
                            "</tr>";
                $("#restaurant_list").append(tag);
            }
            $(".img_btn").click(function(){
                $(".img_popup_area").show();
                seq = $(this).attr("data-seq");
                $(".name").html($(this).attr("name"));
            })

            $(".del_btn").click(function(){
                let seq = $(this).attr("data-seq");
                if(!confirm("영업장 정보를 삭제하시겠습니까?\n삭제된 정보는 되돌릴 수 없습니다.")) return;
                $.ajax({
                    url:"/api/restaurant/delete?seq="+seq,
                    type:"delete",
                    success:function(r) {
                        alert(r.message);
                        location.reload();
                    },
                    error:function(err){
                        alert(err.responseText);
                        location.href="/member/login"
                    }
                })
            })
            $(".edit_btn").click(function(){
                set_popup("edit");
                let seq = $(this).attr("data-seq"); //버튼 생성시 세팅된 영업장seq
                $("#cate_name").val($("#cate"+seq+"").html());
                let cate_seq = $("#cate_name").attr("cate-seq",$(this).attr("cate-seq"));
                $("#ri_name").val($("#name"+seq+"").html());
                $("#ri_min_price").val($("#price"+seq+"").html());
                $("#ri_delivery_fee").val($("#fee"+seq+"").html());
                $("#ri_address").val($("#addr"+seq+"").html());

                $("#mod_btn").click(function(){
                    if(valid_chk())return;
                    let data = {
                        ri_seq: seq,
                        ri_cate_seq: cate_seq,
                        ri_name: $("#ri_name").val(),
                        ri_min_price: $("#ri_min_price").val(),
                        ri_delivery_fee: $("#ri_delivery_fee").val(),
                        ri_address: $("#ri_address").val()
                    };
                    $.ajax({
                        url:"/api/restaurant/update",
                        type:"patch",
                        contentType:"application/json",
                        data:JSON.stringify(data),
                        success:function(r) {
                            alert(r.message);
                            location.reload();
                        },
                        error:function(err){
                            alert(err.responseText);
                            location.href="/member/login"
                        }
                    })
                })
            })
        },
        error:function(err){
            alert(err.responseText);
            location.href="/member/login"
        }
    })

    $("#add_btn").click(function(){
        if(valid_chk())return;
        let data = {
            ri_mi_seq:$(this).attr("data-seq"), //jsp에서 세션의 유저seq를 버튼에 등록해둠
            ri_cate_seq: $("#cate_list option:selected").val(),
            ri_name: $("#ri_name").val(),
            ri_min_price: $("#ri_min_price").val(),
            ri_delivery_fee: $("#ri_delivery_fee").val(),
            ri_address: $("#ri_address").val()
        }
        $.ajax({
            url:"/api/restaurant/insert",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r) {
                alert(r.message);
                location.reload();
            },
            error:function(err){
                alert(err.responseText);
                location.href="/member/login"
            }
        })
    })
})

function set_popup(mode){
    $(".popup_area").hide();
    $("#cate_name").attr("cate-seq","");
    $("#cate_list").html("")    
    let tag = "<option value='' selected disabled>카테고리 선택</option>"
    $("#cate_list").append(tag)
    $("#cate_list option:eq(0)").html("카테고리 검색")
    $("#cate_name").val("")
    $("#ri_name").val("")
    $("#ri_min_price").val("")
    $("#ri_delivery_fee").val("")
    $("#ri_address").val("")
    if(mode=="add"){
        $(".popup_title").html("영업장 등록")
        $("#mod_btn").hide();
        $("#add_btn").show();
        $(".popup_area").show();
    }
    else if(mode=="edit"){
        $(".popup_title").html("영업장 정보 수정")
        $("#add_btn").hide();
        $("#mod_btn").show();
        $(".popup_area").show();
    }
}
function valid_chk(){    
    if(isEmpty($("#cate_list option:selected").val(),false)) {
        alert("카테고리 이름을 입력하세요");
        return false;
    }
    if(isEmpty($("#ri_name").val(),false)) {
        alert("영업장 이름을 입력하세요");
        return false;
    }
    if(isEmpty($("#ri_min_price").val(),false)) {
        alert("최소주문가격을 입력하세요");
        return false;
    }
    if(isEmpty($("#ri_delivery_fee").val(),false)) {
        alert("배달료를 입력하세요");
        return false;
    }
    if(isEmpty($("#ri_address").val(),false)) {
        alert("배달료를 입력하세요");
        return false;
    }
}
function changeSelect(){
    $("#cate_name").val($("#cate_list option:selected").text());
    $("#cate_name").attr("cate-seq",$("#cate_list option:selected").val());
}
