/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditvConfig = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditvConfig.Field = {
		"AUDIT_ID" : [ 4, 11, 0, "", "audit_id", "AUDIT_ID", 0 ],
		"AUDIT_CODE" : [ 1, 10, 0, "", "audit_code", "AUDIT_CODE", 1 ],
		"DOMAIN" : [ 4, 4, 0, "", "domain", "DOMAIN", 2 ],
		"TABLE_NAME" : [ 1, 200, 0, "", "table_name", "TABLE_NAME", 3 ],
		"AUDIT_NAME" : [ 1, 200, 0, "", "audit_name", "AUDIT_NAME", 4 ],
		"AUDIT_TYPE" : [ 1, 4, 0, "", "audit_type", "AUDIT_TYPE", 5 ],
		"SQL_TYPE" : [ 1, 4, 0, "", "sql_type", "SQL_TYPE", 6 ],
		SRC_AUDIT_SQL : [ -1, 21845, 0, "", "src_audit_sql", "SRC_AUDIT_SQL", 7 ],
		"DST_AUDIT_SQL" : [ -1, 21845, 0, "", "dst_audit_sql", "DST_AUDIT_SQL", 8 ],
		"AUDIT_VALUE" : [ -1, 21845, 0, "", "audit_value", "AUDIT_VALUE", 9 ],
		"AUDIT_FLAG" : [ 1, 1, 0, "", "audit_flag", "AUDIT_FLAG", 10 ],
		"INVALID_DATA_SQL" : [ -1, 21845, 0, "", "invalid_data_sql",
				"INVALID_DATA_SQL", 11 ],
		"OPERATOR" : [ 1, 10, 0, "", "operator", "OPERATOR", 12 ],
		"AUDIT_UNIT" : [ 1, 20, 0, "", "audit_unit", "AUDIT_UNIT", 13 ],
		"AUDIT_AUTHOR" : [ 1, 20, 0, "", "audit_author", "AUDIT_AUTHOR", 14 ],
		"REMARK" : [ 12, 1000, 0, "", "remark", "REMARK", 15 ],
		SRC_DB_CONNECT : [ 1, 40, 0, "", "src_db_connect", "SRC_DB_CONNECT", 16 ],
		"DST_DB_CONNECT" : [ 1, 40, 0, "", "dst_db_connect", "DST_DB_CONNECT", 17 ],
		"MIG_SQL_REP" : [ 12, 512, 0, "", "mig_sql_rep", "MIG_SQL_REP", 18 ],
		"VERSION" : [ 1, 10, 0, "", "version", "VERSION", 19 ],
		"ctime" : [ 93, 19, 0, "创建时间", "ctime", "ctime", 20 ],
		"AUDIT_LEVEL" : [ 4, 4, 0, "", "audit_level", "AUDIT_LEVEL", 21 ]
	};
MigAuditvConfig.Export={};
MigAuditvConfig.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditvConfig.Export.export.ColumnMap = {
		"AUDIT_ID" : [ 4, 11, 0, "", 0 ],
		"AUDIT_CODE" : [ 1, 10, 0, "", 1 ],
		"DOMAIN" : [ 1, 20, 0, "", 2 ],
		"TABLE_NAME" : [ 1, 200, 0, "", 3 ],
		"AUDIT_NAME" : [ 1, 200, 0, "", 4 ],
		"AUDIT_TYPE" : [ 1, 4, 0, "", 5 ],
		"SQL_TYPE" : [ 1, 4, 0, "", 6 ],
		SRC_AUDIT_SQL : [ -1, 21845, 0, "", 7 ],
		"DST_AUDIT_SQL" : [ -1, 21845, 0, "", 8 ],
		"AUDIT_VALUE" : [ -1, 21845, 0, "", 9 ],
		"AUDIT_FLAG" : [ 1, 1, 0, "", 10 ],
		"INVALID_DATA_SQL" : [ -1, 21845, 0, "", 11 ],
		"OPERATOR" : [ 1, 10, 0, "", 12 ],
		"AUDIT_UNIT" : [ 1, 20, 0, "", 13 ],
		"AUDIT_AUTHOR" : [ 1, 20, 0, "", 14 ],
		"REMARK" : [ 12, 1000, 0, "", 15 ],
		SRC_DB_CONNECT : [ 1, 40, 0, "", 16 ],
		"DST_DB_CONNECT" : [ 1, 40, 0, "", 17 ],
		"MIG_SQL_REP" : [ 12, 512, 0, "", 18 ],
		"VERSION" : [ 1, 10, 0, "", 19 ]
	};

//MigAuditvConfig.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditvConfig.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditvConfig.Export.export.DefaultColumns = ["AUDIT_ID","AUDIT_CODE","DOMAIN","TABLE_NAME","AUDIT_NAME","AUDIT_TYPE","SQL_TYPE","SRC_AUDIT_SQL","DST_AUDIT_SQL","AUDIT_VALUE","AUDIT_FLAG","INVALID_DATA_SQL","OPERATOR","AUDIT_UNIT","AUDIT_AUTHOR","REMARK","SRC_DB_CONNECT","DST_DB_CONNECT","MIG_SQL_REP","VERSION"];

