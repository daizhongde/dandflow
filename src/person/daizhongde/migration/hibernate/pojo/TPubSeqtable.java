package person.daizhongde.migration.hibernate.pojo;

/**
 * TPubSeqtable entity. @author MyEclipse Persistence Tools
 */

public class TPubSeqtable implements java.io.Serializable {

	// Fields

	private String seqName;
	private Integer seqValue;
	private String seqTime;
	private String prefix;

	// Constructors

	/** default constructor */
	public TPubSeqtable() {
	}

	/** minimal constructor */
	public TPubSeqtable(String seqName, Integer seqValue) {
		this.seqName = seqName;
		this.seqValue = seqValue;
	}

	/** full constructor */
	public TPubSeqtable(String seqName, Integer seqValue, String seqTime,
			String prefix) {
		this.seqName = seqName;
		this.seqValue = seqValue;
		this.seqTime = seqTime;
		this.prefix = prefix;
	}

	// Property accessors

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public Integer getSeqValue() {
		return this.seqValue;
	}

	public void setSeqValue(Integer seqValue) {
		this.seqValue = seqValue;
	}

	public String getSeqTime() {
		return this.seqTime;
	}

	public void setSeqTime(String seqTime) {
		this.seqTime = seqTime;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}