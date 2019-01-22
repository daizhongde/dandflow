/** 
 * note: 
 * 		Don't support back comment
 */
/* the globel variable */
var MigJobPara = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigJobPara.Field ={
		"NODE_ID" : [ 1, 12, 0, "节点", "nodeId", "nodeId", 0 ],
		"PARA" : [ 1, 20, 0, "参数类型", "para", "para", 1 ],
		"PARA_TYPE" : [ 4, 1, 0, "参数类型", "paraType", "paraType", 2 ],
		"PARA_VALUE" : [ 1, 254, 0, "参数值", "paraValue", "paraValue", 3 ],
		"PARA_NAME" : [ 12, 64, 0, "参数中文名", "paraName", "paraName", 4 ]
	};

MigJobPara.Export={};
MigJobPara.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigJobPara.Export.export.ColumnMap = {
		"NODE_ID" : [ 1, 12, 0, "节点", 0 ],
		"PARA" : [ 1, 20, 0, "参数类型", 1 ],
		"PARA_TYPE" : [ 4, 1, 0, "参数类型", 2 ],
		"PARA_VALUE" : [ 1, 254, 0, "参数值", 3 ],
		"PARA_NAME" : [ 12, 64, 0, "参数中文名", 4 ]
	};

//MigJobPara.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigJobPara.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigJobPara.Export.export.DefaultColumns = ["NODE_ID","PARA","PARA_TYPE","PARA_VALUE","PARA_NAME"];
