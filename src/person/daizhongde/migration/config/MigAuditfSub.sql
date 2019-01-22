/** a variable, like javascript

--@JavaScript var MigAuditfSub = {};
--@JavaScript MigAuditfSub.Query = {};
--@JavaScript MigAuditfSub.Add = {};
--@JavaScript MigAuditfSub.Update = {};
--@JavaScript MigAuditfSub.Read = {};
--@JavaScript MigAuditfSub.Del = {};

--@JavaScript MigAuditfSub.Combobox = {};
--@JavaScript MigAuditfSub.Nest = {};
--@JavaScript MigAuditfSub.Export = {};

ten MigAuditfSub's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditfSub.Query.query.SQL        MigAuditfSub.Query.query.HQL        MigAuditfSub.Query.query.JPQL    
	MigAuditfSub.Read.read.SQL          MigAuditfSub.Read.read.HQL          MigAuditfSub.Read.read.JPQL      
	MigAuditfSub.Combobox.combobox.SQL  MigAuditfSub.Combobox.combobox.HQL  MigAuditfSub.Combobox.combobox.JPQL
	MigAuditfSub.Nest.nest.SQL          MigAuditfSub.Nest.nest.HQL          MigAuditfSub.Nest.nest.JPQL      
	MigAuditfSub.Export.export.SQL      MigAuditfSub.Export.export.HQL      MigAuditfSub.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditfSub = {};
--@JavaScript MigAuditfSub.Query = {};
--@JavaScript MigAuditfSub.Add = {};
--@JavaScript MigAuditfSub.Update = {};
--@JavaScript MigAuditfSub.Read = {};
--@JavaScript MigAuditfSub.Del = {};

--@JavaScript MigAuditfSub.Combobox = {};
--@JavaScript MigAuditfSub.Nest = {};
--@JavaScript MigAuditfSub.Export = {};

--@JavaScript MigAuditfSub.Query.query.SQL
select t1.FAUDIT_MAIN_ID "faudit_main_id",
t1.FAUDIT_SUB_INDEX "faudit_sub_index",
t1.FAUDIT_SRC_FIELD "faudit_src_field",
t1.FAUDIT_DST_FIELD "faudit_dst_field",
t1.FAUDIT_ISKEY "faudit_iskey",
t1.FAUDIT_OPT "faudit_opt",
--t1.FAUDIT_CREATETIME "faudit_createtime",
--t1.FAUDIT_MODIFYTIME "faudit_modifytime",
date_format(t1.faudit_createtime, '%Y-%m-%d %H:%i:%S') "faudit_createtime",
date_format(t1.faudit_modifytime, '%Y-%m-%d %H:%i:%S') "faudit_modifytime",


t1.FAUDIT_STATUS "faudit_status" 
from tool.mig_auditf_sub t1


/* tableData HQL   */ 
--@JavaScript MigAuditfSub.Query.query.HQL

--@JavaScript MigAuditfSub.Query.query.JPQL

-- MigAuditfSub.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditfSub.Read.read.SQL
select t1.FAUDIT_MAIN_ID "faudit_main_id",
	(select faudit_name 
	   from tool.mig_auditf_main 
	  where FAUDIT_ID=t1.FAUDIT_MAIN_ID ) "faudit_name",
t1.FAUDIT_SUB_INDEX "faudit_sub_index",
t1.FAUDIT_SRC_FIELD "faudit_src_field",
t1.FAUDIT_DST_FIELD "faudit_dst_field",
--t1.FAUDIT_ISKEY "faudit_iskey",
case when FAUDIT_ISKEY=0 then 'N' else 'Y' end "faudit_iskey",
t1.FAUDIT_OPT "faudit_opt",
--t1.FAUDIT_CREATETIME "faudit_createtime",
--t1.FAUDIT_MODIFYTIME "faudit_modifytime",
date_format(t1.faudit_createtime, '%Y-%m-%d %H:%i:%S') "faudit_createtime",
date_format(t1.faudit_modifytime, '%Y-%m-%d %H:%i:%S') "faudit_modifytime",

--t1.FAUDIT_STATUS "faudit_status" 
case when FAUDIT_STATUS=1 then 'Valid' else 'Invalid' end "faudit_status"

from tool.mig_auditf_sub t1



-- MigAuditfSub.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditfSub.Read.read.HQL
-- MigAuditfSub.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditfSub t1 left outer join t1.NMparent p

--@JavaScript MigAuditfSub.Read.read.JPQL

-- SQL for select MigAuditfSub.Combobox.combobox.data
--@JavaScript MigAuditfSub.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditfSub.Combobox.combobox.data
--@JavaScript MigAuditfSub.Combobox.combobox.HQL

--@JavaScript MigAuditfSub.Combobox.combobox.JPQL

-- MigAuditfSub.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditfSub.Export.export.SQL
select t1.FAUDIT_MAIN_ID "faudit_main_id",
t1.FAUDIT_SUB_INDEX "faudit_sub_index",
t1.FAUDIT_SRC_FIELD "faudit_src_field",
t1.FAUDIT_DST_FIELD "faudit_dst_field",
t1.FAUDIT_ISKEY "faudit_iskey",
t1.FAUDIT_OPT "faudit_opt",
t1.FAUDIT_CREATETIME "faudit_createtime",
t1.FAUDIT_MODIFYTIME "faudit_modifytime",
t1.FAUDIT_STATUS "faudit_status" 
from tool.mig_auditf_sub t1

       
--@JavaScript MigAuditfSub.Export.export.HQL

--@JavaScript MigAuditfSub.Export.export.JPQL

--@JavaScript MigAuditfSub.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditfSub.Nest.nest.HQL

--@JavaScript MigAuditfSub.Nest.nest.JPQL
