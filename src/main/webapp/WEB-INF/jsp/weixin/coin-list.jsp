<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/dd.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <title>我的充值记录</title>
</head>
<body style="max-height: 855px">
<input type="hidden" id="pageSize" value="10"/>
<input type="hidden" id="current" value="${current}"/>
<input type="hidden" id="totalPage" value="${totalPage}"/>
<c:if test="${null == coinList || coinList.size() == 0}">
    <span class="cz">暂无充值记录</span>
</c:if>
<c:forEach var="n" items="${coinList}">
    <div class="list">
        <div class="list1">
            <span class="cz">充值金额</span><span class="sz">${n.money}</span>
            <br>
            <time class="time1"><date:date value="${n.createDate}" format="yyyy-MM-dd HH:mm:ss"></date:date></time>
        </div>
    </div>
</c:forEach>
<div class="fy">
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
            $('.fy').remove();
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

        window.location.href = "${contextPath}/weixin/coinlog/list?pageNum=" + (Number(current) - Number(1)) + "&pageSize=" + pageSize;
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

        window.location.href = "${contextPath}/weixin/coinlog/list?pageNum=" + (Number(current) + Number(1)) + "&pageSize=" + pageSize;
    }
</script>