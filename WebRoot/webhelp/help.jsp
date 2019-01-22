<%@ page import="java.util.*" %>
<%
// 	Object o = session.getAttribute(com.copote.common.Constants.SESSION_HELP_ID);
	Object o = request.getParameter("r_url");
	String r_url = o.toString().trim();
	int beginIndex = r_url.indexOf("/migration/")==-1 ? 0 : r_url.indexOf("/migration/")+11;
	r_url = r_url.substring( beginIndex );
// 	System.out.println("r_url:" + r_url);
	
	Map<String,String> map = new HashMap<String,String>();
	map.put("job/job_new.html", "_2_1_1");
	map.put("migcommon/migJobInfo/migJobInfo_main.html", "_2_1_2");
	map.put("migcommon/migJobInfo/migJobInfo_main2.html", "_2_1_3");
	
	map.put("migcommon/migJobProcess/migJobProcess_main.html", "_2_2_1");
	
	map.put("migcommon/migAuditfDryrunConfig/migAuditfDryrunConfig_main.html", "_2_3_1");
	map.put("migcommon/migAuditcEnumMapping/migAuditcEnumMapping_main.html", "_2_3_2");
	map.put("migcommon/migConfigConnection/migConfigConnection_main.html", "_2_3_3");
	map.put("migcommon/migTaskConfig_SplitFile/migTaskConfig_main.html", "_2_3_4");
	map.put("migcommon/migTaskConfig_LoadData/migTaskConfig_main.html", "_2_3_5");
	map.put("migcommon/migTaskConfig_OutDataFile/migTaskConfig_main.html", "_2_3_6");
	map.put("migcommon/migTaskConfig_OutDataBase/migTaskConfig_main.html", "_2_3_7");
	
	map.put("migcommon/migJobLog/migJobLog_main.html", "_2_4_1");
	
	map.put("migcommon/migAuditvConfig/migAuditvConfig_main.html", "_2_5_1");
	map.put("migcommon/migAuditvResult/migAuditvResult_main.html", "_2_5_2");
	map.put("migcommon/migAuditvDryrun/migJobProcess_main.html", "_2_5_3");
	map.put("migcommon/migAuditvResult/migAuditvResult.html", "_2_5_4");
	
	map.put("migcommon/migAuditcConsistency/migAuditcConsistency_main.html", "_2_6_1");
	map.put("migcommon/migAuditcReportDetailResult/migAuditcReportDetailResult_main.html", "_2_6_2");
	map.put("migcommon/migAuditcDryrun/migJobProcess_main.html", "_2_6_3");
	map.put("migcommon/migAuditcReportDetailResult/migAuditcReportDetailResult.html", "_2_6_4");
	map.put("migcommon/migAuditcReportDetailResult/migAuditcReportDetailResult_gbsubdomain.html", "_2_6_5");
	map.put("migcommon/migAuditcReportDetailResult/migAuditcReportDetailResult_diff.html", "_2_6_6");
	
	map.put("migcommon/migAuditfMain/migAuditfMain_main.html", "_2_7_1");
	map.put("migcommon/migAuditfSub/migAuditfSub_main.html", "_2_7_2");
	map.put("migcommon/migAuditfDryrun/migJobProcess_main.html", "_2_7_3");
	map.put("migcommon/migAuditfMainResult/migAuditfMainResult_main.html", "_2_7_4");
	map.put("migcommon/migAuditfDetailResult/migAuditfDetailResult_main.html", "_2_7_5");
	
	
	map.put("common/tAuthorityUser/user_main.html", "_2_8_1");
	map.put("common/tAuthorityUser/tAuthorityUser_modify_self.jsp", "_2_8_2");
	map.put("common/tAuthorityUser/tAuthorityUser_PasswordModify.html", "_2_8_3");
	map.put("ckfinder/standalone.html", "_2_8_4");
	map.put("http://localhost:8080/fileBaseDir/", "_2_8_5");
	map.put("http://localhost:8080/backupDir/", "_2_8_6");
	
	String title = (null==map.get(r_url))?map.get(r_url):"index";
	
// 	System.out.println("webId:" + o);
// 	String id = (r_url.equalsIgnoreCase("welcome.html") ? "index" : "docfile/"+  map.get(r_url)  ) + ".htm";
	String id = ( null==map.get(r_url) ? "index" : "docfile/"+  map.get(r_url)  ) + ".htm";
	response.sendRedirect(id);
%>

