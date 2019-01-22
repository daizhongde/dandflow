package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditcConsistency entity. @author MyEclipse Persistence Tools
 */

public class MigAuditcConsistency implements java.io.Serializable {

	// Fields

	private Integer auditId;
	private String auditName;
	private String migSql;
	private String author;
	private String migSqlRep;
	private String sqlDb;

	// Constructors

	/** default constructor */
	public MigAuditcConsistency() {
	}

	/** minimal constructor */
	public MigAuditcConsistency(String auditName, String migSql, String sqlDb) {
		this.auditName = auditName;
		this.migSql = migSql;
		this.sqlDb = sqlDb;
	}

	/** full constructor */
	public MigAuditcConsistency(String auditName, String migSql, String author,
			String migSqlRep, String sqlDb) {
		this.auditName = auditName;
		this.migSql = migSql;
		this.author = author;
		this.migSqlRep = migSqlRep;
		this.sqlDb = sqlDb;
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

	public String getSqlDb() {
		return this.sqlDb;
	}

	public void setSqlDb(String sqlDb) {
		this.sqlDb = sqlDb;
	}

}