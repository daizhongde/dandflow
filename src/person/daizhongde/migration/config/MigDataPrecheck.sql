/** a variable, like javascript

--@JavaScript var MigDataPrecheck = {};
--@JavaScript MigDataPrecheck.Query = {};
--@JavaScript MigDataPrecheck.Add = {};
--@JavaScript MigDataPrecheck.Update = {};
--@JavaScript MigDataPrecheck.Read = {};
--@JavaScript MigDataPrecheck.Del = {};

--@JavaScript MigDataPrecheck.Combobox = {};
--@JavaScript MigDataPrecheck.Nest = {};
--@JavaScript MigDataPrecheck.Export = {};

ten MigDataPrecheck's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigDataPrecheck.Query.query.SQL        MigDataPrecheck.Query.query.HQL        MigDataPrecheck.Query.query.JPQL    
	MigDataPrecheck.Read.read.SQL          MigDataPrecheck.Read.read.HQL          MigDataPrecheck.Read.read.JPQL      
	MigDataPrecheck.Combobox.combobox.SQL  MigDataPrecheck.Combobox.combobox.HQL  MigDataPrecheck.Combobox.combobox.JPQL
	MigDataPrecheck.Nest.nest.SQL          MigDataPrecheck.Nest.nest.HQL          MigDataPrecheck.Nest.nest.JPQL      
	MigDataPrecheck.Export.export.SQL      MigDataPrecheck.Export.export.HQL      MigDataPrecheck.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigDataPrecheck = {};
--@JavaScript MigDataPrecheck.Query = {};
--@JavaScript MigDataPrecheck.Add = {};
--@JavaScript MigDataPrecheck.Update = {};
--@JavaScript MigDataPrecheck.Read = {};
--@JavaScript MigDataPrecheck.Del = {};

--@JavaScript MigDataPrecheck.Combobox = {};
--@JavaScript MigDataPrecheck.Nest = {};
--@JavaScript MigDataPrecheck.Export = {};

--@JavaScript MigDataPrecheck.Query.query.SQL
SELECT 
  t1.CHECK_ID "check_id",
  t1.DRYRUN_ID "dryrun_id",
  t1.CHECK_ENV "check_env",
  t1.CHECK_TYPE "check_type",
  t1.CHECK_OBJECT "check_object",
  t1.CHECK_COUNT "check_count",
  t1.CHECK_FIELD "check_field",
  t1.CHECK_DATE "check_date",
  t1.CHECK_REMARK "check_remark" 
FROM
  tool.mig_data_precheck t1 

/* tableData HQL   */ 
--@JavaScript MigDataPrecheck.Query.query.HQL

--@JavaScript MigDataPrecheck.Query.query.JPQL

-- MigDataPrecheck.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigDataPrecheck.Read.read.SQL
SELECT 
  t1.CHECK_ID "check_id",
  t1.DRYRUN_ID "dryrun_id",
  t1.CHECK_ENV "check_env",
  t1.CHECK_TYPE "check_type",
  t1.CHECK_OBJECT "check_object",
  t1.CHECK_COUNT "check_count",
  t1.CHECK_FIELD "check_field",
  t1.CHECK_DATE "check_date",
  t1.CHECK_REMARK "check_remark" 
FROM
  tool.mig_data_precheck t1 

-- MigDataPrecheck.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigDataPrecheck.Read.read.HQL
-- MigDataPrecheck.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigDataPrecheck t1 left outer join t1.NMparent p

--@JavaScript MigDataPrecheck.Read.read.JPQL

-- SQL for select MigDataPrecheck.Combobox.combobox.data
--@JavaScript MigDataPrecheck.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigDataPrecheck.Combobox.combobox.data
--@JavaScript MigDataPrecheck.Combobox.combobox.HQL

--@JavaScript MigDataPrecheck.Combobox.combobox.JPQL

-- MigDataPrecheck.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigDataPrecheck.Export.export.SQL
SELECT 
  t1.CHECK_ID "check_id",
  t1.DRYRUN_ID "dryrun_id",
  t1.CHECK_ENV "check_env",
  t1.CHECK_TYPE "check_type",
  t1.CHECK_OBJECT "check_object",
  t1.CHECK_COUNT "check_count",
  t1.CHECK_FIELD "check_field",
  t1.CHECK_DATE "check_date",
  t1.CHECK_REMARK "check_remark" 
FROM
  tool.mig_data_precheck t1 
       
--@JavaScript MigDataPrecheck.Export.export.HQL

--@JavaScript MigDataPrecheck.Export.export.JPQL

--@JavaScript MigDataPrecheck.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigDataPrecheck.Nest.nest.HQL

--@JavaScript MigDataPrecheck.Nest.nest.JPQL
