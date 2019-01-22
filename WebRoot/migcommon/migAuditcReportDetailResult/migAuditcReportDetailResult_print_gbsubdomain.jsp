<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	List rows = (List) request.getAttribute("rows");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一致性稽核结果报告-子域分组报表</title>
</head>
<body>
<div id="div1">
<DIV style="LINE-HEIGHT: 30px" class=size16 align=center><STRONG><font color="#0000FF">一致性稽核结果报告-子域分组报表</font></STRONG></DIV>    
<!--
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
		<TBODY>
		  <TR>
		    <TD><font color="#0000FF">Dry run：<SPAN>{mig_dryrun_name}</SPAN></font></TD>
		    <TD><font color="#0000FF">稽核项：<SPAN>{faudit_name}</SPAN></font></TD>
		    <TD><font color="#0000FF">流水号：<SPAN>{fares_serial}</SPAN></font></TD></TR>
		</TBODY>
	</TABLE>  -->
</div>
	<table border="1" width="100%" height="106" cellspacing="0"
		bgcolor="#CCFFFF"
		style="border: solid 1px black; border-collapse: collapse">
		<thead>
		<tr>
			<td height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">DryRun</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Domain</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Sub Domain</font></b></DIV></td>
				<!-- 
			<td height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">源枚举值</font></b></DIV></td>
			<td height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">目标枚举值</font></b></DIV></td> -->
			<td  height="32" style="border: solid 1px black">
				
				<DIV align=center><b><font color="#000000">Source Data Count</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black">
				<DIV align=center><b><font color="#000000">Target Data Count</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black">
				<DIV align=center><b><font color="#000000">Different Count</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black">
				<DIV align=center><b><font color="#000000">Difference Rate</font></b></DIV></td>
		</tr>
		</thead>
		<TBODY>
		<c:forEach var="row" items="${rows}">
			<tr>
				<td style="border: solid 1px black">${row.dryrun_name}</td>
				<td style="border: solid 1px black">${row.DOMAIN2}</td>
				<td style="border: solid 1px black">${row.AUDIT_ITEM}</td>
				<td style="border: solid 1px black">${row.src_count}</td>
				<td style="border: solid 1px black">${row.dst_count}</td>
				<td style="border: solid 1px black">${row.diff_count}</td>
				<td style="border: solid 1px black">
    <c:choose>
    <c:when test="${ row.diff_count == 0}">
    	0.00%
    </c:when>
    <c:when test="${0< (row.diff_count+0.001-0.001) div row.src_count
			&& (row.diff_count+0.001-0.001) div row.src_count<0.0001}">
		<0.01%
    </c:when>
    <c:when test="${0.9999< (row.diff_count+0.001-0.001)/row.src_count
			&& (row.diff_count+0.001-0.001)/row.src_count<1}">
		>99.99%
    </c:when>
    <c:otherwise>
		<fmt:formatNumber value="${ (row.diff_count+0.001-0.001) /row.src_count }" type="percent" maxFractionDigits="2"/>
    </c:otherwise>
    </c:choose>
				</td>
			</tr>
		</c:forEach>
		</TBODY>
	</table>

</body>
</html>