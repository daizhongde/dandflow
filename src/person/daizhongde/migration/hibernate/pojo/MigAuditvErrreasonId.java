package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditvErrreasonId entity. @author MyEclipse Persistence Tools
 */

public class MigAuditvErrreasonId implements java.io.Serializable {

	// Fields

	private Integer auditId;
	private String env;

	// Constructors

	/** default constructor */
	public MigAuditvErrreasonId() {
	}

	/** full constructor */
	public MigAuditvErrreasonId(Integer auditId, String env) {
		this.auditId = auditId;
		this.env = env;
	}

	// Property accessors

	public Integer getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	public String getEnv() {
		return this.env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigAuditvErrreasonId))
			return false;
		MigAuditvErrreasonId castOther = (MigAuditvErrreasonId) other;

		return ((this.getAuditId() == castOther.getAuditId()) || (this
				.getAuditId() != null && castOther.getAuditId() != null && this
				.getAuditId().equals(castOther.getAuditId())))
				&& ((this.getEnv() == castOther.getEnv()) || (this.getEnv() != null
						&& castOther.getEnv() != null && this.getEnv().equals(
						castOther.getEnv())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAuditId() == null ? 0 : this.getAuditId().hashCode());
		result = 37 * result
				+ (getEnv() == null ? 0 : this.getEnv().hashCode());
		return result;
	}

}