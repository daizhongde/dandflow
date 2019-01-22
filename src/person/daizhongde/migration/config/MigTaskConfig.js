/** note: 
 * 		Don't support back comment */

/* the globel variable */
var MigTaskConfig = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigTaskConfig.Field = {
		"MIG_config_id" : [ 4, 8, 0, "", "mig_config_id", "MIG_CONFIG_ID", 0 ],
		"MIG_config_type" : [ 4, 4, 0, "", "mig_config_type", "MIG_CONFIG_TYPE", 1 ],
		"domain" : [ 12, 30, 0, "", "domain", "DOMAIN", 2 ],
		"MIG_SRC" : [ 12, 1024, 0, "", "mig_src", "MIG_SRC", 3 ],
		MIG_SRC_CONN : [ 12, 128, 0, "", "mig_src_conn", "MIG_SRC_CONN", 4 ],
		"MIG_WHERE" : [ 12, 1024, 0, "", "mig_where", "MIG_WHERE", 5 ],
		"MIG_DST" : [ 12, 1024, 0, "", "mig_dst", "MIG_DST", 6 ],
		"MIG_DST_CONN" : [ 12, 128, 0, "", "mig_dst_conn", "MIG_DST_CONN", 7 ],
		"MIG_AUTHOR" : [ 12, 20, 0, "", "mig_author", "MIG_AUTHOR", 8 ],
		"MIG_DESC" : [ 12, 1024, 0, "", "mig_desc", "MIG_DESC", 9 ],
		"MIG_MODIFYTIME" : [ 93, 19, 0, "", "mig_modifytime", "MIG_MODIFYTIME", 10 ],
		"mig_status" : [ 4, 11, 0, "", "mig_status", "MIG_STATUS", 11 ]
	};

MigTaskConfig.Export={};
MigTaskConfig.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigTaskConfig.Export.export.ColumnMap =  {
		"MIG_config_id" : [ 4, 8, 0, "", 0 ],
		"MIG_config_type" : [ 4, 4, 0, "", 1 ],
		"MIG_SRC" : [ 12, 1024, 0, "", 2 ],
		MIG_SRC_CONN : [ 12, 128, 0, "", 3 ],
		"MIG_WHERE" : [ 12, 1024, 0, "", 4 ],
		"MIG_DST" : [ 12, 1024, 0, "", 5 ],
		"MIG_DST_CONN" : [ 12, 128, 0, "", 6 ],
		"MIG_AUTHOR" : [ 12, 20, 0, "", 7 ],
		"MIG_DESC" : [ 12, 1024, 0, "", 8 ],
		"MIG_MODIFYTIME" : [ 93, 19, 0, "", 9 ],
		"mig_status" : [ 4, 11, 0, "", 10 ]
	};

//MigTaskConfig.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigTaskConfig.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigTaskConfig.Export.export.DefaultColumns = [ "MIG_config_id", "MIG_config_type", "MIG_SRC", "MIG_SRC_CONN",
                                              		"MIG_WHERE", "MIG_DST", "MIG_DST_CONN", "MIG_AUTHOR", "MIG_DESC",
                                            		"MIG_MODIFYTIME", "mig_status" ];

