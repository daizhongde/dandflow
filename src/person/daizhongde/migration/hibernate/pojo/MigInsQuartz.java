package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigInsQuartz entity. @author MyEclipse Persistence Tools
 */

public class MigInsQuartz implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2180462401190967560L;
	private Integer id;
	private String caseId;
	private String jobName;
	private String jobGroup;
	private String cronExpression;
	private String beanClass;
	private String methodName;
	private String remark;
	private String author;
	private Timestamp createTime;
	private String cip;
	private String modifier;
	private Timestamp modifyTime;
	private String mip;

	// Constructors

	/** default constructor */
	public MigInsQuartz() {
	}

	/** minimal constructor */
	public MigInsQuartz(String caseId, String jobName, String jobGroup,
			String cronExpression, String beanClass, String methodName) {
		this.caseId = caseId;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cronExpression = cronExpression;
		this.beanClass = beanClass;
		this.methodName = methodName;
	}

	/** full constructor */
	public MigInsQuartz(String caseId, String jobName, String jobGroup,
			String cronExpression, String beanClass, String methodName,
			String remark, String author, Timestamp createTime, String cip,
			String modifier, Timestamp modifyTime, String mip) {
		this.caseId = caseId;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cronExpression = cronExpression;
		this.beanClass = beanClass;
		this.methodName = methodName;
		this.remark = remark;
		this.author = author;
		this.createTime = createTime;
		this.cip = cip;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.mip = mip;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return this.jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getCronExpression() {
		return this.cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getBeanClass() {
		return this.beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCip() {
		return this.cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getMip() {
		return this.mip;
	}

	public void setMip(String mip) {
		this.mip = mip;
	}

}