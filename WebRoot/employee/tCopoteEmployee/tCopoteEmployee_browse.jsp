<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" title="" noheader="true">
		<div style="padding:10px 0 10px 30px">
	    	<table align="center">
	    		<tr>
	    			<td>ID:</td>
	    			<td>${map.id }</td>
	    		</tr>
	    		<tr>
	    			<td>uin:</td>
	    			<td>${map.uin }</td>
	    		</tr>
	    		<tr>
	    			<td>部门ID:</td>
	    			<td>${map.pid }</td>
	    		</tr>
	    		<tr>
	    			<td>部门名称:</td>
	    			<td>${map.department }</td>
	    		</tr>
				<tr>
					<td>姓名:</td>
					<td>${map.name }</td>
				</tr>
	    		<tr>
	    			<td>性别:</td>
	    			<td>
	    				${map.sex=='1'?'男':'女' }
                    </td>
	    		</tr>
				<tr>
					<td>员工编号:</td>
					<td>${map.employee_no }</td>
				</tr>
				<tr>
					<td>公司邮箱:</td>
					<td>${map.alias }</td>
				</tr>
				<tr>
					<td>手机号码:</td>
					<td>${map.mobile }</td>
				</tr>
				<tr>
					<td>工牌号码:</td>
					<td>${map.employee_cardno }</td>
				</tr>
				<tr>
					<td>身份证号码:</td>
					<td>${map.employee_idcard }</td>
				</tr>
				<tr>
					<td>QQ号码:</td>
					<td>${map.qq }</td>
				</tr>
						
	    	</table>
	    </div>
    </div>
<script type="text/javascript">
read.close = function (){
	$('#win').window('close');
}
</script>
	<div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;background:#F4F4F4;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#win').dialog('close');" style="width:80px">Close</a>
	</div>
</div>
</body>
</html>