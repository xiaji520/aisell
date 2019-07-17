//右键支持增删改
function showMenu(e, rowIndex, rowData) {
    //选中这个行
    $("#supplierGrid").datagrid("selectRow", rowIndex);
    //第0个位置的面板不支持相应功能
    e.preventDefault();
    $('#gridMenu').menu('show', {
        left: e.pageX,
        top: e.pageY
    });
}

//双击打开编辑
function onDblClickCell() {
    window.xiaji.update();
}

$(function () {
    //获取常用组件(分页/查询条)
    var supplierGrid = $("#supplierGrid");
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
            supplierGrid.datagrid("load", params);
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
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //保存
        save() {
            var url = "/supplier/save";
            //获到id的值
            var supplierId = $("#supplierId").val();
            if (supplierId) {
                url = "/supplier/update?cmd=_upd_";
            }
            //easyui的form
            editForm.form('submit', {
                //提交路径
                url: url,
                //提交前的操作
                onSubmit: function () {
                    // 做一些检查
                    return $(this).form('validate');
                },
                //data : {success:true/false,msg:xxx}
                success: function (data) {
                    var result = JSON.parse(data);
                    if (result.success) {
                        supplierGrid.datagrid("reload");
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
            var row = supplierGrid.datagrid("getSelected");
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
            if (row.department) {
                row["department.id"] = row.department.id;
            }
            editForm.form("load", row);
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //删除
        del() {
            //获取选中数据
            var row = supplierGrid.datagrid('getSelected');
            //如果没有选中给提示 选中就是否确定删除
            if (!row) {
                $.messager.alert('警告', '请选中再删除!', 'warning');
                return;
            } else {
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        //选是 确定删除
                        $.get("/supplier/delete", {id: row.id}, function (result) {
                            if (result.success) {
                                supplierGrid.datagrid("reload");
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
            var rows = supplierGrid.datagrid('getSelections');
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
                            $.get("/supplier/delete", {id: rows[i].id}, function (result) {
                                if (result.success) {
                                    supplierGrid.datagrid("reload");
                                } else {
                                    $.messager.alert('错误', `失败了,失败原因:${result.msg}`, "error");
                                }

                            })
                        }
                    }
                });
            }
        }
    };

    //按键扩展支持
    $(document).bind('keydown', 'del', xiaji.del);
    $(document).bind('keydown', 'Shift+1', xiaji.add);
    $(document).bind('keydown', 'Shift+2', xiaji.update);
    $(document).bind('keydown', 'Shift+3', xiaji.delMore);
    
 });