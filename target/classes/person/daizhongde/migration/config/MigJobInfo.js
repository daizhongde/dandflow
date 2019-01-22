/** note: 
 * 		Don't support back comment */
/* the globel variable */
var MigJobInfo = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigJobInfo.Field ={
		"JOB_ID" : [ 1, 10, 0, "a", "id", "JOB_ID", 0 ],
		"JOB_NAME" : [ 12, 64, 0, "", "name", "JOB_NAME", 1 ],
		"type" : [ 4, 4, 0, "", "type", "TYPE", 2 ],
		"JOB_AUTHOR" : [ 12, 64, 0, "", "author", "JOB_AUTHOR", 3 ],
		"JOB_REMARK" : [ 12, 64, 0, "", "remark", "JOB_REMARK", 4 ],
		"JOB_UPDATE" : [ 93, 19, 0, "", "updatetime", "JOB_UPDATE", 5 ]
	};

MigJobInfo.Export={};
MigJobInfo.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigJobInfo.Export.export.ColumnMap = {
		"JOB_ID" : [ 1, 12, 0, "作业ID", 0 ],
		"JOB_NAME" : [ 12, 64, 0, "作业名称", 1 ],
		"JOB_CRON" : [ 12, 20, 0, "作业定时器", 2 ],
		"JOB_AUTHOR" : [ 12, 64, 0, "作业创建者", 3 ],
		"JOB_REMARK" : [ 12, 20, 0, "作业备注", 4 ],
		"JOB_UPDATE" : [ 93, 19, 0, "作业更新时间", 5 ]
	};

//MigJobInfo.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigJobInfo.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigJobInfo.Export.export.DefaultColumns = ["JOB_ID","JOB_NAME","JOB_CRON","JOB_AUTHOR","JOB_REMARK","JOB_UPDATE"];
