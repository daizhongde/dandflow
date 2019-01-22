package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditvResult entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvResult implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer auditId;
	private String faresDryrunId;
	private String domain;
	private String tableName;
	private String auditName;
	private String srcValue;
	private String dstValue;
	private String minValue;
	private String auditUnit;
	private String invalidDataTable;
	private Integer result;
	private String auditAuthor;
	private Timestamp hdate;
	private String remark;
	private Integer successFlag;
	private String errMsg;
	private Integer invalidDataCnt;
	private String env;
	private String srcAuditSql;

	// Constructors

	/** default constructor */
	public MigAuditvResult() {
	}

	/** minimal constructor */
	public MigAuditvResult(Integer auditId, String auditName, String env) {
		this.auditId = auditId;
		this.auditName = auditName;
		this.env = env;
	}

	/** full constructor */
	public MigAuditvResult(Integer auditId, String faresDryrunId,
			String domain, String tableName, String auditName, String srcValue,
			String dstValue, String minValue, String auditUnit,
			String invalidDataTable, Integer result, String auditAuthor,
			Timestamp hdate, String remark, Integer successFlag, String errMsg,
			Integer invalidDataCnt, String env, String srcAuditSql) {
		this.auditId = auditId;
		this.faresDryrunId = faresDryrunId;
		this.domain = domain;
		this.tableName = tableName;
		this.auditName = auditName;
		this.srcValue = srcValue;
		this.dstValue = dstValue;
		this.minValue = minValue;
		this.auditUnit = auditUnit;
		this.invalidDataTable = invalidDataTable;
		this.result = result;
		this.auditAuthor = auditAuthor;
		this.hdate = hdate;
		this.remark = remark;
		this.successFlag = successFlag;
		this.errMsg = errMsg;
		this.invalidDataCnt = invalidDataCnt;
		this.env = env;
		this.srcAuditSql = srcAuditSql;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getFaresDryrunId() {
		return this.faresDryrunId;
	}

	public void setFaresDryrunId(String faresDryrunId) {
		this.faresDryrunId = faresDryrunId;
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

	public String getSrcValue() {
		return this.srcValue;
	}

	public void setSrcValue(String srcValue) {
		this.srcValue = srcValue;
	}

	public String getDstValue() {
		return this.dstValue;
	}

	public void setDstValue(String dstValue) {
		this.dstValue = dstValue;
	}

	public String getMinValue() {
		return this.minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getAuditUnit() {
		return this.auditUnit;
	}

	public void setAuditUnit(String auditUnit) {
		this.auditUnit = auditUnit;
	}

	public String getInvalidDataTable() {
		return this.invalidDataTable;
	}

	public void setInvalidDataTable(String invalidDataTable) {
		this.invalidDataTable = invalidDataTable;
	}

	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getAuditAuthor() {
		return this.auditAuthor;
	}

	public void setAuditAuthor(String auditAuthor) {
		this.auditAuthor = auditAuthor;
	}

	public Timestamp getHdate() {
		return this.hdate;
	}

	public void setHdate(Timestamp hdate) {
		this.hdate = hdate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSuccessFlag() {
		return this.successFlag;
	}

	public void setSuccessFlag(Integer successFlag) {
		this.successFlag = successFlag;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Integer getInvalidDataCnt() {
		return this.invalidDataCnt;
	}

	public void setInvalidDataCnt(Integer invalidDataCnt) {
		this.invalidDataCnt = invalidDataCnt;
	}

	public String getEnv() {
		return this.env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getSrcAuditSql() {
		return this.srcAuditSql;
	}

	public void setSrcAuditSql(String srcAuditSql) {
		this.srcAuditSql = srcAuditSql;
	}

}