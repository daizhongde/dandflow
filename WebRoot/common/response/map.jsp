<%@ page language="java" pageEncoding="UTF-8"%><%@page import="net.sf.json.JSONObject"%><%
out.print( JSONObject.fromObject( request.getAttribute("map") ).toString() );
%>