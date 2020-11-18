var liW = $('.box_ul li').outerWidth(true)
var len = $('.box_ul li').length
$('.box_ul').css("width", liW * len)
var flag = true
var number=1;
function toLeft() {
	if(flag) {
		if(number>1){
			number-=1;
		}else{
			number=$(".bopage").attr("value");
		}
		$(".sign li").eq(number-1).css("background-color","#1088F2");
		$(".sign li").eq(number-1).siblings().css("background-color","#fff");
		flag = false
		$('.box_ul').find("li").eq(len - 1).prependTo($('.box_ul'))
		$('.box_ul').css({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul').animate({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

function toRight() {
	if(flag) {
		if(number<$(".bopage").attr("value")){
			number+=1;
		}else{
			number=1;
		}
		$(".sign li").eq(number-1).css("background-color","#1088F2");
		$(".sign li").eq(number-1).siblings().css("background-color","#fff");
		flag = false
		$('.box_ul').animate({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul').find("li").eq(0).appendTo($('.box_ul'));
			$('.box_ul').css({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

$(".btn_right").click(function() {
	toRight();
})
$(".btn_left").click(function() {
	toLeft();
})
var timer = setInterval(function() {
	toRight();
}, 3000);



//二次轮播
var liW = $('.box_ul_con1 li').outerWidth(true)
var len = $('.box_ul_con1 li').length
$('.box_ul_con1').css("width", liW * len)
var flag = true
var number2=1;

function toLeft2() {
	if(flag) {
		if(number2>1){
			number2-=1;
		}else{
			number2=7;
		}
		$(".sign_li1 li").eq(number2-1).css("background-color","#1088F2");
		$(".sign_li1 li").eq(number2-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con1').find("li").eq(len - 1).prependTo($('.box_ul_con1'))
		$('.box_ul_con1').css({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con').animate({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

function toRight2() {
	if(flag) {
		if(number2<4){
			number2+=1;
		}else{
			number2=1;
		}
		$(".sign_li1 li").eq(number2-1).css("background-color","#1088F2");
		$(".sign_li1 li").eq(number2-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con1').animate({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con1').find("li").eq(0).appendTo($('.box_ul_con1'));
			$('.box_ul_con1').css({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

$(".btn_right2").click(function() {
	toRight2();
})
$(".btn_left2").click(function() {
	toLeft2();
})


//


var liW = $('.box_ul_con2 li').outerWidth(true)
var len = $('.box_ul_con2 li').length
$('.box_ul_con2').css("width", liW * len)
var flag = true
var number3=1;

function toLeft3() {
	if(flag) {
		if(number3>1){
			number3-=1;
		}else{
			number3=7;
		}
		$(".sign_li2 li").eq(number3-1).css("background-color","#1088F2");
		$(".sign_li2 li").eq(number3-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con2').find("li").eq(len - 1).prependTo($('.box_ul_con2'))
		$('.box_ul_con2').css({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con2').animate({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

function toRight3() {
	if(flag) {
		if(number3<4){
			number3+=1;
		}else{
			number3=1;
		}
		$(".sign_li2 li").eq(number3-1).css("background-color","#1088F2");
		$(".sign_li2 li").eq(number3-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con2').animate({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con2').find("li").eq(0).appendTo($('.box_ul_con2'));
			$('.box_ul_con2').css({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

$(".btn_right3").click(function() {
	toRight3();
})
$(".btn_left3").click(function() {
	toLeft3();
})


//

var liW = $('.box_ul_con3 li').outerWidth(true)
var len = $('.box_ul_con3 li').length
$('.box_ul_con3').css("width", liW * len)
var flag = true
var number4=1;

function toLeft4() {
	if(flag) {
		if(number4>1){
			number4-=1;
		}else{
			number4=7;
		}
		$(".sign_li3 li").eq(number4-1).css("background-color","#1088F2");
		$(".sign_li3 li").eq(number4-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con3').find("li").eq(len - 1).prependTo($('.box_ul_con3'))
		$('.box_ul_con3').css({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con3').animate({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

function toRight4() {
	if(flag) {
		if(number4<4){
			number4+=1;
		}else{
			number4=1;
		}
		$(".sign_li3 li").eq(number4-1).css("background-color","#1088F2");
		$(".sign_li3 li").eq(number4-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con3').animate({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con3').find("li").eq(0).appendTo($('.box_ul_con3'));
			$('.box_ul_con3').css({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

$(".btn_right4").click(function() {
	toRight4();
})
$(".btn_left4").click(function() {
	toLeft4();
})

//

var liW = $('.box_ul_con4 li').outerWidth(true)
var len = $('.box_ul_con4 li').length
$('.box_ul_con4').css("width", liW * len)
var flag = true
var number5=1;

function toLeft5() {
	if(flag) {
		if(number5>1){
			number5-=1;
		}else{
			number5=7;
		}
		$(".sign_li4 li").eq(number5-1).css("background-color","#1088F2");
		$(".sign_li4 li").eq(number5-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con4').find("li").eq(len - 1).prependTo($('.box_ul_con4'))
		$('.box_ul_con4').css({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con4').animate({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

function toRight5() {
	if(flag) {
		if(number5<4){
			number5+=1;
		}else{
			number5=1;
		}
		$(".sign_li4 li").eq(number5-1).css("background-color","#1088F2");
		$(".sign_li4 li").eq(number5-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con4').animate({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con4').find("li").eq(0).appendTo($('.box_ul_con4'));
			$('.box_ul_con4').css({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

$(".btn_right5").click(function() {
	toRight5();
})
$(".btn_left5").click(function() {
	toLeft5();
})


//


var liW = $('.box_ul_con5 li').outerWidth(true)
var len = $('.box_ul_con5 li').length
$('.box_ul_con5').css("width", liW * len)
var flag = true
var number6=1;

function toLeft6() {
	if(flag) {
		if(number6>1){
			number6-=1;
		}else{
			number6=7;
		}
		$(".sign_li5 li").eq(number5-1).css("background-color","#1088F2");
		$(".sign_li5 li").eq(number5-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con5').find("li").eq(len - 1).prependTo($('.box_ul_con5'))
		$('.box_ul_con5').css({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con5').animate({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

function toRight6() {
	if(flag) {
		if(number6<4){
			number6+=1;
		}else{
			number6=1;
		}
		$(".sign_li5 li").eq(number6-1).css("background-color","#1088F2");
		$(".sign_li5 li").eq(number6-1).siblings().css("background-color","#DDDDDD");
		flag = false
		$('.box_ul_con5').animate({
			"marginLeft": -liW
		}).delay(8).queue(function() {
			$('.box_ul_con5').find("li").eq(0).appendTo($('.box_ul_con5'));
			$('.box_ul_con5').css({
				"marginLeft": 0
			})
			flag = true
			$(this).dequeue();
		})
	}
}

$(".btn_right6").click(function() {
	toRight6();
})
$(".btn_left6").click(function() {
	toLeft6();
})

$(".labeltitle").click(function () {
    $(this).siblings().attr("class",$(this).attr("class"));
	$(this).attr("class",$(this).attr("class")+" selected");
	var v=$(this).text();
	if(v=='精选'){
        $(".content").show();
		$(".attention").hide();
        $(".load").hide();
	}else if(v=='关注'){
        $(".content").hide();
        $(".attention").show();
        $(".load").show();
        if($(".attention .content_v .chunk").length<1){
        	focusList(0);
		}
	}
})

//查询关注作品
function focusList(currPage) {
    setTimeout($(".load").css("opacity","1"),30000);
    $.get("/production/focuspro",{currPage:currPage},function (data) {
        if(data!=null&& data.length>0){
            var content="";
            var divArray=$(".attention .content_v");
            for(var i=0;i<data.length;i++) {
				var prodiv=data[i];
				content="<div class=\"chunk\"><div class=\"model1\" onclick=\"javascript:location.href='/production/getPro/"+prodiv['p_id']+"'\">";
				if(prodiv['p_duration']!=null && prodiv['p_duration']!=undefined){
					content+="<div class=\"model_control\"><span>"+prodiv['p_duration']+"</span>"+
						"<div><svg viewBox=\"0 0 48 48\"><g fill=\"none\" fill-rule=\"evenodd\"><circle fill=\"#222\" opacity=\".8\" cx=\"24\" cy=\"24\" r=\"24\"></circle><path fill=\"#FFF\" d=\"M30.692 24.5L21.5 33.692V15.308z\"></path></g></svg>" +
						"</div></div>";
				}
				content+="<div class=\"wallpaper\"><img src=\"https://free-angle.oss-cn-beijing.aliyuncs.com/"+prodiv['p_hiapkURL']+"\"/></div>";
				if(prodiv['p_duration']!=null && prodiv['p_duration']!=undefined){
					content+="<button class=\"_1r-m _3Wrm\"><div class=\"v_ps _3bYN\"></div></button>";
				}
				content+="</div>";
				content+="<div class=\"model2\"><div class=\"moinfo\"><p class=\"designation\">"+prodiv['p_pName']+"</p>" +
					"<p class=\"equipment\">设备:<a>"+prodiv['f_fName']+"</a></p><div class=\"read\"><span class=\"_2cdh\">" +
					"<svg viewBox=\"0 0 48 48\"><path d=\"M24.585 10.012C33.08 10.287 40.552 14.95 47 24c-6.562 9.36-14.229 14.027-23 14-8.771.027-16.438-4.64-23-14 6.448-9.05 13.92-13.713 22.415-13.988a14.244 14.244 0 0 1 1.17 0zM24 34c5.523 0 10-4.477 10-10s-4.477-10-10-10-10 4.477-10 10 4.477 10 10 10zm0-3a7 7 0 1 1 0-14 7 7 0 0 1 0 14z\" fill=\"#838385\" fill-rule=\"evenodd\"></path></svg>" +
					"<span class=\"watch\">"+prodiv['p_viewNum']+"</span></span><span class=\"_3chd\"><svg viewBox=\"0 0 40 40\">" +
					"<path fill=\"#FFFFFF\" d=\"M28.387 13.375h1.446c2.781.115 5 2.403 5 5.208a5.194 5.194 0 0 1-1.701 3.85l-.515 10.398a2.5 2.5 0 0 1-2.497 2.376H6.666a2.5 2.5 0 0 1-2.5-2.5V15.874a2.5 2.5 0 0 1 2.5-2.5h5.344c2.752-.787 4.168-2.653 4.247-5.599a4.958 4.958 0 1 1 9.908-.276l-.001.11c.006.513-.014 1.034-.054 1.562a24.287 24.287 0 0 1-.714 4.203h2.989z\"></path><path fill=\"none\" stroke=\"#838385\" stroke-width=\"2.5\" d=\"M29.833 14.625h-6.047l.401-1.561c.355-1.381.58-2.71.678-3.987a17 17 0 0 0 .051-1.494l.001-.083a3.708 3.708 0 1 0-7.41.207l.001.102c-.094 3.486-1.871 5.829-5.153 6.767l-.168.048H6.668c-.69 0-1.25.56-1.25 1.25v16.833c0 .69.56 1.25 1.25 1.25h23.454a1.25 1.25 0 0 0 1.248-1.188l.54-10.913.381-.347a3.944 3.944 0 0 0 1.293-2.927c0-2.126-1.681-3.872-3.75-3.958z\"></path><path fill=\"#FFFFFF\" d=\"M6.671 15.89h7.079v16.825H6.671z\"></path><path fill=\"#838385\" d=\"M11.25 14.375h2.5v20.833h-2.5V14.375z\"></path></svg>" +
					"<span class=\"like\">"+prodiv['like_num']+"</span></span></div></div><div class=\"author\"><div class=\"author_img\">" +
					"<img src=\"https://free-angle.oss-cn-beijing.aliyuncs.com/"+prodiv['u_headURL']+"\"/>" +
					"<span>"+prodiv['u_userName']+"</span></div><div class=\"ayuthor_date\"><p>"+prodiv['p_createTime']+"</p>" +
					"</div></div></div></div>";
				divArray.append($(content));
            }
            $(".load").attr("value",parseInt(currPage)+20);
        }else{
            $(window).unbind ('scroll');
            $(".load").hide();
        }
        $(".load").css("opacity","0");
    },'json');
}