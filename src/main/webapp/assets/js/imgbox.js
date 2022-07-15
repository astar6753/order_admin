let img_files = new Array();

$(function(){
    $("#img_file").change(function(){
        let form = $("#img_form");
        let formData = new FormData(form[0]);
        if($(this).val() == ''||$(this).val() == null) return;
        $.ajax({
            url:"/images/upload/actor",
            type:"put",
            data:formData,
            contentType:false,
            processData:false,
            success:function(result) {
                if(!result.status) {
                    alert(result.message);
                    return;
                }
                let tag = '<div class="actor_img" filename="'+result.file+'" style="background-image:url(/images/actor/'+result.file+')">'
                +'<button onclick=deleteImg("'+result.file+'")>&times;</button>'
                +'</div>';
                img_files.push(result.file);
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
        url:"/images/delete/actor/"+file,
        type:"delete",
        success:function(result) {
            alert(result.message);
            if(result.status) {
                // img파일에서 입력된 파라미터 file값과 동일하지 않은 것들을 걸러내서 새로운 배열을 만들고 img_files에 덮어쓴다
                img_files = img_files.filter((img)=>(file!=img))
                $(".img_list").html("");
                for(let i = 0; i<img_files.length; i++){
                let tag = '<div class="actor_img" filename="'+img_files[i]+'" style="background-image:url(/images/actor/'+img_files[i]+')">'
                            +'<button onclick=deleteImg("'+img_files[i]+'")>&times;</button>'
                            +'</div>';
                $(".img_list").append(tag);
                }
            }
        }
    })
}