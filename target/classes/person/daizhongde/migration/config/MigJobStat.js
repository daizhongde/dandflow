/** note: 


 * 		Don't support back comment */
/* the globel variable */
var MigJobStat = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigJobStat.Field = {
		"LOG_ID" : [ 1, 12, 0, "日志ID", "log_id", "LOG_ID", 0 ],
		"JOB_ID" : [ 1, 12, 0, "作业ID", "job_id", "JOB_ID", 1 ],
		BEGIN_TIME : [ 93, 19, 0, "开始时间", "begin_time", "BEGIN_TIME", 2 ],
		"END_TIME" : [ 93, 19, 0, "结束时间", "end_time", "END_TIME", 3 ],
		"STATUS" : [ 4, 11, 0, "状态", "status", "STATUS", 4 ],
		"REMAKR" : [ 12, 128, 0, "备注", "remakr", "REMAKR", 5 ]
	};

MigJobStat.Export={};
MigJobStat.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigJobStat.Export.export.ColumnMap = {
		"LOG_ID" : [ 1, 12, 0, "日志ID", 0 ],
		"JOB_ID" : [ 1, 12, 0, "作业ID", 1 ],
		BEGIN_TIME : [ 93, 19, 0, "开始时间", 2 ],
		"END_TIME" : [ 93, 19, 0, "结束时间", 3 ],
		"STATUS" : [ 4, 11, 0, "状态", 4 ],
		"REMAKR" : [ 12, 128, 0, "备注", 5 ]
	};

//MigJobStat.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigJobStat.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigJobStat.Export.export.DefaultColumns = ["LOG_ID","JOB_ID","BEGIN_TIME","END_TIME","STATUS","REMAKR"];
