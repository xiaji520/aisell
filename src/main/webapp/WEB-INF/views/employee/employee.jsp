<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/5
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/js/model/employee.js"></script>
</head>
<body>


<%--pagination:分页--%>
<table id="employeeGrid" class="easyui-datagrid"
       data-options="url:'/employee/page',
       fitColumns:true,
       singleSelect:true,
       pagination:true,
       toolbar:'#gridTools'">
    <thead>
    <tr>
        <th data-options="field:'id',width:50,align:'center'">编码</th>
        <th data-options="field:'headImage',width:50,formatter:formatImage,align:'center'">头像</th>
        <th data-options="field:'username',width:60,align:'center'" sortable="true">名称</th>
        <th data-options="field:'password',width:120">密码</th>
        <th data-options="field:'age',width:50,align:'center'" sortable="true">年龄</th>
        <th data-options="field:'email',width:100,align:'center'">邮箱</th>
        <th data-options="field:'department',width:50,formatter:formatDept,align:'center'" sortable="true">部门</th>
    </tr>
    </thead>
</table>

<%--grid顶部工具栏--%>
<div id="gridTools" style="padding:5px;height:auto">
    <%--功能条--%>
    <div style="margin-bottom:5px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true">剪切</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">移除</a>
    </div>
    <%--查询条--%>
    <form id="searchForm">
        用户名:<input name="username" class="easyui-textbox" style="width:80px">
        邮箱:<input name="email" class="easyui-textbox" style="width:80px">
        部门:<input id="cc" name="departmentId" class="easyui-combobox" panelHeight="auto"
               data-options="valueField:'id',textField:'name',url:'/util/dept'"/>
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>

</body>
</html>
