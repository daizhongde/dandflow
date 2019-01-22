package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigJobLog entity. @author MyEclipse Persistence Tools
 */

public class MigJobLog implements java.io.Serializable {

	// Fields

	private String logId;
	private String jobInsId;
	private Integer dryrunId;
	private String taskId;
	private Integer level;
	private String logMsg;
	private Timestamp ctime;
	private String remark;

	// Constructors

	/** default constructor */
	public MigJobLog() {
	}

	/** minimal constructor */
	public MigJobLog(String logId, String jobInsId, Integer dryrunId,
			String taskId, Integer level, String logMsg, Timestamp ctime) {
		this.logId = logId;
		this.jobInsId = jobInsId;
		this.dryrunId = dryrunId;
		this.taskId = taskId;
		this.level = level;
		this.logMsg = logMsg;
		this.ctime = ctime;
	}

	/** full constructor */
	public MigJobLog(String logId, String jobInsId, Integer dryrunId,
			String taskId, Integer level, String logMsg, Timestamp ctime,
			String remark) {
		this.logId = logId;
		this.jobInsId = jobInsId;
		this.dryrunId = dryrunId;
		this.taskId = taskId;
		this.level = level;
		this.logMsg = logMsg;
		this.ctime = ctime;
		this.remark = remark;
	}

	// Property accessors

	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
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

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLogMsg() {
		return this.logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}