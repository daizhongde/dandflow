package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

/**
 * MigJobProcess entity. @author MyEclipse Persistence Tools
 */

public class MigJobProcess implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8632638611087096199L;
	
	private String processId;
	private String jobInsId;
	private String jobId;
	private String nodeId;
	private Integer isleaf;
	private String nodeName;
	private String nodeRemark;
	private String controlId;
	private String comId;
	private Integer status;
	private String author;
	private String prepos;
	private String postpos;
	private Timestamp createdate;
	private String remark;
	private String coords;
	private Timestamp beginTime;
	private Timestamp endTime;
	
	private transient Coords zb;
	private String totalTime;
	
	// Constructors

	/** default constructor */
	public MigJobProcess() {
	}

	/** minimal constructor */
	public MigJobProcess(String processId) {
		this.processId = processId;
	}

	/** no two time field constructor */
	public MigJobProcess(String processId, String jobInsId, String jobId,
			String nodeId, Integer isleaf, String nodeName, String nodeRemark,
			String controlId, String comId, Integer status, String author, String prepos,
			String postpos, Timestamp createdate, String remark, String coords) {
		this.processId = processId;
		this.jobInsId = jobInsId;
		this.jobId = jobId;
		this.nodeId = nodeId;
		this.isleaf = isleaf;
		this.nodeName = nodeName;
		this.nodeRemark = nodeRemark;
		this.controlId = controlId;
		this.comId = comId;
		this.status = status;
		this.author = author;
		this.prepos = prepos;
		this.postpos = postpos;
		this.createdate = createdate;
		this.remark = remark;
		this.coords = coords;
	}
	
	/** full constructor */
	public MigJobProcess(String processId, String jobInsId, String jobId,
			String nodeId, Integer isleaf, String nodeName, String nodeRemark,
			String controlId, String comId, Integer status, String prepos,
			String postpos, Timestamp createdate, String remark, String coords,
			Timestamp beginTime, Timestamp endTime) {
		this.processId = processId;
		this.jobInsId = jobInsId;
		this.jobId = jobId;
		this.nodeId = nodeId;
		this.isleaf = isleaf;
		this.nodeName = nodeName;
		this.nodeRemark = nodeRemark;
		this.controlId = controlId;
		this.comId = comId;
		this.status = status;
		this.prepos = prepos;
		this.postpos = postpos;
		this.createdate = createdate;
		this.remark = remark;
		this.coords = coords;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	// Property accessors

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

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

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeRemark() {
		return this.nodeRemark;
	}

	public void setNodeRemark(String nodeRemark) {
		this.nodeRemark = nodeRemark;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPrepos() {
		return this.prepos;
	}

	public void setPrepos(String prepos) {
		this.prepos = prepos;
	}

	public String getPostpos() {
		return this.postpos;
	}

	public void setPostpos(String postpos) {
		this.postpos = postpos;
	}

	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCoords() {
		return this.coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
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
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	public Coords getZb() {
		String[] s = this.coords.split("\\,");
		return new Coords(
				Integer.valueOf(s[0]).intValue(), Integer.valueOf(s[1]).intValue()
				);
	}

	public void setZb(Coords zb) {
		this.zb = zb;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {	
				
		return "{" + (processId != null ? "processId:'" + processId + "', " : "processId:'',")
				+ (jobInsId != null ? "jobInsId:'" + jobInsId + "', " : "jobInsId:'',")
				+ (jobId != null ? "jobId:'" + jobId + "', " : "jobId:'',")
				+ (nodeId != null ? "nodeId:'" + nodeId + "', " : "nodeId:'',")
				+ (isleaf != null ? "isleaf:'" + isleaf + "', " : "isleaf:'',")
				+ (nodeName != null ? "nodeName:'" + nodeName + "', " : "nodeName:'',")
				+ (nodeRemark != null ? "nodeRemark:'" + nodeRemark + "', " : "nodeRemark:'',")
				+ (controlId != null ? "controlId:'" + controlId + "', " : "controlId:'',")
				+ (comId != null ? "comId:'" + comId + "', " : "comId:'',")
				+ (status != null ? "status:'" + status + "', " : "status:'',")
				+ (author != null ? "author:'" + author + "', " : "author:'',")
				+ (prepos != null ? "prepos:'" + prepos + "', " : "prepos:'',")
				+ (postpos != null ? "postpos:'" + postpos + "', " : "postpos:'',")
				+ (createdate != null ? "createdate:'" + createdate + "', " : "createdate:'',")
				+ (remark != null ? "remark:'" + remark + "', " : "remark:'',")
				+ (coords != null ? "coords:'" + coords + "' " : "coords:'',")
				+ (beginTime != null ? "beginTime: '" + beginTime + "', " : "beginTime:'',")
				+ (endTime != null ? "endTime: '" + endTime + "', " : "endTime:''")
				+ "}";
	}
}