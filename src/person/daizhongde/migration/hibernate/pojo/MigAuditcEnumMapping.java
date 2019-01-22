package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditcEnumMapping entity. @author MyEclipse Persistence Tools
 */

public class MigAuditcEnumMapping implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer entity;
	private String auditName;
	private String enumDesc;
	private String srcEnum;
	private String dstEnum;

	// Constructors

	/** default constructor */
	public MigAuditcEnumMapping() {
	}

	/** minimal constructor */
	public MigAuditcEnumMapping(Integer entity) {
		this.entity = entity;
	}

	/** full constructor */
	public MigAuditcEnumMapping(Integer entity, String auditName,
			String enumDesc, String srcEnum, String dstEnum) {
		this.entity = entity;
		this.auditName = auditName;
		this.enumDesc = enumDesc;
		this.srcEnum = srcEnum;
		this.dstEnum = dstEnum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEntity() {
		return this.entity;
	}

	public void setEntity(Integer entity) {
		this.entity = entity;
	}

	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getEnumDesc() {
		return this.enumDesc;
	}

	public void setEnumDesc(String enumDesc) {
		this.enumDesc = enumDesc;
	}

	public String getSrcEnum() {
		return this.srcEnum;
	}

	public void setSrcEnum(String srcEnum) {
		this.srcEnum = srcEnum;
	}

	public String getDstEnum() {
		return this.dstEnum;
	}

	public void setDstEnum(String dstEnum) {
		this.dstEnum = dstEnum;
	}

}