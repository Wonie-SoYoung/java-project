$(function () {
    //第一次选择文件
    $("#html5_1da550j8t1trc15hs1nrq11e51rcp3").change(function(){
        //赋值文件名
        var str=$(this).val();
        if(str!==""){
            var arr=str.split("\\");
            var file_name=arr[arr.length-1];
            $("#title").val(file_name);
        }
        if($(this)[0].files.length<10){
            var $file=$(this)[0];
            var formData = new FormData();
            for (var i=0;i<$file.files.length;i++){
                formData.append("file",$file.files[i]);
            }
            formData.append("type",$("input[name='uploadtype']").attr("value"));
            $.ajax({
                url:'/upload/file', /*接口域名地址*/
                type:'post',
                data: formData,
                contentType: false,
                processData: false,
                success:function(d){
                    var json=eval("("+d+")");
                    //隐藏选择文件显示填写信息
                    $("._2Hpe").hide();
                    $("._1zEz").show();
                    //判断上传类型
                    if(json.type==5){
                        $("._3_WY").show();
                        $("._3FRS").remove();
                        addImg(json,$("#html5_1da550j8t1trc15hs1nrq11e51rcp3")[0],0);
                        loadFileplan();
                        var imglingth=$("#html5_1da550j8t1trc15hs1nrq11e51rcp3")[0].files.length;
                        if(imglingth==9){
                            $("._25dy").hide();
                        }
                        $("._28Hw span").html("共"+imglingth+"张，还能上传"+(9-imglingth)+"张");
                        for (var i=0;i<json.proURLs.length;i++){
                            if($("input[name='proURL']").attr("value")==undefined || $("input[name='proURL']").attr("value")==''){
                                $("input[name='proURL']").attr("value",json.proURLs[i]);
                            }else{
                                $("input[name='proURL']").attr("value",$("input[name='proURL']").attr("value")+"+"+json.proURLs[i]);
                            }
                        }
                    }else{
                        $("._3FRS").show();
                        $("._3_WY").remove();
                        $("input[name='proURL']").attr("value",json.proURLs[0]);
                        $("._3FRS").children("._2oFr").children("._2egK").children("._1DX2").children("._21bR").attr("value",json.newNames[0]);
                        $("input[name='fileUrl']").val(str);
                        loadFileplan();
                    }
                }
            })
        }else{
            alert("最多9张图片哦！");
        }
    })

    //添加图片信息
    function addImg(json,file,type) {
        for(var i=0;i<json.newNames.length;i++){
            var windowURL = window.URL || window.webkitURL;
            var dataURL = windowURL.createObjectURL(file.files[i]);
            var content="<div class=\"_1I4i\" value=\""+json.proURLs[i]+"\">\n" +
                "<div class=\"_2NJN _1Gim _1LNE _2G4A hUPH _2xhE\">\n" +
                "\t<img class=\"_18wC\" src=\""+dataURL+"\" width='100%' height='100%' alt=\"1552436061635.jpg\">\n" +
                "\t<img src=\""+dataURL+"\" alt=\"1552436061635.jpg\" style=\"display: none;\">\n" +
                "</div>\n" +
                "<div class=\"_2egK\" style=\"display: none;\">\n" +
                "\t<button class=\"_33fg\" style=\"display: none;\">\n" +
                "\t\t<div class=\"_3aNE Qgfg sQis\">\n" +
                "\t\t\t<svg viewBox=\"0 0 60 60\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "\t\t\t\t<path d=\"M10.381 29.252c.148-11.521 9.534-20.815 21.09-20.815 11.65 0 21.092 9.443 21.092 21.091 0 11.65-9.443 21.092-21.091 21.092v-4.038c9.418 0 17.053-7.635 17.053-17.054 0-9.418-7.635-17.053-17.053-17.053-9.327 0-16.905 7.486-17.052 16.777h4.023a1 1 0 0 1 .828 1.56l-5.867 8.685a1 1 0 0 1-1.658 0L5.88 30.812a1 1 0 0 1 .828-1.56h3.674z\" fill=\"#B0B0B0\" fill-rule=\"evenodd\"></path>\n" +
                "\t\t\t</svg>\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"RP7m\">\n" +
                "\t<span>上传失败，<br>\n" +
                "\t\t点击重新上传</span>\n" +
                "\t\t</div>\n" +
                "\t</button>\n" +
                "\t<div class=\"_1DX2\" >\n" +
                "\t\t<div class=\"_21bR _2zIZ\" value=\""+json.newNames[i]+"\">\n" +
                "\t\t\t<svg class=\"\" viewBox=\"0 0 100 100\">\n" +
                "\t\t\t\t<circle class=\"_1NCc\" cx=\"50\" cy=\"50\" r=\"50\"></circle>\n" +
                "\t\t\t\t<path class=\"_3nDh\" d=\"\n" +
                "      M 50,50\n" +
                "      m 0,-39\n" +
                "      a 39,39 0 1 1 0,78\n" +
                "      a 39,39 0 1 1 0,-78\n" +
                "    \" stroke-width=\"8\" fill-opacity=\"0\"></path><path class=\"_1BRA\" d=\"\n" +
                "      M 50,50\n" +
                "      m 0,-39\n" +
                "      a 39,39 0 1 1 0,78\n" +
                "      a 39,39 0 1 1 0,-78\n" +
                "    \" stroke-width=\"8\" fill-opacity=\"0\" style=\"stroke-dasharray: 245.044px, 245.044px; stroke-dashoffset: 245.044px;\"></path></svg>\n" +
                "\t\t\t<div class=\"_2JSC _1P1v\"></div>\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"RP7m\">\n" +
                "\t\t\t<span>正在上传...</span>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</div>\n" +
                "<button class=\"smVw\" style=\"display: none;\" value=\""+json.proURLs[i]+"\">\n" +
                "\t<div class=\"_3_TC jZEM sQis\">\n" +
                "\t\t<svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "\t\t\t<path d=\"M24 21.03L17.97 15 15 17.97 21.03 24 15 30.03 17.97 33 24 26.97 30.03 33 33 30.03 26.97 24 33 17.97 30.03 15 24 21.03zM24 46C11.85 46 2 36.15 2 24S11.85 2 24 2s22 9.85 22 22-9.85 22-22 22z\" fill-opacity=\".3\" fill-rule=\"evenodd\">\n" +
                "\t\t\t</path>\n" +
                "\t\t</svg>\n" +
                "\t</div>\n" +
                "</button>\n" +
                "</div>";
            if(type==0){
                $("._1Vk3").prepend($(content));
            }else{
                $("._25dy").before($(content));
            }
        }
        $(".smVw").on("click",function () {
            delFile($(this));
        })
    }

    //上传图片
    $("#goon").change(function () {
        var extent=(9-parseInt($("._1I4i").length));
        if($(this)[0].files.length<=extent){
            var $file=$(this)[0];
            var formData = new FormData();
            for (var i=0;i<$file.files.length;i++){
                formData.append("file",$file.files[i]);
            }
            formData.append("type",$("input[name='uploadtype']").attr("value"));
            $.ajax({
                url:'/upload/file', /*接口域名地址*/
                type:'post',
                data: formData,
                contentType: false,
                processData: false,
                success:function(d){
                    var json=eval("("+d+")");
                    addImg(json,$("#goon")[0],1);
                    loadFileplan();
                    var imglingth=$("._1I4i").length;
                    if(imglingth==9){
                        $("._25dy").hide();
                    }
                    $("._28Hw span").html("共"+imglingth+"张，还能上传"+(9-imglingth)+"张");
                    for (var i=0;i<json.proURLs.length;i++){
                        if($("input[name='proURL']").attr("value")==undefined || $("input[name='proURL']").attr("value")==''){
                            $("input[name='proURL']").attr("value",json.proURLs[i]);
                        }else{
                            $("input[name='proURL']").attr("value",$("input[name='proURL']").attr("value")+"+"+json.proURLs[i]);
                        }
                    }
                }
            })
        }else{
            alert("最多上传9张哦！");
        }
    })
    //页面加载获取进度条
    function loadFileplan(){
        $("._21bR").each(function () {
            $(this).parent().parent().show();
            plan($(this));
        })
    }

    //获取进度条
    function plan(vv) {
        var intervalId = setInterval(function () {
            $.get("/upload/item/percent",{key:vv.attr("value")},function (data) {
                var percent = data;
                if (percent >= 100) {
                    clearInterval(intervalId);
                    percent = 100;//不能大于100
                    vv.parent().parent().next().show();
                    vv.parent().parent().remove();
                }
                vv.children("svg").children("._1BRA").css("stroke-dashoffset",((245.044)-parseInt(percent)*2.45044)+"px");
                vv.children("._2JSC").html(percent+"%");
            },"json")
        }, 100);
    }

    //上传视频或360封面
    $("#hiapkFile").change(function () {
        var isbtn=$("#preview-canvas").siblings(".smVw");
        if(isbtn.length>0){
            alert("超过");
            delFile(isbtn);
        }
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
                $img.css("background-image","url("+dataURL+")");
            }
        }
        //上传文件
        var formData = new FormData();
        formData.append("file",fileObj.files[0]);
        formData.append("type",$("input[name='uploadtype']").attr("value")+"change");
        $.ajax({
            url:'/upload/file', /*接口域名地址*/
            type:'post',
            data: formData,
            contentType: false,
            processData: false,
            success:function(res){
                var json=eval("("+res+")");
                var $proURL=json.proURLs[0];
                $("input[name='hiapkURL']").attr("value",$proURL);
                $("#prickbash").show();
                var intervalId = setInterval(function () {
                    $.get("/upload/item/percent",{key:json.newNames[0]},function (data) {
                        var percent = data;
                        if (percent >= 100) {
                            clearInterval(intervalId);
                            percent = 100;//不能大于100
                            var conent="<button class=\"smVw\" value=\""+$proURL+"\">\n" +
                                "\t<div class=\"_3_TC jZEM sQis\">\n" +
                                "\t\t<svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                                "\t\t\t<path d=\"M24 21.03L17.97 15 15 17.97 21.03 24 15 30.03 17.97 33 24 26.97 30.03 33 33 30.03 26.97 24 33 17.97 30.03 15 24 21.03zM24 46C11.85 46 2 36.15 2 24S11.85 2 24 2s22 9.85 22 22-9.85 22-22 22z\" fill-opacity=\".3\" fill-rule=\"evenodd\"></path>\n" +
                                "\t\t</svg>\n" +
                                "\t</div>\n" +
                                "</button>";
                            $("#preview-canvas").after($(conent));
                            $("#prickbash").hide();
                            $(".smVw").on("click",function () {
                                delFile($(this));
                            })
                        }
                        $("#prickbash").html("上传进度:"+percent+"%");
                    },"json")
                }, 100);
            }
        })
    })

    //删除文件
    function delFile(btn){
        var uploadType=$("input[name='uploadtype']").attr("value");
        if(uploadType=='5'){
            $.post("/upload/delFile",{fileKey:btn.attr("value")},function (d) {
                if(d==true){
                    btn.parent().remove();
                    var ind=$("input[name='proURL']");
                    ind.attr("value","");
                    for (var i=0;i<$("._1I4i").length;i++){
                        if(ind.attr("value")==undefined || ind.attr("value")==''){
                            ind.attr("value",$("._1I4i").eq(i).attr("value"));
                        }else{
                            ind.attr("value",ind.attr("value")+"+"+$("._1I4i").eq(i).attr("value"));
                        }
                    }
                }else{
                    alert("取消失败!");
                }
            },"json");
        }else{
            $("#preview-canvas").css("background-image","none");
            $.post("/upload/delFile",{fileKey:btn.attr("value")},function (d) {
                if(d==true){
                    btn.remove();
                    $("input[name='hiapkURL']").attr("value","");
                }else{
                    alert("取消失败!");
                }
            },"json");
        }
    }
})