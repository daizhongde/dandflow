package person.daizhongde.migration.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * MigControlInfo entity. @author MyEclipse Persistence Tools
 */

public class MigControlInfo implements java.io.Serializable {

	// Fields

	private String controlId;
	private String controlName;
	private String controlMark;
	private String iconCls;
//	private Set migTaskInfos = new HashSet(0);
//	private Set migControlTemplates = new HashSet(0);

	// Constructors

	/** default constructor */
	public MigControlInfo() {
	}

	/** minimal constructor */
	public MigControlInfo(String controlId) {
		this.controlId = controlId;
	}

	/** full constructor */
	public MigControlInfo(String controlId, String controlName,
			String controlMark, String iconCls) {
		this.controlId = controlId;
		this.controlName = controlName;
		this.controlMark = controlMark;
		this.iconCls = iconCls;
//		this.migTaskInfos = migTaskInfos;
//		this.migControlTemplates = migControlTemplates;
	}

	// Property accessors

	public String getControlId() {
		return this.controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public String getControlName() {
		return this.controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	public String getControlMark() {
		return this.controlMark;
	}

	public void setControlMark(String controlMark) {
		this.controlMark = controlMark;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

//	public Set getMigTaskInfos() {
//		return this.migTaskInfos;
//	}
//
//	public void setMigTaskInfos(Set migTaskInfos) {
//		this.migTaskInfos = migTaskInfos;
//	}
//
//	public Set getMigControlTemplates() {
//		return this.migControlTemplates;
//	}
//
//	public void setMigControlTemplates(Set migControlTemplates) {
//		this.migControlTemplates = migControlTemplates;
//	}

}