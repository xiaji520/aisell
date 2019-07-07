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
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="del" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <%--查询条--%>
    <form id="searchForm">
        用户名:<input name="username" class="easyui-textbox" style="width:80px">
        邮箱:<input name="email" class="easyui-textbox" style="width:80px">
        部门:<input id="cc" name="departmentId" class="easyui-combobox" panelHeight="auto"
                  data-options="valueField:'id',textField:'name',url:'/util/dept'"/>
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>

<%--添加与修改的表单对话框--%>
<div id="editDialog" class="easyui-dialog" title="功能编辑" style="width:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="editForm" method="post">
        <table cellpadding="5">
            <tr>
                <td>用户名:</td><%--data-options="required:true"必须输入--%>
<%--                <td><input class="easyui-validatebox" type="text" name="username" data-options="required:true,validType:'checkName"></input></td>--%>
                <td><input class="easyui-validatebox" type="text" name="username" data-options="required:true,validType:'checkName'"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input id="password" class="easyui-validatebox" type="password" name="password" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>确认密码:</td>
                <td><input class="easyui-validatebox" type="password" data-options="required:true" validType="equals['password','id']"/></td>
            </tr>
            <tr>
                <td>邮件:</td>
                <td><input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'email'"/></td>
            </tr>
            <tr>
                <td>年龄:</td><%--integerRange[1,100]扩展插件--%>
                <td><input class="easyui-validatebox" type="text" name="age" data-options="validType:'integerRange[1,100]'"/></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input  name="department.id" class="easyui-combobox"   panelHeight="auto"
                            data-options="valueField:'id',textField:'name',url:'/util/dept',required:true" />
                </td>
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
