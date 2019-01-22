/** a variable, like javascript

--@JavaScript var MigAuditvConfigtree = {};
--@JavaScript MigAuditvConfigtree.Query = {};
--@JavaScript MigAuditvConfigtree.Add = {};
--@JavaScript MigAuditvConfigtree.Update = {};
--@JavaScript MigAuditvConfigtree.Read = {};
--@JavaScript MigAuditvConfigtree.Del = {};

--@JavaScript MigAuditvConfigtree.Combobox = {};
--@JavaScript MigAuditvConfigtree.Nest = {};
--@JavaScript MigAuditvConfigtree.Export = {};

ten MigAuditvConfigtree's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditvConfigtree.Query.query.SQL        MigAuditvConfigtree.Query.query.HQL        MigAuditvConfigtree.Query.query.JPQL    
	MigAuditvConfigtree.Read.read.SQL          MigAuditvConfigtree.Read.read.HQL          MigAuditvConfigtree.Read.read.JPQL      
	MigAuditvConfigtree.Combobox.combobox.SQL  MigAuditvConfigtree.Combobox.combobox.HQL  MigAuditvConfigtree.Combobox.combobox.JPQL
	MigAuditvConfigtree.Nest.nest.SQL          MigAuditvConfigtree.Nest.nest.HQL          MigAuditvConfigtree.Nest.nest.JPQL      
	MigAuditvConfigtree.Export.export.SQL      MigAuditvConfigtree.Export.export.HQL      MigAuditvConfigtree.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditvConfigtree = {};
--@JavaScript MigAuditvConfigtree.Query = {};
--@JavaScript MigAuditvConfigtree.Add = {};
--@JavaScript MigAuditvConfigtree.Update = {};
--@JavaScript MigAuditvConfigtree.Read = {};
--@JavaScript MigAuditvConfigtree.Del = {};

--@JavaScript MigAuditvConfigtree.Combobox = {};
--@JavaScript MigAuditvConfigtree.Nest = {};
--@JavaScript MigAuditvConfigtree.Export = {};

--@JavaScript MigAuditvConfigtree.Query.query.SQL
SELECT 
  t1.id "id",
  t1.parent "parent",
  t1.name "name",
  t1.content "content",
  t1.isleaf "isleaf",
  t1.status "status",
  t1.remark "remark",
  t1.ctime "ctime" 
FROM
  tool.mig_auditv_configtree t1 

/* tableData HQL   */ 
--@JavaScript MigAuditvConfigtree.Query.query.HQL

--@JavaScript MigAuditvConfigtree.Query.query.JPQL

-- MigAuditvConfigtree.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditvConfigtree.Read.read.SQL
SELECT 
  t1.id "id",
  t1.parent "parent",
  t1.name "name",
  t1.content "content",
  t1.isleaf "isleaf",
  t1.status "status",
  t1.remark "remark",
  t1.ctime "ctime" 
FROM
  tool.mig_auditv_configtree t1 

-- MigAuditvConfigtree.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditvConfigtree.Read.read.HQL
-- MigAuditvConfigtree.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditvConfigtree t1 left outer join t1.NMparent p

--@JavaScript MigAuditvConfigtree.Read.read.JPQL

-- SQL for select MigAuditvConfigtree.Combobox.combobox.data
--@JavaScript MigAuditvConfigtree.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditvConfigtree.Combobox.combobox.data
--@JavaScript MigAuditvConfigtree.Combobox.combobox.HQL

--@JavaScript MigAuditvConfigtree.Combobox.combobox.JPQL

-- MigAuditvConfigtree.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditvConfigtree.Export.export.SQL
SELECT 
  t1.id "id",
  t1.parent "parent",
  t1.name "name",
  t1.content "content",
  t1.isleaf "isleaf",
  t1.status "status",
  t1.remark "remark",
  t1.ctime "ctime" 
FROM
  tool.mig_auditv_configtree t1 
       
--@JavaScript MigAuditvConfigtree.Export.export.HQL

--@JavaScript MigAuditvConfigtree.Export.export.JPQL

--@JavaScript MigAuditvConfigtree.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditvConfigtree.Nest.nest.HQL

--@JavaScript MigAuditvConfigtree.Nest.nest.JPQL
