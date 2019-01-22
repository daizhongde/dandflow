/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditfDetailResult = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditfDetailResult.Field = {
		FARES_MAIN_ID : [ 12, 20, 0, "", "fares_main_id", "FARES_MAIN_ID", 0 ],
		"FARES_SERIAL" : [ 12, 14, 0, "", "fares_serial", "FARES_SERIAL", 1 ],
		FARES_DRYRUN_ID : [ 12, 20, 0, "", "fares_dryrun_id", "FARES_DRYRUN_ID", 2 ],
		"FARES_UNPASS_TYPE" : [ 4, 11, 0, "", "fares_unpass_type",
				"FARES_UNPASS_TYPE", 3 ],
		"FARES_BITMAP" : [ 12, 256, 0, "", "fares_bitmap", "FARES_BITMAP", 4 ],
		"FARES_UNPASS_SRC" : [ 12, 4000, 0, "", "fares_unpass_src",
				"FARES_UNPASS_SRC", 5 ],
		"FARES_UNPASS_DST" : [ 12, 4000, 0, "", "fares_unpass_dst",
				"FARES_UNPASS_DST", 6 ],
		"FARES_CREATETIME" : [ 93, 19, 0, "", "fares_createtime",
				"FARES_CREATETIME", 7 ]
	};
MigAuditfDetailResult.Export={};
MigAuditfDetailResult.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditfDetailResult.Export.export.ColumnMap =  {
		FARES_MAIN_ID : [ 12, 20, 0, "ID", 0 ],
		"FARES_SERIAL" : [ 12, 14, 0, "Serial", 1 ],
		FARES_DRYRUN_ID : [ 12, 20, 0, "Dryrun ID", 2 ],
		"FARES_UNPASS_TYPE" : [ 4, 11, 0, "Diff TYPE", 3 ],
		"FARES_BITMAP" : [ 12, 256, 0, "", 4 ],
		"FARES_UNPASS_SRC" : [ 12, 4000, 0, "Source More Count", 5 ],
		"FARES_UNPASS_DST" : [ 12, 4000, 0, "Target More Count", 6 ],
		"FARES_CREATETIME" : [ 93, 19, 0, "Create Time", 7 ],
		"mig_dryrun_name" : [ 12, 200, 0, "Dryrun Name", 8 ],
		"faudit_name" : [ 12, 200, 0, "Audit Name", 9 ],
		"diff_type_name" : [ 12, 200, 0, "Diff Type", 10 ]
	};

//MigAuditfDetailResult.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditfDetailResult.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditfDetailResult.Export.export.DefaultColumns = [
"faudit_name","mig_dryrun_name","diff_type_name",
"FARES_UNPASS_SRC","FARES_UNPASS_DST"];

