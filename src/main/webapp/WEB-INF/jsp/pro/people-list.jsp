<%--
  Created by IntelliJ IDEA.
  User: wangbin
  Date: 2015/8/17
  Time: 15:37
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
    <title>抢购人员列表</title>
    <%@ include file="../inc/css.jsp" %>
</head>

<body>
<div id="posts" class="wrapper">

    <%@ include file="../inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">抢购人员列表</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <form class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <label>用户手机：</label>
                            <input type="text" class="form-control" value="" id="mobile" name="mobile" maxlength="20"
                                   placeholder="请输入用户手机">
                        </div>
                        <div class="form-group">
                            <label>是否缴费：</label>
                            <select class="form-control input-sm" id="isPay">
                                <option value="" selected="selected">全部</option>
                                <option value="0">是</option>
                                <option value="1">否</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>是否使用必中券：</label>
                            <select class="form-control input-sm" id="isUseCoupons">
                                <option value="" selected="selected">全部</option>
                                <option value="0">未使用</option>
                                <option value="1">已使用</option>
                            </select>
                        </div>
                        <button type="button" id="c_search" class="btn btn-info btn-sm">查询</button>
                    </form>
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
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="$bluemobi.checkAll(this)" class="checkall"/>
                                    </th>
                                    <th>用户手机</th>
                                    <th>抢购时间</th>
                                    <th>是否使用必中券</th>
                                    <th>抢购结果</th>
                                    <th>缴费情况</th>
                                    <th>抢购结果</th>
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

<%@ include file="../inc/footer.jsp" %>
</body>
<script type="text/javascript">
    Date.prototype.format = function (format) {
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        ;
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        ;
        return format;
    };
</script>
<script type="text/javascript">

    var kuserList = {
        v: {
            id: "kuserList",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                kuserList.fn.dataTableInit();
                // 查询
                $("#c_search").click(function () {
                    kuserList.v.dTable.ajax.reload();
                })
            },
            dataTableInit: function () {
                kuserList.v.dTable = $bluemobi.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ordering": false,
                    "ajax": {
                        "url": "admin/pro/kuserlist",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "user.mobile"},
                        {
                            "data": "createDate", render: function (data) {
                            return new Date(data).format("yyyy-MM-dd hh:mm:ss")
                        }
                        },
                        {
                            "data": "isUserCoupons",
                            render : function(data) {
                                if(data == 0) {
                                    return "否";
                                }
                                else {
                                    return "是";
                                }
                            }
                        },
                        {
                            "data": "resultStatus",
                            render: function (data) {
                                if(data == 0) {
                                    return "抢购成功";
                                }
                                else {
                                    return "抢购失败";
                                }
                            }
                        },
                        {
                            "data": "payResult"

                        },
                        {
                            "data" : "result"
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        kuserList.v.list.push(data);
                        $('td', row).eq(0).html("<input type='checkbox' value=" + data.id + ">");
//                        if(data.status == 0){
//                            $(row).addClass("success")
//
//                            $('td', row).last().find(".settingAdded").addClass("btn-default");
//                            $('td', row).last().find(".settingAdded").attr("title", "禁用")
//                        }else{
//                            $('td', row).last().find(".settingAdded").addClass("btn-info");
//                            $('td', row).last().find(".settingAdded").attr("title", "启用");
//                        }
                    },
                    rowCallback: function (row, data) {
//                        var items = kuserList.v.list;
//                        $('td', row).last().find(".edit").attr("href", 'admin/pro/edit?id=' + data.id);
//                        $('td', row).last().find(".kuserindex").attr("href", 'admin/pro/kuserindex?id=' + data.id);
//
//                        $('td', row).last().find(".delete").click(function() {
//                            kuserList.fn.delete(data.id);
//                        });
//                        $('td', row).last().find(".end").click(function() {
//                            kuserList.fn.end(data.id);
//                        });

                    },
                    "fnServerParams": function (aoData) {
                        aoData.mobile = $("#mobile").val();
                        aoData.isPay = $("#isPay").val();
                        aoData.isUseCoupons = $("#isUseCoupons").val();
                    },
                    "fnDrawCallback": function (row) {
                        $bluemobi.uiform();
                    }
                });
            },
            delete : function(id) {
                $bluemobi.optNotify(function () {
                    $.post("admin/pro/delete",{id:JSON.stringify(id)},function(result) {
                        kuserList.fn.responseComplete(result);
                    })
                },"确定删除吗？","确定");
            },
            end : function (id) {
                $bluemobi.optNotify(function () {
                    $.post("admin/pro/tosnapup/end",{id:JSON.stringify(id)},function(result) {
                        kuserList.fn.responseComplete(result);
                    })
                },"确定结束吗？","确定");
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        kuserList.v.dTable.ajax.reload(null, false);
                    } else {
                        kuserList.v.dTable.ajax.reload();
                    }
                    $bluemobi.notify(result.msg, "success");
                } else {
                    $bluemobi.notify(result.msg, "error");
                }
            }
        }
    }
    $(document).ready(function () {
        kuserList.fn.init();
    });
</script>
</html>