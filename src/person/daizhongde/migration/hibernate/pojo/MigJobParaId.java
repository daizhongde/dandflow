package person.daizhongde.migration.hibernate.pojo;

/**
 * MigJobParaId entity. @author MyEclipse Persistence Tools
 */

public class MigJobParaId implements java.io.Serializable {

	// Fields

	private String nodeId;
	private String para;

	// Constructors

	/** default constructor */
	public MigJobParaId() {
	}

	/** full constructor */
	public MigJobParaId(String nodeId, String para) {
		this.nodeId = nodeId;
		this.para = para;
	}

	// Property accessors

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getPara() {
		return this.para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigJobParaId))
			return false;
		MigJobParaId castOther = (MigJobParaId) other;

		return ((this.getNodeId() == castOther.getNodeId()) || (this
				.getNodeId() != null && castOther.getNodeId() != null && this
				.getNodeId().equals(castOther.getNodeId())))
				&& ((this.getPara() == castOther.getPara()) || (this.getPara() != null
						&& castOther.getPara() != null && this.getPara()
						.equals(castOther.getPara())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getNodeId() == null ? 0 : this.getNodeId().hashCode());
		result = 37 * result
				+ (getPara() == null ? 0 : this.getPara().hashCode());
		return result;
	}

}