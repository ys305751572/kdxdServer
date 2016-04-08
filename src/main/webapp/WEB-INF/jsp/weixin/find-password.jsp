<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <title>找回密码</title>
</head>
<body style="background: #f7f7f7">
<section class="ff1">
    <form>
        <div class="flex_box3">
            <input class="flex4" id="mobile" type="text" placeholder="请输入手机号" name="mobile"/>
        </div>
        <div class="flex_box3" style="border: none;">
            <input class="flex4" id="yzm" type="text" placeholder="请输入验证码" name="code"/>
            <input type="button" class="btn_sendcode1" onclick="sendCode(this);" value="点击发送验证码"/>
        </div>

        <div class="loading">
            <input class="register1" id="tg1" type="button" onclick="subInfo()" value="提&nbsp;交"/>
        </div>
    </form>
</section>
</body>
</html>
<script type="text/javascript">
    var countdown = 60;
    function sendCode(btn) {
        var mobile = $('#mobile').val();
        if (null == mobile || mobile == '') {
            alert('手机号不能为空');
        } else {
            // 发送验证码
            $.ajax({
                type: "post",
                url: "weixin/login/sendCode",
                data: {
                    mobile: mobile
                },
                success: function (result) {
                    if (result.status != 0) {
                        alert(result.msg);
                    }
                }
            });
            jian(btn);
        }
    }

    function jian(btn) {
        var tg = document.getElementById("tg1");
        if (countdown == 0) {
            btn.removeAttribute("disabled");
            btn.value = "点击发送验证码";
            countdown = 60;
            btn.className = "btn_sendcode1";
            tg.className = "register1";
        } else {
            countdown--;
            btn.setAttribute("disabled", true);
            btn.value = countdown + "秒后重新发送";
            btn.className = "btn_sendcode2";
            tg.className = "register2";
            setTimeout(function () {
                jian(btn)
            }, 1000)
        }
    }



    function subInfo() {
        var mobile = $('#mobile').val();
        var code = $('#yzm').val();

        if (null == mobile || mobile == '') {
            alert('手机号不能为空');
        } else if (null == code || code == '') {
            alert('验证码不能为空');
        } else {
            $.ajax({
                type: "post",
                url: "weixin/login/findPwd",
                data: {
                    mobile: mobile,
                    code: code
                },
                success: function (result) {
                    if (result.status != 0) {
                        alert(result.msg);
                    } else {
                        alert("您的旧密码是：" + result.msg);
                        window.location.href = "${contextPath}/weixin/login/toLogin"
                    }
                }
            });
        }
    }
</script>