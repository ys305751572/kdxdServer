<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../inc/taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/dd.css"><title>抢购缴费</title>
</head>
<header class="header">
	<img src="${contextPath}/static/weixin/images/coupons_logo_icon.png" class="logo" id="logo"><span class="caption">抢购缴费</span>
</header>
<section class="header" >
	<div class="logo">
		<span>我的有效期</span>
		<span class="monney"><span style="color: #909090">¥</span>10000</span>
	</div>
	<div class="button">
		<button class="button2">一周</button>
		<button class="button1">两周</button>
	</div>
</section>
<footer class="footer">
	<div class="shijian">
		<div class="start">
			<div class="time">
				<div class="year">2016</div>
				<div class="month">02-12</div>
			</div>		
		</div>
		<div class="ending">
			<div class="time">
				<div class="year">2016</div>
				<div class="month">03-08</div>
			</div>
		</div>
		<div class="footer_box">
		<span class="start1">开始</span>
		<span class="ending2">结束</span>
		</div>
	</div>
	<div class="ending3">
				<input class="button5" type="submit" value="立即支付" style="background:#00a642;" ></input>
		</div>
</footer>
<body>
</body>
</html>
