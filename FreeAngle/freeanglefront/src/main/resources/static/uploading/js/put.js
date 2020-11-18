$(function () {
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
    //点击拍摄设备
    $("._2eASs").click(function () {
        var conul=$(this).children("._3caY").children("._1B4y").children(".Fgiu").children("._2KCW");
        var isNot=conul.children().length;
        if(isNot<1){
            $.post("/facility/getFather",{},function (data) {
                for(var i=0;i<data.length;i++){
                    //获取父元素
                    var content="<li class=\"_3xVL\" role=\"menuitem\" value=\""+data[i]['id']+"\">" +
                        "<span class=\"_1skW\">" +
                        "<span class=\"_2hdk\">"+data[i]['fName']+"</span>" +
                        "<div class=\"_3xGm _1a-Q\">" +
                        "<div class=\"lLO5 jZEM sQis _1Fj7\">" +
                        "<svg viewBox=\"0 0 48 48\">" +
                        "<path fill=\"#333\" fill-rule=\"nonzero\" d=\"M18.344 24.728l11.313 11.313.707-.707-11.313-11.313 11.313-11.314-.707-.707-12.021 12.021z\"></path>" +
                        "</svg>" +
                        "</div>" +
                        "</div>" +
                        "</span>" +
                        "<ul class=\"_3dXC\"></ul></li>";
                    conul.append($(content));
                }
                conul.children("._3xVL").on("mouseenter",function () {
                    var mli=$(this);
                    if($(this).children("._3dXC").children().length<1){
                        //获取子元素
                        $.post("/facility/getChild/"+$(this).attr("value"),{},function (data) {
                            for (var i=0;i<data.length;i++){
                                var content="<li class=\"_3YFh\" role=\"menuitem\" value=\""+data[i]['id']+"\"><span class=\"_1skW\"><span class=\"_2hdk\">"+data[i]['fName']+"</span></span></li>";
                                mli.children("._3dXC").append($(content));
                            }
                            $("._3YFh").on("mouseenter",function () {
                                $(this).css("color","#4b4b4b").siblings().css("color","#b0b0b0");
                            })
                            $("._3YFh").on("click",function () {
                                $("#equipment").attr("value",$(this).attr("value"));
                                $("#equipment").prev().attr("value",$(this).children("._1skW").children("._2hdk").html());
                            })
                        },'json');
                    }
                    $(this).css({"background-color":"#f7f7f7","color":"#4b4b4b","font-weight":"400"}).siblings().css({"background-color":"#fff","color":"#b0b0b0","font-weight":"400"});
                    $(this).children("._3dXC").show();
                    $(this).siblings().children("._3dXC").hide();
                })
            },'json');
        }
        $(this).children("._3caY").show();
    })

    //标签事件
    $(".uCPq").click(function () {
        if($(this).attr("class")=='uCPq _17hM'){
            for (var i=0;i<$("._3ODu").length;i++){
                if($("._3ODu").eq(i).text()==$(this).text()){
                    $("._3ODu").eq(i).remove();
                    $(this).attr("class","uCPq");
                }
            }
        }else{
            insertLabel($(this).html());
            $(this).attr("class","uCPq _17hM");
        }
    })
    //添加标签
    function insertLabel(name) {
        var content="<span class=\"_3ODu\">"+name+"<button type=\"button\" class=\"_3InF\"><div class=\"_2EEW jZEM sQis\"><svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\"><path d=\"M24 20.464l10.253-10.253a1 1 0 0 1 1.414 0l2.122 2.122a1 1 0 0 1 0 1.414L27.536 24l10.253 10.253a1 1 0 0 1 0 1.414l-2.122 2.122a1 1 0 0 1-1.414 0L24 27.536 13.747 37.789a1 1 0 0 1-1.414 0l-2.122-2.122a1 1 0 0 1 0-1.414L20.464 24 10.211 13.747a1 1 0 0 1 0-1.414l2.122-2.122a1 1 0 0 1 1.414 0L24 20.464z\" fill-rule=\"evenodd\" fill=\"#707473\"></path></svg></div></button></span>";
        $("._3s76").before($(content));
        $("._3InF").on("click",function () {
            $("input,.I24N,._3xVR,textarea").css("border",".5px solid #dfdfdf");
            for (var i=0;i<$(".uCPq").length;i++){
                if($(".uCPq").eq(i).text()==$(this).parent().text()){
                    $(this).parent().remove();
                    $(".uCPq").eq(i).attr("class","uCPq");
                    return;
                }
            }
            $(this).parent().remove();
        })
    }
    //按下Enter添加
    $(document).keyup(function(event){
        if(event.keyCode == 13){
            var titlevalue=$("._3s76 input").val();
            if(titlevalue!=""&&titlevalue!=null){
                if($("._3ODu").size()<=20){
                    var isNot=true;
                    $("._3ODu").each(function(){
                        if($(this).text()==titlevalue){
                            isNot=false;
                        }
                    })
                    if(isNot){
                        insertLabel(titlevalue);
                        $("._3s76 input").val("");
                    }else{
                        alert("该标签已经存在")
                    }
                }
            }
        }
    })

    //位置事件
    $(".Vnj8").click(function () {
        $(this).next().show();
    })
    $("#mapSearch").on('input propertychange',function(){
        if(!($('#mapSearch').val()=='')){
            var siteul=$(this).parent().parent().next().children().children().children();
            $.ajax({
                url: "http://api.map.baidu.com/place/v2/suggestion?query="+$('#mapSearch').val()+"&region=全国&output=json&ak=3xU4kuF89HXe1EsZAuQbu1IYqe4ltG37",
                type: 'GET',
                dataType: 'JSONP',//重点在这里，加上这个属性就可以跨域请求了
                success: function (data) {
                    if(data.status==0 && data.message=='ok'){
                        //清空ul
                        siteul.children().remove();
                        for (var i=0;i<data.result.length;i++){
                            var content="<li class=\"_3YFh _3JGv\" role=\"menuitem\"><span class=\"_1skW\"><span class=\"_2hdk\">"+data.result[i].province+data.result[i].city+data.result[i].district+data.result[i].name+"</span></span></li>";
                            siteul.append($(content));
                        }
                        $("._3YFh").on("mouseenter",function () {
                            $(this).css("color","#4b4b4b").siblings().css("color","#b0b0b0");
                        })
                        $("._3YFh").on("click",function () {
                            $("#mapSearch").val($(this).children().children().html());
                            $("#ismapSearch").val($(this).children().children().html());
                        })
                    }
                }
            });
        }
    })
    //隐私版权事件
    $(".CdlZ").click(function () {
        $(this).children("._3erK").remove();
        $(this).attr("class","CdlZ qa6C -Z6V").append($("<div class=\"_3erK jZEM sQis\"><svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 37 32\"><path fill=\"#1088f2\" d=\"M12.68 30.01L.547 17.299a2.023 2.023 0 0 1 0-2.766l2.64-2.766a1.807 1.807 0 0 1 2.64 0l8.174 8.563L31.508 1.99a1.807 1.807 0 0 1 2.64 0l2.64 2.766a2.023 2.023 0 0 1 0 2.766L15.321 30.011a1.807 1.807 0 0 1-2.64 0z\"></path></svg></div>")).siblings(".CdlZ").attr("class","CdlZ -Z6V").children("._3erK").remove();
    })

    //提交验证
    $(".Xwom").click(function () {
        var isNot=true;
        if(!proURLisNot()) isNot=false;
        if(!proNameisNot()) isNot=false;
        if(!facilityisNot()) isNot=false;
        if(!planisNot()) isNot=false;
        if(isNot){
            labelInputValue();
            privatevalue();
            $("form").append($("#html5_1da550j8t1trc15hs1nrq11e51rcp3"));
            $("form").submit();
        }
    })

    //验证作品
    function proURLisNot() {
        var value=$("input[name='proURL']").val();
        if(value==null || value==undefined || value==''){
            alert("请选择作品！");
            return false;
        }else{
            return true;
        }
    }
    //验证作品名称
    function proNameisNot() {
        var value=$("input[name='pName']").val();
        //获取提示元素
        var hint=$("input[name='pName']").parent().parent().next();
        if(value==null || value==undefined || value==''){
            hint.show();
            hint.children("span").html("不能为空");
            return false;
        }else if(value.length<4){
            hint.show();
            hint.children("span").html("最小长度不能少于 3 个字符");
            return false;
        }else{
            hint.hide();
            hint.children("span").html("");
            return true;
        }
    }
    //验证拍摄设备
    function facilityisNot() {
        var value=$("input[name='facilityId']").val();
        //获取提示元素
        var hint=$("input[name='facilityId']").parent().parent().parent().parent().parent().next();
        if(value==null || value==undefined || value==''){
            hint.show();
            hint.children("span").html("不能为空");
            return false;
        }else{
            hint.hide();
            hint.children("span").html("");
            return true;
        }
    }
    //获取标签表单数据
    function labelInputValue() {
        var label=$("input[name='label']");
        for (var i=0;i<$("._3ODu").length;i++){
            if(label.val()=='' || label.val()==null || label.val()==undefined){
                label.val($("._3ODu").eq(i).text());
            }else{
                label.val(label.val()+"+"+$("._3ODu").eq(i).text());
            }
        }
    }
    //获取版权和隐私
    function privatevalue() {
        var privates=$("._3Q2E");
        var copyright=$("input[name='copyrightId']");
        var privacy=$("input[name='privacyId']");
        for (var i=0;i<privates.length;i++){
            if(privates.eq(i).text()=='版权'){
                copyright.val(privates.eq(i).siblings(".qa6C").attr("value"));
            }else if(privates.eq(i).text()=='隐私'){
                privacy.val(privates.eq(i).siblings(".qa6C").attr("value"));
            }
        }
    }
    //判断验证进度
    function planisNot() {
        var isNot=true;
        for (var i=0;i<$("._2JSC").length;i++){
            alert("正在上传！");
            isNot=false;
        }
        return isNot;
    }
})