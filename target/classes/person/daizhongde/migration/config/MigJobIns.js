/** note: 

 * 		Don't support back comment */
/* the globel variable */
var MigJobIns = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigJobIns.Field ={
		"JOB_INS_ID" : [ 1, 10, 0, "", "job_ins_id", "JOB_INS_ID", 0 ],
		"JOB_ID" : [ 1, 10, 0, "b", "job_id", "JOB_ID", 1 ],
		"JOB_INS_NAME" : [ 12, 64, 0, "", "job_ins_name", "JOB_INS_NAME", 2 ],
		"type" : [ 4, 4, 0, "", "type", "TYPE", 3 ],
		"STATUS" : [ 1, 1, 0, "h", "status", "STATUS", 4 ],
		"MTIME" : [ 93, 19, 0, "modify", "mtime", "MTIME", 5 ],
		"AUTHOR" : [ 12, 64, 0, "", "author", "AUTHOR", 6 ],
		"REMARK" : [ 12, 20, 0, "", "remark", "REMARK", 7 ],
		"LOCK_STATUS" : [ 1, 1, 0, "h", "lock_status", "LOCK_STATUS", 8 ],
		"running" : [ 4, 3, 0, "运行状态", "running", "RUNNING", 9 ],
		"dryrun_id" : [ 4, 3, 0, "运行状态", "dryrun_id", "dryrunId", 10 ]
	};

MigJobIns.Export={};
MigJobIns.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigJobIns.Export.export.ColumnMap = {
		"JOB_ID" : [ 1, 12, 0, "作业ID", 0 ],
		"JOB_NAME" : [ 12, 64, 0, "作业名称", 1 ],
		"JOB_CRON" : [ 12, 20, 0, "作业定时器", 2 ],
		"JOB_AUTHOR" : [ 12, 64, 0, "作业创建者", 3 ],
		"JOB_REMARK" : [ 12, 20, 0, "作业备注", 4 ],
		"JOB_UPDATE" : [ 93, 19, 0, "作业更新时间", 5 ]
	};

//MigJobIns.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigJobIns.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigJobIns.Export.export.DefaultColumns = ["JOB_INS_ID","JOB_ID","JOB_INS_NAME","STATUS","MTIME","AUTHOR","REMARK","LOCK_STATUS"];
