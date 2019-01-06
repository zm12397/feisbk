$(document).ready(function () {
    load();
});

function load() {
    var id = getQueryString("id");
    var state = getQueryString("state");
    $.ajax({
        url: 'dynamic/info.action',
        type: 'post',
        data:{"id":id},
        dataType: 'JSON',
        async: false,
        success: function (res) {
            $('.follow_btn').attr("id","id_" + id);
            if(state == 0){
                $('.follow_btn').text("关注")
            }else{
                $('.follow_btn').text("已关注")
            }

            $(document).attr("title",res.data.username+"的主页");
            $('#friend_name').text(res.data.username);
            if(res.data.address!=null) {
                $('#friend_info li:nth-child(1) .personal_item_desc').text(res.data.address);
            }else{
                $('#friend_info li:nth-child(1) .personal_item_desc').text("未填写地址");
            }
            if(res.data.birthday!=null) {
                $('#friend_info li:nth-child(2) .personal_item_desc').text(res.data.birthday);
            }else{
                $('#friend_info li:nth-child(2) .personal_item_desc').text("未填写生日");
            }
            if(res.data.description!=null) {
                $('#friend_info li:nth-child(3) .personal_item_desc').text("简介:"+res.data.description);
            }else{
                $('#friend_info li:nth-child(3) .personal_item_desc').text("未填写简介");
            }

            var optionString = "";
            var blogList = res.data.blogList;
            if (blogList != null) {
                var dataLength = blogList.length;
                for (var i = 0; i < dataLength; i++) {
                    var url = blogList[i].contentImage;
                    // url = url.substring(7);
                    var time = blogList[i].modifyTime;
                    var commonTime = new Date(time).Format("yyyy年MM月dd日 hh:mm");
                    optionString += '<div class="content">'
                        + '<div class="face"><img src="image/tx.jpg" class="face_photo"></div>'
                        + '<div class="detail">'
                        + '<div class="name"><h4 class="txt1">' + res.data.username + '</h4></div>'
                        + '<div class="time"><h5 class="stime">' + commonTime + '</h5></div>'
                        + '<div><p class="text">' + blogList[i].contentTest + '</p></div>'
                        + '<div class="image"><img src="' + url + '" width="450px" height="300px" class="maomao"></div>'
                        + '</div></div>';
                }
                $('#friend_blog_list').append(optionString);
            } else {
                optionString += '<div class="content">写点什么记录一下生活吧！</div>';
                $('#friend_blog_list').append(optionString);
            }
        }
    });
}

