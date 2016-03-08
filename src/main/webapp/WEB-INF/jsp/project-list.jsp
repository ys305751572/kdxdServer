<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta name="format-detection" content="telephone=no">
        <meta charset="UTF-8">

        <meta name="description" content="Violate Responsive Admin Template">
        <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

        <title>Super Admin Responsive Template</title>
            
        <!-- CSS -->
        <link href="${contextPath }/static/html/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/animate.min.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/font-awesome.min.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/form.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/calendar.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/media-player.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/style.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/icons.css" rel="stylesheet">
        <link href="${contextPath }/static/html/css/generics.css" rel="stylesheet"> 
    </head>
    <body id="skin-blur-violate">
        <header id="header" class="media">
            <a href="" id="menu-toggle"></a> 
            <a class="logo pull-left" href="index.html">SUPER ADMIN 1.0</a>
            
            <div class="media-body">
                <div class="media" id="top-menu">
                    <div class="pull-left tm-icon">
                        <a data-drawer="messages" class="drawer-toggle" href="">
                            <i class="sa-top-message"></i>
                            <i class="n-count animated">5</i>
                            <span>Messages</span>
                        </a>
                    </div>
                    <div class="pull-left tm-icon">
                        <a data-drawer="notifications" class="drawer-toggle" href="">
                            <i class="sa-top-updates"></i>
                            <i class="n-count animated">9</i>
                            <span>Updates</span>
                        </a>
                    </div>
                    <div id="time" class="pull-right">
                        <span id="hours"></span>
                        :
                        <span id="min"></span>
                        :
                        <span id="sec"></span>
                    </div>

                    <div class="media-body">
                        <input type="text" class="main-search">
                    </div>
                </div>
            </div>
        </header>
        
        <div class="clearfix"></div>
        
        <section id="main" class="p-relative" role="main">
            
            <!-- Sidebar -->
            <aside id="sidebar">
                
                <!-- Sidbar Widgets -->
                <div class="side-widgets overflow">
                    <!-- Profile Menu -->
                    <div class="text-center s-widget m-b-25 dropdown" id="profile-menu">
                        <a href="" data-toggle="dropdown">
                            <img class="profile-pic animated" src="${contextPath }/static/html/img/profile-pic.jpg" alt="">
                        </a>
                        <ul class="dropdown-menu profile-menu">
                            <li><a href="">My Profile</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>
                            <li><a href="">Messages</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>
                            <li><a href="">Settings</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>
                            <li><a href="">Sign Out</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>
                        </ul>
                        <h4 class="m-0">Malinda Hollaway</h4>
                        @malinda-h
                    </div>
                    
                    <!-- Calendar -->
                    <div class="s-widget m-b-25">
                        <div id="sidebar-calendar"></div>
                    </div>
                    
                    <!-- Feeds -->
                    <div class="s-widget m-b-25">
                        <h2 class="tile-title">
                           News Feeds
                        </h2>
                        
                        <div class="s-widget-body">
                            <div id="news-feed"></div>
                        </div>
                    </div>
                    
                    <!-- Projects -->
                    <div class="s-widget m-b-25">
                        <h2 class="tile-title">
                            Projects on going
                        </h2>
                        
                        <div class="s-widget-body">
                            <div class="side-border">
                                <small>Joomla Website</small>
                                <div class="progress progress-small">
                                     <a href="#" data-toggle="tooltip" title="" class="progress-bar tooltips progress-bar-danger" style="width: 60%;" data-original-title="60%">
                                          <span class="sr-only">60% Complete</span>
                                     </a>
                                </div>
                            </div>
                            <div class="side-border">
                                <small>Opencart E-Commerce Website</small>
                                <div class="progress progress-small">
                                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-info" style="width: 43%;" data-original-title="43%">
                                          <span class="sr-only">43% Complete</span>
                                     </a>
                                </div>
                            </div>
                            <div class="side-border">
                                <small>Social Media API</small>
                                <div class="progress progress-small">
                                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-warning" style="width: 81%;" data-original-title="81%">
                                          <span class="sr-only">81% Complete</span>
                                     </a>
                                </div>
                            </div>
                            <div class="side-border">
                                <small>VB.Net Software Package</small>
                                <div class="progress progress-small">
                                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-success" style="width: 10%;" data-original-title="10%">
                                          <span class="sr-only">10% Complete</span>
                                     </a>
                                </div>
                            </div>
                            <div class="side-border">
                                <small>Chrome Extension</small>
                                <div class="progress progress-small">
                                     <a href="#" data-toggle="tooltip" title="" class="tooltips progress-bar progress-bar-success" style="width: 95%;" data-original-title="95%">
                                          <span class="sr-only">95% Complete</span>
                                     </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Side Menu -->
                <ul class="list-unstyled side-menu">
                    <li class="active">
                        <a class="sa-side-table" href="tables.html">
                            <span class="menu-item">Tables</span>
                        </a>
                    </li>
                </ul>
            </aside>
        
            <!-- Content -->
            <section id="content" class="container">
                
                <!-- Breadcrumb -->
                <ol class="breadcrumb hidden-xs">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Library</a></li>
                    <li class="active">Data</li>
                </ol>
                
                <h4 class="page-title">TABLES</h4>
                <!-- Table Hover -->
                <div class="block-area" id="tableHover">
                	<header class="listview-header media">
                        <input type="checkbox" class="pull-left list-parent-check" value="">
                            
                        <ul class="list-inline pull-right m-t-5 m-b-0">
                            <li>
                                <a href="" title="Previous" class="tooltips">
                                    <i class="sa-list-back"></i>
                                </a>
                            </li>
                            <li>
                                <a href="" title="Next" class="tooltips">
                                    <i class="sa-list-forwad"></i>
                                </a>
                            </li>
                        </ul>
                        
                        <ul class="list-inline list-mass-actions pull-left">
                            <li>
                                <a data-toggle="modal" href="#form-modal" title="新增" class="tooltips">
                                    <i class="sa-list-add"></i>
                                </a>
                            </li>
                            <li>
                                <a href="" title="刷新" class="tooltips">
                                    <i class="sa-list-refresh"></i>
                                </a>
                            </li>
                            <li class="show-on" style="display: none;">
                                <a href="" title="Move" class="tooltips">
                                    <i class="sa-list-move"></i>
                                </a>
                            </li>
                            <li class="show-on" style="display: none;">
                                <a href="" title="Delete" class="tooltips">
                                    <i class="sa-list-delete"></i>
                                </a>
                            </li>
                        </ul>

                        <input class="input-sm col-md-4 pull-right message-search" type="text" placeholder="Search....">
                        <div class="clearfix"></div>
                    </header>
                    <div class="table-responsive overflow">
                        <table class="table table-bordered table-hover tile" id="dataTables">
                            <thead>
                                <tr>
                                	<th><input type="checkbox" onclick="" class="checkall"/></th>
                                    <th>Username</th>
                                    <th>Create Date</th>
                                    <th>Update Date</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                 <!-- Modal -->
                    <div class="modal fade" id="form-modal" tabindex="-1" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="inputName4" class="col-md-2 control-label">Name</label>
                                            <div class="col-md-9">
                                                <input type="text" class="form-control input-sm" id="inputName4" placeholder="...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail4" class="col-md-2 control-label">Email</label>
                                            <div class="col-md-9">
                                                <input type="email" class="form-control input-sm" id="inputEmail4" placeholder="...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail4" class="col-md-2 control-label">Email</label>
                                            <div class="col-md-9">
                                                <input type="email" class="form-control input-sm" id="inputEmail4" placeholder="...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail4" class="col-md-2 control-label">Email</label>
                                            <div class="col-md-9">
                                                <input type="email" class="form-control input-sm" id="inputEmail4" placeholder="...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail4" class="col-md-2 control-label">Email</label>
                                            <div class="col-md-9">
                                                <input type="email" class="form-control input-sm" id="inputEmail4" placeholder="...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail4" class="col-md-2 control-label">Email</label>
                                            <div class="col-md-9">
                                                <input type="email" class="form-control input-sm" id="inputEmail4" placeholder="...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputEmail4" class="col-md-2 control-label">Email</label>
                                            <div class="col-md-9">
                                                <input type="email" class="form-control input-sm" id="inputEmail4" placeholder="...">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputMessage3" class="col-md-2 control-label">Message</label>
                                            <div class="col-md-9">
                                                <textarea class="form-control auto-size input-sm" id="inputMessage3" placeholder="..."></textarea>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-sm btn-alt">Save changes</button>
                                    <button type="button" class="btn btn-sm btn-alt" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
            </section>
            <br/><br/>
        </section>
        
        <!-- Javascript Libraries -->
        <!-- jQuery -->
        <script src="${contextPath }/static/html/js/jquery.min.js"></script> <!-- jQuery Library -->
        <!-- Bootstrap -->
        <script src="${contextPath }/static/html/js/bootstrap.min.js"></script>
        <!-- UX -->
        <script src="${contextPath }/static/html/js/scroll.min.js"></script> <!-- Custom Scrollbar -->
        <!-- Other -->
        <script src="${contextPath }/static/html/js/calendar.min.js"></script> <!-- Calendar -->
        <script src="${contextPath }/static/html/js/feeds.min.js"></script> <!-- News Feeds -->
        <!-- All JS functions -->
        <script src="${contextPath }/static/html/js/functions.js"></script>
        <script src="${contextPath }/static/html/js/icheck.js"></script>
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
                            "sPaginationType": "full_numbers", 
                            "bLengthChange":false,
                            "ajax": {
                                "url": "${contextPath}/admin/member/list",
                                "type": "POST"
                            },
                            "columns": [
                                {"data": "id"},
                                {"data": "username"},
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
                            "rowCallback": function (row, data) {
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

