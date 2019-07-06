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

$(function () {
    //获取组件(分页/查询条)
    var employeeGrid = $("#employeeGrid");
    var searchForm = $("#searchForm");
    //绑定事件
    $("*[data-method]").on("click", function () {
        var method = $(this).data("method");
        //动态调用
        window.xiaji[method]();
    })

    xiaji = {
        search() {
            //serializeObject:可以拿到form中的所有数据,封装成json对象
            var params = searchForm.serializeObject();
            employeeGrid.datagrid("load", params);
        }
    }

});
