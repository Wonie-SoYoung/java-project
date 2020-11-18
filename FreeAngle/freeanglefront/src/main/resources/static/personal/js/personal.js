$(function () {
    $("#backgroundFile").change(function(){
        var $file=$(this)[0];
        var formData = new FormData();
        formData.append("file",$file.files[0]);
        formData.append("fileUrl",$(".-RfM").attr("value"));
        $.ajax({
            url:'/upload/alterbackdrop', /*接口域名地址*/
            type:'post',
            data: formData,
            contentType: false,
            processData: false,
            success:function(d){
                //显示图片
                var fileObj = $file;
                var windowURL = window.URL || window.webkitURL;
                var dataURL;
                var $img = $("#userback");
                if (fileObj && fileObj.files && fileObj.files[0]) {
                    var fileType = fileObj.files[0].name.substr(fileObj.files[0].name.lastIndexOf(".")).toUpperCase();
                    if (fileType != ".BMP" && fileType != ".PNG" && fileType != ".GIF" && fileType != ".JPG" && fileType != ".JPEG") {
                        alert("图片限于bmp,png,gif,jpeg,jpg格式");
                    } else {
                        dataURL = windowURL.createObjectURL(fileObj.files[0]);
                        $img.attr("src",dataURL);
                    }
                }
                var json=eval("("+d+")");
                $(".-RfM").attr("value",json.newobjectName);
            }
        })
    })
    //点击窗口×
    $(".rc-dialog-close").click(function () {
        $("#wicket").hide();
        $(".rc-dialog-mask").css("opacity","0");
        $(this).parent().parent().css({height:'0px'});
        $("#newmessage").val("");
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
        if($value.attr("class")=="Xwom _123Z _1Don _2oaL _1TkV"){
            $.get("/attention/execute",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _1Don _2oaL _1LJQ _1TkV _3-aF");
                    $value.children().children().html("已关注");
                }else{
                    alert("关注失败!");
                }
            })
        }else if($value.attr("class")=="Xwom _123Z _1Don _2oaL _1LJQ _1TkV _3-aF"){
            $.get("/attention/cancel",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _1Don _2oaL _1TkV");
                    $value.children().children().html("关注");
                }else{
                    alert("取消失败!");
                }
            })
        }
    },'json');
}
//私信
function privateletter() {
    $("#wicket").show();
    $(".rc-dialog-wrap .rc-dialog").animate({height:'75%'},200);
    $(".rc-dialog-mask").animate({opacity:1},500);
    //请求ajax查询聊天记录
    $.get("/user/sessionUser",{},function (user) {
        var auid=$(".ZLxF").attr("value");
        var buid=user['id'];
        $.get("/chat/list",{auid:auid,buid:buid},function (data) {
            $("._3Qiz").children().remove();
            var times="";
            for (var i=0;i<data.length;i++){
                var content="";
                var datas=data[i]['createTime'].split(" ");
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
        var talkid=$(".ZLxF").attr("value");
        $.get("/chat/add",{talkid:talkid,content:message},function (data) {
            if(data>0){
                privateletter();
            }
        },'json');
    }
    $("#newmessage").val("");
}
