<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
        <!-- top navigation -->
        <%@include file="/WEB-INF/pages/common/top.jsp" %>

        <!-- page content -->
        <div class="right_col">
            <div class="row">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Users
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
                            <div class="x_content">
                                <div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table id="datatable" class="table table-striped table-bordered">
                                                <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Position</th>
                                                    <th>Office</th>
                                                    <th>Age</th>
                                                    <th>Start date</th>
                                                    <th>Salary</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${pageInfo.list}" var="item">
                                                    <tr role="row" class="odd">
                                                        <td class="sorting_1">${item.id}</td>
                                                        <td>${item.title}</td>
                                                        <td>${item.sellPoint}</td>
                                                        <td>${item.price}</td>
                                                        <td>${item.num}</td>
                                                        <td>
                                                            <button type="button" class="btn btn-success">修改</button>
                                                            <button type="button" class="btn btn-warning">删除</button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div><tags:pagination paginationSize="10" pageInfo="${pageInfo}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

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