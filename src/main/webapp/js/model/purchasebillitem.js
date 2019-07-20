//状态的格式化
function formatStatus(value) {
    if (value == -1) {
        return `<span style="color: grey"><s>删除</s></span>`
    } else if (value == 1) {
        return `<span style="color: green">已审</span>`
    } else if (value == 0) {
        return `<span style="color: red">待审</span>`
    }
}

$(function () {
    //获取常用组件(分页/查询条)
    var purchasebillitemGrid = $("#purchasebillitemGrid");
    var searchForm = $("#searchForm");
    var chartDialog = $('#chartDialog');

    purchasebillitemGrid.datagrid({
        fit: true,
        // 行号
        rownumbers: true,
        //远程排序
        remoteSort: false,
        nowrap: false,
        fitColumns: true,
        toolbar: "#gridTools",
        url: '/purchasebillitem/findItems',
        columns: [[
            {field: 'id', title: '编号', width: 100},
            {field: 'supplierName', title: '供应商', width: 100},
            {field: 'buyerName', title: '采购员', width: 100},
            {field: 'productName', title: '产品', width: 100},
            {field: 'productTypeName', title: '产品类型', width: 100},
            {field: 'vdate', title: '日期', width: 100},
            {field: 'price', title: '单价', width: 100},
            {field: 'num', title: '数量', width: 100},
            {field: 'amount', title: '小计', width: 100},
            {field: 'status', title: '状态', formatter: formatStatus, width: 100}
        ]],
        //供应商分组字段
        groupField: 'groupField',
        //支持分组视图功能
        view: groupview,
        groupFormatter: function (value, rows) {
            var totalNum = 0;
            var totalAmount = 0;
            for (let r of rows) {
                totalNum += r.num;
                totalAmount += r.amount;
            }
            //成都供应商 - 10 条数据 共34商品 总金额:344
            return `${value} - ${rows.length}条数据 <span style="color: deepskyblue">共${totalNum}个商品</span>
                    <span  style="color: deeppink">总金额:${totalAmount}</span>`;
        }
    });

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
            purchasebillitemGrid.datagrid("load", params);
        },
        //查询3D图片
        chart3D() {
            //打开弹出框
            chartDialog.dialog("center").dialog("open");
            //获取到表单所有值
            var params = searchForm.serializeObject();
            //请求的时候把值传到后台
            $.post("/purchasebillitem/findCharts", params, function (result) {
                //展示图表
                Highcharts.chart('container', {
                    chart: {
                        type: 'pie',
                        options3d: {
                            enabled: true,
                            //倾斜度
                            alpha: 45,
                            beta: 0
                        }
                    },
                    title: {
                        text: '销售占比3D'
                    },
                    //鼠标移上去后显示的数据
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            //是否自己可以选择
                            allowPointSelect: true,
                            //鼠标指上来后的样式
                            cursor: 'pointer',
                            //深度
                            depth: 35,
                            dataLabels: {
                                enabled: true,
                                format: '{point.name}'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '销售占比',
                        data: result
                    }]
                });
            })
        },

        //查询2D图片
        chart2D() {
            //打开弹出框
            chartDialog.dialog("center").dialog("open");
            //获取到表单所有值
            var params = searchForm.serializeObject();
            //请求的时候把值传到后台
            $.post("/purchasebillitem/findCharts", params, function (result) {
                //展示图表
                Highcharts.chart('container', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: '销售占比2D'
                    },
                    //鼠标移上去后显示的数据
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            //是否自己可以选择
                            allowPointSelect: true,
                            //鼠标指上来后的样式
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: '销售占比',
                        colorByPoint: true,
                        data: result
                    }]
                });
            })
        }
    };


});