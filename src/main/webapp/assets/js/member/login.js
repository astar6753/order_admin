$(function(){
    $("#member_login").click(function(){
        if(isEmpty($("#user_id").val())) {
            $("#user_id+.error").css("display","block");
            $("#user_id").attr("placeholder","아이디를 올바르게 입력하세요");
        }
        else{ $("#user_id+.error").css("display",""); }
        
        if(isEmpty($("#user_pwd").val())) {
            $("#user_pwd+.error").css("display","block");
            $("#user_pwd").attr("placeholder","비밀번호를 올바르게 입력하세요");
        }
        else{ $("#user_pwd+.error").css("display",""); }
        $.ajax({
            url:"/api/member/login",
            type:"post",
            data: JSON.stringify(
                {
                mi_id: $("#user_id").val(),
                mi_pwd: $("#user_pwd").val()
                }
            ),
            contentType:"application/json",
            success:function(r){
                alert(r.message);
                location.href="/"
            },
            error:function(err){
                alert(err.responseJSON.message);
                $("#user_pwd").val("");
            }
            
        })
    })
    $("#member_join").click(function(){
        location.href="/member/join/"
    })
})
