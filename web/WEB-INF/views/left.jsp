<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Zioer 流程开发学习</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(../images/left.gif);
}
-->
</style>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT language=JavaScript>
function tupian(idt){
    var nametu="xiaotu"+idt;
    var tp = document.getElementById(nametu);
    tp.src="../images/ico05.gif";//图片ico04为白色的正方形
	
	for(var i=1;i<230;i++)
	{
	  
	  var nametu2="xiaotu"+i;
	  if(i!=idt*1)
	  {
	    var tp2=document.getElementById('xiaotu'+i);
		if(tp2!=undefined)
	    {tp2.src="../images/ico06.gif";}//图片ico06为蓝色的正方形
	  }
	}
}

function list(idstr){
	var name1="subtree"+idstr;
	var name2="img"+idstr;
	var objectobj=document.all(name1);
	var imgobj=document.all(name2);
	
	
	//alert(imgobj);
	
	if(objectobj.style.display=="none"){
		for(i=1;i<300;i++){
			var name3="img"+i;
			var name="subtree"+i;
			var o=document.all(name);
			if(o!=undefined){
				o.style.display="none";
				var image=document.all(name3);
				//alert(image);
				image.src="../images/ico04.gif";
			}
		}
		objectobj.style.display="";
		imgobj.src="../images/ico03.gif";
	}
	else{
		objectobj.style.display="none";
		imgobj.src="../images/ico04.gif";
	}
}

</SCRIPT>

<body>
<table width="198" border="0" cellpadding="0" cellspacing="0" class="left-table01">
  <tr>
    <TD>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="207" height="55" background="../images/nav01.gif">
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					<td width="25%" rowspan="2"><img src="../images/ico00.gif" width="35" height="35" /></td>
					<td width="75%" height="22" class="left-font01">您好，<span class="left-font02">${userId}</span></td>
				  </tr>
				  <tr>
					<td height="22" class="left-font01">
						[&nbsp;<a href="../login/out" target="_top" class="left-font01">退出</a>&nbsp;]</td>
				  </tr>
				</table>
			</td>
		  </tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="207" height="30" >
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					<td height="30" class="left-font01">
						&nbsp;-&gt;<a href="../main/main" target="mainFrame" class="left-font03">首页</a>
					</td>
				  </tr>
				</table>
			</td>
		  </tr>
		</table>
		<!--  流程管理开始    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img8" id="img8" src="../images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('8');" >流程管理</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree8" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu20" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="../process/add" target="mainFrame" class="left-font03" onClick="tupian('20');">新增流程</a></td>
				</tr>
				<tr>
				  <td width="9%" height="21" ><img id="xiaotu21" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="../process/list" target="mainFrame" class="left-font03" onClick="tupian('21');">流程列表</a></td>
				</tr>
      </table>
		<!--  流程管理结束    -->
		
		<!--  用户管理开始    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img7" id="img7" src="../images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('7');" >用户管理</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree7" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu17" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../user/add" target="mainFrame" class="left-font03" onClick="tupian('17');">新增用户</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu18" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
					<a href="../user/list" target="mainFrame" class="left-font03" onClick="tupian('18');">用户列表</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu30" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../department/add" target="mainFrame" class="left-font03" onClick="tupian('30');">新增部门
							</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu31" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../department/list" target="mainFrame" class="left-font03" onClick="tupian('31');">部门列表
							</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu19" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../role/add" target="mainFrame" class="left-font03" onClick="tupian('19');">新增职务
							</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu22" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../role/list" target="mainFrame" class="left-font03" onClick="tupian('22');">职务列表
							</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu24" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../group/list" target="mainFrame" class="left-font03" onClick="tupian('24');">组列表
							</a></td>
				</tr>
				
      </table>
		<!--  用户管理结束    -->
		
		<!--  派车流程管理开始    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img21" id="img21" src="../images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('21');" >派车流程</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree21" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu111" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../paiche/add" target="mainFrame" class="left-font03" onClick="tupian('111');">新增表单</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu112" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
					<a href="../paiche/list" target="mainFrame" class="left-font03" onClick="tupian('112');">待办列表</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu113" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../paiche/hlist" target="mainFrame" class="left-font03" onClick="tupian('113');">我的历史
							</a></td>
				</tr>				
      </table>
		<!--  派车流程管理结束    -->
        
		<!--  休假出差管理开始    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img22" id="img22" src="../images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('22');" >休假出差流程</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree22" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu121" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../beaway/add" target="mainFrame" class="left-font03" onClick="tupian('121');">新增表单</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu122" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
					<a href="../beaway/list" target="mainFrame" class="left-font03" onClick="tupian('122');">待办列表</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu123" src="../images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="../beaway/hlist" target="mainFrame" class="left-font03" onClick="tupian('123');">我的历史
							</a></td>
				</tr>				
      </table>
		<!--  休假出差管理结束    -->

		<!--  费用报销管理开始    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
			<tr>
				<td height="29">
					<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="8%"><img name="img22" id="img23" src="../images/ico04.gif" width="8" height="11" /></td>
							<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('23');" >费用报销流程</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</TABLE>
		<table id="subtree23" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0"
			   cellspacing="0" class="left-table02">
			<tr>
				<td width="9%" height="20" ><img id="xiaotu131" src="../images/ico06.gif" width="8" height="12" /></td>
				<td width="91%">
					<a href="../reimbursement/add" target="mainFrame" class="left-font03" onClick="tupian('131');">新增表单</a></td>
			</tr>
			<tr>
				<td width="9%" height="20" ><img id="xiaotu132" src="../images/ico06.gif" width="8" height="12" /></td>
				<td width="91%">
					<a href="../reimbursement/list" target="mainFrame" class="left-font03" onClick="tupian('132');">待办列表</a></td>
			</tr>
			<tr>
				<td width="9%" height="20" ><img id="xiaotu133" src="../images/ico06.gif" width="8" height="12" /></td>
				<td width="91%">
					<a href="../reimbursement/hlist" target="mainFrame" class="left-font03" onClick="tupian('133');">我的历史
					</a></td>
			</tr>
		</table>
		<!--  费用报销管理结束    -->

		<!--  模型管理开始    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
			<tr>
				<td height="29">
					<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="8%"><img name="img9" id="img9" src="../images/ico04.gif" width="8" height="11" /></td>
							<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('9');" >流程模型管理</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</TABLE>
		<table id="subtree9" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0"
			   cellspacing="0" class="left-table02">
			<tr>
				<td width="9%" height="20" ><img id="xiaotu25" src="../images/ico06.gif" width="8" height="12" /></td>
				<td width="91%">
					<a href="../model/add" target="mainFrame" class="left-font03" onClick="tupian('25');">新增模型</a></td>
			</tr>
			<tr>
				<td width="9%" height="20" ><img id="xiaotu26" src="../images/ico06.gif" width="8" height="12" /></td>
				<td width="91%">
					<a href="../model/list" target="mainFrame" class="left-font03" onClick="tupian('26');">模型列表</a></td>
			</tr>
		</table>
		<!--  模型管理结束    -->
          
	  </TD>
  </tr>
  
</table>
</body>
</html>
