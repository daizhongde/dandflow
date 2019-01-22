/** note: 
 * 		Don't support back comment 
 * <p>
 * MigSyncConfig is the globel variable
 * */

var MigSyncConfig = {};
/*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 */
MigSyncConfig.Field = {
		"CONFIG_ID" : [ 4, 8, 0, "ID", "config_id", "ID", 0 ],
		"MIG_GROUP" : [ 4, 4, 0, "分组", "mig_group", "MIG_GROUP", 1 ],
		"MIG_TABLE" : [ 12, 30, 0, "表名", "mig_table", "MIG_TABLE", 2 ],
		"MIG_AUTHOR" : [ 12, 20, 0, "作者", "mig_author", "MIG_AUTHOR", 3 ],
		SRC_TYPE : [ 4, 4, 0, "源数据库类型", "src_type", "SRC_TYPE", 4 ],
		SRC_IP : [ 12, 20, 0, "源数据库IP", "src_ip", "SRC_IP", 5 ],
		SRC_PORT : [ 4, 5, 0, "源数据库端口", "src_port", "SRC_PORT", 6 ],
		SRC_SCHEMA : [ 12, 30, 0, "源数据库Schema", "src_schema", "SRC_SCHEMA", 7 ],
		SRC_USER : [ 12, 20, 0, "源数据库用户名", "src_user", "SRC_USER", 8 ],
		SRC_PASSWORD : [ 12, 40, 0, "源数据库密码", "src_password", "SRC_PASSWORD", 9 ],
		"DST_TYPE" : [ 4, 4, 0, "目标数据库类型", "dst_type", "DST_TYPE", 10 ],
		"DST_IP" : [ 12, 20, 0, "目标数据库IP", "dst_ip", "DST_IP", 11 ],
		"DST_PORT" : [ 4, 5, 0, "目标数据端口", "dst_port", "DST_PORT", 12 ],
		"DST_SCHEMA" : [ 12, 30, 0, "目标数据库Schema", "dst_schema", "DST_SCHEMA", 13 ],
		"DST_USER" : [ 12, 20, 0, "目标数据库用户名", "dst_user", "DST_USER", 14 ],
		"DST_PASSWORD" : [ 12, 40, 0, "目标数据库密码", "dst_password", "DST_PASSWORD", 15 ],
		"MIG_WHERE" : [ 12, 1024, 0, "过滤条件", "mig_where", "MIG_WHERE", 16 ],
		"MIG_MODE" : [ 4, 4, 0, "1-add:", "mig_mode", "MIG_MODE", 17 ],
		"MIG_DESC" : [ 12, 128, 0, "remark", "mig_desc", "MIG_DESC", 18 ],
		"MTIME" : [ 93, 19, 0, "modify", "mtime", "MTIME", 19 ]
	};

MigSyncConfig.Export={};
MigSyncConfig.Export.export={};
/*
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * key whom double quotation marks illustrate it's converted. eg: decode, case
 * when and other process table's column.
 * 
 */
MigSyncConfig.Export.export.ColumnMap = {
		"CONFIG_ID" : [ 4, 8, 0, "ID", 0 ],
		"MIG_GROUP" : [ 4, 4, 0, "分组", 1 ],
		"MIG_TABLE" : [ 12, 30, 0, "表名", 2 ],
		"MIG_AUTHOR" : [ 12, 20, 0, "作者", 3 ],
		SRC_TYPE : [ 4, 4, 0, "源数据库类型", 4 ],
		SRC_IP : [ 12, 20, 0, "源数据库IP", 5 ],
		SRC_PORT : [ 4, 5, 0, "源数据库端口", 6 ],
		SRC_SCHEMA : [ 12, 30, 0, "源数据库Schema", 7 ],
		SRC_USER : [ 12, 20, 0, "源数据库用户名", 8 ],
		SRC_PASSWORD : [ 12, 40, 0, "源数据库密码", 9 ],
		"DST_TYPE" : [ 4, 4, 0, "目标数据库类型", 10 ],
		"DST_IP" : [ 12, 20, 0, "目标数据库IP", 11 ],
		"DST_PORT" : [ 4, 5, 0, "目标数据端口", 12 ],
		"DST_SCHEMA" : [ 12, 30, 0, "目标数据库Schema", 13 ],
		"DST_USER" : [ 12, 20, 0, "目标数据库用户名", 14 ],
		"DST_PASSWORD" : [ 12, 40, 0, "目标数据库密码", 15 ],
		"MIG_WHERE" : [ 12, 1024, 0, "过滤条件", 16 ],
		"MIG_MODE" : [ 4, 4, 0, "1-add:", 17 ],
		"MIG_DESC" : [ 12, 128, 0, "remark", 18 ],
		"MTIME" : [ 93, 19, 0, "modify", 19 ]
	};

//MigSyncConfig.Export.export.DefaultColumns = "AREA_CD|AREA_FG|MERCH_SEQ_ID|AREA_NM";
//MigSyncConfig.Export.export.DefaultColumns = "0|1|2|3|4" +
//"|5";
MigSyncConfig.Export.export.DefaultColumns = ["CONFIG_ID","MIG_GROUP","MIG_TABLE","MIG_AUTHOR","SRC_TYPE","SRC_IP","SRC_PORT","SRC_SCHEMA","SRC_USER","SRC_PASSWORD","DST_TYPE","DST_IP","DST_PORT","DST_SCHEMA","DST_USER","DST_PASSWORD","MIG_WHERE","MIG_MODE","MIG_DESC","MTIME"];

MigSyncConfig.Import={};
MigSyncConfig.Import.import={};

//array's order is import file field order
MigSyncConfig.Import.import.DefaultColumns=["CONFIG_ID","MIG_GROUP","MIG_TABLE","MIG_AUTHOR","SRC_TYPE","SRC_IP","SRC_PORT","SRC_SCHEMA","SRC_USER","SRC_PASSWORD","DST_TYPE","DST_IP","DST_PORT","DST_SCHEMA","DST_USER","DST_PASSWORD","MIG_WHERE","MIG_MODE","MIG_DESC","MTIME"];

