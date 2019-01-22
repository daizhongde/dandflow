package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditfDetailResultHis entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfDetailResultHis implements java.io.Serializable {

	// Fields

	private MigAuditfDetailResultHisId id;

	// Constructors

	/** default constructor */
	public MigAuditfDetailResultHis() {
	}

	/** full constructor */
	public MigAuditfDetailResultHis(MigAuditfDetailResultHisId id) {
		this.id = id;
	}

	// Property accessors

	public MigAuditfDetailResultHisId getId() {
		return this.id;
	}

	public void setId(MigAuditfDetailResultHisId id) {
		this.id = id;
	}

}