<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <script type="text/javascript"></script>
    <title>必中券列表</title>
</head>
<body>
<section class="section">
    <c:forEach var="n" items="${couponList}">
        <div class="bzq" onclick="getInfo(${n.id})">
            <div class="bz">
                <span class="bz1">必中</span></div>
            <div class="qg">
                <ul>
                    <li><h2><span id="toutiao1">抢购必中卷</span></h2></li>
                    <li id="pt1">有效期：${n.endDate}</li>
                </ul>
            </div>
            <c:if test="${n.status == 1}"><span class="gq1">过期</span></c:if>
            <c:if test="${n.status == 0}"><span class="gq1" style="background: #ff6600;color: #fff;">使用</span></c:if>
        </div>
    </c:forEach>
</section>
<section class="fukuan" id="fukuan" style="margin-top: 100px">
    <span>必须支付抢购费用以后，您的订单才会生效</span>
</section>
<div class="ending1" style="margin-top: -50px">
    <input class="button5" type="submit" value="邀请好友" style="background-color: #00a642"/>
</div>
<script type="text/javascript">
    // 点击跳转到必中券详情界面
    function getInfo(id) {
        window.location.href = "weixin/coupon/detail?id=" + id;
    }
</script>
</body>
</html>
