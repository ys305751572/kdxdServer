<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">

    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>

    <title>设置密码</title>
</head>
<body style="background: #f7f7f7">
<section class="ff1">
    <form action="${contextPath}/weixin/login/register" method="post">
        <div class="flex_box3">
            <input type="hidden" id="username" name="username" value="${reg.username}">
            <input type="hidden" id="code" name="code" value="${reg.code}">
            <input class="flex4" type="password" placeholder="请设置登录密码" name="password" id="password"/>
        </div>
        <div class="flex_box3">
            <input class="flex4" type="password" placeholder="请输入确认密码" name="password1" id="confirmPwd"/>
        </div>
        <div class="ending1">
            <input class="button5" type="button" value="提交密码" style="background-color: #00a642"/>
        </div>
    </form>
</section>

<script type="application/javascript">
    $(function () {
        $(".button5").click(function () {
            var password = $("#password").val();
            var confirmPwd = $("#confirmPwd").val();

            if (null == password || password == '') {
                alert('密码不能为空');
            } else if (password != confirmPwd) {
                alert('前后密码不一致');
            } else {
                $.post("${contextPath}/weixin/login/register", {
                            "username": $("#username").val(),
                            "code": $("#code").val(),
                            "password": password
                        },
                        function (result) {
                            if (result.status == 0) {
                                window.location.href = "${contextPath}/weixin/login/toLogin"
                            }
                            else {
                                alert(result.msg);
                            }
                        });
            }
        });
    })

</script>
</body>
</html>
