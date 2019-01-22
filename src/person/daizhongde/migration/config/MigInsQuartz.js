/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigInsQuartz = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigInsQuartz.Field = {
		"id" : [ 4, 11, 0, "job", "id", "ID", 0 ],
		"case_id" : [ 1, 10, 0, "job", "case_id", "CASE_ID", 1 ],
		"jobname" : [ 12, 100, 0, "", "jobname", "JOBNAME", 2 ],
		"jobgroup" : [ 12, 100, 0, "", "jobgroup", "JOBGROUP", 3 ],
		"cronexpression" : [ 12, 100, 0, "", "cronexpression", "CRONEXPRESSION", 4 ],
		"beanclass" : [ 12, 255, 0, "", "beanclass", "BEANCLASS", 5 ],
		"methodname" : [ 12, 100, 0, "", "methodname", "METHODNAME", 6 ],
		"remark" : [ 12, 200, 0, "", "remark", "REMARK", 7 ],
		"author" : [ 12, 20, 0, "", "author", "AUTHOR", 8 ],
		"createtime" : [ 93, 19, 0, "", "createtime", "CREATETIME", 9 ],
		"cip" : [ 12, 60, 0, "", "cip", "CIP", 10 ],
		"modifier" : [ 12, 20, 0, "", "modifier", "MODIFIER", 11 ],
		"modifytime" : [ 93, 19, 0, "", "modifytime", "MODIFYTIME", 12 ],
		"mip" : [ 12, 60, 0, "", "mip", "MIP", 13 ]
	};


MigInsQuartz.Export={};
MigInsQuartz.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigInsQuartz.Export.export.ColumnMap = {
		"id" : [ 4, 11, 0, "job", 0 ],
		"case_id" : [ 1, 10, 0, "job", 1 ],
		"jobName" : [ 12, 100, 0, "", 2 ],
		"jobGroup" : [ 12, 100, 0, "", 3 ],
		"cronExpression" : [ 12, 100, 0, "", 4 ],
		"beanClass" : [ 12, 255, 0, "", 5 ],
		"methodName" : [ 12, 100, 0, "", 6 ],
		"remark" : [ 12, 200, 0, "", 7 ],
		"author" : [ 12, 20, 0, "", 8 ],
		"createTime" : [ 93, 19, 0, "", 9 ],
		"cip" : [ 12, 60, 0, "", 10 ],
		"modifier" : [ 12, 20, 0, "", 11 ],
		"modifyTime" : [ 93, 19, 0, "", 12 ],
		"mip" : [ 12, 60, 0, "", 13 ]
	};

//MigInsQuartz.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigInsQuartz.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigInsQuartz.Export.export.DefaultColumns = ["AUDIT_ID","AUDIT_NAME","MIG_SQL","AUTHOR","MIG_SQL_REP"];

