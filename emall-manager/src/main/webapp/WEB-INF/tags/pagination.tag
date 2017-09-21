<%@ tag import="org.apache.commons.lang3.StringUtils" %>
<%@ tag pageEncoding="UTF-8"%>
<%--<%@ attribute name="page" type="com.lujiahao.manager.utils.Page" required="true"%>--%>
<%@ attribute name="pageInfo" type="com.github.pagehelper.PageInfo" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    if(pageInfo  == null){
        return;
    }
    int current =  pageInfo.getPageNum();
    int begin = Math.max(1, current - paginationSize/2);
    int end = Math.min(begin + (paginationSize - 1), pageInfo.getPages());
    long pageTotalElements = pageInfo.getTotal();
    int pageSizes = pageInfo.getPageSize();
    request.setAttribute("current", current);
    request.setAttribute("begin", begin);
    request.setAttribute("end", end);
    request.setAttribute("pageSizes",pageSizes);
    request.setAttribute("pageTotalElements",pageTotalElements);
        String url  = (String) request.getAttribute("url");
        String showInfo  = (String) request.getAttribute("showInfo");
%>
<%
  if (StringUtils.isBlank(url)){
%>
<div class="" align="right" style="">
	<ul class="pagination">
		 <% if (pageInfo.isHasPreviousPage()){%>
               	<li><a href="?pageNum=1&pageSize=${pageSize}&${searchParams}">首页</a></li>
                <li><a href="?pageNum=${current-1}&pageSize=${pageSize}&${searchParams}">上一页</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">首页</a></li>
                <li class="disabled"><a href="#">上一页</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a href="?pageNum=${i}&pageSize=${pageSize}&${searchParams}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="?pageNum=${i}&pageSize=${pageSize}&${searchParams}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  	 <% if (pageInfo.isHasNextPage()){%>
               	<li><a href="?pageNum=${current+1}&pageSize=${pageSize}&${searchParams}">下一页</a></li>
                <li><a href="?pageNum=${page.totalPages}&pageSize=${pageSize}&${searchParams}">尾页</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">下一页</a></li>
                <li class="disabled"><a href="#">尾页&nbsp;</a></li>
         <%} %>
        <li class="disabled"><a style="padding-right:1px;">共${pageTotalElements}条记录，共${pageSizes}页 &nbsp;</a></li>
		 <%--<li class="disabled"><a style="padding-left:1px;"><input type="text" value="${page}"></a></li>--%>
	</ul>
</div>
<%
}else{
%>
<div class="" align="right" style="">
<ul class="pagination">
    <% if (pageInfo.isHasPreviousPage()){%>
    <li><a href="javascript:searchPage('<%=url%>?pageNum=1&pageSize=${pageSize}&&${searchParams}','<%=showInfo%>')">首页</a></li>
    <li><a href="javascript:searchPage('<%=url%>?pageNum=${current-1}&pageSize=${pageSize}&${searchParams}','<%=showInfo%>')">上一页</a></li>
    <%}else{%>
    <li class="disabled"><a href="#">首页</a></li>
    <li class="disabled"><a href="#">上一页</a></li>
    <%} %>

    <c:forEach var="i" begin="${begin}" end="${end}">
        <c:choose>
            <c:when test="${i == current}">
                <li class="active"><a href="javascript:searchPage('<%=url%>?pageNum=${i}&pageSize=${pageSize}&sortType=${sortType}&sortField=${sortField}&${searchParams}','<%=showInfo%>')">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="javascript:searchPage('<%=url%>?pageNum=${i}&pageSize=${pageSize}&sortType=${sortType}&sortField=${sortField}&${searchParams}','<%=showInfo%>')">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <% if (pageInfo.isHasNextPage()){%>
    <li><a href="javascript:searchPage('<%=url%>?pageNum=${current+1}&pageSize=${pageSize}&${searchParams}','<%=showInfo%>')">下一页</a></li>
    <li><a href="javascript:searchPage('<%=url%>?pageNum=${page.totalPages}&pageSize=${pageSize}&${searchParams}','<%=showInfo%>')">尾页</a></li>
    <%}else{%>
    <li class="disabled"><a href="#">下一页</a></li>
    <li class="disabled"><a href="#">尾页&nbsp;</a></li>
    <%} %>
    <li class="disabled"><a style="padding-right:1px;">共${pageTotalElements}条记录，共${pageSizes}页 &nbsp;</a></li>
    <%--<li class="disabled"><a style="padding-left:1px;"><input type="text" value="${page}"></a></li>--%>
</ul>
</div>

<%}%>
<script type="text/javascript">
</script>