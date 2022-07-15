let dup_check = false;
$(function(){
    $("#cancel").click(function(){
        confirm("회원가입을 취소하고 돌아가시겠습니까?\n입력된 정보는 저장되지 않습니다.")
        location.href = "/member/login"
    })    
    $("#mi_id").keyup(function(){
        dup_check = false;
    })

    $("mi_pwd, #mi_pwd_confirm").keyup(function(){
        if($("#mi_pwd").val() != $("#mi_pwd_confirm").val()) {
            $("#pwd_not_confirm").html("비밀번호가 일치하지 않습니다.")
            return;
        }
        $("#pwd_not_confirm").html("")
        return;
    })

    $("#id_dup_chk").click(function(){
        if(isEmpty($("#mi_id").val())) {
            $("#id_duplicate").html("아이디를 올바르게 입력해주세요")
            $("#id_duplicate").css("display","block");
            return;
        }
        $.ajax({
            url:"/api/member/chk?id="+$("#mi_id").val(),
            type: "get",
            success:function(duplicate){
                if(!duplicate){
                    dup_check = true;
                    $("#id_duplicate").html($("#mi_id").val()+"은(는) 사용가능한 아이디입니다.")
                    $("#id_duplicate").css("display","block");
                }
                else{
                    $("#id_duplicate").html($("#mi_id").val()+"은(는) 이미 사용 중인 아이디입니다.")
                    $("#id_duplicate").css("display","block");
                }
            }
        })
        
    });
    
    $("#join").click(function(){
        if(!dup_check) {
            alert("아이디를 중복 체크를 해주세요.");
            return;
        }
        if(!confirm("회원가입 하시겠습니까?"))return;

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
            "mi_id" : $("#mi_id").val(),
            "mi_pwd" : $("#mi_pwd").val(),
            "mi_name" : $("#mi_name").val(),
            "mi_phone" : $("#mi_phone").val(),
            "mi_address" : $("#mi_address").val()
        }
        
        $.ajax({
            url:"/api/member/join",
            type:"put",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(result){
                alert(result.message)
                location.href = "/"
            },
            error:function(err){
                alert(err.responseJSON.message)
            }
        })
    })
})