$(function () {
    //点击qq登录
    $(".w-btn3-1").click(function () {
        qqLogin();
    })
    /**
     * 封装一个居中打开新窗口的方法
     */
    function openWindow(url, width, height)
    {
        width = width || 600;
        height = height || 400;
        var left = (window.screen.width - width) / 2;
        var top = (window.screen.height - height) / 2;
        window.open(url, "_blank", "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, left="+left+", top="+top+", width="+width+", height="+height);
    }

    function qqLogin()
    {
        var qqAppId = '101828941'; // 上面申请得到的appid
        var qqAuthPath = 'http://meixiaohan.natapp1.cc/oauth/qq'; // 前面设置的回调地址
        var state = 'sunny'; // 防止CSRF攻击的随机参数，必传，登录成功之后会回传，最好后台自己生成然后校验合法性
        openWindow("https://graph.qq.com/oauth2.0/authorize?response_type=token&client_id="+qqAppId+"&redirect_uri="+qqAuthPath+"&state="+state+"");
    }
})
