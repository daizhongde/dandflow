package person.daizhongde.migration.hibernate.pojo;

/**
 * MigComInfoId entity. @author MyEclipse Persistence Tools
 */

public class MigComInfoId implements java.io.Serializable {

	// Fields

	private String comId;
	private Integer paraId;

	// Constructors

	/** default constructor */
	public MigComInfoId() {
	}

	/** full constructor */
	public MigComInfoId(String comId, Integer paraId) {
		this.comId = comId;
		this.paraId = paraId;
	}

	// Property accessors

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
		if (!(other instanceof MigComInfoId))
			return false;
		MigComInfoId castOther = (MigComInfoId) other;

		return ((this.getComId() == castOther.getComId()) || (this.getComId() != null
				&& castOther.getComId() != null && this.getComId().equals(
				castOther.getComId())))
				&& ((this.getParaId() == castOther.getParaId()) || (this
						.getParaId() != null && castOther.getParaId() != null && this
						.getParaId().equals(castOther.getParaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getComId() == null ? 0 : this.getComId().hashCode());
		result = 37 * result
				+ (getParaId() == null ? 0 : this.getParaId().hashCode());
		return result;
	}

}