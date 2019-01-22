package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

/**
 * MigTaskInfo entity. @author MyEclipse Persistence Tools
 */

public class MigTaskInfo implements java.io.Serializable {

	// Fields

	private String taskId;
	private String taskName;
	private String controlId;
	private String comId;
	private String taskAuthor;
	private String taskRemark;
	private Timestamp taskUpdate;

	// Constructors

	/** default constructor */
	public MigTaskInfo() {
	}

	/** minimal constructor */
	public MigTaskInfo(String taskId, Timestamp taskUpdate) {
		this.taskId = taskId;
		this.taskUpdate = taskUpdate;
	}

	/** full constructor */
	public MigTaskInfo(String taskId, String taskName,  String taskAuthor, String taskRemark,
			Timestamp taskUpdate,
			String controlId,
			String comId ) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.controlId = controlId;
		this.comId = comId;
		this.taskAuthor = taskAuthor;
		this.taskRemark = taskRemark;
		this.taskUpdate = taskUpdate;
	}

	// Property accessors

	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getControlId() {
		return this.controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getTaskAuthor() {
		return this.taskAuthor;
	}

	public void setTaskAuthor(String taskAuthor) {
		this.taskAuthor = taskAuthor;
	}

	public String getTaskRemark() {
		return this.taskRemark;
	}

	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getTaskUpdate() {
		return this.taskUpdate;
	}

	public void setTaskUpdate(Timestamp taskUpdate) {
		this.taskUpdate = taskUpdate;
	}
	@Override
	public String toString() {	
		return "{" + (taskId != null ? "taskId:'" + taskId + "', " : "taskId:'',")
				+ (taskName != null ? "taskName:'" + taskName + "', " : "taskName:'',")
				+ (controlId != null ? "controlId:'" + controlId + "', " : "controlId:'',")
				+ (comId != null ? "comId:'" + comId + "', " : "comId:'',")
				+ (taskAuthor != null ? "taskAuthor:'" + taskAuthor + "', " : "taskAuthor:'',")
//				+ (taskRemark != null ? "taskRemark:'" + taskRemark + "', " : "taskRemark:'',")
				+ (taskUpdate != null ? "taskUpdate:'" + taskUpdate + "' " : "taskUpdate:''") + "}";
	}
}