package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditfDetailResult entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfDetailResult implements java.io.Serializable {

	// Fields

	private MigAuditfDetailResultId id;

	// Constructors

	/** default constructor */
	public MigAuditfDetailResult() {
	}

	/** full constructor */
	public MigAuditfDetailResult(MigAuditfDetailResultId id) {
		this.id = id;
	}

	// Property accessors

	public MigAuditfDetailResultId getId() {
		return this.id;
	}

	public void setId(MigAuditfDetailResultId id) {
		this.id = id;
	}

}