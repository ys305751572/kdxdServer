<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="../inc/meta.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>支付测试2</title>
    <script type="text/javascript" src="${contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>

<div style="width: 100px;height: 100px;text-align: center" class="flex_box">
    <a id="toCommit" href="javascript:void(0)">提交</a>
</div>

</body>
<script type="text/javascript">

    $("#toCommit").click(function () {
        WeixinJSBridge.invoke('getBrandWCPayRequest', {
            "appId": "${appId}",
            "timeStamp": "${timeStamp}",
            "nonceStr": "${nonceStr}",
            "package": "${packageVal}",
            "signType": "${signType}",
            "paySign": "${paySign}"
        }, function (res) {
            alert("errorcode:" + res.err_code + "desc:" + res.err_desc + "msg:" + res.err_msg);
        });

    });
</script>
</html>