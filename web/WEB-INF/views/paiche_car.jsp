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
<form action="save/${taskId}" method="post" name="form" id="form">
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >派车管理->派车</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    
                          <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                          <tr>
                            <td nowrap align="right" width="13%">申请人:</td>
                            <td width="41%">${startUsername}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">派车时间:</td>
                            <td width="41%"><fmt:formatDate type="BOTH" value="${data.startdatetime}" /></td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">起始地点:</td>
                            <td width="41%">${data.startposition}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">到达地点:</td>
                            <td width="41%">${data.endposition}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">乘车人数:</td>
                            <td width="41%">${data.persons}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">联系电话:</td>
                            <td width="41%">${data.phone}</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">备注:</td>
                            <td width="41%">${data.bzhu}</td>
                          </tr>
                          </table>
                    </fieldset>			
                </TD>
            </TR>
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                       <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                          <tr>
                            <th nowrap align="right" width="13%">派车:</th>
                            <th align="left" width="41%"></th>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">司机:</td>
                            <td width="41%">
								<input name="driver" id="driver" value="" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span>
							</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">车辆:</td>
                            <td width="41%">
								<input name="car" id="car" value="" class="text" style="width:250px" type="text" size="40" />
                            <span class="red"> *</span>
							</td>
                          </tr>
                        </table>
                    </fieldset>			
                </TD>
            </TR>
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                       <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                          <tr>
                            <th nowrap align="right" width="13%">相关操作:</th>
                            <th align="left" width="41%"></th>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">操作方式:</td>
                            <td width="41%">
								<select name="approval_2" id="approval_2">
									<option value="4">正常派车</option>
								</select>
							</td>
                          </tr>
                          <tr>
                            <td nowrap align="right" width="13%">意见或说明:</td>
                            <td width="41%">
								<textarea  name="log" id="log" class="text" style="width:250px; height:50px"></textarea>
                            	<span class="red"> *</span>
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
  <c:if test="${logList != null && logList.size()>0}">
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                       <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
                          <tr>
                            <th nowrap align="right" width="13%">操作历史:</th>
                            <th align="left" width="41%"></th>
                          </tr>
                          <tr>
                            <td nowrap align="center" width="13%" colspan="4">
								<table border="0" cellpadding="2" cellspacing="1" style="width:80%">
									<tr class="tablestyle_title" style="text-align:center">
								    	<th>操作人</th>
								    	<th>操作方式</th>
								        <th>意见或说明</th>
								        <th>操作时间</th>
								    </tr>
								    <c:forEach items="${logList}" var="var" varStatus="vs">
					                  <tr  <c:if test="${vs.count%2==0}">bgcolor="#AAAABB"</c:if> align="left" >
									    <td >${var.username}</td>
									    <td  height="30">${var.codename}</td>
										<td >${var.log}</td>                    
										<td ><fmt:formatDate value="${var.createdatetime}" type="both"/></td>
									  </tr>
									</c:forEach>
								</table>
							</td>
                          </tr>
                        </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	  </td>
    </tr>
  </c:if>
</table>
</div>
</form>
</body>
</html>