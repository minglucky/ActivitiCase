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

function link2(){
   document.location.href="pagelist";
}

</script>
</head>

<body class="ContentBody">
<form action="add" method="post" name="fom" id="fom">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >用户管理->用户列表</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 <tr>
               <td height="20">
	            <span class="newfont07">
	              <input name="Submit2" type="button" class="right-button08" value="分页数据" onclick="link2();"/>
	            </span>
	            	            
	           </td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#FFFFEE" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="12" align="center" style="font-size:16px">用户详细列表</td>
                  </tr>
                  <tr bgcolor="#EEEEEE">
				    <td width="10%" height="30">用户ID</td>
					<td width="15%">用户姓名</td>
                    <td width="15%">所属部门</td>
                    <td width="15%">所属职务</td>
                    <td width="15%">所属组ID</td>
                    <td width="15%">所属组名称</td>
					<td >操作</td>
                  </tr>
                  <c:forEach items="${users}" var="var" varStatus="vs">
                  <tr  <c:if test="${vs.count%2==0}">bgcolor="#AAAABB"</c:if> align="left" >
				    <td >${var.getUser_id()}</td>
				    <td  height="30">${var.getUsername()}</td>
					<td >${var.getDepartmentname()}</td>   
					<td >${var.getRolename()}</td> 
					<td >${var.getDepartment_id()}:${var.getRole_id()}</td>       
					<td >${var.getDepartmentname()}:${var.getRolename()}</td>               
					<td ><a href="<%=basePath%>user/view/${var.getUser_id()}">查看</a>&nbsp;
					<a href="<%=basePath%>user/edit/${var.getUser_id()}">编辑</a>&nbsp;
					<a href="<%=basePath%>user/delete/${var.getUser_id()}">删除</a></td>
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
