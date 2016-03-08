<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/11
  Time: 14:41
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
    <title>入驻商家</title>
    <%@ include file="inc/css.jsp" %>

</head>

<body>

<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">入驻商家</h1>
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
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="$bluemobi.checkAll(this)" class="checkall"/></th>
                                    <th>商家编号</th>
                                    <th>酒店名称</th>
                                    <th>联系方式</th>
                                    <th>地址</th>
                                    <th>微信昵称</th>
                                    <th>创建日期</th>
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
    <div class="modal fade" id="optionsBusinessModal" tabindex="-1" role="dialog" aria-labelledby="bussinessModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="optionsBusinessModalLabel">操作</h4>
                </div>
                <div class="modal-body">
                    <form id="optionsBusinessForm" method="post" action="admin/business/options" class="form-horizontal" role="form">
                        <input type="hidden" name="businessId" value="">

                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">用户名:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="username" name="username" maxlength="20"
                                       data-rule="required" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码:</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" id="password" name="password" maxlength="20"
                                       data-rule="required" placeholder="请输入密码">
                            </div>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="saveOptions" class="btn btn-primary">确认</button>
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
</body>


<script type="text/javascript">

    var business = {
        v: {
            id: "business",
            list :[],
            dTable: null
        },
        fn: {
            init: function () {
                business.fn.dataTableInit();

                $("#saveOptions").click(function(){
                    business.fn.optionsSave();
                })

            },
            dataTableInit: function () {
                business.v.dTable = $bluemobi.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching":false,
                    "ordering": false,
                    "ajax": {
                        "url": "admin/business/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "code"},
                        {"data": "name"},
                        {"data": "contact"},
                        {"data": "address"},
                        {
                            "data": "wxUser.nickname",
                            "defaultContent":"",
                            "targets": -1
                        },
                        {"data": "createDate"},
                        {
                            "data": null,
                            "defaultContent":
                            "<button type='button'  title='设置账户' class='btn btn-primary btn-circle options'>" +
                            "<i class='fa fa-cog'></i>" +
                            "</button>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button'  title='删除' class='btn btn-danger btn-circle delete'>" +
                            "<i class='fa fa-bitbucket'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        business.v.list.push(data);
                        $('td', row).eq(0).html("<input type='checkbox' value=" + data.id + ">");

                        if(data.code!=null&&data.code!=""){
                            $(row).addClass("success");
                            $('td', row).last().find(".options").hide()
                        }
                    },
                    rowCallback: function (row, data) {
                        var items = business.v.list;
                        $('td', row).last().find(".options").click(function () {
                            var checkbox = $('td', row).first().find("input[type='checkbox']");
                            business.fn.options(data.id);
                        });
                        $('td', row).last().find(".delete").click(function () {
                            business.fn.deleteRow([data.id]);
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
                        $bluemobi.ajax("admin/business/delete", {ids: JSON.stringify(ids)}, function (result) {
                            if (result.status == "0") {
                                $bluemobi.notify(result.msg, "success");
                                business.v.dTable.ajax.reload();

                            } else {
                                $bluemobi.notify(result.msg, "error");
                            }
                        })
                    });
                }
            },
            options:function(id){
                business.fn.showModal("optionsBusinessModal","设置商户账号");
                $("#optionsBusinessForm input[name='businessId']").val(id);
            },
            optionsSave:function(){
                if(!$('#optionsBusinessForm').isValid()) {
                    return false;
                };
                $("#optionsBusinessForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        business.fn.responseComplete(result,true);
                        if (result.status == "0") {
                            $("#optionsBusinessModal").modal("hide");
                        }
                    }
                })
            },
            responseComplete: function (result,action) {
                if (result.status == "0") {
                    if(action){
                        business.v.dTable.ajax.reload(null, false);
                    }else{
                        business.v.dTable.ajax.reload();
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
        business.fn.init();
    });

</script>


</html>