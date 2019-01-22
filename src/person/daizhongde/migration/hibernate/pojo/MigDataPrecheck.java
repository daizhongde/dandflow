package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigDataPrecheck entity. @author MyEclipse Persistence Tools
 */

public class MigDataPrecheck implements java.io.Serializable {

	// Fields

	private String checkId;
	private Integer dryrunId;
	private String checkEnv;
	private String checkType;
	private String checkObject;
	private Integer checkCount;
	private Integer checkField;
	private Timestamp checkDate;
	private String checkRemark;

	// Constructors

	/** default constructor */
	public MigDataPrecheck() {
	}

	/** minimal constructor */
	public MigDataPrecheck(String checkId) {
		this.checkId = checkId;
	}

	/** full constructor */
	public MigDataPrecheck(String checkId, Integer dryrunId, String checkEnv,
			String checkType, String checkObject, Integer checkCount,
			Integer checkField, Timestamp checkDate, String checkRemark) {
		this.checkId = checkId;
		this.dryrunId = dryrunId;
		this.checkEnv = checkEnv;
		this.checkType = checkType;
		this.checkObject = checkObject;
		this.checkCount = checkCount;
		this.checkField = checkField;
		this.checkDate = checkDate;
		this.checkRemark = checkRemark;
	}

	// Property accessors

	public String getCheckId() {
		return this.checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public Integer getDryrunId() {
		return this.dryrunId;
	}

	public void setDryrunId(Integer dryrunId) {
		this.dryrunId = dryrunId;
	}

	public String getCheckEnv() {
		return this.checkEnv;
	}

	public void setCheckEnv(String checkEnv) {
		this.checkEnv = checkEnv;
	}

	public String getCheckType() {
		return this.checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCheckObject() {
		return this.checkObject;
	}

	public void setCheckObject(String checkObject) {
		this.checkObject = checkObject;
	}

	public Integer getCheckCount() {
		return this.checkCount;
	}

	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}

	public Integer getCheckField() {
		return this.checkField;
	}

	public void setCheckField(Integer checkField) {
		this.checkField = checkField;
	}

	public Timestamp getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Timestamp checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckRemark() {
		return this.checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

}