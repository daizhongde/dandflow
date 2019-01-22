package person.daizhongde.migration.hibernate.dto;

/**
 * MigComInfo entity. @author MyEclipse Persistence Tools
 */

public class MigComInfoDto implements java.io.Serializable {

	// Fields

	private String comId;
	private Integer paraId;
	private String paraValue;

	
	public MigComInfoDto() {
		super();
	}

	public MigComInfoDto(String comId, Integer paraId, String paraValue) {
		super();
		this.comId = comId;
		this.paraId = paraId;
		this.paraValue = paraValue;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public Integer getParaId() {
		return paraId;
	}

	public void setParaId(Integer paraId) {
		this.paraId = paraId;
	}

	public String getParaValue() {
		return this.paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	
}