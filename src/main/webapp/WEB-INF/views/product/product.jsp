<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>易用宝，Product管理</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/js/model/product.js"></script>
    <%--图片放大--%>
    <link rel="stylesheet" href="/easyui/plugin/jqzoom/style/jqzoom.css" type="text/css" media="screen">
    <%--    <script type="text/javascript" src="/easyui/plugin/jqzoom/js/jquery.js"></script>--%>
    <script type="text/javascript" src="/easyui/plugin/jqzoom/js/jquery.jqzoom.js"></script>
</head>
<body>

<%--pagination:分页--%>
<table id="productGrid" class="easyui-datagrid"
       data-options="url:'/product/page',
       fitColumns:true,
       singleSelect:false,
       pagination:true,
       onDblClickCell:onDblClickCell,
       toolbar:'#gridTools',
       onRowContextMenu:showMenu
       ,onLoadSuccess:loadSuccess">
    <thead>
    <tr>
        <th data-options="field:'id',width:100,align:'center'">id</th>
        <th data-options="field:'name',width:100,align:'center'">名称</th>
        <th data-options="field:'color',width:100,formatter:productColor,align:'center'">颜色</th>
        <th data-options="field:'smallPic',width:45,formatter:formatImage,align:'center'">图片</th>
        <th data-options="field:'costPrice',width:100,align:'center'">成本价</th>
        <th data-options="field:'salePrice',width:100">销售价</th>
        <th data-options="field:'types',width:100,formatter:productObjs,align:'center'">类型</th>
        <th data-options="field:'unit',width:100,formatter:productObjs,align:'center'">单位</th>
        <th data-options="field:'brand',width:100,formatter:productObjs,align:'center'">品牌</th>
    </tr>
    </thead>
</table>

<%--右键支持增删改--%>
<div id="gridMenu" class="easyui-menu" style="width:120px;">
    <div data-options="iconCls:'icon-add'" data-method="add">添加</div>
    <div data-options="iconCls:'icon-edit'" data-method="update">修改</div>
    <div data-options="iconCls:'icon-remove'" data-method="del">删除</div>
    <div data-options="iconCls:'icon-remove'" data-method="delMore">批量删除</div>
</div>

<%--grid顶部工具栏--%>
<div id="gridTools" style="padding:5px;height:auto">
    <%--功能条--%>
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="del" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
        <a href="#" data-method="delMore" class="easyui-linkbutton" iconCls="icon-remove" plain="true">批量删除</a>
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
    <form id="editForm" method="post" enctype="multipart/form-data">
        <input id="productId" type="hidden" name="id"/>
        <table cellpadding="5">
            <tr>
                <td>名称:</td>
                <td><input class="easyui-validatebox" type="text" name="name"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td>颜色:</td>
                <td><input class="easyui-validatebox" type="color" name="color"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td>图片:</td>
                <td><input class="easyui-filebox" name="productImage"
                           data-options="accept:'image/jpeg,image/png',
                            <%--自定义验证--%>
                            validType:'isImg'"
                /></td>
            </tr>
            <tr>
                <td>成本价:</td>
                <td><input class="easyui-validatebox" type="text" name="costPrice"
                           data-options="required:true,validType:'JustTrueNum'"/></td>
            </tr>
            <tr>
                <td>销售价:</td>
                <td><input class="easyui-validatebox" type="text" name="salePrice"
                           data-options="required:true,validType:'JustTrueNum'"/></td>
            </tr>
            <tr>
                <td>类型:</td>
                <td><input name="types.id" class="easyui-combobox" panelHeight="auto"
                           data-options="valueField:'id',textField:'name',url:'/util/types',required:true"/></td>
            </tr>
            <tr>
                <td>单位:</td>
                <td><input name="unit.id" class="easyui-combobox" panelHeight="auto"
                           data-options="valueField:'id',textField:'name',url:'/util/unit',required:true"/></td>
            </tr>
            <tr>
                <td>品牌:</td>
                <td><input name="brand.id" class="easyui-combobox" panelHeight="auto"
                           data-options="valueField:'id',textField:'name',url:'/util/brand',required:true"/></td>
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