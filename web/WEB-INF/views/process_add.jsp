<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <title>流程管理-新增</title>
    <link rel="stylesheet" rev="stylesheet" href="<%=basePath%>css/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script language=JavaScript>
	function link(){
	   document.location.href="depbpmnmodel";
	}
	function link2(){
	   document.location.href="depclasspath";
	}
	function link3(){
	   document.location.href="depinputstream";
	}
	function link4(){
	   document.location.href="depzip";
	}
	function link5(){
	   document.location.href="depstring";
	}
	
</script>
</head>
<body class="ContentBody">
<form  action="uplaod_save" name="form_add" id="form_add"  method="post" enctype="multipart/form-data">				
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >流程管理->新增流程</th>
  </tr>
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>上传新增</legend>
                          <table class="table table-striped table-bordered table-hover">
					            
					            <tr>
					              <td>选择部署文件</td>
					                <td><input type="file" name="upFile" id="upFile" size="50" placeholder="选择文件"/></td>
					            </tr>
					            <tr >
					              <td>支持文件类型</td>
					              <td>zip、bar、bpmn、bpmn20.xml</td>
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

<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>动态BPMN模型部署</legend>
                          <table class="table table-striped table-bordered table-hover">
					            
					            <tr>
					                <td>提示：点击下面按钮，将触发动态BPMN模型，该模型写在Java代码中，请执行查看源码：</td>
					            </tr>
					            <tr >
					              <td>
					                <span class="newfont07">
						              <input name="Submit2" type="button" class="right-button08" value="动态BPMN生成" onclick="link();"/>
						            </span> 
								  </td>
					            </tr>
					        </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
</table>
</div>
<br>
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>自动部署</legend>
                          <table class="table table-striped table-bordered table-hover">
					            
					            <tr>
					                <td>Activiti服务启动时，将自动进行部署，已在配置文件中元素processEngineConfiguration下，加入如下内容，同时可以参考源码配置文件</td>
					            </tr>
					            <tr >
					              <td>
					                <span class="newfont07">
						              &lt;property name="deploymentResources" value="classpath*:/workflow/*/*.*" /&gt;<br> 
         							  &lt;property name="deploymentMode" value="resource-parent-folder" /&gt;
						            </span> 
								  </td>
					            </tr>
					        </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
</table>
</div>

<br>
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>Classpath部署</legend>
                          <table class="table table-striped table-bordered table-hover">
					            
					            <tr>
					                <td>部署前：可先修改源码中方法addClasspathResource()参数</td>
					            </tr>
					            <tr >
					              <td>
					                <span class="newfont07">
						              <input name="Submit2" type="button" class="right-button08" value="Classpath部署" onclick="link2();"/>
						            </span>
								  </td>
					            </tr>
					        </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
</table>
</div>

<br>
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>输入流部署</legend>
                          <table class="table table-striped table-bordered table-hover">
					            
					            <tr>
					                <td>部署前：可先修改源码中变量bpmnPath的值</td>
					            </tr>
					            <tr >
					              <td>
					                <span class="newfont07">
						              <input name="Submit2" type="button" class="right-button08" value="输入流部署" onclick="link3();"/>
						            </span>
								  </td>
					            </tr>
					        </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
</table>
</div>

<br>
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>压缩包部署</legend>
                          <table class="table table-striped table-bordered table-hover">
					            
					            <tr>
					                <td>部署前：可先修改源码中变量zipPath的值</td>
					            </tr>
					            <tr >
					              <td>
					                <span class="newfont07">
						              <input name="Submit2" type="button" class="right-button08" value="压缩包部署" onclick="link4();"/>
						            </span>
								  </td>
					            </tr>
					        </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
</table>
</div>

<br>
<div class="MainDiv">
<table width="90%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
    <td class="CPanel">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
            <TR>
                <TD width="100%">
                    <fieldset style="height:100%;">
                    <legend>字符串部署</legend>
                          <table class="table table-striped table-bordered table-hover">
					            
					            <tr>
					                <td>部署前：可先修改源码中变量str的值</td>
					            </tr>
					            <tr >
					              <td>
					                <span class="newfont07">
						              <input name="Submit2" type="button" class="right-button08" value="字符串部署" onclick="link5();"/>
						            </span>
								  </td>
					            </tr>
					        </table>
                    </fieldset>			
                </TD>
            </TR>
		</TABLE>
	 </td>
  </tr>
</table>
</div>
</body>
</html>