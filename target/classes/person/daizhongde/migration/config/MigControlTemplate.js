/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigControlTemplate = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigControlTemplate.Field = {
		"CONTROL_ID" : [ 1, 12, 0, "控件ID", "control_id", "CONTROL_ID", 0 ],
		"PARA_ID" : [ 4, 9, 0, "参数ID", "para_id", "PARA_ID", 1 ],
		"PARA_NAME" : [ 12, 20, 0, "参数名称", "para_name", "PARA_NAME", 2 ],
		"IS_NULL" : [ 4, 11, 0, "是否为空", "is_null", "IS_NULL", 3 ],
		"IS_NUMBER" : [ 4, 11, 0, "是否为数字", "is_number", "IS_NUMBER", 4 ],
		"DEF_VALUE" : [ 12, 128, 0, "默认值", "def_value", "DEF_VALUE", 5 ]
	};
MigControlTemplate.Export={};
MigControlTemplate.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigControlTemplate.Export.export.ColumnMap = {
		"CONTROL_ID" : [ 1, 12, 0, "控件ID", 0 ],
		"PARA_ID" : [ 4, 9, 0, "参数ID", 1 ],
		"PARA_NAME" : [ 12, 20, 0, "参数名称", 2 ],
		"IS_NULL" : [ 4, 11, 0, "是否为空", 3 ],
		"IS_NUMBER" : [ 4, 11, 0, "是否为数字", 4 ],
		"DEF_VALUE" : [ 12, 128, 0, "默认值", 5 ]
	};

//MigControlTemplate.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigControlTemplate.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigControlTemplate.Export.export.DefaultColumns = ["CONTROL_ID","PARA_ID","PARA_NAME","IS_NULL","IS_NUMBER","DEF_VALUE"];

