<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/tags" prefix="date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../inc/taglibs.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/tt.css">
    <meta name="viewport"
          content="width=device-width,initial-scale=0.5, minimum-scale=0.5, maximum-scale=0.5, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/weixin/css/lrtk.css"/>
    <script type="text/javascript" src="${contextPath}/static/weixin/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${contextPath}/static/weixin/js/koala.min.1.5.js"></script>
    <title>抢购界面</title>
</head>
<body style="background: url(${contextPath}/static/weixin/images/general_bg.png);">
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
<input type="hidden" id="userId" value="${kUser.id}"/>
<section class="section1">
    <!-- 代码 开始 -->
    <div id="fsD1" class="focus">
        <div id="D1pic1" class="fPic">
            <c:forEach var="n" items="${product.list}">
                <div class="fcon" style="display: none;">
                    <a target="_blank" href="javascript:void(0)"><img src="${n.path}" style="opacity: 1; "></a>
                    <span class="shadow"><a target="_blank" href="javascript:void(0)"></a></span>
                </div>
            </c:forEach>

        </div>
        <div class="fbg">
            <div class="D1fBt" id="D1fBt">
                <c:forEach var="n" items="${product.list}" varStatus="index">
                    <a href="javascript:void(0)" hidefocus="true" target="_self"><i>${index}</i></a>
                </c:forEach>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        Qfast.add('widgets', {
            path: "${contextPath}/static/weixin/js/terminator2.2.min.js",
            type: "js",
            requires: ['fx']
        });
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
    <div class="topbar">
        <h2 style="font-size: 40px;padding-top: 0px;">${product.title}</h2>
    </div>
    <div class="neirong" style="font-size: 30px;">
        ${product.content}
    </div>
    <span style="color: red;margin-left: 5px;">配送服务开始时间：<date:date value="${product.serviceStartDate}" format="yyyy-MM-dd HH:mm:ss"></date:date></span>
    <aside class="aside1">
        <span id="aside1">&nbsp;已抢中：${buyCount}</span>
    </aside>
    <br/>
    <div>
        <%--<input type="hidden" id="id" name="id" value="${product.id}">--%>
        <input type="hidden" id="id" name="id" value="${product.id}">
        <c:if test="${counts > 0}">
            <c:if test="${buyCount < product.counts}">
                <button class="button3" onclick="rushToBuy.fn.goBuy(true)" style="background-color: #00a642">
                    必中卷抢购 (${counts})
                </button>
            </c:if>
        </c:if>
        <c:if test="${counts <= 0}">
            <button class="button3">
                没有必中卷
            </button>
        </c:if>
        <c:if test="${buyCount < product.counts}">
            <button class="button2" onclick="rushToBuy.fn.goBuy(false)" style="background-color: #ff8400">
                直接抢购
            </button>
        </c:if>
        <c:if test="${buyCount >= product.counts}">
            <button class="button2">
                已抢完
            </button>
        </c:if>
    </div>
    <div class="check" onclick="rushToBuy.fn.goActivityInfo()">
        <a href="javascript:void(0)" id="check">查看活动详情<img src="${contextPath}/static/weixin/images/Group 6.png"></a>
    </div>
</section>
<script type="application/javascript">
    var rushToBuy = {
        v: {},
        fn: {
            init: function () {

            },
            goBuy: function (status) {
                var id = $('#id').val();
                var userId = $('#userId').val();

                if (null == userId || userId == '') {
                    var params = window.location.search;
                    var ss = params.lastIndexOf("=");
                    var ss2 = params.substring((ss + 1), params.length);

                    window.location.href = "${contextPath}/weixin/login/toLogin?salemanId=" + ss2;
                } else {
                    // 直接购买
                    $.post("weixin/product/snapUp", {
                        id: id,
                        isUsed: status
                    }, function (result) {
                        if (result.status == '0') {
                            // 如果返回结果不为空，代表抢购成功，此时跳转到抢购结果界面
                            window.location.href = "${contextPath}/weixin/product/toSnapUpResult?pbrId=" + result.data.id;
                        } else {
                            alert("result::" + result.msg);
                        }
                    });
                }
            },
            goActivityInfo: function () {
                window.location.href = "${contextPath}/weixin/activity/info?id=1";
            }
        }
    }

    $(function () {
        rushToBuy.fn.init();
    })
</script>

</body>
</html>