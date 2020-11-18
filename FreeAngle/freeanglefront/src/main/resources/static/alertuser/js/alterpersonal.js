$(function(){
	//点击文本框出现焦点
    $("label input,.I24N,._3xVR,textarea").click(function () {
        $("label input,.I24N,._3xVR,textarea").css("border",".5px solid #dfdfdf");
        $(this).css("border","1px solid #7C7C7C");
    })
    $(document).click(function(e){
        var target = $(e.target);
        if(target.closest("input").length != 0||target.closest(".I24N").length != 0||target.closest("._3xVR").length != 0||target.closest("textarea").length != 0||target.closest("._2B3Z").length != 0) return;
        $("label input,.I24N,._3xVR,textarea").css("border",".5px solid #dfdfdf");
        $("._3caY,._2Xm-").hide();
        if($("#mapSearch").val()!=$("#ismapSearch").val()){
            $("#mapSearch").val("");
        }
    })
    $("._3caY").click(function(){
    	$(this).show();
    })
    $(".L7_O .Fgiu ._2KCW ._3YFh").click(function(){
    	$("._3tMe").val($(this).children().children().text());
    	$(".country").val($(this).children().children().attr("value"));
    })
    $("._2DQI:not(_2Y8n)").click(function(){
    	$(this).siblings().attr("class","_2DQI _1RS_");
    	$(this).attr("class",$(this).attr("class")+" _2Y8n");
    	$("input[name='sex']").val($(this).children("span").text());
    })

    //上传头像
    $("#headUrl").change(function () {
        //显示图片
        var $file = $(this);
        var fileObj = $file[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var $img = $("#preview-canvas");
        if (fileObj && fileObj.files && fileObj.files[0]) {
            var fileType = fileObj.files[0].name.substr(fileObj.files[0].name.lastIndexOf(".")).toUpperCase();
            if (fileType != ".BMP" && fileType != ".PNG" && fileType != ".GIF" && fileType != ".JPG" && fileType != ".JPEG") {
                alert("图片限于bmp,png,gif,jpeg,jpg格式");
            } else {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                $img.attr("src",dataURL);
            }
        }
    })

    //提交表单判断
    $(".tijiao").click(function () {
        var isNot=true;
        if(!isuserName()) isNot=false;
        if(!issex()) isNot=false;
        if(isNot){
            $("form").submit();
        }
    })
    //判断昵称
    function isuserName() {
        var isNot=true;
        var headURl=$("input[name='userName']").val();
        if(headURl==null || headURl==undefined || headURl==''){
            $("._3hMV").show();
            $("._3hMV").children().html("不能为空！");
            isNot=false;
        }
        return isNot;
    }

    //判断性别
    function issex() {
        var isNot=true;
        var headURl=$("input[name='sex']").val();
        if(headURl==null || headURl==undefined || headURl==''){
            alert("性别不能为空！");
            isNot=false;
        }
        return isNot;
    }
})
