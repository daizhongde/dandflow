/** a variable, like javascript

--@JavaScript var MigAuditfFieldResult = {};
--@JavaScript MigAuditfFieldResult.Query = {};
--@JavaScript MigAuditfFieldResult.Add = {};
--@JavaScript MigAuditfFieldResult.Update = {};
--@JavaScript MigAuditfFieldResult.Read = {};
--@JavaScript MigAuditfFieldResult.Del = {};

--@JavaScript MigAuditfFieldResult.Combobox = {};
--@JavaScript MigAuditfFieldResult.Nest = {};
--@JavaScript MigAuditfFieldResult.Export = {};

ten MigAuditfFieldResult's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditfFieldResult.Query.query.SQL        MigAuditfFieldResult.Query.query.HQL        MigAuditfFieldResult.Query.query.JPQL    
	MigAuditfFieldResult.Read.read.SQL          MigAuditfFieldResult.Read.read.HQL          MigAuditfFieldResult.Read.read.JPQL      
	MigAuditfFieldResult.Combobox.combobox.SQL  MigAuditfFieldResult.Combobox.combobox.HQL  MigAuditfFieldResult.Combobox.combobox.JPQL
	MigAuditfFieldResult.Nest.nest.SQL          MigAuditfFieldResult.Nest.nest.HQL          MigAuditfFieldResult.Nest.nest.JPQL      
	MigAuditfFieldResult.Export.export.SQL      MigAuditfFieldResult.Export.export.HQL      MigAuditfFieldResult.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditfFieldResult = {};
--@JavaScript MigAuditfFieldResult.Query = {};
--@JavaScript MigAuditfFieldResult.Add = {};
--@JavaScript MigAuditfFieldResult.Update = {};
--@JavaScript MigAuditfFieldResult.Read = {};
--@JavaScript MigAuditfFieldResult.Del = {};

--@JavaScript MigAuditfFieldResult.Combobox = {};
--@JavaScript MigAuditfFieldResult.Nest = {};
--@JavaScript MigAuditfFieldResult.Export = {};

--@JavaScript MigAuditfFieldResult.Query.query.SQL
SELECT 
  t1.MAIN_ID "main_id",
  (select FAUDIT_NAME from tool.mig_auditf_main where FAUDIT_ID=t1.main_id ) "audit_item",
  t1.DRYRUN_ID "dryrun_id",
   (select MIG_DRYRUN_NAME from tool.v_dryrun_config where MIG_DRYRUN_ID=t1.DRYRUN_ID ) "dryrun_name",
  t1.FARES_SERIAL "fares_serial",
  t1.DOMAIN "domain",
  t1.FIELD_INDEX "field_index",
  t1.SRC_TABLE_NAME "src_table_name",
  t1.DST_TABLE_NAME "dst_table_name",
  t1.SRC_FIELD_NAME "src_field_name",
  t1.DST_FIELD_NAME "dst_field_name",
  t1.UNMATCH_CNT "unmatch_cnt",
  t1.UNMATCH_RATE "unmatch_rate",
  t1.CURRENT_CONTENT_RATE "current_content_rate",
  t1.CURRENT_RESULT_RATE "current_result_rate",
  t1.EFFECT_CONTENT_RATE "effect_content_rate",
  t1.EFFECT_RESULT_RATE "effect_result_rate",
  --t1.CREATE_TIME "create_time"
  date_format(t1.CREATE_TIME, '%Y-%m-%d %H:%i:%S') "create_time"
FROM
  tool.mig_auditf_field_result t1 

/* tableData HQL   */ 
--@JavaScript MigAuditfFieldResult.Query.query.HQL

--@JavaScript MigAuditfFieldResult.Query.query.JPQL

-- MigAuditfFieldResult.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditfFieldResult.Read.read.SQL
SELECT 
  t1.MAIN_ID "main_id",
  t1.DRYRUN_ID "dryrun_id",
  t1.FARES_SERIAL "fares_serial",
  t1.DOMAIN "domain",
  t1.FIELD_INDEX "field_index",
  t1.SRC_TABLE_NAME "src_table_name",
  t1.DST_TABLE_NAME "dst_table_name",
  t1.SRC_FIELD_NAME "src_field_name",
  t1.DST_FIELD_NAME "dst_field_name",
  t1.UNMATCH_CNT "unmatch_cnt",
  t1.UNMATCH_RATE "unmatch_rate",
  t1.CURRENT_CONTENT_RATE "current_content_rate",
  t1.CURRENT_RESULT_RATE "current_result_rate",
  t1.EFFECT_CONTENT_RATE "effect_content_rate",
  t1.EFFECT_RESULT_RATE "effect_result_rate",
  t1.CREATE_TIME "create_time" 
FROM
  tool.mig_auditf_field_result t1 

-- MigAuditfFieldResult.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditfFieldResult.Read.read.HQL
-- MigAuditfFieldResult.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditfFieldResult t1 left outer join t1.NMparent p

--@JavaScript MigAuditfFieldResult.Read.read.JPQL

-- SQL for select MigAuditfFieldResult.Combobox.combobox.data
--@JavaScript MigAuditfFieldResult.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditfFieldResult.Combobox.combobox.data
--@JavaScript MigAuditfFieldResult.Combobox.combobox.HQL

--@JavaScript MigAuditfFieldResult.Combobox.combobox.JPQL

-- MigAuditfFieldResult.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditfFieldResult.Export.export.SQL
SELECT 
  t1.MAIN_ID,
  (select FAUDIT_NAME from tool.mig_auditf_main where FAUDIT_ID=t1.main_id ) AUDIT_ITEM,
  t1.DRYRUN_ID,
  t1.FARES_SERIAL,
  t1.DOMAIN,
  (SELECT VALUE FROM tool.mig_codedetail_define WHERE TYPE='Busi-Domain' AND CODE=t1.DOMAIN) 'DOMAIN_NAME',
  t1.FIELD_INDEX,
  t1.SRC_TABLE_NAME,
  t1.DST_TABLE_NAME,
  t1.SRC_FIELD_NAME,
  t1.DST_FIELD_NAME,
  t1.UNMATCH_CNT,
  t1.UNMATCH_RATE,
  t1.CURRENT_CONTENT_RATE,
  t1.CURRENT_RESULT_RATE,
  t1.EFFECT_CONTENT_RATE,
  t1.EFFECT_RESULT_RATE,
  t1.CREATE_TIME 
FROM
  tool.mig_auditf_field_result t1 
       
--@JavaScript MigAuditfFieldResult.Export.export.HQL

--@JavaScript MigAuditfFieldResult.Export.export.JPQL

--@JavaScript MigAuditfFieldResult.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditfFieldResult.Nest.nest.HQL

--@JavaScript MigAuditfFieldResult.Nest.nest.JPQL
