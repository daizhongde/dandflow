/** note: 

 * 		Don't support back comment */
/* the globel variable */
var MigTaskRel = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigTaskRel.Field = {
		"TASK_ID" : [ 1, 12, 0, "任务ID", "task_id", "TASK_ID", 0 ],
		"SUBTASK_ID" : [ 1, 12, 0, "子任务ID", "subtask_id", "SUBTASK_ID", 1 ],
		"TASK_STATUS" : [ 1, 1, 0, "任务状态", "task_status", "TASK_STATUS", 2 ],
		"PREPOS" : [ 12, 256, 0, "前置任务", "prepos", "PREPOS", 3 ],
		"POSTPOS" : [ 12, 256, 0, "后置任务", "postpos", "POSTPOS", 4 ],
		"LOCK_STATUS" : [ 1, 1, 0, "锁定状态", "lock_status", "LOCK_STATUS", 5 ]
	};

MigTaskRel.Export={};
MigTaskRel.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigTaskRel.Export.export.ColumnMap = {
		"TASK_ID" : [ 1, 12, 0, "任务ID", 0 ],
		"SUBTASK_ID" : [ 1, 12, 0, "子任务ID", 1 ],
		"TASK_STATUS" : [ 1, 1, 0, "任务状态", 2 ],
		"PREPOS" : [ 12, 256, 0, "前置任务", 3 ],
		"POSTPOS" : [ 12, 256, 0, "后置任务", 4 ],
		"LOCK_STATUS" : [ 1, 1, 0, "锁定状态", 5 ]
	};

//MigTaskRel.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigTaskRel.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigTaskRel.Export.export.DefaultColumns = ["TASK_ID","SUBTASK_ID","TASK_STATUS","PREPOS","POSTPOS","LOCK_STATUS"];
