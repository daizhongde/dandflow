/** note: 
 * 		Don't support back comment
 */


/* the globel variable */
var MigInsPara = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigInsPara.Field ={
		"JOB_INS_ID" : [ 1, 10, 0, "", "jobInsId", "JOB_INS_ID", 0 ],
		"NODE_ID" : [ 1, 10, 0, "a", "nodeId", "NODE_ID", 1 ],
		"PARA" : [ 1, 20, 0, "d", "para", "PARA", 2 ],
		"PARA_NAME" : [ 12, 64, 0, "c", "paraName", "PARA_NAME", 3 ],
		"PARA_TYPE" : [ 4, 3, 0, "d", "paraType", "PARA_TYPE", 4 ],
		"PARA_VALUE" : [ 12, 254, 0, "e", "paraValue", "PARA_VALUE", 5 ]
	};

MigInsPara.Export={};
MigInsPara.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigInsPara.Export.export.ColumnMap = {
		"TASK" : [ 1, 12, 0, "任务", 0 ],
		"PARA" : [ 1, 20, 0, "参数类型", 1 ],
		"PARA_TYPE" : [ 4, 1, 0, "参数类型", 2 ],
		"PARA_VALUE" : [ 1, 254, 0, "参数值", 3 ],
		"PARA_NAME" : [ 12, 64, 0, "参数中文名", 4 ]
	};

//MigInsPara.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigInsPara.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigInsPara.Export.export.DefaultColumns = ["TASK","PARA","PARA_TYPE","PARA_VALUE","PARA_NAME"];
