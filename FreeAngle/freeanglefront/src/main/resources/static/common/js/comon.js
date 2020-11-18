$(function(){
	//悬停事件
	$("._2eAS").mouseover(function(){
		$(this).children("._27sX").show();
	})
	$("._2eAS").mouseout(function(){
		$(this).children("._27sX").hide();
	})
	$("._3st1").mouseover(function(){
		$(this).children("svg").children("path").attr("fill","#FFFFFF")
	})
	$("._3st1").mouseout(function(){
		$(this).children("svg").children("path").attr("fill","#9fa3a6")
	})
	$("._1wq2").mouseover(function(){
		$(this).css({"width":"180px","background-color":"#1A1A1A"});
	})
	$("._1wq2").mouseout(function(){
		if($(this).children("input").val()==''){
			$(this).css({"width":"0px","background-color":"transparent"});
		}
	})
	
	//搜索
	$("#search").click(function () {
		var searchvalue=$("input[name='searchvalue']").val();
        grabble(searchvalue);
    })
    $("input[name='searchvalue']").bind('keypress',function (event) {
        if(event.keyCode == 13){
            var searchvalue=$("input[name='searchvalue']").val();
            grabble(searchvalue);
        }
    })
	//进行搜索
	function grabble(searchvalue) {
        if(searchvalue!=null && searchvalue!=undefined && searchvalue!=''){
			location.href="/hunt/info?value="+searchvalue+"&type=video";
        }
    }

    //请求消息
    var websocket = null;
    //浏览器是否支持
    if ('WebSocket' in window) {
        // 上面我们给webSocket定位的路径
        websocket = new WebSocket('ws://localhost:8080/webSocket');
    } else {
        alert('该浏览器不支持websocket!');
    }
    //建立连接
    websocket.onopen = function (event) {
        console.log('建立连接');
    }
    //关闭连接
    websocket.onclose = function (event) {
        console.log('连接关闭');
    }
    //消息来的时候的事件
    websocket.onmessage=function (event) {
        // 这里event.data就是我们从后台推送过来的消息
        var messages=event.data.split("+");
        $.get("/user/sessionUser",{},function (data) {
            var uid=data['id'];
            var arrays=$("._5285");
            if(uid==messages[0]){
                for (var i=0;i<arrays.length;i++){
                    var arr=arrays.eq(i);
                    if(arr.attr("value")==messages[1]){
                        var shiduan=arr.children("._1lac").children("._3S4B");
                        if(shiduan.length>0){
                            shiduan.html(parseInt(shiduan.html())+1);
                        }else{
                            var content="<span class=\"_3S4B _2TdG _Xiao\">1</span>";
                            arr.children("._1lac").append($(content));
                        }
                    }
                }
                parentnewsNum();
            }
        },'json');
    }

    //整合所有消息
    function parentnewsNum() {
        var arrays=$("._1B4y ._Xiao");
        var number=0;
        for (var i=0;i<arrays.length;i++){
            number+=parseInt(arrays.eq(i).html());
        }
        if(number>0){
            if($("._ParentDOm").children("._2TdG").length>0){
                $("._ParentDOm").children("._2TdG").html(number);
            }else{
                var content="<span class=\"_3S4B _2s7C _1GzC _2TdG\">"+number+"</span>";
                $("._ParentDOm").append($(content));
            }
        }
    }
    $.get("/messages/getmessageNum",{},function(e) {
        $.each(e,function (key,values) {
            if(values!=0){
                var arrays=$("._5285");
                for (var i=0;i<arrays.length;i++){
                    var arr=arrays.eq(i);
                    if(arr.attr("value")==key){
                        var shiduan=arr.children("._1lac").children("._3S4B");
                        if(shiduan.length>0){
                            shiduan.html(parseInt(shiduan.html())+parseInt(values));
                        }else{
                            var content="<span class=\"_3S4B _2TdG _Xiao\">"+values+"</span>";
                            arr.children("._1lac").append($(content));
                        }
                    }
                }
            }
        });
        parentnewsNum();
    },"json");
})

