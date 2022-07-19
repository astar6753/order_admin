let img_files = new Array();
$(function(){
    $.ajax({
        url:"/api/user/img/restaurant/0",
        type:"get",
        success:function(r) {
            for(let i = 0; i<r.img_list.length; i++){
                let tag = 
                '<div class="aaa111">'
                +'<div class="restaurant_img" filename="'+r.img_list[i].img_back_name+'" style="background-image:url(/api/img/restaurant/'+r.img_list[i].img_back_name+')">'
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

    $("#img_file").change(function(){
        let form = $("#img_form");
        let formData = new FormData(form[0]);
        if($(this).val() == ''||$(this).val() == null) return;
        $.ajax({
            url:"/api/img/restaurant/upload",
            type:"put",
            data:formData,
            contentType:false,
            processData:false,
            success:function(r) {
                console.log(r)
                if(!r.status) {
                    alert(r.message);
                    return;
                }
                let data ={
                    img_front_name: r.originName,
                    img_back_name: r.saveFileName
                };
                $.ajax({
                    url:"/api/user/img/restaurant",
                    type:"put",
                    contentType:"application/json",
                    data:JSON.stringify(data),
                    success:function(r) {
                        alert(r.message);
                        location.reload();
                    }
                })
                
                img_files.push(r.saveFileName);
            },
            error:function(error) {
                console.log(error);
            }
        })
    });

})

function selectImg(file) {

}
function deleteImg(file,seq) {
    if(!confirm("이미지를 삭제하시겠습니까?"))return;
    console.log(seq);
    $.ajax({
        url:"/api/img/restaurant/"+file,
        type:"delete",
        success:function(r) {
            alert(r.message);
            console.log(seq);
            $.ajax({
                url:"/api/user/img?seq="+seq,
                type:"delete",
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        }
    })
}