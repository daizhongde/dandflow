package person.daizhongde.migration.hibernate.pojo;

/**
 * MigControlTemplateId entity. @author MyEclipse Persistence Tools
 */

public class MigControlTemplateId implements java.io.Serializable {

	// Fields

	private String controlId;
	private Integer paraId;

	// Constructors

	/** default constructor */
	public MigControlTemplateId() {
	}

	/** full constructor */
	public MigControlTemplateId(String controlId, Integer paraId) {
		this.controlId = controlId;
		this.paraId = paraId;
	}

	// Property accessors

	public String getControlId() {
		return this.controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}

	public Integer getParaId() {
		return this.paraId;
	}

	public void setParaId(Integer paraId) {
		this.paraId = paraId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigControlTemplateId))
			return false;
		MigControlTemplateId castOther = (MigControlTemplateId) other;

		return ((this.getControlId() == castOther.getControlId()) || (this
				.getControlId() != null && castOther.getControlId() != null && this
				.getControlId().equals(castOther.getControlId())))
				&& ((this.getParaId() == castOther.getParaId()) || (this
						.getParaId() != null && castOther.getParaId() != null && this
						.getParaId().equals(castOther.getParaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getControlId() == null ? 0 : this.getControlId().hashCode());
		result = 37 * result
				+ (getParaId() == null ? 0 : this.getParaId().hashCode());
		return result;
	}

}