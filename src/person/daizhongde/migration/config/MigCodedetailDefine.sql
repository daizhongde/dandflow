/** a variable, like javascript

--@JavaScript var MigCodedetailDefine = {};
--@JavaScript MigCodedetailDefine.Query = {};
--@JavaScript MigCodedetailDefine.Add = {};
--@JavaScript MigCodedetailDefine.Update = {};
--@JavaScript MigCodedetailDefine.Read = {};
--@JavaScript MigCodedetailDefine.Del = {};

--@JavaScript MigCodedetailDefine.Combobox = {};
--@JavaScript MigCodedetailDefine.Nest = {};
--@JavaScript MigCodedetailDefine.Export = {};

ten MigCodedetailDefine's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigCodedetailDefine.Query.query.SQL        MigCodedetailDefine.Query.query.HQL        MigCodedetailDefine.Query.query.JPQL    
	MigCodedetailDefine.Read.read.SQL          MigCodedetailDefine.Read.read.HQL          MigCodedetailDefine.Read.read.JPQL      
	MigCodedetailDefine.Combobox.combobox.SQL  MigCodedetailDefine.Combobox.combobox.HQL  MigCodedetailDefine.Combobox.combobox.JPQL
	MigCodedetailDefine.Nest.nest.SQL          MigCodedetailDefine.Nest.nest.HQL          MigCodedetailDefine.Nest.nest.JPQL      
	MigCodedetailDefine.Export.export.SQL      MigCodedetailDefine.Export.export.HQL      MigCodedetailDefine.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigCodedetailDefine = {};
--@JavaScript MigCodedetailDefine.Query = {};
--@JavaScript MigCodedetailDefine.Add = {};
--@JavaScript MigCodedetailDefine.Update = {};
--@JavaScript MigCodedetailDefine.Read = {};
--@JavaScript MigCodedetailDefine.Del = {};

--@JavaScript MigCodedetailDefine.Combobox = {};
--@JavaScript MigCodedetailDefine.Nest = {};
--@JavaScript MigCodedetailDefine.Export = {};

--@JavaScript MigCodedetailDefine.Query.query.SQL
SELECT 
  t1.id "id",
  t1.TYPE "type",
  t1.code "code",
  t1.value "value",
  t1.remark "remark" 
FROM
  tool.mig_codedetail_define t1 

/* tableData HQL   */ 
--@JavaScript MigCodedetailDefine.Query.query.HQL

--@JavaScript MigCodedetailDefine.Query.query.JPQL

-- MigCodedetailDefine.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigCodedetailDefine.Read.read.SQL
SELECT 
  t1.id "id",
  t1.TYPE "type",
  t1.code "code",
  t1.value "value",
  t1.remark "remark" 
FROM
  tool.mig_codedetail_define t1 

-- MigCodedetailDefine.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigCodedetailDefine.Read.read.HQL
-- MigCodedetailDefine.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigCodedetailDefine t1 left outer join t1.NMparent p

--@JavaScript MigCodedetailDefine.Read.read.JPQL

-- SQL for select MigCodedetailDefine.Combobox.combobox.data
--@JavaScript MigCodedetailDefine.Combobox.combobox.SQL
SELECT CODE "id", VALUE "text" FROM tool.mig_codedetail_define


-- HQL select MigCodedetailDefine.Combobox.combobox.data
--@JavaScript MigCodedetailDefine.Combobox.combobox.HQL

--@JavaScript MigCodedetailDefine.Combobox.combobox.JPQL

-- MigCodedetailDefine.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigCodedetailDefine.Export.export.SQL
SELECT 
  t1.id "id",
  t1.TYPE "type",
  t1.code "code",
  t1.value "value",
  t1.remark "remark" 
FROM
  tool.mig_codedetail_define t1 
       
--@JavaScript MigCodedetailDefine.Export.export.HQL

--@JavaScript MigCodedetailDefine.Export.export.JPQL

--@JavaScript MigCodedetailDefine.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigCodedetailDefine.Nest.nest.HQL

--@JavaScript MigCodedetailDefine.Nest.nest.JPQL
