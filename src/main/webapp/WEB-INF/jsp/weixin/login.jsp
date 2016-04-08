<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../inc/taglibs.jsp" %>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登录</title>

    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
<header>
    <figure class="logo">
        <img src="${contextPath}/static/weixin/images/login_ logo.png">
    </figure>
</header>
<section class="ff">
    <form action="${contextPath}/weixin/login/loginCheck" method="post">
        <div class="flex_box">
            <input class="flex" type="text" placeholder="请输入手机号码" id="mobile" name="mobile"/>
        </div>
        <div class="flex_box">
            <input class="flex2" type="password" placeholder="请输入密码" id="password" name="password"/>
        </div>
        <div class="loading">
            <input class="loading_box" id="loginBtn" type="submit" value="登&nbsp;录"/>
        </div>
        <div class="loading">
            <input class="loading_box1" id="registerBtn" type="button" value="注&nbsp;册"/>
        </div>
        <div class="loading">
            <input class="loading_box1" onclick="weixin.fn.findPwd()" type="button" value="找回密码"/>
        </div>
    </form>
</section>
<script type="application/javascript">
    var weixin = {
        v: {},
        fn: {
            init: function () {
                $("#registerBtn").click(function () {
                    window.location.href = "${contextPath}/weixin/login/toRegister";
                });
            },
            findPwd: function () {
                window.location.href = "${contextPath}/weixin/login/lostPwd";
            }
        }
    }

    $(function () {
        weixin.fn.init();
    });
</script>
</body>
</html>