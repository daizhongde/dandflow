package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MigConfigConnection entity. @author MyEclipse Persistence Tools
 */

public class MigConfigConnection implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String url;
	private String remark;
	private Timestamp ctime;

	// Constructors

	/** default constructor */
	public MigConfigConnection() {
	}

	/** minimal constructor */
	public MigConfigConnection(String name, String url, Timestamp ctime) {
		this.name = name;
		this.url = url;
		this.ctime = ctime;
	}

	/** full constructor */
	public MigConfigConnection(String name, String url, String remark,
			Timestamp ctime) {
		this.name = name;
		this.url = url;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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