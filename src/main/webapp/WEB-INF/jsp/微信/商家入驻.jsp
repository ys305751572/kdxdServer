<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp"%>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>商家入驻</title>
    <!-- Bootstrap Core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="static/font-awesome-4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">

                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <form  id="business_form" method="post" action="" class="form-horizontal" role="form">
                                    <a type="button" href="javascript:void(0)" id="getmap" class="btn btn-primary btn-sm">获取地理位置</a>

                                    <div class="form-group">
                                        <img src="" id="maplocal" class="img-responsive">
                                    </div>

                                    <input type="hidden" name="latitude" value="">

                                    <input type="hidden" name="longitude" value="">

                                    <div class="form-group">
                                        <label>酒店名称</label>
                                        <input type="text" class="form-control" name="name" value="ss">
                                    </div>
                                    <div class="form-group">
                                        <label>联系方式</label>
                                        <input type="text" class="form-control" name="contact" value="ddd">
                                    </div>
                                    <div class="form-group">
                                        <label>详细地址</label>
                                        <input type="text" class="form-control" name="address" value="ff">
                                    </div>

                                    <a type="button" href="javascript:void(0)" id="submit" class="btn btn-primary btn-sm">提交</a>

                                </form>
                            </div>

                        </div>
                        <!-- /.row (nested) -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>

    </div>
</body>

<!-- jQuery Version 1.11.0 -->
<script src="static/js/zepto.js"></script>
<script src="static/js/plugins/jquery.form.min.js"></script>

<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>


</html>

<script type="text/javascript">

    wx.config({
        debug: false,
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
        $("#getmap").on("click",function(){
            // 7.2 获取当前地理位置
            wx.getLocation({
                success: function (res) {
                    var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                    var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                    var speed = res.speed; // 速度，以米/每秒计
                    var accuracy = res.accuracy; // 位置精度

                    $("#business_form input[name='latitude']").val(latitude);
                    $("#business_form input[name='longitude']").val(longitude);

                    //http://apis.map.qq.com/ws/staticmap/v2/?center=39.908823,116.397496&zoom=15&markers=color:blue|label:A|39.908823,116.397496&key=HUSBZ-Z6BA3-DKH34-3ND42-CP633-QBF4S&size=300*200
                    var url = "http://apis.map.qq.com/ws/staticmap/v2/?center="+latitude+","+longitude+"&zoom=15&markers=color:blue|label:I|"+latitude+","+longitude+"&key=HUSBZ-Z6BA3-DKH34-3ND42-CP633-QBF4S&size=300*200"
                    $('#maplocal').attr("src",url);

                },
                cancel: function (res) {
                    alert('用户拒绝授权获取地理位置');
                }
            });

        })

    })


    Zepto(function($){

        $("#submit").on("click",function(){
            $.ajax({
                type: 'POST',
                url:'weixin/business/submit',
                data:$('#business_form').serialize(),
                success: function(data){

                }
            })

        })


    })


</script>