/** a variable, like javascript

--@JavaScript var MigLoadMap = {};
--@JavaScript MigLoadMap.Query = {};
--@JavaScript MigLoadMap.Add = {};
--@JavaScript MigLoadMap.Update = {};
--@JavaScript MigLoadMap.Read = {};
--@JavaScript MigLoadMap.Del = {};

--@JavaScript MigLoadMap.Combobox = {};
--@JavaScript MigLoadMap.Nest = {};
--@JavaScript MigLoadMap.Export = {};

ten MigLoadMap's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigLoadMap.Query.query.SQL        MigLoadMap.Query.query.HQL        MigLoadMap.Query.query.JPQL    
	MigLoadMap.Read.read.SQL          MigLoadMap.Read.read.HQL          MigLoadMap.Read.read.JPQL      
	MigLoadMap.Combobox.combobox.SQL  MigLoadMap.Combobox.combobox.HQL  MigLoadMap.Combobox.combobox.JPQL
	MigLoadMap.Nest.nest.SQL          MigLoadMap.Nest.nest.HQL          MigLoadMap.Nest.nest.JPQL      
	MigLoadMap.Export.export.SQL      MigLoadMap.Export.export.HQL      MigLoadMap.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigLoadMap = {};
--@JavaScript MigLoadMap.Query = {};
--@JavaScript MigLoadMap.Add = {};
--@JavaScript MigLoadMap.Update = {};
--@JavaScript MigLoadMap.Read = {};
--@JavaScript MigLoadMap.Del = {};

--@JavaScript MigLoadMap.Combobox = {};
--@JavaScript MigLoadMap.Nest = {};
--@JavaScript MigLoadMap.Export = {};

--@JavaScript MigLoadMap.Query.query.SQL
select t1.tname         "tname",
       t1.htype         "htype",
       t1.src_column    "src_column",
       t1.tag_column    "tag_column",
       t1.default_value "default_value",
       t1.s_sql         "s_sql"
  from tool.mig_load_map t1


/* tableData HQL   */ 
--@JavaScript MigLoadMap.Query.query.HQL

--@JavaScript MigLoadMap.Query.query.JPQL

-- MigLoadMap.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigLoadMap.Read.read.SQL
select t1.tname         "tname",
       t1.htype         "htype",
       t1.src_column    "src_column",
       t1.tag_column    "tag_column",
       t1.default_value "default_value",
       t1.s_sql         "s_sql"
  from tool.mig_load_map t1

-- MigLoadMap.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigLoadMap.Read.read.HQL
-- MigLoadMap.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigLoadMap t1 left outer join t1.NMparent p

--@JavaScript MigLoadMap.Read.read.JPQL

-- SQL for select MigLoadMap.Combobox.combobox.data
--@JavaScript MigLoadMap.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigLoadMap.Combobox.combobox.data
--@JavaScript MigLoadMap.Combobox.combobox.HQL

--@JavaScript MigLoadMap.Combobox.combobox.JPQL

-- MigLoadMap.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigLoadMap.Export.export.SQL
select t1.tname         "tname",
       t1.htype         "htype",
       t1.src_column    "src_column",
       t1.tag_column    "tag_column",
       t1.default_value "default_value",
       t1.s_sql         "s_sql"
  from tool.mig_load_map t1

       
--@JavaScript MigLoadMap.Export.export.HQL

--@JavaScript MigLoadMap.Export.export.JPQL

--@JavaScript MigLoadMap.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigLoadMap.Nest.nest.HQL

--@JavaScript MigLoadMap.Nest.nest.JPQL
