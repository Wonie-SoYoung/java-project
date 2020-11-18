<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h3><#if error??>${error}</#if></h3>
    <#if type == '1'>
        <h1>增加</h1>
        <form action="http://localhost:8081/insert" method="post">
            <p>名称：<input type="text" name="gname"></p>
            <p>录入时间：<input type="date" name="created"></p>
            <p>录入人：<input type="text" name="createdUser"></p>
            <p>手机：<input type="text" name="phone"></p>
            <p>分类：
                <select name="type">
                    <option value="1">公司</option>
                    <option value="2">行政部</option>
                    <option value="3">财务部</option>
                    <option value="4">研发部</option>
                </select>
            </p>
            <input type="submit" value="Submit" />
            <a href="http://localhost:8081/list">返回</a>
        </form>
    </#if>
    <#if type == '2'>
        <h1>修改</h1>
        <form action="http://localhost:8081/update" method="post">
            <input type="hidden" name="id" value="${goods.id}">
            <p>名称：<input type="text" name="gname" value="${goods.gname}"></p>
            <p>录入时间：<input type="text" name="created" value="${goods.created?string("yyyy-MM-dd")}"></p>
            <p>录入人：<input type="text" name="createdUser" value="${goods.createdUser}"></p>
            <p>手机：<input type="text" name="phone" value="${goods.phone}"></p>
            <p>分类：
                <select name="type">
                    <option value="1" <#if goods.type==1>selected</#if>>公司</option>
                    <option value="2" <#if goods.type==2>selected</#if>>行政部</option>
                    <option value="3" <#if goods.type==3>selected</#if>>财务部</option>
                    <option value="4" <#if goods.type==4>selected</#if>>研发部</option>
                </select>
            </p>
            <input type="submit" value="Submit" />
            <a href="http://localhost:8081/list">返回</a>
        </form>
    </#if>

</body>
</html>