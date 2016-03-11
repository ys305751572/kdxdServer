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
    <title>查看订单</title>
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
                <h1 class="page-header">查看订单</h1>
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
                                    <label  class="col-sm-2 control-label">订单号:</label>
                                    <div class="col-sm-5">
                                        <h4>${order.sn}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">商品名称:</label>
                                    <div class="col-sm-5">
                                        <h4>${order.product.title}</h4>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">抢购用户:</label>
                                    <div class="col-sm-5">
                                        <h4>${order.user.nickname}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">服务时间:</label>
                                    <div class="col-sm-5">
                                        <h4>${order.createDate}</h4>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">订单状态:</label>
                                        <div class="col-sm-5">
                                            <h4><c:if test="${order.status eq 0 }">待发货</c:if>
                                                <c:if test="${order.status eq 1 }">已发货</c:if>
                                                <c:if test="${order.status eq 2 }">已签收</c:if></h4>
                                        </div>
                                </div>
                                    <div class="form-group">
                                        <label  class="col-sm-2 control-label">收货地址:</label>
                                        <div class="col-sm-5">
                                            <h4>${order.name}(${order.mobile})</h4>
                                            <h4>${order.address}</h4>
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

<%@ include file="../inc/footer.jsp" %>
</body>

</html>