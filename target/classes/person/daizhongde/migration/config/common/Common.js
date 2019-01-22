/** 
 * note: 
 * 		Don't support back comment
 */

/* the globel variable */
var Common = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 * 2-char, 12-varchar, 1-number
 * attemtion : columnLabel and back must UpperCase
 */
Common.Field = {
		"MIG_DRYRUN_ID" : [ 1, 12, 0, "排练ID", "mig_dryrun_id", "MIG_DRYRUN_ID", 0 ],
		"MIG_DRYRUN_NAME" : [ 12, 128, 0, "排练", "mig_dryrun_name", "MIG_DRYRUN_NAME", 1 ],
		"PARA_ID" : [ 4, 9, 0, "参数", "para_id", "PARA_ID", 2 ],
		"PARA_VALUE" : [ 12, 128, 0, "参数值", "para_value", "PARA_VALUE", 3 ]
	};

Common.Export={};
Common.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
Common.Export.export.ColumnMap = {
		"COM_ID" : [ 1, 12, 0, "组件ID", 0 ],
		"CONTROL_ID" : [ 1, 12, 0, "控件ID", 1 ],
		"PARA_ID" : [ 4, 9, 0, "参数", 2 ],
		"PARA_VALUE" : [ 12, 128, 0, "参数值", 3 ]
	};

//Common.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//Common.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
Common.Export.export.DefaultColumns = ["COM_ID","CONTROL_ID","PARA_ID","PARA_VALUE"];
