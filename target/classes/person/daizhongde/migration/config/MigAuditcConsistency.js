/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditcConsistency = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditcConsistency.Field = {
		"AUDIT_ID" : [ 4, 11, 0, "", "audit_id", "AUDIT_ID", 0 ],
		"AUDIT_NAME" : [ 1, 30, 0, "", "audit_name", "AUDIT_NAME", 1 ],
		"MIG_SQL" : [ -1, 21845, 0, "", "mig_sql", "MIG_SQL", 2 ],
		"AUTHOR" : [ 1, 20, 0, "", "author", "AUTHOR", 3 ],
		"MIG_SQL_REP" : [ 12, 512, 0, "", "mig_sql_rep", "MIG_SQL_REP", 4 ],
		"SQL_DB" : [ 12, 100, 0, "", "sql_db", "SQL_DB", 5 ]
	};

MigAuditcConsistency.Export={};
MigAuditcConsistency.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditcConsistency.Export.export.ColumnMap = {
		"AUDIT_ID" : [ 4, 11, 0, "", 0 ],
		"AUDIT_NAME" : [ 1, 30, 0, "", 1 ],
		"MIG_SQL" : [ -1, 21845, 0, "", 2 ],
		"AUTHOR" : [ 1, 20, 0, "", 3 ],
		"MIG_SQL_REP" : [ 12, 512, 0, "", 4 ]
	};

//MigAuditcConsistency.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditcConsistency.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditcConsistency.Export.export.DefaultColumns = ["AUDIT_ID","AUDIT_NAME","MIG_SQL","AUTHOR","MIG_SQL_REP"];

