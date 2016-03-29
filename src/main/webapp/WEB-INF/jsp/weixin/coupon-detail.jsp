<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/ss.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <script type="text/javascript"></script>
    <title>必中券详情</title>
</head>
<body>
<section class="content" id="content">
    <div class="all">
        <div class="bzj">
            <img class="bzj3" src="${contextPath}/static/weixin/images/Rectangle 228.png">
            <span class="bzj1">必中卷</span><br>
            <span class="bzj2">会员专享抢购必中卷</span>
        </div>
        <div class="sy">
            <img class="sy1" src="${contextPath}/static/weixin/images/coupons_logo_icon.png">
        </div>
        <div class="yxq">
            <span class="yxq1">有效期</span>
            <time class="yxq2">${coupon.endDate}</time>
        </div>
    </div>
    <div class="banner">
        <img class="banner_box" src="${contextPath}/static/weixin/images/Bitmap.png">
        <span class="fl">福利大派送，水果大抢购，只要分享100%中奖率</span>
    </div>
    <div class="lxkf">
        <a href="#"><img class="dh" src="${contextPath}/static/weixin/images/coupons_contact customer service.png"><span class="kf">联系客服</span></a>
    </div>
</section>
<footer class="loading" id="loading">
    <div class="ending1" id="ending1">
        <input class="button5" type="button" value="立即使用" style="background: #ff8400"/>
        <input class="button5" id="btn1" type="button" value="转增朋友"/>
    </div>
</footer>
</body>
</html>