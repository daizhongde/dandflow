/** a variable, like javascript

--@JavaScript var MigTaskInfo = {};
--@JavaScript MigTaskInfo.Query = {};
--@JavaScript MigTaskInfo.Add = {};
--@JavaScript MigTaskInfo.Update = {};
--@JavaScript MigTaskInfo.Read = {};
--@JavaScript MigTaskInfo.Del = {};

--@JavaScript MigTaskInfo.Combobox = {};
--@JavaScript MigTaskInfo.Nest = {};
--@JavaScript MigTaskInfo.Export = {};

ten MigTaskInfo's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigTaskInfo.Query.query.SQL        MigTaskInfo.Query.query.HQL        MigTaskInfo.Query.query.JPQL    
	MigTaskInfo.Read.read.SQL          MigTaskInfo.Read.read.HQL          MigTaskInfo.Read.read.JPQL      
	MigTaskInfo.Combobox.combobox.SQL  MigTaskInfo.Combobox.combobox.HQL  MigTaskInfo.Combobox.combobox.JPQL
	MigTaskInfo.Nest.nest.SQL          MigTaskInfo.Nest.nest.HQL          MigTaskInfo.Nest.nest.JPQL      
	MigTaskInfo.Export.export.SQL      MigTaskInfo.Export.export.HQL      MigTaskInfo.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigTaskInfo = {};
--@JavaScript MigTaskInfo.Query = {};
--@JavaScript MigTaskInfo.Add = {};
--@JavaScript MigTaskInfo.Update = {};
--@JavaScript MigTaskInfo.Read = {};
--@JavaScript MigTaskInfo.Del = {};

--@JavaScript MigTaskInfo.Combobox = {};
--@JavaScript MigTaskInfo.Nest = {};
--@JavaScript MigTaskInfo.Export = {};

--@JavaScript MigTaskInfo.Query.query.SQL
select t1.TASK_ID     "task_id",
       t1.TASK_NAME   "task_name",
       t1.COM_ID      "com_id",
       t1.control_id  "control_id",
       t1.TASK_AUTHOR "task_author",
       t1.TASK_REMARK "task_remark",
       t1.TASK_UPDATE "task_update"
  from tool.mig_task_info t1


/* tableData HQL   */ 
--@JavaScript MigTaskInfo.Query.query.HQL

--@JavaScript MigTaskInfo.Query.query.JPQL

-- MigTaskInfo.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigTaskInfo.Read.read.SQL
select t1.TASK_ID     "task_id",
       t1.TASK_NAME   "task_name",
       t1.TASK_NODE   "task_node",
       t1.COM_ID      "com_id",
       t1.control_id  "control_id",
       t1.TASK_AUTHOR "task_author",
       t1.TASK_REMARK "task_remark",
       t1.TASK_UPDATE "task_update"
  from tool.mig_task_info t1



-- MigTaskInfo.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigTaskInfo.Read.read.HQL
-- MigTaskInfo.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigTaskInfo t1 left outer join t1.NMparent p

--@JavaScript MigTaskInfo.Read.read.JPQL

-- SQL for select MigTaskInfo.Combobox.combobox.data
--@JavaScript MigTaskInfo.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigTaskInfo.Combobox.combobox.data
--@JavaScript MigTaskInfo.Combobox.combobox.HQL

--@JavaScript MigTaskInfo.Combobox.combobox.JPQL

-- MigTaskInfo.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigTaskInfo.Export.export.SQL
select t1.TASK_ID     "task_id",
       t1.TASK_NAME   "task_name",
       t1.TASK_NODE   "task_node",
       t1.COM_ID      "com_id",
       t1.control_id  "control_id",
       t1.TASK_AUTHOR "task_author",
       t1.TASK_REMARK "task_remark",
       t1.TASK_UPDATE "task_update"
  from tool.mig_task_info t1
       
--@JavaScript MigTaskInfo.Export.export.HQL

--@JavaScript MigTaskInfo.Export.export.JPQL

--@JavaScript MigTaskInfo.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigTaskInfo.Nest.nest.HQL

--@JavaScript MigTaskInfo.Nest.nest.JPQL
