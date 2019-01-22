package person.daizhongde.migration.hibernate.pojo;

/**
 * MigComIns entity. @author MyEclipse Persistence Tools
 */

public class MigComIns implements java.io.Serializable {

	// Fields

	private MigComInsId id;
	private String paraValue;

	private transient String paraName;
	
	// Constructors

	/** default constructor */
	public MigComIns() {
	}

	/** minimal constructor */
	public MigComIns(MigComInsId id) {
		this.id = id;
	}

	/** full constructor */
	public MigComIns(MigComInsId id, String paraValue) {
		this.id = id;
		this.paraValue = paraValue;
	}

	// Property accessors

	public MigComInsId getId() {
		return this.id;
	}

	public void setId(MigComInsId id) {
		this.id = id;
	}
	public void setComId(String Comid) {
		this.id.setComId(Comid);
	}
	
	public void setParaId(Integer paraId) {
		this.id.setParaId(paraId);
	}
	
	public String getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
}