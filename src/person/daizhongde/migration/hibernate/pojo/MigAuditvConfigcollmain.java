package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditvConfigcollmain entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvConfigcollmain implements java.io.Serializable {

	// Fields

	private Integer mainId;
	private String name;
	private String remark;

	// Constructors

	/** default constructor */
	public MigAuditvConfigcollmain() {
	}

	/** full constructor */
	public MigAuditvConfigcollmain(Integer mainId, String name, String remark) {
		this.mainId = mainId;
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

	public Integer getMainId() {
		return this.mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}