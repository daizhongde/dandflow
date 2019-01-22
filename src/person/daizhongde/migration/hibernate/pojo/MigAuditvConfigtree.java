package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigAuditvConfigtree entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvConfigtree implements java.io.Serializable {

	// Fields

	private Integer id;
	private String parent;
	private String name;
	private Integer content;
	private Short isleaf;
	private Short status;
	private String remark;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public MigAuditvConfigtree() {
	}

	/** minimal constructor */
	public MigAuditvConfigtree(Integer id, String name, Short isleaf,
			Short status, Timestamp ctime) {
		this.id = id;
		this.name = name;
		this.isleaf = isleaf;
		this.status = status;
		this.ctime = ctime;
	}

	/** full constructor */
	public MigAuditvConfigtree(Integer id, String parent, String name,
			Integer content, Short isleaf, Short status, String remark,
			Timestamp ctime) {
		this.id = id;
		this.parent = parent;
		this.name = name;
		this.content = content;
		this.isleaf = isleaf;
		this.status = status;
		this.remark = remark;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getContent() {
		return this.content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}

	public Short getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(Short isleaf) {
		this.isleaf = isleaf;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

}