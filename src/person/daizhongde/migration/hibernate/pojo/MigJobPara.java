package person.daizhongde.migration.hibernate.pojo;

/**
 * MigJobPara entity. @author MyEclipse Persistence Tools
 */

public class MigJobPara implements java.io.Serializable {

	// Fields

	private MigJobParaId id;
	private String paraName;
	private Integer paraType;
	private String paraValue;

	// Constructors

	/** default constructor */
	public MigJobPara() {
	}

	/** minimal constructor */
	public MigJobPara(MigJobParaId id) {
		this.id = id;
	}

	/** full constructor */
	public MigJobPara(MigJobParaId id, String paraName, Integer paraType,
			String paraValue) {
		this.id = id;
		this.paraName = paraName;
		this.paraType = paraType;
		this.paraValue = paraValue;
	}

	// Property accessors

	public MigJobParaId getId() {
		return this.id;
	}

	public void setId(MigJobParaId id) {
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