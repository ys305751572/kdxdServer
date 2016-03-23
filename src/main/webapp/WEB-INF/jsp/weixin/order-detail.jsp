<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../inc/taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
<script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
<title><c:if test="${success eq true}">抢购成功</c:if><c:if test="${success eq false}">抢购失败</c:if></title>
</head>
<body>
<header class="header">
	<p><c:if test="${success eq true}">恭喜您！抢购到如下商品</c:if><c:if test="${success eq false}">手气不太好，没抢到，再来一次</c:if></p>
</header>
<section class="section">
	<div class="list">
	<img src="${contextPath}/static/weixin/images/抢购成功.png">
		<form>
			<ul>
				<li><h2><span id="toutiao">${product.title}</span></h2></li>
				<li id="pt">&nbsp;抢购方式：<c:if test="${isUsed eq true}">优惠卷</c:if> <c:if test="${isUsed eq false}">普通</c:if></li>
			</ul>
		</form>
	</div>
	<c:if test="${isGetCoupon eq true}">
		<div class="bzq">
			<div class="bz">
				<span class="bz1">必中</span></div>
			<div class="qg">
				<form>
					<ul>
						<li><h2><span id="toutiao1">抢购必中卷</span></h2></li>
						<li id="pt1">有效期：<date:date value="${endDate}" format="yyyy-MM-dd HH:mm:ss"></date:date></li>
					</ul>
				</form>
			</div>
		</div>
	</c:if>
	<div class="list1" id="list1">
	<span>配送地址：</span><input id="add1" type="text" style="border: none;" class="add1" value="${address.address}">
	</div>
</section>
<section class="fukuan" id="fukuan">
	<span>必须支付抢购费用以后，您的订单才会生效</span>
</section>
<footer class="button">
		<div class="button_box" >
			<span class="box"><a href="#" id="submitBtn" onclick="changeStyle();">请付款</a></span>
		</div>
</footer>
<script type="application/javascript">
	$(function() {

		var $address = $("#add1").val();
		if($address == null || $address == "") {
			$.post(
				"${contextPath}/weixin/user/findDefaultAddress",
				function(result) {
					if(result == null) {
						$("#add1").val("配送地址空缺，此单无法配送");
					}
					else {
						$("#add1").val(result.address);
					}
				}
			);
		}

		$("#submit").click(function() {
			if($address == null || $address == "") {
				$("#add1").val("配送地址空缺，此单无法配送");
				return;
			}
		});
	});
</script>
</body>
</html>
