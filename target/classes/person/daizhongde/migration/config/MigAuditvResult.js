/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditvResult = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditvResult.Field = {
		"AUDIT_ID" : [ 4, 11, 0, "", "audit_id", "AUDIT_ID", 0 ],
		FARES_DRYRUN_ID : [ 1, 20, 0, "", "fares_dryrun_id", "FARES_DRYRUN_ID", 1 ],
		MIN_ANALYSIS : [ 1, 200, 0, "", "min_analysis", "MIN_ANALYSIS", 2 ],
		"DOMAIN" : [ 1, 20, 0, "", "domain", "DOMAIN", 3 ],
		"TABLE_NAME" : [ 1, 200, 0, "", "table_name", "TABLE_NAME", 4 ],
		"AUDIT_NAME" : [ 1, 200, 0, "", "audit_name", "AUDIT_NAME", 5 ],
		SRC_VALUE : [ -1, 21845, 0, "", "src_value", "SRC_VALUE", 6 ],
		"DST_VALUE" : [ -1, 21845, 0, "", "dst_value", "DST_VALUE", 7 ],
		MIN_VALUE : [ -1, 21845, 0, "", "min_value", "MIN_VALUE", 8 ],
		"AUDIT_UNIT" : [ 1, 20, 0, "", "audit_unit", "AUDIT_UNIT", 9 ],
		"INVALID_DATA_TABLE" : [ 12, 100, 0, "", "invalid_data_table",
				"INVALID_DATA_TABLE", 10 ],
		"RESULT" : [ 4, 10, 0, "", "result", "RESULT", 11 ],
		"AUDIT_AUTHOR" : [ 1, 20, 0, "", "audit_author", "AUDIT_AUTHOR", 12 ],
		"HDATE" : [ 93, 19, 0, "", "hdate", "HDATE", 13 ],
		"REMARK" : [ 12, 1000, 0, "", "remark", "REMARK", 14 ],
		"SUCCESS_FLAG" : [ 4, 11, 0, "", "success_flag", "SUCCESS_FLAG", 15 ],
		"ERR_MSG" : [ -1, 21845, 0, "", "err_msg", "ERR_MSG", 16 ],
		"INVALID_DATA_CNT" : [ 4, 11, 0, "", "invalid_data_cnt",
				"INVALID_DATA_CNT", 17 ],
		"DMP_NUM" : [ 1, 28, 0, "", "dmp_num", "DMP_NUM", 18 ],
		"ENV" : [ 1, 1, 0, "", "env", "ENV", 19 ],
		"AUDIT_ITEM" : [ 1, 200, 0, "", "audit_item", "AUDIT_NAME", 20 ],
		"ID" : [ 2, 8, 0, "", "id", "id", 21 ],
		"SRC_AUDIT_SQL" : [ -1, 21845, 0, "", "src_audit_sql", "src_audit_sql", 22 ]
	};
MigAuditvResult.Export={};
MigAuditvResult.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditvResult.Export.export.ColumnMap = {
		"AUDIT_ID" : [ 4, 11, 0, "ID", 0 ],
		"AUDIT_CODE" : [ 1, 10, 0, "Audit Code", 1 ],
		FARES_DRYRUN_ID : [ 1, 20, 0, "DryrunID", 2 ],
		MIN_ANALYSIS : [ 1, 200, 0, "Diff Analysis", 3 ],
		"DOMAIN" : [4, 4, 0, "domain", 4 ],
		"TABLE_NAME" : [ 1, 200, 0, "Table Name", 5 ],
		"AUDIT_NAME" : [ 1, 200, 0, "Audit Name", 6 ],
		SRC_VALUE : [ -1, 21845, 0, "Source Value", 7 ],
		"DST_VALUE" : [ -1, 21845, 0, "Target Value", 8 ],
		MIN_VALUE : [ -1, 21845, 0, "Diff Value", 9 ],
		"AUDIT_UNIT" : [ 1, 20, 0, "Unit", 10 ],
		"INVALID_DATA_TABLE" : [ 12, 100, 0, "Invalid Data Table", 11 ],
		"RESULT" : [ 1, 20, 0, "Result", 12 ],
		"AUDIT_AUTHOR" : [ 1, 20, 0, "author", 13 ],
		"HDATE" : [ 93, 19, 0, "Audit Time", 14 ],
		"REMARK" : [ 12, 1000, 0, "Remark", 15 ],
		"SUCCESS_FLAG" : [ 4, 11, 0, "Success Flag", 16 ],
		"ERR_MSG" : [ -1, 21845, 0, "Error Message", 17 ],
		"INVALID_DATA_CNT" : [ 4, 11, 0, "Invalid data count", 18 ],
		"dryrun_name" : [ 12, 200, 0, "Dryrun Name", 19 ],
		"hdate2" : [ 12, 100, 0, "Audit Time",20 ],
		"success" : [ 12, 200, 0, "Success Flag", 21 ],
		"DOMAIN2" : [1, 20, 0, "Domain", 22 ],
		"DMP_NUM" : [1, 28, 0, "DefectID", 23 ],
		"ENV" : [1, 1, 0, "ENV", 24 ],
		"AUDIT_LEVEL" : [ 4, 11, 0, "LEVEL", 0 ],
		"AUDIT_ITEM" : [ 1, 200, 0, "audit item", 6 ],
		"OK" : [ 12, 200, 0, "OK", 19 ],
		"INVALID_COUNT" : [ 4, 11, 0, "invalid count", 0 ],
		"REASON" : [ 12, 200, 0, "causes", 19 ],
		"DMP_NO" : [ 12, 200, 0, "DefectID", 19 ],
		"CONFIG_AUTHOR" : [ 1, 20, 0, "author", 13 ],
		"SRC_AUDIT_SQL" : [ -1, 21845, 0,"src_audit_sql", 14 ]
	};

//MigAuditvResult.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditvResult.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditvResult.Export.export.DefaultColumns = [
"AUDIT_NAME","dryrun_name","ENV","DOMAIN2","MIN_ANALYSIS","DMP_NUM",
"TABLE_NAME","SRC_VALUE","DST_VALUE","MIN_VALUE",
"AUDIT_UNIT","INVALID_DATA_TABLE","AUDIT_AUTHOR","hdate2",
"REMARK","success","ERR_MSG","INVALID_DATA_CNT","SRC_AUDIT_SQL"];

