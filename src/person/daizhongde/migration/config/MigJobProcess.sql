/** a variable, like javascript

--@JavaScript var MigJobProcess = {};
--@JavaScript MigJobProcess.Query = {};
--@JavaScript MigJobProcess.Add = {};
--@JavaScript MigJobProcess.Update = {};
--@JavaScript MigJobProcess.Read = {};
--@JavaScript MigJobProcess.Del = {};

--@JavaScript MigJobProcess.Combobox = {};
--@JavaScript MigJobProcess.Nest = {};
--@JavaScript MigJobProcess.Export = {};

ten MigJobProcess's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigJobProcess.Query.query.SQL        MigJobProcess.Query.query.HQL        MigJobProcess.Query.query.JPQL    
	MigJobProcess.Read.read.SQL          MigJobProcess.Read.read.HQL          MigJobProcess.Read.read.JPQL      
	MigJobProcess.Combobox.combobox.SQL  MigJobProcess.Combobox.combobox.HQL  MigJobProcess.Combobox.combobox.JPQL
	MigJobProcess.Nest.nest.SQL          MigJobProcess.Nest.nest.HQL          MigJobProcess.Nest.nest.JPQL      
	MigJobProcess.Export.export.SQL      MigJobProcess.Export.export.HQL      MigJobProcess.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigJobProcess = {};
--@JavaScript MigJobProcess.Query = {};
--@JavaScript MigJobProcess.Add = {};
--@JavaScript MigJobProcess.Update = {};
--@JavaScript MigJobProcess.Read = {};
--@JavaScript MigJobProcess.Del = {};

--@JavaScript MigJobProcess.Combobox = {};
--@JavaScript MigJobProcess.Nest = {};
--@JavaScript MigJobProcess.Export = {};

--@JavaScript MigJobProcess.Query.query.SQL
select t1.PROCESS_ID "process_id",
	t1.JOB_INS_ID "job_ins_id",
	t1.JOB_ID "job_id",
	t1.node_id "node_id",
	t1.isleaf "isleaf",
	t1.node_NAME "node_name",
	t1.node_remark "node_remark",
	t1.CONTROL_ID "control_id",
	t1.com_id "com_id",
	t1.STATUS "status",
	t1.PREPOS "prepos",
	t1.POSTPOS "postpos",
	t1.CREATEDATE "createdate",
	t1.REMARK "remark",
	t1.coords "coords" 
from tool.mig_job_process t1

/* tableData HQL   */ 
--@JavaScript MigJobProcess.Query.query.HQL

--@JavaScript MigJobProcess.Query.query.JPQL

--@JavaScript MigJobProcess.Query.querycbb.SQL
select t1.PROCESS_ID "process_id",
	t1.JOB_INS_ID "job_ins_id",
	t1.JOB_ID "job_id",
	t1.node_id "node_id",
	t1.isleaf "isleaf",
	t1.node_NAME "node_name",
	t1.node_remark "node_remark",
	t1.CONTROL_ID "control_id",
	t1.com_id "com_id",
	t1.STATUS "status",
	t1.PREPOS "prepos",
	t1.POSTPOS "postpos",
	--t1.CREATEDATE "createdate",
date_format(t1.CREATEDATE, '%Y-%m-%d %H:%i:%S') "createdate",
	t1.REMARK "remark",
	t1.coords "coords" 
from tool.mig_job_process t1
where t1.isleaf=1
	
-- MigJobProcess.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigJobProcess.Read.read.SQL
SELECT
	t1.PROCESS_ID "process_id",
	t1.JOB_ID "job_id",
	t1.NODE "node",
	t1.SUBNODE "subnode",
	t1.POSTPOS "postpos",
	t1. STATUS "status",
	t1.CREATEDATE "createdate",
	t1.REMAKR "remakr",
	t1.PREPOS "prepos"
FROM
	tool.mig_job_process t1


-- MigJobProcess.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigJobProcess.Read.read.HQL
-- MigJobProcess.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigJobProcess t1 left outer join t1.NMparent p

--@JavaScript MigJobProcess.Read.read.JPQL

-- SQL for select MigJobProcess.Combobox.combobox.data
--@JavaScript MigJobProcess.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigJobProcess.Combobox.combobox.data
--@JavaScript MigJobProcess.Combobox.combobox.HQL

--@JavaScript MigJobProcess.Combobox.combobox.JPQL

-- MigJobProcess.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigJobProcess.Export.export.SQL
SELECT
	t1.PROCESS_ID "process_id",
	t1.JOB_ID "job_id",
	t1.NODE "node",
	t1.SUBNODE "subnode",
	t1.POSTPOS "postpos",
	t1. STATUS "status",
	t1.CREATEDATE "createdate",
	t1.REMAKR "remakr",
	t1.PREPOS "prepos"
FROM
	tool.mig_job_process t1
       
--@JavaScript MigJobProcess.Export.export.HQL

--@JavaScript MigJobProcess.Export.export.JPQL

--@JavaScript MigJobProcess.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigJobProcess.Nest.nest.HQL

--@JavaScript MigJobProcess.Nest.nest.JPQL
