/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigCodedetailDefine = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigCodedetailDefine.Field = {
		"id" : [ 4, 5, 0, "", "id", "ID", 0 ],
		"TYPE" : [ 12, 64, 0, "参数类型", "type", "TYPE", 1 ],
		"code" : [ 12, 20, 0, "参数", "code", "CODE", 2 ],
		"value" : [ 12, 254, 0, "参数值", "value", "VALUE", 3 ],
		"remark" : [ 12, 1024, 0, "备注", "remark", "REMARK", 4 ]
	};

MigCodedetailDefine.Export={};
MigCodedetailDefine.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigCodedetailDefine.Export.export.ColumnMap = {
		"id" : [ 4, 5, 0, "id", 0 ],
		"TYPE" : [ 12, 64, 0, "参数类型", 1 ],
		"code" : [ 12, 20, 0, "参数", 2 ],
		"value" : [ 12, 254, 0, "参数值", 3 ],
		"remark" : [ 12, 1024, 0, "备注", 4 ]
	};

//MigCodedetailDefine.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigCodedetailDefine.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigCodedetailDefine.Export.export.DefaultColumns = ["id","TYPE","code","value","remark"];

