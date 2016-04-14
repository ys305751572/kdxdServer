<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/dd.css"/>
    <title>个人资料</title>
</head>
<body>
<section>
    <div class="list" id="qb">
        <input type="hidden" id="userId" value="${user.id}"/>
        <span class="cz">我的钱包</span><span class="sz1">¥<span style="color:#ff911a ">${user.money}</span></span>

    </div>
    <form>
        <input class="je" id="money" type="text" placeholder="请输入确认金额"/>
        <div class="ending1" onclick="rechargeMoney()">
            <input class="btn" type="button" onclick="rechargeMoney()" value="立即充值"/>
        </div>
    </form>
</section>
<footer class="check">
    <a href="weixin/coinlog/list" style="color:#ff911a ">查看充值记录></a>
</footer>
</body>
</html>
<script>
    // 充值操作
    function rechargeMoney() {
        $.post("${contextPath}/weixin/coinlog/getRechargeOrderNo", {
                    "money": $("#money").val()
                },
                function (result) {
                    if (result.status == 0) {
                        $.ajax({
                            method: "POST",
                            url: "${contextPath}/kdxgServer/weixin/pay/recharge",
                            dataType: "html",
                            data: {orderId: result.data},
                            success: function (result1) {
                                var obj = eval('(' + result1 + ')');
                                WeixinJSBridge.invoke('getBrandWCPayRequest', {
                                    "appId": obj.appId,                  //公众号名称，由商户传入
                                    "timeStamp": obj.timeStamp,          //时间戳，自 1970 年以来的秒数
                                    "nonceStr": obj.nonceStr,         //随机串
                                    "package": obj.package,          //商品包信息
                                    "signType": obj.signType,        //微信签名方式:
                                    "paySign": obj.paySign           //微信签名
                                }, function (res) {
                                    window.location.href = '${contextPath}/weixin/user/index';
                                });
                            }
                        });
                    } else {
                        alert(result.msg);
                    }
                });
    }
</script>