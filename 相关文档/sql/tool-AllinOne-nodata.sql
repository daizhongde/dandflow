/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.6.14-enterprise-commercial-advanced : Database - tool
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `tool`;

/*Table structure for table `mig_audit_precheck` */

DROP TABLE IF EXISTS `mig_audit_precheck`;

CREATE TABLE `mig_audit_precheck` (
  `AUDIT_ID` int(20) NOT NULL AUTO_INCREMENT,
  `AUDIT_SCHEMA` varchar(21) DEFAULT NULL,
  `AUDIT_ENV` varchar(4) DEFAULT NULL,
  `AUDIT_TYPE` varchar(4) DEFAULT NULL,
  `AUDIT_MODE` varchar(4) DEFAULT NULL,
  `AUDIT_OBJECT` varchar(254) DEFAULT NULL,
  `DRYRUN_FRONT` varchar(12) DEFAULT NULL,
  `DRYRUN_BACK` varchar(12) DEFAULT NULL,
  `COUNT_FRONT` int(11) DEFAULT NULL,
  `COUNT_BACK` int(11) DEFAULT NULL,
  `DIFF_RATIO` int(4) DEFAULT NULL,
  `AUDIT_DATE` datetime DEFAULT NULL,
  `AUDIT_REMARK` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`AUDIT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=105952 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditc_consistency` */

DROP TABLE IF EXISTS `mig_auditc_consistency`;

CREATE TABLE `mig_auditc_consistency` (
  `AUDIT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUDIT_NAME` char(40) NOT NULL,
  `MIG_SQL` text NOT NULL,
  `AUTHOR` char(20) DEFAULT NULL,
  `MIG_SQL_REP` varchar(512) DEFAULT NULL,
  `sql_db` varchar(100) NOT NULL,
  PRIMARY KEY (`AUDIT_ID`),
  UNIQUE KEY `uk_auditvconfig_name` (`AUDIT_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditc_enum_mapping` */

DROP TABLE IF EXISTS `mig_auditc_enum_mapping`;

CREATE TABLE `mig_auditc_enum_mapping` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ENTITY` int(4) NOT NULL,
  `AUDIT_NAME` char(30) DEFAULT NULL,
  `ENUM_DESC` char(30) DEFAULT NULL,
  `SRC_ENUM` char(12) DEFAULT NULL,
  `DST_ENUM` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditc_mid_consistency` */

DROP TABLE IF EXISTS `mig_auditc_mid_consistency`;

CREATE TABLE `mig_auditc_mid_consistency` (
  `AUDIT_ID` int(11) NOT NULL,
  `AUDIT_NAME` char(30) DEFAULT NULL,
  `MIG_SQL` text,
  `AUTHOR` char(20) DEFAULT NULL,
  `MIG_SQL_REP` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`AUDIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditc_report_detail_result` */

DROP TABLE IF EXISTS `mig_auditc_report_detail_result`;

CREATE TABLE `mig_auditc_report_detail_result` (
  `id` int(10) NOT NULL,
  `ENTITY` int(4) NOT NULL,
  `AUDIT_ITEM` char(50) DEFAULT NULL,
  `ENUM_DESC` char(50) DEFAULT NULL,
  `SPLIT_FLAG` char(10) DEFAULT NULL,
  `SRC_ENUM` char(12) DEFAULT NULL,
  `DST_ENUM` char(12) DEFAULT NULL,
  `SRC_COUNT` int(11) DEFAULT NULL,
  `DST_COUNT` int(11) DEFAULT NULL,
  `AUDIT_AUTHOR` char(20) DEFAULT NULL,
  `FARES_DRYRUN_ID` char(20) DEFAULT NULL,
  `MIN_ANALYSIS` varchar(512) DEFAULT NULL,
  `MIN_PER` char(12) DEFAULT NULL,
  `ERR_MSG` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_detail_result` */

DROP TABLE IF EXISTS `mig_auditf_detail_result`;

CREATE TABLE `mig_auditf_detail_result` (
  `FARES_MAIN_ID` varchar(20) DEFAULT NULL,
  `FARES_SERIAL` varchar(14) DEFAULT NULL,
  `FARES_DRYRUN_ID` varchar(20) DEFAULT NULL,
  `FARES_UNPASS_TYPE` int(11) DEFAULT NULL,
  `FARES_BITMAP` varchar(256) NOT NULL,
  `FARES_UNPASS_SRC` varchar(4000) DEFAULT NULL,
  `FARES_UNPASS_DST` varchar(4000) DEFAULT NULL,
  `FARES_CREATETIME` datetime NOT NULL,
  KEY `FK_Reference_14` (`FARES_MAIN_ID`,`FARES_SERIAL`,`FARES_DRYRUN_ID`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`FARES_MAIN_ID`, `FARES_SERIAL`, `FARES_DRYRUN_ID`) REFERENCES `mig_auditf_main_result` (`FARES_MAIN_ID`, `FARES_SERIAL`, `FARES_DRYRUN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_detail_result_his` */

DROP TABLE IF EXISTS `mig_auditf_detail_result_his`;

CREATE TABLE `mig_auditf_detail_result_his` (
  `FARES_MAIN_ID` varchar(20) DEFAULT NULL,
  `FARES_SERIAL` varchar(14) DEFAULT NULL,
  `FARES_DRYRUN_ID` varchar(20) DEFAULT NULL,
  `FARES_UNPASS_TYPE` int(11) NOT NULL,
  `FARES_BITMAP` varchar(256) NOT NULL,
  `FARES_UNPASS_SRC` varchar(4000) DEFAULT NULL,
  `FARES_UNPASS_DST` varchar(4000) DEFAULT NULL,
  `FARES_CREATETIME` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_field_result` */

DROP TABLE IF EXISTS `mig_auditf_field_result`;

CREATE TABLE `mig_auditf_field_result` (
  `MAIN_ID` varchar(20) NOT NULL,
  `DRYRUN_ID` varchar(20) NOT NULL,
  `FARES_SERIAL` varchar(14) NOT NULL,
  `DOMAIN` int(4) NOT NULL,
  `FIELD_INDEX` int(11) NOT NULL,
  `SRC_TABLE_NAME` varchar(128) NOT NULL,
  `DST_TABLE_NAME` varchar(128) NOT NULL,
  `SRC_FIELD_NAME` varchar(64) NOT NULL,
  `DST_FIELD_NAME` varchar(64) NOT NULL,
  `UNMATCH_CNT` int(11) DEFAULT NULL,
  `UNMATCH_RATE` float DEFAULT NULL,
  `CURRENT_CONTENT_RATE` float DEFAULT NULL,
  `CURRENT_RESULT_RATE` float DEFAULT NULL,
  `EFFECT_CONTENT_RATE` float DEFAULT NULL,
  `EFFECT_RESULT_RATE` float DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_main` */

DROP TABLE IF EXISTS `mig_auditf_main`;

CREATE TABLE `mig_auditf_main` (
  `FAUDIT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `domain` int(4) NOT NULL,
  `FAUDIT_NAME` varchar(128) NOT NULL,
  `FAUDIT_SRCTABLE_NAME` varchar(128) NOT NULL,
  `FAUDIT_SRCTABLE_CONN` varchar(1024) NOT NULL,
  `FAUDIT_DSTTABLE_NAME` varchar(128) NOT NULL,
  `FAUDIT_DSTTABLE_CONN` varchar(1024) NOT NULL,
  `Author` varchar(20) NOT NULL,
  `FAUDIT_DESC` varchar(1024) DEFAULT NULL,
  `FAUDIT_CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FAUDIT_ID`),
  UNIQUE KEY `uk_auditf_item` (`FAUDIT_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_main_result` */

DROP TABLE IF EXISTS `mig_auditf_main_result`;

CREATE TABLE `mig_auditf_main_result` (
  `FARES_MAIN_ID` varchar(20) NOT NULL,
  `FARES_DRYRUN_ID` varchar(20) NOT NULL,
  `FARES_SERIAL` varchar(14) NOT NULL,
  `domain` int(4) NOT NULL,
  `FARES_SRC_COUNT` int(11) DEFAULT NULL,
  `FARES_DST_COUNT` int(11) DEFAULT NULL,
  `FARES_SRC_PASSCNT` int(11) DEFAULT NULL,
  `FARES_KEY_PASSCNT` int(11) DEFAULT NULL,
  `FARES_SRC_MORE` int(11) DEFAULT NULL,
  `FARES_DST_MORE` int(11) DEFAULT NULL,
  `FARES_KEY_UNMATCH` int(11) DEFAULT NULL,
  `FARES_ELSE_UNMATCH` int(11) DEFAULT NULL,
  `FARES_CREATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`FARES_MAIN_ID`,`FARES_SERIAL`,`FARES_DRYRUN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_main_result_his` */

DROP TABLE IF EXISTS `mig_auditf_main_result_his`;

CREATE TABLE `mig_auditf_main_result_his` (
  `FARES_MAIN_ID` varchar(20) NOT NULL,
  `FARES_DRYRUN_ID` varchar(20) NOT NULL,
  `FARES_SERIAL` varchar(14) NOT NULL,
  `domain` int(4) unsigned NOT NULL,
  `FARES_SRC_COUNT` int(11) DEFAULT NULL,
  `FARES_DST_COUNT` int(11) DEFAULT NULL,
  `FARES_SRC_PASSCNT` int(11) DEFAULT NULL,
  `FARES_KEY_PASSCNT` int(11) DEFAULT NULL,
  `FARES_SRC_MORE` int(11) DEFAULT NULL,
  `FARES_DST_MORE` int(11) DEFAULT NULL,
  `FARES_KEY_UNMATCH` int(11) DEFAULT NULL,
  `FARES_ELSE_UNMATCH` int(11) DEFAULT NULL,
  `FARES_CREATETIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`FARES_MAIN_ID`,`FARES_SERIAL`,`FARES_DRYRUN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_sub` */

DROP TABLE IF EXISTS `mig_auditf_sub`;

CREATE TABLE `mig_auditf_sub` (
  `FAUDIT_MAIN_ID` varchar(20) NOT NULL,
  `FAUDIT_SUB_INDEX` int(11) NOT NULL,
  `FAUDIT_SRC_FIELD` varchar(64) NOT NULL,
  `FAUDIT_DST_FIELD` varchar(64) NOT NULL,
  `FAUDIT_ISKEY` int(11) NOT NULL DEFAULT '0',
  `FAUDIT_OPT` varchar(128) DEFAULT NULL,
  `FAUDIT_CREATETIME` datetime NOT NULL,
  `FAUDIT_MODIFYTIME` datetime DEFAULT NULL,
  `FAUDIT_STATUS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`FAUDIT_SUB_INDEX`,`FAUDIT_MAIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditf_value_map` */

DROP TABLE IF EXISTS `mig_auditf_value_map`;

CREATE TABLE `mig_auditf_value_map` (
  `FAUDIT_SRCTABLE_NAME` varchar(128) NOT NULL,
  `FAUDIT_SRC_FIELD` varchar(64) NOT NULL,
  `FAUDIT_FIELD_SRCVALUE` varchar(200) NOT NULL,
  `FAUDIT_DSTTABLE_NAME` varchar(128) NOT NULL,
  `FAUDIT_DST_FIELD` varchar(64) NOT NULL,
  `FAUDIT_FIELD_DSTVALUE` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditv_config` */

DROP TABLE IF EXISTS `mig_auditv_config`;

CREATE TABLE `mig_auditv_config` (
  `AUDIT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `domain` int(4) NOT NULL,
  `TABLE_NAME` char(200) DEFAULT NULL,
  `AUDIT_NAME` char(200) NOT NULL,
  `AUDIT_TYPE` char(4) DEFAULT NULL,
  `AUDIT_LEVEL` int(1) NOT NULL DEFAULT '2' COMMENT '1-高级,2-中级,3-低级',
  `SQL_TYPE` char(4) DEFAULT NULL,
  `SRC_AUDIT_SQL` text NOT NULL,
  `DST_AUDIT_SQL` text,
  `AUDIT_VALUE` text NOT NULL,
  `AUDIT_FLAG` char(1) DEFAULT NULL,
  `INVALID_DATA_SQL` text,
  `OPERATOR` char(10) NOT NULL,
  `AUDIT_UNIT` char(20) DEFAULT NULL,
  `AUDIT_AUTHOR` char(20) NOT NULL,
  `REMARK` varchar(1000) DEFAULT NULL,
  `SRC_DB_CONNECT` char(40) NOT NULL DEFAULT '1|@{DIP}|mig123|mig123|test|@{PORT}',
  `DST_DB_CONNECT` char(40) DEFAULT NULL,
  `MIG_SQL_REP` varchar(512) NOT NULL,
  `VERSION` char(10) DEFAULT NULL,
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`AUDIT_ID`),
  UNIQUE KEY `UK_auditvconfig_domainaudititem` (`AUDIT_NAME`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=5000208 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditv_configcolldetail` */

DROP TABLE IF EXISTS `mig_auditv_configcolldetail`;

CREATE TABLE `mig_auditv_configcolldetail` (
  `detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '需要界面配置',
  `main_id` int(11) NOT NULL,
  `audit_id` int(11) NOT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditv_configcollmain` */

DROP TABLE IF EXISTS `mig_auditv_configcollmain`;

CREATE TABLE `mig_auditv_configcollmain` (
  `main_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `remark` varchar(1024) NOT NULL,
  PRIMARY KEY (`main_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditv_configtree` */

DROP TABLE IF EXISTS `mig_auditv_configtree`;

CREATE TABLE `mig_auditv_configtree` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent` varchar(128) DEFAULT NULL COMMENT '父节点',
  `name` varchar(256) NOT NULL COMMENT '名称',
  `content` int(11) DEFAULT NULL,
  `isleaf` smallint(4) NOT NULL DEFAULT '1' COMMENT '1：叶子，0：非叶子',
  `status` smallint(4) NOT NULL DEFAULT '1' COMMENT '1：valid,0:invalid',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14920 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditv_errreason` */

DROP TABLE IF EXISTS `mig_auditv_errreason`;

CREATE TABLE `mig_auditv_errreason` (
  `audit_id` int(8) NOT NULL,
  `dmp_no` char(28) DEFAULT NULL,
  `reason` varchar(1024) DEFAULT NULL,
  `env` char(1) NOT NULL DEFAULT 'C' COMMENT 'C-connextion,R-Retail',
  PRIMARY KEY (`audit_id`,`env`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditv_mid_config` */

DROP TABLE IF EXISTS `mig_auditv_mid_config`;

CREATE TABLE `mig_auditv_mid_config` (
  `AUDIT_ID` int(11) NOT NULL,
  `DOMAIN` int(4) NOT NULL,
  `TABLE_NAME` char(200) DEFAULT NULL,
  `AUDIT_NAME` char(200) DEFAULT NULL,
  `AUDIT_TYPE` char(4) DEFAULT NULL,
  `SQL_TYPE` char(4) DEFAULT NULL,
  `SRC_AUDIT_SQL` text,
  `DST_AUDIT_SQL` text,
  `AUDIT_VALUE` text,
  `AUDIT_FLAG` char(1) DEFAULT NULL,
  `INVALID_DATA_SQL` text,
  `OPERATOR` char(10) DEFAULT NULL,
  `AUDIT_UNIT` char(20) DEFAULT NULL,
  `AUDIT_AUTHOR` char(20) DEFAULT NULL,
  `REMARK` varchar(1000) DEFAULT NULL,
  `SRC_DB_CONNECT` char(40) DEFAULT NULL,
  `DST_DB_CONNECT` char(40) DEFAULT NULL,
  `VERSION` char(10) DEFAULT NULL,
  PRIMARY KEY (`AUDIT_ID`),
  UNIQUE KEY `uk_auditv_mid_config_auditname` (`AUDIT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_auditv_result` */

DROP TABLE IF EXISTS `mig_auditv_result`;

CREATE TABLE `mig_auditv_result` (
  `ID` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `AUDIT_ID` int(11) NOT NULL,
  `FARES_DRYRUN_ID` char(20) DEFAULT NULL,
  `DOMAIN` char(20) DEFAULT NULL,
  `TABLE_NAME` char(200) DEFAULT NULL,
  `AUDIT_NAME` char(200) NOT NULL,
  `SRC_VALUE` text,
  `DST_VALUE` text,
  `MIN_VALUE` text,
  `AUDIT_UNIT` char(20) DEFAULT NULL,
  `INVALID_DATA_TABLE` varchar(100) DEFAULT NULL,
  `RESULT` int(10) DEFAULT '1',
  `AUDIT_AUTHOR` char(20) DEFAULT NULL,
  `HDATE` datetime DEFAULT NULL,
  `REMARK` varchar(1000) DEFAULT NULL,
  `SUCCESS_FLAG` int(11) DEFAULT '1',
  `ERR_MSG` text,
  `INVALID_DATA_CNT` int(11) DEFAULT NULL,
  `ENV` char(1) NOT NULL DEFAULT 'C',
  `src_audit_sql` text,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=199 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_b2b_sync_config` */

DROP TABLE IF EXISTS `mig_b2b_sync_config`;

CREATE TABLE `mig_b2b_sync_config` (
  `MIG_GROUP` int(11) DEFAULT NULL,
  `MIG_TABLE` varchar(128) DEFAULT NULL,
  `MIG_AUTHOR` varchar(20) DEFAULT NULL,
  `MIG_OPTDATE` datetime DEFAULT NULL,
  `MIG_DESC` varchar(128) DEFAULT NULL,
  `MIG_WHERE` varchar(1024) DEFAULT NULL,
  `SRC_DB` varchar(64) DEFAULT NULL,
  `DST_DB` varchar(64) DEFAULT NULL,
  `MIG_TYPE` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_b2b_sync_log` */

DROP TABLE IF EXISTS `mig_b2b_sync_log`;

CREATE TABLE `mig_b2b_sync_log` (
  `DRYRUN_ID` int(4) DEFAULT NULL,
  `DOMAIN_ID` varchar(21) DEFAULT NULL,
  `MIG_TABLE` varchar(128) DEFAULT NULL,
  `BEG_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `OPTDATE` datetime DEFAULT NULL,
  `LOGID` varchar(25) DEFAULT NULL,
  `COUNTS` bigint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_codedetail_define` */

DROP TABLE IF EXISTS `mig_codedetail_define`;

CREATE TABLE `mig_codedetail_define` (
  `id` int(5) NOT NULL,
  `TYPE` varchar(64) NOT NULL COMMENT '参数类型，默认0',
  `code` varchar(20) NOT NULL COMMENT '参数',
  `value` varchar(254) DEFAULT NULL COMMENT '参数值',
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_codedetail_define` (`TYPE`,`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鍏ㄥ眬闈欐€佸弬鏁拌〃';

/*Table structure for table `mig_com_info` */

DROP TABLE IF EXISTS `mig_com_info`;

CREATE TABLE `mig_com_info` (
  `COM_ID` char(30) NOT NULL,
  `PARA_ID` int(11) NOT NULL COMMENT '����ID',
  `PARA_VALUE` text,
  PRIMARY KEY (`COM_ID`,`PARA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����Ϣ��';

/*Table structure for table `mig_com_ins` */

DROP TABLE IF EXISTS `mig_com_ins`;

CREATE TABLE `mig_com_ins` (
  `JOB_INS_ID` char(10) NOT NULL,
  `COM_ID` char(10) NOT NULL COMMENT '���ID',
  `PARA_ID` int(11) NOT NULL COMMENT '����ID',
  `PARA_VALUE` mediumtext COMMENT 'parameter value',
  PRIMARY KEY (`JOB_INS_ID`,`COM_ID`,`PARA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����Ϣ��';

/*Table structure for table `mig_config_connection` */

DROP TABLE IF EXISTS `mig_config_connection`;

CREATE TABLE `mig_config_connection` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `url` varchar(256) NOT NULL COMMENT 'url',
  `remark` varchar(1024) NOT NULL COMMENT 'description',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dbconn_name` (`name`),
  UNIQUE KEY `UK_dbconn_url` (`url`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_control_info` */

DROP TABLE IF EXISTS `mig_control_info`;

CREATE TABLE `mig_control_info` (
  `CONTROL_ID` char(10) NOT NULL COMMENT '�ؼ�ID',
  `CONTROL_NAME` varchar(20) DEFAULT NULL COMMENT '�ؼ����',
  `CONTROL_MARK` varchar(128) DEFAULT NULL COMMENT '�ؼ���ע',
  `icon_cls` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`CONTROL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ؼ���Ϣ��';

/*Table structure for table `mig_control_template` */

DROP TABLE IF EXISTS `mig_control_template`;

CREATE TABLE `mig_control_template` (
  `CONTROL_ID` char(10) NOT NULL COMMENT '�ؼ�ID',
  `PARA_ID` int(11) NOT NULL COMMENT '����ID',
  `PARA_NAME` varchar(20) NOT NULL COMMENT '�������',
  `nullable` int(4) DEFAULT NULL COMMENT '0-不可为空，1-可为空',
  `IS_NUMBER` int(4) DEFAULT NULL COMMENT '0-非数字，1-数字',
  `DEF_VALUE` varchar(128) DEFAULT NULL,
  `input_type` int(4) DEFAULT NULL COMMENT 'text-1  ,textarea-2, combobox-3,date-91, time-92,datetime-93, clob-2005',
  `code_type` varchar(64) DEFAULT NULL,
  `checkrule` varchar(64) NOT NULL DEFAULT '"0"',
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`CONTROL_ID`,`PARA_ID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`CONTROL_ID`) REFERENCES `mig_control_info` (`CONTROL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ؼ�ģ���';

/*Table structure for table `mig_data_precheck` */

DROP TABLE IF EXISTS `mig_data_precheck`;

CREATE TABLE `mig_data_precheck` (
  `CHECK_ID` varchar(21) NOT NULL,
  `DRYRUN_ID` int(4) DEFAULT NULL,
  `CHECK_ENV` varchar(2) DEFAULT NULL,
  `CHECK_TYPE` varchar(2) DEFAULT NULL,
  `CHECK_OBJECT` varchar(254) DEFAULT NULL,
  `CHECK_COUNT` int(11) DEFAULT NULL,
  `CHECK_FIELD` int(4) DEFAULT NULL,
  `CHECK_DATE` datetime DEFAULT NULL,
  `CHECK_REMARK` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`CHECK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_ins_config` */

DROP TABLE IF EXISTS `mig_ins_config`;

CREATE TABLE `mig_ins_config` (
  `MIG_TASK_ID` char(10) NOT NULL,
  `JOB_INS_ID` char(10) NOT NULL,
  `MIG_SRC` varchar(1024) DEFAULT NULL,
  `MIG_SRC_CONN` varchar(128) DEFAULT NULL,
  `MIG_WHERE` varchar(1024) DEFAULT NULL,
  `MIG_DST` varchar(1024) DEFAULT NULL,
  `MIG_DST_CONN` varchar(128) DEFAULT NULL,
  `MIG_AUTHOR` varchar(20) NOT NULL,
  `MIG_DESC` varchar(1024) DEFAULT NULL,
  `MIG_MODIFYTIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `mig_status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_ins_para` */

DROP TABLE IF EXISTS `mig_ins_para`;

CREATE TABLE `mig_ins_para` (
  `JOB_INS_ID` char(10) NOT NULL,
  `node_ID` char(30) NOT NULL,
  `PARA` char(20) NOT NULL COMMENT '����',
  `PARA_NAME` varchar(64) DEFAULT NULL COMMENT '����������',
  `PARA_TYPE` int(3) DEFAULT '1' COMMENT 'parameter type',
  `PARA_VALUE` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`JOB_INS_ID`,`node_ID`,`PARA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҵ�����';

/*Table structure for table `mig_job_content` */

DROP TABLE IF EXISTS `mig_job_content`;

CREATE TABLE `mig_job_content` (
  `JOB_ID` char(10) NOT NULL,
  `node_ID` char(30) NOT NULL,
  `isleaf` int(3) NOT NULL DEFAULT '1' COMMENT '1叶子节点，0非叶子节点',
  `node_STATUS` char(1) DEFAULT NULL COMMENT '����',
  `PREPOS` varchar(256) DEFAULT NULL COMMENT 'ǰ',
  `POSTPOS` varchar(256) DEFAULT NULL,
  `coords` char(9) DEFAULT NULL,
  PRIMARY KEY (`JOB_ID`,`node_ID`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`JOB_ID`) REFERENCES `mig_job_info` (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҵ���ݱ�';

/*Table structure for table `mig_job_info` */

DROP TABLE IF EXISTS `mig_job_info`;

CREATE TABLE `mig_job_info` (
  `JOB_ID` char(10) NOT NULL COMMENT '��ҵID����''J''��ͷ',
  `JOB_NAME` varchar(64) NOT NULL,
  `type` int(4) unsigned DEFAULT '1',
  `JOB_AUTHOR` varchar(64) DEFAULT NULL,
  `JOB_REMARK` varchar(64) DEFAULT NULL,
  `JOB_UPDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`JOB_ID`),
  KEY `UK_jobname` (`JOB_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҵ��Ϣ��';

/*Table structure for table `mig_job_ins` */

DROP TABLE IF EXISTS `mig_job_ins`;

CREATE TABLE `mig_job_ins` (
  `JOB_INS_ID` char(10) NOT NULL,
  `JOB_ID` char(10) NOT NULL COMMENT '��ҵID����''J''��ͷ',
  `dryrun_id` int(11) NOT NULL,
  `JOB_INS_NAME` varchar(64) DEFAULT NULL,
  `type` int(4) unsigned DEFAULT '1',
  `STATUS` char(2) NOT NULL COMMENT 'ins status',
  `MTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modify time',
  `AUTHOR` varchar(64) DEFAULT NULL,
  `REMARK` varchar(20) DEFAULT NULL,
  `LOCK_STATUS` char(1) DEFAULT NULL COMMENT '״̬|��0��δ����1����',
  `running` int(3) DEFAULT '0' COMMENT '运行状态,0-后台无线程，1-有后台线程',
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`JOB_INS_ID`),
  UNIQUE KEY `UK_jobinsname` (`JOB_INS_NAME`),
  KEY `FK_Reference_9` (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҵʵ����Ϣ��';

/*Table structure for table `mig_job_log` */

DROP TABLE IF EXISTS `mig_job_log`;

CREATE TABLE `mig_job_log` (
  `LOG_ID` char(20) NOT NULL COMMENT '��־ID',
  `JOB_INS_ID` char(10) DEFAULT NULL,
  `dryrun_id` int(11) DEFAULT NULL,
  `task_id` char(10) DEFAULT NULL,
  `level` int(11) DEFAULT NULL COMMENT '��־����|log��error��debug',
  `LOG_MSG` varchar(4096) DEFAULT NULL COMMENT '��־��Ϣ',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'create time',
  `REMArk` varchar(128) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`LOG_ID`),
  KEY `NK_Reference_8` (`JOB_INS_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҵ��־��';

/*Table structure for table `mig_job_para` */

DROP TABLE IF EXISTS `mig_job_para`;

CREATE TABLE `mig_job_para` (
  `node_ID` char(30) NOT NULL,
  `PARA` char(20) NOT NULL COMMENT '����',
  `PARA_NAME` varchar(64) DEFAULT NULL COMMENT '����������',
  `PARA_TYPE` int(3) DEFAULT '1' COMMENT 'parameter type',
  `PARA_VALUE` varchar(254) DEFAULT NULL COMMENT '����ֵ',
  PRIMARY KEY (`node_ID`,`PARA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҵ�����';

/*Table structure for table `mig_job_process` */

DROP TABLE IF EXISTS `mig_job_process`;

CREATE TABLE `mig_job_process` (
  `PROCESS_ID` char(10) NOT NULL COMMENT '���ID',
  `JOB_INS_ID` char(10) NOT NULL,
  `JOB_ID` char(10) DEFAULT NULL,
  `node_id` char(30) DEFAULT NULL,
  `isleaf` int(3) DEFAULT '1' COMMENT '0非叶子，1叶子',
  `node_NAME` varchar(64) DEFAULT NULL,
  `node_remark` varchar(64) DEFAULT NULL,
  `CONTROL_ID` char(10) DEFAULT NULL COMMENT '�ؼ�ID',
  `com_id` char(10) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL COMMENT '״̬|��0��δִ�У���1������ִ�У���2��ִ����ɣ���3����ִͣ�У���4�����ִ�У���-1��ִ�г���',
  `author` varchar(24) DEFAULT NULL COMMENT 'author',
  `PREPOS` varchar(256) DEFAULT NULL COMMENT 'ǰ�ù��|process������ǰ��processIDƴ�ӳɵ��ַ�,��:"P001|P002"',
  `POSTPOS` varchar(256) DEFAULT NULL COMMENT '���ù��|process�����к���processIDƴ�ӳɵ��ַ�,��:"P001|P002"',
  `CREATEDATE` datetime DEFAULT NULL COMMENT '����ʱ��',
  `REMARK` varchar(1024) DEFAULT NULL COMMENT '��ע',
  `coords` char(9) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`PROCESS_ID`),
  UNIQUE KEY `UK_insid_nodeid` (`JOB_INS_ID`,`node_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`JOB_INS_ID`) REFERENCES `mig_job_ins` (`JOB_INS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҵ���Ʊ�';

/*Table structure for table `mig_job_stat` */

DROP TABLE IF EXISTS `mig_job_stat`;

CREATE TABLE `mig_job_stat` (
  `LOG_ID` int(11) NOT NULL COMMENT 'id',
  `JOB_ID` char(10) NOT NULL,
  `job_ins_id` char(10) NOT NULL,
  `dryrun_id` int(11) NOT NULL,
  `node_id` char(10) NOT NULL,
  `BEGIN_TIME` datetime DEFAULT NULL COMMENT '��ʼʱ��',
  `END_TIME` datetime DEFAULT NULL COMMENT '����ʱ��',
  `STATUS` int(11) DEFAULT NULL COMMENT '״̬',
  `REMArk` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ͳ�Ʊ�';

/*Table structure for table `mig_sit_sync_config` */

DROP TABLE IF EXISTS `mig_sit_sync_config`;

CREATE TABLE `mig_sit_sync_config` (
  `MIG_GROUP` int(11) DEFAULT NULL,
  `MIG_TABLE` varchar(128) DEFAULT NULL,
  `MIG_AUTHOR` varchar(20) DEFAULT NULL,
  `MIG_OPTDATE` datetime DEFAULT NULL,
  `MIG_DESC` varchar(128) DEFAULT NULL,
  `MIG_WHERE` varchar(1024) DEFAULT NULL,
  `SRC_DB` varchar(64) DEFAULT NULL,
  `DST_DB` varchar(64) DEFAULT NULL,
  `MIG_TYPE` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_sit_sync_log` */

DROP TABLE IF EXISTS `mig_sit_sync_log`;

CREATE TABLE `mig_sit_sync_log` (
  `DRYRUN_ID` int(4) NOT NULL,
  `DRYRUN_ENV` varchar(4) NOT NULL,
  `DOMAIN_ID` varchar(21) NOT NULL,
  `MIG_TABLE` varchar(128) NOT NULL,
  `BEG_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `OPTDATE` datetime DEFAULT NULL,
  `LOGID` varchar(25) DEFAULT NULL,
  `counts` bigint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_sync_config` */

DROP TABLE IF EXISTS `mig_sync_config`;

CREATE TABLE `mig_sync_config` (
  `CONFIG_ID` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `MIG_GROUP` int(4) NOT NULL COMMENT '分组',
  `MIG_TABLE` varchar(30) NOT NULL COMMENT '表名',
  `MIG_AUTHOR` varchar(20) NOT NULL COMMENT '作者',
  `SRC_TYPE` int(4) NOT NULL DEFAULT '1' COMMENT '源数据库类型',
  `SRC_IP` varchar(20) NOT NULL COMMENT '源数据库IP',
  `SRC_PORT` int(5) NOT NULL COMMENT '源数据库端口',
  `SRC_SCHEMA` varchar(30) NOT NULL COMMENT '源数据库Schema',
  `SRC_USER` varchar(20) DEFAULT NULL COMMENT '源数据库用户名',
  `SRC_PASSWORD` varchar(40) DEFAULT NULL COMMENT '源数据库密码',
  `DST_TYPE` int(4) NOT NULL DEFAULT '1' COMMENT '目标数据库类型',
  `DST_IP` varchar(20) NOT NULL COMMENT '目标数据库IP',
  `DST_PORT` int(5) NOT NULL COMMENT '目标数据端口',
  `DST_SCHEMA` varchar(30) NOT NULL COMMENT '目标数据库Schema',
  `DST_USER` varchar(20) DEFAULT NULL COMMENT '目标数据库用户名',
  `DST_PASSWORD` varchar(40) DEFAULT NULL COMMENT '目标数据库密码',
  `MIG_WHERE` varchar(1024) DEFAULT NULL COMMENT '过滤条件，如：where domain=4 and num=45',
  `MIG_MODE` int(4) NOT NULL DEFAULT '5' COMMENT '1-add: append to target table;2-update: update row the same as src in dst;3-add or update: update if exists in dst or else add it;4-delete: delete rows in dst the same as src;5-copy, delete all rows in dst and import from src;6-drop, drop table and create',
  `MIG_DESC` varchar(128) DEFAULT NULL COMMENT 'remark',
  `MTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'modify time',
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='迁移同步表清单';

/*Table structure for table `mig_sync_log` */

DROP TABLE IF EXISTS `mig_sync_log`;

CREATE TABLE `mig_sync_log` (
  `LOG_ID` int(8) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `MIG_GROUP` int(4) NOT NULL COMMENT '分组',
  `MIG_TABLE` varchar(30) NOT NULL COMMENT '表名',
  `MIG_AUTHOR` varchar(20) NOT NULL COMMENT '作者',
  `SRC_TYPE` int(4) NOT NULL DEFAULT '1' COMMENT '源数据库类型',
  `SRC_IP` varchar(20) NOT NULL COMMENT '源数据库IP',
  `SRC_PORT` int(5) NOT NULL COMMENT '源数据库端口',
  `SRC_SCHEMA` varchar(30) NOT NULL COMMENT '源数据库Schema',
  `SRC_USER` varchar(20) NOT NULL COMMENT '源数据库用户名',
  `SRC_PASSWORD` varchar(40) NOT NULL COMMENT '源数据库密码',
  `DST_TYPE` int(4) NOT NULL DEFAULT '1' COMMENT '目标数据库类型',
  `DST_IP` varchar(20) NOT NULL COMMENT '目标数据库IP',
  `DST_PORT` int(5) NOT NULL COMMENT '目标数据端口',
  `DST_SCHEMA` varchar(30) NOT NULL COMMENT '目标数据库Schema',
  `DST_USER` varchar(20) NOT NULL COMMENT '目标数据库用户名',
  `DST_PASSWORD` varchar(40) NOT NULL COMMENT '目标数据库密码',
  `MIG_WHERE` varchar(1024) DEFAULT NULL COMMENT '过滤条件，如：where domain=4 and num=45',
  `MIG_MODE` int(4) NOT NULL DEFAULT '5' COMMENT '1-add: append to target table;2-update: update row the same as src in dst;3-add or update: update if exists in dst or else add it;4-delete: delete rows in dst the same as src;5-copy, delete all rows in dst and import from src;6-drop, drop table and create',
  `MIG_DESC` varchar(128) DEFAULT NULL COMMENT 'remark',
  `MTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'modify time',
  `BATCH_NO` int(8) NOT NULL COMMENT '批次号',
  `DRYRUN_ID` int(4) DEFAULT NULL COMMENT 'DRYRUN_ID',
  `DRYRUN_ENV` varchar(6) DEFAULT NULL COMMENT 'DRYRUN环境',
  `BEG_TIME` datetime DEFAULT NULL COMMENT '开始时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '结束时间',
  `STATUS` int(4) NOT NULL COMMENT '状态|1：初始，2：等待执行，3：执行中，4：执行成功，-1：报错',
  `SRC_COUNT` bigint(11) DEFAULT NULL COMMENT '源表数据量',
  `DST_COUNT1` bigint(11) DEFAULT NULL COMMENT '迁移前目标表数据量',
  `DST_COUNT2` bigint(11) DEFAULT NULL COMMENT '迁移后目标表数据量',
  `DST_ADD` bigint(11) DEFAULT NULL COMMENT '目标表增加的数量',
  `DST_UPDATE` bigint(11) DEFAULT NULL COMMENT '目标表被更新的数量',
  `DST_DELETE` bigint(11) DEFAULT NULL COMMENT '目标表被删除数量',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_task_config` */

DROP TABLE IF EXISTS `mig_task_config`;

CREATE TABLE `mig_task_config` (
  `MIG_config_id` int(8) NOT NULL AUTO_INCREMENT,
  `MIG_config_type` int(4) NOT NULL,
  `domain` int(4) NOT NULL,
  `MIG_SRC` varchar(128) NOT NULL,
  `MIG_SRC_CONN` varchar(128) DEFAULT NULL,
  `MIG_WHERE` varchar(1024) DEFAULT NULL,
  `MIG_DST` varchar(1024) DEFAULT NULL,
  `MIG_DST_CONN` varchar(128) DEFAULT NULL,
  `MIG_AUTHOR` varchar(20) NOT NULL,
  `MIG_DESC` varchar(1024) DEFAULT NULL,
  `MIG_MODIFYTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mig_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MIG_config_id`),
  UNIQUE KEY `UK_taskconfig_type_domain_src` (`MIG_config_type`,`domain`,`MIG_SRC`)
) ENGINE=InnoDB AUTO_INCREMENT=961 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_task_configmsrel` */

DROP TABLE IF EXISTS `mig_task_configmsrel`;

CREATE TABLE `mig_task_configmsrel` (
  `detail_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '需要界面配置',
  `main_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL,
  PRIMARY KEY (`detail_id`),
  UNIQUE KEY `UK_task_configmsrel` (`main_id`,`sub_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `mig_task_configpkg` */

DROP TABLE IF EXISTS `mig_task_configpkg`;

CREATE TABLE `mig_task_configpkg` (
  `main_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `remark` varchar(1024) NOT NULL,
  `type` int(4) DEFAULT NULL,
  PRIMARY KEY (`main_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `mig_task_errinfo` */

DROP TABLE IF EXISTS `mig_task_errinfo`;

CREATE TABLE `mig_task_errinfo` (
  `TABLE_NAME` varchar(128) DEFAULT NULL,
  `ERRNUM` int(11) DEFAULT NULL,
  `ERRFIELD` varchar(20) DEFAULT NULL,
  `ERRCODE` varchar(20) DEFAULT NULL,
  `ERRDESC` varchar(256) DEFAULT NULL,
  `OPTDATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mig_task_info` */

DROP TABLE IF EXISTS `mig_task_info`;

CREATE TABLE `mig_task_info` (
  `TASK_ID` char(30) NOT NULL,
  `TASK_NAME` varchar(64) DEFAULT NULL,
  `TASK_AUTHOR` varchar(64) DEFAULT NULL,
  `TASK_REMARK` varchar(64) DEFAULT NULL,
  `TASK_UPDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CONTROL_ID` char(10) DEFAULT NULL COMMENT '�ؼ�ID',
  `com_id` char(10) DEFAULT NULL,
  PRIMARY KEY (`TASK_ID`),
  KEY `FK_Reference_11` (`CONTROL_ID`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`CONTROL_ID`) REFERENCES `mig_control_info` (`CONTROL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������Ϣ��';

/*Table structure for table `mig_task_log` */

DROP TABLE IF EXISTS `mig_task_log`;

CREATE TABLE `mig_task_log` (
  `MIG_TABLE` varchar(128) DEFAULT NULL,
  `BEG_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `OPTDATE` datetime DEFAULT NULL,
  `LOGID` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_asiainfo_employee` */

DROP TABLE IF EXISTS `t_asiainfo_employee`;

CREATE TABLE `t_asiainfo_employee` (
  `sbu_id` char(3) DEFAULT NULL,
  `sbu` varchar(100) DEFAULT NULL,
  `company_id` char(3) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `organization_id` int(5) DEFAULT NULL,
  `org_name` varchar(100) DEFAULT NULL,
  `office` varchar(100) DEFAULT NULL,
  `pager` varchar(100) DEFAULT NULL,
  `person_id` int(8) NOT NULL,
  `employee_number` varchar(10) NOT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `full_name` varchar(60) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `assignment_id` int(8) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `class` varchar(200) DEFAULT NULL,
  `working_location` varchar(100) DEFAULT NULL,
  `seat_no` varchar(20) DEFAULT NULL,
  `mobile` varchar(16) DEFAULT NULL,
  `nt_account` varchar(100) DEFAULT NULL,
  `supervisor_id` int(8) DEFAULT NULL,
  `supervisor_name` varchar(60) DEFAULT NULL,
  `highest_degree` varchar(100) DEFAULT NULL,
  `hire_date` datetime DEFAULT NULL,
  PRIMARY KEY (`employee_number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `t_authority_button` */

DROP TABLE IF EXISTS `t_authority_button`;

CREATE TABLE `t_authority_button` (
  `n_bid` int(8) NOT NULL AUTO_INCREMENT COMMENT '按钮ID',
  `c_bcode` varchar(30) NOT NULL COMMENT '按钮代码|目前填功能代码。可能用于页面js变量名',
  `c_bname` varchar(30) NOT NULL COMMENT '按钮名称',
  `url` varchar(180) NOT NULL COMMENT '请求路径',
  `remark` varchar(1024) DEFAULT NULL COMMENT '备注',
  `n_mid` int(8) NOT NULL COMMENT '模块ID',
  PRIMARY KEY (`n_bid`),
  UNIQUE KEY `UK_authortiy_button` (`n_mid`,`c_bcode`)
) ENGINE=MyISAM AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;

/*Table structure for table `t_authority_func` */

DROP TABLE IF EXISTS `t_authority_func`;

CREATE TABLE `t_authority_func` (
  `n_fid` smallint(4) NOT NULL COMMENT '功能ID',
  `c_fcode` varchar(30) NOT NULL COMMENT '功能代码',
  `c_fname` varchar(30) NOT NULL COMMENT '功能名称',
  `C_CTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `C_CIP` varchar(180) DEFAULT NULL,
  `C_CREATOR` varchar(72) DEFAULT NULL,
  PRIMARY KEY (`n_fid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `t_authority_inst` */

DROP TABLE IF EXISTS `t_authority_inst`;

CREATE TABLE `t_authority_inst` (
  `N_IID` int(8) NOT NULL AUTO_INCREMENT COMMENT '机构ID',
  `C_ICODE` varchar(9) NOT NULL COMMENT '机构编码',
  `C_INAME` varchar(120) NOT NULL COMMENT '机构名称',
  `N_ILEVEL` decimal(3,0) NOT NULL COMMENT '机构级别|0零级根结点(唯一)，1一级，2二级，3三级...',
  `C_ILEAF` varchar(5) NOT NULL COMMENT '是否末级|default ''true''',
  `N_ITYPE` decimal(3,0) NOT NULL COMMENT '机构类型|1:省公司,2:市公司,3:省处理中心,4:站点,5:揽投部,6:县营业部',
  `N_ISUPERIOR` decimal(8,0) DEFAULT NULL COMMENT '上级机构|0级机构的上级机构是空',
  `C_IMANAGER` varchar(30) DEFAULT NULL COMMENT '机构领导',
  `C_IMTEL` varchar(20) DEFAULT NULL COMMENT '领导电话',
  `C_IMEMAIL` varchar(30) DEFAULT NULL COMMENT '领导邮箱',
  `C_IMQQ` varchar(10) DEFAULT NULL COMMENT '领导QQ',
  `C_ILINKMAN` varchar(30) DEFAULT NULL COMMENT '机构联系人',
  `C_ILTEL` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `C_ILEMAIL` varchar(30) DEFAULT NULL COMMENT '联系人邮箱',
  `C_ILQQ` varchar(10) DEFAULT NULL COMMENT '联系人QQ',
  `C_IADDRESS` varchar(120) DEFAULT NULL COMMENT '机构地址',
  `C_IDESCRIPTION` text COMMENT '机构描述',
  `C_ICTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `C_ICIP` varchar(60) DEFAULT NULL COMMENT '创建者IP',
  `C_ICREATOR` varchar(24) DEFAULT NULL COMMENT '创建者',
  `C_IMTIME` datetime DEFAULT NULL COMMENT '最后修改时间',
  `C_IMIP` varchar(60) DEFAULT NULL COMMENT '最后修改者IP',
  `C_IMODIFIER` varchar(24) DEFAULT NULL COMMENT '最后修改者',
  `C_IONS` char(1) DEFAULT NULL COMMENT '启用状态|(0:未启用;1:启用;)',
  `C_IONT` datetime DEFAULT NULL COMMENT '启用时间',
  PRIMARY KEY (`N_IID`),
  KEY `N_ISUPERIOR` (`N_ISUPERIOR`)
) ENGINE=MyISAM AUTO_INCREMENT=9301838 DEFAULT CHARSET=utf8;

/*Table structure for table `t_authority_level` */

DROP TABLE IF EXISTS `t_authority_level`;

CREATE TABLE `t_authority_level` (
  `N_LID` decimal(4,0) NOT NULL COMMENT '级别',
  `C_LNAME` varchar(90) NOT NULL COMMENT '级别名称',
  `C_LNOTE` text COMMENT '备注',
  PRIMARY KEY (`N_LID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='权限级别表';

/*Table structure for table `t_authority_mbrelation` */

DROP TABLE IF EXISTS `t_authority_mbrelation`;

CREATE TABLE `t_authority_mbrelation` (
  `n_mid` int(8) NOT NULL,
  `n_bid` int(8) NOT NULL,
  `C_CTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `C_CIP` varchar(60) DEFAULT NULL,
  `C_CREATOR` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`n_mid`,`n_bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_authority_module` */

DROP TABLE IF EXISTS `t_authority_module`;

CREATE TABLE `t_authority_module` (
  `N_MID` int(8) NOT NULL AUTO_INCREMENT COMMENT '模块ID',
  `C_MNAME` varchar(90) NOT NULL COMMENT '模块名称',
  `N_MLEVEL` decimal(4,0) NOT NULL COMMENT '模块级别|0零级根结点(唯一)，1一级菜单，2二级菜单，3三级菜单...',
  `C_MLEAF` varchar(5) NOT NULL DEFAULT 'true' COMMENT '模块类型|true:叶子节点 is leaf,false:目录is not leaf,directory',
  `N_MPARENT` decimal(8,0) DEFAULT NULL COMMENT '上级模块|0级模块的上级模块为空',
  `N_MORDER` decimal(4,0) NOT NULL COMMENT '模块次序号',
  `C_MTARGET` varchar(3) NOT NULL DEFAULT 'R' COMMENT '模块链接目标|''R'':right frame(‘basefrm’);''B'':new window;''T'':current browser window;''S'':current frame.',
  `C_MICONCLS` varchar(30) DEFAULT NULL COMMENT '模块图标样式',
  `C_MEXPANDED` varchar(5) NOT NULL DEFAULT 'true' COMMENT '模块结点展开状态',
  `C_MCHECKED` varchar(5) NOT NULL DEFAULT 'false' COMMENT '模块结点是否被选定',
  `C_MPATH` varchar(180) DEFAULT NULL COMMENT '模块url路径',
  `C_MNOTE` text COMMENT '备注',
  `C_MCTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '模块创建时间',
  `C_MCIP` varchar(60) DEFAULT NULL COMMENT '模块创建者创建时所使用的IP',
  `C_MCREATOR` varchar(24) DEFAULT NULL COMMENT '模块创建者',
  `C_MMTIME` datetime DEFAULT NULL COMMENT '模块修改时间,M 触发器自动写入',
  `C_MMIP` varchar(60) DEFAULT NULL COMMENT '模块个最后修改者使用的IP',
  `C_MMODIFIER` varchar(24) DEFAULT NULL COMMENT '模块最后一次的个修改者',
  `C_MONS` char(1) NOT NULL DEFAULT '1' COMMENT '启用状态|(0:未启用;1:启用;)',
  `C_MONT` datetime DEFAULT NULL COMMENT '启用时间',
  PRIMARY KEY (`N_MID`),
  UNIQUE KEY `UK_AUTHORITY_MODULE_NAME` (`C_MNAME`),
  KEY `N_MPARENT` (`N_MPARENT`)
) ENGINE=MyISAM AUTO_INCREMENT=10088 DEFAULT CHARSET=utf8 COMMENT='模块信息表';

/*Table structure for table `t_authority_rbrelation` */

DROP TABLE IF EXISTS `t_authority_rbrelation`;

CREATE TABLE `t_authority_rbrelation` (
  `N_RID` int(11) NOT NULL COMMENT '角色ID',
  `N_BID` int(11) NOT NULL COMMENT '按钮ID',
  `C_CTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '挂接时间',
  `C_CIP` varchar(60) DEFAULT NULL COMMENT '挂接者所使用的IP',
  `C_CREATOR` varchar(24) DEFAULT NULL COMMENT '挂接者',
  PRIMARY KEY (`N_RID`,`N_BID`),
  KEY `FK_ButtonID` (`N_BID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `t_authority_rmrelation` */

DROP TABLE IF EXISTS `t_authority_rmrelation`;

CREATE TABLE `t_authority_rmrelation` (
  `N_RID` decimal(3,0) NOT NULL COMMENT '角色ID',
  `N_MID` decimal(8,0) NOT NULL COMMENT '模块ID',
  `C_CTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '挂接时间',
  `C_CIP` varchar(60) DEFAULT NULL COMMENT '挂接者所使用的IP',
  `C_CREATOR` varchar(24) DEFAULT NULL COMMENT '挂接者',
  `create_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '新增按钮',
  `update_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '修改按钮',
  `update_btn1` char(1) NOT NULL DEFAULT 'N' COMMENT '修改按钮1',
  `read_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '查看按钮',
  `delete_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '删除按钮',
  `delete_btn1` char(1) NOT NULL DEFAULT 'N' COMMENT '删除按钮1',
  `delete_btn2` char(1) NOT NULL DEFAULT 'N' COMMENT '删除按钮2',
  `import_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '导入按钮',
  `import_wizardbtn` char(1) NOT NULL DEFAULT 'N' COMMENT '导入向导按钮',
  `search_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '查询按钮',
  `export_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '导出按钮',
  `export_wizardbtn` char(1) NOT NULL DEFAULT 'N' COMMENT '导出向导按钮',
  `print_btn` char(1) NOT NULL DEFAULT 'N' COMMENT '打印按钮',
  `print_previewbtn` char(1) NOT NULL DEFAULT 'N' COMMENT '打印预览按钮',
  `btn15` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮15',
  `btn16` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮16',
  `btn17` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮17',
  `btn18` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮18',
  `btn19` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮19',
  `btn20` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮20',
  `btn21` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮21',
  `btn22` char(1) NOT NULL DEFAULT 'N' COMMENT '按钮22',
  PRIMARY KEY (`N_RID`,`N_MID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色模块关系表';

/*Table structure for table `t_authority_role` */

DROP TABLE IF EXISTS `t_authority_role`;

CREATE TABLE `t_authority_role` (
  `N_RID` int(3) NOT NULL AUTO_INCREMENT COMMENT '角色ID|PK',
  `C_RNO` char(3) NOT NULL COMMENT '角色编号|UK',
  `C_RNAME` varchar(60) NOT NULL COMMENT '角色名称',
  `N_RLEVEL` decimal(3,0) NOT NULL COMMENT '角色级别(树用上级模块)',
  `C_RNOTE` text COMMENT '注备',
  `C_RCTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `C_RCIP` varchar(60) DEFAULT NULL COMMENT '创建者IP',
  `C_RCREATOR` varchar(24) DEFAULT NULL COMMENT '创建者',
  `C_RONS` char(1) NOT NULL DEFAULT '1' COMMENT '启用状态|(0:未启用;1:启用;)',
  `C_RONT` datetime DEFAULT NULL COMMENT '启用时间',
  PRIMARY KEY (`N_RID`),
  UNIQUE KEY `UK_rolename` (`C_RNAME`),
  KEY `N_RLEVEL` (`N_RLEVEL`)
) ENGINE=MyISAM AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Table structure for table `t_authority_urrelation` */

DROP TABLE IF EXISTS `t_authority_urrelation`;

CREATE TABLE `t_authority_urrelation` (
  `N_UID` decimal(8,0) NOT NULL COMMENT '用户ID',
  `N_RID` decimal(3,0) NOT NULL COMMENT '角色ID',
  `C_CTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '挂接时间',
  `C_CIP` varchar(60) DEFAULT NULL COMMENT '挂接者所使用的IP',
  `C_CREATOR` varchar(24) DEFAULT NULL COMMENT '挂接者所使用的IP',
  PRIMARY KEY (`N_UID`,`N_RID`),
  KEY `N_RID` (`N_RID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

/*Table structure for table `t_authority_user` */

DROP TABLE IF EXISTS `t_authority_user`;

CREATE TABLE `t_authority_user` (
  `N_UID` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `C_ULOGNAME` varchar(24) NOT NULL COMMENT '用户登陆名',
  `C_UNAME` varchar(90) NOT NULL COMMENT '用户姓名',
  `C_UPASSWORD` varchar(40) NOT NULL COMMENT '密码',
  `C_USEX` char(6) NOT NULL COMMENT '性别',
  `employee_number` varchar(10) NOT NULL COMMENT '员工号',
  `supervisor_id` int(8) DEFAULT NULL COMMENT '上司ID',
  `C_UPHONE` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `C_UTEL` varchar(15) DEFAULT NULL COMMENT '办公号码',
  `C_UFAX` varchar(15) DEFAULT NULL COMMENT '传真号码',
  `C_UEMAIL` varchar(40) DEFAULT NULL COMMENT '电子邮箱',
  `C_UQQ` varchar(10) DEFAULT NULL COMMENT 'QQ',
  `C_UADDR` varchar(120) DEFAULT NULL COMMENT '用户地址',
  `C_UNOTE` text COMMENT '注备',
  `N_IID` decimal(9,0) DEFAULT NULL COMMENT '机构ID',
  `N_DID` decimal(9,0) DEFAULT NULL COMMENT '部门ID',
  `C_UCTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `C_UCIP` varchar(60) DEFAULT NULL COMMENT '用户创建者创建时所用IP',
  `C_UCREATOR` varchar(24) DEFAULT NULL COMMENT '用户创建者',
  `C_UMTIME` datetime DEFAULT NULL COMMENT '最后更新时间',
  `C_UMIP` varchar(60) DEFAULT NULL COMMENT '最后修改者IP',
  `C_UMODIFIER` varchar(24) DEFAULT NULL COMMENT '最后修改信息改者',
  `C_UONS` char(1) NOT NULL DEFAULT '1' COMMENT '启用状态|(0:未启用;1:启用;)',
  `C_UONT` datetime DEFAULT NULL COMMENT '启用时间',
  `C_LOGINS` char(1) NOT NULL DEFAULT '0' COMMENT '登陆状态|''0''离线;''1''在线',
  `C_LOGINIP` varchar(60) DEFAULT NULL COMMENT '最后一次登陆IP',
  `C_LOGINT` datetime DEFAULT NULL COMMENT '最后一次登陆时间',
  `C_LOGOUTT` datetime DEFAULT NULL COMMENT '最后一次退出时间',
  PRIMARY KEY (`N_UID`),
  UNIQUE KEY `UK_userlogname` (`C_ULOGNAME`),
  UNIQUE KEY `UK_employee_number` (`employee_number`),
  KEY `NK_supervisor_id` (`supervisor_id`) USING HASH
) ENGINE=MyISAM AUTO_INCREMENT=199755 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Table structure for table `t_chat_msg` */

DROP TABLE IF EXISTS `t_chat_msg`;

CREATE TABLE `t_chat_msg` (
  `N_MID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `N_UID` int(8) NOT NULL,
  `C_MSG` varchar(3072) NOT NULL,
  `D_MSTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`N_MID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `t_pub_seqtable` */

DROP TABLE IF EXISTS `t_pub_seqtable`;

CREATE TABLE `t_pub_seqtable` (
  `seq_name` varchar(30) NOT NULL COMMENT '序列名(名表)',
  `seq_value` int(10) NOT NULL DEFAULT '1' COMMENT '当前值',
  `seq_time` varchar(10) DEFAULT NULL COMMENT '日期',
  `prefix` char(2) DEFAULT NULL COMMENT '前缀',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列表,表名要小写';

/* Function  structure for function  `f_getChildInstList` */

/*!50003 DROP FUNCTION IF EXISTS `f_getChildInstList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getChildInstList`(`rootId` INT) RETURNS varchar(1000) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(1000);
  DECLARE sTempChd VARCHAR(1000);
  SET sTemp = '$';
  SET sTempChd =CAST(rootId AS CHAR);
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = CONCAT(sTemp,',',sTempChd);
		SELECT GROUP_CONCAT(N_Iid) INTO sTempChd FROM t_authority_inst WHERE FIND_IN_SET(N_ISuperior,sTempChd)>0;
	END WHILE;
	RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getChildModuleList` */

/*!50003 DROP FUNCTION IF EXISTS `f_getChildModuleList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getChildModuleList`(`rootId` int) RETURNS varchar(1000) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(1000);
  DECLARE sTempChd VARCHAR(1000);
  SET sTemp = '$';
  SET sTempChd =cast(rootId as CHAR);
	WHILE sTempChd is not null DO
		SET sTemp = concat(sTemp,',',sTempChd);
		SELECT group_concat(N_Mid) INTO sTempChd FROM t_authority_module where FIND_IN_SET(N_Mparent,sTempChd)>0;
	END WHILE;
	RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getChildUserList` */

/*!50003 DROP FUNCTION IF EXISTS `f_getChildUserList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getChildUserList`(`rootId` INT) RETURNS varchar(1000) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(1000);
  DECLARE sTempChd VARCHAR(1000);
  SET sTemp = '$';
  SET sTempChd =CAST(rootId AS CHAR);
	WHILE sTempChd IS NOT NULL DO
		SET sTemp = CONCAT(sTemp,',',sTempChd);
		SELECT GROUP_CONCAT(N_Uid) INTO sTempChd FROM t_authority_user WHERE FIND_IN_SET(supervisor_id,sTempChd)>0;
	END WHILE;
	RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getInstCodeById` */

/*!50003 DROP FUNCTION IF EXISTS `f_getInstCodeById` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getInstCodeById`(`id` INT) RETURNS varchar(100) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(100);
  SET sTemp = '';
  SELECT C_ICODE INTO sTemp FROM t_authority_inst WHERE n_iid= id;
  RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getInstNameById` */

/*!50003 DROP FUNCTION IF EXISTS `f_getInstNameById` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getInstNameById`(`id` INT) RETURNS varchar(100) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(100);
  SET sTemp = '';
  SELECT C_INAME INTO sTemp FROM t_authority_inst WHERE n_iid= id;
  RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getModuleIdListByUserId` */

/*!50003 DROP FUNCTION IF EXISTS `f_getModuleIdListByUserId` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getModuleIdListByUserId`(`userId` int) RETURNS varchar(3000) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(3000);
  DECLARE sTempSup VARCHAR(3000);
DECLARE done INT DEFAULT FALSE;
declare nmid int(10) default -1;
declare cur1 CURSOR FOR
select distinct
n_mid
  from tool.t_authority_module t1
 where t1.N_Mid
    in
     (
      select a.n_mid
        from tool.t_authority_module a
       where a.n_mid 
          in
           (
            select distinct b.n_mid
              from tool.t_authority_rmrelation b
            where b.n_rid in
            (
                select c.n_rid from tool.t_authority_urrelation c where c.n_uid = userId
            )
           )
     );
#declare CONTINUE HANDLER FOR SQLSTATE '02000' SET nmid = -1;
declare CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
SET sTemp = '$';
OPEN cur1;
FETCH cur1 INTO nmid;
WHILE(!done)
	DO	
		SET sTempSup =cast(nmid as CHAR);
		SET sTemp = concat(sTemp,',',sTempSup);
		#SELECT group_concat(N_Mparent) INTO sTempSup FROM t_authority_module where FIND_IN_SET(N_Mid,sTempSup)>0;
		SELECT group_concat(N_Mparent) INTO sTempSup FROM t_authority_module where N_Mid=sTempSup;
	
		WHILE sTempSup is not null 
		DO
			IF !FIND_IN_SET(sTempSup,sTemp) THEN
			  SET sTemp = concat(sTemp,',',sTempSup);
      END IF;
			#SELECT group_concat(N_Mparent) INTO sTempSup FROM t_authority_module where FIND_IN_SET(N_Mid,sTempSup)>0;
			SELECT group_concat(N_Mparent) INTO sTempSup FROM t_authority_module where N_Mid=sTempSup;
		END WHILE;
  FETCH cur1 INTO nmid;
END WHILE;
CLOSE cur1;
RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getModuleNameById` */

/*!50003 DROP FUNCTION IF EXISTS `f_getModuleNameById` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getModuleNameById`(`id` INT) RETURNS varchar(100) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(100);
  SET sTemp = '';
  SELECT C_MNAME INTO sTemp FROM t_authority_module WHERE n_mid= id;
  RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getPInstIdById` */

/*!50003 DROP FUNCTION IF EXISTS `f_getPInstIdById` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getPInstIdById`(`id` INT) RETURNS int(11)
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp int(11);
  SET sTemp = null;
  SELECT N_ISUPERIOR INTO sTemp FROM t_authority_inst WHERE n_iid= id;
  RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getPModuleList` */

/*!50003 DROP FUNCTION IF EXISTS `f_getPModuleList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getPModuleList`(`subNodeId` INT) RETURNS varchar(1000) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(1000);
  DECLARE sTempSup VARCHAR(1000);
  SET sTemp = '$';
  SET sTempSup =CAST(subNodeId AS CHAR);
	WHILE sTempSup IS NOT NULL DO
		SET sTemp = CONCAT(sTemp,',',sTempSup);
		#SELECT group_concat(N_Mparent) INTO sTempSup FROM t_authority_module where FIND_IN_SET(N_Mid,sTempSup)>0;
    SELECT GROUP_CONCAT(N_Mparent) INTO sTempSup FROM t_authority_module WHERE N_Mid=sTempSup;
	END WHILE;
	RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getPUserIdById` */

/*!50003 DROP FUNCTION IF EXISTS `f_getPUserIdById` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getPUserIdById`(`id` INT) RETURNS int(11)
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp INT(11);
  SET sTemp = NULL;
  SELECT supervisor_id INTO sTemp FROM t_authority_user WHERE n_uid= id;
  RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getRoleNameById` */

/*!50003 DROP FUNCTION IF EXISTS `f_getRoleNameById` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getRoleNameById`(`id` INT) RETURNS varchar(100) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(100);
  SET sTemp = '';
  SELECT C_RNAME INTO sTemp FROM t_authority_role WHERE n_rid= id;
  RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_getTheirsPModuleList` */

/*!50003 DROP FUNCTION IF EXISTS `f_getTheirsPModuleList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_getTheirsPModuleList`(`nodeIds` varchar(1000)) RETURNS varchar(1000) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
	DECLARE sTemp VARCHAR(1000);
  DECLARE sTempSup VARCHAR(1000);
  SET sTemp = '$';
  SET sTempSup = nodeIds;
	WHILE sTempSup is not null DO
		SET sTemp = concat(sTemp,',',sTempSup);
		SELECT group_concat(N_Mparent) INTO sTempSup FROM t_authority_module where FIND_IN_SET(N_Mid,sTempSup)>0;
	END WHILE;
	RETURN sTemp;
END */$$
DELIMITER ;

/* Function  structure for function  `f_get_splitStringTotal` */

/*!50003 DROP FUNCTION IF EXISTS `f_get_splitStringTotal` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_get_splitStringTotal`(f_string VARCHAR(10000),f_delimiter VARCHAR(50) ) RETURNS int(11)
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN
  RETURN 1 + (LENGTH(f_string) - LENGTH(REPLACE(f_string,f_delimiter,'')));  
END */$$
DELIMITER ;

/* Function  structure for function  `f_splitString` */

/*!50003 DROP FUNCTION IF EXISTS `f_splitString` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `f_splitString`( f_string VARCHAR(1000),f_delimiter VARCHAR(5),f_order INT) RETURNS varchar(255) CHARSET utf8
    READS SQL DATA
    SQL SECURITY INVOKER
BEGIN  
    DECLARE result VARCHAR(255) DEFAULT '';  
    SET result = REVERSE(SUBSTRING_INDEX(REVERSE(SUBSTRING_INDEX(f_string,f_delimiter,f_order)),f_delimiter,1));  
    RETURN result;  
END */$$
DELIMITER ;

/* Procedure structure for procedure `p_createIndex` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_createIndex` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `p_createIndex`(IN index_name VARCHAR(2000),IN tab_owner VARCHAR(2000),IN tab_name VARCHAR(2000),IN tab_col VARCHAR(2000))
BEGIN				
			DECLARE v_count INT DEFAULT 0;			
			SELECT COUNT(1) INTO  v_count FROM information_schema.STATISTICS WHERE table_schema=LOWER(tab_owner) AND table_name=LOWER(tab_name) AND index_name=UPPER(index_name) AND column_name=UPPER(tab_col);
			IF v_count=0 THEN
			SET @v_sql=CONCAT('create index ',index_name,' on ',tab_owner,'.',tab_name,'(',tab_col,')');  
                        PREPARE stmt FROM @v_sql;  
                        EXECUTE stmt;     
                        DEALLOCATE PREPARE stmt;    
		        END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_generateMainResultData` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_generateMainResultData` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` PROCEDURE `p_generateMainResultData`(IN `param1` INT)
BEGIN
   DECLARE i INT;
   SET @i = 0;
   #set param1 = 200;
   WHILE @i < param1 DO  
INSERT INTO `mig_auditf_main_result` VALUES ( '1',	
 '1', RIGHT(CONCAT('000000',CAST(@i AS CHAR)),6), '100', '100', 
'80', '90', '5', '5', '5', 
'6', '2015-04-29 10:16:23');
 
 SET @i=@i+1;
   END WHILE;
END */$$
DELIMITER ;

/* Procedure structure for procedure `p_tran_data` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_tran_data` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `p_tran_data`(IN p_sdb VARCHAR(10),IN p_tdb VARCHAR(10),IN v_tablename VARCHAR(50),IN v_orgtabname VARCHAR(50))
BEGIN  
    DECLARE  done                INT DEFAULT 0;
    DECLARE  v_table_name        VARCHAR(30);
    DECLARE  v_table_column      VARCHAR(30);
    DECLARE  v_flag              INT;
    DECLARE  v_htype             VARCHAR(1);
    DECLARE  v_s_sql             TEXT;
    DECLARE  v_src_column        VARCHAR(30);
    DECLARE  v_tag_column        VARCHAR(30);
    DECLARE  v_repp_sql          VARCHAR(4000);
    DECLARE  v_insert_src_sql    TEXT;
  
    DECLARE  v_intersect_column  VARCHAR(4000);
    DECLARE  vv_intersect_column VARCHAR(4000);
    DECLARE  v_all_column        VARCHAR(4000);
    
    DECLARE  v_inest_tag_column  VARCHAR(60);
    DECLARE  v_default_value     VARCHAR(60);
    DECLARE  v_inest_sql         TEXT;
    
    DECLARE  v_truncate_sql      TEXT;
 
    DECLARE sql_cur CURSOR FOR
       SELECT TABLE_NAME
         FROM information_schema.tables 
        WHERE table_schema=p_tdb
          AND LOWER(table_name) = LOWER(v_tablename)
      ORDER BY table_name;
     
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
    
   
    OPEN sql_cur;
    w1:LOOP
      FETCH sql_cur INTO v_table_name; 
       IF done=1 THEN
          LEAVE w1;
       END IF;        
         
         SELECT COUNT(1) INTO v_flag
           FROM mig_load_map
          WHERE LOWER(tname)=LOWER(v_orgtabname);
      IF v_flag<>0 THEN
          SELECT htype INTO v_htype
           FROM mig_load_map
          WHERE LOWER(tname)=LOWER(v_orgtabname) LIMIT 1;
         IF  v_htype='1'   THEN
             SELECT s_sql INTO v_s_sql
               FROM mig_load_map
              WHERE LOWER(tname)=LOWER(v_orgtabname);     
              SET @sql1 = v_s_sql;  
              PREPARE s1 FROM  @sql1;  
              EXECUTE s1;  
              DEALLOCATE PREPARE s1;  
         ELSEIF v_htype='2' THEN  
         
             SELECT GROUP_CONCAT(a.column_name SEPARATOR ',') INTO v_all_column
             FROM information_schema.columns a,information_schema.columns b
            WHERE a.table_name=b.table_name
              AND a.COLUMN_NAME=b.column_name
              AND LOWER(a.table_name)=LOWER(v_table_name)
              AND LOWER(a.table_schema)=LOWER(p_sdb)
              AND LOWER(b.table_schema)=LOWER(p_tdb);
              
             SELECT GROUP_CONCAT(src_column SEPARATOR ','),GROUP_CONCAT(tag_column SEPARATOR ',') INTO v_src_column,v_tag_column
               FROM mig_load_map 
              WHERE LOWER(tname)=LOWER(v_orgtabname);
             
             SET v_repp_sql=CONCAT('insert into ',p_tdb,'.',v_table_name,'(',v_all_column,',',v_tag_column,')',' select ',v_all_column,',',v_src_column,' from ',p_sdb,'.',v_table_name);                  
             SET @str_rep=v_repp_sql;  
             PREPARE stmt1 FROM @str_rep;  
             EXECUTE stmt1;  
             DEALLOCATE PREPARE stmt1;  
             
            
         ELSEIF v_htype='3' THEN 
         
            SELECT GROUP_CONCAT(a.column_name SEPARATOR ',') INTO vv_intersect_column
             FROM information_schema.columns a,information_schema.columns b
            WHERE a.table_name=b.table_name
              AND a.COLUMN_NAME=b.column_name
              AND a.table_name=LOWER(v_table_name)
              AND LOWER(a.table_schema)=LOWER(p_sdb)
              AND LOWER(b.table_schema)=LOWER(p_tdb);
              
             SELECT GROUP_CONCAT(tag_column SEPARATOR ','),GROUP_CONCAT(default_value SEPARATOR ',') INTO v_inest_tag_column,v_default_value
               FROM mig_load_map 
              WHERE LOWER(tname)=LOWER(v_orgtabname);
             
             SET v_inest_sql=CONCAT('insert into ',p_tdb,'.',v_table_name,'(',vv_intersect_column,',',v_inest_tag_column,')',' select ',vv_intersect_column,',',v_default_value,' from ',p_sdb,'.',v_table_name);                  
             SET @str_rep=v_inest_sql;  
             PREPARE stmt2 FROM @str_rep;  
             EXECUTE stmt2;  
             DEALLOCATE PREPARE stmt2;  
         END IF;
         
      ELSE            
           SELECT GROUP_CONCAT(a.column_name SEPARATOR ',') INTO v_intersect_column
             FROM information_schema.columns a,information_schema.columns b
            WHERE a.table_name=b.table_name
              AND a.COLUMN_NAME=b.column_name
              AND LOWER(a.table_name)=LOWER(v_table_name)
              AND LOWER(a.table_schema)=LOWER(p_sdb)
              AND LOWER(b.table_schema)=LOWER(p_tdb);
                                 
              SET v_insert_src_sql=CONCAT('insert into ',p_tdb,'.',v_table_name,'(',v_intersect_column,')',' select ',v_intersect_column,' from ',p_sdb,'.',v_table_name);                  
              SET @str_rep=v_insert_src_sql;  
              PREPARE stmt2 FROM @str_rep;  
              EXECUTE stmt2;  
              DEALLOCATE PREPARE stmt2;   
        
      END IF;
       
 	END LOOP;
 	COMMIT;
	CLOSE sql_cur;
	
    END */$$
DELIMITER ;

/*Table structure for table `v_busi_domain` */

DROP TABLE IF EXISTS `v_busi_domain`;

/*!50001 DROP VIEW IF EXISTS `v_busi_domain` */;
/*!50001 DROP TABLE IF EXISTS `v_busi_domain` */;

/*!50001 CREATE TABLE  `v_busi_domain`(
 `id` bigint(20) unsigned ,
 `NAME` varchar(254) ,
 `remark` varchar(1024) 
)*/;

/*Table structure for table `v_dryrun_config` */

DROP TABLE IF EXISTS `v_dryrun_config`;

/*!50001 DROP VIEW IF EXISTS `v_dryrun_config` */;
/*!50001 DROP TABLE IF EXISTS `v_dryrun_config` */;

/*!50001 CREATE TABLE  `v_dryrun_config`(
 `mig_dryrun_id` bigint(20) unsigned ,
 `mig_dryrun_name` varchar(254) ,
 `remark` varchar(1024) 
)*/;

/*Table structure for table `v_dryrun_env` */

DROP TABLE IF EXISTS `v_dryrun_env`;

/*!50001 DROP VIEW IF EXISTS `v_dryrun_env` */;
/*!50001 DROP TABLE IF EXISTS `v_dryrun_env` */;

/*!50001 CREATE TABLE  `v_dryrun_env`(
 `code` varchar(20) ,
 `value` varchar(254) ,
 `remark` varchar(1024) 
)*/;

/*View structure for view v_busi_domain */

/*!50001 DROP TABLE IF EXISTS `v_busi_domain` */;
/*!50001 DROP VIEW IF EXISTS `v_busi_domain` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_busi_domain` AS select cast(`mig_codedetail_define`.`code` as unsigned) AS `id`,`mig_codedetail_define`.`value` AS `NAME`,`mig_codedetail_define`.`remark` AS `remark` from `mig_codedetail_define` where (`mig_codedetail_define`.`TYPE` = 'Busi-Domain') */;

/*View structure for view v_dryrun_config */

/*!50001 DROP TABLE IF EXISTS `v_dryrun_config` */;
/*!50001 DROP VIEW IF EXISTS `v_dryrun_config` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_dryrun_config` AS select cast(`mig_codedetail_define`.`code` as unsigned) AS `mig_dryrun_id`,`mig_codedetail_define`.`value` AS `mig_dryrun_name`,`mig_codedetail_define`.`remark` AS `remark` from `mig_codedetail_define` where (`mig_codedetail_define`.`TYPE` = 'DRYRUN') */;

/*View structure for view v_dryrun_env */

/*!50001 DROP TABLE IF EXISTS `v_dryrun_env` */;
/*!50001 DROP VIEW IF EXISTS `v_dryrun_env` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `v_dryrun_env` AS select `mig_codedetail_define`.`code` AS `code`,`mig_codedetail_define`.`value` AS `value`,`mig_codedetail_define`.`remark` AS `remark` from `mig_codedetail_define` where (`mig_codedetail_define`.`TYPE` = 'DB-Env') */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
