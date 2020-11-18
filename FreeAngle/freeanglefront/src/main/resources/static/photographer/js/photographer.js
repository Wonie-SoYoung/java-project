//关注
function executeattention(value) {
    var uid=0;
    var $value=$(value);
    $.get("/user/sessionUser",{},function (user) {
        uid=user['id'];
        var oruid=$value.attr("value");
        if($value.attr("class")=="Xwom _123Z _1Don _3VBA _1LJQ _1TkV"){
            $.get("/attention/execute",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _1Don _3VBA _1LJQ _1TkV _3-aF");
                    $value.children().children().html("已关注");
                }else{
                    alert("关注失败!");
                }
            })
        }else if($value.attr("class")=="Xwom _123Z _1Don _3VBA _1LJQ _1TkV _3-aF"){
            $.get("/attention/cancel",{uid:uid,byuid:oruid},function (data) {
                if(data>0){
                    $value.attr("class","Xwom _123Z _1Don _3VBA _1LJQ _1TkV");
                    $value.children().children().html("关注");
                }else{
                    alert("取消失败!");
                }
            })
        }
    },'json');
}