/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigAuditcReportDetailResult = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigAuditcReportDetailResult.Field = {
		"id" : [ 4, 10, 0, "", "id", "ID", 0 ],
		"ENTITY" : [ 4, 4, 0, "", "entity", "ENTITY", 1 ],
		"AUDIT_ITEM" : [ 1, 50, 0, "", "audit_item", "AUDIT_ITEM", 2 ],
		"ENUM_DESC" : [ 1, 50, 0, "", "enum_desc", "ENUM_DESC", 3 ],
		"SPLIT_FLAG" : [ 1, 10, 0, "", "split_flag", "SPLIT_FLAG", 4 ],
		SRC_ENUM : [ 1, 12, 0, "", "src_enum", "SRC_ENUM", 5 ],
		"DST_ENUM" : [ 1, 12, 0, "", "dst_enum", "DST_ENUM", 6 ],
		SRC_COUNT : [ 4, 11, 0, "", "src_count", "SRC_COUNT", 7 ],
		"DST_COUNT" : [ 4, 11, 0, "", "dst_count", "DST_COUNT", 8 ],
		"AUDIT_AUTHOR" : [ 1, 20, 0, "", "audit_author", "AUDIT_AUTHOR", 9 ],
		FARES_DRYRUN_ID : [ 1, 20, 0, "", "fares_dryrun_id", "FARES_DRYRUN_ID", 10 ],
		MIN_ANALYSIS : [ 12, 512, 0, "", "min_analysis", "MIN_ANALYSIS", 11 ],
		MIN_PER : [ 1, 12, 0, "", "min_per", "MIN_PER", 12 ]
	};
MigAuditcReportDetailResult.Export={};
MigAuditcReportDetailResult.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigAuditcReportDetailResult.Export.export.ColumnMap ={
		"id" : [ 4, 10, 0, "Audit ID", 0 ],
		"ENTITY" : [ 4, 4, 0, "Domain", 1 ],
		"AUDIT_ITEM" : [ 1, 50, 0, "Sub Domain", 2 ],
		"ENUM_DESC" : [ 1, 50, 0, "Enum Description", 3 ],
		"SPLIT_FLAG" : [ 1, 10, 0, "Split Flag", 4 ],
		SRC_ENUM : [ 1, 12, 0, "Source Enum", 5 ],
		"DST_ENUM" : [ 1, 12, 0, "Target Eunm", 6 ],
		SRC_COUNT : [ 4, 11, 0, "Source Data Count", 7 ],
		"DST_COUNT" : [ 4, 11, 0, "Target Data Count", 8 ],
		"AUDIT_AUTHOR" : [ 1, 20, 0, "Author", 9 ],
		FARES_DRYRUN_ID : [ 1, 20, 0, "Dryrun ID", 10 ],
		MIN_ANALYSIS : [ 12, 512, 0, "Different Analysis", 11 ],
		MIN_PER : [ 1, 12, 0, "Different Percent", 12 ],
		"DOMAIN2" : [1, 20, 0, "Domain", 13 ],
		"dryrun_name" : [ 12, 200, 0, "Dryrun", 14 ],
		"src_count" : [ 4, 10, 0, "Total Source Data Count", 15 ],
		"dst_count" : [ 4, 10, 0, "Total Target Data Count", 16 ],
		"diff_count" : [ 4, 10, 0, "Total Different Count", 17 ],
		"Diff_Rate" : [ 1, 20, 0, "Different Rate", 18 ],
		"Diff_Rate2" : [ 3, 12, 9, "Different Rate", 19 ]
	};

//MigAuditcReportDetailResult.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigAuditcReportDetailResult.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigAuditcReportDetailResult.Export.export.DefaultColumns = [
"dryrun_name","DOMAIN2","AUDIT_ITEM","SRC_COUNT","DST_COUNT","Diff_Rate2"];

