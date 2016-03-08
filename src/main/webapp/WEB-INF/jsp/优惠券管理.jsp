<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/31
  Time: 11:48
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
    <title>优惠券管理</title>
    <%@ include file="inc/css.jsp" %>
</head>

<body>

<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">优惠券管理</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="btn-group pull-left">
                            <a href="javascript:void(0)" id="addCoupons" class="btn btn-outline btn-primary btn-lg"
                               role="button">生成SN码优惠券</a>
                            <a href="javascript:void(0)" id="addUserCoupons" class="btn btn-outline btn-warning btn-lg"
                               role="button">给所有用户发放优惠券</a>
                            <a href="javascript:void(0)" id="delCoupons" class="btn btn-outline btn-danger btn-lg"
                               role="button">批量删除</a>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables">
                                <colgroup>
                                    <col class="gradeA even" style="text-align: center; width: 6%"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="$bluemobi.checkAll(this)" class="checkall"/></th>
                                    <th>标题</th>
                                    <th>SN码</th>
                                    <th>面值</th>
                                    <th>使用金额</th>
                                    <th>有效期</th>
                                    <th>是否使用</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /.panel-body -->

                </div>
                <!-- /.panel -->
            </div>
        </div>

    </div>
    <!-- /#page-wrapper -->


    <!-- Modal -->
    <div class="modal fade" id="addCouponsModal" tabindex="-1" role="dialog" aria-labelledby="memberModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addCouponsModalLabel">操作</h4>
                </div>
                <div class="modal-body ">
                    <form id="addCouponsForm" method="post" action="admin/coupon/save" class="form-horizontal" role="form">
                        <input type="hidden" name="id" value="">
                        <input type="hidden" name="toUser" value="false">
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">标题:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="title" name="title" maxlength="20"
                                       data-rule="required" placeholder="请输入优惠券标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="faceValue" class="col-sm-2 control-label">面值:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="faceValue" name="faceValue" maxlength="20"
                                       data-rule="required" placeholder="请输入面值">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="limitValue" class="col-sm-2 control-label">金额限制:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="limitValue" name="limitValue" maxlength="20"
                                       data-rule="required" placeholder="请输入使用金额限制">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="startDate" class="col-sm-2 control-label">开始时间:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="startDate" name="startDate" maxlength="20"
                                       data-rule="required" placeholder="请输入使用开始时间">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="endDate" class="col-sm-2 control-label">截至时间:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="endDate" name="endDate" maxlength="20"
                                       data-rule="required" placeholder="请输入使用截至时间">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="number" class="col-sm-2 control-label">生成数量:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="number" name="number" maxlength="20"
                                       data-rule="required;integer;range[1~1000]" novalidate placeholder="请输入生成数量">
                            </div>
                        </div>

                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="saveCoupons" class="btn btn-primary">保存</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- Modal end -->





</div>
<!-- /#wrapper -->

<%@ include file="inc/footer.jsp" %>
<script  src="static/js/plugins/My97DatePicker/WdatePicker.js"> </script>
</body>

<script type="text/javascript">


    var coupons = {
        v: {
            id: "coupons",
            list :[],
            dTable: null
        },
        fn: {
            init: function () {
                coupons.fn.dataTableInit();

                $("#addCoupons").click(function(){
                    coupons.fn.showModal("addCouponsModal","批量生成优惠券");
                    $("#addCouponsForm input[name='toUser']").val(false);
                    $("#addCouponsForm input[name='number']").parent().parent().show();
                    $("#addCouponsForm input[name='number']").removeAttr("novalidate")
                })

                $("#addUserCoupons").click(function(){
                    coupons.fn.showModal("addCouponsModal","给所有用户发放优惠券");
                    $("#addCouponsForm input[name='toUser']").val(true);
                    $("#addCouponsForm input[name='number']").parent().parent().hide();
                    $("#addCouponsForm input[name='number']").attr("novalidate","")
                })

                $("#saveCoupons").click(function(){
                    coupons.fn.save();
                })

                $("#delCoupons").click(function(){
                    var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                    var ids = checkBox.getInputId();
                    coupons.fn.deleteRow(ids)
                })

            },
            dataTableInit: function () {
                coupons.v.dTable = $bluemobi.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching":false,
                    "ordering": false,
                    "ajax": {
                        "url": "admin/coupon/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "title"},
                        {"data": "code"},
                        {"data": "faceValue"},
                        {"data": "limitValue"},
                        {"data": "startDate"},
                        {"data": "status"},
                        {"data": "createDate"},
                        {
                            "data": null,
                            "defaultContent":
                            "<button type='button'  title='删除' class='btn btn-danger btn-circle delete'>" +
                            "<i class='fa fa-bitbucket'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        coupons.v.list.push(data);
                        $('td', row).eq(0).html("<input type='checkbox' value=" + data.id + ">");
                        $('td', row).eq(5).html(data.startDate+"~"+data.endDate);
                        if(data.status){
                            $(row).addClass("success");
                            $('td', row).eq(6).html("是");
                        }else{
                            $('td', row).eq(6).html("否");
                        }


                    },
                    rowCallback: function (row, data) {
                        var items = coupons.v.list;

                        $('td', row).last().find(".edit").click(function () {
                            coupons.fn.edit(data.id);
                        })

                        $('td', row).last().find(".delete").click(function () {
                            coupons.fn.deleteRow([data.id]);
                        });

                    },
                    "fnServerParams": function (aoData) {

                    },
                    "fnDrawCallback": function (row) {
                        $bluemobi.uiform();
                    }
                });
            },
            deleteRow: function (ids) {
                if (ids.length > 0) {
                    $bluemobi.optNotify(function () {
                        $bluemobi.ajax("admin/coupon/delete", {ids: JSON.stringify(ids)}, function (result) {
                            if (result.status == "0") {
                                $bluemobi.notify(result.msg, "success");
                                coupons.v.dTable.ajax.reload();

                            } else {
                                $bluemobi.notify(result.msg, "error");
                            }
                        })
                    });
                }
            },
            edit: function (id) {
                coupons.fn.showModal("addCouponsModal","修改商品产区");
                var items = coupons.v.list;
                $.each(items, function (index, item) {
                    if (item.id == id) {
                        for (var key in item) {
                            var element = $("#addCouponsForm :input[name=" + key + "]")
                            if (element.length > 0) {
                                element.val(item[key]);
                            }
                        }
                    }
                })
            },
            save: function () {
                if(!$('#addCouponsForm').isValid()) {
                    return false;
                };
                $("#addCouponsForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        coupons.fn.responseComplete(result,true);
                    }
                })
            },
            responseComplete: function (result,action) {
                if (result.status == "0") {
                    if(action){
                        coupons.v.dTable.ajax.reload(null, false);
                    }else{
                        coupons.v.dTable.ajax.reload();
                    }
                    $bluemobi.notify(result.msg, "success");
                    $("#addCouponsModal").modal("hide");
                } else {
                    $bluemobi.notify(result.msg, "error");
                }
            },
            showModal: function (modalId,title) {
                $("#"+modalId).modal("show");
                $bluemobi.clearForm($("#"+modalId));
                if (title) {
                    $("#"+modalId+" .modal-title").text(title);
                }
            }

        }
    }

    $(document).ready(function () {
        coupons.fn.init();
    });

</script>


</html>