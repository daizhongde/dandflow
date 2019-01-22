package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditvMidConfig entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvMidConfig implements java.io.Serializable {

	// Fields

	private Integer auditId;
	private String domain;
	private String tableName;
	private String auditName;
	private String auditType;
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
	private String version;

	// Constructors

	/** default constructor */
	public MigAuditvMidConfig() {
	}

	/** minimal constructor */
	public MigAuditvMidConfig(Integer auditId) {
		this.auditId = auditId;
	}

	/** full constructor */
	public MigAuditvMidConfig(Integer auditId, String domain, String tableName,
			String auditName, String auditType, String sqlType,
			String srcAuditSql, String dstAuditSql, String auditValue,
			String auditFlag, String invalidDataSql, String operator,
			String auditUnit, String auditAuthor, String remark,
			String srcDbConnect, String dstDbConnect, String version) {
		this.auditId = auditId;
		this.domain = domain;
		this.tableName = tableName;
		this.auditName = auditName;
		this.auditType = auditType;
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
		this.version = version;
	}

	// Property accessors

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
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

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}