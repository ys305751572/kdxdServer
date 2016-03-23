<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="css/ss.css">
<link rel="stylesheet" type="text/css" href="css/awesome-bootstrap-checkbox.css"/>  
<script type="text/javascript"></script>
<title>送货地址记录</title>
</head>
<body style="background: #f7f7f7">

<c:forEach items="${list}" var="address">
    <section class="shdz">
        <div class="address">
    <address>
        <span class="lw">${address.name}&nbsp;&nbsp;${address.mobile}</span><br>
        <span class="wh">${address.address}</span>
    </address>
    </div>
    <div class="mr">
        <div class="radio">
            <input type="radio" id="radio${address.id}" name="radio2" <c:if test="${address.isDefault eq 0}">checked</c:if> />
            <label for="radio${address.id}">
                设为默认地址
            </label>
        </div>
    </div>
    </section>
</c:forEach>
<footer class="footer">
<div class="btn2">
   <input type="button" class="button5" id="btn1" name="address" value="编辑" />
   <input type="button" class="button5" id="btn2" name="address" value="添加" style="background:#ff8400"/>
</div>
</footer>
<script type="application/javascript">
    $(function() {
        $("input[name = 'radio2']").click(function() {
            history.go(-1);
        });
    });
</script>
</body>
</html>
