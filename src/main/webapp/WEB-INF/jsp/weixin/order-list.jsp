<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/cc.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <title>我的订单列表</title>
</head>
<body style="background: #f7f7f7;overflow: auto;">
<input type="hidden" id="pageSize" value="10"/>
<input type="hidden" id="current" value="${current}"/>
<input type="hidden" id="totalPage" value="${totalPage}"/>

<c:if test="${null == orderList || orderList.size() == 0}">
    <span style="font-size: 2.0em;position: relative;top: 50px;left: 50px;color: #ff8400;">暂无订单记录</span>
</c:if>
<c:forEach var="n" items="${orderList}">
    <section class="section2">
        <div class="list">
            <div class="ddbh">
                <span class="ddbh1">订单编号：${n.sn}</span>
                <span class="ddbh2">状态：
                    <c:if test="${n.status == 0}"><span style="color: #ff8400">待付款</span></c:if>
                    <c:if test="${n.status == 1}"><span style="color: #ff8400">待发货</span></c:if>
                    <c:if test="${n.status == 2}"><span style="color: #ff8400">已发货</span></c:if>
                    <c:if test="${n.status == 3}"><span style="color: #ff8400">已签收</span></c:if>
                </span>
            </div>
            <div class="nr">
                <img src="${n.product.coverImage.path}" width="90px" height="90px" />
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
                <span class="sj1">配送服务从<date:date value="${n.serviceStartDate}" format="yyyy-MM-dd HH:mm:ss"></date:date>开始</span>
            </div>
        </div>
    </section>
</c:forEach>
<div class="fy1">
    <button class="btn3" value="上一页" onclick="prevPage()">上一页</button>
    <button class="btn4" value="下一页" onclick="nextPage()">下一页</button>
</div>
</body>
</html>
<script>
    $(function () {
        // 当数据只有一页时，隐藏翻页按钮
        var totalPage = $('#totalPage').val();
        if (Number(totalPage) <= Number(1)) {
            $('.fy1').remove();
        }
    });

    // 上一页
    function prevPage() {
        var current = $('#current').val();
        var totalPage = $('#totalPage').val();
        var pageSize = $('#pageSize').val();

        if (Number(current) <= Number(1)) {
            alert('已经是第一页了');
            return;
        }

        window.location.href = "${contextPath}/weixin/order/index?pageNum=" + (Number(current) - Number(1)) + "&pageSize=" + pageSize;
    }

    // 下一页
    function nextPage() {
        var current = $('#current').val();
        var totalPage = $('#totalPage').val();
        var pageSize = $('#pageSize').val();

        if (Number(current) >= Number(totalPage)) {
            alert('已经是最后一页了');
            return;
        }

        window.location.href = "${contextPath}/weixin/order/index?pageNum=" + (Number(current) + Number(1)) + "&pageSize=" + pageSize;
    }
</script>