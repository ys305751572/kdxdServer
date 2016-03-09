<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/13
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="../inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>会员详情</title>
    <%@ include file="../inc/css.jsp" %>
    <link href="static/js/plugins/dropper/jquery.fs.dropper.css" rel="stylesheet">
    <script src="static/js/plugins/dropper/jquery.fs.dropper.js"></script>
</head>

<body>

<div id="posts" class="wrapper">
    <%@ include file="../inc/nav.jsp" %>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">会员详情</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="optionsBusinessForm" method="post" action="" class="form-horizontal" role="form">
                            <fieldset disabled="">
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">手机号:</label>
                                    <div class="col-sm-5">
                                        <h4>${user.mobile}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">昵称:</label>
                                    <div class="col-sm-5">
                                        <h4>${user.nickname}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">注册途径:</label>
                                    <div class="col-sm-5">
                                        <h4><c:if test="${user.plat eq 0 }">APP平台</c:if><c:if test="${user.plat eq 1}">微信平台</c:if></h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">注册时间:</label>
                                    <div class="col-sm-5">
                                        <h4>${user.createDate}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">余额:</label>
                                    <div class="col-sm-5">
                                        <h4>￥${user.money}</h4>
                                    </div>
                                </div>
                                <div class="form-group img_tooltip" >
                                    <label class="col-sm-2 control-label">头像:</label>
                                    <div class="col-sm-3">
                                        <div class="image_show"  <c:if test=""> style="display: none"  </c:if>>
                                            <img src="" class='img-responsive' >
                                        </div>
                                    </div>

                                </div>
                                <c:forEach items="${user.list}" var="address">
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">收货地址:</label>
                                        <div class="col-sm-5">
                                            <h4>${address.name}(${address.mobile})<span style="color: red"><c:if test="${address.isDefault eq 0}">默认</c:if></span></h4>
                                            <h4>${address.address}</h4>
                                        </div>
                                    </div>
                                </c:forEach>


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

<%@ include file="../inc/footer.jsp" %>
</body>

</html>