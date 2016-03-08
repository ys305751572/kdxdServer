<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/24
  Time: 17:30
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
    <title>订单列表</title>
    <%@ include file="inc/css.jsp" %>

</head>

<body>

<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">订单列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">

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
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="$bluemobi.checkAll(this)" class="checkall"/></th>
                                    <th>名称</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
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

</div>
<!-- /#wrapper -->

<%@ include file="inc/footer.jsp" %>
</body>

<script type="text/javascript">


    var orders = {
        v: {
            id: "orders",
            list :[],
            dTable: null
        },
        fn: {
            init: function () {
                orders.fn.dataTableInit();

                $("#addProductArea").click(function(){
                    orders.fn.showModal("addProductAreaModal","添加商品产区");
                })

                $("#saveProductArea").click(function(){
                    orders.fn.save();
                })

            },
            dataTableInit: function () {
                orders.v.dTable = $bluemobi.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching":false,
                    "ordering": false,
                    "ajax": {
                        "url": "admin/order/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "name"},
                        {"data": "createDate"},
                        {"data": "updateDate"},
                        {
                            "data": null,
                            "defaultContent":
                            "<button type='button'  title='修改' class='btn btn-primary btn-circle edit'>" +
                            "<i class='fa fa-edit'></i>" +
                            "</button>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button'  title='删除' class='btn btn-danger btn-circle delete'>" +
                            "<i class='fa fa-bitbucket'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        orders.v.list.push(data);
                        $('td', row).eq(0).html("<input type='checkbox' value=" + data.id + ">");
                    },
                    rowCallback: function (row, data) {
                        var items = orders.v.list;

                        $('td', row).last().find(".edit").click(function () {
                            orders.fn.edit(data.id);
                        })

                        $('td', row).last().find(".delete").click(function () {
                            orders.fn.deleteRow([data.id]);
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
                        $bluemobi.ajax("admin/order/delete", {ids: JSON.stringify(ids)}, function (result) {
                            if (result.status == "0") {
                                $bluemobi.notify(result.msg, "success");
                                orders.v.dTable.ajax.reload();

                            } else {
                                $bluemobi.notify(result.msg, "error");
                            }
                        })
                    });
                }
            },
            edit: function (id) {
                orders.fn.showModal("addProductAreaModal","修改商品产区");
                var items = orders.v.list;
                $.each(items, function (index, item) {
                    if (item.id == id) {
                        for (var key in item) {
                            var element = $("#addProductAreaForm :input[name=" + key + "]")
                            if (element.length > 0) {
                                element.val(item[key]);
                            }
                        }
                    }
                })
            },
            save: function () {
                $("#addProductAreaForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        orders.fn.responseComplete(result,true);
                        $("#addProductAreaModal").modal("hide");
                    }
                })
            },
            responseComplete: function (result,action) {
                if (result.status == "0") {
                    if(action){
                        orders.v.dTable.ajax.reload(null, false);
                    }else{
                        orders.v.dTable.ajax.reload();
                    }

                    $bluemobi.notify(result.msg, "success");
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
        orders.fn.init();
    });
</script>


</html>