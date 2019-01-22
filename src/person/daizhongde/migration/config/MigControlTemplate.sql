/** a variable, like javascript

--@JavaScript var MigControlTemplate = {};
--@JavaScript MigControlTemplate.Query = {};
--@JavaScript MigControlTemplate.Add = {};
--@JavaScript MigControlTemplate.Update = {};
--@JavaScript MigControlTemplate.Read = {};
--@JavaScript MigControlTemplate.Del = {};

--@JavaScript MigControlTemplate.Combobox = {};
--@JavaScript MigControlTemplate.Nest = {};
--@JavaScript MigControlTemplate.Export = {};

ten MigControlTemplate's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigControlTemplate.Query.query.SQL        MigControlTemplate.Query.query.HQL        MigControlTemplate.Query.query.JPQL    
	MigControlTemplate.Read.read.SQL          MigControlTemplate.Read.read.HQL          MigControlTemplate.Read.read.JPQL      
	MigControlTemplate.Combobox.combobox.SQL  MigControlTemplate.Combobox.combobox.HQL  MigControlTemplate.Combobox.combobox.JPQL
	MigControlTemplate.Nest.nest.SQL          MigControlTemplate.Nest.nest.HQL          MigControlTemplate.Nest.nest.JPQL      
	MigControlTemplate.Export.export.SQL      MigControlTemplate.Export.export.HQL      MigControlTemplate.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigControlTemplate = {};
--@JavaScript MigControlTemplate.Query = {};
--@JavaScript MigControlTemplate.Add = {};
--@JavaScript MigControlTemplate.Update = {};
--@JavaScript MigControlTemplate.Read = {};
--@JavaScript MigControlTemplate.Del = {};

--@JavaScript MigControlTemplate.Combobox = {};
--@JavaScript MigControlTemplate.Nest = {};
--@JavaScript MigControlTemplate.Export = {};

--@JavaScript MigControlTemplate.Query.query.SQL
select t1.CONTROL_ID "control_id",
       t1.PARA_ID    "para_id",
       t1.PARA_NAME  "para_name",
       t1.IS_NULL    "is_null",
       t1.IS_NUMBER  "is_number",
       t1.DEF_VALUE  "def_value"
  from tool.mig_control_template t1


/* tableData HQL   */ 
--@JavaScript MigControlTemplate.Query.query.HQL

--@JavaScript MigControlTemplate.Query.query.JPQL

-- MigControlTemplate.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigControlTemplate.Read.read.SQL
select t1.CONTROL_ID "control_id",
       t1.PARA_ID    "para_id",
       t1.PARA_NAME  "para_name",
       t1.IS_NULL    "is_null",
       t1.IS_NUMBER  "is_number",
       t1.DEF_VALUE  "def_value"
  from tool.mig_control_template t1



-- MigControlTemplate.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigControlTemplate.Read.read.HQL
-- MigControlTemplate.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigControlTemplate t1 left outer join t1.NMparent p

--@JavaScript MigControlTemplate.Read.read.JPQL

-- SQL for select MigControlTemplate.Combobox.combobox.data
--@JavaScript MigControlTemplate.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigControlTemplate.Combobox.combobox.data
--@JavaScript MigControlTemplate.Combobox.combobox.HQL

--@JavaScript MigControlTemplate.Combobox.combobox.JPQL

-- MigControlTemplate.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigControlTemplate.Export.export.SQL
select t1.CONTROL_ID "control_id",
       t1.PARA_ID    "para_id",
       t1.PARA_NAME  "para_name",
       t1.IS_NULL    "is_null",
       t1.IS_NUMBER  "is_number",
       t1.DEF_VALUE  "def_value"
  from tool.mig_control_template t1

       
--@JavaScript MigControlTemplate.Export.export.HQL

--@JavaScript MigControlTemplate.Export.export.JPQL

--@JavaScript MigControlTemplate.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigControlTemplate.Nest.nest.HQL

--@JavaScript MigControlTemplate.Nest.nest.JPQL
