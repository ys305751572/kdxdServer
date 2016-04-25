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
    <title>抢购成功</title>
</head>
<body style="background: url(${contextPath}/static/weixin/images/general_bg.png);">
<header class="header">
    <p>恭喜您！抢购到如下商品</p>
</header>
<section class="section">
    <input type="hidden" id="pbrId" value="${pbr.id}"/>
    <input type="hidden" id="userId" value="${pbr.user.id}"/>
    <div class="list">
        <img src="${pbr.product.coverImage.path}" width="110px" height="110px"/>
        <form>
            <ul>
                <li><h2><span id="toutiao">${pbr.product.title}</span></h2></li>
                <li id="pt">&nbsp;抢购方式：<c:if test="${pbr.isUserCoupons == 1}">优惠卷</c:if> <c:if test="${pbr.isUserCoupons == 0}">普通</c:if></li>
            </ul>
        </form>
    </div>
    <c:if test="${pbr.isGetCoupons == 1}">
        <div class="bzq">
            <div class="bz">
                <span class="bz1">必中</span></div>
            <div class="qg">
                <form>
                    <ul>
                        <li><h2><span id="toutiao1">抢购必中卷</span></h2></li>
                        <li id="pt1">有效期：<date:date value="${coupon.endDate}" format="yyyy-MM-dd HH:mm:ss"></date:date></li>
                    </ul>
                </form>
            </div>
        </div>
    </c:if>
    <div class="list1" id="list1" onclick="addressList(${pbr.id},${pbr.user.id})">
        <span>配送地址：</span><input id="add1" type="text" style="border: none;" readonly class="add1" value="${address.address}">
    </div>
</section>
<section class="fukuan" id="fukuan">
    <span>必须支付抢购费用以后，您的订单才会生效</span>
</section>
<footer class="button">
    <div class="button_box" id="submitBtn">
        <span class="box"><a href="javascript:void(0)">请付款</a></span>
    </div>
</footer>
<script type="application/javascript">
    $(function () {
        var $address = $("#add1").val();
        if ($address == null || $address == "") {
            $.post("${contextPath}/weixin/user/findDefaultAddress",
                    function (result) {
                        if (result == null || result == '') {
                            $("#add1").css('color', 'red');
                            $("#add1").val("配送地址空缺，此单无法配送");
                        } else {
                            $("#add1").val(result);
                        }
                    }
            );
        }

        $("#submitBtn").click(function () {
            if ($address == null || $address == "" || $address == '配送地址空缺，此单无法配送') {
                $("#add1").val("配送地址空缺，此单无法配送");
                return;
            } else {
                toPay($('#pbrId').val(), $('#userId').val());
            }
        });
    });

    // 跳转到用户的收货地址界面
    function addressList(pbrId, userId) {
        window.location.href = "${contextPath}/weixin/address/list?pbrId=" + pbrId + "&userId=" + userId;
    }

    // 跳转到付款详情界面
    function toPay(pbrId, userId) {
        window.location.href = "${contextPath}/weixin/product/toPay?pbrId=" + pbrId;
    }
</script>
</body>
</html>
