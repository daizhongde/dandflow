package person.daizhongde.migration.hibernate.dto;

/**
 * MigControlTemplate entity. @author MyEclipse Persistence Tools
 */

public class MigControlTemplateDto implements java.io.Serializable {

	// Fields

	private String controlId;
	private Integer paraId;
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
	public MigControlTemplateDto() {
	}


	public MigControlTemplateDto(String controlId, Integer paraId,
			String paraName, Integer nullable, Integer isNumber,
			String defValue, Integer inputType, String codeType,
			String checkrule, String remark) {
		super();
		this.controlId = controlId;
		this.paraId = paraId;
		this.paraName = paraName;
		this.nullable = nullable;
		this.isNumber = isNumber;
		this.defValue = defValue;
		this.inputType = inputType;
		this.codeType = codeType;
		this.checkrule = checkrule;
		this.remark = remark;
	}


	public String getControlId() {
		return controlId;
	}


	public void setControlId(String controlId) {
		this.controlId = controlId;
	}


	public Integer getParaId() {
		return paraId;
	}


	public void setParaId(Integer paraId) {
		this.paraId = paraId;
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