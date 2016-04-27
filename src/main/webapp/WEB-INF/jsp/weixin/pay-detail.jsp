<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/dd.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <title>抢购缴费</title>
</head>
<body>
<input type="hidden" id="userId" value="${kUser.id}"/>
<div style="background: rgba(0,0,0,0.5);width: 100%;height: 100%;position: absolute;top: 0;left: 0;z-index: 10;display: none;" id="write" onclick="hideModal()">
</div>
<div id="write1" style="background: #fff;position: absolute;top: 30%;left: 50%;transform: translate(-50%);width: 80%;z-index: 11;display: none;">
    <div style="width: 90%;margin: 0 auto;">
        <span style="display: block;width: 100%;height: 100px;line-height: 100px;font-size: 1.4em;border-bottom: 1px solid #8e8e8e; color: #ff8400;">请选择支付方式</span>
        <a href="javascript:void(0)" onclick="changeMoney()" style="text-decoration: none; color: #a7a7a7;">
            <div style="display: block;width: 100%;height: 100px;line-height: 100px;font-size: 1.2em;border-bottom: 1px solid #8e8e8e;">账户余额支付：${kUser.money}元</div>
        </a>
        <a href="javascript:void(0)" onclick="subForm()" style="text-decoration: none; color: #a7a7a7;">
            <div style="display: block;width: 100%;height: 100px;line-height: 100px;font-size: 1.2em;">微信支付</div>
        </a>

    </div>
</div>
<header class="header">
    <img src="${contextPath}/static/weixin/images/coupons_logo_icon.png" class="logo" id="logo"><span class="caption">抢购缴费</span>
</header>
<section class="header">
    <div class="logo">
        <span>我的有效期</span>
        <c:if test="${productService.id != null}">
            <span class="monney">服务价格：<span style="color: #909090">¥</span>${productService.money}</span>
        </c:if>
    </div>
    <div class="button">
        <c:if test="${null == productServiceList || productServiceList.size() == 0}"><span style="color: #909090">暂无</span></c:if>
        <c:forEach var="n" items="${productServiceList}">
            <input type="hidden" value="${n.id}"/>
            <c:if test="${n.id == productService.id}">
                <button class="button1" onclick="getInfo(this)">${n.days}天</button>
            </c:if>
            <c:if test="${n.id != productService.id}">
                <button class="button2" onclick="getInfo(this)">${n.days}天</button>
            </c:if>
        </c:forEach>
    </div>
</section>
<footer class="footer">
    <div class="shijian">
        <div class="start">
            <div class="time">
                <div class="year">${productService.startYear}</div>
                <div class="month">${productService.startDate}</div>
            </div>
        </div>
        <div class="ending">
            <div class="time">
                <div class="year">${productService.endYear}</div>
                <div class="month">${productService.endDate}</div>
            </div>
        </div>
        <div class="footer_box">
            <span class="start1">开始</span>
            <span class="ending2">结束</span>
        </div>
    </div>
    <div class="ending3">
        <input type="hidden" id="productServiceId" value="${productService.id}"/>
        <input type="hidden" id="pbrId" value="${pbrId}"/>
        <input class="button5" type="button" value="立即支付" onclick="showModal()" style="background:#00a642;"/>
    </div>
</footer>
</body>
</html>
<script type="application/javascript">
    // 加载商品服务信息
    function getInfo(self) {
        var productServiceId = $(self).prev().val();
        var pbrId = $('#pbrId').val();
        window.location.href = "${contextPath}/weixin/product/getInfo?pbrId=" + pbrId + "&productServiceId=" + productServiceId;
    }

    function showModal() {
        $("#write1").fadeIn("slow");
        $("#write").fadeIn("slow");
    }

    function hideModal() {
        $("#write").fadeOut();
        $("#write1").fadeOut();
    }

    // 余额支付
    function changeMoney() {
        var userId = $('#userId').val();

        var productServiceId = $('#productServiceId').val();
        var pbrId = $('#pbrId').val();
        $.post("${contextPath}/weixin/product/createOrder", {
            productServiceId: productServiceId,
            pbrId: pbrId
        }, function (data) {
            if (data != null) {
                $.post("${contextPath}/weixin/order/payOrderPlus", {
                    orderId: data,
                    userId: userId
                }, function (result) {
                    if (result == 1) {
                        alert("操作成功");
                        window.location.href = "${contextPath}/weixin/order/index?pageNum=1&pageSize=10";
                    } else if (result == -1) {
                        alert("用户余额不足，请充值");
                    } else {
                        alert("操作失败");
                    }
                });
            } else {
                alert("操作失败");
            }
        });
    }

    // 生成订单并支付
    function subForm() {
        var productServiceId = $('#productServiceId').val();
        var pbrId = $('#pbrId').val();
        $.post("${contextPath}/weixin/product/createOrder", {
            productServiceId: productServiceId,
            pbrId: pbrId
        }, function (data) {
            if (data != null) {
                // 调用微信浏览器内置功能实现微信支付
                $.ajax({
                    method: "POST",
                    url: "weixin/pay/goPay",
                    dataType: "html",
                    data: {orderId: data},
                    success: function (result) {
                        var obj = eval('(' + result + ')');
                        WeixinJSBridge.invoke('getBrandWCPayRequest', {
                            "appId": obj.appId,                  //公众号名称，由商户传入
                            "timeStamp": obj.timeStamp,          //时间戳，自 1970 年以来的秒数
                            "nonceStr": obj.nonceStr,         //随机串
                            "package": obj.package,           //商品包信息
                            "signType": obj.signType,        //微信签名方式:
                            "paySign": obj.paySign           //微信签名
                        }, function (res) {
                            window.location.href = "${contextPath}/weixin/order/index?pageNum=1&pageSize=10";
                        });
                    }
                })
            } else {
                alert("操作失败");
            }
        });
    }
</script>