//右键支持增删改
function showMenu(e, rowIndex, rowData) {
    //选中这个行
    $("#roleGrid").datagrid("selectRow", rowIndex);
    //第0个位置的面板不支持相应功能
    e.preventDefault();
    $('#gridMenu').menu('show', {
        left: e.pageX,
        top: e.pageY
    });
}

//对权限进行相应的格式化
function permsFormat(val) {
    var permsStr = "";
    for (let o of val) {
        permsStr += o.name + " ";
    }
    return permsStr;
}

//双击打开编辑
function onDblClickCell(index, field, value) {
    if (value != null) {
        var list = "";
        for (var i = 0; i < value.length; i++) {
            var name = value[i].name;
            if (name != undefined) {
                list += name + " ";
            }
        }
        if (list != ""&&list.length>=25) {
            alert(list)

        }
    }

}

$(function () {
    //获取常用组件(分页/查询条)
    var roleGrid = $("#roleGrid");
    var searchForm = $("#searchForm");
    var editDialog = $("#editDialog");
    var editForm = $("#editForm");

    //绑定事件
    $("*[data-method]").on("click", function () {
        var method = $(this).data("method");
        //动态调用
        window.xiaji[method]();
    });

    xiaji = {
        //查询
        search() {
            //serializeObject:可以拿到form中的所有数据,封装成json对象
            var params = searchForm.serializeObject();
            roleGrid.datagrid("load", params);
        },
        //关闭窗口
        closeDialog() {
            editDialog.dialog("close");
        },
        //添加
        add() {
            //让密码框显示
            $("*[data-edit]").show();
            $("*[data-edit] input").validatebox("enable");
            //修改弹出框Title
            editDialog.dialog('setTitle', "添加");
            //清空form中的数据
            editForm.form("clear");
            //清空左边grid的数据 loadData:加载本地数据，旧的行将被移除
            userPermissionGrid.datagrid("loadData", []);
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //保存
        save() {
            var url = "/role/save";
            //获到id的值
            var roleId = $("#roleId").val();
            if (roleId) {
                url = "/role/update?cmd=_upd_";
            }
            //easyui的form
            editForm.form('submit', {
                //提交路径
                url: url,
                //提交前的操作 param这个参数加的属性都会向后台提示
                onSubmit: function (param) {
                    //拿到role(左边的grid)中的所有权限
                    var rows = userPermissionGrid.datagrid("getRows");
                    //遍历它,拼接出相应的结构
                    for (var i = 0; i < rows.length; i++) {
                        var row = rows[i];
                        param[`permissions[${i}].id`] = row.id;
                    }
                    // 做一些检查
                    return $(this).form('validate');
                },
                //data : {success:true/false,msg:xxx}
                success: function (data) {
                    var result = JSON.parse(data);
                    if (result.success) {
                        roleGrid.datagrid("reload");
                    } else {
                        $.messager.alert('错误', `失败了,失败原因::${result.msg}`, "error");
                    }
                    //关闭弹出框
                    xiaji.closeDialog();
                }
            });
        },
        //修改
        update() {
            //获取到选中的那一行数据
            var row = roleGrid.datagrid("getSelected");
            //如果没有选中，给出提示后面的代码就不再执行
            if (!row) {
                $.messager.alert('警告', '请选中再修改!', 'warning');
                return;
            }
            //清空form中的数据
            editForm.form("clear");
            //让密码框失效且隐藏起来
            $("*[data-edit]").hide();
            $("*[data-edit] input").validatebox("disable");
            //修改弹出框Title
            editDialog.dialog('setTitle', "修改");
            //把结果进行回显
            editForm.form("load", row);
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
            //回显左边grid的数据
            //获取当前选中的行的所有权限
            //必需要拷备一个数组 不然会直接修改原数据
            var permissions = [...row.permissions];
            userPermissionGrid.datagrid("loadData", permissions);
        },
        //删除
        del() {
            //获取选中数据
            var row = roleGrid.datagrid('getSelected');
            //如果没有选中给提示 选中就是否确定删除
            if (!row) {
                $.messager.alert('警告', '请选中再删除!', 'warning');
                return;
            } else {
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        //选是 确定删除
                        $.get("/role/delete", {id: row.id}, function (result) {
                            if (result.success) {
                                roleGrid.datagrid("reload");
                            } else {
                                $.messager.alert('错误', `失败了,失败原因:${result.msg}`, "error");
                            }

                        })
                    }
                });
            }
        },
        //批量删除
        delMore() {
            //获取选中数据
            var rows = roleGrid.datagrid('getSelections');
            //如果没有选中给提示 选中就是否确定删除
            if (rows.length == 0) {
                $.messager.alert('警告', '请选中再删除!', 'warning');
                return;
            } else {
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        //定义变量值
                        for (var i = 0; i < rows.length; i++) {
                            //选是 确定删除
                            $.get("/role/delete", {id: rows[i].id}, function (result) {
                                if (result.success) {
                                    roleGrid.datagrid("reload");
                                } else {
                                    $.messager.alert('错误', `失败了,失败原因:${result.msg}`, "error");
                                }

                            })
                        }
                    }
                });
            }
        },
        //添加权限
        addPermission(index, row) {// index:点的行  row:这行的数据
            //获取左边grid的所有数据
            var rows = userPermissionGrid.datagrid("getRows");
            //循环所有的行,如果重复传,就不执行后面代码
            for (var o of rows) {
                if (o.id == row.id) {
                    $.messager.show({
                        title: '提示',
                        msg: '已添加,请勿重复操作!',
                        timeout: 2000,
                        showType: 'slide',
                        style: {
                            right: '',
                            top: document.body.scrollTop + document.documentElement.scrollTop,
                            bottom: ''
                        }
                    });
                    return;
                }
            }
            userPermissionGrid.datagrid("appendRow", row);
        },
        //删除权限
        removePermission(index, row) {
            userPermissionGrid.datagrid("deleteRow", index);
        }
    };

    //单独获取两个rid
    var userPermissionGrid = $('#userPermissionGrid');
    var allPermissionGrid = $('#allPermissionGrid');

    //创建当前角色对应的权限（左）grid
    userPermissionGrid.datagrid({
        fit: true,
        fitColumns: true,
        singleSelect: true,
        columns: [[
            {field: 'name', title: '名称', width: 100},
            {field: 'sn', title: '权限', width: 100},
            {field: 'url', title: '资源', width: 100}
        ]],
        onDblClickRow: xiaji.removePermission
    });

    //创建当前角色对应的权限（右）grid
    allPermissionGrid.datagrid({
        fit: true,
        url: '/permission/page',
        fitColumns: true,
        singleSelect: true,
        pagination: true,
        columns: [[
            {field: 'name', title: '名称', width: 100},
            {field: 'sn', title: '权限', width: 100},
            {field: 'url', title: '资源', width: 100}
        ]],
        onDblClickRow: xiaji.addPermission
    });

    //按键扩展支持
    $(document).bind('keydown', 'del', xiaji.del);
    $(document).bind('keydown', 'Shift+1', xiaji.add);
    $(document).bind('keydown', 'Shift+2', xiaji.update);
    $(document).bind('keydown', 'Shift+3', xiaji.delMore);

});