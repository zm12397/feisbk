var current;
$(document).ready(function () {
    $(document).on("click", ".follow_btn", function () {
        var text = $(event.target).html();
        // console.log(event.target.id.substring(3))
        if (text == '关注') {
            $.ajax({
                url: 'follow/follow.action',
                type: 'post',
                dataType: 'json',
                data: {"id": event.target.id.substring(3)},
                async: false,
                success: function (data) {
                    if (data.code == "0000") {
                        $(event.target).text('已关注');
                        follower_load();
                        followed_load();
                    } else {
                        alert(data.message);
                    }
                }
            });
        }
    });

    $(document).on("mouseenter", ".follow_btn", function (e) {
        current = event.target;
        var text = $(event.target).html();
        if (text == '已关注') {
            var X = $(e.target).offset().top;
            var Y = $(e.target).offset().left;
            var H = $(e.target).outerHeight();
            $("#follow_select").css("left", Y);
            $("#follow_select").css("top", X + H);
            $("#follow_select").show();
        }
    });

    $(document).on("mouseleave", ".follow_btn", function (e) {
        $("#follow_select").hide();
    });

    $(document).on("mouseenter", "#follow_select", function (e) {
        $("#follow_select").show();
        $('#unfollow').click(function () {
            $.ajax({
                url: 'follow/unfollow.action',
                type: 'post',
                dataType: 'json',
                data: {id: current.id.substring(3)},
                async: false,
                success: function (data) {
                    if (data.code == "0000") {
                        $(current).text("关注");
                        followed_load();
                        follower_load();
                    } else {
                        alert(data.message);
                    }
                }
            });
            $("#follow_select").hide();
        })
    });

    $(document).on("mouseleave", "#follow_select", function (e) {
            $("#follow_select").hide();
        });

});