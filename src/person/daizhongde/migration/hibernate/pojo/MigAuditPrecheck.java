package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditPrecheck entity. @author MyEclipse Persistence Tools
 */

public class MigAuditPrecheck implements java.io.Serializable {

	// Fields

	private Integer auditId;
	private String auditSchema;
	private String auditEnv;
	private String auditType;
	private String auditMode;
	private String auditObject;
	private String dryrunFront;
	private String dryrunBack;
	private Integer countFront;
	private Integer countBack;
	private Integer diffRatio;
	private Timestamp auditDate;
	private String auditRemark;

	// Constructors

	/** default constructor */
	public MigAuditPrecheck() {
	}

	/** full constructor */
	public MigAuditPrecheck(String auditSchema, String auditEnv,
			String auditType, String auditMode, String auditObject,
			String dryrunFront, String dryrunBack, Integer countFront,
			Integer countBack, Integer diffRatio, Timestamp auditDate,
			String auditRemark) {
		this.auditSchema = auditSchema;
		this.auditEnv = auditEnv;
		this.auditType = auditType;
		this.auditMode = auditMode;
		this.auditObject = auditObject;
		this.dryrunFront = dryrunFront;
		this.dryrunBack = dryrunBack;
		this.countFront = countFront;
		this.countBack = countBack;
		this.diffRatio = diffRatio;
		this.auditDate = auditDate;
		this.auditRemark = auditRemark;
	}

	// Property accessors

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getAuditSchema() {
		return this.auditSchema;
	}

	public void setAuditSchema(String auditSchema) {
		this.auditSchema = auditSchema;
	}

	public String getAuditEnv() {
		return this.auditEnv;
	}

	public void setAuditEnv(String auditEnv) {
		this.auditEnv = auditEnv;
	}

	public String getAuditType() {
		return this.auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getAuditMode() {
		return this.auditMode;
	}

	public void setAuditMode(String auditMode) {
		this.auditMode = auditMode;
	}

	public String getAuditObject() {
		return this.auditObject;
	}

	public void setAuditObject(String auditObject) {
		this.auditObject = auditObject;
	}

	public String getDryrunFront() {
		return this.dryrunFront;
	}

	public void setDryrunFront(String dryrunFront) {
		this.dryrunFront = dryrunFront;
	}

	public String getDryrunBack() {
		return this.dryrunBack;
	}

	public void setDryrunBack(String dryrunBack) {
		this.dryrunBack = dryrunBack;
	}

	public Integer getCountFront() {
		return this.countFront;
	}

	public void setCountFront(Integer countFront) {
		this.countFront = countFront;
	}

	public Integer getCountBack() {
		return this.countBack;
	}

	public void setCountBack(Integer countBack) {
		this.countBack = countBack;
	}

	public Integer getDiffRatio() {
		return this.diffRatio;
	}

	public void setDiffRatio(Integer diffRatio) {
		this.diffRatio = diffRatio;
	}

	public Timestamp getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditRemark() {
		return this.auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

}