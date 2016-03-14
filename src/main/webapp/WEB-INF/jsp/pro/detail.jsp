<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="../inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>查看抢购</title>
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
                <h1 class="page-header">查看抢购</h1>
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
                                    <label  class="col-sm-2 control-label">商品标题:</label>
                                    <div class="col-sm-5">
                                        <h4>${pro.title}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">抢购时间:</label>
                                    <div class="col-sm-5">
                                        <h4>${pro.startDate}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">开始服务时间:</label>
                                    <div class="col-sm-5">
                                        <h4>${pro.serviceStartDate}</h4>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">数量:</label>
                                    <div class="col-sm-5">
                                        <h4>份数:${pro.counts},优惠券:${pro.couponsCounts}</h4>
                                    </div>
                                </div>
                                <div class="form-group img_tooltip" >
                                    <label for="imageId" class="col-sm-2 control-label">封面:</label>

                                    <div class="col-sm-3">
                                        <input type="hidden" id="imageId" name="imageId" value="${pro.coverImage.path}">
                                        <div class="image_show">
                                            <img src="${pro.coverImage.path}" class='img-responsive' >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" id="imageIds" name="imageIds" value="">
                                    <label  class="col-sm-2 control-label">图片:</label>
                                    <div class="image_show">
                                        <c:forEach items="${pro.list}" var="image">
                                            <img src="${image.path}" class='img-responsive' >
                                        </c:forEach>
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