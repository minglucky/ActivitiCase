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
                <th class="tablestyle_title" >费用报销->申请人确认</th>
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
                                                    <option value="5">确认操作</option>
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