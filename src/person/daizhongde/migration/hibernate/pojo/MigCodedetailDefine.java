package person.daizhongde.migration.hibernate.pojo;

/**
 * MigCodedetailDefine entity. @author MyEclipse Persistence Tools
 */

public class MigCodedetailDefine implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String code;
	private String value;
	private String remark;

	// Constructors

	/** default constructor */
	public MigCodedetailDefine() {
	}

	/** minimal constructor */
	public MigCodedetailDefine(Integer id, String type, String code) {
		this.id = id;
		this.type = type;
		this.code = code;
	}

	/** full constructor */
	public MigCodedetailDefine(Integer id, String type, String code,
			String value, String remark) {
		this.id = id;
		this.type = type;
		this.code = code;
		this.value = value;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}