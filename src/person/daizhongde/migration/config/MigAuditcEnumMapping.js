/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditfMainResult = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditfMainResult.Field = {
		"id" : [ 4, 10, 0, "", "id", "ID", 0 ],
		"ENTITY" : [ 1, 30, 0, "", "entity", "ENTITY", 1 ],
		"AUDIT_NAME" : [ 1, 30, 0, "", "audit_name", "AUDIT_NAME", 2 ],
		"ENUM_DESC" : [ 1, 30, 0, "", "enum_desc", "ENUM_DESC", 3 ],
		SRC_ENUM : [ 1, 12, 0, "", "src_enum", "SRC_ENUM", 4 ],
		"DST_ENUM" : [ 1, 12, 0, "", "dst_enum", "DST_ENUM", 5 ]
	};
MigAuditfMainResult.Export={};
MigAuditfMainResult.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditfMainResult.Export.export.ColumnMap = {
		"id" : [ 4, 10, 0, "", 0 ],
		"ENTITY" : [ 1, 30, 0, "", 1 ],
		"AUDIT_NAME" : [ 1, 30, 0, "", 2 ],
		"ENUM_DESC" : [ 1, 30, 0, "", 3 ],
		SRC_ENUM : [ 1, 12, 0, "", 4 ],
		"DST_ENUM" : [ 1, 12, 0, "", 5 ]
	};

//MigAuditfMainResult.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditfMainResult.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditfMainResult.Export.export.DefaultColumns = ["id","ENTITY","AUDIT_NAME","ENUM_DESC","SRC_ENUM","DST_ENUM"];

