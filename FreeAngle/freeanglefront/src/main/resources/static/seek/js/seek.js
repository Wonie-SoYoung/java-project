$(function () {
    $("#import").focus(function () {
        $(this).parent().attr("class","_1wq2s _1hJo _3ZGu");
    })
    $("#import").blur(function () {
        $(this).parent().attr("class","_1wq2s _1hJo");
    })
    $("._2pco").on("mouseover",function () {
        $(this).children("._2cPJ").children("._1Zls").css("opacity","0.3");
        $(this).children("._1BMp").css("opacity","1");
        $(this).children("._3Wrm").css("opacity","1");
    })
    $("._2pco").on("mouseout",function () {
        $(this).children("._2cPJ").children("._1Zls").css("opacity","0");
        $(this).children("._1BMp").css("opacity","0");
        $(this).children("._3Wrm").css("opacity","0");
    })

    //搜索
    $("#twice").click(function () {
        var searchvalue=$("#import").val();
        var type=$("._2W_h").attr("value");
        grabble(searchvalue,type);
    })
    $("#import").bind('keypress',function (event) {
        if(event.keyCode == 13){
            var searchvalue=$("#import").val();
            var type=$("._2W_h").attr("value");
            grabble(searchvalue,type);
        }
    })
    //进行搜索
    function grabble(searchvalue,type) {
        if(searchvalue!=null && searchvalue!=undefined && searchvalue!=''){
            location.href="/hunt/info?value="+searchvalue+"&type="+type;
        }
    }
})