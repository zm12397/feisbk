$(document).ready(function() {
	$("#uname").focus(function(){
		$("#l-uname").css('display','inline-block')
	}).blur(function(){
		$("#l-uname").css('display','none')
	})
	$("#password").focus(function(){
		$("#l-password").css('display','inline-block')
	}).blur(function(){
		$("#l-password").css('display','none')
	})
	$("#login").click(function(){
		$.ajax({
			url : "login/login.action",
			data : {"username":$("#uname").val(),"password":$("#password").val()},
			method:"post",
			dataType:"json",
			success : function(result) {
				if(result.code == "0000"){
					if($("#uname").val() == "admin"){
                        layer.msg("登录成功。。。正在前往管理页",function(){
                            window.location.href="user-management";
                        })
					}else{
                        layer.msg("登录成功。。。正在前往主页",function(){
                            window.location.href="index";
                        })
					}
				}else{
					layer.msg(result.message)
				}
				
			}
		})
	})
})