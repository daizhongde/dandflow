/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditPrecheck = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditPrecheck.Field = {
		"AUDIT_ID" : [ 4, 20, 0, "", "audit_id", "AUDIT_ID", 0 ],
		"AUDIT_SCHEMA"   : [12,  21, 0, "", "audit_schema",   "AUDIT_SCHEMA",   1],
		"AUDIT_ENV" : [ 12, 4, 0, "", "audit_env", "AUDIT_ENV", 2 ],
		"AUDIT_TYPE" : [ 12, 4, 0, "", "audit_type", "AUDIT_TYPE", 3 ],
		"AUDIT_MODE" : [ 12, 4, 0, "", "audit_mode", "AUDIT_MODE", 4 ],
		"AUDIT_OBJECT" : [ 12, 254, 0, "", "audit_object", "AUDIT_OBJECT", 5 ],
		DRYRUN_FRONT : [ 12, 12, 0, "", "dryrun_front", "DRYRUN_FRONT", 6 ],
		DRYRUN_BACK : [ 12, 12, 0, "", "dryrun_back", "DRYRUN_BACK", 7 ],
		"COUNT_FRONT" : [ 4, 11, 0, "", "count_front", "COUNT_FRONT", 8 ],
		"COUNT_BACK" : [ 4, 11, 0, "", "count_back", "COUNT_BACK", 9 ],
		"DIFF_RATIO" : [ 4, 4, 0, "", "diff_ratio", "DIFF_RATIO", 10 ],
		"AUDIT_DATE" : [ 93, 19, 0, "", "audit_date", "AUDIT_DATE", 11 ],
		"AUDIT_REMARK" : [ 12, 254, 0, "", "audit_remark", "AUDIT_REMARK", 12 ]
	};

MigAuditPrecheck.Export={};
MigAuditPrecheck.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditPrecheck.Export.export.ColumnMap = {
		"AUDIT_ID" : [ 4, 20, 0, "AUDIT_ID", 0 ],
		"AUDIT_SCHEMA" : [12,21,0,"AUDIT_SCHEMA",1],
		"AUDIT_ENV" : [ 12, 4, 0, "AUDIT_ENV", 2 ],
		"AUDIT_TYPE" : [ 12, 4, 0, "AUDIT_TYPE", 3 ],
		"AUDIT_MODE" : [ 12, 4, 0, "AUDIT_MODE", 4 ],
		"AUDIT_OBJECT" : [ 12, 254, 0, "AUDIT_OBJECT", 5 ],
		DRYRUN_FRONT : [ 12, 12, 0, "DRYRUN_FRONT", 6 ],
		DRYRUN_BACK : [ 12, 12, 0, "DRYRUN_BACK", 7 ],
		"COUNT_FRONT" : [ 4, 11, 0, "COUNT_FRONT", 8 ],
		"COUNT_BACK" : [ 4, 11, 0, "COUNT_BACK", 9 ],
		"DIFF_RATIO" : [ 4, 4, 0, "DIFF_RATIO", 10 ],
		"AUDIT_DATE" : [ 93, 19, 0, "AUDIT_DATE", 11 ],
		"AUDIT_REMARK" : [ 12, 254, 0, "AUDIT_REMARK", 12 ],
		"ENV_NAME" : [ 12, 30, 0, "AUDIT_ENV", 13 ],
		"TYPE_NAME" : [ 12, 30, 0, "AUDIT_TYPE", 14 ],
		"MODE_NAME" : [ 12, 30, 0, "AUDIT_MODE", 15 ],
		FRONT_DRNAME : [ 12, 12, 0, "DRYRUN_FRONT", 16 ],
		BACK_DRNAME : [ 12, 12, 0, "DRYRUN_BACK", 17 ],
		"DIFF_RATIO_P" : [ 7, 12, 9, "DIFF_RATIO", 18 ]
	};

//MigAuditPrecheck.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditPrecheck.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditPrecheck.Export.export.DefaultColumns = ["AUDIT_ID","AUDIT_SCHEMA","ENV_NAME","TYPE_NAME","MODE_NAME","AUDIT_OBJECT",
                                                 "FRONT_DRNAME","BACK_DRNAME","COUNT_FRONT","COUNT_BACK","DIFF_RATIO_P",
                                                 "AUDIT_DATE","AUDIT_REMARK"];

