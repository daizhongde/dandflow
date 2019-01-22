package person.daizhongde.migration.hibernate.pojo;

/**
 * MigBusiDomain entity. @author MyEclipse Persistence Tools
 */

public class MigBusiDomain implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String remark;

	// Constructors

	/** default constructor */
	public MigBusiDomain() {
	}

	/** minimal constructor */
	public MigBusiDomain(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public MigBusiDomain(Integer id, String name, String remark) {
		this.id = id;
		this.name = name;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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