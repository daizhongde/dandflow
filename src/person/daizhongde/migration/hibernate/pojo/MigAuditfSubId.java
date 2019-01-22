package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditfSubId entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfSubId implements java.io.Serializable {

	// Fields

	private Integer fauditSubIndex;
	private String fauditMainId;

	// Constructors

	/** default constructor */
	public MigAuditfSubId() {
	}

	/** full constructor */
	public MigAuditfSubId(Integer fauditSubIndex, String fauditMainId) {
		this.fauditSubIndex = fauditSubIndex;
		this.fauditMainId = fauditMainId;
	}

	// Property accessors

	public Integer getFauditSubIndex() {
		return this.fauditSubIndex;
	}

	public void setFauditSubIndex(Integer fauditSubIndex) {
		this.fauditSubIndex = fauditSubIndex;
	}

	public String getFauditMainId() {
		return this.fauditMainId;
	}

	public void setFauditMainId(String fauditMainId) {
		this.fauditMainId = fauditMainId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigAuditfSubId))
			return false;
		MigAuditfSubId castOther = (MigAuditfSubId) other;

		return ((this.getFauditSubIndex() == castOther.getFauditSubIndex()) || (this
				.getFauditSubIndex() != null
				&& castOther.getFauditSubIndex() != null && this
				.getFauditSubIndex().equals(castOther.getFauditSubIndex())))
				&& ((this.getFauditMainId() == castOther.getFauditMainId()) || (this
						.getFauditMainId() != null
						&& castOther.getFauditMainId() != null && this
						.getFauditMainId().equals(castOther.getFauditMainId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFauditSubIndex() == null ? 0 : this.getFauditSubIndex()
						.hashCode());
		result = 37
				* result
				+ (getFauditMainId() == null ? 0 : this.getFauditMainId()
						.hashCode());
		return result;
	}

}