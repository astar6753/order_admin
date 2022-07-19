let offset = 0;
$(function(){
    imgView(type,offset);
    $("#next").click(function(){
        offset++;
        imgView(type,offset);
    })
    $("#prev").click(function(){
        if(offset<0) {alert("처음입니다."); return;}
        offset--;
        imgView(type,offset);
        
    })
    $("#img_file").change(function(){
        let form = $("#img_form");
        let formData = new FormData(form[0]);
        if($(this).val() == ''||$(this).val() == null) return;
        $.ajax({
            url:"/api/img/"+type+"/upload",
            type:"put",
            data:formData,
            contentType:false,
            processData:false,
            success:function(r) {
                console.log(r)
                if(!r.status) {
                    console.log(r.message);
                    return;
                }
                let data ={
                    img_front_name: r.originName,
                    img_back_name: r.saveFileName
                };
                $.ajax({
                    url:"/api/user/img/"+type+"",
                    type:"put",
                    contentType:"application/json",
                    data:JSON.stringify(data),
                    success:function(r) {
                        console.log(r.message);
                        imgView(type,offset);
                    }
                })
            },
            error:function(error) {
                console.log(error);
            }
        })
    });

})

function selectImg(img_seq) {
    $.ajax({
        url:"/api/user/img/"+type+"?img_seq="+img_seq+"&seq="+seq,
        type:"patch",
        success:function(r) {
            console.log(r.message);
            location.reload();
        }
    })
}
function deleteImg(file,seq) {
    if(!confirm("이미지를 삭제하시겠습니까?"))return;
    // console.log(seq);
    $.ajax({
        url:"/api/img/"+type+"/"+file,
        type:"delete",
        success:function(r) {
            console.log(r.message);
            $.ajax({
                url:"/api/user/img/delete?seq="+seq,
                type:"delete",
                success:function(r) {
                    alert(r.message);
                    imgView(type,offset);
                }
            })
        }
    })
}
function imgView(type,offset){
    $(".img_list").html("");
    $.ajax({
        url:"/api/user/img/"+type+"?offset="+offset,
        type:"get",
        success:function(r) {
            for(let i = 0; i<r.img_list.length; i++){
                let tag = 
                '<div class="img_box">'
                +'<div class="'+type+'_img" filename="'+r.img_list[i].img_back_name+'" style="background-image:url(/api/img/'+type+'/'+r.img_list[i].img_back_name+')">'
                            +'<button onclick=selectImg("'+r.img_list[i].img_seq+'")>선택</button>'            
                            +'<button onclick=deleteImg("'+r.img_list[i].img_back_name+'\","'+r.img_list[i].img_seq+'") data-seq='+r.img_list[i].img_seq+'>&times;</button>'
                            +'</div>'
                            +'<p>'+r.img_list[i].img_front_name+'</p>'
                            +'</div>';
                $(".img_list").append(tag);
            }
        },
        error:function(error) {
            console.log(error);
        }
    })
}