<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/12
  Time: 10:17
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
    <title>商家账户管理</title>
    <%@ include file="inc/css.jsp" %>

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
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="$bluemobi.checkAll(this)" class="checkall"/></th>
                                    <th>商家编号</th>
                                    <th>酒店名称</th>
                                    <th>联系方式</th>
                                    <th>用户名</th>
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
    <div class="modal fade" id="updatePassWordModal" tabindex="-1" role="dialog" aria-labelledby="memberModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="updatePassWordModalLabel">操作</h4>
                </div>
                <div class="modal-body ">
                    <form id="updatePassWordForm" method="post" action="admin/member/change/password" class="form-horizontal" role="form">
                        <input type="hidden" name="memberId" value="">
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="password" name="password" maxlength="20"
                                       data-rule="required" placeholder="请输入密码">
                            </div>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="updatePassword" class="btn btn-primary">修改</button>
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

    var members = {
        v: {
            id: "members",
            list :[],
            dTable: null
        },
        fn: {
            init: function () {
                members.fn.dataTableInit();

                $("#saveOptions").click(function(){
                    members.fn.optionsSave();
                })

                $("#updatePassword").click(function(){
                    members.fn.savePassword();
                })


            },
            dataTableInit: function () {
                members.v.dTable = $bluemobi.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching":false,
                    "ordering": false,
                    "ajax": {
                        "url": "admin/member/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "business.code"},
                        {"data": "business.name"},
                        {"data": "business.contact"},
                        {"data": "username"},
                        {"data": "createDate"},
                        {
                            "data": null,
                            "defaultContent":
                            "<button type='button'  title='修改密码' class='btn btn-warning btn-circle updatePassword'>" +
                            "<i class='fa fa-key'></i>" +
                            "</button>"+
                            "&nbsp;&nbsp;" +
                            "<button type='button'  title='删除' class='btn btn-danger btn-circle delete'>" +
                                    "<i class='fa fa-bitbucket'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        members.v.list.push(data);
                        $('td', row).eq(0).html("<input type='checkbox' value=" + data.id + ">");
                    },
                    rowCallback: function (row, data) {
                        var items = members.v.list;
                        $('td', row).last().find(".delete").click(function () {
                            members.fn.deleteRow([data.id]);
                        });
                        $('td', row).last().find(".updatePassword").click(function () {
                            members.fn.updatePassword(data.id);
                        })

                        $('td', row).eq(2).html("<a href='admin/business/info?id="+data.business.id+"' target='_blank'>"+data.business.name+"</a>")
                    },
                    "fnServerParams": function (aoData) {

                    },
                    "fnDrawCallback": function (row) {
                        $bluemobi.uiform();
                    }
                });
            },
            infoShow: function (id) {
                $("#businessInfoModal").modal("show");
                var items = members.v.list;
                $.each(items, function (index, item) {
                    if (item.id == id) {
                        for (var key in item.business) {
                            var element = $("#businessInfoModal").find("#p_"+key);
                            if (element.length > 0) {
                                element.html(item.business[key]);
                            }
                        }
                    }
                });
            },
            updatePassword:function(id){
                members.fn.showModal("updatePassWordModal","修改密码");
                var items = members.v.list;
                $.each(items, function (index, item) {
                    if (item.id == id) {
                        $("#updatePassWordForm :input[name='memberId']").val(item["id"])
                    }
                })
            },
            savePassword:function(){
                if(!$('#updatePassWordForm').isValid()) {
                    return false;
                };
                $("#updatePassWordForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        members.fn.responseComplete(result,true);
                        if (result.status == "0") {
                            $("#updatePassWordModal").modal("hide");
                        }
                    }
                })
            },
            deleteRow: function (ids) {
                if (ids.length > 0) {
                    $bluemobi.optNotify(function () {
                        $bluemobi.ajax("admin/member/delete", {ids: JSON.stringify(ids)}, function (result) {
                            if (result.status == "0") {
                                $bluemobi.notify(result.msg, "success");
                                members.v.dTable.ajax.reload();

                            } else {
                                $bluemobi.notify(result.msg, "error");
                            }
                        })
                    });
                }
            },
            options:function(id){
                members.fn.showModal("optionsBusinessModal","设置商户账号");
                $("#optionsBusinessForm input[name='businessId']").val(id);
            },
            optionsSave:function(){
                if(!$('#optionsBusinessForm').isValid()) {
                    return false;
                };
                $("#optionsBusinessForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        members.fn.responseComplete(result,true);
                        if (result.status == "0") {
                            $("#optionsBusinessModal").modal("hide");
                        }
                    }
                })
            },
            responseComplete: function (result,action) {
                if (result.status == "0") {
                    if(action){
                        members.v.dTable.ajax.reload(null, false);
                    }else{
                        members.v.dTable.ajax.reload();
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
        members.fn.init();
    });
</script>


</html>