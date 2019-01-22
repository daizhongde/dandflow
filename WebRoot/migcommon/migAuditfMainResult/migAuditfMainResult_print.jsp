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
<title>迁移内容稽核报告</title>
</head>
<body>
<div id="div1">
<DIV style="LINE-HEIGHT: 30px" class=size16 align=center><STRONG><font color="#0000FF">迁移内容稽核报告</font></STRONG></DIV>    
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
				><DIV align=center><b><font color="#000000">Audit Item</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Dryrun</font></b></DIV></td>
			<td height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Source Count</font></b></DIV></td>
			<td height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Target Count</font></b></DIV></td>
				
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Structure Accordant Count</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Structure Accordant Rate</font></b></DIV></td>
			
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Content Accordant Count</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Content Accordant Rate</font></b></DIV></td>
			<td  height="32" style="border: solid 1px black"
				><DIV align=center><b><font color="#000000">Result Accordant Rate</font></b></DIV></td>

			
		</tr>
		</thead>
		<TBODY>
		<c:forEach var="row" items="${rows}">
			<tr>
				<td style="border: solid 1px black">${row.faudit_name}</td>
				<td style="border: solid 1px black">${row.mig_dryrun_name}</td>				
				<td style="border: solid 1px black">${row.FARES_SRC_COUNT}</td>
				<td style="border: solid 1px black">${row.FARES_DST_COUNT}</td>
				<td style="border: solid 1px black">${row.FARES_KEY_PASSCNT}</td>
				<td style="border: solid 1px black">
				    <c:choose>
					    <c:when test="${row.FARES_SRC_COUNT == 0}">
					    	0.00%
					    </c:when>
					    <c:when test="${0 < row.FARES_KEY_PASSCNT/row.FARES_SRC_COUNT
								&& row.FARES_KEY_PASSCNT/row.FARES_SRC_COUNT < 0.0001}">
							<0.01%
					    </c:when>
					    <c:when test="${0.9999 < row.FARES_KEY_PASSCNT/row.FARES_SRC_COUNT
								&& row.FARES_KEY_PASSCNT/row.FARES_SRC_COUNT < 1}">
							>99.99%
					    </c:when>
					    <c:otherwise>
							<fmt:formatNumber value="${ row.FARES_SRC_COUNT==0 ? 0 : row.FARES_KEY_PASSCNT/row.FARES_SRC_COUNT }" type="percent" maxFractionDigits="2"/>
					    </c:otherwise>
					</c:choose>
				</td>
				<td style="border: solid 1px black">${row.FARES_SRC_PASSCNT}</td>
				<td style="border: solid 1px black">
				    <c:choose>
					    <c:when test="${row.FARES_KEY_PASSCNT == 0}">
					    	0.00%
					    </c:when>
					    <c:when test="${0 < row.FARES_SRC_PASSCNT/row.FARES_KEY_PASSCNT
								&& row.FARES_SRC_PASSCNT/row.FARES_KEY_PASSCNT < 0.0001}">
							<0.01%
					    </c:when>
					    <c:when test="${0.9999 < row.FARES_SRC_PASSCNT/row.FARES_KEY_PASSCNT
								&& row.FARES_SRC_PASSCNT/row.FARES_KEY_PASSCNT < 1}">
							>99.99%
					    </c:when>
					    <c:otherwise>
							<fmt:formatNumber value="${ row.FARES_KEY_PASSCNT==0 ? 0 : row.FARES_SRC_PASSCNT/row.FARES_KEY_PASSCNT }" type="percent" maxFractionDigits="2"/>
					    </c:otherwise>
					</c:choose>
				</td>
				<td style="border: solid 1px black">
				    <c:choose>
					    <c:when test="${row.FARES_SRC_COUNT == 0}">
					    	0.00%
					    </c:when>
					    <c:when test="${0 < row.FARES_SRC_PASSCNT/row.FARES_SRC_COUNT
								&& row.FARES_SRC_PASSCNT/row.FARES_SRC_COUNT < 0.0001}">
							<0.01%
					    </c:when>
					    <c:when test="${0.9999 < row.FARES_SRC_PASSCNT/row.FARES_SRC_COUNT
								&& row.FARES_SRC_PASSCNT/row.FARES_SRC_COUNT < 1}">
							>99.99%
					    </c:when>
					    <c:otherwise>
							<fmt:formatNumber value="${ row.FARES_SRC_COUNT==0 ? 0 : row.FARES_SRC_PASSCNT/row.FARES_SRC_COUNT }" type="percent" maxFractionDigits="2"/>
					    </c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</TBODY>
	</table>

</body>
</html>