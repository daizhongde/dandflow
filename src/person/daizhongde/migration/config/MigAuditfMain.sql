/** a variable, like javascript

--@JavaScript var MigAuditfMain = {};
--@JavaScript MigAuditfMain.Query = {};
--@JavaScript MigAuditfMain.Add = {};
--@JavaScript MigAuditfMain.Update = {};
--@JavaScript MigAuditfMain.Read = {};
--@JavaScript MigAuditfMain.Del = {};

--@JavaScript MigAuditfMain.Combobox = {};
--@JavaScript MigAuditfMain.Nest = {};
--@JavaScript MigAuditfMain.Export = {};

ten MigAuditfMain's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditfMain.Query.query.SQL        MigAuditfMain.Query.query.HQL        MigAuditfMain.Query.query.JPQL    
	MigAuditfMain.Read.read.SQL          MigAuditfMain.Read.read.HQL          MigAuditfMain.Read.read.JPQL      
	MigAuditfMain.Combobox.combobox.SQL  MigAuditfMain.Combobox.combobox.HQL  MigAuditfMain.Combobox.combobox.JPQL
	MigAuditfMain.Nest.nest.SQL          MigAuditfMain.Nest.nest.HQL          MigAuditfMain.Nest.nest.JPQL      
	MigAuditfMain.Export.export.SQL      MigAuditfMain.Export.export.HQL      MigAuditfMain.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditfMain = {};
--@JavaScript MigAuditfMain.Query = {};
--@JavaScript MigAuditfMain.Add = {};
--@JavaScript MigAuditfMain.Update = {};
--@JavaScript MigAuditfMain.Read = {};
--@JavaScript MigAuditfMain.Del = {};

--@JavaScript MigAuditfMain.Combobox = {};
--@JavaScript MigAuditfMain.Nest = {};
--@JavaScript MigAuditfMain.Export = {};

--@JavaScript MigAuditfMain.Query.query.SQL
select t1.FAUDIT_ID            "faudit_id",
	   t1.domain "domain",
       t1.FAUDIT_NAME          "faudit_name",
       t1.FAUDIT_SRCTABLE_NAME "faudit_srctable_name",
       left( t1.FAUDIT_SRCTABLE_CONN, 200 ) "faudit_srctable_conn",
       t1.FAUDIT_DSTTABLE_NAME "faudit_dsttable_name",
       left( t1.FAUDIT_DSTTABLE_CONN, 200 ) "faudit_dsttable_conn",
       t1.Author               "author",
       left( t1.FAUDIT_DESC, 200 )          "faudit_desc",
	   date_format(t1.faudit_createtime, '%Y-%m-%d %H:%i:%S') "faudit_createtime"
  from tool.mig_auditf_main t1

/* tableData HQL   */ 
--@JavaScript MigAuditfMain.Query.query.HQL

--@JavaScript MigAuditfMain.Query.query.JPQL

--@JavaScript MigAuditfMain.Query.queryAuditfConfig.SQL
select t1.FAUDIT_ID          "faudit_id",
	   t1.domain "domain",
       t1.FAUDIT_NAME        "faudit_name",
       t1.FAUDIT_SRCTABLE_NAME "faudit_srctable_name",
       left( t1.FAUDIT_SRCTABLE_CONN, 200 ) "faudit_srctable_conn",
       t1.FAUDIT_DSTTABLE_NAME "faudit_dsttable_name",
       left( t1.FAUDIT_DSTTABLE_CONN, 200 ) "faudit_dsttable_conn",
       t1.Author               "author",
       left( t1.FAUDIT_DESC, 200 )          "faudit_desc",
	   date_format(t1.faudit_createtime, '%Y-%m-%d %H:%i:%S') "faudit_createtime",
	   cast(sub_desc as CHAR) "sub_desc"
  from tool.mig_auditf_main t1
left outer join
(
select faudit_main_id,GROUP_CONCAT( CONCAT('[',
  CONCAT_WS('&',FAUDIT_SUB_INDEX, FAUDIT_SRC_FIELD,FAUDIT_DST_FIELD,FAUDIT_ISKEY, FAUDIT_OPT),
 ']') ) sub_desc
  from tool.mig_auditf_sub 
 where faudit_status=1
 group by faudit_main_id
) t2 on t2.faudit_main_id = t1.FAUDIT_ID

/* FAUDIT_SUB_INDEX  FAUDIT_SRC_FIELD  FAUDIT_DST_FIELD  FAUDIT_ISKEY  FAUDIT_OPT  */
--@JavaScript MigAuditfMain.Query.querySubDesc.SQL
select GROUP_CONCAT( CONCAT('[',
  CONCAT_WS('&',FAUDIT_SUB_INDEX, FAUDIT_SRC_FIELD,FAUDIT_DST_FIELD,FAUDIT_ISKEY, FAUDIT_OPT),
 ']') )
from tool.mig_auditf_sub
where FAUDIT_MAIN_ID=1
group by faudit_main_id

--@JavaScript MigAuditfMain.Query.querycbb.SQL
select t1.FAUDIT_ID            "faudit_id",
	   t1.domain "domain",
       t1.FAUDIT_NAME          "faudit_name",
       t1.FAUDIT_SRCTABLE_NAME "faudit_srctable_name",
       t1.FAUDIT_SRCTABLE_CONN "faudit_srctable_conn",
       t1.FAUDIT_DSTTABLE_NAME "faudit_dsttable_name",
       t1.FAUDIT_DSTTABLE_CONN "faudit_dsttable_conn",
       t1.Author               "author",
       t1.FAUDIT_DESC          "faudit_desc",
		date_format(t1.faudit_createtime, '%Y-%m-%d %H:%i:%S') "faudit_createtime"
  from tool.mig_auditf_main t1
  
-- MigAuditfMain.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditfMain.Read.read.SQL
select t1.FAUDIT_ID            "faudit_id",
	   t1.domain "domain",
       t1.FAUDIT_NAME          "faudit_name",
       t1.FAUDIT_SRCTABLE_NAME "faudit_srctable_name",
       t1.FAUDIT_SRCTABLE_CONN "faudit_srctable_conn",
       t1.FAUDIT_DSTTABLE_NAME "faudit_dsttable_name",
       t1.FAUDIT_DSTTABLE_CONN "faudit_dsttable_conn",
       t1.Author               "author",
       t1.FAUDIT_DESC          "faudit_desc",
		date_format(t1.faudit_createtime, '%Y-%m-%d %H:%i:%S') "faudit_createtime"
  from tool.mig_auditf_main t1


-- MigAuditfMain.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditfMain.Read.read.HQL
-- MigAuditfMain.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditfMain t1 left outer join t1.NMparent p

--@JavaScript MigAuditfMain.Read.read.JPQL

-- SQL for select MigAuditfMain.Combobox.combobox.data
--@JavaScript MigAuditfMain.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditfMain.Combobox.combobox.data
--@JavaScript MigAuditfMain.Combobox.combobox.HQL

--@JavaScript MigAuditfMain.Combobox.combobox.JPQL

-- MigAuditfMain.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditfMain.Export.export.SQL
select t1.FAUDIT_ID "faudit_id",t1.FAUDIT_NAME "faudit_name",
t1.FAUDIT_SRCTABLE_NAME "faudit_srctable_name",
t1.FAUDIT_SRCTABLE_CONN "faudit_srctable_conn",
t1.FAUDIT_DSTTABLE_NAME "faudit_dsttable_name",
t1.FAUDIT_DSTTABLE_CONN "faudit_dsttable_conn",
t1.FAUDIT_TYPE "faudit_type",
t1.FAUDIT_GROUP "faudit_group",
t1.FAUDIT_GROUP_NAME "faudit_group_name",
t1.FAUDIT_BUSSINESS_CLUSTER "faudit_bussiness_cluster",
t1.FAUDIT_BUSSINESS_NAME "faudit_bussiness_name",
t1.Author "author",t1.FAUDIT_DESC "faudit_desc",
t1.FAUDIT_CREATETIME "faudit_createtime",
t1.FAUDIT_MODIFYTIME "faudit_modifytime",
t1.FAUDIT_FINISHTIME "faudit_finishtime",
t1.FAUDIT_STATUS "faudit_status" 
from tool.mig_auditf_main t1

       
--@JavaScript MigAuditfMain.Export.export.HQL

--@JavaScript MigAuditfMain.Export.export.JPQL

--@JavaScript MigAuditfMain.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditfMain.Nest.nest.HQL

--@JavaScript MigAuditfMain.Nest.nest.JPQL
