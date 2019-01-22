package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditvConfigcolldetail entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvConfigcolldetail implements java.io.Serializable {

	// Fields

	private Integer detailId;
	private Integer mainId;
	private Integer auditId;

	// Constructors

	/** default constructor */
	public MigAuditvConfigcolldetail() {
	}

	/** full constructor */
	public MigAuditvConfigcolldetail(Integer detailId, Integer mainId,
			Integer auditId) {
		this.detailId = detailId;
		this.mainId = mainId;
		this.auditId = auditId;
	}

	// Property accessors

	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getMainId() {
		return this.mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

}