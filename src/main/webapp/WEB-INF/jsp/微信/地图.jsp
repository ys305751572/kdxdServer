<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>个人中心</title>

    <style type="text/css">
        html {
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%;
            -webkit-user-select: none;
            user-select: none;
        }
        body {
            line-height: 1.6;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            background-color: #f1f0f6;
        }
        * {
            margin: 0;
            padding: 0;
        }
        button {
            font-family: inherit;
            font-size: 100%;
            margin: 0;
            *font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }
        ul,
        ol {
            padding-left: 0;
            list-style-type: none;
        }
        a {
            text-decoration: none;
        }
        .label_box {
            background-color: #ffffff;
        }
        .label_item {
            padding-left: 15px;
        }
        .label_inner {
            padding-top: 10px;
            padding-bottom: 10px;
            min-height: 24px;
            position: relative;
        }
        .label_inner:before {
            content: " ";
            position: absolute;
            left: 0;
            top: 0;
            width: 200%;
            height: 1px;
            border-top: 1px solid #ededed;
            -webkit-transform-origin: 0 0;
            transform-origin: 0 0;
            -webkit-transform: scale(0.5);
            transform: scale(0.5);
            top: auto;
            bottom: -2px;
        }
        .lbox_close {
            position: relative;
        }
        .lbox_close:before {
            content: " ";
            position: absolute;
            left: 0;
            top: 0;
            width: 200%;
            height: 1px;
            border-top: 1px solid #ededed;
            -webkit-transform-origin: 0 0;
            transform-origin: 0 0;
            -webkit-transform: scale(0.5);
            transform: scale(0.5);
        }
        .lbox_close:after {
            content: " ";
            position: absolute;
            left: 0;
            top: 0;
            width: 200%;
            height: 1px;
            border-top: 1px solid #ededed;
            -webkit-transform-origin: 0 0;
            transform-origin: 0 0;
            -webkit-transform: scale(0.5);
            transform: scale(0.5);
            top: auto;
            bottom: -2px;
        }
        .lbox_close .label_item:last-child .label_inner:before {
            display: none;
        }
        .btn {
            display: block;
            margin-left: auto;
            margin-right: auto;
            padding-left: 14px;
            padding-right: 14px;
            font-size: 18px;
            text-align: center;
            text-decoration: none;
            overflow: visible;
            /*.btn_h(@btnHeight);*/
            height: 42px;
            border-radius: 5px;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            color: #ffffff;
            line-height: 42px;
            -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
        }
        .btn.btn_inline {
            display: inline-block;
        }
        .btn_primary {
            background-color: #04be02;
        }
        .btn_primary:not(.btn_disabled):visited {
            color: #ffffff;
        }
        .btn_primary:not(.btn_disabled):active {
            color: rgba(255, 255, 255, 0.9);
            background-color: #039702;
        }
        button.btn {
            width: 100%;
            border: 0;
            outline: 0;
            -webkit-appearance: none;
        }
        button.btn:focus {
            outline: 0;
        }
        .wxapi_container {
            font-size: 16px;
        }
        h1 {
            font-size: 14px;
            font-weight: 400;
            line-height: 2em;
            padding-left: 15px;
            color: #8d8c92;
        }
        .desc {
            font-size: 14px;
            font-weight: 400;
            line-height: 2em;
            color: #8d8c92;
        }
        .wxapi_index_item a {
            display: block;
            color: #3e3e3e;
            -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
        }
        .wxapi_form {
            background-color: #ffffff;
            padding: 0 15px;
            margin-top: 30px;
            padding-bottom: 15px;
        }
        h3 {
            padding-top: 16px;
            margin-top: 25px;
            font-size: 16px;
            font-weight: 400;
            color: #3e3e3e;
            position: relative;
        }
        h3:first-child {
            padding-top: 15px;
        }
        h3:before {
            content: " ";
            position: absolute;
            left: 0;
            top: 0;
            width: 200%;
            height: 1px;
            border-top: 1px solid #ededed;
            -webkit-transform-origin: 0 0;
            transform-origin: 0 0;
            -webkit-transform: scale(0.5);
            transform: scale(0.5);
        }
        .btn {
            margin-bottom: 15px;
        }


    </style>
</head>
<body>
<div class="lbox_close wxapi_form">
    <h3 id="menu-basic">基础接口</h3>
    <span class="desc">判断当前客户端是否支持指定JS接口</span>
    <button class="btn btn_primary" id="checkJsApi">checkJsApi</button>

    <h3 id="menu-location">地理位置接口</h3>
    <span class="desc">使用微信内置地图查看位置接口</span>
    <button class="btn btn_primary" id="openLocation">openLocation</button>
    <span class="desc">获取地理位置接口</span>
    <button class="btn btn_primary" id="getLocation">getLocation</button>

</div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
    wx.config({
        debug: true,
        appId: '${appid}',
        timestamp: '${wxJsapiSignature.timestamp}',
        nonceStr: '${wxJsapiSignature.noncestr}',
        signature: '${wxJsapiSignature.signature}',
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
            'hideMenuItems',
            'showMenuItems',
            'hideAllNonBaseMenuItem',
            'showAllNonBaseMenuItem',
            'translateVoice',
            'startRecord',
            'stopRecord',
            'onRecordEnd',
            'playVoice',
            'pauseVoice',
            'stopVoice',
            'uploadVoice',
            'downloadVoice',
            'chooseImage',
            'previewImage',
            'uploadImage',
            'downloadImage',
            'getNetworkType',
            'openLocation',
            'getLocation',
            'hideOptionMenu',
            'showOptionMenu',
            'closeWindow',
            'scanQRCode',
            'chooseWXPay',
            'openProductSpecificView',
            'addCard',
            'chooseCard',
            'openCard'
        ]
    });


    wx.ready(function () {


        // 7 地理位置接口
        // 7.1 查看地理位置
        document.querySelector('#openLocation').onclick = function () {
            wx.openLocation({
                latitude: 23.099994,
                longitude: 113.324520,
                name: 'TIT 创意园',
                address: '广州市海珠区新港中路 397 号',
                scale: 14,
                infoUrl: 'http://weixin.qq.com'
            });
        };

        // 7.2 获取当前地理位置
        document.querySelector('#getLocation').onclick = function () {
            wx.getLocation({
                success: function (res) {
                    alert(JSON.stringify(res));
                },
                cancel: function (res) {
                    alert('用户拒绝授权获取地理位置');
                }
            });
        };


    })

</script>

</html>