<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/ss.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/awesome-bootstrap-checkbox.css"/>

    <script type="text/javascript" src="${contextPath}/static/weixin/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${contextPath}/static/weixin/js/koala.min.1.5.js"></script>

    <script type="text/javascript"></script>
    <title>抢购界面</title>
</head>
<body>
<header class="header_img">
    <div class="img1"><img src="${contextPath}/static/weixin/images/Oval 12 Copy 3.png"></div>
    <div class="line"><img src="${contextPath}/static/weixin/images/Line.png"></div>
    <div class="img2"><img src="${contextPath}/static/weixin/images/Oval 12 Copy 3.png">
    </div>
    <div class="line1"><img src="${contextPath}/static/weixin/images/Line Copy.png"></div>
    <div class="img3"><img src="${contextPath}/static/weixin/images/Oval 12 Copy 3.png">
    </div>
    <nav class="nav">
        <span class="nav1">抢购</span>
        <span class="nav2">选择送货地址</span>
        <span class="nav3">配送签收</span>
    </nav>
</header>
<section class="section1">


    <!-- 代码 开始 -->
    <div id="fsD1" class="focus">
        <div id="D1pic1" class="fPic">
            <div class="fcon" style="display: none;">
                <a target="_blank" href="#"><img src="${contextPath}/static/weixin/images/Bitmap.png" style="opacity: 1; "></a>
                <span class="shadow"><a target="_blank" href="#"></a></span>
            </div>

            <div class="fcon" style="display: none;">
                <a target="_blank" href="#"><img src="${contextPath}/static/weixin/images/Bitmap.png" style="opacity: 1; "></a>
                <span class="shadow"><a target="_blank" href="#"></a></span>
            </div>

            <div class="fcon" style="display: none;">
                <a target="_blank" href="#"><img src="${contextPath}/static/weixin/images/Bitmap.png" style="opacity: 1; "></a>
                <span class="shadow"><a target="_blank" href="#"></a></span>
            </div>

            <div class="fcon" style="display: none;">
                <a target="_blank" href="#"><img src="${contextPath}/static/weixin/images/Bitmap.png" style="opacity: 1; "></a>
                <span class="shadow"></span>
            </div>
        </div>
        <div class="fbg">
            <div class="D1fBt" id="D1fBt">
                <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>1</i></a>
                <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>2</i></a>
                <a href="javascript:void(0)" hidefocus="true" target="_self" class="current"><i>3</i></a>
                <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>4</i></a>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        Qfast.add('widgets', {path: "${contextPath}/static/weixin/js/terminator2.2.min.js", type: "js", requires: ['fx']});
        Qfast(false, 'widgets', function () {
            K.tabs({
                id: 'fsD1',   //焦点图包裹id
                conId: "D1pic1",  //** 大图域包裹id
                tabId: "D1fBt",
                tabTn: "a",
                conCn: '.fcon', //** 大图域配置class
                auto: 0,   //自动播放 1或0
                effect: 'fade',   //效果配置
                eType: 'click', //** 鼠标事件
                pageBt: true,//是否有按钮切换页码
                bns: ['.prev', '.next'],//** 前后按钮配置class
                interval: 3000  //** 停顿时间
            })
        })
    </script>
    <!-- 代码 结束 -->
</section>
<section class="content">
    <!-- 此处存放抢购商品id -->
    <input type="hidden" id="productId"/>
    <div class="topbar">
        <h3>【脐橙五斤大抢购酸甜多汁】湖北南漳脐橙</h3>
    </div>
    <div class="neirong">
        <p>脐橙五斤大抢购酸甜多汁】湖北南漳脐橙【脐橙五斤大抢购酸甜多汁】湖北南漳脐橙【脐橙五斤大抢购酸甜多汁】湖北南漳脐橙【脐橙五斤大抢购酸甜多汁】湖北南漳脐橙</p>
    </div>
    <aside class="aside1">
        <span id="aside1">&nbsp;已抢中：100</span>
    </aside>
    <div class="engding">
        <button class="button3" onclick="rushToBuy.fn.goBuy(true)" style="background-color: #00a642">
            必中卷抢购（3）
        </button>
        <button class="button2" onclick="rushToBuy.fn.goBuy(false)" style="background-color: #ff8400">
            直接抢购
        </button>
    </div>
    <div class="check">
        <a href="#" id="check">查看活动详情<img src="${contextPath}/static/weixin/images/Group 6.png"></a>
    </div>

</section>
</body>
<script type="application/javascript">
    var rushToBuy = {
        v: {},
        fn: {
            init: function () {

            },
            goBuy: function (status) {
                // 直接购买
                var productId = $('#productId').val();
                $.post("weixin/product/snapUp", {
                    id: productId,
                    isUsed: status
                }, function (result) {
                    if (null != result) {
                        // 如果返回结果不为空，代表抢购成功，此时跳转到抢购结果界面
                        window.location.href = "${contextPath}/weixin/product/toSnapUpResult?pbrId=" + result.id
                    } else {
                        $bluemobi.notify("抢购失败", "error");
                    }
                });
            }
        }
    }

    $(function () {
        rushToBuy.fn.init();
    })
</script>
</html>
