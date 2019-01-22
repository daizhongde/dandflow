/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigDataPrecheck = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigDataPrecheck.Field = {
		"CHECK_ID" : [ 12, 21, 0, "", "check_id", "CHECK_ID", 0 ],
		DRYRUN_ID : [ 4, 4, 0, "", "dryrun_id", "DRYRUN_ID", 1 ],
		"CHECK_ENV" : [ 12, 2, 0, "", "check_env", "CHECK_ENV", 2 ],
		"CHECK_TYPE" : [ 12, 2, 0, "", "check_type", "CHECK_TYPE", 3 ],
		"CHECK_OBJECT" : [ 12, 254, 0, "", "check_object", "CHECK_OBJECT", 4 ],
		"CHECK_COUNT" : [ 4, 11, 0, "", "check_count", "CHECK_COUNT", 5 ],
		"CHECK_FIELD" : [ 4, 4, 0, "", "check_field", "CHECK_FIELD", 6 ],
		"CHECK_DATE" : [ 93, 19, 0, "", "check_date", "CHECK_DATE", 7 ],
		"CHECK_REMARK" : [ 12, 254, 0, "", "check_remark", "CHECK_REMARK", 8 ]
	};

MigDataPrecheck.Export={};
MigDataPrecheck.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigDataPrecheck.Export.export.ColumnMap = {
		"CHECK_ID" : [ 12, 21, 0, "", 0 ],
		DRYRUN_ID : [ 4, 4, 0, "", 1 ],
		"CHECK_ENV" : [ 12, 2, 0, "", 2 ],
		"CHECK_TYPE" : [ 12, 2, 0, "", 3 ],
		"CHECK_OBJECT" : [ 12, 254, 0, "", 4 ],
		"CHECK_COUNT" : [ 4, 11, 0, "", 5 ],
		"CHECK_FIELD" : [ 4, 4, 0, "", 6 ],
		"CHECK_DATE" : [ 93, 19, 0, "", 7 ],
		"CHECK_REMARK" : [ 12, 254, 0, "", 8 ]
	};

//MigDataPrecheck.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigDataPrecheck.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigDataPrecheck.Export.export.DefaultColumns =["CHECK_ID","DRYRUN_ID","CHECK_ENV","CHECK_TYPE","CHECK_OBJECT","CHECK_COUNT","CHECK_FIELD","CHECK_DATE","CHECK_REMARK"];

