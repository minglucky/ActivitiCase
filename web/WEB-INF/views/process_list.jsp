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
<title></title>
<link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
<script language=JavaScript>

</script>
</head>

<body class="ContentBody">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >流程管理->列表</th>
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
                    	<td height="22" colspan="14" align="center" style="font-size:16px">流程列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">				    
					<td width="14%">ID</td>
					<td width="14%" height="30">key</td>
                    <td width="14%">部署ID</td>
					<td >名称</td>
					<td width="10%">版本</td>
					<td width="25%">操作</td>
                  </tr>
                  <c:forEach items="${data}" var="var" varStatus="vs">
                  <tr  <c:if test="${vs.count%2==0}">bgcolor="#AAAABB"</c:if> align="left" >
				    <td  height="30">${var.id}</td>
				    <td >${var.key}</td>				    
					<td >${var.deploymentId}</td>
					<td >${var.name}</td>       
					<td >${var.version}</td>                    
					<td >
<%--					<a href="<%=basePath%>process/resource/read?processDefinitionId=${var.id}&reType=xml">XML查看</a>&nbsp;--%>
					<a href="<%=basePath%>process/resource/read?processDefinitionId=${var.id}&reType=image">IMAGE查看</a>&nbsp;
					<a href="<%=basePath%>process/delete/${var.deploymentId}">删除</a></td>
                  </tr>
                  </c:forEach>
                  
                  <tr >
                   	<td height="22" colspan="4" align="center" style="font-size:16px">
                   		<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
						  <tr>
						    <td height="6"><img src="../images/spacer.gif" width="1" height="1" /></td>
						  </tr>
						  <tr>
						    <td height="33"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
						        <tr>
						          <td width="50%">共 <span class="right-text09">${totalPage}</span> 页 | 第 <span class="right-text09">${currentPage}</span> 页</td>
						          <td width="49%" align="right">[<a href="?page=1" class="right-font08">首页</a> | <a href="?page=${currentPage - 1}" class="right-font08">上一页</a> | <a href="?page=${currentPage + 1}" class="right-font08">下一页</a> | <a href="?page=${totalPage}" class="right-font08">末页</a>] 转至：</td>
						          <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
						              <tr>
						                <td width="1%"><input name="textfield3" type="text" class="right-textfield03" size="1" /></td>
						                <td width="87%"><input name="Submit23222" type="submit" class="right-button06" value=" " />
						                </td>
						              </tr>
						          </table></td>
						        </tr>
						    </table></td>
						  </tr>
						</table>

					</td>
                  </tr>
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
