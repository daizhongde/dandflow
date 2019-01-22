package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigJobInfo entity. @author MyEclipse Persistence Tools
 */

public class MigJobInfo implements java.io.Serializable {

	// Fields

	private String jobId;
	private String jobName;
	private Integer type;
	private String jobAuthor;
	private String jobRemark;
	private Timestamp jobUpdate;

	// Constructors

	/** default constructor */
	public MigJobInfo() {
	}

	/** minimal constructor */
	public MigJobInfo(String jobId, Timestamp jobUpdate) {
		this.jobId = jobId;
		this.jobUpdate = jobUpdate;
	}

	/** full constructor */
	public MigJobInfo(String jobId, String jobName, Integer type,
			String jobAuthor, String jobRemark, Timestamp jobUpdate) {
		this.jobId = jobId;
		this.jobName = jobName;
		this.type = type;
		this.jobAuthor = jobAuthor;
		this.jobRemark = jobRemark;
		this.jobUpdate = jobUpdate;
	}

	// Property accessors

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getJobAuthor() {
		return this.jobAuthor;
	}

	public void setJobAuthor(String jobAuthor) {
		this.jobAuthor = jobAuthor;
	}

	public String getJobRemark() {
		return this.jobRemark;
	}

	public void setJobRemark(String jobRemark) {
		this.jobRemark = jobRemark;
	}

	public Timestamp getJobUpdate() {
		return this.jobUpdate;
	}

	public void setJobUpdate(Timestamp jobUpdate) {
		this.jobUpdate = jobUpdate;
	}

	@Override
	public String toString() {
		return "{" + (jobId != null ? "jobId:'" + jobId + "', " : "jobId:'',")
				+ (jobName != null ? "jobName:'" + jobName + "', " : "jobName:'',")
				+ (type != null ? "type: '" + type + "', " : "type:'',")
				+ (jobAuthor != null ? "jobAuthor:'" + jobAuthor + "', " : "jobAuthor:'',")
				+ (jobRemark != null ? "jobRemark:'" + jobRemark + "', " : "jobRemark:'',")
				+ (jobUpdate != null ? "jobUpdate:'" + jobUpdate + "' " : "jobUpdate:''") + "}";
	}
}