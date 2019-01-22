package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditfSub entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfSub implements java.io.Serializable {

	// Fields

	private MigAuditfSubId id;
	private String fauditSrcField;
	private String fauditDstField;
	private Integer fauditIskey;
	private String fauditOpt;
	private Timestamp fauditCreatetime;
	private Timestamp fauditModifytime;
	private Integer fauditStatus;

	// Constructors

	/** default constructor */
	public MigAuditfSub() {
	}

	/** minimal constructor */
	public MigAuditfSub(MigAuditfSubId id, String fauditSrcField,
			String fauditDstField, Integer fauditIskey,
			Timestamp fauditCreatetime, Integer fauditStatus) {
		this.id = id;
		this.fauditSrcField = fauditSrcField;
		this.fauditDstField = fauditDstField;
		this.fauditIskey = fauditIskey;
		this.fauditCreatetime = fauditCreatetime;
		this.fauditStatus = fauditStatus;
	}

	/** full constructor */
	public MigAuditfSub(MigAuditfSubId id, String fauditSrcField,
			String fauditDstField, Integer fauditIskey, String fauditOpt,
			Timestamp fauditCreatetime, Timestamp fauditModifytime,
			Integer fauditStatus) {
		this.id = id;
		this.fauditSrcField = fauditSrcField;
		this.fauditDstField = fauditDstField;
		this.fauditIskey = fauditIskey;
		this.fauditOpt = fauditOpt;
		this.fauditCreatetime = fauditCreatetime;
		this.fauditModifytime = fauditModifytime;
		this.fauditStatus = fauditStatus;
	}

	// Property accessors

	public MigAuditfSubId getId() {
		return this.id;
	}

	public void setId(MigAuditfSubId id) {
		this.id = id;
	}

	public String getFauditSrcField() {
		return this.fauditSrcField;
	}

	public void setFauditSrcField(String fauditSrcField) {
		this.fauditSrcField = fauditSrcField;
	}

	public String getFauditDstField() {
		return this.fauditDstField;
	}

	public void setFauditDstField(String fauditDstField) {
		this.fauditDstField = fauditDstField;
	}

	public Integer getFauditIskey() {
		return this.fauditIskey;
	}

	public void setFauditIskey(Integer fauditIskey) {
		this.fauditIskey = fauditIskey;
	}

	public String getFauditOpt() {
		return this.fauditOpt;
	}

	public void setFauditOpt(String fauditOpt) {
		this.fauditOpt = fauditOpt;
	}

	public Timestamp getFauditCreatetime() {
		return this.fauditCreatetime;
	}

	public void setFauditCreatetime(Timestamp fauditCreatetime) {
		this.fauditCreatetime = fauditCreatetime;
	}

	public Timestamp getFauditModifytime() {
		return this.fauditModifytime;
	}

	public void setFauditModifytime(Timestamp fauditModifytime) {
		this.fauditModifytime = fauditModifytime;
	}

	public Integer getFauditStatus() {
		return this.fauditStatus;
	}

	public void setFauditStatus(Integer fauditStatus) {
		this.fauditStatus = fauditStatus;
	}

}