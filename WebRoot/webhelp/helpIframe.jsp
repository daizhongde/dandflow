<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%
String r_url = request.getParameter("r_url");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>帮助系统</title>
</head>
<body>
<iframe name="helpIframe" style="width:100%; height:100%;" src="help.jsp?r_url=<%=r_url %>" frameborder="0"></iframe>
</body>
</html> 