/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditfMain = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditfMain.Field = {
		"FAUDIT_ID" : [ 4, 11, 0, "", "faudit_id", "FAUDIT_ID", 0 ],
		"domain" : [ 4, 4, 0, "", "domain", "DOMAIN", 1 ],
		"FAUDIT_NAME" : [ 12, 128, 0, "", "faudit_name", "FAUDIT_NAME", 2 ],
		"FAUDIT_SRCTABLE_NAME" : [ 12, 128, 0, "", "faudit_srctable_name",
				"FAUDIT_SRCTABLE_NAME", 3 ],
		"FAUDIT_SRCTABLE_CONN" : [ 12, 1024, 0, "", "faudit_srctable_conn",
				"FAUDIT_SRCTABLE_CONN", 4 ],
		"FAUDIT_DSTTABLE_NAME" : [ 12, 128, 0, "", "faudit_dsttable_name",
				"FAUDIT_DSTTABLE_NAME", 5 ],
		"FAUDIT_DSTTABLE_CONN" : [ 12, 1024, 0, "", "faudit_dsttable_conn",
				"FAUDIT_DSTTABLE_CONN", 6 ],
		"Author" : [ 12, 20, 0, "", "author", "AUTHOR", 7 ],
		"FAUDIT_DESC" : [ 12, 1024, 0, "", "faudit_desc", "FAUDIT_DESC", 8 ],
		"FAUDIT_CREATETIME" : [ 93, 19, 0, "", "faudit_createtime",
				"FAUDIT_CREATETIME", 9 ]
	};

MigAuditfMain.Export={};
MigAuditfMain.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditfMain.Export.export.ColumnMap = {
		"CONTROL_ID" : [ 1, 12, 0, "控件ID", 0 ],
		"PARA_ID" : [ 4, 9, 0, "参数ID", 1 ],
		"PARA_NAME" : [ 12, 20, 0, "参数名称", 2 ],
		"IS_NULL" : [ 4, 11, 0, "是否为空", 3 ],
		"IS_NUMBER" : [ 4, 11, 0, "是否为数字", 4 ],
		"DEF_VALUE" : [ 12, 128, 0, "默认值", 5 ]
	};

//MigAuditfMain.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditfMain.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditfMain.Export.export.DefaultColumns = ["FAUDIT_ID","FAUDIT_NAME","FAUDIT_SRCTABLE_NAME","FAUDIT_SRCTABLE_CONN","FAUDIT_DSTTABLE_NAME","FAUDIT_DSTTABLE_CONN","FAUDIT_TYPE","FAUDIT_GROUP","FAUDIT_GROUP_NAME","FAUDIT_BUSSINESS_CLUSTER","FAUDIT_BUSSINESS_NAME","Author","FAUDIT_DESC","FAUDIT_CREATETIME","FAUDIT_MODIFYTIME","FAUDIT_FINISHTIME","FAUDIT_STATUS"];

