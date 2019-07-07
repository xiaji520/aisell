/*
    value 当前单元格中的值
    row 当前单元格对应的整行的值
    index 行的索引
 */
function formatImage(value, row, index) {
    return `<img src="${value}" width="55" height="55">`;
}

function formatDept(value) {
    return value ? value.name : "";
}

//自定义验证用户名是否存在
$.extend($.fn.validatebox.defaults.rules, {
    //验证名称
    checkName: {
        //验证的规则
        /*
         value ：文本框当前的值
         param : 传过来的值(数组)
         */
        validator: function (value, param) {
            //用ajax的同步提交!!
            var result = $.ajax({
                url: "/employee/checkUsername",
                data: {username: value},
                async: false //false  同步
            }).responseText;
            result = JSON.parse(result);
            //返回false,代表验证失败
            return result;
        },
        //失败提示
        message: '用户名已存在!'
    }
});


$(function () {
    //获取常用组件(分页/查询条)
    var employeeGrid = $("#employeeGrid");
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
            employeeGrid.datagrid("load", params);
        },
        //关闭窗口
        closeDialog() {
            editDialog.dialog("close");
        },
        //添加
        add() {
            //打开弹出框(居中)
            editDialog.dialog("center").dialog("open");
        },
        //保存
        save() {
            //easyui的form
            editForm.form('submit', {
                //提交路径
                url: "/employee/save",
                //提交前的操作
                onSubmit: function () {
                    // 做一些检查
                    return $(this).form('validate');
                },
                //data : {success:true/false,msg:xxx}
                success: function (data) {
                    var result = JSON.parse(data);
                    if (result.success) {
                        employeeGrid.datagrid("reload");
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
            alert("修改")
        },

        //删除
        del() {
            //获取选中数据
            var row = employeeGrid.datagrid('getSelected');
            //如果没有选中给提示 选中就是否确定删除
            if (!row) {
                $.messager.alert('警告', '请选中再删除!', 'warning');
                return;
            } else {
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        //选是 确定删除
                        $.get("/employee/delete", {id: row.id}, function (result) {
                            if (result.success) {
                                employeeGrid.datagrid("reload");
                            } else {
                                $.messager.alert('错误', `失败了,失败原因:${result.msg}`, "error");
                            }

                        })
                    }
                });
            }
        }

    }

});
