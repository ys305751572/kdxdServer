<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/ss.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <title>邀请好友</title>
</head>
<body>
<section class="content">
    <div class="topbar">
        <img class="logo" src="${wxUser.headImgUrl}">
        <img class="bgt" src="${contextPath}/static/weixin/images/Group 2 (2).png">
        <span class="word">hi、您好、我是您的好友${wxUser.nickname}，特邀请您来福利抢购</span>
    </div>
    <div class="banner">
        <img class="banner_box" src="${contextPath}/static/weixin/images/Bitmap.png">
        <span class="fl">福利大派送，水果大抢购，只要分享100%中奖率</span>
    </div>
    <div class="lxkf">
        <a href="#"><img class="dh" src="${contextPath}/static/weixin/images/coupons_contact customer service.png"><span class="kf">联系客服</span></a>
    </div>
    <div class="lxkf" onclick="goActivityInfo()">
        <a href="javascript:void(0)">活动详情</a>
    </div>
    <div class="lxkf">
        <a href="javascript:void(0)">进入公众号</a>
    </div>
</section>
<footer class="loading">
    <div class="ending1">
        <input class="button5" type="button" onclick="toRegister()" value="立即加入"/>
    </div>
</footer>
</body>
</html>
<script type="text/javascript">
    function goActivityInfo() {
        window.location.href = "${contextPath}/weixin/activity/info?id=1";
    }

    function toRegister() {
        window.location.href = "${contextPath}/weixin/user/toLogin";
    }
</script>