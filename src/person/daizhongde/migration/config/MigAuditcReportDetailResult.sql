/** a variable, like javascript

--@JavaScript var MigAuditcReportDetailResult = {};
--@JavaScript MigAuditcReportDetailResult.Query = {};
--@JavaScript MigAuditcReportDetailResult.Add = {};
--@JavaScript MigAuditcReportDetailResult.Update = {};
--@JavaScript MigAuditcReportDetailResult.Read = {};
--@JavaScript MigAuditcReportDetailResult.Del = {};

--@JavaScript MigAuditcReportDetailResult.Combobox = {};
--@JavaScript MigAuditcReportDetailResult.Nest = {};
--@JavaScript MigAuditcReportDetailResult.Export = {};

ten MigAuditcReportDetailResult's basic SQL(include HQL), You can see as query0,read0,combobox0,nest0,export0                         
	MigAuditcReportDetailResult.Query.query.SQL        MigAuditcReportDetailResult.Query.query.HQL        MigAuditcReportDetailResult.Query.query.JPQL    
	MigAuditcReportDetailResult.Read.read.SQL          MigAuditcReportDetailResult.Read.read.HQL          MigAuditcReportDetailResult.Read.read.JPQL      
	MigAuditcReportDetailResult.Combobox.combobox.SQL  MigAuditcReportDetailResult.Combobox.combobox.HQL  MigAuditcReportDetailResult.Combobox.combobox.JPQL
	MigAuditcReportDetailResult.Nest.nest.SQL          MigAuditcReportDetailResult.Nest.nest.HQL          MigAuditcReportDetailResult.Nest.nest.JPQL      
	MigAuditcReportDetailResult.Export.export.SQL      MigAuditcReportDetailResult.Export.export.HQL      MigAuditcReportDetailResult.Export.export.JPQL  

note: 
	Don't support back comment 

**/
 
-- CURD sql config file
-- All table need use alias,the target table name's alias is 't1'
-- the target table name is back of the first 'from' key words, 
-- only a space are permit back of the 'from' key words 

/** Effective config begin there   **/
--@JavaScript var MigAuditcReportDetailResult = {};
--@JavaScript MigAuditcReportDetailResult.Query = {};
--@JavaScript MigAuditcReportDetailResult.Add = {};
--@JavaScript MigAuditcReportDetailResult.Update = {};
--@JavaScript MigAuditcReportDetailResult.Read = {};
--@JavaScript MigAuditcReportDetailResult.Del = {};

--@JavaScript MigAuditcReportDetailResult.Combobox = {};
--@JavaScript MigAuditcReportDetailResult.Nest = {};
--@JavaScript MigAuditcReportDetailResult.Export = {};

--@JavaScript MigAuditcReportDetailResult.Query.query.SQL
select t1.id              "id",
       t1.ENTITY          "entity",
       t1.AUDIT_ITEM      "audit_item",
       t1.ENUM_DESC       "enum_desc",
       t1.SPLIT_FLAG      "split_flag",
       t1.SRC_ENUM        "src_enum",
       t1.DST_ENUM        "dst_enum",
       t1.SRC_COUNT       "src_count",
       t1.DST_COUNT       "dst_count",
       t1.AUDIT_AUTHOR    "audit_author",
       t1.FARES_DRYRUN_ID "fares_dryrun_id",
       (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
       left(t1.MIN_ANALYSIS,200)    "min_analysis",
       t1.MIN_PER         "min_per"
  from tool.mig_auditc_report_detail_result t1

/* tableData HQL   */ 
--@JavaScript MigAuditcReportDetailResult.Query.query.HQL

--@JavaScript MigAuditcReportDetailResult.Query.query.JPQL

--@JavaScript MigAuditcReportDetailResult.Query.queryDiff.SQL
select t1.id              "id",
       t1.ENTITY          "entity",
       t1.AUDIT_ITEM      "audit_item",
       t1.ENUM_DESC       "enum_desc",
       t1.SPLIT_FLAG      "split_flag",
       t1.SRC_ENUM        "src_enum",
       t1.DST_ENUM        "dst_enum",
       t1.SRC_COUNT       "src_count",
       t1.DST_COUNT       "dst_count",
       t1.AUDIT_AUTHOR    "audit_author",
       t1.FARES_DRYRUN_ID "fares_dryrun_id",
       (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
       left(t1.MIN_ANALYSIS,200)    "min_analysis",
       t1.MIN_PER         "min_per"
  from tool.mig_auditc_report_detail_result t1
 where t1.SRC_COUNT <> t1.DST_COUNT

--@JavaScript MigAuditcReportDetailResult.Query.queryGroupbysudomain.SQL
select 
--t1.id              "id",
       t1.ENTITY          "entity",
       t1.AUDIT_ITEM      "audit_item",
       --t1.ENUM_DESC       "enum_desc",
       --t1.SPLIT_FLAG      "split_flag",
       --t1.SRC_ENUM        "src_enum",
      -- t1.DST_ENUM        "dst_enum",
       sum(t1.SRC_COUNT)       "src_count",
       sum(t1.DST_COUNT)       "dst_count",
       sum( ABS(t1.SRC_COUNT-t1.DST_COUNT) )       "diff_count",
       --t1.AUDIT_AUTHOR    "audit_author",
       t1.FARES_DRYRUN_ID "fares_dryrun_id",
       (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name"
       --left(t1.MIN_ANALYSIS,200)    "min_analysis",
       --t1.MIN_PER         "min_per"
  from tool.mig_auditc_report_detail_result t1
 group by t1.FARES_DRYRUN_ID,t1.ENTITY,t1.AUDIT_ITEM
 
-- MigAuditcReportDetailResult.Read.read.SQL,Criteria.ALIAS_TO_ENTITY_MAP will convert column name to UpperCase,column alias must different avoid map key cover
--@JavaScript MigAuditcReportDetailResult.Read.read.SQL
select t1.id              "id",
       t1.ENTITY          "entity",
       t1.AUDIT_ITEM      "audit_item",
       t1.ENUM_DESC       "enum_desc",
       t1.SPLIT_FLAG      "split_flag",
       t1.SRC_ENUM        "src_enum",
       t1.DST_ENUM        "dst_enum",
       t1.SRC_COUNT       "src_count",
       t1.DST_COUNT       "dst_count",
       t1.AUDIT_AUTHOR    "audit_author",
       t1.FARES_DRYRUN_ID "fares_dryrun_id",
       (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
       t1.MIN_ANALYSIS    "min_analysis",
       t1.MIN_PER         "min_per"
  from tool.mig_auditc_report_detail_result t1


-- MigAuditcReportDetailResult.Read.read.HQL, hql haven't decode function, also '||' can't explain in hql
--@JavaScript MigAuditcReportDetailResult.Read.read.HQL
-- MigAuditcReportDetailResult.Read.read.hql=select new map(t1.NMid as id, t1.CMname as name, decode(t1.CMleaf, 'true', '\u662F', 'false', '\u5426', t1.CMleaf) as leaf,t1.NMorder as order1,p.CMname as name2, t1.CMpath as path, t1.CMnote as note ) from TMigAuditcReportDetailResult t1 left outer join t1.NMparent p

--@JavaScript MigAuditcReportDetailResult.Read.read.JPQL

-- SQL for select MigAuditcReportDetailResult.Combobox.combobox.data
--@JavaScript MigAuditcReportDetailResult.Combobox.combobox.SQL
select AREA_CD "id", AREA_NM "text" from cpab.TB_Area_cd


-- HQL select MigAuditcReportDetailResult.Combobox.combobox.data
--@JavaScript MigAuditcReportDetailResult.Combobox.combobox.HQL

--@JavaScript MigAuditcReportDetailResult.Combobox.combobox.JPQL

-- MigAuditcReportDetailResult.Export.export.SQL=select t1.N_MID id,t1.C_MNAME name,decode( t1.N_Mlevel,0,'\u96F6\u7EA7',1,'\u4E00\u7EA7',2,'\u4E8C\u7EA7',3,'\u4E09\u7EA7',4,'\u56DB\u7EA7',5,'\u4E94\u7EA7',6,'\u516D\u7EA7', t1.N_Mlevel||'\u7EA7' ) as level1,decode( t1.C_Mleaf,'true','\u662F','false','\u5426', t1.C_Mleaf) as leaf, t1.N_MORDER order1,t2.C_MNAME super,decode( t1.C_MTARGET,'R','\u53F3\u8FB9\u6846\u67B6','B','\u65B0\u7A97\u53E3','T','\u5F53\u524D\u6D4F\u89C8\u5668\u7A97\u53E3','S','\u5F53\u524D\u6846\u67B6', t1.C_MTARGET) as target,t1.C_MICONCLS iconcls,decode( t1.C_MEXPANDED,'true','\u662F','false','\u5426', t1.C_MEXPANDED) as expanded, decode( t1.C_MCHECKED,'true','\u662F','false','\u5426', t1.C_MCHECKED) as checked, t1.C_MPATH path,t1.C_MNOTE note,t1.C_MCTIME ctime,t1.C_MCIP cip,t1.N_MCUSER cuser,t1.C_MMTIME mtime,t1.C_MMIP mip,t1.N_MMUSER muser from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT\=t2.N_MID
--@JavaScript MigAuditcReportDetailResult.Export.export.SQL
SELECT t1.ID,
	    (select value from tool.mig_codedetail_define where type='Busi-Domain' and code=t1.ENTITY) DOMAIN2,
       t1.AUDIT_ITEM,
       t1.ENUM_DESC,
       t1.SPLIT_FLAG,
       t1.SRC_ENUM,
       t1.DST_ENUM,
       t1.SRC_COUNT,
       t1.DST_COUNT,
       t1.AUDIT_AUTHOR,
       t1.FARES_DRYRUN_ID,
       (SELECT MIG_DRYRUN_NAME FROM v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
       t1.MIN_ANALYSIS,
       t1.MIN_PER,
       CASE WHEN t1.SRC_COUNT-t1.DST_COUNT=0 THEN '0.00%' 
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>0 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<0.0001 THEN '<0.01%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>-0.0001 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<0 THEN '>-0.01%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>0.9999 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<1 THEN '>99.99%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>-1 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<-0.9999 THEN '<-99.99%'
       ELSE cast(CONCAT(  CAST( (t1.SRC_COUNT -t1.DST_COUNT) /t1.SRC_COUNT*100 AS DECIMAL(5,2) ) , '%') AS CHAR) END "Diff_Rate",
       CAST( (t1.SRC_COUNT -t1.DST_COUNT) /t1.SRC_COUNT AS DECIMAL(12,10) ) "Diff_Rate2"
  from tool.mig_auditc_report_detail_result t1

--@JavaScript MigAuditcReportDetailResult.Export.export.HQL

--@JavaScript MigAuditcReportDetailResult.Export.export.JPQL

--@JavaScript MigAuditcReportDetailResult.Export.exportDiff.SQL
SELECT t1.ID,
	    (select VALUE from tool.mig_codedetail_define where type='Busi-Domain' and code=t1.ENTITY ) DOMAIN2,
       t1.AUDIT_ITEM,
       t1.ENUM_DESC,
       t1.SPLIT_FLAG,
       t1.SRC_ENUM,
       t1.DST_ENUM,
       t1.SRC_COUNT,
       t1.DST_COUNT,
       t1.AUDIT_AUTHOR,
       t1.FARES_DRYRUN_ID,
       (SELECT MIG_DRYRUN_NAME FROM v_dryrun_config WHERE MIG_DRYRUN_ID=t1.FARES_DRYRUN_ID ) "dryrun_name",
       t1.MIN_ANALYSIS,
       t1.MIN_PER,
       CASE WHEN t1.SRC_COUNT-t1.DST_COUNT=0 THEN '0.00%' 
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>0 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<0.0001 THEN '<0.01%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>-0.0001 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<0 THEN '>-0.01%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>0.9999 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<1 THEN '>99.99%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>-1 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<-0.9999 THEN '<-99.99%'
       ELSE cast(CONCAT(  CAST( (t1.SRC_COUNT -t1.DST_COUNT) /t1.SRC_COUNT*100 AS DECIMAL(5,2) ) , '%') AS CHAR) END "Diff_Rate",
       CAST( (t1.SRC_COUNT -t1.DST_COUNT) /t1.SRC_COUNT AS DECIMAL(12,10) ) "Diff_Rate2"
  from tool.mig_auditc_report_detail_result t1
 WHERE t1.SRC_COUNT <> t1.DST_COUNT
 
--@JavaScript MigAuditcReportDetailResult.Export.exportGroupbysudomain.SQL
select t1.DOMAIN2,AUDIT_ITEM,src_count "src_count",dst_count "dst_count",diff_count "diff_count",dryrun_name "dryrun_name",
FARES_DRYRUN_ID,ENTITY,

CASE WHEN t1.SRC_COUNT-t1.DST_COUNT=0 THEN '0.00%' 
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>0 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<0.0001 THEN '<0.01%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>-0.0001 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<0 THEN '>-0.01%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>0.9999 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<1 THEN '>99.99%'
       WHEN (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT>-1 AND (t1.SRC_COUNT-t1.DST_COUNT)/t1.SRC_COUNT<-0.9999 THEN '<-99.99%'
       ELSE cast(CONCAT(  CAST( (t1.SRC_COUNT -t1.DST_COUNT) /t1.SRC_COUNT*100 AS DECIMAL(5,2) ) , '%') AS CHAR) END "Diff_Rate",
       CAST( (t1.SRC_COUNT -t1.DST_COUNT) /t1.SRC_COUNT AS DECIMAL(12,10) ) "Diff_Rate2"
from (
 select
 t2.ENTITY,
		 (select VALUE from tool.mig_codedetail_define where type='Busi-Domain' and code=t1.ENTITY ) DOMAIN2,
       t2.AUDIT_ITEM,
       sum(t2.SRC_COUNT)       "src_count",
       sum(t2.DST_COUNT)       "dst_count",
       sum( ABS(t2.SRC_COUNT-t2.DST_COUNT) )       "diff_count",
       t2.FARES_DRYRUN_ID,
       (select MIG_DRYRUN_NAME from v_dryrun_config where MIG_DRYRUN_ID=t2.FARES_DRYRUN_ID ) "dryrun_name"
       
  from tool.mig_auditc_report_detail_result t2
 group by t2.FARES_DRYRUN_ID,t2.ENTITY,t2.AUDIT_ITEM
) t1
--@JavaScript MigAuditcReportDetailResult.Nest.nest.SQL
/**select * 
  from t_authority_module t2 
 where t2.name = t1.name**/
 
--@JavaScript MigAuditcReportDetailResult.Nest.nest.HQL

--@JavaScript MigAuditcReportDetailResult.Nest.nest.JPQL
