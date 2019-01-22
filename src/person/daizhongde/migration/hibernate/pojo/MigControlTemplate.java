package person.daizhongde.migration.hibernate.pojo;

/**
 * MigControlTemplate entity. @author MyEclipse Persistence Tools
 */

public class MigControlTemplate implements java.io.Serializable {

	// Fields

	private MigControlTemplateId id;
	private String paraName;
	private Integer nullable;
	private Integer isNumber;
	private String defValue;
	private Integer inputType;
	private String codeType;
	private String checkrule;
	private String remark;

	// Constructors

	/** default constructor */
	public MigControlTemplate() {
	}

	/** minimal constructor */
	public MigControlTemplate(MigControlTemplateId id, String paraName) {
		this.id = id;
		this.paraName = paraName;
	}

	/** full constructor */
	public MigControlTemplate(MigControlTemplateId id, String paraName,
			Integer nullable, Integer isNumber, String defValue,
			Integer inputType, String codeType, String checkrule, String remark) {
		this.id = id;
		this.paraName = paraName;
		this.nullable = nullable;
		this.isNumber = isNumber;
		this.defValue = defValue;
		this.inputType = inputType;
		this.codeType = codeType;
		this.checkrule = checkrule;
		this.remark = remark;
	}

	// Property accessors

	public MigControlTemplateId getId() {
		return this.id;
	}

	public void setId(MigControlTemplateId id) {
		this.id = id;
	}

	public String getParaName() {
		return this.paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public Integer getNullable() {
		return this.nullable;
	}

	public void setNullable(Integer nullable) {
		this.nullable = nullable;
	}

	public Integer getIsNumber() {
		return this.isNumber;
	}

	public void setIsNumber(Integer isNumber) {
		this.isNumber = isNumber;
	}

	public String getDefValue() {
		return this.defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public Integer getInputType() {
		return this.inputType;
	}

	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCheckrule() {
		return this.checkrule;
	}

	public void setCheckrule(String checkrule) {
		this.checkrule = checkrule;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}