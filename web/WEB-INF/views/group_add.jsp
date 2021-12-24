<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <title></title>
    <link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="ContentBody">
<form action="save" method="post" name="fom" id="fom">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >用户管理->新增组</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>添加组</legend>
                          <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                         
                          <tr>
                            <td nowrap align="right" width="13%">组ID:</td>
                            <td width="41%"><input name="groupid" id="groupid" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">组名称:</td>
                            <td width="41%"><input name="name" id="name" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">组类别:</td>
                            <td width="41%"><input name="type" id="type" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">包含用户:</td>
                            <td width="41%">
								<c:if test="${userList != null}">
								 	<c:forEach items="${userList}" var="var" varStatus="vs">
										<input name="users" value="${var.getId()}" class="ace ace-checkbox-2" type="checkbox">
										${var.getFirstName()}${var.getLastName()}&nbsp;&nbsp;
									</c:forEach>		   
								</c:if>
							</td>
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