<%--
  User: xj
  Date: 2019/7/5
  Time: 11:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
                        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
                        //添加新选项卡
                        mainTab.tabs('add', {
                            //选项卡名称
                            title: text,
                            //是否可以关闭
                            closable: true,
                            //当前选项卡的内容
                            content: content
                        });
                    }
                }
            });
        });

        //展示table的菜单
        function showTabMenu(e, title, index) {
            //第0个位置的面板不支持相应功能
            e.preventDefault();
            if (index == 0) return;
            $('#tabMenu').menu('show', {
                left: e.pageX,
                top: e.pageY,
                onClick: function (item) {
                    if (item.text == "关闭窗口") {
                        $('#mainTab').tabs('close', index);
                    } else if (item.text == "关闭所有窗口") {
                        var tabs = $('#mainTab').tabs('tabs');
                        //第0个位置的面板不关闭
                        for (var i = 1; i <= tabs.length; i++) {
                            //注意，这时永远关掉第一个面板
                            $('#mainTab').tabs('close', 1);
                        }
                    }
                }
            });
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:100px;">
    <h1 style="text-align: center">易 用 宝</h1>
    <%-- shiro:principal：显示主体名称--%>
    <div style="text-align: right;padding-right:20px;">欢迎您, <shiro:principal/> &emsp;<a href="/logout"
                                                                                        style="color: GrayText">注销</a>
    </div>
</div>
<div data-options="region:'west',title:'菜单',split:true" style="width:230px;">
    <ul id="menuTree"></ul>
</div>
<div id="mainTab" class="easyui-tabs" data-options="region:'center',onContextMenu:showTabMenu">
    <div title="首页" style="padding:20px;display:none;">
        <%--<h3 style="text-align: center"> 欢迎来到易用宝操作系统!</h3>--%>
        <img src="/images/img/welcome.jpg" alt="图片走丢了..." height="100%" width="100%">
    </div>
</div>

<div id="tabMenu" class="easyui-menu" style="width:120px;">
    <div data-options="iconCls:'icon-clear'">关闭窗口</div>
    <div data-options="iconCls:'icon-remove'">关闭所有窗口</div>
</div>

</body>
</html>
