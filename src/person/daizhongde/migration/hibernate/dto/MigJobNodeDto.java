package person.daizhongde.migration.hibernate.dto;

import java.util.List;

public class MigJobNodeDto {
	private String id;
	private int isLeaf;
	private String prepos;
	private String postpos;
	private String coords;
	private String dnodeName;
	private String jobRemark;
	
	private List<MigJobParaDto> paras;
	private String controlId;
	private List<MigComInfoDto> comInfos;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getPrepos() {
		return prepos;
	}
	public void setPrepos(String prepos) {
		this.prepos = prepos;
	}
	public String getPostpos() {
		return postpos;
	}
	public void setPostpos(String postpos) {
		this.postpos = postpos;
	}
	public String getCoords() {
		return coords;
	}
	public void setCoords(String coords) {
		this.coords = coords;
	}
	public String getDnodeName() {
		return dnodeName;
	}
	public void setDnodeName(String dnodeName) {
		this.dnodeName = dnodeName;
	}
	public String getJobRemark() {
		return jobRemark;
	}
	public void setJobRemark(String jobRemark) {
		this.jobRemark = jobRemark;
	}
	public List<MigJobParaDto> getParas() {
		return paras;
	}
	public void setParas(List<MigJobParaDto> paras) {
		this.paras = paras;
	}
	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public List<MigComInfoDto> getComInfos() {
		return comInfos;
	}
	public void setComInfos(List<MigComInfoDto> comInfos) {
		this.comInfos = comInfos;
	}
	
}
