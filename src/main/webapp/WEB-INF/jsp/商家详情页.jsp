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
                <h1 class="page-header">商家账户管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="optionsBusinessForm" method="post" action="business/info/save" class="form-horizontal" role="form">
                            <fieldset disabled="">
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
                            </fieldset>
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

</html>