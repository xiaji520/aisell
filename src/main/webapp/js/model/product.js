//自定义上传图片验证
$.extend($.fn.validatebox.defaults.rules, {
    //验证是不是Excel文件
    isImg: {
        validator: function (value) {
            console.log(value);
            var index1 = value.lastIndexOf(".");
            var index2 = value.length;
            //后缀名
            var postf = value.substring(index1, index2).toLowerCase();
            if (postf !== ".jpeg" && postf !== ".jpg" && postf !== ".png") {
                alert('请输入后缀名为: *.jpeg或者 *.jpg或者*.png的文件, 而不是:*' + postf + '文件!')
                return false;
            } else {
                return true;
            }
        }
    }
});


//颜色
function productColor(value, row, index) {
    return `<img width="20" height="20" style="background-color:${value}"></img>`;
}

//其他关联数据库
function productObjs(value, row, index) {
    //return value ? value.name : ""
    return `${value.name}`;
}

//图片
function formatImage(value, row, index) {
    return ` <div class="jqzoom"><img src="${value}" width="60" height="60" jqimg="${value.toString().replace("_small.png", ".png")}"></div>`;
}

function loadSuccess(data) {
    $(document).ready(function () {
        $(".jqzoom").jqueryzoom({
            xzoom: 180, //设置放大 DIV 长度（默认为 200）
            yzoom: 180, //设置放大 DIV 高度（默认为 200）
            offset: 10, //设置放大 DIV 偏移（默认为 10）
            position: "right", //设置放大 DIV 的位置（默认为右边）
            preload: 1,
            lens: 1
        });
    });
}

//右键支持增删改
function showMenu(e, rowIndex, rowData) {
    //选中这个行
    $("#productGrid").datagrid("selectRow", rowIndex);
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
    var productGrid = $("#productGrid");
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
            productGrid.datagrid("load", params);
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
            var url = "/product/save";
            //获到id的值
            var productId = $("#productId").val();
            if (productId) {
                url = "/product/update?cmd=_upd_";
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
                        productGrid.datagrid("reload");
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
            var row = productGrid.datagrid("getSelected");
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
            if (row.unit) {
                row["unit.id"] = row.unit.id;
            }
            if (row.unit) {
                row["brand.id"] = row.brand.id;
            }
            if (row.types) {
                row["types.id"] = row.types.id;
            }
            editForm.form("load", row);
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //删除
        del() {
            //获取选中数据
            var row = productGrid.datagrid('getSelected');
            //如果没有选中给提示 选中就是否确定删除
            if (!row) {
                $.messager.alert('警告', '请选中再删除!', 'warning');
                return;
            } else {
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        //选是 确定删除
                        $.get("/product/delete", {id: row.id}, function (result) {
                            if (result.success) {
                                productGrid.datagrid("reload");
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
            var rows = productGrid.datagrid('getSelections');
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
                            $.get("/product/delete", {id: rows[i].id}, function (result) {
                                if (result.success) {
                                    productGrid.datagrid("reload");
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