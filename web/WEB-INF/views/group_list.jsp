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
</head>

<body class="ContentBody">
<form action="add" method="post" name="fom" id="fom">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >用户管理->组列表</th>
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
                    	<td height="22" colspan="4" align="center" style="font-size:16px">组详细列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="15%" height="30">组ID</td>
					<td width="40%">组名称</td>
					<td width="">操作</td>
                  </tr>
                  <c:forEach items="${groups}" var="var" varStatus="vs">
                  <tr  <c:if test="${vs.count%2==0}">bgcolor="#AAAABB"</c:if> align="left" >
				    <td >${var.getId()}</td>
				    <td  height="30">${var.getName()}</td>                  
					<td ><a href="<%=basePath%>group/view/${var.getId()}">查看</a>&nbsp;
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
