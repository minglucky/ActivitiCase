<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title></title>
<link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />

</head>

<body class="ContentBody">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >�û�����->�û��鿴</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>�鿴�û���Ϣ</legend>
                          <table border="0" cellpadding="4" cellspacing="1" style="width:100%" class="newfont03">
                         
                          <tr>
                            <td nowrap align="right" width="13%">�û�ID:</td>
                            <td width="41%">${user.getUser_id()}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">�û�����:</td>
                            <td width="41%">${user.getUsername()}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">���ڲ���:</td>
                            <td width="41%">${user.getDepartmentname()}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">ְ��:</td>
                            <td width="41%">${user.getRolename()}</td>
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
        <input type="button" name="Submit2" value="����" class="button" onclick="window.history.go(-1);"/>
    </TD>
  </tr>
</table>
</div>
</body>
</html>
