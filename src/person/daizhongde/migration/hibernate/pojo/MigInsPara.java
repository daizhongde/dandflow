package person.daizhongde.migration.hibernate.pojo;

/**
 * MigInsPara entity. @author MyEclipse Persistence Tools
 */

public class MigInsPara implements java.io.Serializable {

	// Fields

	private MigInsParaId id;
	private String paraName;
	private Integer paraType;
	private String paraValue;

	// Constructors

	/** default constructor */
	public MigInsPara() {
	}

	/** minimal constructor */
	public MigInsPara(MigInsParaId id) {
		this.id = id;
	}

	/** full constructor */
	public MigInsPara(MigInsParaId id, String paraName, Integer paraType,
			String paraValue) {
		this.id = id;
		this.paraName = paraName;
		this.paraType = paraType;
		this.paraValue = paraValue;
	}

	// Property accessors

	public MigInsParaId getId() {
		return this.id;
	}

	public void setId(MigInsParaId id) {
		this.id = id;
	}

	public String getParaName() {
		return this.paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public Integer getParaType() {
		return this.paraType;
	}

	public void setParaType(Integer paraType) {
		this.paraType = paraType;
	}

	public String getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}

}