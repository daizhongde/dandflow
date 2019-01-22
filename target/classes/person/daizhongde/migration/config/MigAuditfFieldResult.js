/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditfFieldResult = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditfFieldResult.Field = {
		MAIN_ID : [ 12, 20, 0, "", "main_id", "MAIN_ID", 0 ],
		DRYRUN_ID : [ 12, 20, 0, "", "dryrun_id", "DRYRUN_ID", 1 ],
		"FARES_SERIAL" : [ 12, 14, 0, "", "fares_serial", "FARES_SERIAL", 2 ],
		"DOMAIN" : [ 4, 4, 0, "", "domain", "DOMAIN", 3 ],
		"FIELD_INDEX" : [ 4, 11, 0, "", "field_index", "FIELD_INDEX", 4 ],
		SRC_TABLE_NAME : [ 12, 128, 0, "", "src_table_name", "SRC_TABLE_NAME", 5 ],
		"DST_TABLE_NAME" : [ 12, 128, 0, "", "dst_table_name", "DST_TABLE_NAME", 6 ],
		SRC_FIELD_NAME : [ 12, 64, 0, "", "src_field_name", "SRC_FIELD_NAME", 7 ],
		"DST_FIELD_NAME" : [ 12, 64, 0, "", "dst_field_name", "DST_FIELD_NAME", 8 ],
		"UNMATCH_CNT" : [ 4, 11, 0, "", "unmatch_cnt", "UNMATCH_CNT", 9 ],
		"UNMATCH_RATE" : [ 7, 12, 31, "", "unmatch_rate", "UNMATCH_RATE", 10 ],
		"CURRENT_CONTENT_RATE" : [ 7, 12, 31, "", "current_content_rate",
				"CURRENT_CONTENT_RATE", 11 ],
		"CURRENT_RESULT_RATE" : [ 7, 12, 31, "", "current_result_rate",
				"CURRENT_RESULT_RATE", 12 ],
		"EFFECT_CONTENT_RATE" : [ 7, 12, 31, "", "effect_content_rate",
				"EFFECT_CONTENT_RATE", 13 ],
		"EFFECT_RESULT_RATE" : [ 7, 12, 31, "", "effect_result_rate",
				"EFFECT_RESULT_RATE", 14 ],
		"CREATE_TIME" : [ 93, 19, 0, "", "create_time", "CREATE_TIME", 15 ]
	};

MigAuditfFieldResult.Export={};
MigAuditfFieldResult.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditfFieldResult.Export.export.ColumnMap = {
		MAIN_ID : [ 12, 20, 0, "Main ID", 0 ],
		AUDIT_ITEM : [ 12, 100, 0, "AUDIT_ITEM", 1 ],
		DRYRUN_ID : [ 12, 20, 0, "Dry Run", 2 ],
		"FARES_SERIAL" : [ 12, 14, 0, "Serial NO.", 3 ],
		"DOMAIN" : [ 4, 4, 0, "Domain", 4 ],
		"DOMAIN_NAME" : [ 12, 100, 0, "Domain", 5 ],
		"FIELD_INDEX" : [ 4, 11, 0, "Field Index", 6 ],
		SRC_TABLE_NAME : [ 12, 128, 0, "Source Table", 7 ],
		"DST_TABLE_NAME" : [ 12, 128, 0, "Target Table", 8 ],
		SRC_FIELD_NAME : [ 12, 64, 0, "Source Field", 9 ],
		"DST_FIELD_NAME" : [ 12, 64, 0, "Target Field", 10 ],
		"UNMATCH_CNT" : [ 4, 11, 0, "Unmatch Count", 11 ],
		"UNMATCH_RATE" : [ 7, 12, 9, "Unmatch Rate", 12 ],
		"CURRENT_CONTENT_RATE" : [ 7, 12, 9, "Content Rate", 13 ],
		"CURRENT_RESULT_RATE" : [ 7, 12, 9, "Result Rate", 14 ],
		"EFFECT_CONTENT_RATE" : [ 7, 12, 9, "EPT Content Rate", 15 ],
		"EFFECT_RESULT_RATE" : [ 7, 12, 9, "EPT Result Rate", 16 ],
		"CREATE_TIME" : [ 93, 19, 0, "Create Time", 17 ]
	};

//MigAuditfFieldResult.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditfFieldResult.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditfFieldResult.Export.export.DefaultColumns =["AUDIT_ITEM","DRYRUN_ID","FARES_SERIAL","DOMAIN_NAME","FIELD_INDEX","SRC_TABLE_NAME","DST_TABLE_NAME","SRC_FIELD_NAME","DST_FIELD_NAME","UNMATCH_CNT","UNMATCH_RATE","CURRENT_CONTENT_RATE","CURRENT_RESULT_RATE","EFFECT_CONTENT_RATE","EFFECT_RESULT_RATE","CREATE_TIME"];

