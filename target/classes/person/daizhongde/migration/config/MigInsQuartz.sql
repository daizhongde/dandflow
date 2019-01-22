/** a variable, like javascript

--@JavaScript var MigInsQuartz = {};
--@JavaScript MigInsQuartz.Query = {};
--@JavaScript MigInsQuartz.Add = {};
--@JavaScript MigInsQuartz.Update = {};
--@JavaScript MigInsQuartz.Read = {};
--@JavaScript MigInsQuartz.Del = {};

--@JavaScript MigInsQuartz.Combobox = {};
--@JavaScript MigInsQuartz.Nest = {};
--@JavaScript MigInsQuartz.Export = {};

ten MigInsQuartz's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigInsQuartz.Query.query.SQL        MigInsQuartz.Query.query.HQL        MigInsQuartz.Query.query.JPQL    
	MigInsQuartz.Read.read.SQL          MigInsQuartz.Read.read.HQL          MigInsQuartz.Read.read.JPQL      
	MigInsQuartz.Combobox.combobox.SQL  MigInsQuartz.Combobox.combobox.HQL  MigInsQuartz.Combobox.combobox.JPQL
	MigInsQuartz.Nest.nest.SQL          MigInsQuartz.Nest.nest.HQL          MigInsQuartz.Nest.nest.JPQL      
	MigInsQuartz.Export.export.SQL      MigInsQuartz.Export.export.HQL      MigInsQuartz.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigInsQuartz = {};
--@JavaScript MigInsQuartz.Query = {};
--@JavaScript MigInsQuartz.Add = {};
--@JavaScript MigInsQuartz.Update = {};
--@JavaScript MigInsQuartz.Read = {};
--@JavaScript MigInsQuartz.Del = {};

--@JavaScript MigInsQuartz.Combobox = {};
--@JavaScript MigInsQuartz.Nest = {};
--@JavaScript MigInsQuartz.Export = {};

--@JavaScript MigInsQuartz.Query.query.SQL  
SELECT t1.id "id",t1.case_id "case_id",t2.`JOB_INS_NAME` "insname",t1.jobName "jobname",
	t1.jobGroup "jobgroup",t1.cronExpression "cronexpression",
	t1.beanClass "beanclass",t1.methodName "methodname",
	t1.author "author",
    t1.remark "remark",
	DATE_FORMAT(t1.createtime, '%Y-%m-%d %H:%i:%S') "createtime",
  t1.cip "cip",
  t1.modifier "modifier",
  DATE_FORMAT(t1.modifytime, '%Y-%m-%d %H:%i:%S') "modifytime",
  t1.mip "mip" 
 FROM tool.mig_ins_quartz t1
 LEFT OUTER JOIN tool.mig_job_ins t2
   ON t1.`case_id`= t2.`JOB_INS_ID`

/* tableData HQL   */ 
--@JavaScript MigInsQuartz.Query.query.HQL

--@JavaScript MigInsQuartz.Query.query.JPQL

-- MigInsQuartz.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigInsQuartz.Read.read.SQL
select t1.id "id",t1.case_id "case_id",t1.jobName "jobname",
	t1.jobGroup "jobgroup",t1.cronExpression "cronexpression",
	t1.beanClass "beanclass",t1.methodName "methodname",
	t1.author "author",
    t1.remark "remark",
	date_format(t1.createtime, '%Y-%m-%d %H:%i:%S') "createtime",
  t1.cip "cip",
  t1.modifier "modifier",
  DATE_FORMAT(t1.modifytime, '%Y-%m-%d %H:%i:%S') "modifytime",
  t1.mip "mip" 
 from tool.mig_ins_quartz t1

-- MigInsQuartz.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigInsQuartz.Read.read.HQL
-- MigInsQuartz.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigInsQuartz t1 left outer join t1.NMparent p

--@JavaScript MigInsQuartz.Read.read.JPQL

-- SQL for select MigInsQuartz.Combobox.combobox.data
--@JavaScript MigInsQuartz.Combobox.combobox.SQL
select t1.id "id",t1.case_id "case_id",t1.jobName "jobname",
	t1.jobGroup "jobgroup",t1.cronExpression "cronexpression",
	t1.beanClass "beanclass",t1.methodName "methodname",
	t1.createTime "createtime" 
 from tool.mig_ins_quartz t1


-- HQL select MigInsQuartz.Combobox.combobox.data
--@JavaScript MigInsQuartz.Combobox.combobox.HQL

--@JavaScript MigInsQuartz.Combobox.combobox.JPQL

-- MigInsQuartz.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigInsQuartz.Export.export.SQL
select t1.id "id",t1.case_id "case_id",t1.jobName "jobname",
	t1.jobGroup "jobgroup",t1.cronExpression "cronexpression",
	t1.beanClass "beanclass",t1.methodName "methodname",
	t1.createTime "createtime" ,
  t1.cip "cip",
  t1.modifier "modifier",
  DATE_FORMAT(t1.modifytime, '%Y-%m-%d %H:%i:%S') "modifytime",
  t1.mip "mip" 
 from tool.mig_ins_quartz t1

       
--@JavaScript MigInsQuartz.Export.export.HQL

--@JavaScript MigInsQuartz.Export.export.JPQL

--@JavaScript MigInsQuartz.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigInsQuartz.Nest.nest.HQL

--@JavaScript MigInsQuartz.Nest.nest.JPQL
