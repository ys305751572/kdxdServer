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
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript"></script>
    <title>必中券列表</title>
</head>
<body>
<section class="section">
    <c:forEach var="n" items="${couponList}">
        <div class="bzq" onclick="getInfo(${n.id})">
            <div class="bz">
                <span class="bz1">必中</span></div>
            <div class="qg">
                <ul>
                    <li><h2><span id="toutiao1">抢购必中卷</span></h2></li>
                    <li id="pt1">有效期：<date:date value="${n.endDate}" format="yyyy-MM-dd HH:mm:ss"></date:date></li>
                </ul>
            </div>
            <c:if test="${n.status == 1}"><span class="gq1">过期</span></c:if>
            <c:if test="${n.status == 0}"><span class="gq1" style="background: #ff6600;color: #fff;">使用</span></c:if>
        </div>
    </c:forEach>
</section>
<section class="fukuan" id="fukuan" style="margin-top: 100px">
    <span>必须支付抢购费用以后，您的订单才会生效</span>
</section>
<div class="ending1" style="margin-top: -50px">
    <input class="button5" type="button" onclick="shareToFriend()" value="邀请好友" style="background-color: #00a642"/>
</div>
<script type="text/javascript">
    // 点击跳转到必中券详情界面
    function getInfo(id) {
        window.location.href = "weixin/coupons/detail?id=" + id;
    }
</script>
</body>
</html>
<script type="text/javascript">
    $(function(){
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: '', // 必填，公众号的唯一标识
            timestamp: new Date().getTime, // 必填，生成签名的时间戳
            nonceStr: '', // 必填，生成签名的随机串
            signature: '',// 必填，签名，见附录1
            jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
    });

    function shareToFriend(){
        wx.onMenuShareAppMessage({
            title: '踢踢科技', // 分享标题
            desc: '上的看法是代理费及', // 分享描述
            link: '${contextPath}/weixin/user/invite', // 分享链接
            imgUrl: '${wxUser.headImgUrl}', // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    }
</script>