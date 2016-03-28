<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/ccc.css">
    <title>我的订单列表</title>
</head>
<body style="background: #f7f7f7;overflow: auto;">
<section class="section2">
    <div class="list">
        <c:forEach var="n" items="${orderList}">
            <div class="ddbh">
                <span class="ddbh1">订单编号：${n.sn}</span>
                <span class="ddbh2">状态：
                    <c:if test="${n.status == 0}"><span style="color: #ff8400">待发货</span></c:if>
                    <c:if test="${n.status == 1}"><span style="color: #ff8400">已发货</span></c:if>
                    <c:if test="${n.status == 2}"><span style="color: #ff8400">已签收</span></c:if>
                </span>
            </div>
            <div class="nr">
                <span class="nr2"><h3>${n.product.title}</h3></span>
                <address class="nr3">
                    <ul>
                        <li>${n.name}</li>
                        <li>${n.mobile}</li>
                        <li>${n.address}</li>
                    </ul>
                </address>
            </div>
            <div class="sj">
                <span class="sj1"><date:date value="${n.createDate}" format="yyyy-MM-dd HH:mm:ss"></date:date></span>
            </div>
        </c:forEach>
    </div>
</section>
<div class="fy1">
    <button class="btn3" value="上一页" disabled="disabled">上一页</button>
    <button class="btn4" value="下一页">下一页</button>
</div>
</body>
</html>
