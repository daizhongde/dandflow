/** a variable, like javascript

--@JavaScript var MigJobIns = {};
--@JavaScript MigJobIns.Query = {};
--@JavaScript MigJobIns.Add = {};
--@JavaScript MigJobIns.Update = {};
--@JavaScript MigJobIns.Read = {};
--@JavaScript MigJobIns.Del = {};

--@JavaScript MigJobIns.Combobox = {};
--@JavaScript MigJobIns.Nest = {};
--@JavaScript MigJobIns.Export = {};

ten MigJobIns's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigJobIns.Query.query.SQL        MigJobIns.Query.query.HQL        MigJobIns.Query.query.JPQL    
	MigJobIns.Read.read.SQL          MigJobIns.Read.read.HQL          MigJobIns.Read.read.JPQL      
	MigJobIns.Combobox.combobox.SQL  MigJobIns.Combobox.combobox.HQL  MigJobIns.Combobox.combobox.JPQL
	MigJobIns.Nest.nest.SQL          MigJobIns.Nest.nest.HQL          MigJobIns.Nest.nest.JPQL      
	MigJobIns.Export.export.SQL      MigJobIns.Export.export.HQL      MigJobIns.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigJobIns = {};
--@JavaScript MigJobIns.Query = {};
--@JavaScript MigJobIns.Add = {};
--@JavaScript MigJobIns.Update = {};
--@JavaScript MigJobIns.Read = {};
--@JavaScript MigJobIns.Del = {};

--@JavaScript MigJobIns.Combobox = {};
--@JavaScript MigJobIns.Nest = {};
--@JavaScript MigJobIns.Export = {};

--@JavaScript MigJobIns.Query.query.SQL
select t1.JOB_INS_ID "job_ins_id",
	CAST(IFNULL((SELECT MIG_DRYRUN_NAME FROM v_dryrun_config WHERE MIG_DRYRUN_ID=t1.dryrun_id),t1.dryrun_id) AS CHAR(256)) "dryrun",
	t1.JOB_ID "job_id",
	t1.JOB_INS_NAME "job_ins_name",
	t1.type "type",
	t1.STATUS "status",
	--t1.MTIME "mtime",
date_format(t1.MTIME, '%Y-%m-%d %H:%i:%S') "mtime",
	t1.AUTHOR "author",
	t1.REMARK "remark",
	t1.LOCK_STATUS "lock_status" 
  from tool.mig_job_ins t1


/* tableData HQL   */ 
--@JavaScript MigJobIns.Query.query.HQL

--@JavaScript MigJobIns.Query.query.JPQL

--@JavaScript MigJobIns.Query.queryTreeGridData.SQL
SELECT 
	CONCAT(job_ins_id,'-',job_id) "id", 
	job_ins_name  "text", 'icon-job' AS "iconCls", 'closed' AS "state", remark "note", 
	CAST(IFNULL((SELECT MIG_DRYRUN_NAME FROM v_dryrun_config WHERE MIG_DRYRUN_ID=t1.dryrun_id ), t1.dryrun_id) AS CHAR(256)) "dryrun",
	(SELECT VALUE FROM mig_codedetail_define t2 WHERE t2.type='job_type' AND t2.code=t1.type) "type",
	STATUS "status", 
	author "author", 
	DATE_FORMAT(t1.begin_time, '%Y-%m-%d %H:%i:%S') "beginTime",
	DATE_FORMAT(t1.end_time, '%Y-%m-%d %H:%i:%S') "endTime", 
    CONCAT( IF(end_time IS NOT NULL AND begin_time IS NOT NULL AND  end_time<begin_time, '-',''),
     CAST(SEC_TO_TIME(ABS(UNIX_TIMESTAMP(end_time)-UNIX_TIMESTAMP(begin_time))) AS CHAR(19))) AS "totalTime"
 FROM tool.mig_job_ins t1
  
--@JavaScript MigJobIns.Query.querycbb.SQL
select t1.JOB_INS_ID "job_ins_id",
	t1.JOB_ID "job_id",
	t1.JOB_INS_NAME "job_ins_name",
	t1.type "type",
	t1.STATUS "status",
	--t1.MTIME "mtime",
date_format(t1.MTIME, '%Y-%m-%d %H:%i:%S') "mtime",
	t1.AUTHOR "author",
	t1.REMARK "remark",
	t1.LOCK_STATUS "lock_status" 
  from tool.mig_job_ins t1
  
-- MigJobIns.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigJobIns.Read.read.SQL
select t1.JOB_INS_ID "job_ins_id",
	t1.JOB_ID "job_id",
	t1.JOB_INS_NAME "job_ins_name",
	t1.type "type",
	t1.STATUS "status",
	--t1.MTIME "mtime",
date_format(t1.MTIME, '%Y-%m-%d %H:%i:%S') "mtime",
	t1.AUTHOR "author",
	t1.REMARK "remark",
	t1.LOCK_STATUS "lock_status" 
  from tool.mig_job_ins t1



-- MigJobIns.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigJobIns.Read.read.HQL
-- MigJobIns.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigJobIns t1 left outer join t1.NMparent p

--@JavaScript MigJobIns.Read.read.JPQL

-- SQL for select MigJobIns.Combobox.combobox.data
--@JavaScript MigJobIns.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigJobIns.Combobox.combobox.data
--@JavaScript MigJobIns.Combobox.combobox.HQL

--@JavaScript MigJobIns.Combobox.combobox.JPQL

-- MigJobIns.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigJobIns.Export.export.SQL
select t1.JOB_ID     "job_id",
       t1.JOB_NAME   "job_name",
       t1.JOB_CRON   "job_cron",
       t1.JOB_AUTHOR "job_author",
       t1.JOB_REMARK "job_remark",
       t1.JOB_UPDATE "job_update"
  from tool.mig_job_info t1

       
--@JavaScript MigJobIns.Export.export.HQL

--@JavaScript MigJobIns.Export.export.JPQL

--@JavaScript MigJobIns.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigJobIns.Nest.nest.HQL

--@JavaScript MigJobIns.Nest.nest.JPQL
