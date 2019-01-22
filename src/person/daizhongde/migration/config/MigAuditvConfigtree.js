/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditvConfigtree = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditvConfigtree.Field = {
		"id" : [ 4, 11, 0, "id", "id", "ID", 0 ],
		"parent" : [ 12, 128, 0, "父节点", "parent", "PARENT", 1 ],
		"name" : [ 12, 256, 0, "名称", "name", "NAME", 2 ],
		"content" : [ 4, 11, 0, "", "content", "CONTENT", 3 ],
		"isleaf" : [ 5, 4, 0, "1：叶子", "isleaf", "ISLEAF", 4 ],
		"status" : [ 5, 4, 0, "1：valid", "status", "STATUS", 5 ],
		"remark" : [ 12, 300, 0, "备注", "remark", "REMARK", 6 ],
		"ctime" : [ 93, 19, 0, "", "ctime", "CTIME", 7 ]
	};

MigAuditvConfigtree.Export={};
MigAuditvConfigtree.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditvConfigtree.Export.export.ColumnMap = {
		"id" : [ 4, 11, 0, "id", 0 ],
		"parent" : [ 12, 128, 0, "父节点", 1 ],
		"name" : [ 12, 256, 0, "名称", 2 ],
		"content" : [ 4, 11, 0, "", 3 ],
		"isleaf" : [ 5, 4, 0, "1：叶子", 4 ],
		"status" : [ 5, 4, 0, "1：valid", 5 ],
		"remark" : [ 12, 300, 0, "备注", 6 ],
		"ctime" : [ 93, 19, 0, "", 7 ]
	};

//MigAuditvConfigtree.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditvConfigtree.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditvConfigtree.Export.export.DefaultColumns = ["id","parent","name","content","isleaf","status","remark","ctime"];

