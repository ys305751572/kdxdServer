<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/17
  Time: 15:37
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
    <title>商品列表</title>
    <%@ include file="inc/css.jsp" %>
</head>

<body>
<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">商品列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="admin/product/add" target="_blank" class="btn btn-outline btn-primary btn-lg" role="button">添加商品</a>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">

                        <div class="table-responsive">

                            <table class="table table-striped table-bordered table-hover" id="dataTables">
                                <colgroup>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="$bluemobi.checkAll(this)" class="checkall"/></th>
                                    <th>商品名称</th>
                                    <th>商品编号</th>
                                    <th>优惠价</th>
                                    <th>类型</th>
                                    <th>产区</th>
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


</div>
<!-- /#wrapper -->

<%@ include file="inc/footer.jsp" %>
</body>

<script type="text/javascript">

    var productList = {
        v: {
            id: "productList",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                productList.fn.dataTableInit();
                // 查询
                $("#c_search").click(function () {
                    productList.v.dTable.ajax.reload();
                })

                $("#delete").click(function () {
                    var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                    var ids = checkBox.getInputId();
                    productList.fn.deleteRow(checkBox, ids)
                })


            },
            dataTableInit: function () {
                productList.v.dTable = $bluemobi.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching":false,
                    "ordering": false,
                    "ajax": {
                        "url": "admin/product/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "name"},
                        {"data": "code"},
                        {"data": "price"},
                        {"data": "productType.name"},
                        {"data": "productArea.name"},
                        {"data": "createDate"},
                        {"data": ""}
                    ],
                    "columnDefs": [
                        {
                            "data": null,
                            "defaultContent": "<button type='button'  title='上架商品' class='btn btn-circle settingAdded'>" +
                            "<i class='fa fa-recycle'></i>" +
                            "</button>" +
                            "&nbsp;&nbsp;" +
                            "<a  title='编辑' target='_blank' class='btn btn-primary btn-circle edit'>" +
                            "<i class='fa fa-edit'></i>" +
                            "</a>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button'  title='删除' class='btn btn-danger btn-circle delete'>" +
                            "<i class='fa fa-bitbucket'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        productList.v.list.push(data);
                        $('td', row).eq(0).html("<input type='checkbox' value=" + data.id + ">");
                        if(data.isAdded){
                            $(row).addClass("success")
                            $('td', row).eq(1).prepend("<span style='color: red'>【上架】</span>");
                            $('td', row).last().find(".settingAdded").addClass("btn-default");
                            $('td', row).last().find(".settingAdded").attr("title", "下架商品")
                        }else{
                            $('td', row).last().find(".settingAdded").addClass("btn-info");
                            $('td', row).last().find(".settingAdded").attr("title", "上架商品")
                        }
                    },
                    rowCallback: function (row, data) {
                        var items = productList.v.list;
                        $('td', row).last().find(".settingAdded").click(function(){
                            productList.fn.settingAdded(data);
                        })

                        $('td', row).last().find(".edit").attr("href",'admin/product/add?id='+data.id)

                        $('td', row).last().find(".delete").click(function () {
                            var checkbox = $('td', row).first().find("input[type='checkbox']");
                            productList.fn.deleteRow(checkbox, [data.id]);
                        })
                    },
                    "fnServerParams": function (aoData) {
                        aoData.typeId = $("#typeId").val();
                        aoData.isAdded = $("#isAdded").val();
                    },
                    "fnDrawCallback": function (row) {
                        $bluemobi.uiform();
                    }
                });
            },
            settingAdded:function(data){
                $bluemobi.ajax("admin/product/setting/isAdded", {
                    id: data.id,
                    isAdded: data.isAdded
                }, function (result) {
                    productList.fn.responseComplete(result,true);
                })
            },
            deleteRow: function (checkBox, ids) {
                if (ids.length > 0) {
                    $bluemobi.optNotify(function () {
                        $bluemobi.ajax("admin/product/delete", {ids: JSON.stringify(ids)}, function (result) {
                            if (result.status == "0") {
                                $bluemobi.notify(result.msg, "success");
                                productList.v.dTable.ajax.reload();

                            } else {
                                $bluemobi.notify(result.msg, "error");
                            }
                        })
                    });
                }
            },
            responseComplete: function (result,action) {
                if (result.status == "0") {
                    if(action){
                        productList.v.dTable.ajax.reload(null, false);
                    }else{
                        productList.v.dTable.ajax.reload();
                    }
                    $bluemobi.notify(result.msg, "success");
                } else {
                    $bluemobi.notify(result.msg, "error");
                }
            }
        }
    }

    $(document).ready(function () {
        productList.fn.init();
    });



</script>


</html>