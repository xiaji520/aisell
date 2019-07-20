<%--
  User: xj
  Date: 2019/7/10
  Time: 16:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>找回密码</title>
    <%@ include file="/WEB-INF/views/head.jsp" %>

    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript">

        //判断当前页面是否是顶层页面
        if (top != window) {
            //window.top.location.href = "/login";
            window.top.location.href = window.location.href;
        }
        //回车登录
        $(document.documentElement).on("keyup", function (event) {
            console.debug(event);
            var keyCode = event.keyCode;
            if (keyCode === 13) { // 捕获回车
                submitForm(); // 提交表单
            }
        });

        //提交form表单
        function submitForm() {
            $('#loginForm').form('submit', {
                url: "/forgetPwd",
                onSubmit: function () {
                    return $(this).form('validate');
                },
                //注意:现在这个data是一个字符串:{success:true,msg:""}
                success: function (data) {
                    var result = JSON.parse(data);
                    if (result.success) {
                        alert("请在邮箱查看地址!");
                        window.location.href = "/login";
                    } else {
                        //如果登录失败，给出错误提示
                        $("#msg").text(result.msg);
                        setTimeout(function () {
                            $("#msg").hide("slow").text("");
                            $("#captcha").attr("src", "/images/captcha" + "?" + Math.random());
                        }, 2000);
                        $("#msg").show();

                        //$.messager.alert('错误', result.msg);
                    }
                }
            });
        }
    </script>

</head>
<body>

<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form id="loginForm" action="/setPwd" method="post" class="easyui-form">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top">
                        <h1>找回密码</h1>
                    </div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="email"
                                       placeholder="请输入您的邮箱"/>
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" class="input input-big" name="captcha" placeholder="请填写右侧的验证码"/>
                                <img src="/images/captcha" id="captcha"
                                     alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;"
                                     onClick="this.src=this.src+'?'">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="field field-icon-right">
                            <h4 id="msg" style="text-align: center;color: red"></h4>
                        </div>
                    </div>

                    <div style="padding:30px;">
                        <input type="button" id="button" class="button button-block bg-main text-big input-big"
                               onclick="submitForm();" value="确定">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>