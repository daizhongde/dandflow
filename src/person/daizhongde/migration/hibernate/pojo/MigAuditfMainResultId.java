package person.daizhongde.migration.hibernate.pojo;

/**
 * MigAuditfMainResultId entity. @author MyEclipse Persistence Tools
 */

public class MigAuditfMainResultId implements java.io.Serializable {

	// Fields

	private String faresMainId;
	private String faresDryrunId;
	private String faresSerial;

	// Constructors

	/** default constructor */
	public MigAuditfMainResultId() {
	}

	/** full constructor */
	public MigAuditfMainResultId(String faresMainId, String faresDryrunId,
			String faresSerial) {
		this.faresMainId = faresMainId;
		this.faresDryrunId = faresDryrunId;
		this.faresSerial = faresSerial;
	}

	// Property accessors

	public String getFaresMainId() {
		return this.faresMainId;
	}

	public void setFaresMainId(String faresMainId) {
		this.faresMainId = faresMainId;
	}

	public String getFaresDryrunId() {
		return this.faresDryrunId;
	}

	public void setFaresDryrunId(String faresDryrunId) {
		this.faresDryrunId = faresDryrunId;
	}

	public String getFaresSerial() {
		return this.faresSerial;
	}

	public void setFaresSerial(String faresSerial) {
		this.faresSerial = faresSerial;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigAuditfMainResultId))
			return false;
		MigAuditfMainResultId castOther = (MigAuditfMainResultId) other;

		return ((this.getFaresMainId() == castOther.getFaresMainId()) || (this
				.getFaresMainId() != null && castOther.getFaresMainId() != null && this
				.getFaresMainId().equals(castOther.getFaresMainId())))
				&& ((this.getFaresDryrunId() == castOther.getFaresDryrunId()) || (this
						.getFaresDryrunId() != null
						&& castOther.getFaresDryrunId() != null && this
						.getFaresDryrunId()
						.equals(castOther.getFaresDryrunId())))
				&& ((this.getFaresSerial() == castOther.getFaresSerial()) || (this
						.getFaresSerial() != null
						&& castOther.getFaresSerial() != null && this
						.getFaresSerial().equals(castOther.getFaresSerial())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFaresMainId() == null ? 0 : this.getFaresMainId()
						.hashCode());
		result = 37
				* result
				+ (getFaresDryrunId() == null ? 0 : this.getFaresDryrunId()
						.hashCode());
		result = 37
				* result
				+ (getFaresSerial() == null ? 0 : this.getFaresSerial()
						.hashCode());
		return result;
	}

}