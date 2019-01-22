/** a variable, like javascript

--@JavaScript var MigAuditvConfig = {};
--@JavaScript MigAuditvConfig.Query = {};
--@JavaScript MigAuditvConfig.Add = {};
--@JavaScript MigAuditvConfig.Update = {};
--@JavaScript MigAuditvConfig.Read = {};
--@JavaScript MigAuditvConfig.Del = {};

--@JavaScript MigAuditvConfig.Combobox = {};
--@JavaScript MigAuditvConfig.Nest = {};
--@JavaScript MigAuditvConfig.Export = {};

ten MigAuditvConfig's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditvConfig.Query.query.SQL        MigAuditvConfig.Query.query.HQL        MigAuditvConfig.Query.query.JPQL    
	MigAuditvConfig.Read.read.SQL          MigAuditvConfig.Read.read.HQL          MigAuditvConfig.Read.read.JPQL      
	MigAuditvConfig.Combobox.combobox.SQL  MigAuditvConfig.Combobox.combobox.HQL  MigAuditvConfig.Combobox.combobox.JPQL
	MigAuditvConfig.Nest.nest.SQL          MigAuditvConfig.Nest.nest.HQL          MigAuditvConfig.Nest.nest.JPQL      
	MigAuditvConfig.Export.export.SQL      MigAuditvConfig.Export.export.HQL      MigAuditvConfig.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditvConfig = {};
--@JavaScript MigAuditvConfig.Query = {};
--@JavaScript MigAuditvConfig.Add = {};
--@JavaScript MigAuditvConfig.Update = {};
--@JavaScript MigAuditvConfig.Read = {};
--@JavaScript MigAuditvConfig.Del = {};

--@JavaScript MigAuditvConfig.Combobox = {};
--@JavaScript MigAuditvConfig.Nest = {};
--@JavaScript MigAuditvConfig.Export = {};

--@JavaScript MigAuditvConfig.Query.query.SQL
select t1.AUDIT_ID         "audit_id",
       t1.DOMAIN           "domain",
       t1.TABLE_NAME       "table_name",
       t1.AUDIT_NAME       "audit_name",
       t1.AUDIT_TYPE       "audit_type",
       t1.AUDIT_LEVEL      "audit_level",
       t1.SQL_TYPE         "sql_type",
       left( t1.SRC_AUDIT_SQL, 200 )    "src_audit_sql",
       left( t1.DST_AUDIT_SQL, 200 )    "dst_audit_sql",
       left( t1.AUDIT_VALUE, 200 )      "audit_value",
       t1.AUDIT_FLAG       "audit_flag",
       left( t1.INVALID_DATA_SQL, 200 ) "invalid_data_sql",
       t1.OPERATOR         "operator",
       t1.AUDIT_UNIT       "audit_unit",
       t1.AUDIT_AUTHOR     "audit_author",
       t1.REMARK           "remark",
       t1.SRC_DB_CONNECT   "src_db_connect",
       t1.DST_DB_CONNECT   "dst_db_connect",
       t1.MIG_SQL_REP      "mig_sql_rep",
       t1.VERSION          "version",
       --t1.ctime            "ctime"
	   date_format(t1.ctime, '%Y-%m-%d %H:%i:%S') "ctime"
  from tool.mig_auditv_config t1



/* tableData HQL   */ 
--@JavaScript MigAuditvConfig.Query.query.HQL

--@JavaScript MigAuditvConfig.Query.query.JPQL

--@JavaScript MigAuditvConfig.Query.querycbb.SQL
select t1.AUDIT_ID         "audit_id",
       t1.DOMAIN           "domain",
       t1.TABLE_NAME       "table_name",
       t1.AUDIT_NAME       "audit_name",
       t1.AUDIT_AUTHOR     "audit_author",
       date_format(t1.ctime, '%Y-%m-%d %H:%i:%S') "ctime"
  from tool.mig_auditv_config t1
  
--@JavaScript MigAuditvConfig.Query.querySingleCollDetail.SQL
select 
	   t1.detail_id        "detail_id",
	   t1.main_id          "main_id",
	   t2.AUDIT_ID         "audit_id",
       t2.DOMAIN           "domain",
       t2.TABLE_NAME       "table_name",
       t2.AUDIT_NAME       "audit_name",
       t2.AUDIT_TYPE       "audit_type",
       t2.AUDIT_LEVEL      "audit_level",
       t2.SQL_TYPE         "sql_type",
       left( t2.SRC_AUDIT_SQL, 200 )    "src_audit_sql",
       left( t2.DST_AUDIT_SQL, 200 )    "dst_audit_sql",
       left( t2.AUDIT_VALUE, 200 )      "audit_value",
       t2.AUDIT_FLAG       "audit_flag",
       left( t2.INVALID_DATA_SQL, 200 ) "invalid_data_sql",
       t2.OPERATOR         "operator",
       t2.AUDIT_UNIT       "audit_unit",
       t2.AUDIT_AUTHOR     "audit_author",
       t2.REMARK           "remark",
       t2.SRC_DB_CONNECT   "src_db_connect",
       t2.DST_DB_CONNECT   "dst_db_connect",
       t2.MIG_SQL_REP      "mig_sql_rep",
       t2.VERSION          "version"
  from tool.mig_auditv_configcolldetail t1
 right outer join tool.mig_auditv_config t2
    on t1.AUDIT_ID = t2.AUDIT_ID
--where t1.main_id = :main_id
 
/* where t1.AUDIT_ID in 
 	(select t2.audit_id 
 	   from mig_auditv_configcolldetail t2 
 	  where t2.main_id = :main_id )
  */
-- MigAuditvConfig.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditvConfig.Read.read.SQL
select t1.AUDIT_ID         "audit_id",
       t1.DOMAIN           "domain",
	   (select VALUE from tool.mig_codedetail_define where TYPE='Busi-Domain' and code=t1.DOMAIN) "domain_name",
       t1.TABLE_NAME       "table_name",
       t1.AUDIT_NAME       "audit_name",
       t1.AUDIT_TYPE       "audit_type",
       t1.AUDIT_LEVEL      "audit_level",
       t1.SQL_TYPE         "sql_type",
       t1.SRC_AUDIT_SQL    "src_audit_sql",
       t1.DST_AUDIT_SQL    "dst_audit_sql",
       t1.AUDIT_VALUE      "audit_value",
       t1.AUDIT_FLAG       "audit_flag",
       t1.INVALID_DATA_SQL "invalid_data_sql",
       t1.OPERATOR         "operator",
       t1.AUDIT_UNIT       "audit_unit",
       t1.AUDIT_AUTHOR     "audit_author",
       t1.REMARK           "remark",
       t1.SRC_DB_CONNECT   "src_db_connect",
       t1.DST_DB_CONNECT   "dst_db_connect",
       t1.MIG_SQL_REP      "mig_sql_rep",
       t1.VERSION          "version"
  from tool.mig_auditv_config t1


-- MigAuditvConfig.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditvConfig.Read.read.HQL
-- MigAuditvConfig.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditvConfig t1 left outer join t1.NMparent p

--@JavaScript MigAuditvConfig.Read.read.JPQL

-- SQL for select MigAuditvConfig.Combobox.combobox.data
--@JavaScript MigAuditvConfig.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditvConfig.Combobox.combobox.data
--@JavaScript MigAuditvConfig.Combobox.combobox.HQL

--@JavaScript MigAuditvConfig.Combobox.combobox.JPQL

-- MigAuditvConfig.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditvConfig.Export.export.SQL
select t1.AUDIT_ID         "audit_id",
       t1.DOMAIN           "domain",
	   (select VALUE from tool.mig_codedetail_define where TYPE='Busi-Domain' and code=t1.DOMAIN) "domain_name",
       t1.TABLE_NAME       "table_name",
       t1.AUDIT_NAME       "audit_name",
       t1.AUDIT_TYPE       "audit_type",
       t1.AUDIT_LEVEL      "audit_level",
       t1.SQL_TYPE         "sql_type",
       t1.SRC_AUDIT_SQL    "src_audit_sql",
       t1.DST_AUDIT_SQL    "dst_audit_sql",
       t1.AUDIT_VALUE      "audit_value",
       t1.AUDIT_FLAG       "audit_flag",
       t1.INVALID_DATA_SQL "invalid_data_sql",
       t1.OPERATOR         "operator",
       t1.AUDIT_UNIT       "audit_unit",
       t1.AUDIT_AUTHOR     "audit_author",
       t1.REMARK           "remark",
       t1.SRC_DB_CONNECT   "src_db_connect",
       t1.DST_DB_CONNECT   "dst_db_connect",
       t1.MIG_SQL_REP      "mig_sql_rep",
       t1.VERSION          "version"
  from tool.mig_auditv_config t1

       
--@JavaScript MigAuditvConfig.Export.export.HQL

--@JavaScript MigAuditvConfig.Export.export.JPQL

--@JavaScript MigAuditvConfig.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditvConfig.Nest.nest.HQL

--@JavaScript MigAuditvConfig.Nest.nest.JPQL
