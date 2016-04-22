<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <title>抢购失败</title>
</head>
<body style="background: url(${contextPath}/static/weixin/images/general_bg.png);">
<header class="header">
    <p>手气不太好，没抢到，再来一次</p>
</header>
<section class="section">
    <div class="list">
        <input type="hidden" id="productId" value="${pbr.product.id}"/>
        <img src="${pbr.product.coverImage.path}" width="110px" height="110px" />
        <form>
            <ul>
                <li><h2><span id="toutiao">${pbr.product.title}</span></h2></li>
                <li id="pt">&nbsp;抢购方式：<c:if test="${pbr.isUserCoupons == 1}">优惠卷</c:if> <c:if test="${pbr.isUserCoupons == 0}">普通</c:if></li>
            </ul>
        </form>
    </div>
    <div class="list2">
        <a href="javascript:void(0)" onclick="toPay()" class="list2_link">
            <div class="list1">
                <span>继续抢购</span><img class="list1_img" src="${contextPath}/static/weixin/images/Rectangle 8.png"/>
            </div>
        </a>
        <a href="weixin/order/index" class="list2_link">
            <div class="list1">
                <span>查看订单</span><img class="list1_img" src="${contextPath}/static/weixin/images/Rectangle 8.png"/>
            </div>
        </a>
        <a href="weixin/coupons/list" class="list2_link">
            <div class="list1">
                <span>邀请好友获得必中卷</span><img class="list1_img" src="${contextPath}/static/weixin/images/Rectangle 8.png"/>
            </div>
        </a>
    </div>
</section>
<div class="place" style="height: 100px;width:100%;"></div>
</body>
</html>
<script type="text/javascript">
    // 继续抢购
    function toPay() {
        window.location.href = "${contextPath}/weixin/product/detail?id=" + $('#productId').val();
    }
</script>