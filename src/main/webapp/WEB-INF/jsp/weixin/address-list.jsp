<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/sss.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/awesome-bootstrap-checkbox.css"/>
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <script type="text/javascript"></script>
    <title>收货地址列表</title>
</head>
<body style="background: #f7f7f7">

<c:forEach items="${addressList}" var="address">
    <section class="shdz">
        <div class="address">
            <address>
                <span class="lw">${address.name}&nbsp;&nbsp;${address.mobile}</span><br>
                <span class="wh">${address.address}</span>
            </address>
        </div>
        <div class="mr">
            <div class="radio">
                <input type="radio" id="radio${address.id}" name="radio2"
                       <c:if test="${address.isDefault eq 0}">checked</c:if> />
                <label for="radio${address.id}">
                    设为默认地址
                </label>
            </div>
            <div class="write" id="write1">
                <a href="javascript:void(0)" onclick="updateInfo(${address.id})"><img src="${contextPath}/static/weixin/images/1182053.png"></a>
            </div>
        </div>
    </section>
</c:forEach>
<footer class="footer">
    <div class="btn2">
        <input type="hidden" id="tempId"/>
        <input type="button" class="button5" id="btn1" name="address" onclick="controlDIV()" value="编辑"/>
        <input type="button" class="button5" id="btn2" name="address" onclick="updateAddress()" value="添加" style="background:#ff8400"/>
    </div>
</footer>
<script type="application/javascript">
    var flag = 0;

    $(function () {
        $("input[name = 'radio2']").click(function () {
            // 点击单选按钮，将该地址设置为默认地址
            $.post("${contextPath}/weixin/address/addressDefault", {
                addressId: $(this).prop('id').replace('radio', '')
            }, null);
        });
    });

    // 添加 or 编辑收货地址
    function updateAddress() {
        window.location.href = "weixin/address/update?id=" + $('#tempId').val();
    }

    // 测试编辑收货地址
    function updateInfo(id) {
        window.location.href = "weixin/address/update?id=" + id;
    }

    function controlDIV() {
        if (flag == 0) {
            $("write").style.display = "block";
            $("write1").style.display = "block";
            flag = 1;
        }
        else {
            $("write").style.display = "none";
            $("write1").style.display = "none";
            flag = 0;
        }
    }
</script>
</body>
</html>
