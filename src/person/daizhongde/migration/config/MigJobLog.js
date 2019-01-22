/** note: 

 * 		Don't support back comment */
/* the globel variable */
var MigJobLog = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigJobLog.Field = {
	"LOG_ID" : [ 1, 20, 0, "日志ID", "log_id", "LOG_ID", 0 ],
	"JOB_INS_ID" : [ 1, 10, 0, "实例ID", "job_ins_id", "JOB_INS_ID", 1 ],
	"TASK_ID" : [ 1, 10, 0, "任务ID", "task_id", "TASK_ID", 2 ],
	"LEVEL" : [ 4, 11, 0, "任务级别", "level", "LEVEL", 3 ],
	"LOG_MSG" : [ 12, 4096, 0, "日志信息", "log_msg", "LOG_MSG", 4 ],
	"CTIME" : [ 93, 19, 0, "记录时间", "ctime", "CTIME", 5 ],
	"REMArk" : [ 12, 128, 0, "备注", "remark", "REMARK", 6 ]
};

MigJobLog.Export={};
MigJobLog.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigJobLog.Export.export.ColumnMap = {
		"LOG_ID" : [ 1, 12, 0, "日志ID", 0 ],
		"PROCESS_ID" : [ 1, 12, 0, "过程ID", 1 ],
		"LOG_MSG" : [ 12, 254, 0, "日志信息", 2 ],
		BEGIN_TIME : [ 93, 19, 0, "开始时间", 3 ],
		"END_TIME" : [ 93, 19, 0, "结束时间", 4 ],
		"REMAKR" : [ 12, 128, 0, "备注", 5 ]
	};

//MigJobLog.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigJobLog.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigJobLog.Export.export.DefaultColumns = ["LOG_ID","PROCESS_ID","LOG_MSG","BEGIN_TIME","END_TIME","REMAKR"];
