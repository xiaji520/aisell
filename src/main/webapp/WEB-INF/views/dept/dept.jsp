<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>易用宝，Dept管理</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/js/model/dept.js"></script>
</head>
<body>

<%--pagination:分页--%>
<table id="deptGrid" class="easyui-datagrid"
       data-options="url:'/dept/page',
       fitColumns:true,
       singleSelect:true,
       pagination:true,
       toolbar:'#gridTools',
       onRowContextMenu:showMenu">
    <thead>
    <tr>
                    <th width="20"  field="id" sortable="true">id</th>
                    <th width="20"  field="name" sortable="true">name</th>
            </tr>
    </thead>
</table>

<%--右键支持增删改--%>
<div id="gridMenu" class="easyui-menu" style="width:120px;">
    <div data-options="iconCls:'icon-add'" data-method="add">添加</div>
    <div data-options="iconCls:'icon-edit'" data-method="update">修改</div>
    <div data-options="iconCls:'icon-remove'" data-method="del">删除</div>
</div>

<%--grid顶部工具栏--%>
<div id="gridTools" style="padding:5px;height:auto">
    <%--功能条--%>
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="del" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <%--查询条--%>
    <form id="searchForm">
        名称:<input name="name" class="easyui-textbox" style="width:80px">
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>

<%--添加与修改的表单对话框--%>
<div id="editDialog" class="easyui-dialog" title="功能编辑" style="width:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="editForm" method="post">
        <input id="deptId" type="hidden" name="id"/>
        <table cellpadding="5">
                    <tr>
                <td>name:</td>
                <td><input class="easyui-validatebox" type="text" name="name"
                           data-options="required:true"/></td>
            </tr>
                 </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-method="save">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-method="closeDialog">关闭</a>
    </div>
</div>


</body>
</html>