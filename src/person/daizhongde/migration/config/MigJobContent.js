/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigJobContent = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigJobContent.Field = {
		"JOB_ID" : [ 1, 12, 0, "作业ID", "job_id", "JOB_ID", 0 ],
		"TASK_ID" : [ 1, 12, 0, "任务ID", "task_id", "TASK_ID", 1 ],
		"TASK_STATUS" : [ 1, 1, 0, "任务状态", "task_status", "TASK_STATUS", 2 ],
		"PREPOS" : [ 12, 256, 0, "前置作业", "prepos", "PREPOS", 3 ],
		"POSTPOS" : [ 12, 256, 0, "后置作业", "postpos", "POSTPOS", 4 ],
		"LOCK_STATUS" : [ 1, 1, 0, "锁定状态", "lock_status", "LOCK_STATUS", 5 ]
	};

MigJobContent.Export={};
MigJobContent.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigJobContent.Export.export.ColumnMap = {
		"JOB_ID" : [ 1, 12, 0, "作业ID", 0 ],
		"TASK_ID" : [ 1, 12, 0, "任务ID", 1 ],
		"TASK_STATUS" : [ 1, 1, 0, "任务状态", 2 ],
		"PREPOS" : [ 12, 256, 0, "前置作业", 3 ],
		"POSTPOS" : [ 12, 256, 0, "后置作业", 4 ],
		"LOCK_STATUS" : [ 1, 1, 0, "锁定状态", 5 ]
	};

//MigJobContent.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigJobContent.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigJobContent.Export.export.DefaultColumns = ["JOB_ID","TASK_ID","TASK_STATUS","PREPOS","POSTPOS","LOCK_STATUS"];
