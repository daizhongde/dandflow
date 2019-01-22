package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditfFieldResult entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfFieldResult implements java.io.Serializable {

	// Fields

	private MigAuditfFieldResultId id;

	// Constructors

	/** default constructor */
	public MigAuditfFieldResult() {
	}

	/** full constructor */
	public MigAuditfFieldResult(MigAuditfFieldResultId id) {
		this.id = id;
	}

	// Property accessors

	public MigAuditfFieldResultId getId() {
		return this.id;
	}

	public void setId(MigAuditfFieldResultId id) {
		this.id = id;
	}

}