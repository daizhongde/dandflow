/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditfMainResult = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditfMainResult.Field = {
		FARES_MAIN_ID : [ 12, 20, 0, "", "fares_main_id", "FARES_MAIN_ID", 0 ],
		FARES_DRYRUN_ID : [ 12, 20, 0, "", "fares_dryrun_id", "FARES_DRYRUN_ID", 1 ],
		"FARES_SERIAL" : [ 12, 14, 0, "", "fares_serial", "FARES_SERIAL", 2 ],
		FARES_SRC_COUNT : [ 4, 11, 0, "", "fares_src_count", "FARES_SRC_COUNT", 3 ],
		"FARES_DST_COUNT" : [ 4, 11, 0, "", "fares_dst_count", "FARES_DST_COUNT", 4 ],
		FARES_SRC_PASSCNT : [ 4, 11, 0, "", "fares_src_passcnt",
				"FARES_SRC_PASSCNT", 5 ],
		"FARES_KEY_PASSCNT" : [ 4, 11, 0, "", "fares_key_passcnt",
				"FARES_KEY_PASSCNT", 6 ],
		FARES_SRC_MORE : [ 4, 11, 0, "", "fares_src_more", "FARES_SRC_MORE", 7 ],
		"FARES_DST_MORE" : [ 4, 11, 0, "", "fares_dst_more", "FARES_DST_MORE", 8 ],
		"FARES_KEY_UNMATCH" : [ 4, 11, 0, "", "fares_key_unmatch",
				"FARES_KEY_UNMATCH", 9 ],
		"FARES_ELSE_UNMATCH" : [ 4, 11, 0, "", "fares_else_unmatch",
				"FARES_ELSE_UNMATCH", 10 ],
		"FARES_CREATETIME" : [ 93, 19, 0, "", "fares_createtime",
				"FARES_CREATETIME", 11 ]
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
		FARES_MAIN_ID : [ 4, 11, 0, "Audit ID", 0 ],
		FARES_DRYRUN_ID : [ 12, 20, 0, "Dryrun ID", 1 ],
		"FARES_SERIAL" : [ 12, 14, 0, "Serial", 2 ],
		FARES_SRC_COUNT : [ 4, 11, 0, "Source Count", 3 ],
		"FARES_DST_COUNT" : [ 4, 11, 0, "Target Count", 4 ],
		FARES_SRC_PASSCNT : [ 4, 11, 0, "Source Accordant Count", 5 ],
		"FARES_KEY_PASSCNT" : [ 4, 11, 0, "Key Accordant Count", 6 ],
		FARES_SRC_MORE : [ 4, 11, 0, "Source More", 7 ],
		"FARES_DST_MORE" : [ 4, 11, 0, "Target More", 8 ],
		"FARES_KEY_UNMATCH" : [ 4, 11, 0, "Key Unmatch", 9 ],
		"FARES_ELSE_UNMATCH" : [ 4, 11, 0, "Other unmatch", 10 ],
		"FARES_CREATETIME" : [ 93, 19, 0, "Create Time", 11 ],
		"mig_dryrun_name" : [ 12, 200, 0, "Dryrun Name", 12 ],
		"faudit_name" : [ 12, 200, 0, "Audit Name", 13 ],
		"struct_rate" : [ 3, 30, 9, "Struct Accordant Rate", 14 ],
		"content_rate" : [ 3, 30, 9, "Content Accordant Rate", 15 ],
		"result_rate" : [ 3, 30, 9, "Result Accordant Rate", 16 ]
	};

//MigAuditfMainResult.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditfMainResult.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditfMainResult.Export.export.DefaultColumns = [
"faudit_name","mig_dryrun_name","FARES_SRC_COUNT","FARES_DST_COUNT",
"FARES_KEY_PASSCNT","struct_rate","FARES_SRC_PASSCNT","content_rate","result_rate"];

