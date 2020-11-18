$(function(){
    //控制播放和暂停按钮
    $(".tvp-play").click(function(){
        var content=$("#tvp-svg-play").attr("d");
        if(content=="M11,10 L18,13.74 18,22.28 11,26 Z M18,13.74 L26,18 26,18 18,22.28 Z"){
            $("#player_b86d095e_d12").trigger("play");
            $("#tvp-svg-play").attr("d","M11,10 L17,10 17,26 11,26 Z M20,10 L26,10 26,26 20,26 Z");
        }else if(content=="M11,10 L17,10 17,26 11,26 Z M20,10 L26,10 26,26 20,26 Z"){
            $("#player_b86d095e_d12").trigger("pause");
            $("#tvp-svg-play").attr("d","M11,10 L18,13.74 18,22.28 11,26 Z M18,13.74 L26,18 26,18 18,22.28 Z");
        }
    })
    //点击播放
    $(".tvp-button-play").click(function(){
        $(".tvp-overlay-poster").animate({"opacity":"0"},300,function(){
            $(".tvp-overlay-poster").hide();
        });
        $(this).addClass("donghua").animate({"opacity":"0"},300,function(){
            $(this).hide();
        });
        $("#player_b86d095e_d12").trigger("play");
        $("#tvp-svg-play").attr("d","M11,10 L17,10 17,26 11,26 Z M20,10 L26,10 26,26 20,26 Z");
    })

//显示视频播放时间和持续时间
    var video=$("#player_b86d095e_d12");
    var doubltvio=0;
    video.on('loadedmetadata', function() {
        doubltvio=parseFloat(657/parseFloat(video[0].duration));
    });
    video.on('timeupdate', function() {
        var theTime = parseInt(video[0].currentTime);// 秒
        var oneTime=parseInt(video[0].duration);
        $('.tvp-progress-play').css("width",(doubltvio*parseFloat(video[0].currentTime)));
        $(".tvp-time-panel-current").text(formatTime(theTime));
        $(".tvp-time-panel-total").text(formatTime(oneTime));

    });
    function formatTime(timeTemp) {
        var second =parseInt(timeTemp % 60);
        var minuteTemp = parseInt(timeTemp / 60);
        if (minuteTemp > 0) {
            var minute = minuteTemp % 60;
            return (minute > 9 ? (minute + "") : ("0" + minute)) + ":"	+ (second > 9 ? (second + "") : ("0" + second));
        }else {
            return "00:" + (second > 9 ? (second + "") : ("0" + second));
        }
    }

    //全屏显示
    $('.tvp-fullscreen-default').on('click', function() {
        //For Webkit
        video[0].webkitEnterFullscreen();
        //For Firefox
        video[0].mozRequestFullScreen();
        return false;
    });

})
