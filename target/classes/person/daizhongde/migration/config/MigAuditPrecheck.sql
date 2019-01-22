/** a variable, like javascript

--@JavaScript var MigAuditPrecheck = {};
--@JavaScript MigAuditPrecheck.Query = {};
--@JavaScript MigAuditPrecheck.Add = {};
--@JavaScript MigAuditPrecheck.Update = {};
--@JavaScript MigAuditPrecheck.Read = {};
--@JavaScript MigAuditPrecheck.Del = {};

--@JavaScript MigAuditPrecheck.Combobox = {};
--@JavaScript MigAuditPrecheck.Nest = {};
--@JavaScript MigAuditPrecheck.Export = {};

ten MigAuditPrecheck's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditPrecheck.Query.query.SQL        MigAuditPrecheck.Query.query.HQL        MigAuditPrecheck.Query.query.JPQL    
	MigAuditPrecheck.Read.read.SQL          MigAuditPrecheck.Read.read.HQL          MigAuditPrecheck.Read.read.JPQL      
	MigAuditPrecheck.Combobox.combobox.SQL  MigAuditPrecheck.Combobox.combobox.HQL  MigAuditPrecheck.Combobox.combobox.JPQL
	MigAuditPrecheck.Nest.nest.SQL          MigAuditPrecheck.Nest.nest.HQL          MigAuditPrecheck.Nest.nest.JPQL      
	MigAuditPrecheck.Export.export.SQL      MigAuditPrecheck.Export.export.HQL      MigAuditPrecheck.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditPrecheck = {};
--@JavaScript MigAuditPrecheck.Query = {};
--@JavaScript MigAuditPrecheck.Add = {};
--@JavaScript MigAuditPrecheck.Update = {};
--@JavaScript MigAuditPrecheck.Read = {};
--@JavaScript MigAuditPrecheck.Del = {};

--@JavaScript MigAuditPrecheck.Combobox = {};
--@JavaScript MigAuditPrecheck.Nest = {};
--@JavaScript MigAuditPrecheck.Export = {};

--@JavaScript MigAuditPrecheck.Query.query.SQL
SELECT 
  t1.AUDIT_ID "audit_id",
  t1.AUDIT_SCHEMA "audit_schema",
  t1.AUDIT_ENV "audit_env",
  t1.AUDIT_TYPE "audit_type",
  t1.AUDIT_MODE "audit_mode",
  t1.AUDIT_OBJECT "audit_object",
  t1.DRYRUN_FRONT "dryrun_front",
  t1.DRYRUN_BACK "dryrun_back",
  t1.COUNT_FRONT "count_front",
  t1.COUNT_BACK "count_back",
  t1.DIFF_RATIO "diff_ratio",
  --t1.AUDIT_DATE "audit_date",
  date_format(t1.AUDIT_DATE, '%Y-%m-%d %H:%i:%S') "audit_date",
  t1.AUDIT_REMARK "audit_remark" 
FROM
  tool.mig_audit_precheck t1 

/* tableData HQL   */ 
--@JavaScript MigAuditPrecheck.Query.query.HQL

--@JavaScript MigAuditPrecheck.Query.query.JPQL

-- MigAuditPrecheck.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditPrecheck.Read.read.SQL
SELECT 
  t1.AUDIT_ID "audit_id",
  t1.AUDIT_SCHEMA "audit_schema",
  t1.AUDIT_ENV "audit_env",
  t1.AUDIT_TYPE "audit_type",
  t1.AUDIT_MODE "audit_mode",
  t1.AUDIT_OBJECT "audit_object",
  t1.DRYRUN_FRONT "dryrun_front",
  t1.DRYRUN_BACK "dryrun_back",
  t1.COUNT_FRONT "count_front",
  t1.COUNT_BACK "count_back",
  t1.DIFF_RATIO "diff_ratio",
  --t1.AUDIT_DATE "audit_date",
  date_format(t1.AUDIT_DATE, '%Y-%m-%d %H:%i:%S') "audit_date",
  t1.AUDIT_REMARK "audit_remark" 
FROM
  tool.mig_audit_precheck t1 

-- MigAuditPrecheck.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditPrecheck.Read.read.HQL
-- MigAuditPrecheck.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditPrecheck t1 left outer join t1.NMparent p

--@JavaScript MigAuditPrecheck.Read.read.JPQL

-- SQL for select MigAuditPrecheck.Combobox.combobox.data
--@JavaScript MigAuditPrecheck.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditPrecheck.Combobox.combobox.data
--@JavaScript MigAuditPrecheck.Combobox.combobox.HQL

--@JavaScript MigAuditPrecheck.Combobox.combobox.JPQL

-- MigAuditPrecheck.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditPrecheck.Export.export.SQL
SELECT 
  t1.AUDIT_ID,
  t1.AUDIT_ENV,
  t1.AUDIT_TYPE,
  t1.AUDIT_MODE,
  CASE WHEN t1.AUDIT_ENV='C' THEN 'Connextion' WHEN t1.AUDIT_ENV='R' THEN 'Retail' ELSE t1.AUDIT_ENV END ENV_NAME,
  CASE WHEN t1.AUDIT_TYPE='F' THEN 'File' WHEN t1.AUDIT_TYPE='M' THEN 'Middle Table' 
       WHEN t1.AUDIT_TYPE='D' THEN 'Target Table' WHEN t1.AUDIT_TYPE='FM' THEN 'File Mid-Table' 
       ELSE t1.AUDIT_TYPE END TYPE_NAME,
  CASE WHEN t1.AUDIT_MODE='L' THEN 'less than before' WHEN t1.AUDIT_MODE='M' THEN 'more than before' 
       WHEN t1.AUDIT_MODE='D' THEN 'record number diff' WHEN t1.AUDIT_MODE='F' THEN 'field number diff' 
       ELSE t1.AUDIT_MODE END MODE_NAME,
  t1.AUDIT_OBJECT,
  t1.DRYRUN_FRONT,
  t1.DRYRUN_BACK,
  (SELECT MIG_DRYRUN_NAME FROM v_dryrun_config WHERE MIG_DRYRUN_ID=t1.DRYRUN_FRONT ) "FRONT_DRNAME",
  (SELECT MIG_DRYRUN_NAME FROM v_dryrun_config WHERE MIG_DRYRUN_ID=t1.DRYRUN_BACK ) "BACK_DRNAME",
  t1.COUNT_FRONT,
  t1.COUNT_BACK,
  t1.DIFF_RATIO,
  --CAST(CONCAT(t1.DIFF_RATIO,'%') as char(10)) DIFF_RATIO_P,
  t1.DIFF_RATIO/100  DIFF_RATIO_P,
  t1.AUDIT_DATE,
  --DATE_FORMAT(t1.AUDIT_DATE, '%Y-%m-%d %H:%i:%S') AUDIT_DATE,
  t1.AUDIT_REMARK 
FROM
  tool.mig_audit_precheck t1 
       
--@JavaScript MigAuditPrecheck.Export.export.HQL

--@JavaScript MigAuditPrecheck.Export.export.JPQL

--@JavaScript MigAuditPrecheck.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditPrecheck.Nest.nest.HQL

--@JavaScript MigAuditPrecheck.Nest.nest.JPQL
