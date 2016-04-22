<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
    <script src="${contextPath}/static/js/jquery-1.11.0.js"></script>
    <script type="text/javascript"></script>
    <title>收货地址编辑</title>
</head>
<body style="background: #f7f7f7">
<section class="ff1">
    <form>
        <input type="hidden" id="id" name="id" value="${address.id}"/>
        <input type="hidden" id="pbrId" name="pbrId" value="${pbrId}"/>
        <div class="flex_box3">
            <input class="flex4" type="text" placeholder="收货人姓名" value="${address.name}" id="name" maxlength="50" name="user"/>
        </div>
        <div class="flex_box3">
            <input class="flex4" type="text" placeholder="收货人手机号" value="${address.mobile}" id="mobile" maxlength="20" name="tel"/>
        </div>
        <div class="flex_box3">
            <input class="flex4" type="text" placeholder="收货地址" value="${address.address}" id="address" maxlength="500" name="address"/>
        </div>
        <div class="ending1">
            <input class="button5" type="button" value="保存" style="background-color: #00a642" onclick="saveInfo()"/>
            <input class="button5" type="button" value="取消" style="background-color: #ff8400;margin-top: 1.2em;" onclick="goBack()"/>
        </div>
    </form>
</section>
</body>
</html>
<script type="text/javascript">
    // 返回列表界面
    function goBack() {
        window.location.href = '${contextPath}/weixin/address/list';
    }

    // 保存收货地址信息
    function saveInfo() {
        var flag = true;
        var name = $('#name').val();
        var mobile = $('#mobile').val();
        var address = $('#address').val();
        var pbrId = $('#pbrId').val();

        if (null == name || name == '') {
            alert('收货人姓名不能为空');
            flag = false;
            return;
        }

        if (null == mobile || mobile == '') {
            alert('收货人手机号不能为空');
            flag = false;
            return;
        }

        if (null == address || address == '') {
            alert('收货地址不能为空');
            flag = false;
            return;
        }

        if (flag) {
            $.post("${contextPath}/weixin/address/save", {
                addressId: $('#id').val(),
                name: name,
                mobile: mobile,
                address: address,
                pbrId: pbrId
            }, function (result) {
                if (result > 0) {
                    alert('操作成功');
                    if (null == pbrId || pbrId == '') {
                        window.location.href = "weixin/address/list?pbrId=" + pbrId;
                    } else {
                        window.location.href = "weixin/product/toSnapUpResult?pbrId=" + pbrId + "&addressId=" + result;
                    }
                } else {
                    alert('操作失败');
                }
            });
        }
    }
</script>