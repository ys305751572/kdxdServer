<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/cc.css"/>
    <title>个人资料</title>
</head>
<body>
<section class="banner">
    <div class="pic_box">
        <img src="${wxUser.headImgUrl}"><br>
        <span class="pic_id">${wxUser.nickname}</span>
    </div>
</section>
<aside>
    <span class="sidebar"><a href="javascript:void(0)">¥${user.money}</a></span>
</aside>
<section class="menu">
    <div class="bgt">
        <img src="${contextPath}/static/weixin/images/personal center_dotted line _view.png">
    </div>
    <a href="weixin/coinlog/index">
        <div class="cz">
            <span class="span">我要充值</span>
        </div>
    </a>
    <a href="weixin/order/index">
        <div class="dd">
            <span class="span">我的订单</span>
        </div>
    </a>
    <a href="weixin/address/list">
        <div class="add">
            <span class="span">收货地址</span>
        </div>
    </a>
    <a href="weixin/coinlog/list">
        <div class="jl">
            <span class="span">充值记录</span>
        </div>
    </a>
</section>

<footer class="loading">
    <div class="ending1" onclick="logOut()">
        <input class="button5" type="submit" value="注&nbsp;销" style="background:#ff8400; "/>
    </div>
</footer>

</body>
</html>
<script>
    // 用户注销
    function logOut() {
        window.location.href = "weixin/user/logout";
    }
</script>