<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>hello world</h1><a href="http://localhost:8081/addHtml">增加</a>
    <div>
        <form action="http://localhost:8081/list" method="get">
            名称：<input type="text" name="gname">
            登记日期：<input type="date" name="created">
            登记人：<input type="text" name="createdUser">
            手机号：<input type="text" name="phone">
            分类：
            <select name="type">
                <option value="">未选择</option>
                <option value="1">公司</option>
                <option value="2">行政部</option>
                <option value="3">财务部</option>
                <option value="4">研发部</option>
            </select>
            <input type="submit" value="Submit" />
        </form>
    </div>
    <table style="margin-top: 20px">
        <tr>
            <th>id</th>
            <th>名称</th>
            <th>登记日期</th>
            <th>登记人</th>
            <th>手机号</th>
            <th>分类</th>
            <th>操作</th>
        </tr>
        <#list goods as item>
            <tr>
                <td>${item.id}</td>
                <td>${item.gname}</td>
                <td>${item.created?string("yyyy/MM/dd")}</td>
                <td>${item.createdUser}</td>
                <td>${item.phone}</td>
                <#switch item.type>
                    <#case 1>
                        <td>公司</td>
                        <#break>
                    <#case 2>
                        <td>行政部</td>
                        <#break>
                    <#case 3>
                        <td>财务部</td>
                        <#break>
                    <#case 4>
                        <td>研发部</td>
                        <#break>
                </#switch>
                <td><a href="http://localhost:8081/updateHtml/${item.id}">修改</a>/<a href="http://localhost:8081/delete/${item.id}">删除</a></td>
            </tr>
        </#list>
    </table>

    <script type="text/javascript" src="/static/jquery-1.12.4.js" ></script>
    <script>
        $(function () {
            $("update")
        })
    </script>
</body>
</html>