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
   document.location.href="list";
}
</script>
</head>

<body class="ContentBody">
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
               <td height="20">
               	<span class="newfont07">
	              <input name="Submit2" type="button" class="right-button08" value="单页数据" onclick="link2();"/>
	            </span>  	            
	           </td>
          	 </tr>
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
					<td ><a href="<%=basePath%>group/view/${var.getId()}">查看</a>
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
