$(document).ready(function(){
    $("#login").click(function() {
// 处理表单验证和交给后台处理的逻辑
        var oError = document.getElementById("error_box");
        var username = $("#username").val();
        var password = $("#password").val();
        var isNotError = true;
        if(username==""){
            oError.innerHTML = "用户名为空 ";
            isNotError = false;
            $("#username").focus();
            return;
        }
        if(password==""){
            oError.innerHTML = "密码为空 ";
            isNotError = false;
            $("#password").focus();
            return;
        }
        $.ajax({
            type: "post",
            url: "login/login.action",
            dataType: "json",
            data: {"username":username,"password":password},
            //data:dataString,
            success : function(json) {
                if(json.code==0000) {
                    alert("登录成功，点击前往主页..");
                    window.location.href = "my_homepage.html";
                }
                else if(json.code==9999){
                    alert(json.message);
                }
            },
            error : function(json) {
                alert(json.message);
            }
        });

    });
});