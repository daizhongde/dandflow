package person.daizhongde.migration.hibernate.pojo;

/**
 * MigComInfo entity. @author MyEclipse Persistence Tools
 */

public class MigComInfo implements java.io.Serializable {

	// Fields

	private MigComInfoId id;
	private String paraValue;

	/** add by daizhongde, this field don't presistent  **/
//	private transient String paraName;
//	private transient MigControlTemplate controlTPL;
	
	// Constructors

	/** default constructor */
	public MigComInfo() {
	}

	/** minimal constructor */
	public MigComInfo(MigComInfoId id) {
		this.id = id;
	}

	/** full constructor */
	public MigComInfo(MigComInfoId id, String paraValue) {
		this.id = id;
		this.paraValue = paraValue;
	}

	// Property accessors

	public MigComInfoId getId() {
		return this.id;
	}

	public void setId(MigComInfoId id) {
		this.id = id;
	}

	public String getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
//	public String getParaName() {
//		return paraName;
//	}
//
//	public void setParaName(String paraName) {
//		this.paraName = paraName;
//	}

//	public MigControlTemplate getControlTPL() {
//		return controlTPL;
//	}
//
//	public void setControlTPL(MigControlTemplate controlTPL) {
//		this.controlTPL = controlTPL;
//	}
	
}