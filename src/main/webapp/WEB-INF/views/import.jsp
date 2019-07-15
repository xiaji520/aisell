<%--
  User: xj
  Date: 2019/7/14
  Time: 15:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script type="text/javascript">
        $.extend($.fn.validatebox.defaults.rules, {
            //验证是不是Excel文件
            isExcel: {
                validator: function (value) {
                    console.log(value);
                    var index1 = value.lastIndexOf(".");
                    var index2 = value.length;
                    //后缀名
                    var postf = value.substring(index1, index2).toLowerCase();
                    if (postf !== ".xlsx" && postf !== ".xls") {
                        $("#sub").attr('disabled', true);
                        alert('请输入后缀名为: *.xlsx或者 *.xls的文件, 而不是:*' + postf + '文件!')
                    } else {
                        $("#sub").attr('disabled', false);
                    }
                },
            }
        });

    </script>
</head>
<body>

<form method="post" action="/import/empxlsx" enctype="multipart/form-data">
    <input class="easyui-filebox" name="empFile"
           data-options="prompt:'请选择一个excel文件',
                         accept:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel',
                         validType:'isExcel'"
           style="width:80%">
    &emsp;
    <button id="sub" type="submit" disabled="disabled">导入Excel</button>
</form>

</body>
</html>
