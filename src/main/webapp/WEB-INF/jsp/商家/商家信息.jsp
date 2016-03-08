<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/13
  Time: 14:28
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
    <title>商家信息</title>
    <%@ include file="inc/css.jsp" %>
    <link href="static/js/plugins/dropper/jquery.fs.dropper.css" rel="stylesheet">
    <script src="static/js/plugins/dropper/jquery.fs.dropper.js"></script>
</head>

<body>

<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">商家信息</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="optionsBusinessForm" method="post" action="business/info/save" class="form-horizontal" role="form">
                            <input type="hidden" name="businessId" value="">

                            <div class="form-group">
                                <label  class="col-sm-2 control-label">酒店名称:</label>
                                <div class="col-sm-5">
                                    <h4>${business.name}</h4>
                                </div>
                            </div>
                            <div class="form-group img_tooltip" >
                                <label for="imageId" class="col-sm-2 control-label">缩略图:</label>

                                <div class="col-sm-3">
                                    <input type="hidden" id="imageId" name="imageId" value="${business.image.id}">


                                    <div class="image_show"  <c:if test="${business.image==null}"> style="display: none"  </c:if>>
                                        <img src="${business.image.path}" class='img-responsive' >
                                    </div>
                                    <div class="image_handle"  <c:if test="${business.image!=null}">  style="display: none"  </c:if>data-toggle="tooltip" data-placement="top" title="" data-original-title="建议上传宽480px高320px的图片">
                                        <div class="dropped"></div>
                                    </div>
                                </div>
                                <div class="col-sm-5">
                                    <a href="javascript:void(0)" id="removeImg" class="btn btn-info" role="button" >删除图片</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="linkman" class="col-sm-2 control-label">联系人:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="linkman" name="linkman" maxlength="20"
                                           data-rule="required" value="${business.linkman}" placeholder="请输入联系人">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contact" class="col-sm-2 control-label">联系电话:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="contact" name="contact" maxlength="20"
                                           data-rule="required" value="${business.contact}" placeholder="请输入联系电话">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="orderTel" class="col-sm-2 control-label">联系电话:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="orderTel" name="orderTel" maxlength="20"
                                           data-rule="required" value="${business.orderTel}" placeholder="请输入订餐电话">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address" class="col-sm-2 control-label">详细地址:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="address" name="address" maxlength="20"
                                           data-rule="required" value="${business.address}" placeholder="请输入详细地址">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="content" class="col-sm-2 control-label">酒店介绍:</label>
                                <div class="col-sm-5">
                                    <textarea id="content" name="content" class="form-control" placeholder="请输入酒店介绍" rows="3">${business.content}</textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label">  </label>
                                <label class="col-sm-2 control-label">
                                    <button id="infoSave" type="button" class="btn btn-primary btn-lg btn-block">保存</button>
                                </label>

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


    var information = {
        v: {
            id: "information",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $("#removeImg").click(function(){
                    information.fn.clearImageView();
                })
                information.fn.dropperInit();

                $("#infoSave").click(function(){
                    information.fn.save();
                })

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
            save:function(){
                if(!$('#optionsBusinessForm').isValid()) {
                    return false;
                };
                $("#optionsBusinessForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        information.fn.responseComplete(result,true);
                    }
                })
            },
            dropperInit: function () {
                $(".dropped").dropper({
                    postKey: "file",
                    action: "common/file/save/image",
                    postData: {thumbSizes: '480x800'},
                    label: "把图片拖拽到此处",
                    maxSize: 204857
                }).on("fileComplete.dropper", information.fn.onFileComplete)
                        .on("fileError.dropper", information.fn.onFileError);
            },
            onFileComplete: function (e, file, response) {
                if (response.status == '0') {
                    information.fn.viewImage(response.data);
                } else {
                    $bluemobi.notify(response.msg, "error");
                }
            },
            onFileError: function (e, file, error) {
                $bluemobi.notify(error, "error");
            },
            responseComplete: function (result,action) {
                if (result.status == "0") {
                    $bluemobi.notify(result.msg, "success");
                } else {
                    $bluemobi.notify(result.msg, "error");
                }
            }
        }
    }

    $(document).ready(function () {
        information.fn.init();
    });

</script>


</html>