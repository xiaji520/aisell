<html>
<body>
<h2>Hello World!</h2>
<img src="/images/captcha"/>
<br>
<%@include file="/WEB-INF/views/head.jsp" %>
<%--图片放大--%>
<link rel="stylesheet" href="/easyui/plugin/jqzoom/style/jqzoom.css" type="text/css" media="screen">
<%--<script type="text/javascript" src="/easyui/plugin/jqzoom/js/jquery.js"></script>--%>
<script type="text/javascript" src="/easyui/plugin/jqzoom/js/jquery.jqzoom.js"></script>


<div class="jqzoom"><img src="images/head/2.jpg"  width="55" height="55" alt="shoe" jqimg="images/head/2.jpg" ></div>

</body>
<script type="text/javascript">
    $(document).ready(function () {
        $(document).ready(function(){
            $(".jqzoom").jqueryzoom({
                xzoom: 100, //设置放大 DIV 长度（默认为 200）
                yzoom: 100, //设置放大 DIV 高度（默认为 200）
                offset: 10, //设置放大 DIV 偏移（默认为 10）
                position: "right", //设置放大 DIV 的位置（默认为右边）
                preload:1,
                lens:1
            });
        });
    });
</script>
</html>
