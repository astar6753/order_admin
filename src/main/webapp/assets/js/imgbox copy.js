let img_files = new Array();

$(function(){
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
                if(!r.status) {
                    alert(r.message);
                    return;
                }
                console.log(r);
                let tag = '<div class="restaurant_img" filename="'+r.saveFileName+'" style="background-image:url(/api/img/restaurant/'+r.saveFileName+')">'
                +'<button onclick=deleteImg("'+r.saveFileName+'")>&times;</button>'
                +'</div>';
                img_files.push(r.saveFileName);
                $(".img_list").append(tag);
            },
            error:function(error) {
                console.log(error);
            }
        })
    });

})

function deleteImg(file) {
    $.ajax({
        url:"/api/img/restaurant/"+file,
        type:"delete",
        success:function(r) {
            alert(r.message);
            if(r.status) {
                // img파일에서 입력된 파라미터 file값과 동일하지 않은 것들을 걸러내서 새로운 배열을 만들고 img_files에 덮어쓴다
                img_files = img_files.filter((img)=>(file!=img))
                $(".img_list").html("");
                for(let i = 0; i<img_files.length; i++){
                let tag = '<div class="restaurant_img" filename="'+img_files[i]+'" style="background-image:url(/api/img/restaurant/'+img_files[i]+')">'
                            +'<button onclick=deleteImg("'+img_files[i]+'")>&times;</button>'
                            +'</div>';
                $(".img_list").append(tag);
                }
            }
        }
    })
}