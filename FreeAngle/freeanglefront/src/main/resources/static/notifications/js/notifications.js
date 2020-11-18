$(function () {
    //分页提交
    $(".rc-pagination-prev:not(.rc-pagination-disabled),.rc-pagination-next:not(.rc-pagination-disabled)").click(function () {
        var currPage=$(this).attr("value");
        var type=$("._1fvP").attr("value");
        location.href="/messages/notifications?type="+type+"&currPage="+currPage;
    })
    $(".pageCount").bind('keypress',function (event) {
        if(event.keyCode == 13){
            var type=$("._1fvP").attr("value");
            location.href="/messages/notifications?type="+type+"&currPage="+$(this).val();
        }
    })
    $(".pageCount").keyup(function () {
        var value=$(this).val();
        if(isNaN(value)){
            $(this).val(1);
        }else{
            if(value>$(this).attr("size")){
                $(this).val($(this).attr("size"));
            }
        }
    })

    //评论
    $(".Ax8v").click(function () {
        $(this).parent().parent().parent().parent().siblings("._2bF5").show();
    })
    $(".Bmsa .o1Nm").click(function () {
        $("._2bF5").hide();
    })
    $("#shuru").keyup(function () {
        var content=$(this).val();
        if(content==null || content==undefined || content==''){
            $(".Bmsa .Ax8v").attr("disabled","true");
        }else{
            $(".Bmsa .Ax8v").removeAttr("disabled");
        }
    })
    $(".Bmsa .Ax8v").click(function () {
        var pid=$("._2bF5").attr("value");
        var content=$("#shuru").val();
        var replyid=$(this).attr("value");
        $.get("/comment/add",{pid:pid,content:content,replyid:replyid},function (data) {
            if(data>0){
                $("#shuru").val("");
                $("._2bF5").hide();
                alert("回复成功！");
            }
        },'json');
    })
    //点击窗口×
    $(".rc-dialog-close").click(function () {
        $("#wicket").hide();
        $(".rc-dialog-mask").css("opacity","0");
        $(this).parent().parent().css({height:'0px'});
        $("#newmessage").val("");
        window.location.reload();
    })
    //点击回车
    $("#newmessage").bind('keypress',function (event) {
        if(event.keyCode == 13){
            event.preventDefault();
            var searchvalue=$("#newmessage").val();
            addmessage(searchvalue);
        }
    })
})
//关注
function executeattention(value) {
    var uid=0;
    var $value=$(value);
    $.get("/user/sessionUser",{},function (user) {
        uid=user['id'];
        var oruid=$value.attr("value");
        if($value.attr("class")=="Xwom _123Z _1Don undefined _1TkV"){
            $.get("/attention/execute",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _1Don _1LJQ undefined _1TkV _3-aF");
                    $value.children().children().html("已关注");
                }else{
                    alert("关注失败!");
                }
            })
        }else if($value.attr("class")=="Xwom _123Z _1Don _1LJQ undefined _1TkV _3-aF"){
            $.get("/attention/cancel",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _1Don undefined _1TkV");
                    $value.children().children().html("关注");
                }else{
                    alert("取消失败!");
                }
            })
        }
    },'json');
}

//私信
function openPrivate(uid) {
    $("#wicket").show();
    $(".rc-dialog-wrap .rc-dialog").animate({height:'75%'},200);
    $(".rc-dialog-mask").animate({opacity:1},500);
    $("#messagetijiao").attr("value",uid);
    //请求ajax查询聊天记录
    $.get("/user/sessionUser",{},function (user) {
        var auid=uid;
        var buid=user['id'];
        $.get("/chat/list",{auid:auid,buid:buid},function (data) {
            $.get("/messages/update",{type:$("._1fvP").attr("value")},function () {},'json');
            $("._3Qiz").children().remove();
            var times="";
            for (var i=0;i<data.length;i++){
                var content="";
                var datas=data[i]['createTime'].split(" ");
                $("#rcDialogTitle1").children("span").html("与 "+data[i]['cozeUser']['userName']+" 对话");
                if(data[i]['cozeUser']['id']==buid){
                    content+="<div class=\"_DSO zVtP\">\n";
                    if(times!=datas[0]){
                        content+="\t<div class=\"_1vau\"><span>"+datas[0]+"</span></div>\n";
                        times=datas[0];
                    }
                    content+="\t<div class=\"_1a3E\">\n" +
                        "\t\t<div class=\"JTA4 _1cx7 undefined\">\n" +
                        "\t\t\t<div class=\"_3llO _1Gim _1LNE hUPH\"><img src=\"https://free-angle.oss-cn-beijing.aliyuncs.com/"+data[i]['cozeUser']['headURL']+"\" alt=\"\" width=\"100%\" height=\"100%\"></div>\n" +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"b_xy\">"+data[i]['content']+"</div>\n" +
                        "\t</div>\n" +
                        "\t<div class=\"_3l9m\"><span>"+datas[1]+"</span></div>\n" +
                        "</div>";
                }else{
                    content+="<div class=\"_DSO\">\n";
                    if(times!=datas[0]){
                        content+="\t<div class=\"_1vau\"><span>"+datas[0]+"</span></div>\n";
                        times=datas[0];
                    }
                    content+="\t<div class=\"_1a3E\">\n" +
                        "\t\t<div class=\"JTA4 _1cx7 undefined\">\n" +
                        "\t\t\t<div class=\"_3llO _1Gim _1LNE hUPH\"><img src=\"https://free-angle.oss-cn-beijing.aliyuncs.com/"+data[i]['cozeUser']['headURL']+"\" alt=\"\" width=\"100%\" height=\"100%\"></div>\n" +
                        "\t\t</div>\n" +
                        "\t\t<div class=\"b_xy\">"+data[i]['content']+"</div>\n" +
                        "\t</div>\n" +
                        "\t<div class=\"_3l9m\"><span>"+datas[1]+"</span></div>\n" +
                        "</div>";
                }
                $("._3Qiz").append($(content));
                var divscll = document.getElementById('myby');
                divscll.scrollTop = divscll.scrollHeight;
            }
        },'json');
    },'json');
}
$("#newmessage").keyup(function () {
    var remain = $(this).val().length;
    if(remain>0){
        $("._1Jv9").removeAttr("disabled");
    }else{
        $("._1Jv9").attr("disabled","true");
    }
    if(remain > 500){
        $("._24zN").children("span").html("字数超过限制！");
        $(this).val($(this).val().substring(0,500));
    }else{
        var result = 500 - remain;
        $("._24zN").children("span").html("剩余可输入"+result+"个字");
        pattern = '还可以输入' + result + '字符';
    }
})
function addmessage(message) {
    if(message!=null){
        var talkid=$("#messagetijiao").attr("value");
        $.get("/chat/add",{talkid:talkid,content:message},function (data) {
            if(data>0){
                openPrivate(talkid);
            }
        },'json');
    }
    $("#newmessage").val("");
}