/** a variable, like javascript

--@JavaScript var MigAuditfDetailResult = {};
--@JavaScript MigAuditfDetailResult.Query = {};
--@JavaScript MigAuditfDetailResult.Add = {};
--@JavaScript MigAuditfDetailResult.Update = {};
--@JavaScript MigAuditfDetailResult.Read = {};
--@JavaScript MigAuditfDetailResult.Del = {};

--@JavaScript MigAuditfDetailResult.Combobox = {};
--@JavaScript MigAuditfDetailResult.Nest = {};
--@JavaScript MigAuditfDetailResult.Export = {};

ten MigAuditfDetailResult's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditfDetailResult.Query.query.SQL        MigAuditfDetailResult.Query.query.HQL        MigAuditfDetailResult.Query.query.JPQL    
	MigAuditfDetailResult.Read.read.SQL          MigAuditfDetailResult.Read.read.HQL          MigAuditfDetailResult.Read.read.JPQL      
	MigAuditfDetailResult.Combobox.combobox.SQL  MigAuditfDetailResult.Combobox.combobox.HQL  MigAuditfDetailResult.Combobox.combobox.JPQL
	MigAuditfDetailResult.Nest.nest.SQL          MigAuditfDetailResult.Nest.nest.HQL          MigAuditfDetailResult.Nest.nest.JPQL      
	MigAuditfDetailResult.Export.export.SQL      MigAuditfDetailResult.Export.export.HQL      MigAuditfDetailResult.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditfDetailResult = {};
--@JavaScript MigAuditfDetailResult.Query = {};
--@JavaScript MigAuditfDetailResult.Add = {};
--@JavaScript MigAuditfDetailResult.Update = {};
--@JavaScript MigAuditfDetailResult.Read = {};
--@JavaScript MigAuditfDetailResult.Del = {};

--@JavaScript MigAuditfDetailResult.Combobox = {};
--@JavaScript MigAuditfDetailResult.Nest = {};
--@JavaScript MigAuditfDetailResult.Export = {};

--@JavaScript MigAuditfDetailResult.Query.query.SQL
select t1.FARES_MAIN_ID    "fares_main_id",
	  (select FAUDIT_NAME from tool.mig_auditf_main where FAUDIT_ID=t1.FARES_MAIN_ID ) "faudit_name",
       t1.FARES_SERIAL     "fares_serial",
       t1.FARES_DRYRUN_ID  "fares_dryrun_id",
      (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "mig_dryrun_name",
       t1.FARES_UNPASS_TYPE "fares_unpass_type",
       t1.FARES_BITMAP      "fares_bitmap",
       left(t1.FARES_UNPASS_SRC,200)  "fares_unpass_src",
       left(t1.FARES_UNPASS_DST,200)  "fares_unpass_dst",
       date_format(t1.FARES_CREATETIME, '%Y-%m-%d %H:%i:%S') "fares_createtime"
  from tool.mig_auditf_detail_result t1

/* tableData HQL   */ 
--@JavaScript MigAuditfDetailResult.Query.query.HQL

--@JavaScript MigAuditfDetailResult.Query.query.JPQL

-- MigAuditfDetailResult.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditfDetailResult.Read.read.SQL
select t1.FARES_MAIN_ID    "fares_main_id",
	  (select FAUDIT_NAME from tool.mig_auditf_main where FAUDIT_ID=t1.FARES_MAIN_ID ) "faudit_name",
       t1.FARES_SERIAL     "fares_serial",
       t1.FARES_DRYRUN_ID  "fares_dryrun_id",
      (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "mig_dryrun_name",
       t1.FARES_UNPASS_TYPE "fares_unpass_type",
       t1.FARES_BITMAP      "fares_bitmap",
       t1.FARES_UNPASS_SRC  "fares_unpass_src",
       t1.FARES_UNPASS_DST  "fares_unpass_dst",
       date_format(t1.FARES_CREATETIME, '%Y-%m-%d %H:%i:%S') "fares_createtime"
  from tool.mig_auditf_detail_result t1


-- MigAuditfDetailResult.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditfDetailResult.Read.read.HQL
-- MigAuditfDetailResult.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditfDetailResult t1 left outer join t1.NMparent p

--@JavaScript MigAuditfDetailResult.Read.read.JPQL

-- SQL for select MigAuditfDetailResult.Combobox.combobox.data
--@JavaScript MigAuditfDetailResult.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditfDetailResult.Combobox.combobox.data
--@JavaScript MigAuditfDetailResult.Combobox.combobox.HQL

--@JavaScript MigAuditfDetailResult.Combobox.combobox.JPQL

-- MigAuditfDetailResult.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditfDetailResult.Export.export.SQL
select t1.FARES_MAIN_ID,
	   (select FAUDIT_NAME from tool.mig_auditf_main where FAUDIT_ID=t1.FARES_MAIN_ID ) "faudit_name",
       t1.FARES_SERIAL,
       t1.FARES_DRYRUN_ID,
       (select MIG_DRYRUN_NAME from tool.v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "mig_dryrun_name",
       t1.FARES_UNPASS_TYPE,
       (select value from tool.mig_codedetail_define where type='unPassType' and code=t1.FARES_UNPASS_TYPE ) "diff_type_name",
       t1.FARES_BITMAP,
       t1.FARES_UNPASS_SRC,
       t1.FARES_UNPASS_DST,
       date_format(t1.FARES_CREATETIME, '%Y-%m-%d %H:%i:%S') "fares_createtime"
  from tool.mig_auditf_detail_result t1
       
--@JavaScript MigAuditfDetailResult.Export.export.HQL

--@JavaScript MigAuditfDetailResult.Export.export.JPQL

--@JavaScript MigAuditfDetailResult.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditfDetailResult.Nest.nest.HQL

--@JavaScript MigAuditfDetailResult.Nest.nest.JPQL
