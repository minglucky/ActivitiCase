<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <title>费用报销-审批</title>
    <link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language=JavaScript>
		function save(){
		   document.getElementById("form").submit();
		}
	</script>
</head>
<body>
<form action="save/${taskId}" method="post" name="form" id="form">
<div class="MainDiv">
<table width="60%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >费用报销(业务表单)-审批</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>内容填写</legend>
                        <c:choose>
		                  	<c:when test="${actId=='usertask1'}">
		                  		<table border="0" cellpadding="2" cellspacing="1" style="width:100%">
								    <tr>
								    <td nowrap align="right" width="13%">申请人</td>
								    <td  colspan="3">${data.userId}</td>
								    </tr>
									<tr>
								    <td nowrap align="right" width="13%">费用</td>
								    <td width="19%">${data.fee}</td>
								    <td width="13%" align="right" nowrap>费用类型</td>
								    <td width="55%">${data.type}</td>
								    </tr>
								    <tr>
								      <td nowrap align="right">发生日期</td>
								      <td colspan="3"><fmt:formatDate type="date" value="${data.feedate}" /></td>
								    </tr>
								    <tr>
								    <td nowrap align="right" width="13%">说明</td>
								    <td colspan="3">${data.note}</td>
								    </tr>
									<tr>
								    <td nowrap align="right" width="13%">部门领导意见</td>
								    <td colspan="3">
								    <textarea id='bmyj' name='bmyj' rows="5" cols="50"></textarea>
								    <input type="hidden" name="id" id="id" value="${data.id}" />
								    </td>
								    </tr>
								</table>   
		                  	</c:when>
		                  	<c:when test="${actId=='usertask2'}">
		                  		<table border="0" cellpadding="2" cellspacing="1" style="width:100%">
								    <tr>
								    <td nowrap align="right" width="13%">申请人</td>
								    <td  colspan="3">${data.userId}</td>
								    </tr>
									<tr>
								    <td nowrap align="right" width="13%">费用</td>
								    <td width="19%">${data.fee}</td>
								    <td width="13%" align="right" nowrap>核实费用</td>
								    <td width="55%"><input type='text' id='refee' name='refee' value='' /></td>
								    </tr>
								    <tr>
								      <td nowrap align="right">发生日期</td>
								      <td><fmt:formatDate type="date" value="${data.feedate}" /></td>
									  <td width="13%" align="right" nowrap>费用类型</td>
								      <td width="55%">${data.type}</td>
								    </tr>
								    <tr>
								    <td nowrap align="right" width="13%">说明</td>
								    <td colspan="3">${data.note}</td>
								    </tr>
									<tr>
								    <td nowrap align="right" width="13%">部门领导意见</td>
								    <td colspan="3">${data.bmyj}</td>
								    </tr>
									<tr>
								    <td nowrap align="right" width="13%">财务部门意见</td>
								    <td colspan="3">
								    <textarea id='bzhu' name='bzhu' rows="5" cols="50"></textarea>
								    <input type="hidden" name="id" id="id" value="${data.id}" />
								    </td>
								    </tr>
								</table>
		                  	</c:when>
		                  	<c:otherwise>
		                  		<table border="0" cellpadding="2" cellspacing="1" style="width:100%">
								    <tr>
								    <td nowrap align="right" width="13%">费用</td>
								    <td width="19%">${data.fee}</td>
								    <td width="13%" align="right" nowrap>核实费用</td>
								    <td width="55%">${data.refee}</td>
								    </tr>
								    <tr>
								      <td nowrap align="right">发生日期</td>
								      <td><fmt:formatDate type="date" value="${data.feedate}" /></td>
									  <td width="13%" align="right" nowrap>费用类型</td>
								      <td width="55%">${data.type}</td>
								    </tr>
								    <tr>
								    <td nowrap align="right" width="13%">说明</td>
								    <td colspan="3">${data.note}</td>
								    </tr>
									<tr>
								    <td nowrap align="right" width="13%">部门领导意见</td>
								    <td colspan="3">${data.bmyj}</td>
								    </tr>
									<tr>
								    <td nowrap align="right" width="13%">财务部门意见</td>
								    <td colspan="3">
								    ${data.bzhu}
								    <input type="hidden" name="id" id="id" value="${data.id}" />
								    </td>
								    </tr>
								</table>
		                  	</c:otherwise>
			          </c:choose>	
                    </fieldset>
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
  <tr>
    <TD colspan="2" align="center" height="50px">
		<input type="hidden" name="taskId" id="taskId" value="${taskId}" />
        <input type="button" name="Submit" value="保存" class="button" onclick="save();"/>　    
        <input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>
    </TD>
  </tr>
</table>
</div>
</form>
</body>
</html>