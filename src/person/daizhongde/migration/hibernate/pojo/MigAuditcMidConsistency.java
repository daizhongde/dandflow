package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditcMidConsistency entity. @author MyEclipse Persistence Tools
 */

public class MigAuditcMidConsistency implements java.io.Serializable {

	// Fields

	private Integer auditId;
	private String auditName;
	private String migSql;
	private String author;
	private String migSqlRep;

	// Constructors

	/** default constructor */
	public MigAuditcMidConsistency() {
	}

	/** minimal constructor */
	public MigAuditcMidConsistency(Integer auditId) {
		this.auditId = auditId;
	}

	/** full constructor */
	public MigAuditcMidConsistency(Integer auditId, String auditName,
			String migSql, String author, String migSqlRep) {
		this.auditId = auditId;
		this.auditName = auditName;
		this.migSql = migSql;
		this.author = author;
		this.migSqlRep = migSqlRep;
	}

	// Property accessors

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getMigSql() {
		return this.migSql;
	}

	public void setMigSql(String migSql) {
		this.migSql = migSql;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMigSqlRep() {
		return this.migSqlRep;
	}

	public void setMigSqlRep(String migSqlRep) {
		this.migSqlRep = migSqlRep;
	}

}