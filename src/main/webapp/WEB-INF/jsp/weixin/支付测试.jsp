<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="../inc/meta.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>支付测试</title>
    <script type="text/javascript" src="${contextPath}/kdxgServer/static/js/jquery-1.11.0.js"></script>
</head>
<body>

<div style="width: 100px;height: 100px;text-align: center" class="flex_box">
    <a id="toCommit" href="javascript:void(0)">提交</a>
</div>
</body>
<script type="text/javascript">
    $("#toCommit").click(function () {
        $.ajax({
            method: "POST",
            url: "${contextPath}/kdxgServer/weixin/pay/go",
            dataType: "html",
            data: {orderId: new Date().getTime()},
            success: function (result) {
                var obj = eval('(' + result + ')');
                alert("appId:" + obj.appId);
                WeixinJSBridge.invoke('getBrandWCPayRequest', {
                    "appId": obj.appId,                  //公众号名称，由商户传入
                    "timeStamp": obj.timeStamp,          //时间戳，自 1970 年以来的秒数
                    "nonceStr": obj.nonceStr,         //随机串
                    "package": obj.package,      //<span style="font-family:微软雅黑;">商品包信息</span>
                    "signType": obj.signType,        //微信签名方式:
                    "paySign": obj.paySign           //微信签名
                }, function (res) {
                    alert("errorcode:" + res.err_code + "desc:" + res.err_desc + "msg:" + res.err_msg);
                });
            }
        })
    });
</script>
</html>
