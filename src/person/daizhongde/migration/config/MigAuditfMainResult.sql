/** a variable, like javascript

--@JavaScript var MigAuditfMainResult = {};
--@JavaScript MigAuditfMainResult.Query = {};
--@JavaScript MigAuditfMainResult.Add = {};
--@JavaScript MigAuditfMainResult.Update = {};
--@JavaScript MigAuditfMainResult.Read = {};
--@JavaScript MigAuditfMainResult.Del = {};

--@JavaScript MigAuditfMainResult.Combobox = {};
--@JavaScript MigAuditfMainResult.Nest = {};
--@JavaScript MigAuditfMainResult.Export = {};

ten MigAuditfMainResult's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditfMainResult.Query.query.SQL        MigAuditfMainResult.Query.query.HQL        MigAuditfMainResult.Query.query.JPQL    
	MigAuditfMainResult.Read.read.SQL          MigAuditfMainResult.Read.read.HQL          MigAuditfMainResult.Read.read.JPQL      
	MigAuditfMainResult.Combobox.combobox.SQL  MigAuditfMainResult.Combobox.combobox.HQL  MigAuditfMainResult.Combobox.combobox.JPQL
	MigAuditfMainResult.Nest.nest.SQL          MigAuditfMainResult.Nest.nest.HQL          MigAuditfMainResult.Nest.nest.JPQL      
	MigAuditfMainResult.Export.export.SQL      MigAuditfMainResult.Export.export.HQL      MigAuditfMainResult.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditfMainResult = {};
--@JavaScript MigAuditfMainResult.Query = {};
--@JavaScript MigAuditfMainResult.Add = {};
--@JavaScript MigAuditfMainResult.Update = {};
--@JavaScript MigAuditfMainResult.Read = {};
--@JavaScript MigAuditfMainResult.Del = {};

--@JavaScript MigAuditfMainResult.Combobox = {};
--@JavaScript MigAuditfMainResult.Nest = {};
--@JavaScript MigAuditfMainResult.Export = {};

--@JavaScript MigAuditfMainResult.Query.query.SQL
	select t1.FARES_MAIN_ID      "fares_main_id",
	  (select FAUDIT_NAME from tool.mig_auditf_main where FAUDIT_ID=t1.FARES_MAIN_ID ) "faudit_name",
      --t2.FAUDIT_NAME "faudit_name",
       
       t1.FARES_DRYRUN_ID    "fares_dryrun_id",
      (select MIG_DRYRUN_NAME from tool.v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "mig_dryrun_name",
       --t3.MIG_DRYRUN_NAME "mig_dryrun_name",
       t1.domain       "domain",
       t1.FARES_SERIAL       "fares_serial",
       t1.FARES_SRC_COUNT    "fares_src_count",
       t1.FARES_DST_COUNT    "fares_dst_count",
       t1.FARES_SRC_PASSCNT  "fares_src_passcnt",
       t1.FARES_KEY_PASSCNT  "fares_key_passcnt",
       t1.FARES_SRC_MORE     "fares_src_more",
       t1.FARES_DST_MORE     "fares_dst_more",
       t1.FARES_KEY_UNMATCH  "fares_key_unmatch",
       t1.FARES_ELSE_UNMATCH "fares_else_unmatch",
       date_format(t1.fares_createtime, '%Y-%m-%d %H:%i:%S') "fares_createtime"
       --t1.FARES_CREATETIME   "fares_createtime"
  from tool.mig_auditf_main_result t1
  --MIG_AUDITF_MAIN t2, v_dryrun_config t3
 --where t2.FAUDIT_ID = t1.FARES_MAIN_ID
   --and t3.MIG_DRYRUN_ID = t1.FARES_DRYRUN_ID

/* tableData HQL   */ 
--@JavaScript MigAuditfMainResult.Query.query.HQL

--@JavaScript MigAuditfMainResult.Query.query.JPQL

  
-- MigAuditfMainResult.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditfMainResult.Read.read.SQL
select t1.FARES_MAIN_ID      "fares_main_id",
       t1.FARES_DRYRUN_ID    "fares_dryrun_id",
       t1.FARES_SERIAL       "fares_serial",
       t1.FARES_SRC_COUNT    "fares_src_count",
       t1.FARES_DST_COUNT    "fares_dst_count",
       t1.FARES_SRC_PASSCNT  "fares_src_passcnt",
       t1.FARES_KEY_PASSCNT  "fares_key_passcnt",
       t1.FARES_SRC_MORE     "fares_src_more",
       t1.FARES_DST_MORE     "fares_dst_more",
       t1.FARES_KEY_UNMATCH  "fares_key_unmatch",
       t1.FARES_ELSE_UNMATCH "fares_else_unmatch",
       date_format(t1.fares_createtime, '%Y-%m-%d %H:%i:%S') "fares_createtime"
       --t1.FARES_CREATETIME   "fares_createtime"
  from tool.mig_auditf_main_result t1




-- MigAuditfMainResult.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditfMainResult.Read.read.HQL
-- MigAuditfMainResult.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditfMainResult t1 left outer join t1.NMparent p

--@JavaScript MigAuditfMainResult.Read.read.JPQL

-- SQL for select MigAuditfMainResult.Combobox.combobox.data
--@JavaScript MigAuditfMainResult.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditfMainResult.Combobox.combobox.data
--@JavaScript MigAuditfMainResult.Combobox.combobox.HQL

--@JavaScript MigAuditfMainResult.Combobox.combobox.JPQL

-- MigAuditfMainResult.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditfMainResult.Export.export.SQL
	select t1.FARES_MAIN_ID,
	  (select FAUDIT_NAME from tool.mig_auditf_main where FAUDIT_ID=t1.FARES_MAIN_ID ) "faudit_name",
      --t2.FAUDIT_NAME "faudit_name",
       
       t1.FARES_DRYRUN_ID    "fares_dryrun_id",
      (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "mig_dryrun_name",
       --t3.MIG_DRYRUN_NAME "mig_dryrun_name",
       t1.FARES_SERIAL,
       t1.FARES_SRC_COUNT,
       t1.FARES_DST_COUNT,
       t1.FARES_SRC_PASSCNT,
       t1.FARES_KEY_PASSCNT,
       t1.FARES_SRC_MORE,
       t1.FARES_DST_MORE,
       t1.FARES_KEY_UNMATCH,
       t1.FARES_ELSE_UNMATCH,
       date_format(t1.fares_createtime, '%Y-%m-%d %H:%i:%S') "fares_createtime",
       --t1.FARES_CREATETIME   "fares_createtime"
       case 
       WHEN t1.FARES_SRC_COUNT=0 THEN 0 
       ELSE CAST( t1.FARES_KEY_PASSCNT/t1.FARES_SRC_COUNT AS DECIMAL(12,10) ) END "struct_rate", 
       
       case 
       WHEN t1.FARES_KEY_PASSCNT=0 THEN 0 
       ELSE CAST( t1.FARES_SRC_PASSCNT/t1.FARES_KEY_PASSCNT AS DECIMAL(12,10) ) END "content_rate",
       
       case 
       WHEN t1.FARES_SRC_COUNT=0 THEN 0 
       ELSE CAST( t1.FARES_SRC_PASSCNT/t1.FARES_SRC_COUNT AS DECIMAL(12,10) ) END "result_rate"      
  from tool.mig_auditf_main_result t1

       
--@JavaScript MigAuditfMainResult.Export.export.HQL

--@JavaScript MigAuditfMainResult.Export.export.JPQL

--@JavaScript MigAuditfMainResult.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditfMainResult.Nest.nest.HQL

--@JavaScript MigAuditfMainResult.Nest.nest.JPQL
