/** note: 

 * 		Don't support back comment */
/* the globel variable */
var MigStaticprarDefine = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigStaticprarDefine.Field = {
		"PARA" : [ 12, 20, 0, "参数类型", "para", "PARA", 0 ],
		"PARA_NAME" : [ 12, 20, 0, "参数中文名", "para_name", "PARA_NAME", 1 ],
		"PARA_TYPE" : [ 4, 11, 0, "参数类型", "para_type", "PARA_TYPE", 2 ],
		"PARA_VALUE" : [ 12, 254, 0, "参数值", "para_value", "PARA_VALUE", 3 ]
	};

MigStaticprarDefine.Export={};
MigStaticprarDefine.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigStaticprarDefine.Export.export.ColumnMap = {
		"PARA" : [ 12, 20, 0, "参数类型", 0 ],
		"PARA_NAME" : [ 12, 20, 0, "参数中文名", 1 ],
		"PARA_TYPE" : [ 4, 11, 0, "参数类型", 2 ],
		"PARA_VALUE" : [ 12, 254, 0, "参数值", 3 ]
	};

//MigStaticprarDefine.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigStaticprarDefine.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigStaticprarDefine.Export.export.DefaultColumns = ["PARA","PARA_NAME","PARA_TYPE","PARA_VALUE"];
