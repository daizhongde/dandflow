/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditfSub = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditfSub.Field =  {
		FAUDIT_MAIN_ID : [ 4, 11, 0, "", "faudit_main_id", "FAUDIT_MAIN_ID", 0 ],
		"FAUDIT_SUB_INDEX" : [ 4, 11, 0, "", "faudit_sub_index",
				"FAUDIT_SUB_INDEX", 1 ],
		FAUDIT_SRC_FIELD : [ 12, 64, 0, "", "faudit_src_field", "FAUDIT_SRC_FIELD",
				2 ],
		"FAUDIT_DST_FIELD" : [ 12, 64, 0, "", "faudit_dst_field",
				"FAUDIT_DST_FIELD", 3 ],
		"FAUDIT_ISKEY" : [ 4, 11, 0, "", "faudit_iskey", "FAUDIT_ISKEY", 4 ],
		"FAUDIT_OPT" : [ 12, 128, 0, "", "faudit_opt", "FAUDIT_OPT", 5 ],
		"FAUDIT_CREATETIME" : [ 93, 19, 0, "", "faudit_createtime",
				"FAUDIT_CREATETIME", 6 ],
		"FAUDIT_MODIFYTIME" : [ 93, 19, 0, "", "faudit_modifytime",
				"FAUDIT_MODIFYTIME", 7 ],
		"FAUDIT_STATUS" : [ 4, 11, 0, "", "faudit_status", "FAUDIT_STATUS", 8 ]
	};
MigAuditfSub.Export={};
MigAuditfSub.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditfSub.Export.export.ColumnMap = {
		FAUDIT_MAIN_ID : [ 4, 11, 0, "", 0 ],
		"FAUDIT_SUB_INDEX" : [ 4, 11, 0, "", 1 ],
		FAUDIT_SRC_FIELD : [ 12, 64, 0, "", 2 ],
		"FAUDIT_DST_FIELD" : [ 12, 64, 0, "", 3 ],
		"FAUDIT_ISKEY" : [ 4, 11, 0, "", 4 ],
		"FAUDIT_OPT" : [ 12, 128, 0, "", 5 ],
		"FAUDIT_CREATETIME" : [ 93, 19, 0, "", 6 ],
		"FAUDIT_MODIFYTIME" : [ 93, 19, 0, "", 7 ],
		"FAUDIT_STATUS" : [ 4, 11, 0, "", 8 ]
	};

//MigAuditfSub.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditfSub.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditfSub.Export.export.DefaultColumns = ["FAUDIT_MAIN_ID","FAUDIT_SUB_INDEX","FAUDIT_SRC_FIELD","FAUDIT_DST_FIELD","FAUDIT_ISKEY","FAUDIT_OPT","FAUDIT_CREATETIME","FAUDIT_MODIFYTIME","FAUDIT_STATUS"];

