<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--公司logo--%>
<%--<link rel="shortcut icon" href="//images.01zhuanche.com/statics/web/images/favicon.jpg" type="image/x-icon">--%>
<%
String path = request.getContextPath();
Integer serverPort = request.getServerPort();
String basePath;
if(serverPort==80){
	basePath = "//"+request.getServerName()+path+"/";
}else{
   basePath = "//"+request.getServerName()+":"+request.getServerPort()+path+"/";
}
%>
<c:set var="ctx" value="<%=basePath%>"/>
<script>
    var basePath ='<%=basePath%>';
</script>