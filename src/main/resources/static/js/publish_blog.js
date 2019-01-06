$(function () {
    var imgpath;
    $("#publish_blog").click(function () {
        $(".dialog").slideToggle(100);
        //     $(document).click(function () {
        //         $(".dialog").hide();
        //     })
    });

    $(".addpic").click(
        function () {
            $(".select_pic").click();
        })

    $("#pic_cancle").click(function () {
            $("#preview").src="";
            $("#show").toggle();
        }
    )

    $("#pic_upload").click(function () {
        $.ajax({
            type:'POST',
            url:'dynamic/upload.action',
            data: new FormData($( "#uploadform" )[0]),
            contentType:false,
            processData:false,//这个很有必要，不然不行
            dataType:"JSON",
            success:function (res) {
                alert('上传成功');
                imgpath=res.data;
                console.log(res.data);
            },
            error:function () {
                alert("上传失败");
            }
        });
    })
    $(".sbtn").click(function () {
        if($("#input").text()==""){
            alert("发布内容不能为空");
        }
        else {
            $.ajax({
                url: 'dynamic/add.action',
                type: 'post',
                dataType: 'JSON',
                data: {contentTest: $("#input").text(), contentImage: imgpath},
                success: function (data) {
                    $("#input").text("");
                    if(flag==1) {
                        $("#show").toggle();
                    }
                    alert(data.message);
                    $(".dialog").toggle();
                    blog_load();
                    getUserInfo();
                    // $.ajax({
                    //     url:'/dynamic/init.action',
                    //     type:'get',
                    //     dataType:'JSON',
                    //     success:function (res) {
                    //         var url=res.data[0].contentImage;
                    //         var img='<img src=" '+url+'"/>';
                    //         $('#ttest').append(img);
                    //     },
                    //     error:function (data) {
                    //         alert(data.code);
                    //     }
                    // })
                },
                error: function (data) {
                    alert(data.message);
                }
            });
        }
    })
});
var flag;
function changepic() {
    flag=0;
    var reads=new FileReader();
    f=document.getElementById("image").files[0];
    reads.readAsDataURL(f);
    reads.onload=function (e) {
        document.getElementById("preview").src=this.result;
    };
    $("#show").toggle();
    flag=1;
}