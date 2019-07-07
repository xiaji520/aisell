<%--
  User: xj
  Date: 2019/7/5
  Time: 11:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>易用宝首页</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script>
        $(function () {
            var mainTab = $("#mainTab");
            $('#menuTree').tree({
                url: '/json/treeMenu.json',
                onClick: function (data) {
                    //获取路径
                    var url = data.url;
                    //判断是否有路径,没有就不打开
                    if (!url) {
                        return;
                    }
                    //获取菜单名
                    var text = data.text;

                    //判断选项卡是否存在,存在就选中,不存在就添加新的
                    if (mainTab.tabs("exists", text)) {
                        mainTab.tabs("select", text)
                    } else {
                        //添加新选项卡
                        mainTab.tabs('add', {
                            //选项卡名称
                            title: text,
                            //是否可以关闭
                            closable: true,
                            //当前选项卡的内容
                            content: 'Tab Body'
                        });
                    }
                }
            });

        })
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:100px;">
    <h1 style="text-align: center">易 用 宝</h1>
</div>
<div data-options="region:'west',title:'菜单',split:true" style="width:230px;">
    <ul id="menuTree"></ul>
</div>
<div id="mainTab" class="easyui-tabs" data-options="region:'center'">
    <div title="首页" style="padding:20px;display:none;">
       <h3> 欢迎来到易用宝操作系统!</h3>
    </div>
</div>
</body>
</html>
