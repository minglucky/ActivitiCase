<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <title>费用报销-新增</title>
    <link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language=JavaScript>
		function save(){
		   document.getElementById("form").submit();
		}
	</script>
</head>
<body class="ContentBody">
<form action="start/save" method="post" name="form" id="form">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >费用报销(业务表单)-新增</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>内容填写</legend>
                         <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
						    <tr>
						    <td nowrap align="right" width="13%">费用</td>
						    <td width="19%"><input type='text' id='fee' name='fee' value='' /></td>
						    <td width="13%" align="right" nowrap>费用类型</td>
						    <td width="55%"><select id="type" name="type">
						      <option value="差旅费">差旅费</option>
						      <option value="书报费">书报费</option>
						      <option value="会议费">会议费</option>
						      <option value="其他费">其他费</option>
						    </select></td>
						    </tr>
						    <tr>
						      <td nowrap align="right">发生日期</td>
						      <td colspan="3"><input type='text' id='feedate' name='feedate' value='' onClick="WdatePicker()"/></td>
						    </tr>
						    <tr>
						    <td nowrap align="right" width="13%">说明</td>
						    <td colspan="3"><textarea id='note' name='note' rows="5" cols="50"></textarea></td>
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
        <input type="button" name="Submit" value="保存" class="button" onclick="save();"/>　   
        <input type="button" name="Submit2" value="返回" class="button" onclick="window.history.go(-1);"/>
    </TD>
  </tr>
</table>
</div>
</form>
</body>
</html>