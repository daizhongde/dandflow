/** a variable, like javascript

--@JavaScript var MigTaskConfig = {};
--@JavaScript MigTaskConfig.Query = {};
--@JavaScript MigTaskConfig.Add = {};
--@JavaScript MigTaskConfig.Update = {};
--@JavaScript MigTaskConfig.Read = {};
--@JavaScript MigTaskConfig.Del = {};

--@JavaScript MigTaskConfig.Combobox = {};
--@JavaScript MigTaskConfig.Nest = {};
--@JavaScript MigTaskConfig.Export = {};

ten MigTaskConfig's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigTaskConfig.Query.query.SQL        MigTaskConfig.Query.query.HQL        MigTaskConfig.Query.query.JPQL    
	MigTaskConfig.Read.read.SQL          MigTaskConfig.Read.read.HQL          MigTaskConfig.Read.read.JPQL      
	MigTaskConfig.Combobox.combobox.SQL  MigTaskConfig.Combobox.combobox.HQL  MigTaskConfig.Combobox.combobox.JPQL
	MigTaskConfig.Nest.nest.SQL          MigTaskConfig.Nest.nest.HQL          MigTaskConfig.Nest.nest.JPQL      
	MigTaskConfig.Export.export.SQL      MigTaskConfig.Export.export.HQL      MigTaskConfig.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigTaskConfig = {};
--@JavaScript MigTaskConfig.Query = {};
--@JavaScript MigTaskConfig.Add = {};
--@JavaScript MigTaskConfig.Update = {};
--@JavaScript MigTaskConfig.Read = {};
--@JavaScript MigTaskConfig.Del = {};

--@JavaScript MigTaskConfig.Combobox = {};
--@JavaScript MigTaskConfig.Nest = {};
--@JavaScript MigTaskConfig.Export = {};

--@JavaScript MigTaskConfig.Query.query.SQL
select t1.MIG_config_id   "mig_config_id",
       t1.MIG_config_type "mig_config_type",
       t1.domain          "domain",
       t1.MIG_SRC         "mig_src",
       t1.MIG_SRC_CONN    "mig_src_conn",
       t1.MIG_WHERE       "mig_where",
       t1.MIG_DST         "mig_dst",
       t1.MIG_DST_CONN    "mig_dst_conn",
       t1.MIG_AUTHOR      "mig_author",
       t1.MIG_DESC        "mig_desc",
       t1.MIG_MODIFYTIME  "mig_modifytime",
       t1.mig_status      "mig_status"
  from tool.mig_task_config t1


/* tableData HQL   */ 
--@JavaScript MigTaskConfig.Query.query.HQL

--@JavaScript MigTaskConfig.Query.query.JPQL

-- MigTaskConfig.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigTaskConfig.Read.read.SQL
select t1.MIG_config_id   "mig_config_id",
       t1.MIG_config_type "mig_config_type",
       t1.domain "domain",
       t1.MIG_SRC         "mig_src",
       t1.MIG_SRC_CONN    "mig_src_conn",
       t1.MIG_WHERE       "mig_where",
       t1.MIG_DST         "mig_dst",
       t1.MIG_DST_CONN    "mig_dst_conn",
       t1.MIG_AUTHOR      "mig_author",
       t1.MIG_DESC        "mig_desc",
       t1.MIG_MODIFYTIME  "mig_modifytime",
       t1.mig_status      "mig_status"
  from tool.mig_task_config t1


-- MigTaskConfig.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigTaskConfig.Read.read.HQL
-- MigTaskConfig.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigTaskConfig t1 left outer join t1.NMparent p

--@JavaScript MigTaskConfig.Read.read.JPQL

-- SQL for select MigTaskConfig.Combobox.combobox.data
--@JavaScript MigTaskConfig.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigTaskConfig.Combobox.combobox.data
--@JavaScript MigTaskConfig.Combobox.combobox.HQL

--@JavaScript MigTaskConfig.Combobox.combobox.JPQL

-- MigTaskConfig.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigTaskConfig.Export.export.SQL
select t1.MIG_config_id   "mig_config_id",
       t1.MIG_config_type "mig_config_type",
       t1.domain "domain",
       t1.MIG_SRC         "mig_src",
       t1.MIG_SRC_CONN    "mig_src_conn",
       t1.MIG_WHERE       "mig_where",
       t1.MIG_DST         "mig_dst",
       t1.MIG_DST_CONN    "mig_dst_conn",
       t1.MIG_AUTHOR      "mig_author",
       t1.MIG_DESC        "mig_desc",
       t1.MIG_MODIFYTIME  "mig_modifytime",
       t1.mig_status      "mig_status"
  from tool.mig_task_config t1

       
--@JavaScript MigTaskConfig.Export.export.HQL

--@JavaScript MigTaskConfig.Export.export.JPQL

--@JavaScript MigTaskConfig.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigTaskConfig.Nest.nest.HQL

--@JavaScript MigTaskConfig.Nest.nest.JPQL
