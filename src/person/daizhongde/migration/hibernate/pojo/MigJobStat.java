package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigJobStat entity. @author MyEclipse Persistence Tools
 */

public class MigJobStat implements java.io.Serializable {

	// Fields

	private Integer logId;
	private String jobId;
	private String jobInsId;
	private Integer dryrunId;
	private String nodeId;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Integer status;
	private String remark;

	// Constructors

	/** default constructor */
	public MigJobStat() {
	}

	/** minimal constructor */
	public MigJobStat(Integer logId, String jobId, String jobInsId,
			Integer dryrunId, String nodeId, Timestamp beginTime,
			Timestamp endTime, Integer status) {
		this.logId = logId;
		this.jobId = jobId;
		this.jobInsId = jobInsId;
		this.dryrunId = dryrunId;
		this.nodeId = nodeId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
	}

	/** full constructor */
	public MigJobStat(Integer logId, String jobId, String jobInsId,
			Integer dryrunId, String nodeId, Timestamp beginTime,
			Timestamp endTime, Integer status, String remark) {
		this.logId = logId;
		this.jobId = jobId;
		this.jobInsId = jobInsId;
		this.dryrunId = dryrunId;
		this.nodeId = nodeId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.status = status;
		this.remark = remark;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobInsId() {
		return this.jobInsId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

	public Integer getDryrunId() {
		return this.dryrunId;
	}

	public void setDryrunId(Integer dryrunId) {
		this.dryrunId = dryrunId;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}