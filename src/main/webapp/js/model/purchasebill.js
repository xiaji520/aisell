function formatObjs(value) {
    return value ? value.name || value.username : "";
}

function formatStatus(value) {
    if (value == -1) {
        return `<span style="color: grey"><s>删除</s></span>`
    } else if (value == 1) {
        return `<span style="color: green">已审</span>`
    } else if (value == 0) {
        return `<span style="color: red">待审</span>`
    }
}

//右键支持增删改
function showMenu(e, rowIndex, rowData) {
    //选中这个行
    $("#purchasebillGrid").datagrid("selectRow", rowIndex);
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
    var purchasebillGrid = $("#purchasebillGrid");
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
            purchasebillGrid.datagrid("load", params);
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
            var url = "/purchasebill/save";
            //获到id的值
            var purchasebillId = $("#purchasebillId").val();
            if (purchasebillId) {
                url = "/purchasebill/update?cmd=_upd_";
            }
            //easyui的form
            editForm.form('submit', {
                //提交路径
                url: url,
                //提交前的操作
                //param里面的数据就会传到后台
                onSubmit: function (param) {
                    //获取dg的值 getRows:返回当前页的所有行。
                    var rows = dg.datagrid("getRows");
                    for (var i = 0; i < rows.length; i++) {
                        var row = rows[i];
                        //将值放入param 对应domian里面的List items
                        param[`items[${i}].product.id`] = row.product.id;
                        param[`items[${i}].num`] = row.num;
                        param[`items[${i}].price`] = row.price;
                        param[`items[${i}].descs`] = row.descs;
                    }

                    // 做一些检查
                    return $(this).form('validate');
                },
                //data : {success:true/false,msg:xxx}
                success: function (data) {
                    var result = JSON.parse(data);
                    if (result.success) {
                        purchasebillGrid.datagrid("reload");
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
            var row = purchasebillGrid.datagrid("getSelected");
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
            // 供应商
            if (row.supplier) {
                row["supplier.id"] = row.supplier.id;
            }
            // 采购员
            if (row.buyer) {
                row["buyer.id"] = row.buyer.id;
            }
            editForm.form("load", row);
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
            //拿到这行数据中的items(明细)
            var items = [...row.items];
            dg.datagrid("loadData", items);
        },
        //删除
        del() {
            //获取选中数据
            var row = purchasebillGrid.datagrid('getSelected');
            //如果没有选中给提示 选中就是否确定删除
            if (!row) {
                $.messager.alert('警告', '请选中再删除!', 'warning');
                return;
            } else {
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        //选是 确定删除
                        $.get("/purchasebill/delete", {id: row.id}, function (result) {
                            if (result.success) {
                                purchasebillGrid.datagrid("reload");
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
            var rows = purchasebillGrid.datagrid('getSelections');
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
                            $.get("/purchasebill/delete", {id: rows[i].id}, function (result) {
                                if (result.success) {
                                    purchasebillGrid.datagrid("reload");
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


    //放最后
    var dg = $("#itemsGrid"),
        defaultRow = {id: "", product: "", productColor: "", productImg: "", num: 0, price: 0, amount: 0, descs: ""},
        insertPosition = "bottom";
    //明细的grid组件的初始化设置
    var dgInit = function () {
        //datagrid的列数据
        var getColumns = function () {
            var result = [];

            //row：行记录数据
            var normal = [
                {
                    field: 'product', title: '商品', width: 180,
                    editor: {
                        type: "combobox",
                        options: {
                            valueField: 'id',
                            textField: 'name',
                            panelHeight: 'auto',
                            url: '/util/findProduct'
                        }
                    },
                    formatter(value) {
                        return value ? value.name : "";
                    }
                },
                {
                    field: 'productColor', title: '颜色', width: 80,
                    formatter(value, row, index) {
                        if (row && row.product) {
                            return `<div style='width: 25px;height: 25px;background-color:${row.product.color}'></div>`;
                        }
                    }
                },
                {
                    field: 'productImg', title: '图片', width: 100,
                    formatter(value, row, index) {
                        if (row && row.product) {
                            return `<img src='${row.product.smallPic}' alt='图片走丢了' />`;
                        }
                    }
                },
                {
                    field: 'num', title: '数量', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'price', title: '价格', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'amount', title: '小计', width: 100,
                    formatter(value, row, index) {
                        if (row && row.num && row.price) {
                            return row.num * row.price + "元";
                            // return row.num * row.price;
                        }
                        return 0;
                    }
                },
                {
                    field: 'descs', title: '备注', width: 100,
                    editor: {
                        type: "text"
                    }
                }
            ];
            result.push(normal);

            return result;
        };
        //准备datagrid组件中的属性
        var options = {
            idField: "ID", //id的字段(唯一的)
            rownumbers: true, // 行号
            fitColumns: true, //列的自适应
            fit: true, //自适应咱们的父窗口
            border: true, //是否显示边框
            singleSelect: true,
            columns: getColumns(),
            toolbar: "#itemsTools",
            bodyCls: "bodyCls",
            //表示开启单元格编辑功能
            enableCellEdit: true
        };
        //创建datagrid组件
        dg.datagrid(options);
    };

    //拿到插入的那一行数据的索引
    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : dg.datagrid("getRows").length;
    };

    //定义了一个变量,这个变量也是一个方法
    //button(按钮)Bind(绑定)Event(事件)
    var buttonBindEvent = function () {
        //添加一行数据
        $("#btnInsert").click(function () {
            var targetIndex = getInsertRowIndex(), targetRow = $.extend({}, defaultRow, {ID: $.util.guid()});
            dg.datagrid("insertRow", {index: targetIndex, row: targetRow});
            dg.datagrid("editCell", {index: 0, field: "Code"});
        });
        //删除一行数据
        $("#btnRemove").click(function () {
            var row = dg.datagrid("getSelected");
            if (row) {
                //得到行号
                var index = dg.datagrid("getRowIndex", row);
                //把这一行数据删除掉
                dg.datagrid("deleteRow", index);
            }
        });
    };

    //把grid初始化与事件绑定完成
    dgInit();
    buttonBindEvent();
});