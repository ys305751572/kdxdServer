<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="../inc/taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/dd.css">
<title>活动说明</title>
</head>
<body>
<header class="header">
	<div class="all">
		<div class="note"></div>
		<div class="header_box" id="header_box">
			<h3>
				${info.title}
			</h3>
			<h5><date:date value="${info.createDate}" format="yyyy-MM-dd HH:mm:ss"></date:date> 踢踢同城</h5>
		</div>
	</div>
</header>
<section>
	<div class="section">
		${info.content}
	</div>
</section>
</body>
</html>