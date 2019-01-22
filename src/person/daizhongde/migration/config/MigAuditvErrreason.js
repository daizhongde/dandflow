/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditvErrreason = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditvErrreason.Field = {
		"audit_id" : [ 4, 8, 0, "", "audit_id", "AUDIT_ID", 0 ],
		"dmp_no" : [ 1, 28, 0, "", "dmp_no", "DMP_NO", 1 ],
		"reason" : [ 12, 1024, 0, "", "reason", "REASON", 2 ],
		"env" : [ 1, 1, 0, "C-connextion", "env", "ENV", 3 ]
	};

MigAuditvErrreason.Export={};
MigAuditvErrreason.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditvErrreason.Export.export.ColumnMap = {
		"audit_id" : [ 4, 8, 0, "", 0 ],
		"dmp_no" : [ 1, 28, 0, "", 1 ],
		"reason" : [ 12, 1024, 0, "", 2 ],
		"env" : [ 1, 1, 0, "C-connextion", 3 ]
	};

//MigAuditvErrreason.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditvErrreason.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditvErrreason.Export.export.DefaultColumns = ["AUDIT_ID","AUDIT_NAME","MIG_SQL","AUTHOR","MIG_SQL_REP"];

