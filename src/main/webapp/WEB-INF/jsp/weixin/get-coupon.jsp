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
    <script type="text/javascript"></script>
    <title>优惠卷领取</title>
</head>
<body>
<section class="content">

    <div class="topbar1">
        <img class="logo1" src="${wxUser.headImgUrl}"/>
        <span class="note1">hi、您好、我是您的好友${wxUser.nickname}，赠送您</span>
        <img class="bgt3" src="images/Group 2.png">
    </div>
    <div class="topbar2">
        <span class="bzq2">必中卷</span>
        <span class="hy">会员专享抢购必中卷</span>

    </div>
    <span class="sj">有效期：2016年2月22日13；44；01</span>
    <div class="banner">
        <img class="banner_box" src="images/Bitmap.png">
        <span class="fl">福利大派送，水果大抢购，只要分享100%中奖率</span>
    </div>
    <div class="lxkf">
        <a href="#"><img class="dh" src="static/weixin/images/coupons_contact customer service.png"><span class="kf">联系客服</span></a>
    </div>
    <div class="lxkf" onclick="goActivityInfo()">
        <a href="#">活动详情</a>
    </div>
    <div class="lxkf">
        <a href="#">进入公众号</a>
    </div>
</section>
<footer class="loading">
    <div class="ending1">
        <input class="button5" type="button" onclick="toRegister()" value="立即加入"/>
    </div>
</footer>
<input type="hidden" id="fromUserId" value="${fromUserId}"/>
<input type="hidden" id="couponId" value="${couponId}"/>
</body>
</html>
<script type="text/javascript">
    function goActivityInfo() {
        window.location.href = "${contextPath}/weixin/activity/info?id=1";
    }

    function toRegister() {
        window.location.href = "${contextPath}/weixin/user/toLogin?fromUserId=" + $('#fromUserId').val() + "&couponId=" + $('#couponId').val();
    }
</script>