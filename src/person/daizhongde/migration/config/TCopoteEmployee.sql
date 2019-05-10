/** a variable, like javascript

--@JavaScript var TCopoteEmployee = {};
--@JavaScript TCopoteEmployee.Query = {};
--@JavaScript TCopoteEmployee.Add = {};
--@JavaScript TCopoteEmployee.Update = {};
--@JavaScript TCopoteEmployee.Read = {};
--@JavaScript TCopoteEmployee.Del = {};

--@JavaScript TCopoteEmployee.Combobox = {};
--@JavaScript TCopoteEmployee.Nest = {};
--@JavaScript TCopoteEmployee.Export = {};

ten TCopoteEmployee's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	TCopoteEmployee.Query.query.SQL        TCopoteEmployee.Query.query.HQL        TCopoteEmployee.Query.query.JPQL    
	TCopoteEmployee.Read.read.SQL          TCopoteEmployee.Read.read.HQL          TCopoteEmployee.Read.read.JPQL      
	TCopoteEmployee.Combobox.combobox.SQL  TCopoteEmployee.Combobox.combobox.HQL  TCopoteEmployee.Combobox.combobox.JPQL
	TCopoteEmployee.Nest.nest.SQL          TCopoteEmployee.Nest.nest.HQL          TCopoteEmployee.Nest.nest.JPQL      
	TCopoteEmployee.Export.export.SQL      TCopoteEmployee.Export.export.HQL      TCopoteEmployee.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var TCopoteEmployee = {};
--@JavaScript TCopoteEmployee.Query = {};
--@JavaScript TCopoteEmployee.Add = {};
--@JavaScript TCopoteEmployee.Update = {};
--@JavaScript TCopoteEmployee.Read = {};
--@JavaScript TCopoteEmployee.Del = {};

--@JavaScript TCopoteEmployee.Combobox = {};
--@JavaScript TCopoteEmployee.Nest = {};
--@JavaScript TCopoteEmployee.Export = {};

--@JavaScript TCopoteEmployee.Query.query.SQL
SELECT
  t1.id "id",
  t1.uin "uin",
  t1.pid "pid",
  t1.name "name",
  t1.alias "alias",
  t1.sex "sex",
  t1.pos "pos",
  t1.tel "tel",
  t1.birth "birth",
  t1.slave_alias "slave_alias",
  t1.department "department",
  t1.mobile "mobile",
  t1.employee_no "employee_no",
  t1.employee_cardno "employee_cardno",
  t1.employee_idcard "employee_idcard",
  t1.logname "logname"
FROM
  tool.t_copote_employee t1

/** tableData HQL   */ 
--@JavaScript TCopoteEmployee.Query.query.HQL

--@JavaScript TCopoteEmployee.Query.query.JPQL

-- TCopoteEmployee.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript TCopoteEmployee.Read.read.SQL
SELECT
  t1.id "id",
  t1.uin "uin",
  t1.pid "pid",
  t1.name "name",
  t1.alias "alias",
  t1.sex "sex",
  t1.pos "pos",
  t1.tel "tel",
  t1.birth "birth",
  t1.slave_alias "slave_alias",
  t1.department "department",
  t1.mobile "mobile",
  t1.employee_no "employee_no",
  t1.employee_cardno "employee_cardno",
  t1.employee_idcard "employee_idcard",
  t1.logname "logname"
FROM
  tool.t_copote_employee t1

-- TCopoteEmployee.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript TCopoteEmployee.Read.read.HQL
-- TCopoteEmployee.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TTCopoteEmployee t1 left outer join t1.NMparent p

--@JavaScript TCopoteEmployee.Read.read.JPQL

-- SQL for select TCopoteEmployee.Combobox.combobox.data
--@JavaScript TCopoteEmployee.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd

--@JavaScript TCopoteEmployee.Combobox.combobox.HQL

--@JavaScript TCopoteEmployee.Combobox.combobox.JPQL

--@JavaScript TCopoteEmployee.Combobox.comboboxSBU.SQL
SELECT DISTINCT t.sbu_id "id", sbu "text" FROM tool.`t_Copote_employee` t WHERE sbu_id IS NOT NULL ORDER BY 2

--@JavaScript TCopoteEmployee.Combobox.comboboxCompany.SQL
SELECT DISTINCT company_id "id", company "text" FROM tool.`t_Copote_employee` t WHERE company_id IS NOT NULL ORDER BY 2

-- TCopoteEmployee.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript TCopoteEmployee.Export.export.SQL
SELECT
  t1.id "id",
  t1.uin "uin",
  t1.pid "pid",
  t1.name "name",
  t1.alias "alias",
  t1.sex "sex",
  t1.pos "pos",
  t1.tel "tel",
  t1.birth "birth",
  t1.slave_alias "slave_alias",
  t1.department "department",
  t1.mobile "mobile",
  t1.employee_no "employee_no",
  t1.employee_cardno "employee_cardno",
  t1.employee_idcard "employee_idcard",
  t1.logname "logname"
FROM
  tool.t_copote_employee t1
       
--@JavaScript TCopoteEmployee.Export.export.HQL

--@JavaScript TCopoteEmployee.Export.export.JPQL

--@JavaScript TCopoteEmployee.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript TCopoteEmployee.Nest.nest.HQL

--@JavaScript TCopoteEmployee.Nest.nest.JPQL
