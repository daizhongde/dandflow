/** note: 
 * 		Don't support back comment */
/* the globel variable */
var MigControlInfo = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigControlInfo.Field = {
		"CONTROL_ID" : [ 1, 12, 0, "控件ID", "control_id", "CONTROL_ID", 0 ],
		"CONTROL_NAME" : [ 12, 20, 0, "控件名称", "control_name", "CONTROL_NAME", 1 ],
		"CONTROL_MARK" : [ 12, 128, 0, "控件备注", "control_mark", "CONTROL_MARK", 2 ]
	};

MigControlInfo.Export={};
MigControlInfo.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigControlInfo.Export.export.ColumnMap = {
		"CONTROL_ID" : [ 1, 12, 0, "控件ID", 0 ],
		"CONTROL_NAME" : [ 12, 20, 0, "控件名称", 1 ],
		"CONTROL_MARK" : [ 12, 128, 0, "控件备注", 2 ]
	};

//MigControlInfo.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigControlInfo.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigControlInfo.Export.export.DefaultColumns = ["CONTROL_ID","CONTROL_NAME","CONTROL_MARK"];
