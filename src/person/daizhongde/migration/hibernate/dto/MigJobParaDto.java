package person.daizhongde.migration.hibernate.dto;

/**
 * MigJobPara entity. @author MyEclipse Persistence Tools
 */

public class MigJobParaDto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8245482141345018783L;
	private String nodeId;
	private String para;
	private String paraName;
	private Integer paraType;
	private String paraValue;

	// Constructors

	/** default constructor */
	public MigJobParaDto() {
	}

	/** minimal constructor */
	public MigJobParaDto(String nodeId,  String para) {
		this.nodeId = nodeId;
		this.para = para;
	}

	/** full constructor */
	public MigJobParaDto(String nodeId,  String para, String paraName, Integer paraType,
			String paraValue) {
		this.nodeId = nodeId;
		this.para = para;
		this.paraName = paraName;
		this.paraType = paraType;
		this.paraValue = paraValue;
	}

	// Property accessors

	public String getParaName() {
		return this.paraName;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
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