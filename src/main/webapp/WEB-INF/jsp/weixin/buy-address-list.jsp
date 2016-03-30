<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/ss.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/awesome-bootstrap-checkbox.css"/>
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <script type="text/javascript"></script>
    <title>收货地址列表</title>
</head>
<body style="background: #f7f7f7">

<c:forEach items="${addressList}" var="address">
    <section class="shdz">
        <div class="address" onclick="goPbr(${address.id})">
            <address>
                <span class="lw">${address.name}&nbsp;&nbsp;${address.mobile}</span><br>
                <span class="wh">${address.address}</span>
            </address>
            <c:if test="${address.isDefault eq 0}">默认地址</c:if>
        </div>
    </section>
</c:forEach>
<input type="hidden" id="userId" value="${userId}"/>
<input type="hidden" id="pbrId" value="${pbrId}"/>
<script type="application/javascript">
    // 跳回抢购界面
    function goPbr(addressId) {
        window.location.href = "${contextPath}/weixin/product/toSnapUpResult?pbrId=" + $('#pbrId').val() + "&addressId=" + addressId;
    }
</script>
</body>
</html>
