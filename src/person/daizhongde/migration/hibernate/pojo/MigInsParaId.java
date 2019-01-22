package person.daizhongde.migration.hibernate.pojo;

/**
 * MigInsParaId entity. @author MyEclipse Persistence Tools
 */

public class MigInsParaId implements java.io.Serializable {

	// Fields

	private String jobInsId;
	private String nodeId;
	private String para;

	// Constructors

	/** default constructor */
	public MigInsParaId() {
	}

	/** full constructor */
	public MigInsParaId(String jobInsId, String nodeId, String para) {
		this.jobInsId = jobInsId;
		this.nodeId = nodeId;
		this.para = para;
	}

	// Property accessors

	public String getJobInsId() {
		return this.jobInsId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

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
		if (!(other instanceof MigInsParaId))
			return false;
		MigInsParaId castOther = (MigInsParaId) other;

		return ((this.getJobInsId() == castOther.getJobInsId()) || (this
				.getJobInsId() != null && castOther.getJobInsId() != null && this
				.getJobInsId().equals(castOther.getJobInsId())))
				&& ((this.getNodeId() == castOther.getNodeId()) || (this
						.getNodeId() != null && castOther.getNodeId() != null && this
						.getNodeId().equals(castOther.getNodeId())))
				&& ((this.getPara() == castOther.getPara()) || (this.getPara() != null
						&& castOther.getPara() != null && this.getPara()
						.equals(castOther.getPara())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getJobInsId() == null ? 0 : this.getJobInsId().hashCode());
		result = 37 * result
				+ (getNodeId() == null ? 0 : this.getNodeId().hashCode());
		result = 37 * result
				+ (getPara() == null ? 0 : this.getPara().hashCode());
		return result;
	}

}