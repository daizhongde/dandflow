package person.daizhongde.migration.hibernate.pojo;

/**
 * MigComInsId entity. @author MyEclipse Persistence Tools
 */

public class MigComInsId implements java.io.Serializable {

	// Fields

	private String jobInsId;
	private String comId;
	private Integer paraId;

	// Constructors

	/** default constructor */
	public MigComInsId() {
	}

	/** full constructor */
	public MigComInsId(String jobInsId, String comId, Integer paraId) {
		this.jobInsId = jobInsId;
		this.comId = comId;
		this.paraId = paraId;
	}

	// Property accessors

	public String getJobInsId() {
		return this.jobInsId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

	public String getComId() {
		return this.comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
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
		if (!(other instanceof MigComInsId))
			return false;
		MigComInsId castOther = (MigComInsId) other;

		return ((this.getJobInsId() == castOther.getJobInsId()) || (this
				.getJobInsId() != null && castOther.getJobInsId() != null && this
				.getJobInsId().equals(castOther.getJobInsId())))
				&& ((this.getComId() == castOther.getComId()) || (this
						.getComId() != null && castOther.getComId() != null && this
						.getComId().equals(castOther.getComId())))
				&& ((this.getParaId() == castOther.getParaId()) || (this
						.getParaId() != null && castOther.getParaId() != null && this
						.getParaId().equals(castOther.getParaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getJobInsId() == null ? 0 : this.getJobInsId().hashCode());
		result = 37 * result
				+ (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result
				+ (getParaId() == null ? 0 : this.getParaId().hashCode());
		return result;
	}

}