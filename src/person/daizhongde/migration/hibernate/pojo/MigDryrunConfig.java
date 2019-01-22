package person.daizhongde.migration.hibernate.pojo;

/**
 * MigDryrunConfig entity. @author MyEclipse Persistence Tools
 */

public class MigDryrunConfig implements java.io.Serializable {

	// Fields

	private Integer migDryrunId;
	private String migDryrunName;
	private String remark;

	// Constructors

	/** default constructor */
	public MigDryrunConfig() {
	}

	/** minimal constructor */
	public MigDryrunConfig(Integer migDryrunId, String migDryrunName) {
		this.migDryrunId = migDryrunId;
		this.migDryrunName = migDryrunName;
	}

	/** full constructor */
	public MigDryrunConfig(Integer migDryrunId, String migDryrunName,
			String remark) {
		this.migDryrunId = migDryrunId;
		this.migDryrunName = migDryrunName;
		this.remark = remark;
	}

	// Property accessors

	public Integer getMigDryrunId() {
		return this.migDryrunId;
	}

	public void setMigDryrunId(Integer migDryrunId) {
		this.migDryrunId = migDryrunId;
	}

	public String getMigDryrunName() {
		return this.migDryrunName;
	}

	public void setMigDryrunName(String migDryrunName) {
		this.migDryrunName = migDryrunName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}