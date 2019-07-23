$(function () {
    $('#systypes').datagrid({
        onSelect: function (index, row) {
            $.get("/sn", {sn: row.sn}, function (data) {
                $('#sysdetail').datagrid('loadData', data);
            })
        }
    });
})
