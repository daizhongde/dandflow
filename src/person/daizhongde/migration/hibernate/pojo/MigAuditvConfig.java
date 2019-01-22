package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditvConfig entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvConfig implements java.io.Serializable {

	// Fields

	private Integer auditId;
	private Integer domain;
	private String tableName;
	private String auditName;
	private String auditType;
	private Integer auditLevel;
	private String sqlType;
	private String srcAuditSql;
	private String dstAuditSql;
	private String auditValue;
	private String auditFlag;
	private String invalidDataSql;
	private String operator;
	private String auditUnit;
	private String auditAuthor;
	private String remark;
	private String srcDbConnect;
	private String dstDbConnect;
	private String migSqlRep;
	private String version;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public MigAuditvConfig() {
	}

	/** minimal constructor */
	public MigAuditvConfig(Integer domain, String auditName,
			Integer auditLevel, String srcAuditSql, String auditValue,
			String operator, String auditAuthor, String srcDbConnect,
			String migSqlRep, Timestamp ctime) {
		this.domain = domain;
		this.auditName = auditName;
		this.auditLevel = auditLevel;
		this.srcAuditSql = srcAuditSql;
		this.auditValue = auditValue;
		this.operator = operator;
		this.auditAuthor = auditAuthor;
		this.srcDbConnect = srcDbConnect;
		this.migSqlRep = migSqlRep;
		this.ctime = ctime;
	}

	/** full constructor */
	public MigAuditvConfig(Integer domain, String tableName, String auditName,
			String auditType, Integer auditLevel, String sqlType,
			String srcAuditSql, String dstAuditSql, String auditValue,
			String auditFlag, String invalidDataSql, String operator,
			String auditUnit, String auditAuthor, String remark,
			String srcDbConnect, String dstDbConnect, String migSqlRep,
			String version, Timestamp ctime) {
		this.domain = domain;
		this.tableName = tableName;
		this.auditName = auditName;
		this.auditType = auditType;
		this.auditLevel = auditLevel;
		this.sqlType = sqlType;
		this.srcAuditSql = srcAuditSql;
		this.dstAuditSql = dstAuditSql;
		this.auditValue = auditValue;
		this.auditFlag = auditFlag;
		this.invalidDataSql = invalidDataSql;
		this.operator = operator;
		this.auditUnit = auditUnit;
		this.auditAuthor = auditAuthor;
		this.remark = remark;
		this.srcDbConnect = srcDbConnect;
		this.dstDbConnect = dstDbConnect;
		this.migSqlRep = migSqlRep;
		this.version = version;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public Integer getDomain() {
		return this.domain;
	}

	public void setDomain(Integer domain) {
		this.domain = domain;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getAuditType() {
		return this.auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public Integer getAuditLevel() {
		return this.auditLevel;
	}

	public void setAuditLevel(Integer auditLevel) {
		this.auditLevel = auditLevel;
	}

	public String getSqlType() {
		return this.sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getSrcAuditSql() {
		return this.srcAuditSql;
	}

	public void setSrcAuditSql(String srcAuditSql) {
		this.srcAuditSql = srcAuditSql;
	}

	public String getDstAuditSql() {
		return this.dstAuditSql;
	}

	public void setDstAuditSql(String dstAuditSql) {
		this.dstAuditSql = dstAuditSql;
	}

	public String getAuditValue() {
		return this.auditValue;
	}

	public void setAuditValue(String auditValue) {
		this.auditValue = auditValue;
	}

	public String getAuditFlag() {
		return this.auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getInvalidDataSql() {
		return this.invalidDataSql;
	}

	public void setInvalidDataSql(String invalidDataSql) {
		this.invalidDataSql = invalidDataSql;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAuditUnit() {
		return this.auditUnit;
	}

	public void setAuditUnit(String auditUnit) {
		this.auditUnit = auditUnit;
	}

	public String getAuditAuthor() {
		return this.auditAuthor;
	}

	public void setAuditAuthor(String auditAuthor) {
		this.auditAuthor = auditAuthor;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSrcDbConnect() {
		return this.srcDbConnect;
	}

	public void setSrcDbConnect(String srcDbConnect) {
		this.srcDbConnect = srcDbConnect;
	}

	public String getDstDbConnect() {
		return this.dstDbConnect;
	}

	public void setDstDbConnect(String dstDbConnect) {
		this.dstDbConnect = dstDbConnect;
	}

	public String getMigSqlRep() {
		return this.migSqlRep;
	}

	public void setMigSqlRep(String migSqlRep) {
		this.migSqlRep = migSqlRep;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}