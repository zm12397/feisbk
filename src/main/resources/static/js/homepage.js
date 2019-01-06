var my_id;
$(document).ready(function () {
    getUserInfo();
    blog_load();
    recommend_load();
    followed_load();
    follower_load();
});

//主页好友推荐模块加载
function recommend_load() {
    $('#my_recommend_list li').remove();
    $.ajax({
        url: 'recommend/my.action',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (data) {
            var optionString = "";
            if(data.data!=null){
                for (var i = 0; i < data.data.length; i++) {
                    optionString += '<li class=\"recommend_item\">'
                        + '<div class="recommend_item_img"  onclick=gotoFriend('+ data.data[i].id + ','+0+ ')>'
                        + '<img src=\"image/touxiang1.jpg\" class=\"touxiang\">'
                        + '</div><div class="recommend_item_follow follow_btn" id="rc_'+data.data[i].id + '">关注</div>'
                        + '<div class="recommend_item_info" onclick=gotoFriend('+ data.data[i].id + ','+0+ ')>'
                        + '<div class="recommend_item_name txt1">'
                        + data.data[i].username
                        + '</div>'
                        + '<div class="recommend_item_desc">'
                        + data.data[i].description
                        + '</div></div></li>'
                }
                $('#my_recommend_list').append(optionString);
            }else {
                optionString += '<li class=\"recommend_item\">暂时没有推荐的人呐，去找找有趣的人吧!</li>';
                $('#my_recommend_list').append(optionString);
            }
        }
    });
}

//主页关注模块加载
function followed_load() {
    $('#my_followed_list li').remove();
    $.ajax({
        url: 'follow/getto.action',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (data) {
            var optionString = "";
            if (data.data != null) {
                var dataLength = data.data.length;
                // var flag = -1;
                $('#show_followed strong').text(dataLength);
                for (var i = 0; i < dataLength; i++) {
                    // //flag = 1为关注，flag = 3为互关
                    // flag = 1;
                    // if(data.data.followeds!=null){
                    //     var followedLength = data.data.followeds.length;
                    //     for(var j = 0; j < followedLength; j++){
                    //         if(data.data.followeds[j].id == my_id){
                    //             flag = 3;
                    //             break;
                    //         }
                    //     }
                    // }

                    optionString += '<li class=\"recommend_item\">'
                        + '<div class="recommend_item_img"  onclick=gotoFriend('+ data.data[i].id + ','+1+ ')>'
                        + '<img src=\"image/touxiang1.jpg\" class=\"touxiang\">'
                        + '</div><div class="recommend_item_follow follow_btn" id="fd_'+data.data[i].id+'">';
                    // if(flag == 1) {
                        optionString += '已关注';
                    // }else{
                    //     optionString += '互相关注';
                    // }

                    optionString += '</div>'
                        + '<div class="recommend_item_info" onclick=gotoFriend('+ data.data[i].id + ','+1+ ')>'
                        + '<div class="recommend_item_name txt1">'
                        + data.data[i].username
                        + '</div>'
                        + '<div class="recommend_item_desc">'
                        + data.data[i].description
                        + '</div></div></li>'
                }
                $('#my_followed_list').append(optionString);
            } else {
                $('#show_followed strong').text(0);
                optionString += '<li class=\"recommend_item\">暂时没有关注的人，去找找有趣的人吧!</li>';
                $('#my_followed_list').append(optionString);
            }
        }
    });
}

//主页粉丝模块加载
function follower_load() {
    $('#my_follower_list li').remove();
    $.ajax({
        url: 'follow/getbe.action',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (data) {
            var optionString = "";
            if (data.data != null) {
                // var flag = -1;
                var dataLength = data.data.length;
                $('#show_follower strong').text(dataLength);
                for (var i = 0; i < data.data.length; i++) {
                    var followers = data.data[i].followers;
                    var flag = 2;//可以关注
                    for(var j in followers){
                        if(followers[j].endNode.id == my_id){
                            flag = 1;//已经互关了
                            break;
                        }
                    }
                    var state = 0;
                    var mydiv = '</div><div class="recommend_item_follow follow_btn"  id="fr_'+data.data[i].id+'">关注';
                    var disabled = ""
                    if(flag == 1){
                        state = 1;
                        mydiv = '</div><div class="recommend_item_follow follow_btn" disabled="disabled"  id="fr_'+data.data[i].id+'">互粉';
                    }else{
                        state = 0;
                        mydiv = '</div><div class="recommend_item_follow follow_btn"  id="fr_'+data.data[i].id+'">关注';
                    }
                    optionString += '<li class=\"recommend_item\">'
                        + '<div class="recommend_item_img"  onclick=gotoFriend('+ data.data[i].id + ','+state+ ')>'
                        + '<img src=\"image/touxiang1.jpg\" class=\"touxiang\">';
                        + '</div><div class="recommend_item_follow follow_btn"  id="fr_'+data.data[i].id+'">';
                    optionString += mydiv;
                    optionString += '</div>'
                        + '<div class="recommend_item_info" onclick=gotoFriend('+ data.data[i].id + ','+state+ ')>'
                        + '<div class="recommend_item_name txt1">'
                        + data.data[i].username
                        + '</div>'
                        + '<div class="recommend_item_desc">'
                        + data.data[i].description
                        + '</div></div></li>'
                }
                $('#my_follower_list').append(optionString);
            } else {
                $('#show_follower strong').text(0);
                optionString += '<li class=\"recommend_item\">咦!怎么没有粉丝呀，多发点博客吸引粉丝吧！</li>';
                $('#my_follower_list').append(optionString);
            }
        }
    });
}

//主页博客模块加载
function blog_load() {
    $('#blog_list div').remove();
    $.ajax({
        url: 'dynamic/init.action',
        type: 'get',
        dataType: 'JSON',
        async: false,
        success: function (res) {
            var optionString = "";
            if (res.data != null) {
                var dataLength = res.data.length;
                for (var i = 0; i < dataLength; i++) {
                    var url = res.data[i].contentImage;
                    /*console.log(url)
                    url = url.substring(8);*/
                    var time = res.data[i].modifyTime;
                    var commonTime = new Date(time).Format("yyyy年MM月dd日 hh:mm");
                    optionString += '<div class="content">'
                        + '<div class="face"><img src="image/tx.jpg" class="face_photo"></div>'
                        + '<div class="detail">'
                        + '<div class="name"><h4 class="txt1">' + res.data[i].username + '</h4></div>'
                        + '<div class="time"><h5 class="stime">' + commonTime + '</h5></div>'
                        + '<div><p class="text">' + res.data[i].contentTest + '</p></div>'
                        + '<div class="image"><img src="' + url + '" width="450px" height="300px" class="maomao"></div>'
                        + '</div></div>';
                }
                $('#blog_list').append(optionString);
            } else {
                $('#show_blog strong').text(0);
                optionString += '<li class=\"recommend_item\">写点什么记录一下生活吧！</li>';
                $('#blog_list').append(optionString);
            }
        },
        error: function (data) {
            alert("blog:"+data.message);
        }
    })
}

//博客时间格式化
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

//获取当前用户信息,包括我的博客
function getUserInfo() {
    $('#my_blog_list div').remove();
    $.ajax({
        url: 'dynamic/info.action',
        type: 'post',
        data:{"id":null},
        dataType: 'JSON',
        async: false,
        success: function (res) {
            my_id = res.data.id;
            $('#my_name').text(res.data.username);
            if(res.data.address!=null) {
                $('#my_info li:nth-child(1) .personal_item_desc').text(res.data.address);
            }else{
                $('#my_info li:nth-child(1) .personal_item_desc').text("未填写地址");
            }
            if(res.data.birthday!=null) {
                $('#my_info li:nth-child(2) .personal_item_desc').text(res.data.birthday);
            }else{
                $('#my_info li:nth-child(2) .personal_item_desc').text("未填写生日");
            }
            if(res.data.description!=null) {
                $('#my_info li:nth-child(3) .personal_item_desc').text("简介:"+res.data.description);
            }else{
                $('#my_info li:nth-child(3) .personal_item_desc').text("未填写简介");
            }

            var optionString = "";
            var blogList = res.data.blogList;
            if (blogList != null) {
                var dataLength = blogList.length;
                $('#show_blog strong').text(dataLength);
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
                $('#my_blog_list').append(optionString);
            } else {
                optionString += '<div class="content">写点什么记录一下生活吧！</div>';
                $('#my_blog_list').append(optionString);
            }
        }
    });
}

//跳转到好友页面
function gotoFriend(id,state) {
    window.open("./friend_homepage.html?id="+id+"&state="+state);
    // window.location.href = "./friend_homepage.html?id=" + id;
}

//搜索按钮事件
function doSearch() {
    var username = $("#s_input").val();
    $('#my_search_list li').remove();
    $.ajax({
        url: "follow/get.action",
        data: {"username": username},
        type: 'get',
        dataType: 'JSON',
        success: function (data) {
            console.log(data)
            var optionString = "";
            if (data.data != null) {
                var dataLength = data.data.length;
                $('#search_result_count').text("搜索结果: 共" + dataLength + "条");
                for (var i = 0; i < dataLength; i++) {
                    optionString += '<li class=\"recommend_item\">'
                        + '<div class="recommend_item_img" onclick=gotoFriend('+ data.data[i].id + ','+0+')>'
                        + '<img src=\"image/touxiang1.jpg\" class=\"touxiang\">'
                        + '</div><div class="recommend_item_follow follow_btn" id="sc_'+data.data[i].id+'">关注</div>'
                        + '<div class="recommend_item_info" onclick=gotoFriend('+ data.data[i].id + ','+0+')>'
                        + '<div class="recommend_item_name txt1">'
                        + data.data[i].username
                        + '</div>'
                        + '<div class="recommend_item_desc">'
                        + data.data[i].description
                        + '</div></div></li>'
                }
                $('#my_search_list').append(optionString);
                $("#blog_list_tab").hide();
                $("#followed_list_tab").hide();
                $("#follower_list_tab").hide();
                $('#search_list_tab').show();
            } else {
                $('#search_result_count').text("搜索结果: 共0条");
                optionString += '<li class=\"recommend_item\">没有搜索到呢，重新输入一下吧！</li>';
                $('#my_search_list').append(optionString);
            }
        },
        error: function (data) {
            alert("search:"+data.message);
        }
    });
}
