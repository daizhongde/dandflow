/** a variable, like javascript

--@JavaScript var MigAuditvResult = {};
--@JavaScript MigAuditvResult.Query = {};
--@JavaScript MigAuditvResult.Add = {};
--@JavaScript MigAuditvResult.Update = {};
--@JavaScript MigAuditvResult.Read = {};
--@JavaScript MigAuditvResult.Del = {};

--@JavaScript MigAuditvResult.Combobox = {};
--@JavaScript MigAuditvResult.Nest = {};
--@JavaScript MigAuditvResult.Export = {};

ten MigAuditvResult's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditvResult.Query.query.SQL        MigAuditvResult.Query.query.HQL        MigAuditvResult.Query.query.JPQL    
	MigAuditvResult.Read.read.SQL          MigAuditvResult.Read.read.HQL          MigAuditvResult.Read.read.JPQL      
	MigAuditvResult.Combobox.combobox.SQL  MigAuditvResult.Combobox.combobox.HQL  MigAuditvResult.Combobox.combobox.JPQL
	MigAuditvResult.Nest.nest.SQL          MigAuditvResult.Nest.nest.HQL          MigAuditvResult.Nest.nest.JPQL      
	MigAuditvResult.Export.export.SQL      MigAuditvResult.Export.export.HQL      MigAuditvResult.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditvResult = {};
--@JavaScript MigAuditvResult.Query = {};
--@JavaScript MigAuditvResult.Add = {};
--@JavaScript MigAuditvResult.Update = {};
--@JavaScript MigAuditvResult.Read = {};
--@JavaScript MigAuditvResult.Del = {};

--@JavaScript MigAuditvResult.Combobox = {};
--@JavaScript MigAuditvResult.Nest = {};
--@JavaScript MigAuditvResult.Export = {};

--@JavaScript MigAuditvResult.Query.query.SQL
SELECT t1.ID				 "id",
	   t1.AUDIT_ID           "audit_id",
       t1.FARES_DRYRUN_ID    "fares_dryrun_id",
      (SELECT MIG_DRYRUN_NAME FROM tool.v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
      (SELECT LEFT(reason,200) FROM tool.mig_auditv_errreason WHERE audit_id=t1.audit_id AND env=t1.env ) "min_analysis",
       t1.DOMAIN             "domain",
      (SELECT VALUE FROM tool.mig_codedetail_define WHERE TYPE='Busi-Domain' AND CODE=t1.DOMAIN) "domain2",
       t1.TABLE_NAME         "table_name",
       t1.AUDIT_NAME         "audit_name",
       t1.SRC_VALUE          "src_value",
       t1.DST_VALUE          "dst_value",
       t1.MIN_VALUE          "min_value",
       t1.AUDIT_UNIT         "audit_unit",
       t1.INVALID_DATA_TABLE "invalid_data_table",
       t1.RESULT             "result",
       CASE WHEN t1.RESULT=1 THEN '稽核正确' ELSE '稽核错误' END "result_msg",
       t1.AUDIT_AUTHOR       "audit_author",
       -- t1.HDATE              "hdate",
       DATE_FORMAT(t1.HDATE, '%Y-%m-%d %H:%i:%S') "hdate",
       LEFT(t1.REMARK,200)             "remark",
       t1.SUCCESS_FLAG       "success_flag",
       CASE WHEN t1.SUCCESS_FLAG=1 THEN '没有错误记录' ELSE '有错误记录' END "success",
       t1.ERR_MSG            "err_msg",
       t1.INVALID_DATA_CNT   "invalid_data_cnt",
       (SELECT dmp_no FROM tool.mig_auditv_errreason WHERE audit_id=t1.audit_id AND env=t1.env ) "dmp_num",
       -- IFNULL(ENV,'') "env",
       IFNULL((SELECT VALUE FROM tool.mig_codedetail_define WHERE TYPE='DB-Env' AND CODE=ENV),'') "env",
       t2.AUDIT_LEVEL "audit_level",
       t1.src_audit_sql "src_audit_sql"
  FROM tool.mig_auditv_result t1 
  LEFT OUTER JOIN tool.mig_auditv_config t2
  ON t1.AUDIT_ID = t2.AUDIT_ID
  
/* tableData HQL   */ 
--@JavaScript MigAuditvResult.Query.query.HQL

--@JavaScript MigAuditvResult.Query.query.JPQL

--@JavaScript MigAuditvResult.Query.queryDiff.SQL
SELECT t3.*, IF(ok='FALSE',IFNULL(t4.reason,''),'') "reason", IFNULL(t4.dmp_no,'') "dmp_no" FROM 
(
	SELECT t2.audit_level "audit_level",
		   t2.AUDIT_ID "audit_id", 
	       t2.audit_name AS "audit_item",
	       t1.fares_dryrun_id "fares_dryrun_id",
	       (SELECT MIG_DRYRUN_NAME FROM tool.v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
	       IFNULL(t1.ENV,'') "env",
	       t2.domain "domain",
	       t2.AUDIT_AUTHOR AS "config_author",
	       DATE_FORMAT(t1.hdate,"%Y-%m-%d") "audit_date",
	-- acorss 0 clock audit have problem
	       IF(SUM(IF(t1.success_flag='0',1,IF(t1.result='0',1,0)))>0,'FALSE','TRUE') AS "ok",
	       CAST(IF(MIN(t1.success_flag)='0',-1,SUM(t1.min_value))  AS SIGNED) AS "invalid_count"
	  FROM tool.mig_auditv_result t1, tool.mig_auditv_config t2
	 WHERE t1.audit_id=t2.audit_id
	   -- AND t1.fares_dryrun_id='5' AND t1.env='C' AND t1.domain='2' AND t2.AUDIT_AUTHOR='huyx3'
     GROUP BY env,domain, config_author, AUDIT_ID, audit_item,fares_dryrun_id,  audit_date
) t3
  LEFT OUTER JOIN tool.mig_auditv_errreason t4
    ON t3.audit_id = t4.audit_id AND t3.env=t4.env
 
-- MigAuditvResult.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditvResult.Read.read.SQL
SELECT t1.ID				 "id",
	   t1.AUDIT_ID           "audit_id",
       t1.FARES_DRYRUN_ID    "fares_dryrun_id",
      (SELECT MIG_DRYRUN_NAME FROM tool.v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
       (SELECT reason FROM tool.mig_auditv_errreason WHERE audit_id=t1.audit_id AND env=t1.env ) "min_analysis",
       t1.DOMAIN             "domain",
       (SELECT VALUE FROM tool.mig_codedetail_define WHERE TYPE='Busi-Domain' AND CODE=t1.DOMAIN) "domain2",
       t1.TABLE_NAME         "table_name",
       t1.AUDIT_NAME         "audit_name",
       t1.SRC_VALUE          "src_value",
       t1.DST_VALUE          "dst_value",
       t1.MIN_VALUE          "min_value",
       t1.AUDIT_UNIT         "audit_unit",
       t1.INVALID_DATA_TABLE "invalid_data_table",
       t1.RESULT             "result",
       CASE WHEN t1.RESULT=1 THEN '稽核正确' ELSE '稽核错误' END "result_msg",
       t1.AUDIT_AUTHOR       "audit_author",
       --t1.HDATE              "hdate",
       DATE_FORMAT(t1.HDATE, '%Y-%m-%d %H:%i:%S') "hdate",
       t1.REMARK             "remark",
       t1.SUCCESS_FLAG      "success_flag",
       CASE WHEN SUCCESS_FLAG=1 THEN '没有错误记录' ELSE '有错误记录' END "success",
       t1.ERR_MSG            "err_msg",
       t1.INVALID_DATA_CNT   "invalid_data_cnt",
       (SELECT dmp_no FROM tool.mig_auditv_errreason WHERE audit_id=t1.audit_id AND env=t1.env ) "dmp_num",
       IFNULL(t1.ENV,'') "env",
       t1.src_audit_sql "src_audit_sql"
  FROM tool.mig_auditv_result t1
  
--@JavaScript MigAuditvResult.Read.readDiff.SQL
SELECT t3.*, IF(ok='FALSE',IFNULL(t4.reason,''),'') "reason", IFNULL(t4.dmp_no,'') "dmp_no" FROM 
(
	SELECT t2.audit_level "audit_level",
		   t2.AUDIT_ID "audit_id", 
	       t2.audit_name AS "audit_item",
	       t1.fares_dryrun_id "fares_dryrun_id",
	       (SELECT MIG_DRYRUN_NAME FROM tool.v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
	       IFNULL(t1.ENV,'') "env",
	       t2.domain "domain",
	       (SELECT NAME FROM tool.v_busi_domain WHERE id=t1.FARES_DRYRUN_ID ) "domainName",
	       t2.AUDIT_AUTHOR AS "config_author",
	       DATE_FORMAT(t1.hdate,"%Y-%m-%d") "audit_date",
	-- acorss 0 clock audit have problem, because of group by audit_date
	       IF(SUM(IF(t1.success_flag='0',1,IF(t1.result='0',1,0)))>0,'FALSE','TRUE') AS "ok",
	       cast(IF(MIN(t1.success_flag)='0','#',SUM(t1.min_value))  AS CHAR(11)) AS "invalid_count"
	  FROM tool.mig_auditv_result t1, tool.mig_auditv_config t2
	 WHERE t1.audit_id=t2.audit_id
	       AND t1.env = :env and t1.audit_id = :audit_id
     GROUP BY env,domain, config_author, AUDIT_ID, audit_item,fares_dryrun_id,  audit_date
) t3
  LEFT OUTER JOIN tool.mig_auditv_errreason t4
    ON t3.audit_id = t4.audit_id AND t3.env=t4.env
 WHERE ok='FALSE'
  
-- MigAuditvResult.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditvResult.Read.read.HQL
-- MigAuditvResult.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditvResult t1 left outer join t1.NMparent p

--@JavaScript MigAuditvResult.Read.read.JPQL

-- SQL for select MigAuditvResult.Combobox.combobox.data
--@JavaScript MigAuditvResult.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditvResult.Combobox.combobox.data
--@JavaScript MigAuditvResult.Combobox.combobox.HQL

--@JavaScript MigAuditvResult.Combobox.combobox.JPQL

-- MigAuditvResult.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditvResult.Export.export.SQL
SELECT t1.AUDIT_ID,
       t1.FARES_DRYRUN_ID,
      (SELECT MIG_DRYRUN_NAME FROM tool.v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
       (SELECT reason FROM tool.mig_auditv_errreason WHERE audit_id=t1.audit_id AND env=t1.env ) "MIN_ANALYSIS",
       (SELECT VALUE FROM tool.mig_codedetail_define WHERE CODE=t1.DOMAIN AND TYPE='Busi-Domain' ) DOMAIN2,
       t1.TABLE_NAME,
       t1.AUDIT_NAME,
       t1.SRC_VALUE,
       t1.DST_VALUE,
       t1.MIN_VALUE,
       t1.AUDIT_UNIT,
       t1.INVALID_DATA_TABLE,
       --t1.RESULT,
       --case WHEN t1.RESULT=1 THEN '稽核正确' ELSE '稽核错误' END "result_msg",
       t1.AUDIT_AUTHOR,
       --t1.HDATE              "hdate",
       DATE_FORMAT(t1.HDATE, '%Y-%m-%d %H:%i:%S') "hdate2",
       t1.REMARK,
       t1.SUCCESS_FLAG,
       CASE WHEN t1.SUCCESS_FLAG=0 THEN 'Error' ELSE (CASE WHEN t1.RESULT=0 THEN 'Unpass' ELSE 'Pass' END ) END "success",
       t1.ERR_MSG,
       t1.INVALID_DATA_CNT,
       (SELECT dmp_no FROM tool.mig_auditv_errreason WHERE audit_id=t1.audit_id AND env=t1.env ) "DMP_NUM",
       IFNULL(t1.ENV,'') "ENV",
       t1.src_audit_sql "SRC_AUDIT_SQL"
  FROM tool.mig_auditv_result t1

--@JavaScript MigAuditvResult.Export.export4KPIReport.SQL
SELECT t1.AUDIT_LEVEL, 
       t1.AUDIT_ID, 
       t1.AUDIT_ITEM, 
       t1.FARES_DRYRUN_ID, 
       t1.DRYRUN_NAME "dryrun_name", 
       t1.ENV, 
       t1.DOMAIN, 
       t1.CONFIG_AUTHOR, 
       t1.AUDIT_DATE, 
       t1.OK, 
       t1.INVALID_COUNT,
IF(ok='FALSE',IFNULL(t4.reason,''),'') REASON, IFNULL(t4.dmp_no,'') DMP_NO

 FROM 
(
	SELECT t2.audit_level "audit_level",
		   t2.AUDIT_ID "audit_id", 
	       t2.audit_name AS "audit_item",
	       t1.fares_dryrun_id "fares_dryrun_id",
	       (SELECT MIG_DRYRUN_NAME FROM tool.v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
	       IFNULL(t1.ENV,'') "env",
	       t2.domain "domain",
	       t2.AUDIT_AUTHOR AS "config_author",
	       DATE_FORMAT(t1.hdate,"%Y-%m-%d") "audit_date",
	-- acorss 0 clock audit have problem
	       IF(SUM(IF(t1.success_flag='0',1,IF(t1.result='0',1,0)))>0,'FALSE','TRUE') AS "ok",
	       CAST(IF(MIN(t1.success_flag)='0',-1,SUM(t1.min_value))  AS SIGNED) AS "invalid_count"
	  FROM tool.mig_auditv_result t1, tool.mig_auditv_config t2
	 WHERE t1.audit_id=t2.audit_id
	  -- AND t1.fares_dryrun_id=7 -- AND t1.env='C' AND t1.domain='2' AND t2.AUDIT_AUTHOR='huyx3'
     GROUP BY env,domain, config_author, AUDIT_ID, audit_item,fares_dryrun_id,  audit_date
) t1
  LEFT OUTER JOIN tool.mig_auditv_errreason t4
    ON t1.audit_id = t4.audit_id AND t1.env=t4.env
    -- WHERE ok='false'
    
--@JavaScript MigAuditvResult.Export.export.HQL

--@JavaScript MigAuditvResult.Export.export.JPQL

--@JavaScript MigAuditvResult.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditvResult.Nest.nest.HQL

--@JavaScript MigAuditvResult.Nest.nest.JPQL
