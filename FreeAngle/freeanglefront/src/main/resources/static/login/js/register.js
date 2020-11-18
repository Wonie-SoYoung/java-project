$(function () {
    if($("#errey .tishi").text()!=null && $("#errey .tishi").text()!=undefined && $("#errey .tishi").text()!=''){
        erreyshow();
    }
    $(".codetext").click(function () {
        if($(this).html()!='获取验证码'){
            $("#errey span").html("不可重复点击!").attr("class","tishi");
            erreyshow();
        }else{
            getCode();
        }
    })

    //异常特效
    function erreyshow(){
        $("#errey").animate({"top":15,"opacity":1},500);
        setTimeout("$('#errey').animate({'top':-40,'opacity':0},800)",2000);
    }
    //点击发送验证码
    function getCode() {
        var nums=60;	//时间
        var clock = '';	//定时事件
        var $phone=$("#phone").val();
        var code=$(".codetext");
        var context=/^1(3[0-9]|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8[56789])[0-9]{8}$/;
        // 判断手机号码
        if ($phone=="" || $phone==null) {
            $("#errey span").html("手机号没有输入!").attr("class","tishi");
            erreyshow();
            $('#phone').focus();
        } else if(context.test($phone)==false){
            $("#errey span").html("手机号码不正确!").attr("class","tishi");
            erreyshow();
            $('#phone').focus();
        }else{
            $.post("/oauth/getCode/"+$phone,'',function (data) {
                if(data=='1'){
                    $("#errey span").html("验证码发送成功！").attr("class","tongguo");
                    erreyshow();
                    code.html(nums+"秒");
                    clock = setInterval(function(){
                        nums--;
                        if(nums > 0){
                            code.html(nums+"秒");
                        }else{
                            clearInterval(clock);
                            //清除js定时器
                            code.html('获取验证码');
                            nums = 60; //重置时间
                        }
                    }, 1000); //一秒执行一次
                }else{
                    $("#errey span").html("验证码发送失败！").attr("class","tishi");
                    erreyshow();
                }
            },'json');
        }
    }
    //验证注册提交
    $(".rsub").click(function () {
        var context=/^1(3[0-9]|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8[56789])[0-9]{8}$/;
        var $phone=$("#phone").val();
        var $code=$("#code").val();
        var $pwd=$("#pwd").val();
        if($phone==null || $phone==undefined || $phone==''){
            $("#errey span").html("手机号不为空!").attr("class","tishi");
            erreyshow();
        }else if($code==null || $code==undefined || $code==''){
            $("#errey span").html("验证码不为空！").attr("class","tishi");
            erreyshow();
        }else if($pwd==null || $pwd==undefined || $pwd==''){
            $("#errey span").html("密码不为空！").attr("class","tishi");
            erreyshow();
        }else if(context.test($phone)==false){
            $("#errey span").html("手机号码不正确!").attr("class","tishi");
            erreyshow();
        }else if($code.length!=4){
            $("#errey span").html("验证码不正确!").attr("class","tishi");
            erreyshow();
        }else if($pwd.length<6){
            $("#errey span").html("密码长度6位以上!").attr("class","tishi");
            erreyshow();
        }else{
            $("form").submit();
        }
    })
})
