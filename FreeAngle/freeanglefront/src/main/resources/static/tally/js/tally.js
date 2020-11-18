$(function () {
    $(".react_tabs:not(._1vFD)").mouseover(function () {
        $(this).css("color","#2E97F4");
    })
    $(".react_tabs:not(._1vFD)").mouseout(function () {
        $(this).css("color","#595959");
    })
    //点击标签查询
    $(".react_tabs:not(._1vFD)").click(function () {
        var content=$(this).attr("value");
        location.href="/hotlabels/getLabel?hid="+content;
    })
})