<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="../inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>添加抢购</title>
    <%@ include file="../inc/css.jsp" %>
    <link href="static/js/plugins/bootstrap-fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <script src="static/js/plugins/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
    <script src="static/js/plugins/bootstrap-fileinput/js/fileinput_locale_zh.js" type="text/javascript"></script>
    <link href="static/js/plugins/dropper/jquery.fs.dropper.css" rel="stylesheet">
    <script src="static/js/plugins/dropper/jquery.fs.dropper.js"></script>
</head>
<style>
    .kv-file-upload{display: none;}
    .fileinput-upload-button {display: none;}
</style>
<body>

<div id="posts" class="wrapper">

    <%@ include file="../inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">添加抢购</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="productForm" method="post" enctype="multipart/form-data" action="admin/pro/save" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">商品标题:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="title" name="title" maxlength="20"
                                           data-rule="required" value="" placeholder="请输入商品标题">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">抢购时间:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control input-append date form_datetime" readonly id="startDate" name="startDate" maxlength="20"
                                           data-rule="required" value="" placeholder="请选择抢购时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">开始时间:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control input-append date form_datetime" readonly id="serviceStartDate" name="serviceStartDate" maxlength="20"
                                           data-rule="required" value="" placeholder="请选择开始时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">商品数量:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="counts" name="counts" maxlength="20"
                                           data-rule="required" value="" placeholder="商品数量">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">优惠券数量:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="couponsCounts" name="couponsCounts" maxlength="20"
                                           data-rule="required" value="" placeholder="优惠券数量">
                                </div>
                            </div>
                            <div class="form-group img_tooltip">
                                <label for="imageId" class="col-sm-2 control-label">封面:</label>
                                <div class="col-sm-3">
                                    <input type="hidden" id="imageId" name="imageId" value="">
                                    <div class="image_show"  <c:if test=""> style="display: none"  </c:if>>
                                        <img src="" class='img-responsive' >
                                    </div>
                                    <div class="image_handle"  <c:if test="">  style="display: none"  </c:if>data-toggle="tooltip" data-placement="top" title="" data-original-title="建议上传宽480px高320px的图片">
                                        <div class="dropped"></div>
                                    </div>
                                </div>
                                <div class="col-sm-5">
                                    <a href="javascript:void(0)" id="removeImg" class="btn btn-info" role="button" >删除图片</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="hidden" id="imageIds" name="imageIds" value="">
                                <label  class="col-sm-2 control-label">轮换图:</label>
                                <div class="col-sm-10">
                                    <input id="the_file" name="files" type="file" multiple=true class="file-loading">
                                </div>
                            </div>

                            <div class="form-group form-service">
                                <label  class="col-sm-2 control-label" >金额:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="days" name="days" maxlength="20"
                                            value="" placeholder="请输入服务天数">
                                </div>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="days2" name="days" maxlength="20"
                                           value="" placeholder="请输入服务金额">
                                </div>
                                <div class="col-sm-3">
                                    <button type='button'  title='' class='btn btn-circle form-service-add'>
                                        <i class='fa fa-plus-circle'></i>
                                    </button>
                                    <button type='button'  title='' class='btn btn-circle form-service-minus hidden'>
                                        <i class='fa fa-minus-circle'></i>
                                    </button>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">简介:</label>
                                <div class="col-sm-6">
                                    <script id="container" name="content" type="text/plain"></script>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" id="submitProduct" class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </form>

                    </div>
                    <!-- /.panel-body -->

                </div>
                <!-- /.panel -->
            </div>
        </div>

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<%@ include file="../inc/footer.jsp" %>
<!-- 配置文件 -->
<script type="text/javascript" src="ueditor1_4_3/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="ueditor1_4_3/ueditor.all.js"></script>
<script>
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format:'yyyy-mm-dd hh:ii'
    });
</script>
</body>

<script type="text/javascript">
    var product = {
        v: {
            id: "product",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                var a = "ass";
                var b = "dfgg";
                var object1 = {"a":a,"b":b};
                var object2 = {"a":a,"b":b};
                var objList = [];
                objList.push(object1);
                objList.push(object2);

                console.log(JSON.stringify(objList));

                if($("#id").val()!=""){
                    $(".page-header").text("编辑商品")
                }
                $("#submitProduct").click(function(){
                    product.fn.save();
                })
                product.fn.imageInit();
                product.fn.dropperInit();
                product.fn.serviceInit();

                $("#removeImg").click(function(){
                    product.fn.clearImageView();
                })

                $(".addService").click(function() {

                });
                UE.getEditor('container');

            },
            serviceInit : function() {
                $(".form-service-minus").removeClass("hidden");
            },
            clearImageView: function(){
                $("#imageId").val("");
                $(".image_show").html("");
                $(".image_handle").show();
                $(".dropper-input").val("");
            },
            viewImage: function (image) {
                if (image) {
                    $(".dropper-input").val("");
                    $(".image_handle").hide();
                    $(".image_show").show();
                    $("#imageId").val(image.id);
                    $(".image_show").html("<img src='" + image.path + "' class='img-responsive' >");
                }
            },
            dropperInit: function () {
                $(".dropped").dropper({
                    postKey: "file",
                    action: "common/file/save/image",
                    postData: {thumbSizes: '480x800'},
                    label: "把图片拖拽到此处 ",
                    maxSize: 204857
                }).on("fileComplete.dropper", product.fn.onFileComplete)
                        .on("fileError.dropper", product.fn.onFileError);
            },
            onFileComplete: function (e, file, response) {
                if (response.status == '0') {
                    product.fn.viewImage(response.data);
                } else {
                    $bluemobi.notify(response.msg, "error");
                }
            },
            onFileError: function (e, file, error) {
                $bluemobi.notify(error, "error");
            },
            initialPreview:function(){
                var imgPreViews = [];
                <c:forEach var="_image" items="${product.images}" >
                var img =  "<img src='${_image.path}' style ='height:160px'>"
                imgPreViews.push(img);
                </c:forEach>
                return imgPreViews;
            },
            initialPreviewConfig:function(){
                var imgPreViewsConf = [];
                <c:forEach var="_image" items="${product.images}" >
                var conf = {
                    caption: "",
                    width: "120px",
                    url: "product/delPic?productId=${product.id}&imageId=${_image.id}",
                    key: ${_image.id}
                }
                imgPreViewsConf.push(conf);
                </c:forEach>
                return imgPreViewsConf;

            },
            imageInit:function(){
                var $input = $("#the_file");
                $input.fileinput({
                    uploadUrl: "gen/save/images", // server upload action
                    uploadAsync: false,
                    showUpload: true, // hide upload button
                    showRemove: false, // hide remove button
                    overwriteInitial: false,
                    minFileCount: 1,
                    maxFileCount: 3,
                    initialPreview: product.fn.initialPreview(),
                    initialPreviewConfig: product.fn.initialPreviewConfig(),
                    msgFilesTooMany:"只能上传三张图片",
                    allowedFileTypes:['image'],
                    uploadExtraData: function() {  // callback example
                        var out = {}, key, i = 0;
                        $('.kv-input:visible').each(function() {
                            $el = $(this);
                            key = $el.hasClass('kv-new') ? 'new_' + i : 'init_' + i;
                            out[key] = $el.val();
                            i++;
                        });
                        return out;
                    }
                }).on('filebatchuploadsuccess', function(event, data, previewId, index) {
                    var response = data.response;
                    if(response.status==0){
                        var imageIds = "";
                        $.each(response.data,function(index,data){
                            imageIds+=data.id+",";
                        })
                        if(imageIds.length>0){
                            imageIds =  imageIds.substr(0,imageIds.length-1);
                        }
                        $("#imageIds").val(imageIds);

                        $("#productForm").ajaxSubmit({
                            dataType: "json",
                            success: function (result) {
                                $("#imageIds").val("");
                                product.fn.responseComplete(result);
                            }
                        })
                    }
                });
            },
            save: function () {

                console.log($("#datetest").val());
                if(!$('#productForm').isValid()) {
                    return false;
                };
                if($("#imageId")==""||$("#imageId")==null){
                    $bluemobi.notify("缩略图不能为空!", "error");
                    return false;
                }

                if($(".glyphicon-hand-down").length==0){ // 没有图片的情况
                    $("#productForm").ajaxSubmit({
                        dataType: "json",
                        success: function (result) {
                            product.fn.responseComplete(result,true);
                        }
                    });
                }else{ // 有图片的情况
                    $(".fileinput-upload-button").trigger("click");
                }

            },
            responseComplete: function (result) {
                if (result.status == "0") {
                    $bluemobi.notify(result.msg, "success");
//                    $("#id").val(result.data.id)
                    window.location.href = " ${contextPath}/admin/info/index";
                } else {
                    $bluemobi.notify(result.msg, "error");
                }
            }
        }
    }

    $(document).ready(function () {
        product.fn.init();
    });
</script>
</html>