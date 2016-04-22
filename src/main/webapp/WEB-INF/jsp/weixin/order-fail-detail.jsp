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
    <title>抢购失败</title>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            $("#btn1").click(function () {
                $("#share").fadeToggle(1000);
            });
        });
    </script>
</head>
<body style="background: url(${contextPath}/static/weixin/images/general_bg.png);">
<div class="share" id="share">
    <p class="share_word">点击右上角菜单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>分享发送给您的好友</p>
    <img src="${contextPath}/static/weixin/images/箭头.png">
</div>
<input type="hidden" id="timestamp" value="${timestamp}"/>
<input type="hidden" id="noncestr" value="${noncestr}"/>
<input type="hidden" id="signature" value="${signature}"/>
<input type="hidden" id="userId" value="${user.id}"/>
<input type="hidden" id="activityTitle" value="${activity.title}"/>
<input type="hidden" id="activityContent" value="${activity.content}"/>
<input type="hidden" id="activityPath" value="${activity.image.path}"/>
<input type="hidden" id="wxUserHead" value="${wxUser.headImgUrl}"/>
<input type="hidden" id="wxUserName" value="${wxUser.nickname}"/>
<header class="header">
    <p>手气不太好，没抢到，再来一次</p>
</header>
<section class="section">
    <div class="list">
        <input type="hidden" id="productId" value="${pbr.product.id}"/>
        <img src="${pbr.product.coverImage.path}" width="110px" height="110px" />
        <form>
            <ul>
                <li><h2><span id="toutiao">${pbr.product.title}</span></h2></li>
                <li id="pt">&nbsp;抢购方式：<c:if test="${pbr.isUserCoupons == 1}">优惠卷</c:if> <c:if test="${pbr.isUserCoupons == 0}">普通</c:if></li>
            </ul>
        </form>
    </div>
    <div class="list2">
        <a href="javascript:void(0)" onclick="toPay()" class="list2_link">
            <div class="list2_link">
                <span>继续抢购</span><img src="${contextPath}/static/weixin/images/Rectangle 8.png"/>
            </div>
        </a>
        <a href="weixin/order/index" class="list2_link">
            <div class="list1">
                <span>查看订单</span><img src="${contextPath}/static/weixin/images/Rectangle 8.png"/>
            </div>
        </a>
        <a href="javascript:void(0)" class="list2_link">
            <div class="list1" id="btn1">
                <span>邀请好友获得必中卷</span><img src="${contextPath}/static/weixin/images/Rectangle 8.png"/>
            </div>
        </a>
    </div>
</section>
<div class="place" style="height: 100px;width:100%;"></div>
</body>
</html>
<script type="text/javascript">
    /*
     * 注意：
     * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
     * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
     * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
     *
     * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
     * 邮箱地址：weixin-open@qq.com
     * 邮件主题：【微信JS-SDK反馈】具体问题
     * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
     */
    wx.config({
        debug: false,
        appId: 'wxc0dd28b467224a05',
        timestamp: $('#timestamp').val(),
        nonceStr: $('#noncestr').val(),
        signature: $('#signature').val(),
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
            'hideOptionMenu',
            'showOptionMenu'
        ]
    });

    wx.error(function (res) {
        alert(res.errMsg);
    });

    wx.ready(function () {
        wx.onMenuShareAppMessage({
            title: '踢踢送福利啦', // 分享标题
            desc: $('#activityTitle').val(), // 分享描述
            link: 'http://qq.tt/kdxgServer/weixin/user/invite?userId=' + $('#userId').val() + "&wxUserHead=" + $('#wxUserHead').val() + "&wxUserName=" + $('#wxUserName').val(), // 分享链接
            imgUrl: $('#activityPath').val(), // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    });

    // 继续抢购
    function toPay() {
        window.location.href = "${contextPath}/weixin/product/detail?id=" + $('#productId').val();
    }
</script>