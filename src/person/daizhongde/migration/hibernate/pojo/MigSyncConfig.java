package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigSyncConfig entity. @author MyEclipse Persistence Tools
 */

public class MigSyncConfig implements java.io.Serializable {

	// Fields

	private Integer configId;
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

	// Constructors

	/** default constructor */
	public MigSyncConfig() {
	}

	/** minimal constructor */
	public MigSyncConfig(Integer migGroup, String migTable, String migAuthor,
			Integer srcType, String srcIp, Integer srcPort, String srcSchema,
			Integer dstType, String dstIp, Integer dstPort, String dstSchema,
			Integer migMode, Timestamp mtime) {
		this.migGroup = migGroup;
		this.migTable = migTable;
		this.migAuthor = migAuthor;
		this.srcType = srcType;
		this.srcIp = srcIp;
		this.srcPort = srcPort;
		this.srcSchema = srcSchema;
		this.dstType = dstType;
		this.dstIp = dstIp;
		this.dstPort = dstPort;
		this.dstSchema = dstSchema;
		this.migMode = migMode;
		this.mtime = mtime;
	}

	/** full constructor */
	public MigSyncConfig(Integer migGroup, String migTable, String migAuthor,
			Integer srcType, String srcIp, Integer srcPort, String srcSchema,
			String srcUser, String srcPassword, Integer dstType, String dstIp,
			Integer dstPort, String dstSchema, String dstUser,
			String dstPassword, String migWhere, Integer migMode,
			String migDesc, Timestamp mtime) {
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
	}

	// Property accessors

	public Integer getConfigId() {
		return this.configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
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

}