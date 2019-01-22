/** a variable, like javascript

--@JavaScript var MigSyncConfig = {};
--@JavaScript MigSyncConfig.Query = {};
--@JavaScript MigSyncConfig.Add = {};
--@JavaScript MigSyncConfig.Update = {};
--@JavaScript MigSyncConfig.Read = {};
--@JavaScript MigSyncConfig.Del = {};

--@JavaScript MigSyncConfig.Combobox = {};
--@JavaScript MigSyncConfig.Nest = {};
--@JavaScript MigSyncConfig.Export = {};

ten MigSyncConfig's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigSyncConfig.Query.query.SQL        MigSyncConfig.Query.query.HQL        MigSyncConfig.Query.query.JPQL    
	MigSyncConfig.Read.read.SQL          MigSyncConfig.Read.read.HQL          MigSyncConfig.Read.read.JPQL      
	MigSyncConfig.Combobox.combobox.SQL  MigSyncConfig.Combobox.combobox.HQL  MigSyncConfig.Combobox.combobox.JPQL
	MigSyncConfig.Nest.nest.SQL          MigSyncConfig.Nest.nest.HQL          MigSyncConfig.Nest.nest.JPQL      
	MigSyncConfig.Export.export.SQL      MigSyncConfig.Export.export.HQL      MigSyncConfig.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigSyncConfig = {};
--@JavaScript MigSyncConfig.Query = {};
--@JavaScript MigSyncConfig.Add = {};
--@JavaScript MigSyncConfig.Update = {};
--@JavaScript MigSyncConfig.Read = {};
--@JavaScript MigSyncConfig.Del = {};

--@JavaScript MigSyncConfig.Combobox = {};
--@JavaScript MigSyncConfig.Nest = {};
--@JavaScript MigSyncConfig.Export = {};

--@JavaScript MigSyncConfig.Query.query.SQL
SELECT 
  t1.CONFIG_ID "config_id",
  t1.MIG_GROUP "mig_group",
  t1.MIG_TABLE "mig_table",
  t1.MIG_AUTHOR "mig_author",
  t1.SRC_TYPE "src_type",
  t1.SRC_IP "src_ip",
  t1.SRC_PORT "src_port",
  t1.SRC_SCHEMA "src_schema",
  t1.SRC_USER "src_user",
  t1.SRC_PASSWORD "src_password",
  t1.DST_TYPE "dst_type",
  t1.DST_IP "dst_ip",
  t1.DST_PORT "dst_port",
  t1.DST_SCHEMA "dst_schema",
  t1.DST_USER "dst_user",
  t1.DST_PASSWORD "dst_password",
  t1.MIG_WHERE "mig_where",
  t1.MIG_MODE "mig_mode",
  t1.MIG_DESC "mig_desc",
  date_format(t1.MTIME, '%Y-%m-%d %H:%i:%S') "mtime"
  -- t1.MTIME "mtime" 
FROM
  tool.mig_sync_config t1 

/* tableData HQL   */ 
--@JavaScript MigSyncConfig.Query.query.HQL

--@JavaScript MigSyncConfig.Query.query.JPQL

-- MigSyncConfig.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigSyncConfig.Read.read.SQL
SELECT 
  t1.CONFIG_ID "config_id",
  t1.MIG_GROUP "mig_group",
  t1.MIG_TABLE "mig_table",
  t1.MIG_AUTHOR "mig_author",
  t1.SRC_TYPE "src_type",
  t1.SRC_IP "src_ip",
  t1.SRC_PORT "src_port",
  t1.SRC_SCHEMA "src_schema",
  t1.SRC_USER "src_user",
  t1.SRC_PASSWORD "src_password",
  t1.DST_TYPE "dst_type",
  t1.DST_IP "dst_ip",
  t1.DST_PORT "dst_port",
  t1.DST_SCHEMA "dst_schema",
  t1.DST_USER "dst_user",
  t1.DST_PASSWORD "dst_password",
  t1.MIG_WHERE "mig_where",
  t1.MIG_MODE "mig_mode",
  t1.MIG_DESC "mig_desc",
  t1.MTIME "mtime" 
FROM
  tool.mig_sync_config t1 

-- MigSyncConfig.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigSyncConfig.Read.read.HQL
-- MigSyncConfig.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigSyncConfig t1 left outer join t1.NMparent p

--@JavaScript MigSyncConfig.Read.read.JPQL

-- SQL for select MigSyncConfig.Combobox.combobox.data
--@JavaScript MigSyncConfig.Combobox.combobox.SQL
SELECT DISTINCT mig_group id FROM tool.`mig_sync_config`


-- HQL select MigSyncConfig.Combobox.combobox.data
--@JavaScript MigSyncConfig.Combobox.combobox.HQL

--@JavaScript MigSyncConfig.Combobox.combobox.JPQL

-- MigSyncConfig.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigSyncConfig.Export.export.SQL
SELECT 
  t1.CONFIG_ID "config_id",
  t1.MIG_GROUP "mig_group",
  t1.MIG_TABLE "mig_table",
  t1.MIG_AUTHOR "mig_author",
  t1.SRC_TYPE "src_type",
  t1.SRC_IP "src_ip",
  t1.SRC_PORT "src_port",
  t1.SRC_SCHEMA "src_schema",
  t1.SRC_USER "src_user",
  t1.SRC_PASSWORD "src_password",
  t1.DST_TYPE "dst_type",
  t1.DST_IP "dst_ip",
  t1.DST_PORT "dst_port",
  t1.DST_SCHEMA "dst_schema",
  t1.DST_USER "dst_user",
  t1.DST_PASSWORD "dst_password",
  t1.MIG_WHERE "mig_where",
  t1.MIG_MODE "mig_mode",
  t1.MIG_DESC "mig_desc",
  t1.MTIME "mtime" 
FROM
  tool.mig_sync_config t1 
       
--@JavaScript MigSyncConfig.Export.export.HQL

--@JavaScript MigSyncConfig.Export.export.JPQL

--@JavaScript MigSyncConfig.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigSyncConfig.Nest.nest.HQL

--@JavaScript MigSyncConfig.Nest.nest.JPQL
