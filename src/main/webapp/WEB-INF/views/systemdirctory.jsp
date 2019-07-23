<%--
  User: xj
  Date: 2019/7/10
  Time: 16:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/js/model/systemdirctory.js"></script>
</head>
<body>
<div id="p" class="easyui-panel"
     data-options="iconCls:'icon-save',closable:true,fit:true,
                collapsible:true,minimizable:true,maximizable:true">
    <div>
        <form id="editForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>
                        <div id="roleLayout" class="easyui-layout" style="width:853px;height:400px;">
                            <div data-options="region:'west',title:'数据字典类型'" style="width:430px;">
                                <table id="systypes" class="easyui-datagrid" fit="true"
                                       data-options="fitColumns:true,url:'/query',singleSelect:true">
                                    <thead>
                                    <tr>
                                        <th data-options="field:'name',width:100">名称</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>


                            <div data-options="region:'center',title:'数据字典明细'">
                                <table id="sysdetail" class="easyui-datagrid" fit="true"
                                       data-options="fitColumns:true,singleSelect:true">
                                    <thead>
                                    <tr>
                                        <th data-options="field:'name',width:100">名称</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>

                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>


</div>


</body>
</html>
