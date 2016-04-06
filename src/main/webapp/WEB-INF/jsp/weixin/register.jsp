<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../inc/taglibs.jsp" %>
<meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
<script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
<title>注册页面</title>

<script type="text/javascript"> 
var countdown=60; 
function settime(btn) { 
	var tg = document.getElementById("tg1");
	if (countdown == 0) { 
	btn.removeAttribute("disabled");    
	btn.value="点击发送验证码"; 
	countdown = 60; 
	btn.className="btn_sendcode1";
		tg.className="register1";
	} else { 
	countdown--; 
	btn.setAttribute("disabled", true); 
	btn.value=countdown +"秒后重新发送"; 
	btn.className="btn_sendcode2";
		tg.className="register2";
		setTimeout(function() { 
		settime(btn) 
	},1000)
	
	} 
	
} 
</script>
</head>
<body style="background: #f7f7f7">
<section class="ff1" >
		<form action="${contextPath}/weixin/login/toPassword" method="post">
			<div class="flex_box3">
				<input class="flex4" type="text" placeholder="请输入手机号" id="username"  name="username" />
			</div>
			<div  class="flex_box3" style="border: none;">
				<input class="flex4" id="yzm" name="yzm" type="text" placeholder="请输入验证码" />
				<input type="button" class="btn_sendcode1" onclick="settime(this);" value="点击发送验证码" />
			</div>
			<div class="loading">
				<input class="register1" id="tg1" type="submit" value="注&nbsp;册" />
			</div>
		</form>
	</section>
</body>
</html>
