<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
 <%@ page import="com.opensymphony.xwork2.*"%>
<%@page import="java.util.*,com.opensymphony.xwork2.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <title>出错提示页</title>
</head> 
<body>
<TABLE width="780" align="center" CELLSPACING=0 >
<tr> 
	<td height="39" valign=top>
	<br><div align="center"><font color="#FF0000" size="+1">
<b>系统处理过程中发生了一个错误，信息如下：</b></font></div>
	</td>
</tr>
<tr>
	<td height="100" valign=top>
	<div class="error"><br><br>
	<s:property value="errMsg"/>
	<s:property value="exception"/>
	</div>
	</td>
</tr>
<tr>
	<td valign=top><div align="center" style="font:10pt">请您先核对输入，如果再次出现该错误，请与站长联系。error@163.com 谢谢。</div><br></td>
</tr>
<tr>
<td>
  <%
    ActionContext cxt = ActionContext.getContext();

    out.println("<h2>ValueStack</h2>");
    ValueStack stack = cxt.getValueStack();
    List list = (List) stack.getRoot();
    for (int i=0; i<list.size(); i++)
    {
     out.print("<FONT Color='Red'>" + list.get(i)+"</FONT>");
     out.println("<BR/>");
    }
   
    out.println("<h2>ContextMap</h2>");
    Map map = cxt.getContextMap();
    Set set = map.entrySet();
    for (Iterator it = set.iterator(); it.hasNext();)
    {
     Map.Entry es = (Map.Entry) it.next();
     // key
     out.print("<B><Font Color='Red'>"+es.getKey()+"</FONT></B>");
     // value
     out.print("=" + es.getValue());
     out.println("<br/>");
    }
   %>

</td>
</tr>
</table>
</body>
</html>