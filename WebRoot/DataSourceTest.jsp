<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<sql:query var="rs" dataSource="toolJNDI">
select 	N_MID,		C_MNAME,	N_MTYPE,	N_MLEVEL,	N_MPARENT,
		N_MORDER,	C_MICONCLS,	C_MSTATE,	N_MCHECKED,	C_MPATH,
		C_MNOTE,	N_MCUSER,	C_MCIP,		C_MCTIME,	N_MMUSER,
		C_MMIP,	C_MMTIME
 from t_authority_module
</sql:query>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitionaal//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据源测试</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style> 
body {
     font-family: Verdana, sans-serif;
     }
</style>

  </head>
  
  <body>
	 <table border="1" cellpadding="1" cellspacing="2">
		<thead>
		 <tr bgcolor="#EE0FDD">
				<th>模块ID</th>
				<th>模块名称</th>
				<th>模块类型</th>
				<th>模块级别</th>
				<th>上级模块</th>
				<th>模块次序号</th>
				<th>模块图标样式</th>
				<th>模块结点展开状态</th>
				<th>模块结点是否是选定的</th>
				<th>模块url路径</th>
				<th>注备</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="module" items="${rs.rows}">
				<tr bgcolor="#00FF00">
					<td>${module.N_MID}</td>
					<td>${module.C_MNAME}</td>
					<td>${module.N_MTYPE}</td>
					<td>${module.N_MLEVEL}</td>
					<td>${module.N_MPARENT}</td>
					<td>${module.N_MORDER}</td>
					<td>${module.C_MICONCLS}</td>
					<td>${module.C_MSTATE}</td>
					<td>${module.N_MCHECKED}</td>
					<td>${module.C_MPATH}</td>
					<td>${module.C_MNOTE}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  </body>
</html>
