<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
<title>江西师范大学</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.inputsize{ width:120px; height:18px; }
-->
</style>
<SCRIPT language=JavaScript>
if (window.frames.length != parent.frames.length) {
	top.location.href = location.href;
}
</SCRIPT>

<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<form action="<%=basePath%>login/save" method="post" name="fom" id="fom">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
<%--    <td height="147" background="<%=basePath%>images/top02.gif"><img src="<%=basePath%>images/top03.gif" width="776" height="147" /></td>--%>
  </tr>
</table>
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="<%=basePath%>images/shida.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
          
        </table></td>
        <td><img src="<%=basePath%>images/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="31%" height="35" class="login-text02">用户名&nbsp;<br /></td>
        <td width="69%"><input name="username" type="text" class="inputsize"/></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">密码&nbsp;<br /></td>
        <td><input name="password" type="password"  class="inputsize" /></td>
      </tr>
      
      <tr>
        <td height="35">&nbsp;</td>
        <td><input name="Submit2" type="submit" class="right-button01" value="确认登录" onClick="window.location='index.html'" />
          <input name="Submit232" type="submit" class="right-button02" value="重置" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>