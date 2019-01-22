package person.daizhongde.migration.hibernate.pojo;

/**
 * MigLoadMap entity. @author MyEclipse Persistence Tools
 */

public class MigLoadMap implements java.io.Serializable {

	// Fields

	private MigLoadMapId id;

	// Constructors

	/** default constructor */
	public MigLoadMap() {
	}

	/** full constructor */
	public MigLoadMap(MigLoadMapId id) {
		this.id = id;
	}

	// Property accessors

	public MigLoadMapId getId() {
		return this.id;
	}

	public void setId(MigLoadMapId id) {
		this.id = id;
	}

}