/** 
 * note: 
 * 		Don't support back comment
 */

/* the globel variable */
var MigComIns = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigComIns.Field = {
		"JOB_INS_ID" : [ 1, 10, 0, "ins ID", "job_ins_id", "COM_ID", 0 ],
		"COM_ID" : [ 1, 10, 0, "组件ID", "com_id", "COM_ID", 1 ],
		"PARA_ID" : [ 4, 9, 0, "参数", "para_id", "PARA_ID", 2 ],
		"PARA_VALUE" : [ 12, 128, 0, "参数值", "para_value", "PARA_VALUE", 3 ]
	};

MigComIns.Export={};
MigComIns.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigComIns.Export.export.ColumnMap = {
		"COM_ID" : [ 1, 12, 0, "组件ID", 0 ],
		"CONTROL_ID" : [ 1, 12, 0, "控件ID", 1 ],
		"PARA_ID" : [ 4, 9, 0, "参数", 2 ],
		"PARA_VALUE" : [ 12, 128, 0, "参数值", 3 ]
	};

//MigComIns.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigComIns.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigComIns.Export.export.DefaultColumns = ["COM_ID","CONTROL_ID","PARA_ID","PARA_VALUE"];
