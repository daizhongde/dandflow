/** note: 

 * 		Don't support back comment */
/* the globel variable */
var MigLoadMap = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigLoadMap.Field = {
		"tname" : [ 12, 30, 0, "", "tname", "TNAME", 0 ],
		"htype" : [ 12, 30, 0, "", "htype", "HTYPE", 1 ],
		"src_column" : [ 12, 30, 0, "", "src_column", "SRC_COLUMN", 2 ],
		"tag_column" : [ 12, 30, 0, "", "tag_column", "TAG_COLUMN", 3 ],
		"default_value" : [ 12, 30, 0, "", "default_value", "DEFAULT_VALUE", 4 ],
		"s_sql" : [ -1, 21845, 0, "", "s_sql", "S_SQL", 5 ]
	};

MigLoadMap.Export={};
MigLoadMap.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigLoadMap.Export.export.ColumnMap = {
		"tname" : [ 12, 30, 0, "", 0 ],
		"htype" : [ 12, 30, 0, "", 1 ],
		"src_column" : [ 12, 30, 0, "", 2 ],
		"tag_column" : [ 12, 30, 0, "", 3 ],
		"default_value" : [ 12, 30, 0, "", 4 ],
		"s_sql" : [ -1, 21845, 0, "", 5 ]
	};

//MigLoadMap.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigLoadMap.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigLoadMap.Export.export.DefaultColumns = ["tname","htype","src_column","tag_column","default_value","s_sql"];
