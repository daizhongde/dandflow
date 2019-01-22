package person.daizhongde.migration.hibernate.pojo;

/**
 * MigInsConfig entity. @author MyEclipse Persistence Tools
 */

public class MigInsConfig implements java.io.Serializable {

	// Fields

	private MigInsConfigId id;

	// Constructors

	/** default constructor */
	public MigInsConfig() {
	}

	/** full constructor */
	public MigInsConfig(MigInsConfigId id) {
		this.id = id;
	}

	// Property accessors

	public MigInsConfigId getId() {
		return this.id;
	}

	public void setId(MigInsConfigId id) {
		this.id = id;
	}

}