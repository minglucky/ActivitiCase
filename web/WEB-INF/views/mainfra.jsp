<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 1px;
	margin-right: 0px;
	margin-bottom: 0px;
}
html { overflow-x: ; overflow-y: ; border:0;} 
-->
</style>
</head>

<body class="ContentBody">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >待办工作</th>
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
                    	<td height="22" colspan="7" align="center" style="font-size:16px">当前用户办理工作列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="10%" height="30">所属流程</td>
					<td width="10%">任务Id</td>
					<td width="10%">当前节点</td>
                    <td width="10%">办理人</td>
                    <td width="15%">创建时间</td>
					<td >操作</td>
                  </tr>
                  <c:forEach items="${tasklist}" var="var" varStatus="vs">
                  <tr  <c:if test="${vs.count%2==0}">bgcolor="#AAAABB"</c:if> align="left" >
				    <td>${var.processDefinitionName}</td>
				    <td height="30">${var.taskId}</td>
				    <td height="30">${var.taskName}</td>
					<td >${var.assignee}</td>                    
					<td ><fmt:formatDate value="${var.createTime}" type="both"/></td>                    
					<td >
					<c:choose>
						<c:when test="${empty var.assignee}">
							<a href="../${var.processDefinitionKey}/claim/${var.taskId}">签收</a>
						</c:when>
						<c:otherwise>
							<a href="../${var.processDefinitionKey}/startform/${var.taskId}">办理</a>
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
<br>
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >我的历史</th>
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
                    	<td height="22" colspan="11" align="center" style="font-size:16px">我的工作历史列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
                    <td width="10%">所属流程</td>
				    <td width="7%" height="30">任务ID</td>
					<td width="7%">实例ID</td>
					<td width="10%">上级流程名称(实例Id)</td>
                    <td width="15%">开始时间</td>
					<td width="15%">结束时间</td>
					<td width="">当前节点</td>
					<td >操作</td>
                  </tr>
                  <c:forEach items="${hlist}" var="var" varStatus="vs">
                  <tr  <c:if test="${vs.count%2==0}">bgcolor="#AAAABB"</c:if> align="left" >
				    <td  height="30">${var.ProcessDefinitionName}</td>
				    <td >${var.taskId}</td>
				    <td  height="30">${var.processInstanceId}</td>
				    <td  height="30">${var.superProcessDefinitionName}(${var.superProcessInstanceId})</td>
					<td ><fmt:formatDate value="${var.startTime}" type="both"/></td>                    
					<td ><fmt:formatDate value="${var.endTime}" type="both"/></td>                    
					<td >${var.name}</td>  
					<td ><a href="../${var.processDefinitionKey}/hview/${var.processInstanceId}">查看</a></td>                    
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
</body>
</html>
