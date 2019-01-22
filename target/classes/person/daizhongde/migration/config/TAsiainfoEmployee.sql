/** a variable, like javascript

--@JavaScript var TAsiainfoEmployee = {};
--@JavaScript TAsiainfoEmployee.Query = {};
--@JavaScript TAsiainfoEmployee.Add = {};
--@JavaScript TAsiainfoEmployee.Update = {};
--@JavaScript TAsiainfoEmployee.Read = {};
--@JavaScript TAsiainfoEmployee.Del = {};

--@JavaScript TAsiainfoEmployee.Combobox = {};
--@JavaScript TAsiainfoEmployee.Nest = {};
--@JavaScript TAsiainfoEmployee.Export = {};

ten TAsiainfoEmployee's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	TAsiainfoEmployee.Query.query.SQL        TAsiainfoEmployee.Query.query.HQL        TAsiainfoEmployee.Query.query.JPQL    
	TAsiainfoEmployee.Read.read.SQL          TAsiainfoEmployee.Read.read.HQL          TAsiainfoEmployee.Read.read.JPQL      
	TAsiainfoEmployee.Combobox.combobox.SQL  TAsiainfoEmployee.Combobox.combobox.HQL  TAsiainfoEmployee.Combobox.combobox.JPQL
	TAsiainfoEmployee.Nest.nest.SQL          TAsiainfoEmployee.Nest.nest.HQL          TAsiainfoEmployee.Nest.nest.JPQL      
	TAsiainfoEmployee.Export.export.SQL      TAsiainfoEmployee.Export.export.HQL      TAsiainfoEmployee.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var TAsiainfoEmployee = {};
--@JavaScript TAsiainfoEmployee.Query = {};
--@JavaScript TAsiainfoEmployee.Add = {};
--@JavaScript TAsiainfoEmployee.Update = {};
--@JavaScript TAsiainfoEmployee.Read = {};
--@JavaScript TAsiainfoEmployee.Del = {};

--@JavaScript TAsiainfoEmployee.Combobox = {};
--@JavaScript TAsiainfoEmployee.Nest = {};
--@JavaScript TAsiainfoEmployee.Export = {};

--@JavaScript TAsiainfoEmployee.Query.query.SQL
SELECT 
  t1.sbu_id "sbu_id",
  t1.sbu "sbu",
  t1.company_id "company_id",
  t1.company "company",
  t1.organization_id "organization_id",
  t1.org_name "org_name",
  t1.office "office",
  t1.pager "pager",
  t1.person_id "person_id",
  t1.employee_number "employee_number",
  t1.first_name "first_name",
  t1.last_name "last_name",
  t1.full_name "full_name",
  IFNULL( t1.email_address,'') "email_address",
  t1.age "age",
  t1.assignment_id "assignment_id",
  -- t1.birth_date "birth_date",
  date_format(t1.birth_date, '%Y-%m-%d') "birth_date",
  t1.class "class",
  t1.working_location "working_location",
  t1.seat_no "seat_no",
  t1.mobile "mobile",
  t1.nt_account "nt_account",
  t1.supervisor_id "supervisor_id",
  t1.supervisor_name "supervisor_name",
  t1.highest_degree "highest_degree",
  -- t1.hire_date "hire_date" 
  date_format(t1.hire_date, '%Y-%m-%d') "hire_date"
FROM
  tool.t_asiainfo_employee t1 

/** tableData HQL   */ 
--@JavaScript TAsiainfoEmployee.Query.query.HQL

--@JavaScript TAsiainfoEmployee.Query.query.JPQL

-- TAsiainfoEmployee.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript TAsiainfoEmployee.Read.read.SQL
SELECT 
  t1.sbu_id "sbu_id",
  t1.sbu "sbu",
  t1.company_id "company_id",
  t1.company "company",
  t1.organization_id "organization_id",
  t1.org_name "org_name",
  t1.office "office",
  t1.pager "pager",
  t1.person_id "person_id",
  t1.employee_number "employee_number",
  t1.first_name "first_name",
  t1.last_name "last_name",
  t1.full_name "full_name",
  t1.email_address "email_address",
  t1.age "age",
  t1.assignment_id "assignment_id",
  -- t1.birth_date "birth_date",
  date_format(t1.birth_date, '%Y-%m-%d') "birth_date",
  t1.class "class",
  t1.working_location "working_location",
  t1.seat_no "seat_no",
  t1.mobile "mobile",
  t1.nt_account "nt_account",
  t1.supervisor_id "supervisor_id",
  t1.supervisor_name "supervisor_name",
  t1.highest_degree "highest_degree",
  -- t1.hire_date "hire_date" 
  date_format(t1.hire_date, '%Y-%m-%d') "hire_date"
FROM
  tool.t_asiainfo_employee t1 

-- TAsiainfoEmployee.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript TAsiainfoEmployee.Read.read.HQL
-- TAsiainfoEmployee.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TTAsiainfoEmployee t1 left outer join t1.NMparent p

--@JavaScript TAsiainfoEmployee.Read.read.JPQL

-- SQL for select TAsiainfoEmployee.Combobox.combobox.data
--@JavaScript TAsiainfoEmployee.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd

--@JavaScript TAsiainfoEmployee.Combobox.combobox.HQL

--@JavaScript TAsiainfoEmployee.Combobox.combobox.JPQL

--@JavaScript TAsiainfoEmployee.Combobox.comboboxSBU.SQL
SELECT DISTINCT t.sbu_id "id", sbu "text" FROM tool.`t_asiainfo_employee` t WHERE sbu_id IS NOT NULL ORDER BY 2

--@JavaScript TAsiainfoEmployee.Combobox.comboboxCompany.SQL
SELECT DISTINCT company_id "id", company "text" FROM tool.`t_asiainfo_employee` t WHERE company_id IS NOT NULL ORDER BY 2

-- TAsiainfoEmployee.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript TAsiainfoEmployee.Export.export.SQL
SELECT 
  t1.sbu_id "sbu_id",
  t1.sbu "sbu",
  t1.company_id "company_id",
  t1.company "company",
  t1.organization_id "organization_id",
  t1.org_name "org_name",
  t1.office "office",
  t1.pager "pager",
  t1.person_id "person_id",
  t1.employee_number "employee_number",
  t1.first_name "first_name",
  t1.last_name "last_name",
  t1.full_name "full_name",
  t1.email_address "email_address",
  t1.age "age",
  t1.assignment_id "assignment_id",
  -- t1.birth_date "birth_date",
  date_format(t1.birth_date, '%Y-%m-%d') "birth_date",
  t1.class "class",
  t1.working_location "working_location",
  t1.seat_no "seat_no",
  t1.mobile "mobile",
  t1.nt_account "nt_account",
  t1.supervisor_id "supervisor_id",
  t1.supervisor_name "supervisor_name",
  t1.highest_degree "highest_degree",
  -- t1.hire_date "hire_date" 
  date_format(t1.hire_date, '%Y-%m-%d') "hire_date"
FROM
  tool.t_asiainfo_employee t1 
       
--@JavaScript TAsiainfoEmployee.Export.export.HQL

--@JavaScript TAsiainfoEmployee.Export.export.JPQL

--@JavaScript TAsiainfoEmployee.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript TAsiainfoEmployee.Nest.nest.HQL

--@JavaScript TAsiainfoEmployee.Nest.nest.JPQL
