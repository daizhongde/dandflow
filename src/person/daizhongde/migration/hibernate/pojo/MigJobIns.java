package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * MigJobIns entity. @author MyEclipse Persistence Tools
 */

public class MigJobIns implements java.io.Serializable {

	// Fields

	private String jobInsId;
	private String jobId;
	private Integer dryrunId;
	private String jobInsName;
	private Integer type;
	private String status;
	private Timestamp mtime;
	private String author;
	private String remark;
	private String lockStatus;
	private Integer running;
	private Timestamp beginTime;
	private Timestamp endTime;

	private String totalTime;
	
	// Constructors

	/** default constructor */
	public MigJobIns() {
	}

	/** minimal constructor */
	public MigJobIns(String jobInsId, String jobId, Integer dryrunId,
			String jobInsName, Integer type, String status, Timestamp mtime) {
		this.jobInsId = jobInsId;
		this.jobId = jobId;
		this.dryrunId = dryrunId;
		this.jobInsName = jobInsName;
		this.type = type;
		this.status = status;
		this.mtime = mtime;
	}

	/** without two time field constructor */
	public MigJobIns(String jobInsId, String jobId, Integer dryrunId,
			String jobInsName, Integer type, String status, Timestamp mtime,
			String author, String remark, String lockStatus, Integer running) {
		this.jobInsId = jobInsId;
		this.jobId = jobId;
		this.dryrunId = dryrunId;
		this.jobInsName = jobInsName;
		this.type = type;
		this.status = status;
		this.mtime = mtime;
		this.author = author;
		this.remark = remark;
		this.lockStatus = lockStatus;
		this.running = running;
	}
	
	/** full constructor */
	public MigJobIns(String jobInsId, String jobId, Integer dryrunId,
			String jobInsName, Integer type, String status, Timestamp mtime,
			String author, String remark, String lockStatus, Integer running,
			Timestamp beginTime, Timestamp endTime) {
		this.jobInsId = jobInsId;
		this.jobId = jobId;
		this.dryrunId = dryrunId;
		this.jobInsName = jobInsName;
		this.type = type;
		this.status = status;
		this.mtime = mtime;
		this.author = author;
		this.remark = remark;
		this.lockStatus = lockStatus;
		this.running = running;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	// Property accessors

	public String getJobInsId() {
		return this.jobInsId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Integer getDryrunId() {
		return this.dryrunId;
	}

	public void setDryrunId(Integer dryrunId) {
		this.dryrunId = dryrunId;
	}

	public String getJobInsName() {
		return this.jobInsName;
	}

	public void setJobInsName(String jobInsName) {
		this.jobInsName = jobInsName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getMtime() {
		return this.mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Integer getRunning() {
		return this.running;
	}

	public void setRunning(Integer running) {
		this.running = running;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getTotalTime() {
		return totalTime;
	}

	@Override
	public String toString() {
		return "{ "
				+ (jobInsId != null ? "jobInsId: '" + jobInsId + "', " : "jobInsId:'',")
				+ (jobId != null ? "jobId: '" + jobId + "', " : "jobId:'',")
				+ (jobInsName != null ? "jobInsName: '" + jobInsName + "', ": "jobInsName:'',")
				+ (dryrunId != null ? "dryrunId: " + dryrunId + ", " : "dryrunId:0,")
				+ (type != null ? "type: " + type + ", " : "type: 0 ,")
				+ (status != null ? "status: '" + status + "', " : "status:'',")
				+ (mtime != null ? "mtime: '" + mtime + "', " : "mtime:'',")
				+ (author != null ? "author: '" + author + "', " : "author:'',")
				+ (remark != null ? "remark: '" + remark + "', " : "remark:'',")
				+ (lockStatus != null ? "lockStatus: '" + lockStatus + "', ": "lockStatus:'',") 
				+ (running != null ? "running: " + running + ", " : "running:0,")
				+ (beginTime != null ? "beginTime: '" + beginTime + "', " : "beginTime:'',")
				+ (endTime != null ? "endTime: '" + endTime + "', " : "endTime:''")
				+ "}";
	}
}