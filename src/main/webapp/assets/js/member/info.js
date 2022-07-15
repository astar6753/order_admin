$(function(){
    $.ajax({
        url:"/api/member/info",
        type:"get",
        success:function(r){
            $("#mi_name").val(r.member_info.mi_name)
            $("#mi_phone").val(r.member_info.mi_phone)
            $("#mi_address").val(r.member_info.mi_address)
        },
        error:function(err){
            alert(err.responseJSON.message)
            location.href = "/"
        }
    })

    $("#cancel").click(function(){
        confirm("회원정보 수정을 취소하고 돌아가시겠습니까?\n입력된 정보는 저장되지 않습니다.")
        location.href = "/"
    })

    $("#mi_pwd, #mi_pwd_confirm").keyup(function(){
        if($("#mi_pwd").val() != $("#mi_pwd_confirm").val()) {
            $("#pwd_not_confirm").html("비밀번호가 일치하지 않습니다.")
            return;
        }
        $("#pwd_not_confirm").html("")
        return;
    })
    
    $("#modify").click(function(){
        if(!confirm("회원정보를 수정 하시겠습니까?"))return;

        if(isEmpty($("#mi_pwd").val())) {alert("비밀번호를 올바르게 입력해주세요.");return;}
        if(isEmpty($("#mi_name").val())) {alert("이름을 올바르게 입력해주세요.");return;}
        if(isEmpty($("#mi_phone").val())) {alert("전화번호를 올바르게 입력해주세요.");return;}

        if($("#mi_pwd").val() != $("#mi_pwd_confirm").val()) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }
        if(!nameValidate($("#mi_name").val())) {
            alert("이름을 올바르게 입력해주세요");
            return;
        }
        if(!phoneNumberValidate($("#mi_phone").val())) {
            alert("전화번호 형식 틀림");
            return;
        }
        
        let data = {
            "mi_seq" : "",  //컨트롤러에서 세션값으로 채워줌
            "origin_pwd" : $("#origin_pwd").val(),
            "mi_pwd" : $("#mi_pwd").val(),
            "mi_name" : $("#mi_name").val(),
            "mi_phone" : $("#mi_phone").val(),
            "mi_address" : $("#mi_address").val()
        }
        $.ajax({
            url:"/api/member/update",
            type:"patch",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r){
                alert(r.message)
                location.href = "/"
            },
            error:function(err){
                alert(err.responseJSON.message)
                location.href = "/"
            }
        })
    })
})