<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <title>用户管理-新增</title>
    <link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="ContentBody">
<form action="save" method="post" name="fom" id="fom">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >用户管理->新增用户</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>添加用户</legend>
                          <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                         
                          <tr>
                            <td nowrap align="right" width="13%">用户ID:</td>
                            <td width="41%"><input name="userid" id="userid" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">用户姓名:</td>
                            <td width="41%"><input name="username" id="username" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">所在部门:</td>
                            <td width="41%">
                            <select name="department_id" id="department_id">
                            	<c:if test="${departmentList != null}">
								 	<c:forEach items="${departmentList}" var="var" varStatus="vs">
										<option value="${var.getDepartment_id()}">${var.getDepartmentname()}</option>
									</c:forEach>		   
								</c:if>
                            </select>
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">职务:</td>
                            <td width="41%">
                            <select name="role_id" id="role_id">
                            	<c:if test="${roleList != null}">
								 	<c:forEach items="${roleList}" var="var" varStatus="vs">
										<option value="${var.getRole_id()}">${var.getRolename()}</option>
									</c:forEach>		   
								</c:if>
                            </select>
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">用户密码:</td>
                            <td width="41%"><input name="pwd" id="pwd" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
  <tr>
    <TD colspan="2" align="center" height="50px">
        <input type="submit" name="Submit" value="保存" class="button"/>　    
        <input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>
    </TD>
  </tr>
</table>
</div>
</form>
</body>
</html>