package person.daizhongde.migration.hibernate.pojo;

import java.sql.Timestamp;

/**
 * TChatMsg entity. @author MyEclipse Persistence Tools
 */

public class TChatMsg implements java.io.Serializable {

	// Fields

	private Integer NMid;
	private Integer NUid;
	private String CMsg;
	private Timestamp DMstime;

	// Constructors

	/** default constructor */
	public TChatMsg() {
	}

	/** full constructor */
	public TChatMsg(Integer NUid, String CMsg) {
		this.NUid = NUid;
		this.CMsg = CMsg;
	}

	// Property accessors

	public Integer getNMid() {
		return this.NMid;
	}

	public void setNMid(Integer NMid) {
		this.NMid = NMid;
	}

	public Integer getNUid() {
		return this.NUid;
	}

	public void setNUid(Integer NUid) {
		this.NUid = NUid;
	}

	public String getCMsg() {
		return this.CMsg;
	}

	public void setCMsg(String CMsg) {
		this.CMsg = CMsg;
	}

	public Timestamp getDMstime() {
		return this.DMstime;
	}

	public void setDMstime(Timestamp DMstime) {
		this.DMstime = DMstime;
	}

}