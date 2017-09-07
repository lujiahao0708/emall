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
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <div class="dataTables_info" id="datatable_info" role="status" aria-live="polite">
                                                第 ${pageInfo.pageNum} 页,共 ${pageInfo.pages} 页,共${pageInfo.total}条数据
                                            </div>
                                        </div>
                                        <div class="col-sm-7">
                                            <div class="dataTables_paginate paging_simple_numbers" id="datatable_paginate">
                                                <ul class="pagination">
                                                    <c:if test="${pageInfo.hasPreviousPage}">
                                                        <li><a href="?pageNum=1&pageSize=${pageInfo.pageSize}">首页</a></li>
                                                        <li class="paginate_button previous" id="datatable_previous">
                                                            <a href="?pageNum=${pageInfo.pageNum - 1}&pageSize=${pageInfo.pageSize}" aria-controls="datatable" data-dt-idx="0" tabindex="0">上一页</a>
                                                        </li>
                                                    </c:if>
                                                    <c:if test="${!pageInfo.hasPreviousPage}">
                                                        <li class="disabled"><a href="#">首页</a></li>
                                                        <li class="disabled"><a href="#">上一页</a></li>
                                                    </c:if>

                                                    <c:forEach var="i" begin="${pageInfo.firstPage}" end="${pageInfo.lastPage}">
                                                        <c:choose>
                                                            <c:when test="${i == pageInfo.pageNum}">
                                                                <li class="paginate_button active">
                                                                    <a href="?pageNum=${i}&pageSize=${pageInfo.pageSize}" aria-controls="datatable" data-dt-idx="${i}" tabindex="0">${i}</a>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="paginate_button">
                                                                    <a href="?pageNum=${i}&pageSize=${pageInfo.pageSize}" aria-controls="datatable" data-dt-idx="${i}" tabindex="0">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>

                                                    <c:if test="${pageInfo.hasNextPage}">
                                                        <li class="paginate_button next" id="datatable_next">
                                                            <a href="?pageNum=${pageInfo.pageNum + 1}&pageSize=${pageInfo.pageSize}" aria-controls="datatable" data-dt-idx="${pageInfo.pages}" tabindex="0">下一页</a>
                                                        </li>
                                                        <li><a href="?pageNum=${pageInfo.pages}&pageSize=${pageInfo.pageSize}">尾页</a></li>
                                                    </c:if>
                                                    <c:if test="${!pageInfo.hasNextPage}">
                                                        <li class="disabled"><a href="#">下一页</a></li>
                                                        <li class="disabled"><a href="#">尾页</a></li>
                                                    </c:if>
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