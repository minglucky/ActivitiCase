<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>Zioer-Activiti示例</title>
<link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
<script language=JavaScript>

</script>
</head>

<body class="ContentBody">
<form action="add" method="post" name="fom" id="fom">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >费用报销管理（业务表单）-待办工作</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#FFFFEE" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="5" align="center" style="font-size:16px">当前用户办理工作列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="10%" height="30">任务ID</td>
					<td width="20%">当前节点</td>
                    <td width="20%">办理人</td>
                    <td width="36%">创建时间</td>
					<td width="17%">操作</td>
                  </tr>
                    <c:forEach items="${list}" var="var" varStatus="vs">
                        <tr  <c:if test="${vs.count%2==0}">bgcolor="#AAAABB"</c:if> align="left" >
                            <td >${var.id}</td>
                            <td  height="30">${var.name}</td>
                            <td >${var.assignee}</td>
                            <td ><fmt:formatDate value="${var.createTime}" type="both"/></td>
                            <td >
                                <c:choose>
                                    <c:when test="${empty var.assignee}">
                                        <a href="./claim/${var.id}">签收</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="./startform/${var.id}">办理</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
            </table></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
	 </td>
  </tr>

</table>
</div>
</form>
</body>
</html>
