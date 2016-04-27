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
                            <input type="hidden" id="id" name="id" value="${product.id}">
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">商品标题:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="title" name="title" maxlength="20"
                                           data-rule="required" value="${product.title}" placeholder="请输入商品标题">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">抢购时间:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control input-append date form_datetime" readonly id="startDate" name="startDate" maxlength="20"
                                           data-rule="required" value="<date:date value="${product.startDate}" format="yyyy-MM-dd HH:mm"></date:date>" placeholder="请选择抢购时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">配送服务时间:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control input-append date form_datetime" readonly id="serviceStartDate" name="serviceStartDate" maxlength="20"
                                           data-rule="required" value="<date:date value="${product.serviceStartDate}" format="yyyy-MM-dd HH:mm"></date:date>" placeholder="请选择服务时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">商品数量:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="counts" name="counts" maxlength="20"
                                           data-rule="required;integer" value="${product.counts}" placeholder="商品数量">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">优惠券数量:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="couponsCounts" name="couponsCounts" maxlength="20"
                                           data-rule="required;integer" value="${product.couponsCounts}" placeholder="优惠券数量">
                                </div>
                            </div>
                            <div class="form-group img_tooltip">
                                <label for="imageId" class="col-sm-2 control-label">封面:</label>
                                <div class="col-sm-3">
                                    <input type="hidden" id="imageId" name="imageId" value="${product.coverImage.id}">
                                    <div class="image_show"  <c:if test="${product.coverImage.path eq null}"> style="display: none"  </c:if>>
                                        <img src="${product.coverImage.path}" class='img-responsive' >
                                    </div>
                                    <div class="image_handle"  <c:if test="${product.coverImage.path ne null}">  style="display: none"  </c:if>data-toggle="tooltip" data-placement="top" title="" data-original-title="建议上传宽480px高320px的图片">
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

                            <c:if test="${product.id eq null}">
                                <div class="form-group form-service">
                                    <label  class="col-sm-2 control-label" >天数/金额:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="days" name="days" maxlength="20"
                                                value="" data-rule="required;integer" placeholder="请输入服务天数">
                                    </div>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="money" name="money" maxlength="20"
                                               value="" data-rule="required;decimal" placeholder="请输入服务金额">
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
                            </c:if>
                            <c:if test="${product.id ne null}">
                                <c:forEach items="${product.serviceList}" var="serviceObj">
                                    <div class="form-group form-service">
                                        <label  class="col-sm-2 control-label" >金额:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="days" name="days" maxlength="20"  data-rule="required;integer"
                                                   value="${serviceObj.days}" placeholder="请输入服务天数">
                                        </div>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="money" name="money" maxlength="20"
                                                   value="${serviceObj.money}" data-rule="required;decimal" placeholder="请输入服务金额">
                                        </div>
                                        <div class="col-sm-3">
                                            <button type='button'  title='' class='btn btn-circle form-service-add'>
                                                <i class='fa fa-plus-circle'></i>
                                            </button>
                                            <button type='button'  title='' class='btn btn-circle form-service-minus'>
                                                <i class='fa fa-minus-circle'></i>
                                            </button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">简介:</label>
                                <div class="col-sm-8">
                                    <textarea cols="50" rows="8" name="content" style="resize: none;" class="form-control">${product.content}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" id="submitProduct" class="btn btn-primary">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <c:if test="${product.id ne null}">
                                        <button type="button" id="submitAndReset" class="btn btn-primary">提交并重置</button>
                                    </c:if>
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
            index : 3,
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
//                if($("#id").val()!=""){
//                    $(".page-header").text("编辑商品")
//                }
                $("#submitProduct").click(function(){
                    product.fn.save();
                });

                $("#submitAndReset").click(function() {
                    product.fn.submitAndResult();
                });

                product.fn.imageInit();
                product.fn.dropperInit();

                $("#removeImg").click(function(){
                    product.fn.clearImageView();
                })

                $(".form-service-add").click(function() {
                    product.fn.addLine();
                });

                $(".form-service-minus").click(function() {
                    product.fn.delLine(this);
                });
                UE.getEditor('container');

            },
            addLine : function() {
                var html = "<div class=\"form-group form-service\">";
                html += "<label  class=\"col-sm-2 control-label\" ></label>";
                html += "<div class=\"col-sm-3\">";
                html += "<input type=\"text\" class=\"form-control\" id=\"days"+ product.v.index +"\" name=\"days\" maxlength=\"20\" value=\"\"  data-rule=\"required;integer\" placeholder=\"请输入服务天数\">";
                html += "</div>";
                html += "<div class=\"col-sm-3\">";
                html += "<input type=\"text\" class=\"form-control\" id=\"money"+ product.v.index +"\" name=\"money\" maxlength=\"20\" data-rule=\"required;decimal\" value=\"\" placeholder=\"请输入服务金额\">";
                html += "</div>";
                html += "<div class=\"col-sm-3\">";
                html += "<button type=\'button\' title=\'\' class=\'btn btn-circle form-service-add\'>";
                html += "<i class=\'fa fa-plus-circle\'></i>";
                html += "</button>";
                html += "<button type=\'button\'  title=\'\' class=\'btn btn-circle form-service-minus\'>";
                html += "<i class=\'fa fa-minus-circle\'></i>";
                html += "</button>";
                html += "</div></div>";

                product.v.index++;

                $(".form-service").last().after(html);
                var $first = $(".form-service").first().find("button").last();
                if( $first.hasClass("hidden")) {
                    $first.removeClass("hidden");
                }

                var $addbutton = $(".form-service").last().find("button").first();
                $addbutton.click(function(){
                    product.fn.addLine();
                });

                var $minusbutton = $(".form-service").last().find("button").last();
                $minusbutton.click(function(){
                    product.fn.delLine(this);
                });
            },
            delLine : function(data) {
                var size = $(".form-service").size();
                if(size == 1) {
                    $bluemobi.notify("最少保留一条数据", "error");
                    return;
                }
                $(data).parent().parent().remove();
                var $label = $(".form-service").first().find("label");
                $label.text("金额:");
                var size = $(".form-service").size();
                if(size == 1) {
                    var $first = $(".form-service").first().find("button").last();
                    if( !$first.hasClass("hidden")) {
                        $first.addClass("hidden");
                    }
                }
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
                <c:forEach var="_image" items="${product.list}" >
                var img =  "<img src='${_image.path}' style ='height:160px'>"
                imgPreViews.push(img);
                </c:forEach>
                return imgPreViews;
            },
            initialPreviewConfig:function(){
                var imgPreViewsConf = [];
                <c:forEach var="_image" items="${product.list}" >
                var conf = {
                    caption: "",
                    width: "120px",
                    url: "${contextPath}/admin/pro/delPic?productId=${product.id}&imageId=${_image.id}",
                    key: ${_image.id}
                }
                imgPreViewsConf.push(conf);
                </c:forEach>
                return imgPreViewsConf;

            },
            imageInit:function(){
                var $input = $("#the_file");
                $input.fileinput({
                    uploadUrl: "common/file/save/images", // server upload action
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

                    var days = $("input[name='days']");
                    var money = $("input[name = 'money']");
                    var serviceArray = [];
                    for(var i = 0;i < days.length; i++) {
                        var serviceObj = {"days" : $(days[i]).val(),"money" : $(money[i]).val()}
                        serviceArray.push(serviceObj);
                    }
                    var productService = JSON.stringify(serviceArray);

                    var response = data.response;
                    if(response.status==0){
                        var imageIds = "";
                        $.each(response.data,function(index,data){
                            imageIds+=data.id+",";
                        })
                        if(imageIds.length>0){
                            imageIds =  imageIds.substr(0,imageIds.length-1);
                        }
                        console.log("imageIds:" + imageIds);
                        $("#imageIds").val(imageIds);

                        $("#productForm").ajaxSubmit({
                            dataType: "json",
                            data : {"productService": productService},
                            success: function (result) {
                                    $("#imageIds").val("");
                                product.fn.responseComplete(result);
                            }
                        })
                    }
                });
            },
            submitAndResult : function() {
               $("#productForm").attr("action","admin/pro/save?reset=true");
                product.fn.save();
            },
            save: function () {
                var days = $("input[name='days']");
                var money = $("input[name = 'money']");
                var serviceArray = [];
                for(var i = 0;i < days.length; i++) {
                   var serviceObj = {"days" : $(days[i]).val(),"money" : $(money[i]).val()}
                    serviceArray.push(serviceObj);
                }
                var productService = JSON.stringify(serviceArray);
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
                        data : {"productService": productService},
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
                    window.location.href = " ${contextPath}/admin/pro/index";
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