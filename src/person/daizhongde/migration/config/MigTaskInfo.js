/** note: 

 * 		Don't support back comment */
/* the globel variable */
var MigTaskInfo = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigTaskInfo.Field = {
		"TASK_ID" : [ 1, 12, 0, "任务ID", "task_id", "TASK_ID", 0 ],
		"TASK_NAME" : [ 12, 64, 0, "参数名称", "task_name", "TASK_NAME", 1 ],
		"TASK_NODE" : [ 1, 1, 0, "节点属性", "task_node", "TASK_NODE", 2 ],
		"COM_ID" : [ 1, 12, 0, "功能ID", "com_id", "COM_ID", 3 ],
		"TASK_AUTHOR" : [ 12, 64, 0, "任务作者", "task_author", "TASK_AUTHOR", 4 ],
		"TASK_REMARK" : [ 12, 20, 0, "任务备注", "task_remark", "TASK_REMARK", 5 ],
		"TASK_UPDATE" : [ 12, 20, 0, "任务更新者", "task_update", "TASK_UPDATE", 6 ]
	};

MigTaskInfo.Export={};
MigTaskInfo.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigTaskInfo.Export.export.ColumnMap = {
		"TASK_ID" : [ 1, 12, 0, "任务ID", 0 ],
		"TASK_NAME" : [ 12, 64, 0, "参数名称", 1 ],
		"TASK_NODE" : [ 1, 1, 0, "节点属性", 2 ],
		"COM_ID" : [ 1, 12, 0, "功能ID", 3 ],
		"TASK_AUTHOR" : [ 12, 64, 0, "任务作者", 4 ],
		"TASK_REMARK" : [ 12, 20, 0, "任务备注", 5 ],
		"TASK_UPDATE" : [ 12, 20, 0, "任务更新者", 6 ]
	};

//MigTaskInfo.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigTaskInfo.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigTaskInfo.Export.export.DefaultColumns = ["TASK_ID","TASK_NAME","TASK_NODE","COM_ID","TASK_AUTHOR","TASK_REMARK","TASK_UPDATE"];
