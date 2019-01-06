$(document).ready(function(){
    $("#useradd").click(function() {
// 处理表单验证和交给后台处理的逻辑
        var oError = document.getElementById("error_box");
        var username = $("#username").val();
        var password = $("#password").val();
        var name = $("#name").val();
        var tel = $("#tel").val();
        var email = $("#email").val();
        var address = $("#address").val();
        var sex=$('input[name="sex"]:checked').val();
        var birthday = $("#birthday").val();
        var description = $("#description").val();
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
        /* 2015/11/28  --20151128  */
        var year=birthday.substring(0,4);
        var month=birthday.substring(5,7);
        var day=birthday.substring(8,10);
        birthday=year+month+day;
        $.ajax({
            type: "post",
            url: "manage/adduser.action",
            dataType: "json",
            data: {"username":username,"password":password,"name":name,"tel":tel,"email":email,"address":address,"sex":sex,"birthday":birthday,"description":description},
            //data:dataString,
            success: function(json){
                if(json.code==0000) {
                    alert(json.message);
                    window.location.href = "user-management.html";
                }
                else if(json.code==9999){
                    alert(json.message);
                }
            },
            error: function(json){
                alert(json.message);
            }
        });

    });
});
