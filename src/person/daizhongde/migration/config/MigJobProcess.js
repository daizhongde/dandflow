/** 
 * note: 

 * 		Don't support back comment
 */
/* the globel variable  */
var MigJobProcess = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigJobProcess.Field = {
		"PROCESS_ID" : [ 1, 10, 0, "a", "process_id", "PROCESS_ID", 0 ],
		"JOB_INS_ID" : [ 1, 10, 0, "", "job_ins_id", "JOB_INS_ID", 1 ],
		"JOB_ID" : [ 1, 10, 0, "c", "job_id", "JOB_ID", 2 ],
		"NODE_ID" : [ 1, 10, 0, "d", "node_id", "NODE_ID", 3 ],
		"ISLEAF" : [ 4, 3, 0, "0非叶子", "isleaf", "ISLEAF", 4 ],
		"NODE_NAME" : [ 12, 64, 0, "", "node_name", "NODE_NAME", 5 ],
		"NODE_REMARK" : [ 12, 64, 0, "", "node_remark", "NODE_REMARK", 6 ],
		"CONTROL_ID" : [ 1, 10, 0, "x", "control_id", "CONTROL_ID", 7 ],
		"COM_ID" : [ 1, 10, 0, "", "com_id", "COM_ID", 8 ],
		"STATUS" : [ 4, 11, 0, "x", "status", "STATUS", 9 ],
		"PREPOS" : [ 12, 256, 0, "x", "prepos", "PREPOS", 10 ],
		"POSTPOS" : [ 12, 256, 0, "x", "postpos", "POSTPOS", 11 ],
		"CREATEDATE" : [ 93, 19, 0, "x", "createdate", "CREATEDATE", 12 ],
		"REMARK" : [ 12, 1024, 0, "", "remark", "REMARK", 13 ],
		"COORDS" : [ 1, 9, 0, "", "coords", "COORDS", 14 ]
	};

MigJobProcess.Export={};
MigJobProcess.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigJobProcess.Export.export.ColumnMap = {
		"PROCESS_ID" : [ 1, 12, 0, "过程ID", 0 ],
		"JOB_ID" : [ 1, 12, 0, "作业ID", 1 ],
		"NODE" : [ 1, 12, 0, "节点", 2 ],
		"SUBNODE" : [ 1, 12, 0, "子节点", 3 ],
		"POSTPOS" : [ 12, 254, 0, "后置任务", 4 ],
		"STATUS" : [ 4, 11, 0, "状态", 5 ],
		"CREATEDATE" : [ 93, 19, 0, "创建时间", 6 ],
		"REMAKR" : [ 12, 128, 0, "备注", 7 ],
		"PREPOS" : [ 12, 254, 0, "前置任务", 8 ]
	};

//MigJobProcess.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigJobProcess.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigJobProcess.Export.export.DefaultColumns = ["AREA_CD","AREA_FG","MERCH_SEQ_ID","AREA_NM"];
