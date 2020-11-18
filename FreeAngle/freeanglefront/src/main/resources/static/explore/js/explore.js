$(function () {
    //悬停图片特效
    $("._2pco").on("mouseover",function () {
        $(this).children("._2cPJ").children("._1Zls").css("opacity","0.3");
        $(this).children("._1BMp").css("opacity","1");
        $(this).children("._3Wrm").css("opacity","1");
    })
    $("._2pco").on("mouseout",function () {
        $(this).children("._2cPJ").children("._1Zls").css("opacity","0");
        $(this).children("._1BMp").css("opacity","0");
        $(this).children("._3Wrm").css("opacity","0");
    })
    //悬停标签特效
    $(".react-tabs").mouseover(function () {
        $(this).css("color","#2E97F4");
    })
    $(".react-tabs").mouseout(function () {
        $(this).css("color","#595959");
    })
    $("._1vFD").mouseover(function () {
        $(this).css("color","#262626");
    })
    $("._1vFD").mouseout(function () {
        $(this).css("color","#262626");
    })
    //点击标签查询
    $(".react-tabs:not(._1vFD)").click(function () {
        var content=$(this).html();
        if(content!='全部'){
            location.href="/production/getProList?label="+content;
        }else{
            location.href="/production/getProList";
        }
    })
})