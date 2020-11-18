$(function(){
	var liarray=$("._1MCd li");
	//数量
	var imgnum=$("._1MCd li").size();
	//当前下标
	var indexli=0;

	function leftchunk(){
		if(indexli>0){
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"0"},500);
			indexli=indexli-1;
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"1"},500);
		}else{
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"0"},500);
			indexli=imgnum-1;
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"1"},500);
		}
		$("._1MCd li").eq(indexli).attr("class","slick-active").siblings().attr("class"," ");
	}
	
	function rightchunk(){
		if(indexli<imgnum-1){
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"0"},500);
			indexli=indexli+1;
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"1"},500);
		}else{
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"0"},500);
			indexli=0;
			$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"1"},500);
		}
		$("._1MCd li").eq(indexli).attr("class","slick-active").siblings().attr("class"," ");
	}
	
	$(".slick-prev").click(function(){
		leftchunk();
	})
	$(".slick-next").click(function(){
		rightchunk();
	})
	$("._1MCd li").click(function(){
		indexli=$("._1MCd li").index($(this));
		$(".slick-track .slick-slider").eq(indexli).siblings().animate({"opacity":"0"},500);
		$(".slick-track .slick-slider").eq(indexli).animate({"opacity":"1"},500);
		$(this).attr("class","slick-active").siblings().attr("class"," ");
	})
    //作品详细事件
	if($(".GHQR").height()>48){
        $(".GHQR").parent().parent().parent().attr("class","_2EqF _20iQ _3eKX");
        $("._32zZ").append($("<a class=\"_3mWR\"><span>展开</span></a>"));
        $("._3mWR").on("click",function () {
            if($(this).children().text()=="展开"){
                $(".GHQR").parent().parent().parent().attr("class","_2EqF _3eKX");
                $(this).children().html("收起");
            }else{
                $(".GHQR").parent().parent().parent().attr("class","_2EqF _20iQ _3eKX");
                $(this).children().html("展开");
            }

        })
    }
    //点击小窗口×
    $(".rc-dialog-close").click(function () {
        $("#wicket").hide();
        $(this).parent().parent().parent().hide();
        $(".rc-dialog-mask").css("opacity","0");
        $(this).parent().parent().css({height:'0px'});
    })
    $("._cha").click(function () {
        $("#wicket").hide();
        $(this).parent().parent().parent().parent().hide();
        $(".rc-dialog-mask").css("opacity","0");
        $(this).parent().parent().parent().css({height:'0px'});
    })
    //举报窗口的text有值时事件
    $("#reporttext").on('input propertychange',function(){
        $(".sMhg").removeAttr("disabled");
    })
})
//点赞
function executelike(value) {
	var pid=$("#root").attr("value");
	var $value=$(value);
	if($value.attr("class")=="Xwom kdjc _1JHv _1Osl _1TkV _3-aF"){
        $.get("/likes/execute",{pid:pid},function (data) {
            if(data>0){
                $value.attr("class","Xwom kdjc _1JHv _1Osl _2EPU _1TkV");
                $value.children().children().children().children().remove();
                $value.children().children().children().append($("<svg viewBox=\"0 0 40 40\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<path fill=\"#87b9ff\" d=\"M28.387 13.375h1.446c2.781.115 5 2.403 5 5.208a5.194 5.194 0 0 1-1.701 3.85l-.515 10.398a2.5 2.5 0 0 1-2.497 2.376H6.666a2.5 2.5 0 0 1-2.5-2.5V15.874a2.5 2.5 0 0 1 2.5-2.5h5.344c2.752-.787 4.168-2.653 4.247-5.599a4.958 4.958 0 1 1 9.908-.276l-.001.11c.006.513-.014 1.034-.054 1.562a24.287 24.287 0 0 1-.714 4.203h2.989z\"></path>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<path fill=\"none\" stroke=\"#FFFFFF\" stroke-width=\"2.5\" d=\"M29.833 14.625h-6.047l.401-1.561c.355-1.381.58-2.71.678-3.987a17 17 0 0 0 .051-1.494l.001-.083a3.708 3.708 0 1 0-7.41.207l.001.102c-.094 3.486-1.871 5.829-5.153 6.767l-.168.048H6.668c-.69 0-1.25.56-1.25 1.25v16.833c0 .69.56 1.25 1.25 1.25h23.454a1.25 1.25 0 0 0 1.248-1.188l.54-10.913.381-.347a3.944 3.944 0 0 0 1.293-2.927c0-2.126-1.681-3.872-3.75-3.958z\"></path>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<path fill=\"#5d99ec\" d=\"M6.671 15.89h7.079v16.825H6.671z\"></path><path fill=\"#3d85ec\" d=\"M11.25 14.375h2.5v20.833h-2.5V14.375z\"></path>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</svg>"))
                $value.children().children().children("span").text(parseInt($value.children().children().children("span").text())+1);
            }else{
                alert("点赞失败!");
            }
        })
	}else if($value.attr("class")=="Xwom kdjc _1JHv _1Osl _2EPU _1TkV"){
        $.get("/likes/cancel",{pid:pid},function (data) {
            if(data>0){
                $value.attr("class","Xwom kdjc _1JHv _1Osl _1TkV _3-aF");
                $value.children().children().children().children().remove();
                $value.children().children().children().append($("<svg viewBox=\"0 0 40 40\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<path fill=\"#FFFFFF\" d=\"M28.387 13.375h1.446c2.781.115 5 2.403 5 5.208a5.194 5.194 0 0 1-1.701 3.85l-.515 10.398a2.5 2.5 0 0 1-2.497 2.376H6.666a2.5 2.5 0 0 1-2.5-2.5V15.874a2.5 2.5 0 0 1 2.5-2.5h5.344c2.752-.787 4.168-2.653 4.247-5.599a4.958 4.958 0 1 1 9.908-.276l-.001.11c.006.513-.014 1.034-.054 1.562a24.287 24.287 0 0 1-.714 4.203h2.989z\"></path>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<path fill=\"none\" stroke=\"#8B8B8E\" stroke-width=\"2.5\" d=\"M29.833 14.625h-6.047l.401-1.561c.355-1.381.58-2.71.678-3.987a17 17 0 0 0 .051-1.494l.001-.083a3.708 3.708 0 1 0-7.41.207l.001.102c-.094 3.486-1.871 5.829-5.153 6.767l-.168.048H6.668c-.69 0-1.25.56-1.25 1.25v16.833c0 .69.56 1.25 1.25 1.25h23.454a1.25 1.25 0 0 0 1.248-1.188l.54-10.913.381-.347a3.944 3.944 0 0 0 1.293-2.927c0-2.126-1.681-3.872-3.75-3.958z\"></path>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<path fill=\"#B6B6B8\" d=\"M6.671 15.89h7.079v16.825H6.671z\"></path>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<path fill=\"#B6B6B8\" d=\"M11.25 14.375h2.5v20.833h-2.5V14.375z\"></path>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</svg>"))
                $value.children().children().children("span").text(parseInt($value.children().children().children("span").text())-1);
            }else{
                alert("取消失败!");
            }
        })
	}
}
//收藏
function executecollect(value) {
    var pid=$("#root").attr("value");
    var $value=$(value);
    if($value.attr("class")=="Xwom kdjc _1JHv _34Wn _1TkV _3-aF"){
        $.get("/collect/execute",{pid:pid},function (data) {
            if(data>0){
                $value.attr("class","Xwom kdjc _1JHv _34Wn _2EPU _1TkV");
                $value.children().children().children().children().remove();
                $value.children().children().children().append($("<svg viewBox=\"0 0 40 40\"><path fill=\"none\" stroke=\"#FFFFFF\" stroke-width=\"3\" d=\"M8.5 5.5v28.156L20 28.348l11.5 5.308V5.5h-23z\"></path></svg>"))
            }else{
                alert("收藏失败!");
            }
        })
    }else if($value.attr("class")=="Xwom kdjc _1JHv _34Wn _2EPU _1TkV"){
        $.get("/collect/cancel",{pid:pid},function (data) {
            if(data>0){
                $value.attr("class","Xwom kdjc _1JHv _34Wn _1TkV _3-aF");
                $value.children().children().children().children().remove();
                $value.children().children().children().append($("<svg viewBox=\"0 0 40 40\"><path fill=\"none\" stroke=\"#4b4b4b\" stroke-width=\"3\" d=\"M8.5 5.5v28.156L20 28.348l11.5 5.308V5.5h-23z\"></path></svg>"))
            }else{
                alert("取消失败!");
            }
        })
    }
}
//执行关注
function executeattention(value){
    var uid=0;
    $.get("/user/sessionUser",{},function (user) {
        uid=user['id'];
        var oruid=$("._3F58").attr("value");
        var $value=$(value);
        if($value.attr("class")=="Xwom _123Z _3le- _1TkV _3bvn"){
            $.get("/attention/execute",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _3le- undefined _1TkV _2OcN");
                    $value.children().children().html("已关注");
                }else{
                    alert("关注失败!");
                }
            })
        }else if($value.attr("class")=="Xwom _123Z _3le- undefined _1TkV _2OcN"){
            $.get("/attention/cancel",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _3le- _1TkV _3bvn");
                    $value.children().children().html("关注");
                }else{
                    alert("取消失败!");
                }
            })
        }
    },'json');
}
//点击回复
function executereply(value) {
    var $value=$(value);
    var content=$value.prev().text();
    var userName=$value.prev().prev().children("._2643").children("._11Bg").text();
    var cid=$value.parent().parent().attr("value");
    if($(".D6lj ._1EI-").length>0){
        $(".D6lj ._1EI-").remove();
    }
    var replycontent="<div class=\"_1EI-\" value=\""+cid+"\"><span>回复 <b class=\"_2PCD\">@"+userName+"</b>： "+content+"\n" +
        "</span><a style=\"cursor: pointer;\"><div class=\"_35f9 jZEM sQis\"><svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\"><path d=\"M24 20.464l10.253-10.253a1 1 0 0 1 1.414 0l2.122 2.122a1 1 0 0 1 0 1.414L27.536 24l10.253 10.253a1 1 0 0 1 0 1.414l-2.122 2.122a1 1 0 0 1-1.414 0L24 27.536 13.747 37.789a1 1 0 0 1-1.414 0l-2.122-2.122a1 1 0 0 1 0-1.414L20.464 24 10.211 13.747a1 1 0 0 1 0-1.414l2.122-2.122a1 1 0 0 1 1.414 0L24 20.464z\" fill-rule=\"evenodd\" fill=\"#707473\"></path></svg></div></a></div>";
    $(".D6lj").prepend($(replycontent));
    $("._35f9").on("click",function () {
        $(this).parent().parent().remove();
    })
}

//评论轮播
function commentPage(currPage) {
    var pid=$("#root").attr("value");
    var uid=null;
    $.get("/user/sessionUser",{},function (data) {
        uid=data['id'];
    },'json');
    $.get("/comment/list",{pid:pid,currPage:currPage},function (data) {
        if(data!=null){
            $("._2ggr").children(".Jgy0").remove();
            $("._2ggr").children("ul").remove();
            var content="<div class=\"Jgy0\">\n";
            for (var i=0;i<data['list'].length;i++){
                content+="<div class=\"_3mH0\" value=\""+data['list'][i]['cid']+"\">\n" +
                    "\t<a class=\"\" target=\"\" href=\"/user/getUser/"+data['list'][i]['user']['id']+"\">\n" +
                    "\t\t<div class=\"JTA4 yZCl undefined\">\n" +
                    "\t\t\t<div class=\"_3llO _1Gim _1LNE _2G4A hUPH\">\n" +
                    "\t\t\t\t<img src=\"https://free-angle.oss-cn-beijing.aliyuncs.com/"+data['list'][i]['user']['headURL']+"\" alt=\"\" width=\"100%\" height=\"100%\">\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t</a>\n" +
                    "\t<div class=\"_3qwe\">\n" +
                    "\t\t<div class=\"_1SCg\">\n" +
                    "\t\t\t<div class=\"_2643\">\n" +
                    "\t\t\t\t<div class=\"_11Bg\">"+data['list'][i]['user']['userName']+"</div>\n" +
                    "\t\t\t\t<div class=\"_3U-j\">\n" +
                    "\t\t\t\t\t<div class=\"_3fuh\">\n" +
                    "\t\t\t\t\t\t"+data['list'][i]['createTime']+"\n" +
                    "\t\t\t\t\t</div>\n" +
                    "\t\t\t\t</div>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<div class=\"_2ib4\">\n";
                if(uid==null){
                    content+="\t\t\t\t<a href=\"/login\"><span>举报</span></a>\n";
                }else if(uid!=null && uid!=data['list'][i]['user']['id']){
                    content+="\t\t\t\t<a onclick=\"executereport("+data['list'][i]['cid']+")\"><span>举报</span></a>\n";
                }else if(uid!=null && uid==data['list'][i]['user']['id']){
                    content+="\t\t\t\t<a onclick=\"executdelete("+data['list'][i]['cid']+")\"><span>删除</span></a>\n";
                }
                content+="\t\t\t</div>\n" +
                    "\t\t\t\n" +
                    "\t\t\t\n" +
                    "\t\t</div>\n" +
                    "\t\t<div class=\"_1cq7\">";
                if(data['list'][i]['replcomment']!=null){
                    content+="<b class=\"_3YmC\">@"+data['list'][i]['replcomment']['user']['userName']+"</b>";
                }
                content+=""+data['list'][i]['content']+"</div>\n";
                if(uid==null){
                    content+="\t\t<a href=\"/login\" class=\"_1Ihd\">\n" +
                        "\t\t\t<div class=\"_3_de sQis _2tlM _3EyJ\">\n" +
                        "\t\t\t\t<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 32 32\">\n" +
                        "\t\t\t\t\t<path fill=\"#4b4b4b\" d=\"M12.087 23.333h15.246V6H4.666v17.333h3.359v2.934l4.062-2.934zm-6.084 5.08L6 28.412v-3.078H4a1.333 1.333 0 0 1-1.333-1.333V5.334c0-.736.597-1.333 1.333-1.333h24c.736 0 1.333.597 1.333 1.333v18.667c0 .736-.597 1.333-1.333 1.333H12.677l-4.991 3.727a1 1 0 0 1-1.683-.647z\"></path>\n" +
                        "\t\t\t\t</svg>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<span>回复</span>\n" +
                        "\t\t</a>\n";
                }else if(uid!=null && uid!=data['list'][i]['user']['id']){
                    content+="\t\t<a class=\"_1Ihd\" onclick=\"executereply(this)\">\n" +
                        "\t\t\t<div class=\"_3_de sQis _2tlM _3EyJ\">\n" +
                        "\t\t\t\t<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 32 32\">\n" +
                        "\t\t\t\t\t<path fill=\"#4b4b4b\" d=\"M12.087 23.333h15.246V6H4.666v17.333h3.359v2.934l4.062-2.934zm-6.084 5.08L6 28.412v-3.078H4a1.333 1.333 0 0 1-1.333-1.333V5.334c0-.736.597-1.333 1.333-1.333h24c.736 0 1.333.597 1.333 1.333v18.667c0 .736-.597 1.333-1.333 1.333H12.677l-4.991 3.727a1 1 0 0 1-1.683-.647z\"></path>\n" +
                        "\t\t\t\t</svg>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t\t<span>回复</span>\n" +
                        "\t\t</a>\n";
                }
                 content+="\t</div>\n" +
                    "</div>";
            }
            content+="</div>";
            $("._2ggr").append($(content));
            if(data['currPageNo']==1 && data['list'].length<10){}else{
                var pagecontent="<ul class=\"rc-pagination _29tj pOYt mini\" unselectable=\"unselectable\">\n";
                if(data['currPageNo']<=1){
                    pagecontent+="\t<li title=\"上一页\" class=\"rc-pagination-disabled rc-pagination-prev\" aria-disabled=\"true\" onclick=\"commentPage("+(data['currPageNo']-1)+")\">\n";
                }else{
                    pagecontent+="\t<li title=\"上一页\" class=\"rc-pagination-prev\" aria-disabled=\"true\" onclick=\"commentPage("+(data['currPageNo']-1)+")\">\n";
                }
                pagecontent+="\t\t<div class=\"_1Oh5 _1a-Q _1_oh EYLS\">\n" +
                    "\t\t\t<div class=\"lLO5 jZEM sQis\" style=\"top: 0\">\n" +
                    "\t\t\t\t<svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\"><path d=\"M14.89 21.449c-.045.032-.088.069-.129.11l-2.053 2.071-.002.002a1 1 0 0 0 .01 1.414l15.938 15.706a1 1 0 0 0 1.409-.005l2.044-2.044a1 1 0 0 0-.004-1.419L19.008 24.35 32.1 11.215a1 1 0 0 0-.001-1.413l-2.094-2.094a1 1 0 0 0-1.415.001l-13.7 13.74z\" fill=\"#B2B2B2\" fill-rule=\"evenodd\"></path></svg>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t</div>\n" +
                    "\t</li>\n";
                for (var i=0;i<data['totalPageCount'];i++){
                    if((i+1)==data['currPageNo']){
                        pagecontent+="\t<li title=\"1\" tabindex=\"0\" class=\"rc-pagination-item rc-pagination-item-1 rc-pagination-item-active\" onclick=\"commentPage("+(data['currPageNo']*0+(i+1))+")\">\n" +
                            "\t\t<a>"+(i+1)+"</a>\n" +
                            "\t</li>\n";
                    }else{
                        pagecontent+="\t<li title=\"1\" tabindex=\"0\" class=\"rc-pagination-item rc-pagination-item-2\" onclick=\"commentPage("+(data['currPageNo']*0+(i+1))+")\">\n" +
                            "\t\t<a>"+(i+1)+"</a>\n" +
                            "\t</li>\n";
                    }
                }
                if(data['currPageNo']<data['totalPageCount']){
                    pagecontent+="\t<li title=\"下一页\" tabindex=\"0\" class=\"rc-pagination-next\" aria-disabled=\"false\" onclick=\"commentPage("+(data['currPageNo']+1)+")\">\n" +
                        "\t\t<div class=\"_1Oh5 _1a-Q _1_oh EYLS\">\n" +
                        "\t\t\t<div class=\"lLO5 jZEM sQis A-w_\" style=\"top: 0\">\n" +
                        "\t\t\t\t<svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\" style=\"transform: rotate(180deg)\"><path d=\"M14.89 21.449c-.045.032-.088.069-.129.11l-2.053 2.071-.002.002a1 1 0 0 0 .01 1.414l15.938 15.706a1 1 0 0 0 1.409-.005l2.044-2.044a1 1 0 0 0-.004-1.419L19.008 24.35 32.1 11.215a1 1 0 0 0-.001-1.413l-2.094-2.094a1 1 0 0 0-1.415.001l-13.7 13.74z\" fill=\"#B2B2B2\" fill-rule=\"evenodd\"></path></svg>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</li>\n";
                }else{
                    pagecontent+="\t<li title=\"下一页\" tabindex=\"0\" class=\"rc-pagination-disabled rc-pagination-next\" aria-disabled=\"false\" onclick=\"commentPage("+(data['currPageNo']+1)+")\">\n" +
                        "\t\t<div class=\"_1Oh5 _1a-Q _1_oh EYLS\">\n" +
                        "\t\t\t<div class=\"lLO5 jZEM sQis A-w_\" style=\"top: 0\">\n" +
                        "\t\t\t\t<svg viewBox=\"0 0 48 48\" xmlns=\"http://www.w3.org/2000/svg\" style=\"transform: rotate(180deg)\"><path d=\"M14.89 21.449c-.045.032-.088.069-.129.11l-2.053 2.071-.002.002a1 1 0 0 0 .01 1.414l15.938 15.706a1 1 0 0 0 1.409-.005l2.044-2.044a1 1 0 0 0-.004-1.419L19.008 24.35 32.1 11.215a1 1 0 0 0-.001-1.413l-2.094-2.094a1 1 0 0 0-1.415.001l-13.7 13.74z\" fill=\"#B2B2B2\" fill-rule=\"evenodd\"></path></svg>\n" +
                        "\t\t\t</div>\n" +
                        "\t\t</div>\n" +
                        "\t</li>\n";
                }
                pagecontent+="</ul>";
                $("._2ggr").append($(pagecontent));
            }
        }
    },'json')
}

//点击评论
function executecomment(value) {
    var $value=$(value);
    var content=$value.prev().children("._2sB4").children().children().val();
    if(content!=null && content!=undefined && content!=''){
        //获取数据
        var replyid;
        if($("._1EI-").length>0) replyid=$("._1EI-").attr("value");
        var pid=$("#root").attr("value");
        $.get("/comment/add",{pid:pid,content:content,replyid:replyid},function (data) {
            if(data!=null && data>0){
                commentPage(1);
                if($("._1EI-").length>0) $("._1EI-").remove();
                $value.prev().children("._2sB4").children().children().val("");
                $("._18aH").children().children("span").html(parseInt($("._18aH").children().children("span").html())+1);
            }else{
                alert("添加失败!");
            }
        },'json');
    }
}

//点击评论删除
function executdelete(cid) {
    $.get("/comment/del",{cid:cid},function (data) {
        if(data!=null && data>0){
            commentPage(1);
            $("._18aH").children().children("span").html(parseInt($("._18aH").children().children("span").html())-1);
        }else{
            alert("抱歉！删除失败！");
        }
    },'json');
}

//点击评论举报
function executecommentreport(cid) {
    $("#wicket").show();
    $("._1E8a").show();
    $("._1E8a .rc-dialog").animate({height:'250px'},200);
    $(".rc-dialog-mask").animate({opacity:1},500);
    $(".sMhg").on('click',function () {
        //判断
        var content=$("#reporttext").val();
        if(content!=undefined && content!=null && content!=''){
            //发送举报请求
            $.get("/report/addComment",{reTypeCodeId:cid,explains:content},function (data) {
                if(data>0){
                    $("#wicket").hide();
                    $("._1E8a").hide();
                    $(".rc-dialog-mask").css("opacity","0");
                    $("._1E8a .rc-dialog").css({height:'0px'});
                    $("#reporttext").val("");
                    $(".sMhg").attr("disabled","true");
                    alert("举报成功！待管理员审核！");
                    $(".sMhg").unbind("click");
                }else{
                    alert("举报失败！操作有误！");
                }
            },'json');
        }else{
            $(".sMhg").attr("disabled","true");
        }
    })
}
//点击作品举报
function executeproductionreport() {
    $("#wicket").show();
    $("._1E8a").show();
    $("._1E8a .rc-dialog").animate({height:'250px'},200);
    $(".rc-dialog-mask").animate({opacity:1},500);
    $(".sMhg").on("click",function () {
        //判断
        var content=$("#reporttext").val();
        if(content!=undefined && content!=null && content!=''){
            //发送举报请求
            var pid=$("#root").attr("value");
            $.get("/report/addProduction",{reTypeCodeId:pid,explains:content},function (data) {
                if(data>0){
                    $("#wicket").hide();
                    $("._1E8a").hide();
                    $(".rc-dialog-mask").css("opacity","0");
                    $("._1E8a .rc-dialog").css({height:'0px'});
                    $("#reporttext").val("");
                    $(".sMhg").attr("disabled","true");
                    alert("举报成功！待管理员审核！");
                    $(".sMhg").unbind("click");
                }else{
                    alert("举报失败！操作有误！");
                }
            },'json');
        }else{
            $(".sMhg").attr("disabled","true");
        }
    })
}
//删除作品
function delProduction() {
    $("#wicket").show();
    $("._2E8a").show();
    $(".rc-dialog-mask").animate({opacity:1},500);
    $("._2E8a .rc-dialog").animate({height:'250px'},200);
    $(".delpro").click(function () {
        //获取数据
        var pid=$("#root").attr("value");
        $.get("/production/del/"+pid,{},function (data) {
            if(data!=undefined && data!=null && data!=0){
                location.href="/user/getUser/"+data;
            }else{
                $("#wicket").hide();
                $("._2E8a").hide();
                $(".rc-dialog-mask").css("opacity","0");
                $("._2E8a .rc-dialog").css({width:'0px',height:'0px'});
                alert("删除失败！");
            }
        },'json');
    })
}

//修改作品
function updateProduction() {
    var pid=$("#root").attr("value");
    location.href="/upload/update/"+pid;
}
