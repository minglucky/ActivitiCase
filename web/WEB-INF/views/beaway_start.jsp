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
<form action="start/save" method="post" name="form" id="form">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >休假出差管理->新增</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    
                          <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                          <tr>
                            <td nowrap align="right" width="13%">类别:</td>
                            <td width="41%">
                            	<select id="sort" name="sort">
                            		<option value="6">休假</option>
                            		<option value="7">出差</option>
                            	</select>
                            <span class="red">*</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">地点:</td>
                            <td width="41%"><input name="onposition" id="onposition" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">开始时间:</td>
                            <td width="41%"><input name="startdatetime" id="startdatetime" class="text" style="width:250px" type="text" size="40" onClick="WdatePicker({ dateFmt: 'yyyy-MM-dd' })"/>
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">结束时间:</td>
                            <td width="41%"><input name="enddatetime" id="enddatetime" class="text" style="width:250px" type="text" size="40" onClick="WdatePicker({ dateFmt: 'yyyy-MM-dd' })"/>
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">联系电话:</td>
                            <td width="41%"><input name="phone" id="phone" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">备注:</td>
                            <td width="41%"><textarea  name="bzhu" id="bzhu" class="text" style="width:250px; height:50px"></textarea>
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