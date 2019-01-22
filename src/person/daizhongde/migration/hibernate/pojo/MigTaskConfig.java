package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigTaskConfig entity. @author MyEclipse Persistence Tools
 */

public class MigTaskConfig implements java.io.Serializable {

	// Fields

	private Integer migConfigId;
	private Integer migConfigType;
	private Integer domain;
	private String migSrc;
	private String migSrcConn;
	private String migWhere;
	private String migDst;
	private String migDstConn;
	private String migAuthor;
	private String migDesc;
	private Timestamp migModifytime;
	private Integer migStatus;

	// Constructors

	/** default constructor */
	public MigTaskConfig() {
	}

	/** minimal constructor */
	public MigTaskConfig(Integer migConfigType, Integer domain,
			String migAuthor, Timestamp migModifytime, Integer migStatus) {
		this.migConfigType = migConfigType;
		this.domain = domain;
		this.migAuthor = migAuthor;
		this.migModifytime = migModifytime;
		this.migStatus = migStatus;
	}

	/** full constructor */
	public MigTaskConfig(Integer migConfigType, Integer domain, String migSrc,
			String migSrcConn, String migWhere, String migDst,
			String migDstConn, String migAuthor, String migDesc,
			Timestamp migModifytime, Integer migStatus) {
		this.migConfigType = migConfigType;
		this.domain = domain;
		this.migSrc = migSrc;
		this.migSrcConn = migSrcConn;
		this.migWhere = migWhere;
		this.migDst = migDst;
		this.migDstConn = migDstConn;
		this.migAuthor = migAuthor;
		this.migDesc = migDesc;
		this.migModifytime = migModifytime;
		this.migStatus = migStatus;
	}

	// Property accessors

	public Integer getMigConfigId() {
		return this.migConfigId;
	}

	public void setMigConfigId(Integer migConfigId) {
		this.migConfigId = migConfigId;
	}

	public Integer getMigConfigType() {
		return this.migConfigType;
	}

	public void setMigConfigType(Integer migConfigType) {
		this.migConfigType = migConfigType;
	}

	public Integer getDomain() {
		return this.domain;
	}

	public void setDomain(Integer domain) {
		this.domain = domain;
	}

	public String getMigSrc() {
		return this.migSrc;
	}

	public void setMigSrc(String migSrc) {
		this.migSrc = migSrc;
	}

	public String getMigSrcConn() {
		return this.migSrcConn;
	}

	public void setMigSrcConn(String migSrcConn) {
		this.migSrcConn = migSrcConn;
	}

	public String getMigWhere() {
		return this.migWhere;
	}

	public void setMigWhere(String migWhere) {
		this.migWhere = migWhere;
	}

	public String getMigDst() {
		return this.migDst;
	}

	public void setMigDst(String migDst) {
		this.migDst = migDst;
	}

	public String getMigDstConn() {
		return this.migDstConn;
	}

	public void setMigDstConn(String migDstConn) {
		this.migDstConn = migDstConn;
	}

	public String getMigAuthor() {
		return this.migAuthor;
	}

	public void setMigAuthor(String migAuthor) {
		this.migAuthor = migAuthor;
	}

	public String getMigDesc() {
		return this.migDesc;
	}

	public void setMigDesc(String migDesc) {
		this.migDesc = migDesc;
	}

	public Timestamp getMigModifytime() {
		return this.migModifytime;
	}

	public void setMigModifytime(Timestamp migModifytime) {
		this.migModifytime = migModifytime;
	}

	public Integer getMigStatus() {
		return this.migStatus;
	}

	public void setMigStatus(Integer migStatus) {
		this.migStatus = migStatus;
	}

}