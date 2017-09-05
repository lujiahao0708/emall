<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/pages/common/header.jsp" %>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <!--左侧菜单-->
        <%@include file="/WEB-INF/pages/common/menu.jsp" %>
        <!--顶部导航栏 -->
        <%@include file="/WEB-INF/pages/common/top.jsp" %>

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Tables
                            <small>Some examples to get you started</small>
                        </h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search for...">
                                <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>商品列表
                                    <small>Users</small>
                                </h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <%--搜索框--%>
                                            <div id="datatable_filter" class="dataTables_filter"><label>Search:<input
                                                    type="search" class="form-control input-sm" placeholder=""
                                                    aria-controls="datatable"></label></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="datatable" class="table table-striped table-bordered dataTable no-footer"
                                                   role="grid" aria-describedby="datatable_info">
                                                <thead>
                                                <tr role="row">
                                                    <th class="sorting_asc" tabindex="0" aria-controls="datatable" rowspan="1"
                                                        colspan="1" aria-sort="ascending"
                                                        aria-label="Name: activate to sort column descending"
                                                        style="width: 265px;">Name
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                        colspan="1" aria-label="Position: activate to sort column ascending"
                                                        style="width: 422px;">Position
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                        colspan="1" aria-label="Office: activate to sort column ascending"
                                                        style="width: 199px;">Office
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                        colspan="1" aria-label="Age: activate to sort column ascending"
                                                        style="width: 112px;">Age
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                        colspan="1" aria-label="Start date: activate to sort column ascending"
                                                        style="width: 199px;">Start date
                                                    </th>
                                                    <th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
                                                        colspan="1" aria-label="Salary: activate to sort column ascending"
                                                        style="width: 155px;">Salary
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <#list allItem as item>
                                                    <tr role="row" class="odd">
                                                        <td class="sorting_1">Airi Satou${item.id}</td>
                                                        <td>Accountant${item.title}</td>
                                                        <td>Tokyo${item.sellPoint}</td>
                                                        <td>33${item.price}</td>
                                                        <td>2008/11/28${item.num}</td>
                                                        <td>
                                                            <div>
                                                                <button type="button" class="btn btn-success">修改</button>
                                                                <button type="button" class="btn btn-warning">删除</button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </#list>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <div class="dataTables_info" id="datatable_info" role="status" aria-live="polite">
                                                Showing 1 to 10 of 57 entries
                                            </div>
                                        </div>
                                        <div class="col-sm-7">
                                            <div class="dataTables_paginate paging_simple_numbers" id="datatable_paginate">
                                                <ul class="pagination">
                                                    <li class="paginate_button previous disabled" id="datatable_previous"><a
                                                            href="#" aria-controls="datatable" data-dt-idx="0" tabindex="0">Previous</a>
                                                    </li>
                                                    <li class="paginate_button active"><a href="#" aria-controls="datatable"
                                                                                          data-dt-idx="1" tabindex="0">1</a>
                                                    </li>
                                                    <li class="paginate_button "><a href="#" aria-controls="datatable"
                                                                                    data-dt-idx="2" tabindex="0">2</a></li>
                                                    <li class="paginate_button "><a href="#" aria-controls="datatable"
                                                                                    data-dt-idx="3" tabindex="0">3</a></li>
                                                    <li class="paginate_button "><a href="#" aria-controls="datatable"
                                                                                    data-dt-idx="4" tabindex="0">4</a></li>
                                                    <li class="paginate_button "><a href="#" aria-controls="datatable"
                                                                                    data-dt-idx="5" tabindex="0">5</a></li>
                                                    <li class="paginate_button "><a href="#" aria-controls="datatable"
                                                                                    data-dt-idx="6" tabindex="0">6</a></li>
                                                    <li class="paginate_button next" id="datatable_next"><a href="#"
                                                                                                            aria-controls="datatable"
                                                                                                            data-dt-idx="7"
                                                                                                            tabindex="0">Next</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--底部导航栏-->
        <%@include file="/WEB-INF/pages/common/footer.jsp" %>

    </div>
</div>
<script src="${ctx}resources/js/jquery.min.js"></script>
<script src="${ctx}resources/js/bootstrap.min.js"></script>
<script src="${ctx}resources/js/fastclick.js"></script>
<script src="${ctx}resources/js/nprogress.js"></script>
<script src="${ctx}resources/js/Chart.min.js"></script>
<script src="${ctx}resources/js/gauge.min.js"></script>
<script src="${ctx}resources/js/bootstrap-progressbar.min.js"></script>
<script src="${ctx}resources/js/icheck.min.js"></script>
<script src="${ctx}resources/js/skycons.js"></script>
<script src="${ctx}resources/js/jquery.flot.js"></script>
<script src="${ctx}resources/js/jquery.flot.pie.js"></script>
<script src="${ctx}resources/js/jquery.flot.time.js"></script>
<script src="${ctx}resources/js/jquery.flot.stack.js"></script>
<script src="${ctx}resources/js/jquery.flot.resize.js"></script>
<script src="${ctx}resources/js/jquery.flot.orderBars.js"></script>
<script src="${ctx}resources/js/jquery.flot.spline.min.js"></script>
<script src="${ctx}resources/js/curvedLines.js"></script>
<script src="${ctx}resources/js/date.js"></script>
<script src="${ctx}resources/js/jquery.vmap.min.js"></script>
<script src="${ctx}resources/js/jquery.vmap.world.js"></script>
<script src="${ctx}resources/js/jquery.vmap.sampledata.js"></script>
<script src="${ctx}resources/js/moment.min.js"></script>
<script src="${ctx}resources/js/daterangepicker.js"></script>
<script src="${ctx}resources/js/custom.min.js"></script>
</body>
</html>
