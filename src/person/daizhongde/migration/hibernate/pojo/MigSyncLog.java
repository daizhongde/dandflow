package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigSyncLog entity. @author MyEclipse Persistence Tools
 */

public class MigSyncLog implements java.io.Serializable {

	// Fields

	private Integer logId;
	private Integer migGroup;
	private String migTable;
	private String migAuthor;
	private Integer srcType;
	private String srcIp;
	private Integer srcPort;
	private String srcSchema;
	private String srcUser;
	private String srcPassword;
	private Integer dstType;
	private String dstIp;
	private Integer dstPort;
	private String dstSchema;
	private String dstUser;
	private String dstPassword;
	private String migWhere;
	private Integer migMode;
	private String migDesc;
	private Timestamp mtime;
	private Integer batchNo;
	private Integer dryrunId;
	private String dryrunEnv;
	private Timestamp begTime;
	private Timestamp endTime;
	private Integer status;
	private Long srcCount;
	private Long dstCount1;
	private Long dstCount2;
	private Long dstAdd;
	private Long dstUpdate;
	private Long dstDelete;

	// Constructors

	/** default constructor */
	public MigSyncLog() {
	}

	/** minimal constructor */
	public MigSyncLog(Integer migGroup, String migTable, String migAuthor,
			Integer srcType, String srcIp, Integer srcPort, String srcSchema,
			String srcUser, String srcPassword, Integer dstType, String dstIp,
			Integer dstPort, String dstSchema, String dstUser,
			String dstPassword, Integer migMode, Timestamp mtime,
			Integer batchNo, Integer status) {
		this.migGroup = migGroup;
		this.migTable = migTable;
		this.migAuthor = migAuthor;
		this.srcType = srcType;
		this.srcIp = srcIp;
		this.srcPort = srcPort;
		this.srcSchema = srcSchema;
		this.srcUser = srcUser;
		this.srcPassword = srcPassword;
		this.dstType = dstType;
		this.dstIp = dstIp;
		this.dstPort = dstPort;
		this.dstSchema = dstSchema;
		this.dstUser = dstUser;
		this.dstPassword = dstPassword;
		this.migMode = migMode;
		this.mtime = mtime;
		this.batchNo = batchNo;
		this.status = status;
	}

	/** full constructor */
	public MigSyncLog(Integer migGroup, String migTable, String migAuthor,
			Integer srcType, String srcIp, Integer srcPort, String srcSchema,
			String srcUser, String srcPassword, Integer dstType, String dstIp,
			Integer dstPort, String dstSchema, String dstUser,
			String dstPassword, String migWhere, Integer migMode,
			String migDesc, Timestamp mtime, Integer batchNo, Integer dryrunId,
			String dryrunEnv, Timestamp begTime, Timestamp endTime,
			Integer status, Long srcCount, Long dstCount1, Long dstCount2,
			Long dstAdd, Long dstUpdate, Long dstDelete) {
		this.migGroup = migGroup;
		this.migTable = migTable;
		this.migAuthor = migAuthor;
		this.srcType = srcType;
		this.srcIp = srcIp;
		this.srcPort = srcPort;
		this.srcSchema = srcSchema;
		this.srcUser = srcUser;
		this.srcPassword = srcPassword;
		this.dstType = dstType;
		this.dstIp = dstIp;
		this.dstPort = dstPort;
		this.dstSchema = dstSchema;
		this.dstUser = dstUser;
		this.dstPassword = dstPassword;
		this.migWhere = migWhere;
		this.migMode = migMode;
		this.migDesc = migDesc;
		this.mtime = mtime;
		this.batchNo = batchNo;
		this.dryrunId = dryrunId;
		this.dryrunEnv = dryrunEnv;
		this.begTime = begTime;
		this.endTime = endTime;
		this.status = status;
		this.srcCount = srcCount;
		this.dstCount1 = dstCount1;
		this.dstCount2 = dstCount2;
		this.dstAdd = dstAdd;
		this.dstUpdate = dstUpdate;
		this.dstDelete = dstDelete;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getMigGroup() {
		return this.migGroup;
	}

	public void setMigGroup(Integer migGroup) {
		this.migGroup = migGroup;
	}

	public String getMigTable() {
		return this.migTable;
	}

	public void setMigTable(String migTable) {
		this.migTable = migTable;
	}

	public String getMigAuthor() {
		return this.migAuthor;
	}

	public void setMigAuthor(String migAuthor) {
		this.migAuthor = migAuthor;
	}

	public Integer getSrcType() {
		return this.srcType;
	}

	public void setSrcType(Integer srcType) {
		this.srcType = srcType;
	}

	public String getSrcIp() {
		return this.srcIp;
	}

	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}

	public Integer getSrcPort() {
		return this.srcPort;
	}

	public void setSrcPort(Integer srcPort) {
		this.srcPort = srcPort;
	}

	public String getSrcSchema() {
		return this.srcSchema;
	}

	public void setSrcSchema(String srcSchema) {
		this.srcSchema = srcSchema;
	}

	public String getSrcUser() {
		return this.srcUser;
	}

	public void setSrcUser(String srcUser) {
		this.srcUser = srcUser;
	}

	public String getSrcPassword() {
		return this.srcPassword;
	}

	public void setSrcPassword(String srcPassword) {
		this.srcPassword = srcPassword;
	}

	public Integer getDstType() {
		return this.dstType;
	}

	public void setDstType(Integer dstType) {
		this.dstType = dstType;
	}

	public String getDstIp() {
		return this.dstIp;
	}

	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}

	public Integer getDstPort() {
		return this.dstPort;
	}

	public void setDstPort(Integer dstPort) {
		this.dstPort = dstPort;
	}

	public String getDstSchema() {
		return this.dstSchema;
	}

	public void setDstSchema(String dstSchema) {
		this.dstSchema = dstSchema;
	}

	public String getDstUser() {
		return this.dstUser;
	}

	public void setDstUser(String dstUser) {
		this.dstUser = dstUser;
	}

	public String getDstPassword() {
		return this.dstPassword;
	}

	public void setDstPassword(String dstPassword) {
		this.dstPassword = dstPassword;
	}

	public String getMigWhere() {
		return this.migWhere;
	}

	public void setMigWhere(String migWhere) {
		this.migWhere = migWhere;
	}

	public Integer getMigMode() {
		return this.migMode;
	}

	public void setMigMode(Integer migMode) {
		this.migMode = migMode;
	}

	public String getMigDesc() {
		return this.migDesc;
	}

	public void setMigDesc(String migDesc) {
		this.migDesc = migDesc;
	}

	public Timestamp getMtime() {
		return this.mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	public Integer getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getDryrunId() {
		return this.dryrunId;
	}

	public void setDryrunId(Integer dryrunId) {
		this.dryrunId = dryrunId;
	}

	public String getDryrunEnv() {
		return this.dryrunEnv;
	}

	public void setDryrunEnv(String dryrunEnv) {
		this.dryrunEnv = dryrunEnv;
	}

	public Timestamp getBegTime() {
		return this.begTime;
	}

	public void setBegTime(Timestamp begTime) {
		this.begTime = begTime;
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

	public Long getSrcCount() {
		return this.srcCount;
	}

	public void setSrcCount(Long srcCount) {
		this.srcCount = srcCount;
	}

	public Long getDstCount1() {
		return this.dstCount1;
	}

	public void setDstCount1(Long dstCount1) {
		this.dstCount1 = dstCount1;
	}

	public Long getDstCount2() {
		return this.dstCount2;
	}

	public void setDstCount2(Long dstCount2) {
		this.dstCount2 = dstCount2;
	}

	public Long getDstAdd() {
		return this.dstAdd;
	}

	public void setDstAdd(Long dstAdd) {
		this.dstAdd = dstAdd;
	}

	public Long getDstUpdate() {
		return this.dstUpdate;
	}

	public void setDstUpdate(Long dstUpdate) {
		this.dstUpdate = dstUpdate;
	}

	public Long getDstDelete() {
		return this.dstDelete;
	}

	public void setDstDelete(Long dstDelete) {
		this.dstDelete = dstDelete;
	}

}