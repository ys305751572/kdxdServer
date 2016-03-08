<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/17
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>添加商品</title>
    <%@ include file="inc/css.jsp" %>
    <link href="static/js/plugins/bootstrap-fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
    <script src="static/js/plugins/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
    <script src="static/js/plugins/bootstrap-fileinput/js/fileinput_locale_zh.js" type="text/javascript"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="ueditor1_4_3/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor1_4_3/ueditor.all.js"></script>
</head>
<style>
    .kv-file-upload{display: none;}
    .fileinput-upload-button {display: none;}
</style>
<body>

<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">添加商品</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="productForm" method="post" enctype="multipart/form-data" action="admin/product/save" class="form-horizontal" role="form">
                            <input type="hidden" id="id" name="id" value="${product.id}">

                            <div class="form-group">
                                <label  class="col-sm-2 control-label">商品名称:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="name" name="name" maxlength="20"
                                           data-rule="required" value="${product.name}" placeholder="请输入商品名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">商品编码:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="code" name="code" maxlength="20"
                                           data-rule="required" value="${product.code}" placeholder="请输入商品编码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">商品产区:</label>
                                <div class="col-sm-3">

                                    <select class="form-control" name="productArea.id" data-rule="required">
                                        <c:forEach items="${productAreaList}" var="productArea">
                                            <option value="${productArea.id}" <c:if test="${product.productArea.id == productArea.id}">selected</c:if> >${productArea.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">商品类型:</label>
                                <div class="col-sm-3">
                                    <select class="form-control" name="productType.id" data-rule="required">
                                        <c:forEach items="${productTypeList}" var="productType">
                                            <option value="${productType.id}">${productType.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">品牌:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="brand" name="brand" maxlength="20"
                                           data-rule="required" value="${product.brand}" placeholder="请输入商品名称">
                                </div>
                            </div>

                            <div class="form-group  has-feedback">
                                <label  class="col-sm-2 control-label">市场价:</label>
                                <div class="col-sm-3">
                                    <input type="text" value="${product.marketPrice}" class="form-control" id="marketPrice" name="marketPrice" maxlength="20"
                                           data-rule="required;price"  data-rule-price="[/^\d{0,8}\.{0,1}(\d{1,2})?$/, '请输入正确的价格']" placeholder="请输入商品市场价">
                                    <span class="glyphicon  form-control-feedback" >元</span>
                                </div>
                            </div>

                            <div class="form-group  has-feedback">
                                <label  class="col-sm-2 control-label">优惠价:</label>
                                <div class="col-sm-3">
                                    <input type="text" value="${product.price}" class="form-control" id="price" name="price" maxlength="20"
                                           data-rule="required;price"  data-rule-price="[/^\d{0,8}\.{0,1}(\d{1,2})?$/, '请输入正确的价格']" placeholder="请输入商品市场价">
                                    <span class="glyphicon  form-control-feedback" >元</span>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label">简介:</label>
                                <div class="col-sm-6">
                                    <script id="container" name="content" type="text/plain">${product.content}</script>
                                </div>
                            </div>


                            <div class="form-group">
                                <input type="hidden" id="imageIds" name="imageIds" value="">
                                <label  class="col-sm-2 control-label">图片:</label>
                                <div class="col-sm-10">
                                    <input id="the_file" name="files" type="file" multiple=true class="file-loading">
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

<%@ include file="inc/footer.jsp" %>
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

                if($("#id").val()!=""){
                    $(".page-header").text("编辑商品")
                }
                $("#submitProduct").click(function(){
                    product.fn.save();
                })
                product.fn.imageInit();


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
                        url: "admin/product/delPic?productId=${product.id}&imageId=${_image.id}",
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
                    maxFileCount: 4,
                    initialPreview: product.fn.initialPreview(),
                    initialPreviewConfig: product.fn.initialPreviewConfig(),
                    msgFilesTooMany:"只能上传四张图片",
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
                if(!$('#productForm').isValid()) {
                    return false;
                };

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
                } else {
                    $bluemobi.notify(result.msg, "error");
                }
            }
        }
    }

    $(document).ready(function () {
        product.fn.init();
        var ue = UE.getEditor('container');

    });

</script>

</html>