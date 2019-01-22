package person.daizhongde.migration.hibernate.pojo;

/**
 * MigJobContentId entity. @author MyEclipse Persistence Tools
 */

public class MigJobContentId implements java.io.Serializable {

	// Fields

	private String jobId;
	private String nodeId;

	// Constructors

	/** default constructor */
	public MigJobContentId() {
	}

	/** full constructor */
	public MigJobContentId(String jobId, String nodeId) {
		this.jobId = jobId;
		this.nodeId = nodeId;
	}

	// Property accessors

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MigJobContentId))
			return false;
		MigJobContentId castOther = (MigJobContentId) other;

		return ((this.getJobId() == castOther.getJobId()) || (this.getJobId() != null
				&& castOther.getJobId() != null && this.getJobId().equals(
				castOther.getJobId())))
				&& ((this.getNodeId() == castOther.getNodeId()) || (this
						.getNodeId() != null && castOther.getNodeId() != null && this
						.getNodeId().equals(castOther.getNodeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getJobId() == null ? 0 : this.getJobId().hashCode());
		result = 37 * result
				+ (getNodeId() == null ? 0 : this.getNodeId().hashCode());
		return result;
	}

}