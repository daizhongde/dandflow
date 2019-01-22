package junit.test;

/**
 * AbstractTIsoftstoneEmployee entity provides the base persistence definition
 * of the TIsoftstoneEmployee entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTIsoftstoneEmployee implements
		java.io.Serializable {

	// Fields

	private TIsoftstoneEmployeeId id;

	// Constructors

	/** default constructor */
	public AbstractTIsoftstoneEmployee() {
	}

	/** full constructor */
	public AbstractTIsoftstoneEmployee(TIsoftstoneEmployeeId id) {
		this.id = id;
	}

	// Property accessors

	public TIsoftstoneEmployeeId getId() {
		return this.id;
	}

	public void setId(TIsoftstoneEmployeeId id) {
		this.id = id;
	}

}