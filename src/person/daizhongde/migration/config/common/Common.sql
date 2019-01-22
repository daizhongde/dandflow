/** a variable, like javascript

--@JavaScript var Common = {};
--@JavaScript Common.Query = {};
--@JavaScript Common.Add = {};
--@JavaScript Common.Update = {};
--@JavaScript Common.Read = {};
--@JavaScript Common.Del = {};

--@JavaScript Common.Combobox = {};
--@JavaScript Common.Nest = {};
--@JavaScript Common.Export = {};

ten Common's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	Common.Query.query.SQL        Common.Query.query.HQL        Common.Query.query.JPQL    
	Common.Read.read.SQL          Common.Read.read.HQL          Common.Read.read.JPQL      
	Common.Combobox.combobox.SQL  Common.Combobox.combobox.HQL  Common.Combobox.combobox.JPQL
	Common.Nest.nest.SQL          Common.Nest.nest.HQL          Common.Nest.nest.JPQL      
	Common.Export.export.SQL      Common.Export.export.HQL      Common.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var Common = {};
--@JavaScript Common.Query = {};
--@JavaScript Common.Add = {};
--@JavaScript Common.Update = {};
--@JavaScript Common.Read = {};
--@JavaScript Common.Del = {};

--@JavaScript Common.Combobox = {};
--@JavaScript Common.Nest = {};
--@JavaScript Common.Export = {};

--@JavaScript Common.Query.query.SQL
select t1.COM_ID     "com_id",
       t1.CONTROL_ID "control_id",
       t1.PARA_ID    "para_id",
       t1.PARA_VALUE "para_value"
  from tool.mig_com_ins t1


/* tableData HQL   */ 
--@JavaScript Common.Query.query.HQL

--@JavaScript Common.Query.query.JPQL

-- Common.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript Common.Read.read.SQL
select t1.COM_ID     "com_id",
       t1.CONTROL_ID "control_id",
       t1.PARA_ID    "para_id",
       t1.PARA_VALUE "para_value"
  from tool.mig_com_ins t1



-- Common.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript Common.Read.read.HQL
-- Common.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TCommon t1 left outer join t1.NMparent p

--@JavaScript Common.Read.read.JPQL

--@JavaScript Common.Read.read_TaskConfig.SQL
select t1.MIG_config_id   "mig_config_id",
       --t1.MIG_config_type "mig_config_type",
      (select value from tool.mig_codedetail_define where type='config_type' and code=t1.mig_config_type) "mig_config_type",
       t1.domain          "domain",
       t1.MIG_SRC         "mig_src",
       t1.MIG_SRC_CONN    "mig_src_conn",
       t1.MIG_WHERE       "mig_where",
       t1.MIG_DST         "mig_dst",
       t1.MIG_DST_CONN    "mig_dst_conn",
       t1.MIG_AUTHOR      "mig_author",
       t1.MIG_DESC        "mig_desc",
       --t1.MIG_MODIFYTIME  "mig_modifytime",
       date_format(t1.MIG_MODIFYTIME, '%Y-%m-%d %H:%i:%S') "mig_modifytime",
       --t1.mig_status      "mig_status"
	  (select value from tool.mig_codedetail_define where type='record_status' and code=t1.mig_status) "mig_status"
  from tool.mig_task_config t1

-- SQL for select Common.Combobox.combobox.data
--@JavaScript Common.Combobox.combobox.SQL
SELECT schema_name "id",schema_name "text" 
  FROM INFORMATION_SCHEMA.SCHEMATA
 WHERE schema_name NOT IN ('information_schema','mysql','test','tool')

-- HQL select Common.Combobox.combobox.data
--@JavaScript Common.Combobox.combobox.HQL

--@JavaScript Common.Combobox.combobox.JPQL

--@JavaScript Common.Combobox.combobox_Schema.SQL
SELECT schema_name "id",schema_name "text" 
  FROM INFORMATION_SCHEMA.SCHEMATA
 WHERE schema_name NOT IN ('information_schema','mysql','test','tool')

--@JavaScript Common.Combobox.combobox_buttonNo.SQL
SELECT ordinal_position-5 "id",column_comment "text"
  FROM INFORMATION_SCHEMA.COLUMNS 
 WHERE TABLE_SCHEMA='tool' AND TABLE_NAME='t_authority_rmrelation' AND column_type='char(1)'


-- Common.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript Common.Export.export.SQL
select t1.COM_ID     "com_id",
       t1.CONTROL_ID "control_id",
       t1.PARA_ID    "para_id",
       t1.PARA_VALUE "para_value"
  from tool.mig_com_ins t1

       
--@JavaScript Common.Export.export.HQL

--@JavaScript Common.Export.export.JPQL

--@JavaScript Common.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript Common.Nest.nest.HQL

--@JavaScript Common.Nest.nest.JPQL
