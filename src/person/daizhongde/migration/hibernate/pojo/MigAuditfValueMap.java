package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditfValueMap entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfValueMap implements java.io.Serializable {

	// Fields

	private MigAuditfValueMapId id;

	// Constructors

	/** default constructor */
	public MigAuditfValueMap() {
	}

	/** full constructor */
	public MigAuditfValueMap(MigAuditfValueMapId id) {
		this.id = id;
	}

	// Property accessors

	public MigAuditfValueMapId getId() {
		return this.id;
	}

	public void setId(MigAuditfValueMapId id) {
		this.id = id;
	}

}